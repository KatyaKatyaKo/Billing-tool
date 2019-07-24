package iConsolejPaneAncien;

import java.util.InputMismatchException;
import java.util.Scanner;

import mesExceptions.AbandonException;
//Remplace Terminal
//CA POURRAIT ETRE STATIQUE (CAR NE DEMANDE PAS L'OBJET, JUSTE N'UTILISE PAS L'OBJET) 
							//MAIS !!
//CA CONVIENT PAS POUR CREER ENSUITE UNE INTERFACE, CAR INTERFACE N'UTILISE PAS STATIQUE


public class iConsoleAncien  {
	private Scanner sc = new Scanner(System.in);
	
	//Saisie (2 méthodes pour int, 2 méthodes pour float, 1 String, boolean sasieOuiNon)
	
	public int saisie(String message, int mini, int maxi) throws AbandonException  { //Exception géré au niveau de ClientJava
		do { //Je reste dans la saisie tant que la valeur n'est pas valide
			affiche(message);
		int data; 	
		try { 	//ZONE DE CAPTURE pour l'erreur de Java (MismatchException)
		data=sc.nextInt(); //On saisit un int
		//SI la saisie pas numérique==> ligne N 28 : catch(InputMismatchException ime)
		if (data>=mini&&data<=maxi) { return data;} //SI OK
		else {throw new AbandonException();} //SI sasie hors intervalle
		}
		catch(InputMismatchException ime) {
			sc.nextLine(); //IMPORTANT DE VIDER LE BUFFER PARCE QUE SINON BOUCLE À L'INFINIE, IL FAUT VIDER LE CASH - VIDE LE RETOUR CHARIOT
			affiche("\n\t\tSAISIE NON NUMERIQUE. RESAISISSEZ SVP\n");}
		catch(AbandonException abe) {
			affiche("\n\t   SAISIE HORS INTERVALLE de "+mini+" à " + maxi+"\n");
			if (saisieOuiNon("\n\tVoulez-vous QUITTER l'APPLICATION ? (O/N):")) throw abe;	
		}
		}while(true);
	}
		

	public int saisie(String message, int mini) throws AbandonException  { 
		do { 
			affiche(message);
		int data; 	
		try { 	
		data=sc.nextInt(); 
		//SI la saisie pas numérique==> ligne N 28 : catch(InputMismatchException ime)
		if (data>=mini) { return data;} 
		else {throw new AbandonException();} 
		}
		catch(InputMismatchException ime) {
			sc.nextLine(); //IMPORTANT DE VIDER LE BUFFER PARCE QUE SINON BOUCLE À L'INFINIE, IL FAUT VIDER LE CASH - VIDE LE RETOUR CHARIOT
			affiche("\n\t\tSAISIE NON NUMERIQUE. RESAISISSEZ SVP\n");}
		catch(AbandonException abe) {
			affiche("\n\t   SAISIE INFERIEUR à "+mini+"\n");
			if (saisieOuiNon("\n\tVoulez-vous QUITTER l'APPLICATION ? (O/N):")) throw abe;	
		}
		}while(true);
	}

	
// FLOAT - 2 MÉTHODES	
	public float saisie(String message, float mini, float maxi) throws AbandonException  { 
		do { 
			affiche(message);
		float data; 	
		try { 	
		data=sc.nextFloat(); 
		//SI la saisie pas numérique==> ligne N 28 : catch(InputMismatchException ime)
		if (data>=mini&&data<=maxi) { return data;} 
		else {throw new AbandonException();} 
		}
		catch(InputMismatchException ime) {
			sc.nextLine(); //IMPORTANT DE VIDER LE BUFFER PARCE QUE SINON BOUCLE À L'INFINIE, IL FAUT VIDER LE CASH - VIDE LE RETOUR CHARIOT
			affiche("\n\t\tSAISIE NON NUMERIQUE. RESAISISSEZ SVP\n");}
		catch(AbandonException abe) {
			affiche("\n\t   SAISIE HORS INTERVALLE de "+mini+" à " + maxi+"\n");
			if (saisieOuiNon("\n\tVoulez-vous QUITTER l'APPLICATION ? (O/N):")) throw abe;	
		}
		}while(true);
	}
		

	public float saisie(String message, float mini) throws AbandonException  { 
		do { 
			affiche(message);
		float data; 	
		try { 	
		data=sc.nextFloat(); 
		//SI la saisie pas numérique==> ligne N 28 : catch(InputMismatchException ime)
		if (data>=mini) { return data;} 
		else {throw new AbandonException();} 
		}
		catch(InputMismatchException ime) {
			sc.nextLine(); 
			affiche("\n\t\tSAISIE NON NUMERIQUE. RESAISISSEZ SVP\n");}
		catch(AbandonException abe) {
			affiche("\n\t   SAISIE INFERIEUR à "+mini+"\n");
			if (saisieOuiNon("\n\tVoulez-vous QUITTER l'APPLICATION ? (O/N):")) throw abe;	
		}
		}while(true);
	}

		
// STRING - 1 MÉTHODE		
	public String saisie (String message) {
		affiche(message);
		sc.nextLine(); //vide le chariot
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



