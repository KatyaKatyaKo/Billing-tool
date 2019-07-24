package serie51;

import connexion.connexionFichier;
import iConsole.ES;
import mesExceptions.AbandonException;
import mesInterfaces.interfaceGestion;


public class GestionTableDesArticles51 implements interfaceGestion<TableArticle51> {
	private ES ES = new ES();
	private String nomPhysique;
	private connexionFichier<TableArticle51> fichArt;
	
	//Constructeur - on récupère grace au constructeur qui ocntient le nomPhysique fichier
	public GestionTableDesArticles51(String nomPhysique) {
		this.nomPhysique=nomPhysique;
		fichArt = new connexionFichier<TableArticle51>(nomPhysique);
	}
	
	//Getters-Setters
	public String getNomPhysique() {
		return nomPhysique;
	}

	public void setNomPhysique(String nomPhysique) {
		this.nomPhysique = nomPhysique;
	}
	
	//Méthodes
	public TableArticle51 recuperer() {
		TableArticle51 tabArt = fichArt.recuperer();
		if(tabArt==null) {
			//ES.affiche("NOUVEAU FICHIER A CREER");
			tabArt = new TableArticle51(); //On récupère les articles par défaut depui sla callse si le fichier est vide
		}
		return tabArt;
	}
	
	public void ecrire(TableArticle51 tabArt) {
		fichArt.ecrire(tabArt);
	}
	
	public void fermer(TableArticle51 tabArt) {
		fichArt.fermer(tabArt);
	}
	//************************************** MENU GESTION STOCK ****************************************
	
	public int menuChoix() throws AbandonException { //ICI ON NE TRAITE PAS L EXCEPTION, ON LE LAISSE TRAITER PAR LE MAIN ==> iIL VA ABANDONNER DIRECTEMENT L APPLICATION
		String menu = "\n\t\t GESTION des ARTICLES \n"
					 +"\n\t CREER un NOUVEL ARTICLE..1" 
				     +"\n\t SUPPRIMER UN ARTICLE.....2" 
				     +"\n\t MODIFIER UN ARTICLE......3"
				     +"\n\t AFFICHER LE STOCK........4" 
				     +"\n\t AFFICHER LES PROMOTIONS..5" 
				     +"\n\t TRANSFORMER EN PROMO.....6" 
				     +"\n\t FIN......................0" 
					 + "\n\t\t\t\t VOTRE CHOIX:";
		return (int)ES.saisie(menu, 0, 5);		
	}
	
	public void menuGeneral(TableArticle51 tabArt, Object...obj ) throws AbandonException {
		int choix;

		TableDesCommandes51	tabCde = (TableDesCommandes51) obj[0]; //CAST DE PARAM 0
	 do {
		 choix = menuChoix();
		 switch(choix) {
		 case 1: creer(tabArt); break;
		 case 2: supprimer(tabArt, tabCde); break; //CASCADE
		 case 3: modifier(tabArt); break;
		 case 4: afficher(tabArt); break;
		 case 5: afficherPromo(tabArt); break;
		 case 6: transformer(tabArt); break;
		 case 0: break;	 
		 }
	 }
		while(choix!=0); 
	}
	
	//************************************** METHODES GESTION STOCK ****************************************
	
	//REVERIFIER LA METHODE
	private void transformer(TableArticle51 tabArt) throws AbandonException {
		int code = (int)ES.saisie("\n\t CHOISSISSEZ UN ARTICLE A TRANSFORMER: "+tabArt.cle(), 1);
		ArticleAbstract51 art = tabArt.retourner(code);
		if(art==null) ES.affiche("\t ARTICLE N'EXISTE PAS\n");
		else {
			if(art instanceof ArticlePromo51) {art=new Article51(art.getCode(),art.getDesignation(),art.getPu());
				tabArt.ajouter(art); //PAS BESOIN DE SUPPRIMER C ES TLE MEME CODE
			}
			else {
				int quantite  = (int)ES.saisie("\n\t ARTICLE TRANSFORME EN PROMO: ", 1);
				float reduction = (float)ES.saisie("\n\t ARTICLE TRANSFORME EN PROMO: ", 0f);
				art=new ArticlePromo51(art.getCode(),art.getDesignation(),art.getPu(), quantite, reduction);
				tabArt.ajouter(art);
			}
		}
	}

			//CREER un NOUVEL ARTICLE..1 
			public void creer(TableArticle51 tabArt, Object... objects) throws AbandonException {
				ArticleAbstract51 art = saisieArt(tabArt);
				if(art!=null) {
					tabArt.ajouter(art); //A CE MOMENT EST INITIALISE LA TABLE DES COMMANDES AVEC 5 ARTICLES	
				}
				else ES.affiche("\t CODE ARTICLE EXISTE DEJA\n");
			}
			
			private ArticleAbstract51 saisieArt(TableArticle51 tabArt) throws AbandonException { //Comment afficher pour que ca sera sur la meme ligne
				int code;
				code=(int)ES.saisie("\n\t SAISISSEZ LE CODE DE L'ARTICLE\n\t (Le premier numéro disponible est " + (tabArt.premierNumDispo()) + ")",1); //COMPLETER LE MESSAGE!!!!!
				if (tabArt.retourner(code)== null) {
					String designation = ES.saisie("\t DESIGNATION ARTICLE: ");
					designation=designation.toUpperCase(); 
					//NE MARCHE PAS AVEC LE FLOAT DANS LA METHIDE SAISIE
					int pu = (int)ES.saisie("\t PRIX UNITAIRE (saisissez un entier): ", 1); //RAJOUTER UNE METHODE LIRE FLOAT
					
				
					if (ES.saisieOuiNon(" Promo Ou Pas? "))
					{ // Article promo
						int mini = (int)ES.saisie(" Quelle Quantite Mini? ", 1);
						//NE MARCHE PAS AVEC LE FLOAT DANS LA METHIDE SAISIE
						int reduc= (int)ES.saisie(" Combien de reduction? ", 1);
						return new ArticlePromo51(code, designation,pu, mini, reduc);
						
					}
					
					return new Article51(code, designation, pu); 
				}
				return null;
			}
			
			//SUPPRIMER UN ARTICLE.....2  // Cascade des valeurs
			public  void supprimer(TableArticle51 tabArt, Object...obj ) throws AbandonException { //TableDesCommandes51 tabCde
				TableDesCommandes51 tabCde = (TableDesCommandes51)obj[0];
				int code = (int)ES.saisie("\n\t CHOISSIEZ un ARTICLE a SUPPRIMER"+tabArt.cle()+":", 1);
				if (tabArt.retourner(code)==null) {
					ES.affiche("\t ARTICLE N'EXISTE PAS\n");
				}
				else {
					tabArt.supprimer(code);
					tabCde.purge(code);
					ES.affiche("\t ARTICLE A ETE SUPPRIME\n");
				}
			}
			
			//MODIFIER UN ARTICLE......3  
			public void modifier(TableArticle51 tabArt, Object... objects) throws AbandonException { //On modifie la designation
				int code = (int)ES.saisie("\n\t CHOISSISSEZ UN ARTICLE A MODIFIER: "+tabArt.cle(), 1);
				ArticleAbstract51 art = tabArt.retourner(code);
				if(art==null) {
					ES.affiche("\t ARTICLE N'EXISTE PAS\n");
				}
				else {
					if (art instanceof ArticlePromo51) {
						System.out.println("\n\t\t C'EST UN ARTICLE PROMO");
						int quantiteMini = (int)ES.saisie("\n\t SAISISSEZ LA NOUVELLE QUANTITE MINIMALE (Actuellement "+art.getQuantiteMini()+"):", 1, Integer.MAX_VALUE);
						art.setQuantiteMini(quantiteMini);
						if (quantiteMini!=0)  //n est pas vide parce qu'il y a un retour chariot avant ca veut dire qu on a pas rajouter un article, parce quand on ajoute un article on l'ajoute lcean, en supprimant le retour chariot (voir saisie dans DataUser)
							art.setQuantiteMini(quantiteMini);
						//!!!!!!ICI PROBLEME DE CONVERSION FLOAT STRING!!!!!!
						float reduc = (int)ES.saisie("\n\t SAISISSEZ LA NOUVELLE REDUCTION (Actuellement "+art.getReduc()+"):", 1);
							art.setReduc(reduc);
					}
					String designation = ES.saisie("\n\t SAISISSEZ LA NOUVELLE DESIGNATION:"); //On appue enter (rc) si on veut rien modifier
					if (!designation.equals("")) { //n est pas vide parce qu'il y a un retour chariot avant ca veut dire qu on a pas rajouter un article, parce quand on ajoute un article on l'ajoute lcean, en supprimant le retour chariot (voir saisie dans DataUser)
						art.setDesignation(designation.toUpperCase()); //Affichage at UpperCase
					}
					String pu  = ES.saisie("\n\t SAISISSEZ LE NOUVEAU PRIX: "); //On appue enter (rc) si on veut rien modifier
					if (!pu.equals("")) art.setPu(Float.parseFloat(pu));	
				}
				}
	
			//AFFICHER LE STOCK........4
			public void afficher(TableArticle51 tabArt) { //Correspond à 4 - afficher le stock
				if (tabArt.taille() == 0)
					ES.affiche("STOCK VIDE)");
				else{
					ES.affiche(tabArt.toString());
				}
			}
			
			//AFFICHER LES PROMOTIONS........5
			public void afficherPromo(TableArticle51 tabArt)
			{
				if (tabArt.taille()==0) ES.affiche("\n ** AUCUN ARTICLE EN STOCK ");
				else ES.affiche(tabArt.promo());
			}
}
