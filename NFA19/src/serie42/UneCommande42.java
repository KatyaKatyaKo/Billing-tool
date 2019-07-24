package serie42;

import java.util.Vector;

import mesInterfaces.InterfaceStructure;
import utils.DateUser;

public class UneCommande42<TypeDuCode> implements InterfaceStructure <TypeDuCode, LigneDeCommande42> { //VERIFIER SI C EST CORRECT
	//VARIABLES D'INSTANCE
	protected Vector <LigneDeCommande42> cde = new Vector <LigneDeCommande42>();
	protected TypeDuCode numCde;
	protected DateUser dateCde = new DateUser();
	
	//CONSTRUCTEUR
	public UneCommande42(TypeDuCode numCode,Vector<LigneDeCommande42> cde) {
		this.numCde = numCode;
		this.cde=cde;
		} // Constructeur 1
	
	public UneCommande42() {} //Constructeur 2
	
	//GETTER ET SETTER - 1 SEUL
	
	//Getter - Attention - getter = public Variable d'instance et pas public NameClass
	public Vector <LigneDeCommande42> getUneCommande(){return cde;}
	
	//Setteur - Attention - setter = public Variable d'instance et pas public NameClass
	public void getUneCommande (Vector <LigneDeCommande42> cde)  {this.cde=cde;}
	
	public void setNumCde(TypeDuCode numCde) {this.numCde = numCde;}
	
	public TypeDuCode getCode() {return this.numCde;} //Generique //A RENOMMER PARTOUT DANS TOUTE SLES CALLSES
	
	//toString()
	public String toString() {
		String mes = "\n\t\t *** CONTENU DE VOTRE COMMANDE***\n"+"\tNumero de commande: "+ this.numCde +"   Date de commande: " +dateCde + "\n";
		for (int i=0; i<taille(); i++) {
			mes = mes + cde.get(i).toString();
		}
		return mes;
	}
	
	public void supprimer(int codeArt) { //COMMENT ON SUPPRIMER DANS UN VECTOR???? PAR QUEL MOYEN
		for (int i=0; i<taille(); i++) {
			if(cde.get(i).getCode()==codeArt){
				cde.remove(i); break;
			}
		}
	}
	
	
	//4 methodes
	public int taille() {return cde.size();}
	
	public LigneDeCommande42 retourner(int code) { //On cherche par code dans toute la commande et ça retourne l'article //Clé étrangère		
		for (int i=0; i<taille(); i++) {
			if(this.cde.get(i).getCode()==code)  return this.cde.get(i);	
			}
		return null;
		}
		
	public void ajouter(LigneDeCommande42 ldc) {this.cde.addElement(ldc);}
	
	public void supprimer(LigneDeCommande42 ldc) { 
		for (int i=0; i<taille(); i++) {
			if(ldc.getCode()==cde.get(i).getCode()) {
				cde.removeElement(ldc);
			}
		}
	}
	
	//A RAJOUTER LES NOUVELLES VARIABLES DATE FACTURE ET NUMERO
	public String facturer(TableArticle42 tabArt) { //Affiche l'ensemble des "factures de lignes de commande"
		
		String entete, details, pied;
		float prixTotal =0;
		
		entete ="\n\t ****************************************************"+
				"\n\n\t\t\t FACTURE n°..."+getCode() +"\n"+
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
	
	//NOUVELLE FONCTION 
	public float prixCommandeTTC(TableArticle42 tabArt) {
		float prix = 0;
		for (int i=0; i<taille(); i++) {
			this.cde.get(i).facture(tabArt);           // Affiche la facture pour chaque ligne de commande
			prix=prix+this.cde.get(i).prixTotal(tabArt);	
		}
		//A REVOIR LA CONVERSION TROP COMPLIQUE
		int prixTotalTTCInt=(int)(prix*(1+0.196f))*100;
		double prixTotalTTCConverted = prixTotalTTCInt/100;
		return (float) prixTotalTTCConverted;
	}

	//METHODES INTERFACE Interface Strcuture
	@Override
	public void supprimer(TypeDuCode code) { //ON AURA DEUX METHODES SUPPRIMER CAR LA PREMIERE NE PEUT PAS PRENDRE TYPEDUCPDE CST IN INTEGER
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifier(TypeDuCode code) {
		// TODO Auto-generated method stub
		
	}
	
}
	
	
	
