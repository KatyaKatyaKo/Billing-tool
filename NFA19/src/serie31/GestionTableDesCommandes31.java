package serie31;

import iConsole.ES;
import mesExceptions.AbandonException;
import serie22.TableArticle22;
import serie22.TableDesCommandes22;
import serie22.UneCommande22;

public class GestionTableDesCommandes31 {
	private ES ES = new ES();
	
	private GestionUneCommande31 gestCde = new GestionUneCommande31(); //Pour gérer une Commande
	
	//************************************** MENU GESTION DES COMMANDES ****************************************

			public int menuChoix(int numCde) throws AbandonException { //ON A RAJOUTE LE PARAMETRE int numCd
				String menu = "\n\t\t GESTION des COMMANDES \n"
						     +"\n\t CREER une COMMANDE N"+numCde+"....1" //Correspond creerArt(TableArticle22 tabArt)
						     +"\n\t SUPPRIMER une COMMANDE...2" //CASCADE
						     +"\n\t MODIFIER une COMMANDE....3" //CASCADE
						     +"\n\t AFFICHER une COMMANDE....4"
						     +"\n\t AFFICHER les COMMANDES...5" //Correspond afficheStock(tabArt) 
						     +"\n\t FACTURER des COMMANDES...6" //Correspond afficheStock(tabArt) 
						     +"\n\t FIN......................0" //Correspond à choix==0; break;
							 + "\n\t\t\t\t VOTRE CHOIX:";
				return (int)ES.saisie(menu, 0, 6) ;		
			}
		
			public void menuGeneral(TableDesCommandes22 tabCde, TableArticle22 tabArt) { 
				int choix;
				do {
					try {
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
						catch(AbandonException abe) {
							choix=0;
								}
				 }
					while(choix!=0); 
				}
	
	//------------------------------------METODES GESTION DES COMMANDES --------------------------------
	
	
			// CREER une COMMANDE N"+numCde+"....1"
			public void creer(TableDesCommandes22 tabCde, TableArticle22 tabArt, int numCde) throws AbandonException { //A REVOIR
				//On cree une commande
				UneCommande22<Integer> cde = new UneCommande22<Integer>();
				//on crée une variable de gestionnaireUneCommande
				GestionUneCommande31 gestCde = new GestionUneCommande31 ();
				gestCde.menuChoix(cde, tabArt,numCde);
				cde.setNumCde(numCde); //On attribut un numéro à une commande
				//gestionUneCommande(cde, tabArt, numCde);
					if(cde.taille()!=0) {  //Si la commande n'est pas vide (l'utilisateur a effectué le sopérations d ajout de code + quantité), on ajoute la commande dans tabCde
						tabCde.ajouter(cde);  
					}
			}
			
			//Boucle infinie
			public static int premierNumDispo(TableDesCommandes22 tabCde) { //CHAQUE FOIS LE COMPTEUR COMMENCE PAR 1 ==>
				int compteur = 1;
				do {
					if(tabCde.retourner(compteur)==null) return compteur; //Si la commande n'existe pas, on prend ce numéro
						compteur++;			
				}while(true);
			}
			
			
			//SUPPRIMER une COMMANDE...2
			public void supprimer(TableDesCommandes22 tabCde)  throws AbandonException { //ON TRAITE LES EXCEPTIONS PAS ICI MAIS DANS LE CLIENT jAVA ==> THROWS
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
						String menu = "\n\t AJOUTER ou MODIFIER un ARTICLE.......1" //Correspond creerArt(TableArticle22 tabArt)
							     +"\n\t SUPPRIMER un ARTICLE...2" //CASCADE
							     +"\n\t FIN....................0" //CASCADE
								 + "\n\t\t\t\t VOTRE CHOIX:";
					return (int)ES.saisie(menu, 0, 2);		
					}			
					/**
					private void modifier(TableDesCommandes22 tabCde, TableArticle22 tabArt) {
						if(tabCde.taille()==0) {
							ES.affiche("\n\t LA TABLE des COMMANDES est VIDE");
						}
						else {
							int numCde = ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a MODIFIER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
							afficherLcd (tabCde.retourner(numCde)); 
							int choix;
							do {
								choix = menuModifierCommande();
								switch(choix) {
								 case 1: 
									 creerLcd (tabCde.retourner(numCde), tabArt);  //Parametres - creerLcd (cde, tabArt);
								 //tabCde.getTabCde().get(numCde).visualiserCommande(tabCde);
								 afficherLcd (tabCde.retourner(numCde));break;
								 case 2: 
									 //Prevenir d ela suppression de commande
									 if(tabCde.retourner(numCde).getUneCommande().size()==1) {
										 ES.affiche("\n\t IL RESTE le DERNIER ARTICLE :\n\t si vous le supprimer, la commande sera supprimée aussi\n");
										 menuModifierCommande(); //Comment faire un goto
									 }
									 int codeArt = ES.saisie("\n\t SAISISSEZ le Code d'ARTICLE a SUPPRIMER:\n\t " + ":", 1, Integer.MAX_VALUE);
									 tabCde.getTabCde().get(numCde).supprimer(codeArt); 
									 ES.affiche("\n\t ARTICLE A ETE SUPPRIME");
									 tabCde.retourner(numCde).getUneCommande();
									 //Ici situation très intéréssante: même si après la suppression il ne reste plus de lignes de commandes,
									 //LA COMMANDE N'EST PAS VIDE, parce qu'elle a le numéro de commande est une date==>
									 //pour vérifier que la commande est vide: la condition tabCde.retourner(numCde)==null ne sera jamais vrai==>
									 //il faut écrire tabCde.retourner(numCde).getUneCommande()==null On teste le Vector de Commande
									 if(tabCde.retourner(numCde).getUneCommande().isEmpty()) { //Si la commande est vide après la suppression d'un article
										 tabCde.supprimer(numCde); 
										 tabCde.purge(codeArt); //Ne pas oublier de purger chaque fois on supprime qqch
										 ES.affiche("\n\t IL NE RESTE PLUS D'ARTICLES : LA COMMANDE A ETE SUPPRIMEE\n"); 
										 choix=0; //POUR SORTIR DE CE CASE IL FAUT ARTIFICIELLEMENT DECLARER LE choix à zero (choix=0)
									 }
									 else {
										 ES.affiche(tabCde.retourner(numCde).toString()); //Réaffiche la commande
										 tabCde.purge(codeArt); break;//Ne pas oublier de purger chaque fois on supprime qqch
									 }
								 case 0: break;	 
								 }
							}
								while(choix!=0);}
						}
					
					*/
					
					private void modifier(TableDesCommandes22 tabCde, TableArticle22 tabArt) throws AbandonException {
						int numCde = (int)ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a MODIFIER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
						UneCommande22 <Integer> cde = tabCde.retourner(numCde);
						if(cde==null) {/*Afficher mssage*/ }
						//else {gestCde.menuGeneral(tabCde, tabArt, numCde);}
						else {gestCde.menuGeneral(cde, tabArt, numCde);}
						
						//(TableDesCommandes22 tabCde, TableArticle22 tabArt, int numCde)
						
					}
			
			//AFFICHER une COMMANDE....4
			public void visualiser(TableDesCommandes22 tabCde) throws AbandonException {
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
			public void visualiserToutesCommandes(TableDesCommandes22 tabCde) { //case 4: visualiserToutesCommandes
				if(tabCde.taille()==0){
					ES.affiche("\n\t TABLE DES COMMANDES EST VIDE");
				}
				else {
					ES.affiche(tabCde.toString()); //N est pas créée dans TabledesCommandes
				}
			}
			
			//FACTURER des COMMANDES...6
			
			private void facturer(TableDesCommandes22 tabCde, TableArticle22 tabArt) {
			if(tabCde.taille()==0){
				ES.affiche("\n\t IL N Y A PAS DE COMMMANDES A FACTURER");
			}
			else {
				//for (UneCommande22 Commande: tabCde)
				//int numCde = ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a FACTURER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
				//EST-CE QU'IL NOUS MANQUE UNE METHODE POUR AFFICHER UNE COMMANDE DANS TABLE DE COMMANDE. ON DOIT LA DEPLACER DE MAIN??
				//ES.affiche(tabCde.retourner(numCde).facturer(tabArt)); 
				//Afficher le numéro
				ES.affiche(tabCde.facturerCommandes(tabArt));
			}	
		}
		

		//Facturer une commande
			/*
		
		private static void facturerDesCommandes(TableDesCommandes22 tabCde, TableArticle22 tabArt) {
			if(tabCde.taille()==0){
				ES.affiche("\n\t IL N Y A PAS DE COMMMANDES A FACTURER");
			}
			else {
				int numCde = ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a FACTURER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
				//EST-CE QU'IL NOUS MANQUE UNE METHODE POUR AFFICHER UNE COMMANDE DANS TABLE DE COMMANDE. ON DOIT LA DEPLACER DE MAIN??
				ES.affiche(tabCde.retourner(numCde).facturer(tabArt)); 
			}
				
		}

			 */


}
