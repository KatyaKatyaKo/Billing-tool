package serie22;
import java.util.TreeMap;

import serie21.Article21;

public class TableArticle22 {
	//Variable d'instance
	private TreeMap <Integer, Article21> tabArt = new TreeMap <Integer, Article21>(); //Integer = art1.getCode()
	
	//Constructeur
	public TableArticle22(){
		Article21 art1 = new Article21(1, "DISK DUR 1TO", 50.5f);
		Article21 art2 = new Article21(2, "CARTE MERE", 1000f);
		Article21 art3 = new Article21(3, "CARTE RESEAU", 24.7f);
		Article21 art4 = new Article21(4, "BOITE 100CD", 75.5f);
		Article21 art5 = new Article21(5, "MEMOIRE FLASH", 17f);
		
		tabArt.put(art1.getCode(), art1);
		tabArt.put(art2.getCode(), art2);
		tabArt.put(art3.getCode(), art3);
		tabArt.put(art4.getCode(), art4);
		tabArt.put(art5.getCode(), art5);
	}
	
	//Getters
	public TreeMap <Integer,Article21> getTabArt(){return tabArt; }
	
	//Setters		
	public void setTabArt(TreeMap <Integer,Article21> tabArt){this.tabArt = tabArt;}
	
	public String toString() {
		String st = "\n\t\t CONTENU DE STOCK: \n";
		for (Article21 art: tabArt.values()) {
			st =st+ art.toString()+"\n";	
		}
		return st;
	}
	
	public Article21 retourner(int code) { //Recupere directement
		return tabArt.get(code);
	}
	
	public void ajouter (Article21 art) {
		tabArt.put(art.getCode(), art);
	}
	
	public void supprimer(int code) {
		tabArt.remove(code);
	}
	
	public int taille() {
		return tabArt.size();
	}

	public String cle () {
		String st = "\n\t (Voici la iste des codes des articles: ";
		for(int code: tabArt.keySet()) {
			st = st+ code + " ";
		}
		return st ;
	}
	
	
	//Last element in a tree map
//	public int lastkey() {
//		int lastKey = 0;
//	for (Entry<Integer, Article21> any: this.tabArt.entrySet()) {
//		lastKey = any.getKey();
//		}
//	return lastKey;
//	}
	
	//Deuxième méthode pour accéder au dernier numéro dispo
	
	public int premierNumDispo() {
		int compteur = 1;
		do {
		
		if(retourner(compteur)==null) {
			return compteur;
		}
		else {
				compteur++;
			}
		}
		while(true); //Boucle infinie, car toujours true	
	}
	
	
	
}

