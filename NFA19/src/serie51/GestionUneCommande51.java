package serie51;
import iConsole.ES;
import mesExceptions.AbandonException;
import mesInterfaces.interfaceGestion;

public class GestionUneCommande51 implements interfaceGestion<UneCommande51<String>> {
	private ES ES = new ES();
	
	//************************************** MENU GESTION UNE COMMANDE ****************************************
	
			public int menuChoix(UneCommande51<String> cde, Object...obj) throws AbandonException { //TableArticle51 tabArt, int numCde
				//TableArticle51 tabArt = (TableArticle51) obj[0];
				String menu = "\n\t\t GESTION d'une COMMANDE \n"
						     +"\n\t SAISIR une LIGNE DE COMMANDE.......................1"  //creerLcd(cde, tabArt)
						     +"\n\t AFFICHER la COMMANDE EN COURS......................2"  //afficherLcd(cde)
						     +"\n\t EDITER la FACTURE..................................3"  //facturerCommande(cde, tabArt)
						     +"\n\t FIN................................................0"
							 +"\n\t\t\t\t VOTRE CHOIX:";
				return (int)ES.saisie(menu, 0, 3);		
			}
					
			public void menuGeneral(UneCommande51<String> cde, Object...obj) throws AbandonException { //TableArticle51 tabArt, int numCde
				TableArticle51 tabArt = (TableArticle51) obj[0];
				String numCde = (String)obj[1];
				
				int choix;
				do {
				choix = menuChoix(cde, tabArt,numCde);
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
			private LigneDeCommande51 saisieLdc(TableArticle51 tabArt) throws AbandonException {
				ES.affiche("\n\t\t CREATION d'une LIGNE de COMMANDE\n");
				int codeArt = (int)ES.saisie("\t SAISISSEZ le CODE de l'ARTICLE "+ tabArt.cle(), 1, Integer.MAX_VALUE);
						if(tabArt.retourner(codeArt) instanceof ArticlePromo51) System.out.println("\n\t Cet article est en promotion!! Quantité minimale: " + tabArt.retourner(codeArt).getQuantiteMini());
						if (tabArt.retourner(codeArt)==null) return null;
						else { int quanity = (int)ES.saisie("\t SAISISSEZ la QUANTITE: ", 1, Integer.MAX_VALUE);
						return (new LigneDeCommande51(codeArt, quanity ));}
			}
			
			public void creer (UneCommande51<String> cde, Object...obj ) throws AbandonException { //TableArticle51 tabArt
				TableArticle51 tabArt = (TableArticle51) obj[0];
				LigneDeCommande51 ldc = saisieLdc(tabArt);
				if(ldc!= null) {
					int code = ldc.getCode(); 
					LigneDeCommande51 ldc2 = cde.retourner(code); //Au premier passage de la boucle le cde est encore vide==> par default ldc2== null, mais au 2e passage de la boucle il y a déjà cde.get(0) g^race à l'instruction cde.ajouter(ldc); à la fin de cette boucle
					if(ldc2!=null) { //Si la commande contient une ligne de commande (on la cjerche par un code saisi lors de la saisie d'une ligne de commande)
						ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());
					}
					else {
					cde.ajouter(ldc); 
					}
				}
				else {
					ES.affiche("\n\t ARTICLE COMMENDE N'EXISTE PAS");
				}
			}

			//AFFICHER la COMMANDE EN COURS......................2
			public void afficher (UneCommande51<String> cde){  //POURQUOI CETTE MÉTHODE EST STATIQUE??? LA DEPLACER PE DANS COMMANDE
				if(cde.taille()==0) {ES.affiche("\n\t\t COMMANDE EST VIDE");
				}
				else {ES.affiche(cde.toString());}	
			}
			
			
			//EDITER la FACTURE..................................3 //ERREUR EST LA
			private void facturer(UneCommande51<String> cde, TableArticle51 tabArt) { //Une commande
				if(cde.taille()==0){
					ES.affiche("\n\t COMMANDE EST VIDE");
				}
				else {
					ES.affiche(cde.facturer(tabArt)); // Affiche ligne de commande
				}	
			}

			//Méthodes de l'interface Gestion
			public void supprimer(UneCommande51<String> tab, Object... objects) throws AbandonException {
				// TODO Auto-generated method stub	
			}


			@Override
			public void modifier(UneCommande51<String> tab, Object... objects) throws AbandonException {
				// TODO Auto-generated method stub	
			}			

					
			
}
