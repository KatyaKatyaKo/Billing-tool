package serie42;

import jPane.ES;
import mesExceptions.AbandonException;
import mesInterfaces.interfaceGestion;
import serie41.GestionUneCommande41;
import serie41.UneCommande41;
import serie42.TableArticle42;
import serie42.TableDesCommandes42;
import serie42.UneCommande42;

public class GestionTableDesCommandes42 implements interfaceGestion <TableDesCommandes42>{
	private ES ES = new ES();
	
	private GestionUneCommande42 gestCde = new GestionUneCommande42(); //Pour gérer une Commande
	
	//************************************** MENU GESTION DES COMMANDES ****************************************

			public int menuChoix(int numCde) throws AbandonException { 
				String menu = "\n\t\t GESTION des COMMANDES \n"
						     +"\n\t CREER une COMMANDE N"+numCde+"....1" //Correspond creerArt(TableArticle42 tabArt)
						     +"\n\t SUPPRIMER une COMMANDE...2" //CASCADE
						     +"\n\t MODIFIER une COMMANDE....3" //CASCADE
						     +"\n\t AFFICHER une COMMANDE....4"
						     +"\n\t AFFICHER les COMMANDES...5" //Correspond afficheStock(tabArt) 
						     +"\n\t FACTURER des COMMANDES...6" //Correspond afficheStock(tabArt) 
						     +"\n\t FIN......................0" //Correspond à choix==0; break;
							 + "\n\t\t\t\t VOTRE CHOIX:";
				return (int)ES.saisie(menu, 0, 6) ;		
			}
		
			public void menuGeneral(TableDesCommandes42 tabCde, Object...obj) throws AbandonException {  //TableArticle42 tabArt
				TableArticle42 tabArt = (TableArticle42)obj[0];
				int choix;
				do {
					int numCde = premierNumDispo(tabCde); //ON RAJOUTE UNE VARIABLE 
					choix = menuChoix(numCde);
					switch(choix) {
					 case 1: creer(tabCde, tabArt, numCde); break; //ON DIRAIT QUE CA MARCHE PAS
					 case 2: supprimer(tabCde); break; //A FINIR PLUS TARD CAR CASCADE
					 case 3: modifier(tabCde, tabArt); break;
					 case 4: visualiser(tabCde); break;
					 case 5: visualiserToutesCommandes(tabCde); break;
					 case 6: facturer(tabCde, tabArt); break;
					 case 0: break;	 
					 }
				 }
					while(choix!=0); 
				}
	
	//------------------------------------METODES GESTION DES COMMANDES --------------------------------
	
	
			// CREER une COMMANDE N"+numCde+"....1"
			public void creer(TableDesCommandes42 tabCde, TableArticle42 tabArt, int numCde) throws AbandonException { //A REVOIR
				//On cree une commande
				UneCommande42<Integer> cde = new UneCommande42<Integer>();
				//On cree le numéro de commande
				cde.setNumCde(numCde); //On attribut un numéro à une commande
				//on crée une variable de gestionnaireUneCommande
				GestionUneCommande42 gestCde = new GestionUneCommande42 ();
				gestCde.menuGeneral(cde, tabArt,numCde);
				
				//gestionUneCommande(cde, tabArt, numCde);
					if(cde.taille()!=0) {  //Si la commande n'est pas vide (l'utilisateur a effectué le sopérations d ajout de code + quantité), on ajoute la commande dans tabCde
						tabCde.ajouter(cde);  
					}
			}
			
			//Boucle infinie
			public static int premierNumDispo(TableDesCommandes42 tabCde) { //CHAQUE FOIS LE COMPTEUR COMMENCE PAR 1 ==>
				int compteur = 1;
				do {
					if(tabCde.retourner(compteur)==null) return compteur; //Si la commande n'existe pas, on prend ce numéro
						compteur++;			
				}while(true);
			}
			
			
			//SUPPRIMER une COMMANDE...2
			public void supprimer(TableDesCommandes42 tabCde)  throws AbandonException { //ON TRAITE LES EXCEPTIONS PAS ICI MAIS DANS LE CLIENT jAVA ==> THROWS
				//ICI C EST JUST EPOUR INDIQUER QUE L EXCEPTION EXISTE ET ELLE SERA TRAITER AILLEURS
				if(tabCde.taille()==0) {
					ES.affiche("\n\t LA TABLE des COMMANDES est VIDE");
				}
				else {
					int numCde = (int)ES.saisie("\n\t SAISISSEZ le NUMERO DE COMMANDE A SUPPRIMER:"+"\n\t\t"+tabCde.cle() + ":", 1, Integer.MAX_VALUE);
					tabCde.supprimer(numCde);
					ES.affiche("\t LA COMMANDE N"+numCde+" a été SUPPRIMEE\n");
				}
			}
			
			//MODIFIER une COMMANDE....3
					public int menuModifierCommande() throws AbandonException {
						String menu = "\n\t AJOUTER ou MODIFIER un ARTICLE.......1" //Correspond creerArt(TableArticle42 tabArt)
							     +"\n\t SUPPRIMER un ARTICLE...2" //CASCADE
							     +"\n\t FIN....................0" //CASCADE
								 + "\n\t\t\t\t VOTRE CHOIX:";
					return (int)ES.saisie(menu, 0, 2);		
					}			

					private void modifier(TableDesCommandes42 tabCde, TableArticle42 tabArt) throws AbandonException {
						int numCde = (int)ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a MODIFIER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
						UneCommande42 <Integer> cde = tabCde.retourner(numCde);
						if(cde==null) {/*Afficher mssage*/ }
						//else {gestCde.menuGeneral(tabCde, tabArt, numCde);}
						else {gestCde.menuGeneral(cde, tabArt, numCde);}
						
						//(TableDesCommandes42 tabCde, TableArticle42 tabArt, int numCde)
						
					}
			
			//AFFICHER une COMMANDE....4
			public void visualiser(TableDesCommandes42 tabCde) throws AbandonException {
				if(tabCde.taille()==0) {
					ES.affiche("\n\t LA TABLE des COMMANDES EST VIDE, IL y a RIEN a AFFICHER");
					}
				else {	
					int numCde = (int)ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a AFFICHER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
					//EST-CE QU'IL NOUS MANQUE UNE METHODE POUR AFFICHER UNE COMMANDE DANS TABLE DE COMMANDE. ON DOIT LA DEPLACER DE MAIN??
					 tabCde.retourner(numCde).toString(); //EST-CE 
				}
			}
			
			//AFFICHER les COMMANDES...5
			public void visualiserToutesCommandes(TableDesCommandes42 tabCde) { //case 4: visualiserToutesCommandes
				if(tabCde.taille()==0){
					ES.affiche("\n\t TABLE DES COMMANDES EST VIDE");
				}
				else {
					ES.affiche(tabCde.toString()); //N est pas créée dans TabledesCommandes
				}
			}
			
			//FACTURER des COMMANDES...6
			
			private void facturer(TableDesCommandes42 tabCde, TableArticle42 tabArt) {
			if(tabCde.taille()==0){
				ES.affiche("\n\t IL N Y A PAS DE COMMMANDES A FACTURER");
			}
			else {
				ES.affiche(tabCde.facturerCommandes(tabArt));
			}	
		}


			@Override
			public void creer(TableDesCommandes42 tab, Object... objects) throws AbandonException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void supprimer(TableDesCommandes42 tab, Object... objects) throws AbandonException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void modifier(TableDesCommandes42 tab, Object... objects) throws AbandonException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afficher(TableDesCommandes42 tab) {
				// TODO Auto-generated method stub
				
			}


	
		



}
