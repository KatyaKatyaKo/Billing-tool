package serie42;

public class Article42  extends ArticleAbstract{
	
	public Article42() {super();}
	public Article42(int code, String designation, float pu) {
		super(code, designation, pu);
	}
	
	//ToString()
	public String toString() { //Ce qui s'affiche qaund on appelle une méthode TabArt.get(i)
		return "\t **" + super.getCode()+"   " + super.getDesignation()+ "         "+ super.getPu();
	}

	
	public int compareTo(Article42 o) {
		//Version1
			//return this.getDesignation().compareTo(o.getDesignation()); //Va marcher que pour String car c'est une classe avec ces méthodes, mais PAS pour int, double etc car ce sont des types primitive
		//Version 2
		if(this.getCode()>o.getCode()) return 1;
		else if (this.getCode()<o.getCode()) return -1;
		else return 0;
	}
	
	public float prixFacture(int quantite)
	{
		return pu*quantite;
	}
	
	public String infoArticle() {
		return "\tArticle normal" ;
	}


	
}
