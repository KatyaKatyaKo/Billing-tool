package serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import connexion.connexionFichier;
import jPane.ES;
import mesExceptions.AbandonException;
import mesInterfaces.interfaceGestion;
import utils.DateUser;

public class GestionTableDesCommandes61 implements interfaceGestion <TableDesCommandes61>, ActionListener{
	public ES ES = new ES();
	String numCde;
	//Serie 51
	//Ce sont les nouvelles variables ==> il faut un constructeur 
	private String nomPhysique;
	private connexionFichier<TableDesCommandes61> fichCommandes;
	
	
	GestionUneCommande61 gestUneCommande = new GestionUneCommande61(); 
	UneCommande61<String> cde;
	TableArticle61 tabArt; 
	TableDesCommandes61 tabCde; 
	
	//JFrame
	JFrame menu = new JFrame();
	
	//Les boutons du Menu
	JButton creer = new JButton (" CREER");
	JButton supprimer = new JButton (" SUPPRIMER");
	JButton modifier = new JButton (" MODIFIER");
	JButton afficher = new JButton( " AFFICHER une COMMANDE");
	JButton afficherCommandes = new JButton( " AFFICHER des COMMANDES");
	JButton facturer = new JButton(" FACTURER");
	JButton fin = new JButton(" FIN");
	
	//Constructeur
	public GestionTableDesCommandes61(String nomPhysique) {
		fichCommandes=new connexionFichier<TableDesCommandes61>(nomPhysique);	
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
	public TableDesCommandes61 recuperer() {
		TableDesCommandes61 tabCde = fichCommandes.recuperer();
		if (tabCde==null) {
		//	ES.affiche("NOUVEAU FICHIER A CREER");
			tabCde = new TableDesCommandes61();
		}
		return tabCde;
	}
	
		//Ecrire
	public void ecrire(TableDesCommandes61 tabCde) {
		fichCommandes.ecrire(tabCde);
	}
	
	public void fermer(TableDesCommandes61 tabCde) {
		fichCommandes.fermer(tabCde);
	}
	
	private GestionUneCommande61 gestCde = new GestionUneCommande61(); //Pour gérer une Commande
	
	//************************************** MENU GESTION DES COMMANDES ****************************************
		
			public void menuGeneral(TableDesCommandes61 tabCde, Object...objects) throws AbandonException {  //TableArticle51 tabArt
				
				//On met en place le cadre
				menu.setLayout(new GridLayout(3,2));
				menu.setTitle("GESTION COMMANDES");
				menu.setSize(350,300);
				
				//On ajoute des boutons
				menu.add(creer);
				menu.add(supprimer);
				menu.add(modifier);
				menu.add(afficher);
				menu.add(afficherCommandes);
				menu.add(facturer);
				menu.add(fin);
				
				//On les écoute
				creer.addActionListener(this);
				supprimer.addActionListener(this);
				modifier.addActionListener(this);
				afficher.addActionListener(this);
				afficherCommandes.addActionListener(this);
				facturer.addActionListener(this);
				fin.addActionListener(this);
				
				
				//Set 
				menu.setVisible(true);
				menu.setLocationRelativeTo(null);
				}
		
			
	//------------------------------------METODES GESTION DES COMMANDES --------------------------------
	
	
			public void creer(TableDesCommandes61 tabCde, Object... objects) throws AbandonException { //A REVOIR	
				TableArticle61 tabArt = (TableArticle61)objects[0];
				String numCde = (String)objects[1];
				//On cree une commande
				UneCommande61<String> cde = new UneCommande61<String>();
				cde.setCode((String)numCde); //On attribut un numéro à une commande
				//on crée une variable de gestionnaireUneCommande
				GestionUneCommande61 gestCde = new GestionUneCommande61 ();
				gestUneCommande.menuGeneral(cde);
				gestCde.menuGeneral(cde, tabArt,numCde);
					if(cde.taille()!=0) {  //Si la commande n'est pas vide (l'utilisateur a effectué le sopérations d ajout de code + quantité), on ajoute la commande dans tabCde
						tabCde.ajouter(cde);  
					}
			}
			
			//Boucle infinie
			public static String premierNumDispo(TableDesCommandes61 tabCde) { //CHAQUE FOIS LE COMPTEUR COMMENCE PAR 1 ==>
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
			public void supprimer(TableDesCommandes61 tabCde, Object... objects)  throws AbandonException { //ON TRAITE LES EXCEPTIONS PAS ICI MAIS DANS LE CLIENT jAVA ==> THROWS
				//ICI C EST JUST EPOUR INDIQUER QUE L EXCEPTION EXISTE ET ELLE SERA TRAITER AILLEURS
				if(tabCde.taille()==0) {
					jPane.ES.affiche("\n\t LA TABLE des COMMANDES est VIDE");
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

			public void modifier(TableDesCommandes61 tabCde, Object...objects) throws AbandonException {
				TableArticle61 tabArt = (TableArticle61) objects[0];
				String numCde = (String)ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a MODIFIER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
				UneCommande61 <String> cde = tabCde.retourner(numCde);
					if(cde==null) {/*Afficher mssage*/ }
						//else {gestCde.menuGeneral(tabCde, tabArt, numCde);}
					else {gestCde.menuGeneral(cde, tabArt, numCde);}
						//(TableDesCommandes51 tabCde, TableArticle51 tabArt, String numCde)
			}
					
			//AFFICHER une COMMANDE....4
			public void afficher(TableDesCommandes61 tabCde) throws AbandonException {
				if(tabCde.taille()==0) {
					ES.affiche("\n\t LA TABLE des COMMANDES EST VIDE, IL y a RIEN a AFFICHER");
					}
				else {	
					String numCde = (String)ES.saisie("\n\t SAISISSEZ le NUMERO de COMMANDE a AFFICHER:\n\t "+ tabCde.cle() + ":");
					ES.affiche(tabCde.retourner(numCde).toString());
				}
			}
			
			//AFFICHER les COMMANDES...5
			public void afficherCommandes(TableDesCommandes61 tabCde) { //case 4: afficherCommandes
				if(tabCde.taille()==0){
					ES.affiche("\n\t TABLE DES COMMANDES EST VIDE");
				}
				else {
					ES.affiche(tabCde.toString()); //N est pas créée dans TabledesCommandes
				}
			}
			
			//FACTURER des COMMANDES...6
			
			private void facturer(TableDesCommandes61 tabCde, TableArticle61 tabArt) {
			if(tabCde.taille()==0){
				ES.affiche("\n\t IL N Y A PAS DE COMMMANDES A FACTURER");
			}
			else {
				ES.affiche(tabCde.facturerCommandes(tabArt));
			}	
		}

			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if (evt.getSource()==creer) {     		//Choix 1
						menu.setVisible(false);
						creer(tabCde);
					}
					if(evt.getSource()==supprimer) {  		//Choix 2
						supprimer(tabCde);
					}
					if(evt.getSource()==modifier) {   		//Choix 3
						modifier(tabCde);
					}
					if (evt.getSource()==afficher) {  		//Choix 4
						afficher(tabCde);
					}
					if(evt.getSource()==afficherCommandes) {    //Choix 5
						afficherCommandes(tabCde);
					}
					if(evt.getSource()==facturer) {    //Choix 5
						facturer(tabCde, tabArt);
					}
					
					if(evt.getSource()==fin) {    	   //Choix 6 - OK
						ecrire(tabCde);
						menu.setVisible(false);
						new ClientJava61();
					}
				
				}
				catch(Exception ect) {}
			}

}
