package mesInterfaces;

import mesExceptions.AbandonException;

public interface interfaceGestion <TypeStructure> {
	
	void menuGeneral(TypeStructure tab, Object...objects) throws AbandonException;
	void creer(TypeStructure tab, Object...objects) throws AbandonException;
	void supprimer(TypeStructure tab, Object...objects) throws AbandonException;
	void modifier(TypeStructure tab, Object...objects) throws AbandonException;
	void afficher(TypeStructure tab) throws AbandonException;
}
