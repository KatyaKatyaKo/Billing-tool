package serie21;

import java.util.Vector;

public class UneCommande { //On introduit un type generique

	//VARIABLES D'INSTANCE
	private Vector <LigneDeCommande> cde = new Vector <LigneDeCommande>();
	
	//CONSTRUCTEUR
	public UneCommande(Vector<LigneDeCommande> cde) {this.cde=cde;} // Constructeur 1
	public UneCommande() {} //Constructeur 2
	
	
	//Setteur et getteur - 1 seule
	public LigneDeCommande get(int x) { //Correct ou pas???
		return cde.get(x);
	}
	
	//toString()
	public String toString() {
		String mes = "\n\t\t *** CONTENU DE VOTRE COMMANDE***\n";
		for (int i=0; i<taille(); i++) {
			mes = mes + cde.get(i).toString();
		}
		return mes;
	}
	
	
	//4 methodes
	public int taille() {return cde.size();}
	
	public LigneDeCommande retourner(int code) { //On cherche par code dans toute la commande et ça retourne l'article //Clé étrangère		
		for (int i=0; i<taille(); i++) {
			if(this.cde.get(i).getCode()==code)  return this.cde.get(i);	
			}
		return null;
		}
		
	public void ajouter(LigneDeCommande ldc) {this.cde.addElement(ldc);}
	
	
	public LigneDeCommande ajouterQuestion(LigneDeCommande ldc) { //POSER UNE QUESTION SUR addElement vs add
		for (int i=0; i<taille(); i++) {
			if(ldc.getCode()!=cde.get(i).getCode()) {
				cde.addElement(ldc);
				//addElement() method is identical in functionality to the add(Object) method. The add() method returns true/false but the addElement() method does not return any value.
			}
			else {
				//utils.DateUseraffiche();  POURQUOI LIMPORT NE MARCH EPAS????
				System.out.println("Vous avez déjà ajouté cette article");
			}
		}
		return ldc;
	}
	

	public void supprimer(LigneDeCommande ldc) { 
		for (int i=0; i<taille(); i++) {
			if(ldc.getCode()==cde.get(i).getCode()) {
				cde.removeElement(ldc);
			}
		}
	}
	
	
	public String facturer(TableArticles21 tabArt) { //Affiche l'ensemble des "factures de lignes de commande"
		
		String entete, details, pied;
		float prixTotal =0;
		
		entete ="\n\t ****************************************************"+
				"\n\n\t\t\t FACTURE n°...\n"+
				"\n\t ****************************************************"+
				"\n\t Code   Designation   Quantite   PU(HT)   Total(HT)\n";
		
		details="";
		for (int i=0; i<taille(); i++) {
			details=details + cde.get(i).facture(tabArt)+"\n";           // Affiche la facture pour chaque ligne de commande
			prixTotal=prixTotal+cde.get(i).prixTotal(tabArt);
		}
		
		//PrixTTC:
		int prixTotalTTCInt=(int)(prixTotal*(1+0.196f))*100;
		double prixTotalTTCConverted = prixTotalTTCInt/100;
		
		//On perd les chiffres apres virgule
		
		pied = "\n\t -----------------------------------------------------"+
				"\n\t PRIX TOTAL HT\t\t\t" + prixTotal +//ca sera la boucle for pour cumuler le prix de chaque ligne de command epour air ele totl
			    "\n\t PRIX TOTAL TTC\t\t\t" + prixTotalTTCConverted+ //REGARDER LA FORMULE ATTENTIVEMENT, pê  à remplacer juste par prixTotal*(1+19.6f)/100.0f
			    "\n\t dont TVA\t\t\t"+(prixTotalTTCConverted-prixTotal)+
			    "\n\t ----------------------------------------------------\n\n\n";
		return entete+details+pied;
	}
	
}
	
	
	
