package serie42;

public class LigneDeCommande42 { //Clé etrangère
	int code;
	int quantite;

	// CONSTRUCTEURS
		// Constructeur 1
	public LigneDeCommande42(int code, int quantite) {
		this.code = code;
		this.quantite = quantite;
	}

		// Constructer 2
	public LigneDeCommande42() {
	}

	// GETTERS
	public int getCode() {
		return code;
	}

	public int getQuantity() {
		return quantite;
	}

	// SETTERS

	public void setCode(int code) {
		this.code = code;
	}
	
	public void setQuantite(int quantite) {
		this.quantite= quantite;
	}
	
	public String toString() {
		return "\t\t CODE: "+code + "         QUANTITE:  " +quantite +"\n";
	}

	public String facture(TableArticle42 tabArt) { //Affiche une ligne de commande avec tous ces attributs (nom, quantité, prix etc)
		ArticleAbstract art = tabArt.retourner(code);
		String facture="";
		facture= "\n\t " +code+"\t"+art.getDesignation()+"\t" + quantite +"\t "+/*PrixUnit HT */art.getPu() +"\t "+ /*Prix Total Ligne HT */prixTotal(tabArt); 
		return facture;
	//	 		+ "pu*quantite+(pu*quantite)*(1+19.6f)
	}
	
	public float prixTotal(TableArticle42 tabArt) { //Attention prix total d'une ligne de commande, mais pas de facture, vu qu'on est dans la classe LigneDeCommande
		ArticleAbstract art= tabArt.retourner(code); //clé étrangère
		return quantite*art.getPu();
	}

}
