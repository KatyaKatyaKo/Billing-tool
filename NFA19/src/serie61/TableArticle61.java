package serie61;
import java.io.Serializable;
import java.util.TreeMap;

import mesInterfaces.InterfaceStructure;


														//ICI ON RAJOUTE 
public class TableArticle61 implements InterfaceStructure <Integer, ArticleAbstract61>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Variable d'instance
	private TreeMap <Integer, ArticleAbstract61> tabArt = new TreeMap <Integer, ArticleAbstract61>(); 
	
	//Constructeur
	public TableArticle61(){
		Article61 art1 = new Article61(1, "DISK DUR 1TO", 50.5f);
		Article61 art2 = new Article61(2, "CARTE MERE", 1000f);
		Article61 art3 = new Article61(3, "CARTE RESEAU", 24.7f);
		Article61 art4 = new Article61(4, "BOITE 100CD", 75.5f);
		Article61 art5 = new Article61(5, "MEMOIRE FLASH", 17f);
		
		//Article en promotion
		ArticlePromo61 art1Promo= new ArticlePromo61(300," CLE USB 1TO", 20.5f,10,50f);
		
		tabArt.put(art1.getCode(), art1);
		tabArt.put(art2.getCode(), art2);
		tabArt.put(art3.getCode(), art3);
		tabArt.put(art4.getCode(), art4);
		tabArt.put(art5.getCode(), art5);
		tabArt.put(art1Promo.getCode(), art1Promo);
	}
	
	//Getters
	public TreeMap<Integer, ArticleAbstract61> getTabArt(){return tabArt; }
	
	//Setters		
	public void setTabArt(TreeMap<Integer, ArticleAbstract61> tabArt){this.tabArt = tabArt;}
	
	public String toString() {
		String st = "\n\t\t CONTENU DE STOCK: \n";
		for (ArticleAbstract61 art: tabArt.values()) {
			st =st+ art.toString()+"\n";	
		}
		return st;
	}
	
	public ArticleAbstract61 retourner(int code) { //Recupere directement
		return tabArt.get(code);
	}
	
	public void ajouter (ArticleAbstract61 art) {
		tabArt.put(art.getCode(), art);
	}
	
	//SUPPRIMER A REVOIR int VS iNTEGER
	public void supprimer(int code) {
		tabArt.remove(code);
	}
	
	@Override
	public void supprimer(Integer code) {
		// TODO Auto-generated method stub	
	}
	
	//Il manque modifier
	@Override
	public void modifier(Integer code) {
		// TODO Auto-generated method stub	
	}
	
	public int taille() {
		return tabArt.size();
	}

	public String cle () {
		String st = "\n\t (La iste de codes des articles: ";
		for(int code: tabArt.keySet()) {
			st = st+ code + " ";
		}
		return st+"): " ;
	}
	
	
	//Last element in a tree map
//	public int lastkey() {
//		int lastKey = 0;
//	for (Entry<Integer, Article61> any: this.tabArt.entrySet()) {
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
		for( ArticleAbstract61 art : tabArt.values())
		{ if (art instanceof ArticlePromo61)
			mes = mes + art.toString() +"\n";
		}
		if (mes.equals("")) return " \n AUCUN ARTICLE PROMO **\n";
		else return "\n\t\t *** LISTE DES PROMOTIONS **\n" + mes;	
	}
}

