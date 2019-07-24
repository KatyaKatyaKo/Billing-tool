package serie42;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import mesInterfaces.InterfaceStructure;


public class TableDesCommandes42 implements  InterfaceStructure <Integer, UneCommande42<Integer>>{ 
					//Numero de commande + Contenu de commande
	public Hashtable <Integer, UneCommande42<Integer>> tabCde = new Hashtable <Integer, UneCommande42<Integer>>(); //On transforme le generique en Integer
	
	//Constructeur 1 Par default
	public TableDesCommandes42() {}
	
	//Constructeur 2 
	public TableDesCommandes42(Hashtable <Integer, UneCommande42<Integer>> tabCde) {this.tabCde =tabCde;}
	
	//Getters
	public Hashtable <Integer, UneCommande42<Integer>> getTabCde(){return tabCde;}
	
	//Setters
	public void setTabCde(Hashtable<Integer, UneCommande42<Integer>> tabCde)  {this.tabCde = tabCde;}
	
	
	//Retourne les codes toString()
	//get(Object key)
	//Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	public String toString() {
	String mes = "\n\t\t *** CONTENU DE VOTRE COMMANDE***"+"Numero de commande" +cle().toString();
	//+tabCde.get(cde.getCode()) +"Date de commande" +tabCde.get(cde.getCode());
	for (UneCommande42<Integer> cde22: tabCde.values()) {
		mes = mes +cde22.toString();
	}
	return mes;
	}
	
	//taille
	public int taille() {return tabCde.size() ;}

	//ajouter
	public void ajouter (UneCommande42<Integer> cde) {
		this.tabCde.put(cde.getCode(), cde);
	}
	
	//numero de commmande
	public UneCommande42<Integer> retourner (int code){
		return tabCde.get(code);
	}
	
	public void supprimerArt (int code) {
		tabCde.remove(code);
	}
	
	public void purge (int codeArt) {
		//INTERFACE ENUMERATION - IL FAUT PASSER PAR UN ITERATEUR SINON UNE FOIS ON SUPPRIME UNE COMMANDE LE tabCde N EST PAS AU COURANT ET IL ENVOIE UNE ERREUR
		Enumeration<UneCommande42<Integer>> cdeEnum =  tabCde.elements();	
		while(cdeEnum.hasMoreElements()){
			UneCommande42<Integer> cde = cdeEnum.nextElement();
			cde.supprimer(codeArt); //SUPPRIME LA LIGNE
			if(cde.taille()==0) {
				supprimer(cde.getCode()); //SUPPRIME LA COMMANDE
			}
		}
		
		/* 
		//si ON UTILISE FOR EACH DES QUE LA COMMANDE EST VIDE ==> LE PROGRAMME PLANTE
		for(UneCommande42<Integer> cde: tabCde.values()) {
			if (cde.taille()==0){ //sI LA COMMANDE EST VIDE, ON LA SUPPRIME DE LA TABLE DE COMMANDE
				supprimer(cde.getCode()); //A RENOMMER PARTOUT DANS TOUTE SLES CALLSES, ON VERIFIE SI A COMMANDE EST VIDE, EN CE CAS ON SUPPRIME
			}
		}
		*/
		
	}

	public Set<Integer> cle() { // Set parce que c'est une structure de données sans doublons, et les clés sont sans doublons
		return tabCde.keySet();
	}
	
	//Rcuperer le prix pour toutes les commandes
	public String facturerCommandes(TableArticle42 tabArt) {
		String sc = "\n\t\t LE TOTAL des COMMANDES \n";
		String sc2 = "";
		float prixTotalFacturesTTC=0;
		//Transformer une commande en collection
				Enumeration<UneCommande42<Integer>> cdeEnum =  this.tabCde.elements();	
				while(cdeEnum.hasMoreElements()){
					UneCommande42<Integer> cde = cdeEnum.nextElement();
					prixTotalFacturesTTC = prixTotalFacturesTTC+ cde.prixCommandeTTC(tabArt);
					sc2= sc2+ "\t COMMANDE NUMERO:" +cde.getCode()+ "\t PRIX TOTAL TTC: " + cde.prixCommandeTTC(tabArt) +"\n";
				}
		return sc+sc2+"\n\t\t LE TOTAL des COMMANDES TTC: "+prixTotalFacturesTTC++
				+"\n\t\t LE TOTAL des COMMANDES HT: " + (float)(prixTotalFacturesTTC/(1+0.196f))
				+"\n\t\t\t dont TVA: \n" + (prixTotalFacturesTTC - (float)(prixTotalFacturesTTC*(1+0.196f)));	 //PROBLEME DANS L'AFFICHAGE DE tva
	}

	//Méthodes de l'interfaceStructure
	@Override
	public void supprimer(Integer code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifier(Integer code) {
		// TODO Auto-generated method stub
		
	}
		
	

}
