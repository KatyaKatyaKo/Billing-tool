package serie61;

public class test {

	public static void main(String[] args) {
		float prixtotal = 151.5f;
		float prixTTC=181.8f;
		float TVA = (float)prixTTC-(float)prixtotal;
		System.out.println(((double)TVA*10)/10);
		
		
		double toto=321.6584;
		toto=(double)((int)(toto*10))/10;
		System.out.println(toto);
	}

}
