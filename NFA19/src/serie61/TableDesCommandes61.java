package serie61;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;
import mesInterfaces.InterfaceStructure;


public class TableDesCommandes61 implements  InterfaceStructure <String, UneCommande61<String>>, Serializable{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Numero de commande + Contenu de commande
	public Hashtable <String, UneCommande61<String>> tabCde = new Hashtable <String, UneCommande61<String>>(); //On transforme le generique en String
	
	//Constructeur 1 Par default
	public TableDesCommandes61() {}
	
	//Constructeur 2 
	public TableDesCommandes61(Hashtable <String, UneCommande61<String>> tabCde) {this.tabCde =tabCde;}
	
	//Getters
	public Hashtable <String, UneCommande61<String>> getTabCde(){return tabCde;}
	
	//Setters
	public void setTabCde(Hashtable<String, UneCommande61<String>> tabCde)  {this.tabCde = tabCde;}
	
	
	//Retourne les codes toString()
	//get(Object key)
	//Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	public String toString() {
	String mes = "\n\t\t\t *** VOS COMMANDES***\n\t\t\t ("+cle().toString()+")\n";
	//+tabCde.get(cde.getCode()) +"Date de commande" +tabCde.get(cde.getCode());
	for (UneCommande61<String> cde22: tabCde.values()) {
		mes = mes +cde22.toString();
	}
	return mes;
	}
	
	//taille
	public int taille() {return tabCde.size() ;}

	//ajouter
	public void ajouter (UneCommande61<String> cde) {
		this.tabCde.put(cde.getCode(), cde);
	}
	
	//numero de commmande
	public UneCommande61<String> retourner (String code){
		return tabCde.get(code);
	}
	
	/*A VOIR SI IL EST UTILISE _ CAR DEUX METHODES supprimerArt et supprimer
	public void supprimerArt (String code) {
		tabCde.remove(code);
	}
	*/
	
	@Override
	public void supprimer(String code) {
		tabCde.remove(code);
	}
	
	@Override
	public void modifier(String code) {
		// TODO Auto-generated method stub
		
	}
	
	public void purge (int codeArt) {
		//INTERFACE ENUMERATION - IL FAUT PASSER PAR UN ITERATEUR SINON UNE FOIS ON SUPPRIME UNE COMMANDE LE tabCde N EST PAS AU COURANT ET IL ENVOIE UNE ERREUR
		Enumeration<UneCommande61<String>> cdeEnum =  tabCde.elements();	
		while(cdeEnum.hasMoreElements()){
			UneCommande61<String> cde = cdeEnum.nextElement();
			cde.supprimer(codeArt); //SUPPRIME LA LIGNE
			if(cde.taille()==0) {
				supprimer(cde.getCode()); //SUPPRIME LA COMMANDE
			}
		}
		
		/* 
		//si ON UTILISE FOR EACH DES QUE LA COMMANDE EST VIDE ==> LE PROGRAMME PLANTE
		for(UneCommande42<String> cde: tabCde.values()) {
			if (cde.taille()==0){ //sI LA COMMANDE EST VIDE, ON LA SUPPRIME DE LA TABLE DE COMMANDE
				supprimer(cde.getCode()); //A RENOMMER PARTOUT DANS TOUTE SLES CALLSES, ON VERIFIE SI A COMMANDE EST VIDE, EN CE CAS ON SUPPRIME
			}
		}
		*/
		
	}

	public Set<String> cle() { // Set parce que c'est une structure de données sans doublons, et les clés sont sans doublons
		return tabCde.keySet();
	}
	
	//Rcuperer le prix pour toutes les commandes
	public String facturerCommandes(TableArticle61 tabArt) {
		String sc = "\n\t\t LE TOTAL des COMMANDES \n";
		String sc2 = "";
		float prixTotalFacturesTTC=0;
		//Transformer une commande en collection
				Enumeration<UneCommande61<String>> cdeEnum =  this.tabCde.elements();	
				while(cdeEnum.hasMoreElements()){
					UneCommande61<String> cde = cdeEnum.nextElement();
					prixTotalFacturesTTC = prixTotalFacturesTTC+ cde.prixCommandeTTC(tabArt);
					sc2= sc2+ "\t COMMANDE NUMERO:" +cde.getCode()+ "\t PRIX TOTAL TTC: " + cde.prixCommandeTTC(tabArt) +"\n";
				}
		return sc+sc2+"\n\t\t LE TOTAL des COMMANDES TTC: "+prixTotalFacturesTTC++
				+"\n\t\t LE TOTAL des COMMANDES HT: " + (float)(prixTotalFacturesTTC/(1+0.196f))
				+"\n\t\t\t dont TVA: \n" + (prixTotalFacturesTTC - (float)(prixTotalFacturesTTC*(1+0.196f)));	 //PROBLEME DANS L'AFFICHAGE DE tva
	}











		
	

}
