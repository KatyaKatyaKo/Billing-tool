package serie51;

public class ArticlePromo51 extends ArticleAbstract51 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int quantiteMini;
	private float reduc;

	public ArticlePromo51(int code, String designation, float pu, int quantiteMini, float reduc) {
		super(code, designation, pu);
		this.quantiteMini = quantiteMini;
		this.reduc = reduc;
	}

	public void setQuantiteMini(int quantiteMini) {this.quantiteMini= quantiteMini;}
	public int getQuantiteMini() {return quantiteMini;}
	public float getReduc() {return reduc;}
	public void setReduc(float reduc) {this.reduc=reduc;}
	
	@Override
	public String toString() {
		return "\t **" + super.getCode()+"   " + super.getDesignation()+ "         "+ super.getPu()+ " Article promo "+ " Quantité minimale:" +quantiteMini+ " Réduction:"+reduc;
	}

	@Override
	public float prixFacture(int quantite) //A REECRIRE CAR L METHODE NE MARCHE PAS
	{
		//if (quantite < quantiteMini) return super.prixFacture(quantite);
		//else
		//	return super.prixFacture(quantite)* (1 - reduc/100f);
		return 0;
	}

	@Override
	public String infoArticle() {
		return " ** Article en Promo! Mini: " + quantiteMini + " **Reduc: "
				+ reduc + "%";
	}



}
