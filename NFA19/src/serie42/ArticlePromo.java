package serie42;

public class ArticlePromo extends ArticleAbstract {
	private int quantiteMini;
	private float reduc;

	public ArticlePromo(int code, String designation, float pu, int quantiteMini, float reduc) {
		super(code, designation, pu);
		this.quantiteMini = quantiteMini;
		this.reduc = reduc;
	}

	public int getQuantiteMini() {return quantiteMini;}
	public float getReduc() {return reduc;}
	
	
	@Override
	public String toString() {
		return "\t **" + super.getCode()+"   " + super.getDesignation()+ "         "+ super.getPu()+ "Article promo"+ quantiteMini+ ""+reduc;
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
