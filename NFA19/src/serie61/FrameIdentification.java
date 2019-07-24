package serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jPane.ES;

public class FrameIdentification extends JFrame implements ActionListener {	
	//Variables d'instances - car contneu editable
	
	private static final long serialVersionUID = 1L;
	//Largueur de la fenetre
	JTextField usr = new JTextField(15);
	JPasswordField pwd = new JPasswordField(15);
	JButton valider = new JButton("VALIDER");
	
	//Constructeur de la Frame
	public FrameIdentification() {
		JLabel username = new JLabel(" USERNAME");
		JLabel password = new JLabel(" MOT DE PASSE");
		
		JPanel panel1, panel2, panel3;
		
		//Declarer le frame
		//3 lignes, 2 colonnes
		setLayout(new GridLayout(3, 2));
		//largueur, hauteur
		setSize(350, 250);
		setTitle(" FENETRE D'IDENTIFICATION");
		
		//Compostion de Panel1
		panel1 = new JPanel();
		panel1.add(username);
		panel1.add(usr); //Crée un evenement
		add(panel1);
		
		//Compostion de Panel2
		panel2 = new JPanel();
		panel2.add(password);
		panel2.add(pwd);
		add(panel2);
		
		//Compostion de Panel3
		panel3 = new JPanel();
		panel3.add(valider); //Ecoute l'évenement qui se produit ici
		valider.addActionListener(this); //Tout ce qui se produit ici, sera écouter par la méthode actionPerformed
		add(panel3);
		
		//Metrre au milieu
		this.setLocationRelativeTo(null);
		
		//Affichage
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==valider) {
			controle();
		}	
	}
	
	public void controle() {
		String userName = usr.getText();
		char[] mdpC = pwd.getPassword(); //retourne un tableau de char 
		
		String mdpS = convert(mdpC); //
		if(verif(userName, mdpS)) {
			ES.affiche(" Declanchement de client Série 61");
			setVisible(false); //On ferme le Frame
			new ClientJava61(); //C'est ici qu'on appelle un nouveau Frame-application elle même
			}
		else {
			ES.affiche(" Ressaisissez");
			raz();
			}
	}
	
	public String convert(char[] mdp) {
	String setConvert="";
	for (int i=0; i<mdp.length; i++) {
		setConvert = setConvert+mdp[i];
	}
	return setConvert;
	}
	
	public boolean verif(String usr, String pwd) { //Balayage du tableau des users autorisés
		 return ((usr.equals("psi"))&&(pwd.equals("psi"))); 
	}

	public void raz() {
		usr.setText("");
		pwd.setText("");
	}
}
