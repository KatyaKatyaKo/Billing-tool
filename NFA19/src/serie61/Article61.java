package serie61;

public class Article61  extends ArticleAbstract61{
	
	private static final long serialVersionUID = 1L;

	public Article61() {super();}
	public Article61(int code, String designation, float pu) {
		super(code, designation, pu);
	}
	
	//ToString()
	public String toString() {
		return "\t **" + super.getCode()+"   " + super.getDesignation()+ "         "+ super.getPu();
	}

	public int compareTo(Article61 o) {
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