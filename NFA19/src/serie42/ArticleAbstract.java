package serie42;

public abstract class ArticleAbstract {
	
	protected int code;
	protected String designation;
	protected float pu;
	
	
	public ArticleAbstract(int code, String designation,float pu ) {
		this.code= code;
		this.designation=designation;
		this.pu = pu;
	}

	
	public ArticleAbstract() {}


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

		//QUE LA SIGNATURE, PAS DE CORPS
		public abstract String toString();
		public abstract float prixFacture(int quantite);
		public abstract String infoArticle();

}
