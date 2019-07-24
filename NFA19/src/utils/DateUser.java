package utils;

import java.io.Serializable;
import java.util.Calendar;

public class DateUser implements Serializable{ // Cette classe doit valider toutes les données de la classe

	public int jour; // Variables d'instance
	public int mois;
	public int annee;
	public String string;

	// CONSTRUCTEUR 1 - pou récuprérer la date du Système

	public DateUser() {
		java.util.Calendar calc = java.util.Calendar.getInstance();
		this.jour = calc.get(Calendar.DAY_OF_MONTH);
		this.mois = calc.get(Calendar.MONTH) + 1; // parce que commence à zero
		this.annee = calc.get(Calendar.YEAR);
	}

	// Constructeur 2 - pour creer n'importe quelle date. A l'instance - date
	// d'anniversaire
	public DateUser(int jour, int mois, int a) { // Paramètres
		this.jour = jour;
		this.mois = mois;
		this.annee = a;
	}

	// GETTERS
	public int getJour() {return jour;}
	public int getMois() {return mois;}
	public int getAnnee() {return annee;}

	// SETTERS
	public void setJour(int j) {j = jour;}
	public void setMois(int k) {k = mois;}
	public void setAnnee(int l) {l = annee;}

	// toString() RETOURNE LES VARIABLES DE LA CLASSE - // ex: affiche(dat1.toString());
	public String toString() {return jour + "/" + mois + "/" + annee;}

	
// METHODE POUR VALIDER LA DATE
	public static boolean validDate(int jour, int mois, int annee) { // On appelle avec la classe, indépendement de l'objet DateUser
		return jour <= nbMaxJour(mois, annee);
	}

	// METHODES POUR VERIFIER LA DATE
	public static int nbMaxJour(int mois, int annee) { // Verifier le nombre de jours dans le mois
		switch (mois) {
		case 4:
		case 6:
		case 9:
		case 11:
			return 30; // il y a 30 jours dans les mois 4, 6, 9, 11
		case 2:
			if (bissextile(annee))
				return 29;
			else
				return 28;
		default:
			return 31; // tous les autres mois restans
		}
	}

	public static boolean bissextile(int annee) { // Verifier si l'annee est bissextile
		boolean test;
		if ((annee % 400 == 0) || ((annee % 100 != 0) & (annee % 4 != 0))) {
			test = false;
		} else {
			test = true;
		}
		return test;
	}
	
//METHODE POUR COMPARER 2 DATES	
	public boolean avant(DateUser ct) { 
		boolean test;
		if ((ct.getAnnee() > this.getAnnee()) || ((ct.getAnnee() == this.getAnnee()) && (ct.getMois() > this.getMois()))
				|| ((ct.getAnnee() == this.getAnnee()) && (ct.getMois() == this.getMois())&& (ct.getJour() >= this.getJour()))) {
			test = true; // si DateAuj>DateAnniv
		} else {
			test = false; // Date correct
		}
		return test;
	}

// METHODES POUR TRAVAILLER SUR LES DATES

	// METHODE LENDEMAIN
	public void lendemain1() { // On modifie l'objet courant //méthode d 'instance peut appeler un méthode
								// static, mais pas l'envers

		this.jour++; // passe au jour de plus
		if (jour > nbMaxJour(mois, annee)) { // si le dernier jour du mois
			jour = 1;
			mois++;
			if (mois > 1) {// rajouter l'annne // OU 1, il faut verifier ??
				mois = 1;
				annee++;
			}
		}
	}

	public DateUser lendemain2() { // Crer un nouveau objet sans modifier le premier
		DateUser dat1 = new DateUser(); // Cree un objet qui recupère les 3 paramètres
		dat1.lendemain1();
		return dat1;
	}

	// METHODE HIER
	public void hier1() { // On modifie l'objet courant
		if (jour == 1) { // si c'est le premier jour du mois, on doit mettre le dernier jour du mois
			jour = nbMaxJour(mois, annee);
			mois = mois--;
		} else {
			jour--;
		}
	}

	public DateUser hier2() { // On cree un nouvel objet
		DateUser dat2 = new DateUser(this.jour, this.mois, this.annee); // pour ça on cree un nouveau objet en lui
																		// attribuant les valeurs de paramètres = dat1
		dat2.hier1(); // Sur le nouveau objet on applique une méthode hier1
		return dat2;
	}

	// METHODE POUR CALCULER L'AGE
	public int age() {// On travaille sur l'objet existant ==>this
		DateUser dateActuel = new DateUser(); // date actuelle
		// DateActuel sera egale à this.jour
		// On compare l'objet courant avec la date actuelle un des deux est get.blabla l'autre est this.blabla
	
		int age; // On cree une variable age qui = la valeur de retour de la fonction

		if ((dateActuel.getMois() < this.getMois())
				|| ((dateActuel.getMois() == this.getMois()) && (dateActuel.getJour() < this.getJour()))) { // On compare mois, ensuitz mois et jour																							
			age = (dateActuel.getAnnee() - this.getAnnee()) - 1;
		} // formule ne marche pas, n'enlève pas 1
		else {
			age = dateActuel.getAnnee() - this.getAnnee();
		}
		return age;
	}

	
	// METHODE POUR JOUR DE LA SEMAINE
	public String jourDeSemaine() { //Toujours erreur dans la formule
		

		int jj = this.jour;
		int mZ=this.mois;
		int anneeZ=this.annee;
		
		if (mZ == 1) {
			mZ = 11;
			anneeZ--;
		}
		if (mZ == 2) {
			mZ = 12;
			anneeZ--;
		}
		else {
			mZ=mZ-2;
		}
		
		int aZ = anneeZ % 100;
		int sZ = (int) (anneeZ / 100);
		
		int Z = ((int)((2.6 * mZ - 0.2) + jj + aZ + (int)(aZ/4) + (int)(sZ/4 - 2*sZ)))%7; 
		
		if(Z<0) {
			Z=Z+7;
		}
		//ERREUR CALCUL, RESULTAT "ADAPTE A L ERREUR

		switch(Z) {
		case 5: return "Vendredi";
		case 6: return "Samedi";
		case 0: return "Dimanche";
		case 1: return "Lundi";
		case 2: return "Mardi";
		case 3: return "Mercredi";
		case 4: return "Jeudi";
		
		}
		return null;
	}
}



