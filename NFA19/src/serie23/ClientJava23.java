package serie23;
import utils.ClientJava1DateUser;
import serie22.*;

public class ClientJava23 {
	
	public static void main(String[] args) {
		TableArticle22 tabArt = new TableArticle22(); // Stock
		TableDesCommandes22 tabCde = new TableDesCommandes22(); //Commandes
		
		// NOUVELLES STRUCTURES GESTIONNAIRE
		GestionTableDesArticles23 gestArt = new GestionTableDesArticles23();
		GestionTableDesCommandes23 gestCommandes = new GestionTableDesCommandes23();
	
		
		//************************************** MENU GENERAL ****************************************
		
	int choix;		
		do {
			choix = menuGeneralChoix();	
			switch (choix) {
			case 1: gestArt.menuGeneral(tabArt, tabCde); break;
			case 2: gestCommandes.menuGeneral(tabCde, tabArt); break; //ON RAJOUTE LE NUMERO DE COMMANDE
			case 0: break; //CETTE CASE EST ELLE VRAIMENT UTILE??
			}
		}
		while (choix!=0) ;
		ClientJava1DateUser.affiche("\n\t *** AU REVOIR A BIENTOT DANS NOTRE SUPERETTE ***\n"); //NE MARCHE PLUS SI AON VEUT SORTIR DE SUITE D EPROGRAMME, MAIS JUSTEMENT PAS BESOIN	
	}
	
	public static int menuGeneralChoix() {
		String menu = "\n\t\t BIENVENUE A LA SUPERETTE \n" 
					+ "\n\t GESTION DU STOCK......................1"  //Correspond à GestArt.menuGeneral(tabArt, tabCde)
					+ "\n\t GESTION DES COMMANDES.................2"  //Correspond à gestCommandes.menuGeneral(tabCde, tabArt)
					+ "\n\t FIN...................................0"  //Correspond à choix==0; break;
					+ "\n\t\t\t\t VOTRE CHOIX:";
		return ClientJava1DateUser.lireEnt(menu, 0, 2);
	}
				
}
