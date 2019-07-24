package serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import mesExceptions.AbandonException;

public class testesta implements ActionListener{
	
	

	//Variables d'instance de JFrame
	JFrame menu = new JFrame();
	JButton ldc = new JButton(" SAISIR une LIGNE DE COMMANDE");
	JButton afficher = new JButton(" AFFICHER la COMMANDE EN COURS");
	JButton facture = new JButton(" EDITER la FACTURE");
	JButton fin = new JButton(" FIN");
	
	
	public void menuGeneral() throws AbandonException { //TableArticle61 tabArt, int numCde

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
		
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}

	
	

	public static void main(String[] args) throws AbandonException {
		
		testesta testik = new testesta();
		testik.menuGeneral();
	}




	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==ldc) {
			System.out.println("ligne de commande");
		}
		if(evt.getSource()==afficher) {
			System.out.println("afficher");
		}
		if(evt.getSource()==facture) {
			System.out.println("facture");
		}
		/*
		if(evt.getSource()==fin) {
			ecrire(tabArt);
			menu.setVisible(false);
			new ClientJava61();
		}
		*/
		}

}
