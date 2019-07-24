package serie61;

public class ArticlePromo61 extends ArticleAbstract61 {

	private static final long serialVersionUID = 1L;
	private int quantiteMini;
	private float reduc;

	public ArticlePromo61(int code, String designation, float pu, int quantiteMini, float reduc) {
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
	public float prixFacture(int quantite)
	{
		if (quantite < quantiteMini) return prixFacture(quantite);
		else
		return prixFacture(quantite)* (1 - reduc/100f);
	}

	@Override
	public String infoArticle() {
		return " ** Article en Promo! Mini: " + quantiteMini + " **Reduc: "
				+ reduc + "%";
	}
}
