package serie61;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jPane.ES;
import mesExceptions.AbandonException;

public class GestionUneCommande61 implements ActionListener {
	public ES ES = new ES();
	
	TableArticle61 tabArt; 
	TableDesCommandes61 tabCde; 
	UneCommande61<String> cde;
	
	//Variables d'instance de JFrame
	JFrame menu = new JFrame();
	JButton ldc = new JButton(" SAISIR une LIGNE DE COMMANDE");
	JButton afficher = new JButton(" AFFICHER la COMMANDE EN COURS");
	JButton facture = new JButton(" EDITER la FACTURE");
	JButton fin = new JButton(" FIN");
	
	//************************************** MENU GESTION UNE COMMANDE ****************************************
			
			public void menuGeneral(UneCommande61<String> cde, Object...obj) throws AbandonException { //TableArticle61 tabArt, int numCde
			
				menu.setLayout(new GridLayout(3,1));
				menu.setTitle(" GESTION une COMMANDE");
				menu.setSize(350,300);
				
				menu.add(ldc);
				menu.add(afficher);
				menu.add(facture);
				menu.add(fin);
						
				ldc.addActionListener(this);
				afficher.addActionListener(this);
				facture.addActionListener(this);
				fin.addActionListener(this);
				
				menu.setVisible(true);
				menu.setLocationRelativeTo(null);
				
				TableArticle61 tabArt = (TableArticle61) obj[0];
				String numCde = (String)obj[1];
				this.tabArt= tabArt;
				
				
			}
	
			//------------------------------------METODES GESTION une COMMANDE --------------------------------



			//SAISIR une LIGNE DE COMMANDE.......................1
			private LigneDeCommande61 saisieLdc(TableArticle61 tabArt) throws AbandonException {
				jPane.ES.affiche("\n\t\t CREATION d'une LIGNE de COMMANDE\n");
				int codeArt = (int)ES.saisie("\t SAISISSEZ le CODE de l'ARTICLE "+ tabArt.cle(), 1, Integer.MAX_VALUE);
						if(tabArt.retourner(codeArt) instanceof ArticlePromo61) System.out.println("\n\t Cet article est en promotion!! Quantité minimale: " + ((ArticlePromo61)tabArt.retourner(codeArt)).getQuantiteMini());
						if (tabArt.retourner(codeArt)==null) return null;
						else { int quanity = (int)ES.saisie("\t SAISISSEZ la QUANTITE: ", 1, Integer.MAX_VALUE);
						return (new LigneDeCommande61(codeArt, quanity ));}
			}
			
			public void creer (UneCommande61<String> cde, Object...obj ) throws AbandonException { //TableArticle61 tabArt
				TableArticle61 tabArt = (TableArticle61) obj[0];
				LigneDeCommande61 ldc = saisieLdc(tabArt);
				if(ldc!= null) {
					int code = ldc.getCode(); 
					LigneDeCommande61 ldc2 = cde.retourner(code); //Au premier passage de la boucle le cde est encore vide==> par default ldc2== null, mais au 2e passage de la boucle il y a déjà cde.get(0) g^race à l'instruction cde.ajouter(ldc); à la fin de cette boucle
					if(ldc2!=null) { //Si la commande contient une ligne de commande (on la cjerche par un code saisi lors de la saisie d'une ligne de commande)
						ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());
					}
					else {
					cde.ajouter(ldc); 
					}
				}
				else {
					jPane.ES.affiche("\n\t ARTICLE COMMENDE N'EXISTE PAS");
				}
			}

			//AFFICHER la COMMANDE EN COURS......................2
			public void afficher (UneCommande61<String> cde){  //POURQUOI CETTE MÉTHODE EST STATIQUE??? LA DEPLACER PE DANS COMMANDE
				if(cde.taille()==0) {jPane.ES.affiche("\n\t\t COMMANDE EST VIDE");
				}
				else {jPane.ES.affiche(cde.toString());}	
			}
			
			
			//EDITER la FACTURE..................................3 //ERREUR EST LA
			private void facturer(UneCommande61<String> cde, TableArticle61 tabArt) { //Une commande
				if(cde.taille()==0){
					jPane.ES.affiche("\n\t COMMANDE EST VIDE");
				}
				else {
					jPane.ES.affiche(cde.facturer(tabArt)); // Affiche ligne de commande
				}	
			}

			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if(evt.getSource()==ldc) {
						creer(cde);
					}
					if(evt.getSource()==afficher) {
						afficher(cde);
					}
					if(evt.getSource()==facture) {
						facturer(cde, tabArt);
					}
					if(evt.getSource()==fin) {
						menu.setVisible(false);
						new ClientJava61();
					}
					
					} catch (AbandonException e) {
						e.printStackTrace();
					}
				}				
}
