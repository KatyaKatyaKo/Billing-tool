package serie21;

import java.util.Vector;

public class TableArticles21 {

	// VARIABLES D'INSTANCE
	private Vector<Article21> tabArt = new Vector<Article21>();

	// CONSTRUCTOR
	public TableArticles21() { //POUR LE MOMENT LE CONSTRUCTEUR PAR DEFAULT CREE UN VECTEUR AVEC CES 5 ELEMENTS, PAS DE POSSIBILITÉ DE CREER LES OBJETS "LIBRES"
		Article21 art1 = new Article21(1, "DISK DUR 1TO", 50.5f);
		Article21 art2 = new Article21(2, "CARTE MERE", 1000f);
		Article21 art3 = new Article21(3, "CARTE RESEAU", 24.7f);
		Article21 art4 = new Article21(4, "BOITE 100CD", 75.5f);
		Article21 art5 = new Article21(5, "MEMOIRE FLASH", 17f);

		tabArt.addElement(art1);
		tabArt.addElement(art2);
		tabArt.addElement(art3);
		tabArt.addElement(art4);
		tabArt.addElement(art5);
	}

	// SETTERS
	public void setTabArt(Vector<Article21> tab) {tab = this.tabArt;} 	// EST-CE CORRECT?
	
	// GETTERS
	public Vector<Article21> getTabArt(Vector<Article21> tab){return this.tabArt;} // EST-CE CORRECT?
	
	// ToString() - boucle sur la structure
	public String toString() {
		String mes = "\t\t CONTENU DE STOCK:\n";
		for (int i = 0; i < taille(); i++) {
			mes = mes +"\t"+ tabArt.get(i).toString()+"\n";
		}
		return mes;
	}

	
	// 4 Méthodes à rajouter dans chaque class de type structure

	public int taille() {return tabArt.size();}

	public Article21 retourner(int code) { // On cherche par code et ça retourne l'article, 	
		for (int i = 0; i < taille(); i++) {
			if (tabArt.get(i).getCode() == code)
				return tabArt.get(i); //Retourne le ???
		}
		return null;  
	}
	
	public String retournerParCode(int code) { // On cherche par code et ça retourne l'article, 
		for (int i = 0; i < taille(); i++) {
			if (tabArt.get(i).getCode() == code)
				return tabArt.get(i).getDesignation(); //Retourne la designation produit
		}
		return null;
	}
	
	public int retournerParDesignation (String designation) {
		for (int i = 0; i < taille(); i++) {
			if (tabArt.get(i).getDesignation() == designation )
				return tabArt.get(i).getCode(); //Retourne le code du produit
			}
		return 0;
	}

	
	public Article21 ajouterWithCode(Article21 a) {
		for (int i = 0; i < taille(); i++) {
			if (a.getCode() != (tabArt.get(i).getCode())) { // Contraintes si l'article existe déjà
				tabArt.add(a);
			}
			else {System.out.println("Cet article existe déjà");}
		}
		return a; 
	}
	
	public Article21 ajouterWithDesignation(Article21 a) {
		for(int i=0; i<taille(); i++) {
			if(tabArt.get(i).getDesignation()!=a.getDesignation()) {
				tabArt.add(a);
			}
			else {
				System.out.println("Cet article existe déjà");
			}
		}
			return a;
	}
	
	public Article21 supprimerWithCode(Article21 a) { //supprimer (code) : supprime l’élément de clé ou d’indice « code » A RAJOUTER 2E METHODE AVEC RETOUR D'INDICE
		for (int i=0; i<taille(); i++) {
			if(a.getCode()==tabArt.get(i).getCode()) {
				tabArt.remove(i);
			}
			else {
				System.out.println("Cet article n'existe pas dans le stock.");
			}
		}
		return a; 
	}
	
	public Article21 supprimerWithDesignation(Article21 a) {
		for(int i=0; i<taille(); i++) {
			if(tabArt.get(i).getDesignation()==a.getDesignation()) {
				tabArt.remove(a);
			}
			else {
				System.out.println("Cet article n'existe pas dans le stock.");
			}
		}
		return a;
	}
	
}
