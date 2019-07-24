package serie31;

import iConsole.ES;
import mesExceptions.AbandonException;
import serie21.Article21;
import serie22.TableArticle22;
import serie22.TableDesCommandes22;

public class GestionTableDesArticles31 {
	private ES ES = new ES();
	
	
	//************************************** MENU GESTION STOCK ****************************************
	
	public int menuChoix() throws AbandonException { //ICI ON NE TRAITE PAS L EXCEPTION, ON LE LAISSE TRAITER PAR LE MAIN ==> iIL VA ABANDONNER DIRECTEMENT L APPLICATION
		String menu = "\n\t\t GESTION des ARTICLES \n"
					 +"\n\t CREER un NOUVEL ARTICLE..1" //Correspond creerArt(TableArticle22 tabArt)
				     +"\n\t SUPPRIMER UN ARTICLE.....2" 
				     +"\n\t MODIFIER UN ARTICLE......3"
				     +"\n\t AFFICHER LE STOCK........4" //Correspond afficheStock(tabArt) 
				     +"\n\t FIN......................0" //Correspond à choix==0; break;
					 + "\n\t\t\t\t VOTRE CHOIX:";
		return (int)ES.saisie(menu, 0, 4);		
	}
	
	public void menuGeneral(TableArticle22 tabArt, TableDesCommandes22 tabCde )  {
		int choix;
	 do {
		 try {
			 choix = menuChoix();
			 switch(choix) {
			 case 1: creer(tabArt); break;
			 case 2: supprimer(tabArt, tabCde); break; //CASCADE
			 case 3: modifier(tabArt); break;
			 case 4: afficher(tabArt); break;
			 case 0: break; 	 
			 } 
		 	}
		catch(AbandonException abe) {
			choix=0;
			}
	 }
		while(choix!=0); 
	}
	

	//************************************** METHODES GESTION STOCK ****************************************
	
	//CREER un NOUVEL ARTICLE..1 
			public void creer(TableArticle22 tabArt) throws AbandonException {
				Article21 art = saisieArt(tabArt);
				if(art!=null) {
					tabArt.ajouter(art); //A CE MOMENT EST INITIALISE LA TABLE DES COMMANDES AVEC 5 ARTICLES	
				}
				else ES.affiche("\t CODE ARTICLE EXISTE DEJA\n");
			}
			
			private Article21 saisieArt(TableArticle22 tabArt) throws AbandonException { //Comment afficher pour que ca sera sur la meme ligne
				System.out.println("\n\t SAISISSEZ LE CODE DE L'ARTICLE");
				System.out.print("\t (Le premier numéro disponible est " +(tabArt.premierNumDispo()) + "): ");
				int code;
				code=(int)ES.saisie("",1); //COMPLETER LE MESSAGE!!!!!
				if (tabArt.retourner(code)== null) {
					String designation = ES.saisie("\t DESIGNATION ARTICLE: ");
					designation=designation.toUpperCase(); //PAS SURE QUE CA MARCHE
					//REVERIFIER SI CA MARCHE
					float pu = (float)ES.saisie("\t PRIX UNITAIRE (saisissez un entier): ", 1); //RAJOUTER UNE METHODE LIRE FLOAT
					return new Article21(code, designation, pu); 
				}
				return null;
			}
			
			//SUPPRIMER UN ARTICLE.....2  // Cascade des valeurs
			public  void supprimer(TableArticle22 tabArt, TableDesCommandes22 tabCde) throws AbandonException {
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
			public void modifier(TableArticle22 tabArt) throws AbandonException { //On modifie la designation
				int code = (int)ES.saisie("\n\t CHOISSISSEZ UN ARTICLE A MODIFIER: "+tabArt.cle(), 1);
				Article21 art = tabArt.retourner(code);
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
			
			//AFFICHER LE STOCK........4
			public void afficher(TableArticle22 tabArt) { //Correspond à 4 - afficher le stock
				if (tabArt.taille() == 0)
					ES.affiche("STOCK VIDE)");
				else{
					ES.affiche(tabArt.toString());
				}
			}
	

}
