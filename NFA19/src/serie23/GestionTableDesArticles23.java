package serie23;

import serie21.Article21;
import serie22.TableArticle22;
import serie22.TableDesCommandes22;
import utils.ClientJava1DateUser;

public class GestionTableDesArticles23 {
	
	//************************************** MENU GESTION STOCK ****************************************
	
	public int menuChoix() {
		String menu = "\n\t\t GESTION des ARTICLES \n"
					 +"\n\t CREER un NOUVEL ARTICLE..1" //Correspond creerArt(TableArticle22 tabArt)
				     +"\n\t SUPPRIMER UN ARTICLE.....2" //A FINIR PLUS TARD CAR CASCADE
				     +"\n\t MODIFIER UN ARTICLE......3"
				     +"\n\t AFFICHER LE STOCK........4" //Correspond afficheStock(tabArt) 
				     +"\n\t FIN......................0" //Correspond à choix==0; break;
					 + "\n\t\t\t\t VOTRE CHOIX:";
		return ClientJava1DateUser.lireEnt(menu, 0, 4);		
	}
	
	public void menuGeneral(TableArticle22 tabArt, TableDesCommandes22 tabCde ) {
		int choix;
	 do {
		 choix = menuChoix();
		 switch(choix) {
		 case 1: creer(tabArt); break;
		 case 2: supprimer(tabArt, tabCde); break; //A FINIR PLUS TARD CAR CASCADE
		 case 3: modifier(tabArt); break;
		 case 4: afficher(tabArt); break;
		 case 0: break;	 
		 }
	 }
		while(choix!=0); 
	}
	
	
	//************************************** METHODES GESTION STOCK ****************************************
	
	//CREER un NOUVEL ARTICLE..1 
			public void creer(TableArticle22 tabArt) {
				Article21 art = saisieArt(tabArt);
				if(art!=null) {
					tabArt.ajouter(art); //A CE MOMENT EST INITIALISE LA TABLE DES COMMANDES AVEC 5 ARTICLES	
				}
				else ClientJava1DateUser.affiche("\t CODE ARTICLE EXISTE DEJA\n");
			}
			
			private Article21 saisieArt(TableArticle22 tabArt) { //Comment afficher pour que ca sera sur la meme ligne
				System.out.println("\n\t SAISISSEZ LE CODE DE L'ARTICLE");
				System.out.print("\t (Le premier numéro disponible est " +(tabArt.premierNumDispo()) + "): ");
				int code;
				code=ClientJava1DateUser.lireEnt("", 1, Integer.MAX_VALUE);
				//sc.next();
				if (tabArt.retourner(code)== null) {
					String designation = ClientJava1DateUser.lireString("\t DESIGNATION ARTICLE: ");
					//System.out.println(designation.toUpperCase()); A FINIR LA CONVERSION EN MAJUSCULE
					
					//REVERIFIER SI CA MARCHE
					float pu = (float)ClientJava1DateUser.lireFloat("\t PRIX UNITAIRE (saisissez un entier): ", 1, Integer.MAX_VALUE); //RAJOUTER UNE METHODE LIRE FLOAT
					return new Article21(code, designation, pu);
				}
				return null;
			}
			
			//SUPPRIMER UN ARTICLE.....2  // Cascade des valeurs
			public  void supprimer(TableArticle22 tabArt, TableDesCommandes22 tabCde) {
				int code = ClientJava1DateUser.lireEnt("\n\t CHOISSIEZ un ARTICLE a SUPPRIMER"+tabArt.cle()+":", 1, Integer.MAX_VALUE);
				if (tabArt.retourner(code)==null) {
					ClientJava1DateUser.affiche("ARTICLE N'EXISTE PAS");
				}
				else {
					tabArt.supprimer(code);
					tabCde.purge(code);
				}
			}
			
			//MODIFIER UN ARTICLE......3  
			public void modifier(TableArticle22 tabArt) { //On modifie la designation
				int code = ClientJava1DateUser.lireEnt("\n\t CHOISSISSEZ UN ARTICLE A MODIFIER: "+tabArt.cle(), 1, Integer.MAX_VALUE);
				Article21 art = tabArt.retourner(code);
				if(art==null) {
					ClientJava1DateUser.affiche("\t ARTICLE N'EXISTE PAS\n");
				}
				else {
					System.out.println("");
					String designation = ClientJava1DateUser.lireString("\n\t SAISISSEZ LA NOUVELLE DESIGNATION: (si sr n as pas ete supprimé"); //On appue enter (rc) si on veut rien modifier
					if (!designation.equals("")) { //n est pas vide parce qu'il y a un retour chariot avant ca veut dire qu on a pas rajouter un article, parce quand on ajoute un article on l'ajoute lcean, en supprimant le retour chariot (voir lireString dans DataUser)
						art.setDesignation(designation.toUpperCase()); //Affichage at UpperCase
					}
					String pu  = ClientJava1DateUser.lireString("\n\t SAISISSEZ LE NOUVEAU PRIX: "); //On appue enter (rc) si on veut rien modifier
						if (!pu.equals("")) art.setPu(Float.parseFloat(pu));
					}
				}
			
			//AFFICHER LE STOCK........4
			public void afficher(TableArticle22 tabArt) { //Correspond à 4 - afficher le stock
				if (tabArt.taille() == 0)
					ClientJava1DateUser.affiche("STOCK VIDE)");
				else{
					ClientJava1DateUser.affiche(tabArt.toString());
				}
			}
	

}
