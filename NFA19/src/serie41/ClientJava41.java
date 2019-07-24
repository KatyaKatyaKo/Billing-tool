package serie41;
import jPane.ES;
import mesExceptions.AbandonException;

public class ClientJava41 {
	
	private static ES ES = new ES();
	
	public static void main(String[] args) throws AbandonException {
		TableArticle41 tabArt = new TableArticle41(); // Stock
		TableDesCommandes41 tabCde = new TableDesCommandes41(); //Commandes
		
		// NOUVELLES STRUCTURES GESTIONNAIRE
		GestionTableDesArticles41 gestArt = new GestionTableDesArticles41();
		GestionTableDesCommandes41 gestCommandes = new GestionTableDesCommandes41();
	
		
		
	
	
		
		//************************************** MENU GENERAL ****************************************
		
	int choix=-1;		
		do {
			try {
				choix = menuGeneralChoix();	
				switch (choix) {
				case 1: gestArt.menuGeneral(tabArt, tabCde); break;
				case 2: gestCommandes.menuGeneral(tabCde, tabArt); break; //ON RAJOUTE LE NUMERO DE COMMANDE
				case 0: break; //CETTE CASE EST ELLE VRAIMENT UTILE??
				}
				throw new AbandonException(); //Si la saisie n'est pas numérique
			}
			catch(AbandonException abe) {
				choix=0;
			}
		}	while (choix!=0) ;
		ES.affiche("\n\t *** AU REVOIR A BIENTOT DANS NOTRE SUPERETTE ***\n"); //NE MARCHE PLUS SI AON VEUT SORTIR DE SUITE D EPROGRAMME, MAIS JUSTEMENT PAS BESOIN			
	}
	
	public static int menuGeneralChoix() throws AbandonException { //C EST QUOI ABA,DON EXCEPTION???
		String menu = "\n\t\t BIENVENUE A LA SUPERETTE \n" 
					+ "\n\t GESTION DU STOCK......................1"  //Correspond à GestArt.menuGeneral(tabArt, tabCde)
					+ "\n\t GESTION DES COMMANDES.................2"  //Correspond à gestCommandes.menuGeneral(tabCde, tabArt)
					+ "\n\t FIN...................................0"  //Correspond à choix==0; break;
					+ "\n\t\t\t\t VOTRE CHOIX:";
		return (int)ES.saisie(menu, 0, 2); //
	}
				
}
