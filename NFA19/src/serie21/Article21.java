package serie21;

public class Article21 {
	private int code;
	private String designation;
	private float pu;
	
	public Article21(int code, String designation,float pu) {
		this.code= code;
		this.designation=designation;
		this.pu = pu;
	}

	public Article21() {};
	
	//Getteurs
	public void setCode(int code) {
		this.code=code; 
	}
	
	public void setDesignation(String designation) {
		this.designation=designation;  
	}
	
	public void setPu(float prix) {
		this.pu = prix; 
	}
	
	//Setters
	public int getCode() {return this.code;}
	public String getDesignation(){return this.designation;}
	public float getPu() {return this.pu;}
	
	//ToString()
	public String toString() { //Ce qui s'affiche qaund on appelle une méthode TabArt.get(i)
		return "\t **" + code+"   " + designation+ "         "+ pu;
	}

	//Problème avec override - à resoudre
	//Redifinition de la méthode comparreTO pour implémenter Comparable
	public int compareTo(Article21 o) {
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
	
	protected String infoArticle() {
		return "\tArticle normal" ;
	}
}
