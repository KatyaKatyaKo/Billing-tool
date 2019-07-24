package serie61;

import java.io.Serializable;

public abstract class ArticleAbstract61 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected int code;
	protected String designation;
	protected float pu;
	
	public ArticleAbstract61(int code, String designation,float pu ) {
		this.code= code;
		this.designation=designation;
		this.pu = pu;
	}

	public ArticleAbstract61() {}

		//Getteurs
		public void setCode(int code) {this.code=code; }
		public void setDesignation(String designation) {this.designation=designation; }
		public void setPu(float prix) {this.pu = prix; }
		
		//Setters
		public int getCode() {return this.code;}
		public String getDesignation(){return this.designation;}
		public float getPu() {return this.pu;}
		
		//Méthodes abtraits, que l'entete, pas de corps
		public abstract String toString();
		public abstract float prixFacture(int quantite);
		public abstract String infoArticle();
}
