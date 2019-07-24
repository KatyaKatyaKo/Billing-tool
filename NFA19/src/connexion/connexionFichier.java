package connexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import iConsole.ES;


//pParametres générique

public class connexionFichier <typeElement>{
	private ES ES = new ES();
	//Nom physique vs nom logique

	private String nomPhysique;
	
	//Constructeur
	public connexionFichier(String nomPhysique) { //path
		this.nomPhysique=nomPhysique;
	}
	
	//3 exceptions à resoudre
	public typeElement recuperer() {
		typeElement tab = null;
		try {
			//InputStream = fochier= lien vers le fichier ==> InputStream.readObject() - ce qu'on peut faire avec ce path
		FileInputStream fis = new FileInputStream(nomPhysique); //Recupère le fichier physique
		ObjectInputStream ois = new ObjectInputStream(fis); //Transforme le fichier physique en Object
		tab = (typeElement)ois.readObject(); //On crée un Object avec readObject() depuis le fichier
		}
		catch(FileNotFoundException fnfe) {
			ES.affiche("Nouveau fichier à creer **\n");
		}
		catch(IOException ioe) {
			ES.affiche("Problème de lecture fichier **\n"); //Disque endommagé
		}
		catch(ClassNotFoundException cnfe) {
			ES.affiche("Problème de compatibilité **\n"); //Problème de compatibilité
		}
		return tab;
	}
	 //RALE QUE LE FICHIER N'EST AJMAIS FERMÉ
	public void ecrire(typeElement tab) {
		try {
		FileOutputStream fos = new FileOutputStream(nomPhysique); //Fichier où on va écrire , il y a 2 paramètres: 
		ObjectOutputStream oos = new ObjectOutputStream(fos);//true - rajoute le nouveau table à la fin du fichier, false - supprime l ancien et cree un noveu
		oos.writeObject(tab);
		}
		catch(FileNotFoundException fnfe) {
			ES.affiche("Nouveau fichier à creer **\n");
		}
		catch(IOException ioe) { //Support endommagé ou pplus de place sur le disque
			ES.affiche("SUPPORT ENDOMMAGE OU PLUS DE PLACE SUR LE DISQUE **\n");
		}
		}
	
	//Il faut rendre les données sérialisables
	
	//Pour la fermeture du fichier
	public void fermer(typeElement tab) {
		try {
			FileInputStream fis = new FileInputStream(nomPhysique);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ois.close();
		}
		catch(IOException ioe) {
			ES.affiche("FICHIER A FERMER INEXISTANT");
		}
	}
	}
	
	
