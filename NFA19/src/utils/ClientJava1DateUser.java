package utils;
import java.io.*;


public class ClientJava1DateUser { // Cette classe client utilise seulement les objets, la validation se fait dans DateUser

	static java.util.Scanner sc = new java.util.Scanner(System.in); 
	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) { 
		// Récupération de la date du jour
		DateUser datAuj = new DateUser();
		affiche("On est le " + datAuj.toString()+"\n");

		// Date de naissance de l'utilisateur
		int jour;
		int mois;
		int annee;
		DateUser dat1; // Declaration de date

		for (;;) {
			affiche("Saisissez la date de naissance.");

			jour = lireEnt("\nJour: ", 1, 31);
			mois = lireEnt("Mois: ", 1, 12);
			annee = lireEnt("Annee:", 1, Integer.MAX_VALUE);

			if (DateUser.validDate(jour, mois, annee)) { // Si la date saisi au clavier est correct,
				dat1 = new DateUser(jour, mois, annee); // On cree un objet

				if (dat1.avant(datAuj)==true) { 
					affiche("Vous avez " + dat1.age() + " ans \n");
					System.out.print("Vous êtes né(e) le ");
					System.out.println(dat1.jourDeSemaine());
				
					// Affiche la date du lendemain
					//affiche(" "+dat1.lendemain1()+" "); //Ne marche pas
					break;
				} 
				else {
					System.out.println("DATE PAS VALIDE!\n");
				continue;
				}
			}
		}
	}

	// Methodes

	public static int lireEnt(String mes, int mini, int maxi) { // Static n'a pas besoin de variables d'instance. La
																// classe statique ne travaille pas qu'a des variables
																// statiques.
		// Exemple - classe main est statique
		
		 // Pourquoi on verifie pas cette info dans la classe
																	// date??? PE plus interessant de creer une méthode
																	// dans la classe DateUser
		affiche(mes);
		int data;
		do {
			data = sc.nextInt();

			if (data >= mini && data <= maxi)
				return data;
			affiche("RESAISISSER SVP");
		} while (true);
	}
	
	
	public static float lireFloat (String mes, float mini, float maxi) {
		affiche(mes);
		float data; //Le float to return
		do {
			data=sc.nextFloat(); //Scan le float
			//Si la conditon est correct
			if (data>=mini && data<=maxi)
				return data; //Scan le float
			//Si la condition n'est pas respectée
			affiche ("REISSAIEZ SVP");
		}while(true);
	}
	
	public static void affiche(String mes) {System.out.print(mes);}

	/*
	public static String lireString(String mes) {
		affiche(mes);
		String data=sc.nextLine(); //Ca permet de vider le retour chariot
		return data;
	}
	*/
	
	public static String lireString(String s) //A REVOIR
    {
		affiche(s);
	String tmp="";
	try {
	    tmp = in.readLine();
	}
	catch (IOException e) {
		System.out.println("hoho");}
	return tmp;
    } // fin de lireString()
	
}
