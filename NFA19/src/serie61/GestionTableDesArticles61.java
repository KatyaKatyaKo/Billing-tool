package serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import connexion.connexionFichier;
import jPane.ES;
import mesExceptions.AbandonException;
import mesInterfaces.interfaceGestion;
import serie61.ClientJava61;
import serie61.TableArticle61;
import serie61.TableDesCommandes61;


public class GestionTableDesArticles61 implements interfaceGestion<TableArticle61>, ActionListener {
	private ES ES = new ES();
	private String nomPhysique;
	private connexionFichier<TableArticle61> fichArt;
	
	TableArticle61 tabArt; 
	TableDesCommandes61 tabCde; 
	
	//Varialbe sd instances de jFrame
	JFrame menu = new JFrame();
	
	JButton creer = new JButton(" CREER");
	JButton afficher = new JButton( "AFFICHER");
	JButton fin = new JButton( "FIN");
	
	//Nouveaux boutons
	JButton afficherPromo = new JButton(" AFFICHER LES PROMOTIONS");     
	JButton supprimer = new JButton (" SUPPRIMER");    				//NE MARCHE PAS!!!	
	JButton modifier = new JButton (" MODIFIER"); 	    
	JButton transformerPromo = new JButton(" TRANSFORMER EN PROMO)"); //PAS FAIT ENCORE 
	

	//Constructeur - on récupère grace au constructeur qui ocntient le nomPhysique fichier
	public GestionTableDesArticles61(String nomPhysique) {
		this.nomPhysique=nomPhysique;
		fichArt = new connexionFichier<TableArticle61>(nomPhysique);
	}
	
	//Getters-Setters
	public String getNomPhysique() {
		return nomPhysique;
	}

	public void setNomPhysique(String nomPhysique) {
		this.nomPhysique = nomPhysique;
	}
	
	//Méthodes
	public TableArticle61 recuperer() {
		TableArticle61 tabArt = fichArt.recuperer();
		if(tabArt==null) {
			//ES.affiche("NOUVEAU FICHIER A CREER");
			tabArt = new TableArticle61(); //On récupère les articles par défaut depui sla callse si le fichier est vide
		}
		return tabArt;
	}
	
	public void ecrire(TableArticle61 tabArt) {
		fichArt.ecrire(tabArt);
	}
	
	public void fermer(TableArticle61 tabArt) {
		fichArt.fermer(tabArt);
	}
	//************************************** MENU GESTION STOCK ****************************************
	
	public void menuGeneral(TableArticle61 tabArt, Object...obj ) throws AbandonException {
		TableDesCommandes61	tabCde = (TableDesCommandes61) obj[0]; //CAST DE PARAM 0
		
		this.tabArt = tabArt;
		this.tabCde  = tabCde;
		
		menu.setLayout(new GridLayout(3,1));
		menu.setTitle(" GESTION STOCK");
		menu.setSize(350,300);
		
		//Evenements
		creer.addActionListener(this);
		supprimer.addActionListener(this);
		modifier.addActionListener(this);
		afficher.addActionListener(this);
		afficherPromo.addActionListener(this);
		fin.addActionListener(this);
	
		//Remplir le Frame - NB - c'est l'ordre d'ajout qui crée l'ordre d'apparition des boutons
		menu.add(creer);          //Choix 1
		menu.add(supprimer);      //Choix 2
		menu.add(modifier);       //Choix 3
		menu.add(afficher);       //Choix 4
		menu.add(afficherPromo);  //Choix 5
		menu.add(fin);			  //Choix 6
	
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		
	}
	
	//************************************** METHODES GESTION STOCK ****************************************
	
	//REVERIFIER LA METHODE
	private void transformer(TableArticle61 tabArt) throws AbandonException {
		int code = (int)ES.saisie("\n\t CHOISSISSEZ UN ARTICLE A TRANSFORMER: "+tabArt.cle(), 1);
		ArticleAbstract61 art = tabArt.retourner(code);
		if(art==null) ES.affiche("\t ARTICLE N'EXISTE PAS\n");
		else {
			if(art instanceof ArticlePromo61) {art=new Article61(art.getCode(),art.getDesignation(),art.getPu());
				tabArt.ajouter(art); //PAS BESOIN DE SUPPRIMER C ES TLE MEME CODE
			}
			else {
				int quantite  = (int)ES.saisie("\n\t ARTICLE TRANSFORME EN PROMO: ", 1);
				float reduction = (float)ES.saisie("\n\t ARTICLE TRANSFORME EN PROMO: ", 0f);
				art=new ArticlePromo61(art.getCode(),art.getDesignation(),art.getPu(), quantite, reduction);
				tabArt.ajouter(art);
			}
		}
	}

			//CREER un NOUVEL ARTICLE..1 
			public void creer(TableArticle61 tabArt, Object... objects) throws AbandonException {
				ArticleAbstract61 art = saisieArt(tabArt);
				if(art!=null) {
					tabArt.ajouter(art); //A CE MOMENT EST INITIALISE LA TABLE DES COMMANDES AVEC 5 ARTICLES	
				}
				else ES.affiche("\t CODE ARTICLE EXISTE DEJA\n");
			}
			
			private ArticleAbstract61 saisieArt(TableArticle61 tabArt) throws AbandonException { //Comment afficher pour que ca sera sur la meme ligne
				int code;
				code=(int)ES.saisie("\n\t SAISISSEZ LE CODE DE L'ARTICLE\n\t (Le premier numéro disponible est " + (tabArt.premierNumDispo()) + ")",1); //COMPLETER LE MESSAGE!!!!!
				if (tabArt.retourner(code)== null) {
					String designation = ES.saisie("\t DESIGNATION ARTICLE: ");
					designation=designation.toUpperCase(); //PAS SURE QUE CA MARCHE
					//NE MARCHE PAS AVEC LE FLOAT DANS LA METHIDE SAISIE
					int pu = (int)ES.saisie("\t PRIX UNITAIRE (saisissez un entier): ", 1); //RAJOUTER UNE METHODE LIRE FLOAT
					
				
					if (ES.saisieOuiNon(" Promo Ou Pas? "))
					{ // Article promo
						int mini = (int)ES.saisie(" Quelle Quantite Mini? ", 1);
						//NE MARCHE PAS AVEC LE FLOAT DANS LA METHIDE SAISIE
						int reduc= (int)ES.saisie(" Combien de reduction? ", 1);
						return new ArticlePromo61(code, designation,pu, mini, reduc);
						
					}
					
					return new Article61(code, designation, pu); 
				}
				return null;
			}
			
			//SUPPRIMER UN ARTICLE.....2  // Cascade des valeurs
			public  void supprimer(TableArticle61 tabArt, Object...obj ) throws AbandonException {
				TableDesCommandes61 tabCde = (TableDesCommandes61)obj[0];
				int code = (int)ES.saisie("\n\t CHOISSIEZ un ARTICLE a SUPPRIMER"+tabArt.cle()+":", 1);
				if (tabArt.retourner(code)==null) {
					ES.affiche("\t ARTICLE N'EXISTE PAS\n");
				}
				else {
					tabArt.supprimer(code);
					tabCde.purge(code);
					ES.affiche("\t ARTICLE A ETE SUPPRIME\n");
				}
			}
			
			//MODIFIER UN ARTICLE......3  
			public void modifier(TableArticle61 tabArt, Object... objects) throws AbandonException { //On modifie la designation
				int code = (int)ES.saisie("\n\t CHOISSISSEZ UN ARTICLE A MODIFIER: "+tabArt.cle(), 1);
				ArticleAbstract61 art = tabArt.retourner(code);
				if(art==null) {
					ES.affiche("\t ARTICLE N'EXISTE PAS\n");
				}
				else {
					if (art instanceof ArticlePromo61) {
						System.out.println("\n\t\t C'EST UN ARTICLE PROMO");
						int quantiteMini = (int)ES.saisie("\n\t SAISISSEZ LA NOUVELLE QUANTITE MINIMALE (Actuellement "+((ArticlePromo61) art).getQuantiteMini()+"):", 1, Integer.MAX_VALUE);
						((ArticlePromo61) art).setQuantiteMini(quantiteMini);
						if (quantiteMini!=0)  //n est pas vide parce qu'il y a un retour chariot avant ca veut dire qu on a pas rajouter un article, parce quand on ajoute un article on l'ajoute lcean, en supprimant le retour chariot (voir saisie dans DataUser)
							((ArticlePromo61) art).setQuantiteMini(quantiteMini);
						//!!!!!!ICI PROBLEME DE CONVERSION FLOAT STRING!!!!!!
						float reduc = (int)ES.saisie("\n\t SAISISSEZ LA NOUVELLE REDUCTION (Actuellement "+((ArticlePromo61) art).getReduc()+"):", 1);
						((ArticlePromo61) art).setReduc(reduc);
					}
					String designation = ES.saisie("\n\t SAISISSEZ LA NOUVELLE DESIGNATION:"); //On appue enter (rc) si on veut rien modifier
					if (!designation.equals("")) { //n est pas vide parce qu'il y a un retour chariot avant ca veut dire qu on a pas rajouter un article, parce quand on ajoute un article on l'ajoute lcean, en supprimant le retour chariot (voir saisie dans DataUser)
						art.setDesignation(designation.toUpperCase()); //Affichage at UpperCase
					}
					String pu  = ES.saisie("\n\t SAISISSEZ LE NOUVEAU PRIX: "); //On appue enter (rc) si on veut rien modifier
					if (!pu.equals("")) art.setPu(Float.parseFloat(pu));	
				}
				}
	
			//AFFICHER LE STOCK........4
			public void afficher(TableArticle61 tabArt) { //Correspond à 4 - afficher le stock
				if (tabArt.taille() == 0)
					ES.affiche("STOCK VIDE)");
				else{
					ES.affiche(tabArt.toString());
				}
			}
			
			//AFFICHER LES PROMOTIONS........5
			public void afficherPromo(TableArticle61 tabArt)
			{
				if (tabArt.taille()==0) ES.affiche("\n ** AUCUN ARTICLE EN STOCK ");
				else ES.affiche(tabArt.promo());
			}
			
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if (evt.getSource()==creer) {     		//Choix 1
						creer(tabArt);	
					}
					if(evt.getSource()==supprimer) {  		//Choix 2
						supprimer(tabArt);
					}
					if(evt.getSource()==modifier) {   		//Choix 3
						modifier(tabArt);
					}
					if (evt.getSource()==afficher) {  		//Choix 4
						afficher(tabArt);	
					}
					if(evt.getSource()==afficherPromo) {    //Choix 5
						afficherPromo(tabArt);
					}
					if(evt.getSource()==fin) {    		    //Choix 6
						ecrire(tabArt);
						menu.setVisible(false);
						new ClientJava61();
					}
				}
				catch(Exception ect) {}
			}
}
