package serie51;
import iConsole.ES;
import mesExceptions.AbandonException;

public class ClientJava51 {
	
	private static ES ES = new ES();
	
	public static void main(String[] args) throws AbandonException {
		// MAINTENANT D AORD LES GESTIONNAIES 
		GestionTableDesArticles51 gestArt = new GestionTableDesArticles51("/Users/Hekatari/eclipse-workspace/NFA019_Serie61.zip_expanded/NFA19/src/serie51/stock.DATA"); //On doit passer le nom physique qui ensuite est gérée par le gestionnaire
		GestionTableDesCommandes51 gestCommandes = new GestionTableDesCommandes51("/Users/Hekatari/eclipse-workspace/NFA019_Serie61.zip_expanded/NFA19/src/serie51/commandes.DATA");
		
		TableArticle51 tabArt = gestArt.recuperer(); // Stock, LE CLIENT NE CHARCHE PAS LE FICHIER AVEC LE STOCK
		TableDesCommandes51 tabCde =gestCommandes.recuperer(); //Commandes //LA GESTION EST DIFFERENTE , ELLE VA PASSER PAR TABLE DEScOMMANDES DIRECTEMENT ET PAS PAR LA GESTIONTABLES DE COMMANDES
		
		//************************************** MENU GENERAL ****************************************

	int choix;		
		do {
			choix = menuGeneralChoix();
			try {
				switch (choix) {
					case 1: gestArt.menuGeneral(tabArt, tabCde); break;
					case 2: gestCommandes.menuGeneral(tabCde, tabArt); break; //ON RAJOUTE LE NUMERO DE COMMANDE
					case 3: gestArt.ecrire(tabArt); gestCommandes.ecrire(tabCde);
							gestArt.fermer(tabArt); gestCommandes.fermer(tabCde); 
							ES.affiche("\n\t   LES DONNEES ONT ETE ENREGISTREES\n"); break;
					case 0: break; 
					}
				}
			catch(AbandonException abe) {
				choix=0;
			}
		}	while (choix!=0) ;
		
		gestArt.ecrire(tabArt); //POUR ENREGISTRER LA DATA AVANT LA FERMETURE
		gestCommandes.ecrire(tabCde);
		gestArt.fermer(tabArt);  //FERMER LE FICHIER
		gestCommandes.fermer(tabCde);
		ES.affiche("\n\t *** SAUVEGARDE DU FICHIER ***\n");
		ES.affiche("\n\t *** AU REVOIR A BIENTOT DANS NOTRE SUPERETTE ***\n"); //NE MARCHE PLUS SI AON VEUT SORTIR DE SUITE D EPROGRAMME, MAIS JUSTEMENT PAS BESOIN			
	}
	
	public static int menuGeneralChoix() throws AbandonException { //C EST QUOI ABA,DON EXCEPTION???
		String menu = "\n\t\t BIENVENUE A LA SUPERETTE \n" 
					+ "\n\t GESTION DU STOCK......................1"  //Correspond à GestArt.menuGeneral(tabArt, tabCde)
					+ "\n\t GESTION DES COMMANDES.................2"  //Correspond à gestCommandes.menuGeneral(tabCde, tabArt)
					+ "\n\t SAUVEGARDE DES DONNEES................3" 			  //Correspond à gestCommandes.menuGeneral(tabCde, tabArt)
					+ "\n\t FIN...................................0"  //Correspond à choix==0; break;
					+ "\n\t\t\t\t VOTRE CHOIX:";
		return (int)ES.saisie(menu, 0, 3); 
	}
				
}
