package serie22;
import serie21.Article21;
import utils.ClientJava1DateUser;

public class ClientJava22 {
	
	public static void main(String[] args) {
		// Appel des strcutures
		TableArticle22 tabArt = new TableArticle22(); // Stock
		TableDesCommandes22 tabCde = new TableDesCommandes22(); //Commandes
		
		//************************************** MENU GENERAL ****************************************
		
	int choix;		
		do {
			choix = menuGeneralChoix();	
			switch (choix) {
			case 1: gestionStock(tabArt, tabCde); break;
			case 2: gestionDesCommandes(tabCde,tabArt); break; //ON RAJOUTE LE NUMERO DE COMMANDE
			case 0: break; 
			}
		}
		while (choix!=0) ;
		ClientJava1DateUser.affiche("\n\t *** AU REVOIR A BIENTOT DANS NOTRE SUPERETTE ***\n"); //NE MARCHE PLUS SI AON VEUT SORTIR DE SUITE D EPROGRAMME, MAIS JUSTEMENT PAS BESOIN	
	}
	
	public static int menuGeneralChoix() {
		String menu = "\n\t\t BIENVENUE A LA SUPERETTE \n" 
					+ "\n\t GESTION DU STOCK......................1"  //Correspond gestionStock(TableArticle22 tabArt)
					+ "\n\t GESTION DES COMMANDES.................2"  //Correspond gestionUneCommande(cde, tabArt)
					+ "\n\t FIN...................................0"  //Correspond à choix==0; break;
					+ "\n\t\t\t\t VOTRE CHOIX:";
		return ClientJava1DateUser.lireEnt(menu, 0, 2);
	}
	

	//************************************** MENU GESTION STOCK ****************************************
	
	public static int menuGestionStock() {
		String menu = "\n\t\t GESTION des ARTICLES \n"
					 +"\n\t CREER un NOUVEL ARTICLE..1" //Correspond creerArt(TableArticle22 tabArt)
				     +"\n\t SUPPRIMER UN ARTICLE.....2" //A FINIR PLUS TARD CAR CASCADE
				     +"\n\t MODIFIER UN ARTICLE......3"
				     +"\n\t AFFICHER LE STOCK........4" //Correspond afficheStock(tabArt) 
				     +"\n\t FIN......................0" //Correspond à choix==0; break;
					 + "\n\t\t\t\t VOTRE CHOIX:";
		return ClientJava1DateUser.lireEnt(menu, 0, 4);		
	}
	
	public static void gestionStock(TableArticle22 tabArt, TableDesCommandes22 tabCde ) {
		int choix;
	 do {
		 choix = menuGestionStock();
		 switch(choix) {
		 case 1: creerArt(tabArt); break;
		 case 2: supprimerArt(tabArt, tabCde); break; //A FINIR PLUS TARD CAR CASCADE
		 case 3: modifier(tabArt); break;
		 case 4: afficheStock(tabArt); break;
		 case 0: break;	 
		 }
	 }
		while(choix!=0); 
	}

	//************************************** MENU GESTION DES COMMANDES ****************************************

		public static int menuGestionDesCommandes(int numCde) { //ON A RAJOUTE LE PARAMETRE int numCd
			String menu = "\n\t\t GESTION des COMMANDES \n"
					     +"\n\t CREER une COMMANDE N"+numCde+"....1" //Correspond creerArt(TableArticle22 tabArt)
					     +"\n\t SUPPRIMER une COMMANDE...2" //CASCADE
					     +"\n\t MODIFIER une COMMANDE....3" //CASCADE
					     +"\n\t AFFICHER une COMMANDE....4"
					     +"\n\t AFFICHER les COMMANDES...5" //Correspond afficheStock(tabArt) 
					     +"\n\t FACTURER des COMMANDES...6" //Correspond afficheStock(tabArt) 
					     +"\n\t FIN......................0" //Correspond à choix==0; break;
						 + "\n\t\t\t\t VOTRE CHOIX:";
			return ClientJava1DateUser.lireEnt(menu, 0, 6);		
		}
	
		public static void gestionDesCommandes(TableDesCommandes22 tabCde, TableArticle22 tabArt) { 
			int choix;
			do {
				int numCde = premierNumDispo(tabCde); //ON RAJOUTE UNE VARIABLE 
				choix = menuGestionDesCommandes(numCde);
				switch(choix) {
				 case 1: creer1Commande(tabCde, tabArt, numCde); break; //ON DIRAIT QUE CA MARCHE PAS
				 case 2: supprimer1Commnade(tabCde); break; //A FINIR PLUS TARD CAR CASCADE
				 case 3: modifierCommande(tabCde, tabArt); break;
				 case 4: visualiserCommande(tabCde); break;
				 case 5: visualiserToutesCommandes(tabCde); break;
				 case 6: facturerDesCommandes(tabCde, tabArt); break;
				 case 0: break;	 
				 }
			 }
				while(choix!=0); 
			}

		//************************************** MENU GESTION UNE COMMANDE ****************************************
		
		public static int menuGestionUneCommande(int numCde) {
			String menu = "\n\t\t GESTION d'une COMMANDE \n"
					     +"\n\t SAISIR une LIGNE DE COMMANDE.......................1"  //creerLcd(cde, tabArt)
					     +"\n\t AFFICHER la COMMANDE EN COURS......................2"  //afficherLcd(cde)
					     +"\n\t EDITER la FACTURE..................................3"  //facturerCommande(cde, tabArt)
					     +"\n\t FIN................................................0"
						 +"\n\t\t\t\t VOTRE CHOIX:";
			return ClientJava1DateUser.lireEnt(menu, 0, 3);		
		}
		
		public static void gestionUneCommande(UneCommande22<Integer> cde, TableArticle22 tabArt, int numCde) { ////Correspond à 2 - afficher la commande en cours
			int choix;
		
			do {
			choix = menuGestionUneCommande(numCde);
					switch(choix)
					{
						case 1: creerLcd(cde, tabArt);break; //ok
						case 2: afficherLcd(cde); break;
						case 3: facturerCommande(cde, tabArt);break;
						case 0: break;
					}
			}while(choix!=0); 
		}
		
		//---------------------------------------------------METHODES--------------------------------------------------------
		
				//------------------------------------METODES GESTION DU STOCK --------------------------------
		
		//CREER un NOUVEL ARTICLE..1 
		public static void creerArt(TableArticle22 tabArt) {
			Article21 art = saisieArt(tabArt);
			if(art!=null) {
				tabArt.ajouter(art);	
			}
			else ClientJava1DateUser.affiche("\t CODE ARTICLE EXISTE DEJA\n");
		}
		
		private static Article21 saisieArt(TableArticle22 tabArt) { //Comment afficher pour que ca sera sur la meme ligne
			System.out.println("\n\t SAISISSEZ LE CODE DE L'ARTICLE:");
			System.out.println("\t (Le premier numéro disponible est " +(tabArt.premierNumDispo()) + ")\n\t");
			int code;
			code=ClientJava1DateUser.lireEnt("", 1, Integer.MAX_VALUE);
			//sc.next();
			if (tabArt.retourner(code)== null) {
				String designation = ClientJava1DateUser.lireString("\t DESIGNATION ARTICLE: \n\t");
				//System.out.println(designation.toUpperCase()); A FINIR LA CONVERSION EN MAJUSCULE
				float pu = (float)ClientJava1DateUser.lireEnt("\t PRIX UNITAIRE (saisissez un entier): ", 1, Integer.MAX_VALUE); //RAJOUTER UNE METHODE LIRE FLOAT
				return new Article21(code, designation, pu);
			}
			return null;
		}
		
		//SUPPRIMER UN ARTICLE.....2  // Cascade des valeurs
		public static void supprimerArt(TableArticle22 tabArt, TableDesCommandes22 tabCde) {
			int code = ClientJava1DateUser.lireEnt("\n\t CHOISSIEZ un ARTICLE a SUPPRIMER"+tabArt.cle()+":", 1, Integer.MAX_VALUE);
			if (tabArt.retourner(code)==null) {
				ClientJava1DateUser.affiche("ARTICLE N'EXISTE PAS");
			}
			else {
				tabArt.supprimer(code);
				tabCde.purge(code);
			}
		}
		
		//MODIFIER UN ARTICLE......3  
		public static void modifier(TableArticle22 tabArt) { //On modifie la designation
			int code = ClientJava1DateUser.lireEnt("\n\t CHOISSISSEZ UN ARTICLE A MODIFIER: "+tabArt.cle(), 1, Integer.MAX_VALUE);
			Article21 art = tabArt.retourner(code);
			if(art==null) {
				ClientJava1DateUser.affiche("\t ARTICLE N'EXISTE PAS\n");
			}
			else {
				System.out.println("");
				String designation = ClientJava1DateUser.lireString("\n\t SAISISSEZ LA NOUVELLE DESIGNATION: (si sr n as pas ete supprimé"); //On appue enter (rc) si on veut rien modifier
				if (!designation.equals("")) { //n est pas vide parce qu'il y a un retour chariot avant ca veut dire qu on a pas rajouter un article, parce quand on ajoute un article on l'ajoute lcean, en supprimant le retour chariot (voir lireString dans DataUser)
					art.setDesignation(designation.toUpperCase()); //Affichage at UpperCase
				}
				String pu  = ClientJava1DateUser.lireString("\n\t SAISISSEZ LE NOUVEAU PRIX: "); //On appue enter (rc) si on veut rien modifier
					if (!pu.equals("")) art.setPu(Float.parseFloat(pu));
				}
			}
		
		//AFFICHER LE STOCK........4
		public static void afficheStock(TableArticle22 tabArt) { //Correspond à 4 - afficher le stock
			if (tabArt.taille() == 0)
				ClientJava1DateUser.affiche("STOCK VIDE)");
			else{
				ClientJava1DateUser.affiche(tabArt.toString());
			}
		}
		
			//------------------------------------METODES GESTION DES COMMANDES --------------------------------
		
		
		// CREER une COMMANDE N"+numCde+"....1"
		public static void creer1Commande(TableDesCommandes22 tabCde, TableArticle22 tabArt, int numCde) { //A REVOIR
			//On cree une commande
			UneCommande22<Integer> cde = new UneCommande22<Integer>();
			cde.setNumCde(numCde); //On attribut un numéro à une commande
			gestionUneCommande(cde, tabArt, numCde);
				if(cde.taille()!=0) {  //Si la commande n'est pas vide (l'utilisateur a effectué le sopérations d ajout de code + quantité), on ajoute la commande dans tabCde
					tabCde.ajouter(cde);  
				}
		}
		
		//Boucle infinie
		public static int premierNumDispo(TableDesCommandes22 tabCde) { //CHAQUE FOIS LE COMPTEUR COMMENCE PAR 1 ==>
			int compteur = 1;
			do {
				if(tabCde.retourner(compteur)==null) return compteur; //Si la commande n'existe pas, on prend ce numéro
					compteur++;			
			}while(true);
		}
		
		
		//SUPPRIMER une COMMANDE...2
		public static void supprimer1Commnade(TableDesCommandes22 tabCde) {
			int numCde = ClientJava1DateUser.lireEnt("\n\t SAISISSEZ le NUMERO DE COMMANDE A SUPPRIMER:"+"\n\t\t"+tabCde.cle() + ":", 1, Integer.MAX_VALUE);
			if(tabCde.retourner(numCde)==null) {
				ClientJava1DateUser.affiche("\n\t LA TABLE des COMMANDES est VIDE");
			}
			else {
				tabCde.supprimer(numCde);
				ClientJava1DateUser.affiche("\t LA COMMANDE N"+numCde+" a été SUPPRIMEE\n");
			}
		}
		
		
		//MODIFIER une COMMANDE....3
				public static int menuModifierCommande() {
					String menu = "\n\t AJOUTER ou MODIFIER un ARTICLE.......1" //Correspond creerArt(TableArticle22 tabArt)
						     +"\n\t SUPPRIMER un ARTICLE...2" //CASCADE
						     +"\n\t FIN....................0" //CASCADE
							 + "\n\t\t\t\t VOTRE CHOIX:";
				return ClientJava1DateUser.lireEnt(menu, 0, 2);		
				}
			
				private static void modifierCommande(TableDesCommandes22 tabCde, TableArticle22 tabArt) {
					if(tabCde.taille()==0) {
						ClientJava1DateUser.affiche("\n\t LA TABLE des COMMANDES est VIDE");
					}
					else {
						int numCde = ClientJava1DateUser.lireEnt("\n\t SAISISSEZ le NUMERO de COMMANDE a MODIFIER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
						afficherLcd (tabCde.retourner(numCde)); 
						int choix;
						do {
							choix = menuModifierCommande();
							switch(choix) {
							 case 1: 
								 creerLcd (tabCde.retourner(numCde), tabArt);  //Parametres - creerLcd (cde, tabArt);
							 //tabCde.getTabCde().get(numCde).visualiserCommande(tabCde);
							 afficherLcd (tabCde.retourner(numCde));break;
							 case 2: 
								 //Prevenir d ela suppression de commande
								 if(tabCde.retourner(numCde).getUneCommande().size()==1) {
									 ClientJava1DateUser.affiche("\n\t IL RESTE le DERNIER ARTICLE :\n\t si vous le supprimer, la commande sera supprimée aussi\n");
									 menuModifierCommande(); //Comment faire un goto
								 }
								 int codeArt = ClientJava1DateUser.lireEnt("\n\t SAISISSEZ le Code d'ARTICLE a SUPPRIMER:\n\t " + ":", 1, Integer.MAX_VALUE);
								 tabCde.getTabCde().get(numCde).supprimer(codeArt); 
								 ClientJava1DateUser.affiche("\n\t ARTICLE A ETE SUPPRIME");
								 tabCde.retourner(numCde).getUneCommande();
								 //Ici situation très intéréssante: même si après la suppression il ne reste plus de lignes de commandes,
								 //LA COMMANDE N'EST PAS VIDE, parce qu'elle a le numéro de commande est une date==>
								 //pour vérifier que la commande est vide: la condition tabCde.retourner(numCde)==null ne sera jamais vrai==>
								 //il faut écrire tabCde.retourner(numCde).getUneCommande()==null On teste le Vector de Commande
								 if(tabCde.retourner(numCde).getUneCommande().isEmpty()) { //Si la commande est vide après la suppression d'un article
									 tabCde.supprimer(numCde); 
									 tabCde.purge(codeArt); //Ne pas oublier de purger chaque fois on supprime qqch
									 ClientJava1DateUser.affiche("\n\t IL NE RESTE PLUS D'ARTICLES : LA COMMANDE A ETE SUPPRIMEE\n"); 
									 choix=0; //POUR SORTIR DE CE CASE IL FAUT ARTIFICIELLEMENT DECLARER LE choix à zero (choix=0)
								 }
								 else {
									 ClientJava1DateUser.affiche(tabCde.retourner(numCde).toString()); //Réaffiche la commande
									 tabCde.purge(codeArt); break;//Ne pas oublier de purger chaque fois on supprime qqch
								 }
							 case 0: break;	 
							 }
						}
							while(choix!=0);}
					}	
		
		
		
		//AFFICHER une COMMANDE....4
		public static void visualiserCommande(TableDesCommandes22 tabCde) {
			if(tabCde.taille()==0) {
				ClientJava1DateUser.affiche("\n\t LA TABLE des COMMANDES EST VIDE, IL y a RIEN a AFFICHER");
				}
			else {	
				int numCde = ClientJava1DateUser.lireEnt("\n\t SAISISSEZ le NUMERO de COMMANDE a AFFICHER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
				//EST-CE QU'IL NOUS MANQUE UNE METHODE POUR AFFICHER UNE COMMANDE DANS TABLE DE COMMANDE. ON DOIT LA DEPLACER DE MAIN??
				afficherLcd (tabCde.retourner(numCde)); 
			}
		}
		
		//AFFICHER les COMMANDES...5
		public static void visualiserToutesCommandes(TableDesCommandes22 tabCde) { //case 4: visualiserToutesCommandes
			if(tabCde.taille()==0){
				ClientJava1DateUser.affiche("\n\t TABLE DES COMMANDES EST VIDE");
			}
			else {
				ClientJava1DateUser.affiche(tabCde.toString()); //N est pas créée dans TabledesCommandes
			}
		}
		
		//FACTURER des COMMANDES...6
		
		private static void facturerDesCommandes(TableDesCommandes22 tabCde, TableArticle22 tabArt) {
		if(tabCde.taille()==0){
			ClientJava1DateUser.affiche("\n\t IL N Y A PAS DE COMMMANDES A FACTURER");
		}
		else {
			//for (UneCommande22 Commande: tabCde)
			//int numCde = ClientJava1DateUser.lireEnt("\n\t SAISISSEZ le NUMERO de COMMANDE a FACTURER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
			//EST-CE QU'IL NOUS MANQUE UNE METHODE POUR AFFICHER UNE COMMANDE DANS TABLE DE COMMANDE. ON DOIT LA DEPLACER DE MAIN??
			//ClientJava1DateUser.affiche(tabCde.retourner(numCde).facturer(tabArt)); 
			//Afficher le numéro
			ClientJava1DateUser.affiche(tabCde.facturerCommandes(tabArt));
		}	
	}
	

	//Facturer une commande
		/*
	
	private static void facturerDesCommandes(TableDesCommandes22 tabCde, TableArticle22 tabArt) {
		if(tabCde.taille()==0){
			ClientJava1DateUser.affiche("\n\t IL N Y A PAS DE COMMMANDES A FACTURER");
		}
		else {
			int numCde = ClientJava1DateUser.lireEnt("\n\t SAISISSEZ le NUMERO de COMMANDE a FACTURER:\n\t "+ tabCde.cle() + ":", 1, Integer.MAX_VALUE);
			//EST-CE QU'IL NOUS MANQUE UNE METHODE POUR AFFICHER UNE COMMANDE DANS TABLE DE COMMANDE. ON DOIT LA DEPLACER DE MAIN??
			ClientJava1DateUser.affiche(tabCde.retourner(numCde).facturer(tabArt)); 
		}
			
	}

		 */

			//------------------------------------METODES GESTION une COMMANDE --------------------------------



	//SAISIR une LIGNE DE COMMANDE.......................1
	private static LigneDeCommande22 saisieLdc(TableArticle22 tabArt) {
		ClientJava1DateUser.affiche("\n\t\t CREATION d'une LIGNE de COMMANDE\n");
		int codeArt = ClientJava1DateUser.lireEnt("\t SAISISSEZ le CODE de l'ARTICLE: ", 1, Integer.MAX_VALUE);
				if (tabArt.retourner(codeArt)==null) return null;
				else { int quanity = ClientJava1DateUser.lireEnt("\t SAISISSEZ la QUANTITE: ", 1, Integer.MAX_VALUE);
				return (new LigneDeCommande22(codeArt, quanity ));}
	}
	
	//Si la ldc n'existe pas dans cde ==> c'est ldc ==> action:cde.ajouter(ldc)  / si elle existe déjà, c'est ldc2 ==> action: ldc2.setQuantite(ldc2.getQuantity()+ldc.getQuantity());  (on rajoute la quantité) 
	private static void creerLcd (UneCommande22<Integer> cde, TableArticle22 tabArt) {
		LigneDeCommande22 ldc = saisieLdc(tabArt);
		if(ldc!= null) {
			int code = ldc.getCode(); //Avec ce code de commande on va retrouver l'article dans TabArt
			LigneDeCommande22 ldc2 = cde.retourner(code); //Au premier passage de la boucle le cde est encore vide==> par default ldc2== null, mais au 2e passage de la boucle il y a déjà cde.get(0) g^race à l'instruction cde.ajouter(ldc); à la fin de cette boucle
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
	public static void afficherLcd (UneCommande22<Integer> cde){  //POURQUOI CETTE MÉTHODE EST STATIQUE??? LA DEPLACER PE DANS COMMANDE
		if(cde.taille()==0) {ClientJava1DateUser.affiche("\n\t\t COMMANDE EST VIDE");
		}
		else {ClientJava1DateUser.affiche(cde.toString());}	
	}
	
	
	//EDITER la FACTURE..................................3 //ERREUR EST LA
	private static void facturerCommande(UneCommande22<Integer> cde, TableArticle22 tabArt) { //Une commande
		if(cde.taille()==0){
			ClientJava1DateUser.affiche("\n\t COMMANDE EST VIDE");
		}
		else {
			ClientJava1DateUser.affiche(cde.facturer(tabArt)); // Affiche ligne de commande
		}	
	}			
}
