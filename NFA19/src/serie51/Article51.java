package serie51;

public class Article51  extends ArticleAbstract51{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Article51() {super();}
	public Article51(int code, String designation, float pu) {
		super(code, designation, pu);
	}
	
	//ToString()
	public String toString() {
		return "\t **" + super.getCode()+"   " + super.getDesignation()+ "         "+ super.getPu();
	}

	public int compareTo(Article51 o) {
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
	@Override
	protected void setQuantiteMini(int quantiteMini) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setReduc(float reduc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected int getQuantiteMini() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected float getReduc() {
		// TODO Auto-generated method stub
		return 0;
	}

}
