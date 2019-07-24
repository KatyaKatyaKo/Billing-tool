package mesInterfaces;

import mesExceptions.AbandonException;

public interface interfaceIO {
	Object saisie(String message, Object...obj) throws AbandonException;
	String saisie(String mes);
	boolean saisieOuiNon(String mes); 
}
