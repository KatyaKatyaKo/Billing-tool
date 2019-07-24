package serie21;

import utils.ClientJava1DateUser;

public class ClientJava21 {

	public static void main(String[] args) {

		// Appel des strcutures
		TableArticles21 tabArt = new TableArticles21(); // Stock
		//System.out.println(tabArt.get(1)); //Impossible d'utiliser cette méthode, on doir la héritier de class Objet et redefinir si besoin
		
		UneCommande cde = new UneCommande(); // Lignes de commandes
		
		//Vector <Article> brizk = new Vector();
		//brizk.add(new Article (2, "clou", 12));
		//brizk.add(new Article(11, "planche bois", 34));
		
		//System.out.println(brizk.get(1));
		
		

		// MENU1
		int choix = menuGeneralChoix();
		
		do {
			switch (choix) {
			case 1: menuGestionStock(); 
			case 2: gestionUneCommande(cde, tabArt); break; // Pour passer une commande il faut verifier le stock
			case 0: break; 
			}
		}
		while (choix==0) ;
		
		ClientJava1DateUser.affiche("\n\t *** AU REVOIR A BIENTOT DANS NOTRE SUPERETTE ***\n"); //NE MARCHE PLUS SI AON VEUT SORTIR DE SUITE D EPROGRAMME, MAIS JUSTEMENT PAS BESOIN
		
	}
	
	
	
	// METHODES
	public static int menuGeneralChoix() {
		String menu = "\n\t\t BIENVENUE A LA SUPERETTE \n" 
					+ "\n\t GESTION DU STOCK......................1"  //Correspond gestionStock(TableArticles21 tabArt)
					+ "\n\t GESTION COMMANDE......................2"  //Correspond gestionUneCommande(cde, tabArt)
					+ "\n\t FIN...................................0"  //Correspond à choix==00; break;
					+ "\n\t\t\t\t VOTRE CHOIX:";
		return ClientJava1DateUser.lireEnt(menu, 0, 2);
	}

	//**************************************GESTION STOCK ****************************************
	//GESTION DU STOCK (DES ARTICLES) - MENU COMMANDE
	public static int menuGestionStock() {
		String menu = "\n\t CREER un NOUVEL ARTICLE..1"
				     +"\n\t SUPPRIMER UN ARTICLE.....2"
				     +"\n\t MODIFIER UN ARTICLE......3"
				     +"\n\t AFFICHER LE STOCK........4" //afficheStock(tabArt) 
				     +"\n\t FIN......................0" 
					 + "\n\t\t\t\t VOTRE CHOIX:";
		return ClientJava1DateUser.lireEnt(menu, 0, 4);		
	}
	
	//GESTION DU STOCK - CHOIX
	public static void gestionStock() {
		int choix;
	 do {
		 choix = menuGestionStock();
		 switch(choix) {
		 case 1:
		 case 2: 
		 case 3:
		// case 4: afficheStock(tabArt);
		 case 0: break;	 
		 }
	 }
		while(choix!=0); 
	}

	//CREER un NOUVEL ARTICLE..1  //A FINIR
	
	//SUPPRIMER UN ARTICLE.....2  //A FINIR
	
	//MODIFIER UN ARTICLE......3  //A FINIR
	
	//AFFICHER LE STOCK........4
	public static void afficheStock(TableArticles21 tabArt) { //Correspond à 4 - afficher le stock
		if (tabArt.taille() == 0)
			ClientJava1DateUser.affiche("STOCK VIDE)");
		else{
			ClientJava1DateUser.affiche(tabArt.toString());
		}
	}

	//**************************************GESTION COMMANDE****************************************
	
	//GESTION COMMANDE - MENU COMMANDE
	public static int menuGestionUneCommande() {
		String menu = "\n\t SAISIR une LIGNE DE COMMANDE.......................1"  //creerLcd(cde, tabArt)
				     +"\n\t AFFICHER la COMMANDE EN COURS......................2"  //afficherLcd(cde)
				     +"\n\t EDITER la FACTURE..................................3"  //facturerCommande(cde, tabArt)
				     +"\n\t FIN................................................0"
					 +"\n\t\t\t\t VOTRE CHOIX:";
		return ClientJava1DateUser.lireEnt(menu, 0, 3);		
	}
	
	//GESTION COMMANDE - CHOIX
	public static void gestionUneCommande(UneCommande cde, TableArticles21 tabArt) { ////Correspond à 2 - afficher la commande en cours
		int choix;
	
		do {
		choix = menuGestionUneCommande();
				switch(choix)
				{
					case 1: creerLcd(cde, tabArt);break; //ok
					case 2: afficherLcd(cde); break;
					case 3: facturerCommande(cde, tabArt);break;
					case 0: break;
				}
		}while(choix!=0); 
	}
	
	
	//SAISIR une LIGNE DE COMMANDE.......................1
	private static LigneDeCommande saisieLdc(TableArticles21 tabArt) {
		ClientJava1DateUser.affiche("\n\t\t CREATION d'une LIGNE de COMMANDE\n");
		int codeArt = ClientJava1DateUser.lireEnt("\t SAISISSEZ le CODE de l'ARTICLE: ", 1, Integer.MAX_VALUE);
				if (tabArt.retourner(codeArt)==null) return null;
				else { int quanity = ClientJava1DateUser.lireEnt("\t SAISISSEZ la QUANTITE: ", 1, Integer.MAX_VALUE);
				return (new LigneDeCommande(codeArt, quanity ));}
	}
	
	//Si la ldc n'existe pas dans cde ==> c'est ldc ==> action:cde.ajouter(ldc)  / si elle existe déjà, c'est ldc2 ==> action: ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());  (on rajoute la quantité) 
	private static void creerLcd (UneCommande cde, TableArticles21 tabArt) {
		LigneDeCommande ldc = saisieLdc(tabArt);
		if(ldc!= null) {
			int code = ldc.getCode(); //Avec ce code de commande on va retrouver l'article dans TabArt
			LigneDeCommande ldc2 = cde.retourner(code); //Au premier passage de la boucle le cde est encore vide==> par default ldc2== null, mais au 2e passage de la boucle il y a déjà cde.get(0) g^race à l'instruction cde.ajouter(ldc); à la fin de cette boucle
			if(ldc2!=null) { //Si la commande contient une ligne de commande (on la cjerche par un code saisi lors de la saisie d'une ligne de commande)
				ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());
			}
			else {
			cde.ajouter(ldc); //Nous ajoutons nouvelle Ligne de Commande
			}
		}
		else {
			ClientJava1DateUser.affiche("\n\t ARTICLE COMMENDE N'EXISTE PAS");
		}
		
	}

	//AFFICHER la COMMANDE EN COURS......................2
	public static void afficherLcd (UneCommande cde){  //POURQUOI CETTE MÉTHODE EST STATIQUE???
		if(cde.taille()==0) {ClientJava1DateUser.affiche("\n\t\t COMMANDE EST VIDE");
		}
		else {ClientJava1DateUser.affiche(cde.toString());}	
	}
	
	
	//EDITER la FACTURE..................................3
	private static void facturerCommande(UneCommande cde, TableArticles21 tabArt) { //Une commande
		if(cde.taille()==0){
			ClientJava1DateUser.affiche("COMMANDE VIDE");
		}
		else {
			
			ClientJava1DateUser.affiche(cde.facturer(tabArt)); // Affiche ligne de commande
			//cde.facturer
		}	
	}




			
}
