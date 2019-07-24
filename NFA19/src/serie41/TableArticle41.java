package serie41;
import java.util.TreeMap;


public class TableArticle41 {
	//Variable d'instance
	private TreeMap <Integer, Article41> tabArt = new TreeMap <Integer, Article41>(); //Integer = art1.getCode()
	
	//Constructeur
	public TableArticle41(){
		Article41 art1 = new Article41(1, "DISK DUR 1TO", 50.5f);
		Article41 art2 = new Article41(2, "CARTE MERE", 1000f);
		Article41 art3 = new Article41(3, "CARTE RESEAU", 24.7f);
		Article41 art4 = new Article41(4, "BOITE 100CD", 75.5f);
		Article41 art5 = new Article41(5, "MEMOIRE FLASH", 17f);
		
		//Article en promotion
		ArticlePromo41 art1Promo= new ArticlePromo41(300," CLE USB 1TO", 20.5f,10,50f);
		
		tabArt.put(art1.getCode(), art1);
		tabArt.put(art2.getCode(), art2);
		tabArt.put(art3.getCode(), art3);
		tabArt.put(art4.getCode(), art4);
		tabArt.put(art5.getCode(), art5);
		tabArt.put(art1Promo.getCode(), art1Promo);
	}
	
	//Getters
	public TreeMap <Integer,Article41> getTabArt(){return tabArt; }
	
	//Setters		
	public void setTabArt(TreeMap <Integer,Article41> tabArt){this.tabArt = tabArt;}
	
	public String toString() {
		String st = "\n\t\t CONTENU DE STOCK: \n";
		for (Article41 art: tabArt.values()) {
			st =st+ art.toString()+"\n";	
		}
		return st;
	}
	
	public Article41 retourner(int code) { //Recupere directement
		return tabArt.get(code);
	}
	
	public void ajouter (Article41 art) {
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
//	for (Entry<Integer, Article> any: this.tabArt.entrySet()) {
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
	
	public String promo()
	{
		String mes="";
		for( Article41 art : tabArt.values())
		{ if (art instanceof ArticlePromo41)
			mes = mes + art.toString() +"\n";
		}
		if (mes.equals("")) return " \n AUCUN ARTICLE PROMO **\n";
		else return "\n *** LISTE DES PROMOTIONS **\n" + mes;
		
	}
	
	
}

