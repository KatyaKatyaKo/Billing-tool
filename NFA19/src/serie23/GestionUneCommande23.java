package serie23;

import serie22.LigneDeCommande22;
import serie22.TableArticle22;
import serie22.UneCommande22;
import utils.ClientJava1DateUser;

public class GestionUneCommande23 {
	
	//************************************** MENU GESTION UNE COMMANDE ****************************************
	
			public int menuGeneral(UneCommande22<Integer> cde,TableArticle22 tabArt, int numCde) {
				String menu = "\n\t\t GESTION d'une COMMANDE \n"
						     +"\n\t SAISIR une LIGNE DE COMMANDE.......................1"  //creerLcd(cde, tabArt)
						     +"\n\t AFFICHER la COMMANDE EN COURS......................2"  //afficherLcd(cde)
						     +"\n\t EDITER la FACTURE..................................3"  //facturerCommande(cde, tabArt)
						     +"\n\t FIN................................................0"
							 +"\n\t\t\t\t VOTRE CHOIX:";
				return ClientJava1DateUser.lireEnt(menu, 0, 3);		
			}
			
						
			public void menuChoix(UneCommande22<Integer> cde, TableArticle22 tabArt, int numCde) { ////Correspond à 2 - afficher la commande en cours
				int choix;
			
				do {
				choix = menuGeneral(cde, tabArt,numCde);
						switch(choix)
						{
							case 1: creer(cde, tabArt);break; //ok
							case 2: afficher(cde); break;
							case 3: facturer(cde, tabArt);break;
							case 0: break;
						}
				}while(choix!=0); 
			}
	
			//------------------------------------METODES GESTION une COMMANDE --------------------------------



			//SAISIR une LIGNE DE COMMANDE.......................1
			private static LigneDeCommande22 saisieLdc(TableArticle22 tabArt) {
				ClientJava1DateUser.affiche("\n\t\t CREATION d'une LIGNE de COMMANDE\n");
				int codeArt = ClientJava1DateUser.lireEnt("\t SAISISSEZ le CODE de l'ARTICLE: ", 1, Integer.MAX_VALUE);
						if (tabArt.retourner(codeArt)==null) return null;
						else { int quanity = ClientJava1DateUser.lireEnt("\t SAISISSEZ la QUANTITE: ", 1, Integer.MAX_VALUE);
						return (new LigneDeCommande22(codeArt, quanity ));}
			}
			
			//Si la ldc n'existe pas dans cde ==> c'est ldc ==> action:cde.ajouter(ldc)  / si elle existe déjà, c'est ldc2 ==> action: ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());  (on rajoute la quantité) 
			private void creer (UneCommande22<Integer> cde, TableArticle22 tabArt) {
				LigneDeCommande22 ldc = saisieLdc(tabArt);
				if(ldc!= null) {
					int code = ldc.getCode(); //Avec ce code de commande on va retrouver l'article dans TabArt
					LigneDeCommande22 ldc2 = cde.retourner(code); //Au premier passage de la boucle le cde est encore vide==> par default ldc2== null, mais au 2e passage de la boucle il y a déjà cde.get(0) g^race à l'instruction cde.ajouter(ldc); à la fin de cette boucle
					if(ldc2!=null) { //Si la commande contient une ligne de commande (on la cjerche par un code saisi lors de la saisie d'une ligne de commande)
						ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());
					}
					else {
					cde.ajouter(ldc); //Nous ajoutons nouvelle Ligne de Commande
					}
				}
				else {
					ClientJava1DateUser.affiche("\n\t ARTICLE COMMENDE N'EXISTE PAS");
				}
				
			}

			//AFFICHER la COMMANDE EN COURS......................2
			public void afficher (UneCommande22<Integer> cde){  //POURQUOI CETTE MÉTHODE EST STATIQUE??? LA DEPLACER PE DANS COMMANDE
				if(cde.taille()==0) {ClientJava1DateUser.affiche("\n\t\t COMMANDE EST VIDE");
				}
				else {ClientJava1DateUser.affiche(cde.toString());}	
			}
			
			
			//EDITER la FACTURE..................................3 //ERREUR EST LA
			private void facturer(UneCommande22<Integer> cde, TableArticle22 tabArt) { //Une commande
				if(cde.taille()==0){
					ClientJava1DateUser.affiche("\n\t COMMANDE EST VIDE");
				}
				else {
					ClientJava1DateUser.affiche(cde.facturer(tabArt)); // Affiche ligne de commande
				}	
			}			

					
			
}
