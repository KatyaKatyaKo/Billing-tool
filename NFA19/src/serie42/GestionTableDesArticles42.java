package serie42;

import jPane.ES;
import mesExceptions.AbandonException;
import mesInterfaces.interfaceGestion;
import serie42.ArticleAbstract;
import serie42.TableArticle42;
import serie42.TableDesCommandes42;

public class GestionTableDesArticles42 implements interfaceGestion<TableArticle42> {
	private ES ES = new ES();
	
	
	//************************************** MENU GESTION STOCK ****************************************
	
	public int menuChoix() throws AbandonException { //ICI ON NE TRAITE PAS L EXCEPTION, ON LE LAISSE TRAITER PAR LE MAIN ==> iIL VA ABANDONNER DIRECTEMENT L APPLICATION
		String menu = "\n\t\t GESTION des ARTICLES \n"
					 +"\n\t CREER un NOUVEL ARTICLE..1" //Correspond creerArt(TableArticle42 tabArt)
				     +"\n\t SUPPRIMER UN ARTICLE.....2" 
				     +"\n\t MODIFIER UN ARTICLE......3"
				     +"\n\t AFFICHER LE STOCK........4" //Correspond afficheStock(tabArt)
				     +"\n\t AFFICHER LES PROMOTIONS..5" //Correspond afficheStock(tabArt) 
				     +"\n\t TRANSFORMER EN PROMO.....6" //Correspond afficheStock(tabArt) 
				     +"\n\t FIN......................0" //Correspond à choix==0; break;
					 + "\n\t\t\t\t VOTRE CHOIX:";
		return (int)ES.saisie(menu, 0, 5);		
	}
	
	public void menuGeneral(TableArticle42 tabArt, Object...obj ) throws AbandonException { //TableDesCommandes42 tabCde
		int choix;

		TableDesCommandes42	tabCde = (TableDesCommandes42) obj[0]; //CAST DE PARAM 0
	 do {
		 choix = menuChoix();
		 switch(choix) {
		 case 1: creer(tabArt); break;
		 case 2: supprimer(tabArt, tabCde); break; //A FINIR PLUS TARD CAR CASCADE
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
	
	private void transformer(TableArticle42 tabArt) throws AbandonException {
		int code = (int)ES.saisie("\n\t CHOISSISSEZ UN ARTICLE A TRANSFORMER: "+tabArt.cle(), 1);
		ArticleAbstract art = tabArt.retourner(code);
		if(art==null) ES.affiche("\t ARTICLE N'EXISTE PAS\n");
		else {
			
			if(art instanceof ArticlePromo) art=new ArticlePromo(art.getCode(),art.getDesignation(),art.getPu(),  ((ArticlePromo) art).getQuantiteMini(), ((ArticlePromo) art).getReduc());
				tabArt.ajouter(art); //PAS BESOIN DE SUPPRIMER C ES TLE MEME CODE

				//int quantite  = (int)ES.saisie("\n\t ARTICLE TRANSFORME EN PROMO: ", 1);
				//float reduction = (float)ES.saisie("\n\t ARTICLE TRANSFORME EN PROMO: ", 0f);
				//art = new Article42(art.getCode(),art.getDesignation(),art.getPu());	
				//tabArt.ajouter(art);
				}
			}



			//CREER un NOUVEL ARTICLE..1 
			public void creer(TableArticle42 tabArt) throws AbandonException {
				ArticleAbstract art = saisieArt(tabArt);
				if(art!=null) {
					tabArt.ajouter(art); //A CE MOMENT EST INITIALISE LA TABLE DES COMMANDES AVEC 5 ARTICLES	
				}
				else ES.affiche("\t CODE ARTICLE EXISTE DEJA\n");
			}
			
			private ArticleAbstract saisieArt(TableArticle42 tabArt) throws AbandonException { //Comment afficher pour que ca sera sur la meme ligne
				int code;
				code=(int)ES.saisie("\n\t SAISISSEZ LE CODE DE L'ARTICLE\n\t (Le premier numéro disponible est " + (tabArt.premierNumDispo()) + ")",1); //COMPLETER LE MESSAGE!!!!!
				if (tabArt.retourner(code)== null) {
					String designation = ES.saisie("\t DESIGNATION ARTICLE: ");
					designation=designation.toUpperCase(); //PAS SURE QUE CA MARCHE
					//REVERIFIER SI CA MARCHE
					float pu = (float)ES.saisie("\t PRIX UNITAIRE (saisissez un entier): ", 1); //RAJOUTER UNE METHODE LIRE FLOAT
					
				
					if (ES.saisieOuiNon(" Promo Ou Pas? "))
					{ // Article promo
						int mini = (int)ES.saisie(" Quelle Quantite Mini? ", 1);
						float reduc= (float)ES.saisie(" Combien de reduction? ", 1);
						return new ArticlePromo(code, designation,pu, mini, reduc);
						
					}
					
					return new Article42(code, designation, pu); 
				}
				return null;
			}
			
			//SUPPRIMER UN ARTICLE.....2  // Cascade des valeurs
			public  void supprimer(TableArticle42 tabArt, Object...obj ) throws AbandonException { //TableDesCommandes42 tabCde
				TableDesCommandes42 tabCde = (TableDesCommandes42)obj[0];
				int code = (int)ES.saisie("\n\t CHOISSIEZ un ARTICLE a SUPPRIMER"+tabArt.cle()+":", 1);
				if (tabArt.retourner(code)==null) {
					ES.affiche("ARTICLE N'EXISTE PAS");
				}
				else {
					tabArt.supprimer(code);
					tabCde.purge(code);
				}
			}
			
			//MODIFIER UN ARTICLE......3  
			public void modifier(TableArticle42 tabArt) throws AbandonException { //On modifie la designation
				int code = (int)ES.saisie("\n\t CHOISSISSEZ UN ARTICLE A MODIFIER: "+tabArt.cle(), 1);
				ArticleAbstract art = tabArt.retourner(code);
				if(art==null) {
					ES.affiche("\t ARTICLE N'EXISTE PAS\n");
				}
				else {
					System.out.println("");
					String designation = ES.saisie("\n\t SAISISSEZ LA NOUVELLE DESIGNATION: (si sr n as pas ete supprimé"); //On appue enter (rc) si on veut rien modifier
					if (!designation.equals("")) { //n est pas vide parce qu'il y a un retour chariot avant ca veut dire qu on a pas rajouter un article, parce quand on ajoute un article on l'ajoute lcean, en supprimant le retour chariot (voir saisie dans DataUser)
						art.setDesignation(designation.toUpperCase()); //Affichage at UpperCase
					}
					String pu  = ES.saisie("\n\t SAISISSEZ LE NOUVEAU PRIX: "); //On appue enter (rc) si on veut rien modifier
						if (!pu.equals("")) art.setPu(Float.parseFloat(pu));
					}
				}
			
			
			
			/*//A FINIR CAR MODOFIE L ARTICLE EN PROMO
			public  void modifier(TableArticles41 tabArt) throws AbandonException
			{
				int code= ES.saisie
						(" \n*** MODIFIER UN ARTICLE **\n" + tabArt.cle()+ "\n ** Quel Article? ",1);
				
				Article art= tabArt.retourner(code);
				
				if (art == null)
					ES.affiche( "*** CET ARTICLE N'EXISTE PAS **\n" );
				else
				{
					String designation =ES.saisie(" Designation: " + art.getDesignation() + "\n (RC si inchange ) ");
					if (!designation.equals("")) art.setDesignation(designation);
					String pu=ES.saisie( " Prix Unitaire : " + art.getPu()+ "\n Nouveau prix? ");
					if (!pu.equals("")) art.setPu(Float.parseFloat(pu));
					if (art instanceof ArticlePromo)
					{ // article promo ... suite a modifier
						
					}
				}
				
				*/
			
			
			//AFFICHER LE STOCK........4
			public void afficher(TableArticle42 tabArt) { //Correspond à 4 - afficher le stock
				if (tabArt.taille() == 0)
					ES.affiche("STOCK VIDE)");
				else{
					ES.affiche(tabArt.toString());
				}
			}
			
			//AFFICHER LES PROMOTIONS........5
			public void afficherPromo(TableArticle42 tabArt)
			{
				if (tabArt.taille()==0) ES.affiche("\n ** AUCUN ARTICLE EN STOCK ");
				else ES.affiche(tabArt.promo());
			}

			@Override
			public void creer(TableArticle42 tab, Object... objects) throws AbandonException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void modifier(TableArticle42 tab, Object... objects) throws AbandonException {
				// TODO Auto-generated method stub
				
			}
	

}
