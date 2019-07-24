package iConsole;
import java.util.Scanner;

import mesExceptions.AbandonException;
//Remplace Terminal
//CA POURRAIT ETRE STATIQUE (CAR NE DEMANDE PAS L'OBJET, JUSTE N'UTILISE PAS L'OBJET) 
							//MAIS !!
//CA CONVIENT PAS POUR CREER ENSUITE UNE INTERFACE, CAR INTERFACE N'UTILISE PAS STATIQUE
import mesInterfaces.interfaceIO;

public class ES implements interfaceIO {
	private Scanner sc = new Scanner(System.in);
	
	public Object saisie(String message, Object ...param) throws AbandonException{
		boolean entier = false;
		int mini = -1, maxi = -1;
		float minif = -1, maxif=-1;
		//On vérifie le premier paramètre Integer = true
		/*
		 1. MIN (c 'est le 1er paramètre) si entier = true , mini, else - minif
		 2. MAX
		 	a). si 2 param en tout: max = param[1]
		 	b). else (ça veut dire il n'y qu'un seul param) : max = Integer.Max_VALUE
		 3.	
		 */
		
		if (param[0]instanceof Integer) entier = true; 
		
		// On calcule le mini
		if(entier) mini = (Integer) param[0]; //Integer mini
		else minif = (Float) param[0]; //Float mini
		
		//on cherche le maxi en voyant combien ti l ya de param
		if(param.length==2) {
				//Si un integer
			//JUSTE POUR LE TEST
				if(entier) maxi = (Integer) param[1] ; 
				else maxif = (Float) param[1];
		}
		//Si autre longuer que deux
		else { 
			if (entier) maxi = Integer.MAX_VALUE ;  maxif = Integer.MAX_VALUE; //ERREUR ICI
		}
		String data = null; // Si on appuie sur la croix ou annuler (n'importe quel bouton sauf "OK"
		Object valeur; //ON DECLARE VALEUR EN TANT QU OBJECT

		do {
		try {
			affiche(message);
			data=sc.nextLine(); //On saisit un int
		 if(data==null) throw new AbandonException(); //EXCEPTION ICI
		 
		 if(entier) valeur = Integer.parseInt(data); else valeur = Float.parseFloat(data);
		 //apres la saisie on doit le convertir en entier
		 
		 //verifier la conditon d'intervalle
		 if(entier) {
			 
			 if((Integer)valeur>=mini&&(Integer)valeur<=maxi)break;
			 affiche("Saisie hors intervalle de "+ mini+ "à " + maxi + "resaisissez svp") ;
		 }
		 else {
			 if ((Float)valeur>=minif&&(Float)valeur<=maxif) break;
		 }
		}
		 catch (NumberFormatException nfe){
			 if(data.equals("")) affiche("Il faut saisir qqch");
			 affiche("Saisie non numérique. resaisissez)");}
		catch(AbandonException ae) { 
			if (saisieOuiNon("Voulez vous abandonner?"))
			throw new  AbandonException(); // c 'est cette exception qui permet d'abandonner l'application. Elle est géré dans le main
		}
		}
		while(true);
		 return valeur;
	//La fenetre affiche OK annler et croix
	//Pour ok on fait tous lestests nécessaires
	}
	
	
// STRING - 1 MÉTHODE		
	public String saisie (String message) {
		affiche(message);
		//sc.nextLine(); //vide le chariot //FAUT VOIR SI CA VA MARCHER MAINTENANT POUR jPANE
		String result = sc.nextLine();
		return result;
	}
	
	public boolean saisieOuiNon(String message) {
		affiche(message);
		char rep = sc.next().charAt(0);
		return (rep == 'O'||rep == 'o'); //=true
	}
	
	//Sortie
	public void affiche(String message) {System.out.print(message);}



}
