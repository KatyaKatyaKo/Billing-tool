package serie42;
import java.util.TreeMap;

import mesInterfaces.InterfaceStructure;


														//ICI ON RAJOUTE 
public class TableArticle42 implements InterfaceStructure <Integer, ArticleAbstract> {
	//Variable d'instance
	private TreeMap <Integer, ArticleAbstract> tabArt = new TreeMap <Integer, ArticleAbstract>(); 
	
	//Constructeur
	public TableArticle42(){
		Article42 art1 = new Article42(1, "DISK DUR 1TO", 50.5f);
		Article42 art2 = new Article42(2, "CARTE MERE", 1000f);
		Article42 art3 = new Article42(3, "CARTE RESEAU", 24.7f);
		Article42 art4 = new Article42(4, "BOITE 100CD", 75.5f);
		Article42 art5 = new Article42(5, "MEMOIRE FLASH", 17f);
		
		//Article en promotion
		ArticlePromo art1Promo= new ArticlePromo(300," CLE USB 1TO", 20.5f,10,50f);
		
		tabArt.put(art1.getCode(), art1);
		tabArt.put(art2.getCode(), art2);
		tabArt.put(art3.getCode(), art3);
		tabArt.put(art4.getCode(), art4);
		tabArt.put(art5.getCode(), art5);
		tabArt.put(art1Promo.getCode(), art1Promo);
	}
	
	//Getters
	public TreeMap<Integer, ArticleAbstract> getTabArt(){return tabArt; }
	
	//Setters		
	public void setTabArt(TreeMap<Integer, ArticleAbstract> tabArt){this.tabArt = tabArt;}
	
	public String toString() {
		String st = "\n\t\t CONTENU DE STOCK: \n";
		for (ArticleAbstract art: tabArt.values()) {
			st =st+ art.toString()+"\n";	
		}
		return st;
	}
	
	public ArticleAbstract retourner(int code) { //Recupere directement
		return tabArt.get(code);
	}
	
	public void ajouter (ArticleAbstract art) {
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
//	for (Entry<Integer, Article42> any: this.tabArt.entrySet()) {
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
		for( ArticleAbstract art : tabArt.values())
		{ if (art instanceof ArticlePromo)
			mes = mes + art.toString() +"\n";
		}
		if (mes.equals("")) return " \n AUCUN ARTICLE PROMO **\n";
		else return "\n *** LISTE DES PROMOTIONS **\n" + mes;
		
	}

	//Mérhode de l'interface


	@Override
	public void supprimer(Integer code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifier(Integer code) {
		// TODO Auto-generated method stub
		
	}


	
	
	
}

