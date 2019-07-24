package serie51;

import connexion.connexionFichier;
import iConsole.ES;
import mesExceptions.AbandonException;
import mesInterfaces.interfaceGestion;
import utils.DateUser;

public class GestionTableDesCommandes51 implements interfaceGestion <TableDesCommandes51>{
	private ES ES = new ES();
	String numCde;
	//Serie 51
	//Ce sont les nouvelles variables ==> il faut un constructeur 
	private String nomPhysique;
	
	
	private connexionFichier<TableDesCommandes51> fichCommandes;
	
	//Constructeur
	public GestionTableDesCommandes51(String nomPhysique) {
		fichCommandes=new connexionFichier<TableDesCommandes51>(nomPhysique);
	}
	
	//Setters-Getters
	public String getNomPhysique() {
		return nomPhysique;
	}

	public void setNomPhysique(String nomPhysique) {
		this.nomPhysique = nomPhysique;
	}
	
	
	//Méthodes de connexion
		//Récuperer
	public TableDesCommandes51 recuperer() {
		TableDesCommandes51 tabCde = fichCommandes.recuperer();
		if (tabCde==null) {
		//	ES.affiche("NOUVEAU FICHIER A CREER");
			tabCde = new TableDesCommandes51();
		}
		return tabCde;
	}
	
		//Ecrire
	public void ecrire(TableDesCommandes51 tabCde) {
		fichCommandes.ecrire(tabCde);
	}
	
	public void fermer(TableDesCommandes51 tabCde) {
		fichCommandes.fermer(tabCde);
	}
	
	
	private GestionUneCommande51 gestCde = new GestionUneCommande51(); //Pour gérer une Commande
	
	//************************************** MENU GESTION DES COMMANDES ****************************************

			public int menuChoix(String numCde) throws AbandonException { 
				String menu = "\n\t\t GESTION des COMMANDES \n"
						     +"\n\t CREER une COMMANDE N"+numCde+"....1" //Correspond creerArt(TableArticle51 tabArt)
						     +"\n\t SUPPRIMER une COMMANDE...2" //CASCADE
						     +"\n\t MODIFIER une COMMANDE....3" //CASCADE
						     +"\n\t AFFICHER une COMMANDE....4"
						     +"\n\t AFFICHER les COMMANDES...5" //Correspond afficheStock(tabArt) 
						     +"\n\t FACTURER des COMMANDES...6" //Correspond afficheStock(tabArt) 
						     +"\n\t FIN......................0" //Correspond à choix==0; break;
							 + "\n\t\t\t\t VOTRE CHOIX:";
				return (int)ES.saisie(menu, 0, 6) ;		
			}
		
			public void menuGeneral(TableDesCommandes51 tabCde, Object...objects) throws AbandonException {  //TableArticle51 tabArt
				TableArticle51 tabArt = (TableArticle51)objects[0];
				int choix;
				do {
					String numCde = premierNumDispo(tabCde); 
					choix = menuChoix(numCde);
					switch(choix) {
					 case 1: creer(tabCde, tabArt, numCde); break; 
					 case 2: supprimer(tabCde); break; 
					 case 3: modifier(tabCde, tabArt); break;
					 case 4: afficher(tabCde); break;
					 case 5: visualiserToutesCommandes(tabCde); break;
					 case 6: facturer(tabCde, tabArt); break;
					 case 0: break;	 
					 }
				 }
					while(choix!=0); 
				}
		
			
	//------------------------------------METODES GESTION DES COMMANDES --------------------------------
	
	
			public void creer(TableDesCommandes51 tabCde, Object... objects) throws AbandonException { //A REVOIR
				TableArticle51 tabArt = (TableArticle51)objects[0];
				String numCde = (String)objects[1];
				//On cree une commande
				UneCommande51<String> cde = new UneCommande51<String>();
				cde.setCode((String)numCde); //On attribut un numéro à une commande
				//on crée une variable de gestionnaireUneCommande
				GestionUneCommande51 gestCde = new GestionUneCommande51 ();
				gestCde.menuGeneral(cde, tabArt,numCde);
					if(cde.taille()!=0) {  //Si la commande n'est pas vide (l'utilisateur a effectué le sopérations d ajout de code + quantité), on ajoute la commande dans tabCde
						tabCde.ajouter(cde);  
					}
			}
			
			//Boucle infinie
			public static String premierNumDispo(TableDesCommandes51 tabCde) { //CHAQUE FOIS LE COMPTEUR COMMENCE PAR 1 ==>
				int cpteur = 1;
				String cle="";
				DateUser dat = new DateUser();
				String prem = ""+dat.getAnnee()+""+dat.getMois()+""+dat.getJour(); //date inversée
				do {
					cle = prem+cpteur;
					if(tabCde.retourner(cle)==null) {
					return cle;
					}
					cpteur++;
				}while(true);
			}
			
			
			//SUPPRIMER une COMMANDE...2
			public void supprimer(TableDesCommandes51 tabCde, Object... objects)  throws AbandonException { //ON TRAITE LES EXCEPTIONS PAS ICI MAIS DANS LE CLIENT jAVA ==> THROWS
				//ICI C EST JUST EPOUR INDIQUER QUE L EXCEPTION EXISTE ET ELLE SERA TRAITER AILLEURS
				if(tabCde.taille()==0) {
					ES.affiche("\n\t LA TABLE des COMMANDES est VIDE");
				}
				else {
					String numCde = (String)ES.saisie("\n\t SAISISSEZ le NUMERO DE COMMANDE A SUPPRIMER:"+"\n\t\t"+tabCde.cle() + ":");
					tabCde.supprimer(numCde);
					ES.affiche("\t LA COMMANDE N"+numCde+" a été SUPPRIMEE\n");
				}
			}
			
			
			//MODIFIER une COMMANDE....3
			public int menuModifierCommande() throws AbandonException {
					String menu = "\n\t AJOUTER ou MODIFIER un ARTICLE.......1" //Correspond creerArt(TableArticle51 tabArt)
							     +"\n\t SUPPRIMER un ARTICLE...2" //CASCADE
							     +"\n\t FIN....................0" //CASCADE
								 + "\n\t\t\t\t VOTRE CHOIX:";
					return (int)ES.saisie(menu, 0, 2);		
			}			

			public void modifier(TableDesCommandes51 tabCde, Object...objects) throws AbandonException {
				TableArticle51 tabArt = (TableArticle51) objects[0];
				String numCde = (String)ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a MODIFIER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
				UneCommande51 <String> cde = tabCde.retourner(numCde);
					if(cde==null) {/*Afficher mssage*/ }
						//else {gestCde.menuGeneral(tabCde, tabArt, numCde);}
					else {gestCde.menuGeneral(cde, tabArt, numCde);}
						//(TableDesCommandes51 tabCde, TableArticle51 tabArt, String numCde)
			}
					
			//AFFICHER une COMMANDE....4
			public void afficher(TableDesCommandes51 tabCde) throws AbandonException {
				if(tabCde.taille()==0) {
					ES.affiche("\n\t LA TABLE des COMMANDES EST VIDE, IL y a RIEN a AFFICHER");
					}
				else {	
					String numCde = (String)ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a AFFICHER:\n\t "+ tabCde.cle() + ":");
					ES.affiche(tabCde.retourner(numCde).toString());
				}
			}
			
			//AFFICHER les COMMANDES...5
			public void visualiserToutesCommandes(TableDesCommandes51 tabCde) { //case 4: visualiserToutesCommandes
				if(tabCde.taille()==0){
					ES.affiche("\n\t TABLE DES COMMANDES EST VIDE");
				}
				else {
					ES.affiche(tabCde.toString()); //N est pas créée dans TabledesCommandes
				}
			}
			
			//FACTURER des COMMANDES...6
			
			private void facturer(TableDesCommandes51 tabCde, TableArticle51 tabArt) {
			if(tabCde.taille()==0){
				ES.affiche("\n\t IL N Y A PAS DE COMMMANDES A FACTURER");
			}
			else {
				ES.affiche(tabCde.facturerCommandes(tabArt));
			}	
		}

}
