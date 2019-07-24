package serie51;

import java.io.Serializable;
import java.util.Vector;

import mesInterfaces.InterfaceStructure;
import utils.DateUser;

public class UneCommande51<TypeDuCode> implements InterfaceStructure <TypeDuCode, LigneDeCommande51>, Serializable { //VERIFIER SI C EST CORRECT
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//VARIABLES D'INSTANCE
	protected Vector <LigneDeCommande51> cde = new Vector <LigneDeCommande51>();
	protected TypeDuCode numCde;
	protected DateUser dateCde = new DateUser();
	
	//NOUVELLES VARIABLES - Date facturE, ETAT DACTURE
	private DateUser dateFacturation;
	private boolean etatFacture = false; //ETAT FACTURE SERA TOUJOURS FALSE CAR DANS 51 ON FACTURE PAS, CA SERA DANS 52 ON VA SUPPRIMER SEULEMENT SI L AFACTUR A ETE FACTURE DEPUIS UNE SEMAINE 
	//NOUVELLE SVARIABLE SPUR 52
	private String numClient;
	private float valeurTotaleComClient;
	
	//CONSTRUCTEUR
	public UneCommande51(TypeDuCode numCode,Vector<LigneDeCommande51> cde) {
		this.numCde = numCode;
		this.cde=cde;
		} // Constructeur 1
	
	public UneCommande51() {} //Constructeur 2
	
	//GETTER ET SETTER - 1 SEUL
	
	//Getter - Attention - getter = public Variable d'instance et pas public NameClass
	public Vector <LigneDeCommande51> getUneCommande(){return cde;}
	
	//Setteur - Attention - setter = public Variable d'instance et pas public NameClass
	public void getUneCommande (Vector <LigneDeCommande51> cde)  {this.cde=cde;}
	
	public void setCode(TypeDuCode numCde) {this.numCde = numCde;}
	
	public TypeDuCode getCode() {return this.numCde;} //Generique //A RENOMMER PARTOUT DANS TOUTE SLES CALLSES
	
	//toString()
	public String toString() {
																							
		String mes = "\n\t\t *** CONTENU DE VOTRE COMMANDE***\n"+"\tClient numero:"+numClient+"             Facturation:"+etatFacture+"\n\tNumero de commande: "+ (String)numCde +"   Date de commande: " +dateCde + "\n";
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
	
	public LigneDeCommande51 retourner(int code) { //On cherche par code dans toute la commande et ça retourne l'article //Clé étrangère		
		for (int i=0; i<taille(); i++) {
			if(this.cde.get(i).getCode()==code)  return this.cde.get(i);	
			}
		return null;
		}
		
	public void ajouter(LigneDeCommande51 ldc) {this.cde.addElement(ldc);}
	
	public void supprimer(LigneDeCommande51 ldc) { 
		for (int i=0; i<taille(); i++) {
			if(ldc.getCode()==cde.get(i).getCode()) {
				cde.removeElement(ldc);
			}
		}
	}
	
	//A RAJOUTER LES NOUVELLES VARIABLES DATE FACTURE ET NUMERO
	public String facturer(TableArticle51 tabArt) { //Affiche l'ensemble des "factures de lignes de commande"
		
		String entete, details, pied;
		float prixTotal =0f;
		
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
		float prixTotalTTC = prixTotal*(1+20.0F/100);
		
		//TVA:
		float TVA =prixTotal*20.0F/100;
		
		
		pied = "\n\t -----------------------------------------------------"+
				"\n\t PRIX TOTAL HT\t\t\t" + prixTotal +//ca sera la boucle for pour cumuler le prix de chaque ligne de command epour air ele totl
			    "\n\t PRIX TOTAL TTC\t\t\t" + prixTotalTTC+ //REGARDER LA FORMULE ATTENTIVEMENT, pê  à remplacer juste par prixTotal*(1+19.6f)/100.0f
		
			    "\n\t dont TVA\t\t\t"+TVA+
			    "\n\t ----------------------------------------------------\n\n\n";
		return entete+details+pied;
	}
	
	//NOUVELLE FONCTION 
	public float prixCommandeTTC(TableArticle51 tabArt) {
		float prix = 0;
		for (int i=0; i<taille(); i++) {
			this.cde.get(i).facture(tabArt);           // Affiche la facture pour chaque ligne de commande
			prix=prix+this.cde.get(i).prixTotal(tabArt);	
		}
		//A REVOIR LA CONVERSION TROP COMPLIQUE
		int prixTotalTTCInt=(int)(prix*(1+0.200f))*100;
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
	
	
	
