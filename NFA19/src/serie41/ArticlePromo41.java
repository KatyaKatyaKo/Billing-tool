package serie41;

public class ArticlePromo41 extends Article41{
	private int quantiteMinimum;
	private float reduction;
	
	//Constructeur sans paramètre - avec super()
	public ArticlePromo41() {super();}
	
	//Constructeur avec paramètre
	public ArticlePromo41(int code, String designation,float pu, int quantiteMinimum, float reduction) {
		super(code,designation,pu);
		this.quantiteMinimum=quantiteMinimum;
		this.reduction=reduction;
	}
	
	//Setters
	public void setQuantiteMinimum(int quantiteMinimum) {
		this.quantiteMinimum = quantiteMinimum;
	}
	
	public void setReduction(float reduction) {
		this.reduction = reduction;
	}
	
	//Getteurs
	public int getQuantiteMinimum() {return quantiteMinimum;}
	public float getReduction() {return reduction;}
	
	//ToString()
	public String toString() { //Ce qui s'affiche qaund on appelle une méthode TabArt.get(i)
		return super.toString()+ " Qte Mini: " + this.quantiteMinimum+ " Reduc: "+ 
				this.reduction + "%";
	}

	//Problème avec override - à resoudre
	//Redifinition de la méthode comparreTO pour implémenter Comparable
	public int compareTo(ArticlePromo41 o) {
		//Version1
			//return this.getDesignation().compareTo(o.getDesignation()); //Va marcher que pour String car c'est une classe avec ces méthodes, mais PAS pour int, double etc car ce sont des types primitive
		//Version 2
		if(this.getCode()>o.getCode()) return 1;
		else if (this.getCode()<o.getCode()) return -1;
		else return 0;
	}
	
	public float prixFacture(int quantite)
	{
		if (quantite < quantiteMinimum) return super.prixFacture(quantite);
		else
			return super.prixFacture(quantite)* (1 - reduction/100f);
	}


	protected String infoArticle() {
		return " ** Article en Promo! Mini: " + quantiteMinimum + " **Reduc: "
				+ reduction + "%";
	}
	


	
}
