package serie41;

public class Article41 {
	
	private int code;
	private String designation;
	private float pu;
	
	
	public Article41(int code,String designation,float pu)
	{
		this.code=code;
		this.designation=designation;
		this.pu=pu;
	}
	
	public Article41() {}
	
	public int getCode() { return this.code;}
	public String getDesignation() { return this.designation;}
	public float getPu() {return pu;}
	
	public void setCode(int code) { this.code=code;}
	public void setDesignation(String designation) { this.designation= designation;}
	public void setPu(float pu) { this.pu=pu;}
	
	public String toString()
	{
		return " ** code: " + code + "  ** Designation: " + designation 
				+ " Prix Unitaire " + pu ;
	}	
	
	public float prixFacture(int quantite)
	{
		return pu*quantite;
	}
	
	public String infoArt()
	{
		return " ** Article  Normal * ";
	}

}
