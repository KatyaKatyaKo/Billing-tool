package serie61;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import jPane.ES;
import mesExceptions.AbandonException;


public class ClientJava61 implements ActionListener {
	//Saisie
	//Plus besoin de ES, car la saisie passe par le Frame
	
	//Tables
	GestionTableDesArticles61 gestArt = new GestionTableDesArticles61("stock.DATA"); 
	GestionTableDesCommandes61 gestCommandes = new GestionTableDesCommandes61("commandes.DATA");
	
	TableArticle61 tabArt = gestArt.recuperer(); 
	TableDesCommandes61 tabCde =gestCommandes.recuperer(); 
	
	//Nouvelle variable
	JFrame menuGene = new JFrame();
	
	JButton stock = new JButton( "GESTION STOCK");
	JButton commandes = new JButton (" GESTION DES COMMANDES");
	JButton sauvegarde = new JButton(" SAUVEGARDE DE FICHIER");
	JButton fin = new JButton(" FIN");
	
	public ClientJava61(){
		//Declarer le frame MenuGeneral
		menuGene.setLayout(new GridLayout(2, 2)); 	//3 lignes, 2 colonnes
		menuGene.setSize(450, 400);
		menuGene.setTitle(" GESTION SUPERETTE");
		
		//Ajouter les evenements
		stock.addActionListener(this);
		commandes.addActionListener(this);
		sauvegarde.addActionListener(this);
		fin.addActionListener(this);
		
		//Mettre les composantes
		menuGene.add(stock);
		menuGene.add(commandes);
		menuGene.add(sauvegarde);
		menuGene.add(fin);
		
		//Afficher le frame
		menuGene.setLocationRelativeTo(null);
		menuGene.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==stock) {
			menuGene.setVisible(false); //On éteint le Frame actuel
			new GestionTableDesArticles61(null); //On ouvre le nouveau Menu Stock
				try {
					gestArt.menuGeneral(tabArt, tabCde);
				} catch (AbandonException e) {
					e.printStackTrace();
				}	
		}
		if(evt.getSource()==commandes) {
			menuGene.setVisible(false); //On éteint le Frame actuel
			new GestionTableDesCommandes61(null);
				try {
					gestCommandes.menuGeneral(tabCde);
				}
				catch(AbandonException e) {
					e.printStackTrace();
				}
			}
		
		if(evt.getSource()==fin) {
			//Enregistrer les fichier
			gestArt.ecrire(tabArt); 
			gestCommandes.ecrire(tabCde);
			//Fermer les fichier
			gestArt.fermer(tabArt);  
			gestCommandes.fermer(tabCde);
			//Ferme les Frames
			menuGene.setVisible(false);
			ES.affiche("\n\t *** SAUVEGARDE FU FICHIER ***\n");
			ES.affiche("\n\t *** AU REVOIR A BIENTOT DANS NOTRE SUPERETTE ***\n"); 		
			//Ferme l'application
			System.exit(0);	
		}
	}
		
	public static void main(String[] args) throws AbandonException {
				new FrameIdentification();			
	}
}
