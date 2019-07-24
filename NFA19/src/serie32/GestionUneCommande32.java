package serie32;

import jPane.ES;
import mesExceptions.AbandonException;
import serie22.LigneDeCommande22;
import serie22.TableArticle22;
import serie22.UneCommande22;

public class GestionUneCommande32 {
	private ES ES = new ES();
	
	//************************************** MENU GESTION UNE COMMANDE ****************************************
	
			public int menuGeneral(UneCommande22<Integer> cde,TableArticle22 tabArt, int numCde) throws AbandonException  {
				String menu = "\n\t\t GESTION d'une COMMANDE \n"
						     +"\n\t SAISIR une LIGNE DE COMMANDE.......................1"  //creerLcd(cde, tabArt)
						     +"\n\t AFFICHER la COMMANDE EN COURS......................2"  //afficherLcd(cde)
						     +"\n\t EDITER la FACTURE..................................3"  //facturerCommande(cde, tabArt)
						     +"\n\t FIN................................................0"
							 +"\n\t\t\t\t VOTRE CHOIX:";
				return (int) ES.saisie(menu, 0, 3);		
			}
			
						
			public void menuChoix(UneCommande22<Integer> cde, TableArticle22 tabArt, int numCde) { ////Correspond à 2 - afficher la commande en cours
				int choix;
			
				do {
					try {
						choix = menuGeneral(cde, tabArt,numCde);
								switch(choix)
								{
									case 1: creer(cde, tabArt);break; //ok
									case 2: afficher(cde); break;
									case 3: facturer(cde, tabArt);break;
									case 0: break;
								}
					}
					catch(AbandonException ae2) {choix =0;}		
				}while(choix!=0); 
			}
	
			//------------------------------------METODES GESTION une COMMANDE --------------------------------



			//SAISIR une LIGNE DE COMMANDE.......................1
			private LigneDeCommande22 saisieLdc(TableArticle22 tabArt) throws AbandonException {
				int codeArt = (int) ES.saisie("\n\t\t CREATION d'une LIGNE de COMMANDE\n\t SAISISSEZ le CODE de l'ARTICLE: ", 1, Integer.MAX_VALUE);
						if (tabArt.retourner(codeArt)==null) return null;
						else { int quanity = (int) ES.saisie("\t SAISISSEZ la QUANTITE: ", 1, Integer.MAX_VALUE);
						return (new LigneDeCommande22(codeArt, quanity ));}
			}
			
			//Si la ldc n'existe pas dans cde ==> c'est ldc ==> action:cde.ajouter(ldc)  / si elle existe déjà, c'est ldc2 ==> action: ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());  (on rajoute la quantité) 
			private void creer (UneCommande22<Integer> cde, TableArticle22 tabArt) throws AbandonException {
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
					ES.affiche("\n\t ARTICLE COMMENDE N'EXISTE PAS");
				}
				
			}

			//AFFICHER la COMMANDE EN COURS......................2
			public void afficher (UneCommande22<Integer> cde){  //POURQUOI CETTE MÉTHODE EST STATIQUE??? LA DEPLACER PE DANS COMMANDE
				if(cde.taille()==0) {ES.affiche("\n\t\t COMMANDE EST VIDE");
				}
				else {ES.affiche(cde.toString());}	
			}
			
			
			//EDITER la FACTURE..................................3 //ERREUR EST LA
			private void facturer(UneCommande22<Integer> cde, TableArticle22 tabArt) { //Une commande
				if(cde.taille()==0){
					ES.affiche("\n\t COMMANDE EST VIDE");
				}
				else {
					ES.affiche(cde.facturer(tabArt)); // Affiche ligne de commande
				}	
			}			

					
			
}
