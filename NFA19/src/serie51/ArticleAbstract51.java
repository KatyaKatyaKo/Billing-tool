package serie51;

import java.io.Serializable;

public abstract class ArticleAbstract51 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int code;
	protected String designation;
	protected float pu;
	
	
	public ArticleAbstract51(int code, String designation,float pu ) {
		this.code= code;
		this.designation=designation;
		this.pu = pu;
	}

	
	public ArticleAbstract51() {}

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
		
		//NE MARCHE PAS
		protected abstract void setQuantiteMini(int quantiteMini);
		public abstract void setReduc(float reduc);
		protected abstract int getQuantiteMini();
		protected abstract float getReduc();

		//QUE LA SIGNATURE, PAS DE CORPS
		public abstract String toString();
		public abstract float prixFacture(int quantite);
		public abstract String infoArticle();






	


		


	

}
