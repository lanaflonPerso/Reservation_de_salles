package gestion.salle;

import java.util.ArrayList;
import java.util.Scanner;

import fr.unantes.bdd.Connexion;
import fr.unantes.beans.Administrateur;
import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;
import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class App {
	private static Connexion connexion;

	private static ArrayList<Batiment> listeBatiments = new ArrayList();
	private static ArrayList<Adresse> listeAdresses = new ArrayList();
	private static ArrayList<Salle> listeSalles = new ArrayList();
	private static ArrayList<TypeSalle> listeTypeSalles = new ArrayList();

	public static void main(String[] args) {
		// Création des données avec une BDD
		// connexion.creationBDD();
		// connexion.insertionTables();

		// Création des données sans BDD
		initialisation();

		// Identification du nom
		System.out.println("Votre nom : ");
		Scanner demandeNom = new Scanner(System.in);
		String nom = demandeNom.next();

		if (nom.equals("admin")) {
			menuAdmin();
		} else {
			menuDemandeur();
		}

	}

	// Laisse le choix à l'administrateur de faire les actions cités
	public static void menuAdmin() {
		int choix = 0;
		do {
			System.out.println("Que souhaitez-vous faire ?");
			System.out.println("1. Ajouter un nouveau batiment");
			System.out.println("2. Rechercher un batiment");
			System.out.println("3. Ajouter une salle");
			System.out.println("4. Rechercher une salle");
			System.out.println("5. Ajouter un type de salle");
			System.out.println("10. Quitter");

			Scanner demandeChoix = new Scanner(System.in);
			choix = demandeChoix.nextInt();

			switch (choix) {
			case 1:
				ajoutBatiment();
				break;
			case 2:
				actionAvec(rechercheBatiment());
				break;
			case 3:
				ajoutSalle();
				break;
			case 4:
				rechercheSalle();
				break;
			case 5:
				ajoutTypeSalle();
				break;
			case 6:

				break;
			case 10:
				System.out.println("Bye");
			}
		} while (choix != 10);
	}

	// Laisse le choix à un demandeur de faire les actions cités
	public static void menuDemandeur() {
		System.out.println("Que souhaitez-vous faire ?");
		System.out.println("1. Réserver une salle");
		System.out.println("2. Gérer mes réservations");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}

	// Initialise tous les objets
	public static void initialisation() {
		Origine origine1 = new Origine(1, "Nantes");
		Origine origine2 = new Origine(2, "Paris");
		Origine origine3 = new Origine(3, "Tours");
		Origine origine4 = new Origine(4, "Lyon");

		Titre titre1 = new Titre(1, "Monsieur");
		Titre titre2 = new Titre(2, "Madame");
		Titre titre3 = new Titre(3, "Mademoiselle");

		TypeSalle typeSalle1 = new TypeSalle(1, "reunion");
		TypeSalle typeSalle2 = new TypeSalle(2, "fete");
		listeTypeSalles.add(typeSalle1);
		listeTypeSalles.add(typeSalle2);
		
		TypeMateriel typeMateriel1 = new TypeMateriel(1, "fixe");
		TypeMateriel typeMateriel2 = new TypeMateriel(2, "mobile");

		Adresse adresse1 = new Adresse("10", "Boulevard Amiral Courbet", "44000", "Nantes");
		Adresse adresse2 = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		Adresse adresse3 = new Adresse("23", "Rue de Bourgogne", "92000", "Paris");
		Adresse adresse4 = new Adresse("1", "Rue de l'école", "44000", "Nantes");
		Adresse adresse5 = new Adresse("2", "Rue du foix", "37000", "Tours");
		listeAdresses.add(adresse1);
		listeAdresses.add(adresse2);
		listeAdresses.add(adresse3);
		listeAdresses.add(adresse4);
		listeAdresses.add(adresse5);

		Salle salle1 = new Salle(0, 1, 0, 68, null, null, typeSalle1);
		Salle salle2 = new Salle(1, 2, 0, 30, null, null, typeSalle1);
		Salle salle3 = new Salle(0, 6, 1, 40, null, null, typeSalle2);
		listeSalles.add(salle1);
		listeSalles.add(salle2);
		listeSalles.add(salle3);

		Batiment batiment1 = new Batiment(0, "Universite de Nantes", adresse2,null);
		Batiment batiment2 = new Batiment(1, "Salle des fêtes de Nantes",adresse1,null);
		batiment1.ajoutSalle(salle1);
		batiment1.ajoutSalle(salle2);
		batiment2.ajoutSalle(salle3);
		listeBatiments.add(batiment1);
		listeBatiments.add(batiment2);

		Demandeur demandeur1 = new Demandeur(1, "Roger", adresse3, origine1, titre1, null);
		Demandeur demandeur2 = new Demandeur(2, "Giselle", adresse3, origine1, titre2, null);
		Demandeur demandeur3 = new Demandeur(3, "Godefroy", adresse4, origine3, titre1, null);

	}

	//Vérifie si une entrée d'un Scanner est correct
	public static boolean entreeCorecte(int entree,int max){
		boolean corect = true;
		if(entree >= max || 0 > entree){
			corect = false;
			System.out.println("Entrée incorrecte");
		}
		return corect;
	}
	
	// Ajouter une nouvelle adresse
	public static Adresse ajoutAdresse() {

		System.out.println("Saisissez une nouvelle adresse");
		System.out.println("------------------------------");

		// Code postal
		System.out.println("Code postal : ");
		Scanner demandeCodePostal = new Scanner(System.in);
		String codePostal = demandeCodePostal.next();

		// on crée une nouvelle adresse
		// if(choisirAdresse(codePostal).equals(null)){
		// if (true) {
		// Numéro
		System.out.println("Numéro : ");
		Scanner demandeNumero = new Scanner(System.in);
		String numero = demandeNumero.next();

		// Adresse
		System.out.println("Adresse : ");
		Scanner demandeAdresse = new Scanner(System.in);
		String adresse2 = demandeAdresse.nextLine();

		// Ville
		System.out.println("Ville :");
		Scanner demandeVille = new Scanner(System.in);
		String ville = demandeVille.nextLine();

		Adresse adresse = new Adresse(numero, adresse2, codePostal, ville);
		listeAdresses.add(adresse);
		// } else {

		// }
		return adresse;
	}

	// Choisir une adresse selon le code postal
	public static Adresse choisirAdresse(String codePostal) {
		Adresse adresse = new Adresse();

		// On affiche que les adresses dont le CP est le meme
		for (int i = 0; i < listeAdresses.size(); i++) {
			if (listeAdresses.get(i).getCode().equals(codePostal))
				System.out.println(i + ". " + listeAdresses.get(i).toString());
		}
		System.out.println(listeAdresses.size() + ". --------------------");
		System.out.println("Ajouter une nouvelle adresse");

		// On choisit l'action
		Scanner choixAdresse = new Scanner(System.in);
		int choix = choixAdresse.nextInt();
		if (choix < listeAdresses.size())
			adresse = listeAdresses.get(choix);
		else
			return null;

		return adresse;
	}
	
	// Ajout d'un nouveau batiment
	public static void ajoutBatiment() {
		Batiment batiment = new Batiment();

		// Nom du batiment
		System.out.println("Nom du bâtiment : ");
		Scanner demandeNom = new Scanner(System.in);
		String nom = demandeNom.nextLine();

		Adresse adresse = ajoutAdresse();

		batiment.setNom(nom);
		batiment.setAdresse(adresse);

		// Renvoie visuel
		System.out.println("Ajout du batiment " + batiment.getNom()
				+ " à l'adresse suivante :");
		System.out.println(batiment.getAdresse().toString());

		listeBatiments.add(batiment);
	}

	// Rechercher un batiment selon 3 cas possibles
	public static Batiment rechercheBatiment() {
		Batiment batiment = new Batiment();

		System.out.println("Recherche d'un batiment");
		System.out.println("-----------------------");
		System.out.println("1. Rechercher par nom de batiment");
		System.out.println("2. Rechercher par numero de batiment");
		System.out.println("3. Rechercher un batiment avec son adresse");
		Scanner demandeChoix = new Scanner(System.in);
		int choix = demandeChoix.nextInt();

		switch (choix) {
		case 1:
			batiment = rechercheBatimentParNom();
			break;
		case 2:
			batiment = rechercheBatimentParNum();
			break;
		case 3:
			batiment = rechercheBatimentParAdresse();
			break;
		default:
			break;
		}
		return batiment;
	}

	// Recherche d'un batiment grace a son nom
	public static Batiment rechercheBatimentParNom() {
		Batiment batiment = new Batiment();

		System.out.println("Nom du batiment : ");
		Scanner demandeNom = new Scanner(System.in);
		String nom = demandeNom.nextLine();

		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNom().equals(nom))
				batiment = listeBatiments.get(i);
		}
		return batiment;
	}

	// Recherche un batiment grace a son numero
	public static Batiment rechercheBatimentParNum() {
		Batiment batiment = new Batiment();

		System.out.println("Numéro du batiment : ");
		Scanner demandeNumero = new Scanner(System.in);
		int numero = demandeNumero.nextInt();

		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNo_bat() == numero)
				batiment = listeBatiments.get(i);
		}
		return batiment;
	}

	// Recherche d'un batiment grace a son adresse
	public static Batiment rechercheBatimentParAdresse() {
		Adresse adresse = new Adresse();
		Batiment batiment = new Batiment();

		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getAdresse().getCode()
					.equals(adresse.getCode())
					&& listeBatiments.get(i).getAdresse().getAdresse()
							.equals(adresse.getAdresse())
					&& listeBatiments.get(i).getAdresse().getNo()
							.equals(adresse.getNo())
					&& listeBatiments.get(i).getAdresse().getVille()
							.equals(adresse.getVille()))
				batiment = listeBatiments.get(i);
		}
		return batiment;
	}
	
	//Demande a faire une action avec un batiment
	public static void actionAvec(Batiment batiment){
		System.out.println("Que voulez-vous faire avec le batiment " + batiment.getNom()+" ?");
		System.out.println("1. Supprimer le batiment");
		System.out.println("2. Modifier le batiment");
		System.out.println("Autre. Retour au menu");
		Scanner demandeChoix = new Scanner(System.in);
		int choix = demandeChoix.nextInt();
			
		switch(choix){
		case 1:
			System.out.println("Si vous supprimez ce batiment, les salles qui lui sont attachées seront supprimées aussi, êtes-vous sur de votre choix ?");
			System.out.println("1. Oui");
			System.out.println("2. Non");
			Scanner demandeSupp = new Scanner(System.in);
			int choixSupp = demandeSupp.nextInt();
			if(choixSupp == 1){
				listeBatiments.remove(batiment);
			}
			break;
		case 2:
			System.out.println("Nouveau nom du batiment : ");
			Scanner demandeNom = new Scanner(System.in);
			String nom = demandeNom.nextLine();
			batiment.setNom(nom);
			break;
			}
	}
		
	//Demande le type de salle
	public static TypeSalle getTypeSalle(){
		System.out.println("Type de salle : ");
		int choix = -1;
		for(int i=0; i<listeTypeSalles.size();i++){
			System.out.println((i+". "+listeTypeSalles.get(i).getNom()));
		}
		do{
			System.out.println("Quelle type de salle choisir ?");
			Scanner demandeChoix = new Scanner(System.in);
			choix = demandeChoix.nextInt();	
		}
		while(!entreeCorecte(choix, listeTypeSalles.size()));
		TypeSalle typeSalle = listeTypeSalles.get(choix);
		
		return typeSalle;
	}
	
	//Ajout d'un nouveau type de salle
	public static void ajoutTypeSalle(){
		TypeSalle type = new TypeSalle();
		
		System.out.println("Ajouter un type de salle");
		System.out.println("------------------------");
		System.out.println("Nom du type de salle : ");
		Scanner choixNom = new Scanner(System.in);
		String nom = choixNom.nextLine();
		
		type.setNom(nom);
		listeTypeSalles.add(type);
		
	}
		
	//Ajouter une salle
	public static void ajoutSalle() {
		Salle salle = new Salle();

		System.out.println("Création d'une nouvelle salle");
		System.out.println("-----------------------------");

		// Batiment
		System.out.println("Liste de tous nos batiments : ");
		for(int i=0; i<listeBatiments.size(); i++){
			System.out.println(i+". "+listeBatiments.get(i).getNom());
		}
		System.out.println(listeBatiments.size()+". Ajouter un nouveau batiment");
		
		int choix_batiment = -1;
		int num_batiment = -1;
		do{
		Scanner demandeBatiment = new Scanner(System.in);
		choix_batiment = demandeBatiment.nextInt();

		//Ajout d'un nouveau batiment
		if(choix_batiment == listeBatiments.size()){
			ajoutBatiment();
		}
		else if(choix_batiment < listeBatiments.size()){
			num_batiment = listeBatiments.get(choix_batiment).getNo_bat();
		}
		}
		while(!entreeCorecte(choix_batiment, listeBatiments.size()+1));	
	
		// Type
		TypeSalle typeSalle = getTypeSalle();
		
		// Etage
		System.out.println("Etage de la salle : ");
		Scanner demandeEtage = new Scanner(System.in);
		int etage = demandeEtage.nextInt();
		
		// Numero
		System.out.println("Numéro de salle : ");
		Scanner demandeNumero = new Scanner(System.in);
		int numero = demandeNumero.nextInt();
		
		// Superficie
		System.out.println("Superficie de la salle :");
		Scanner demandeSuperficie = new Scanner(System.in);
		int superficie = demandeSuperficie.nextInt();		
		
		salle.setNo_etage(etage);
		salle.setNo_salle(numero);
		salle.setNo_bat(num_batiment);
		salle.setSuperficie(superficie);
		salle.setType(typeSalle);

		listeBatiments.get(choix_batiment).ajoutSalle(salle);
		
	}
	
	//Recherche d'une salle
	public static Salle rechercheSalle(){
		Salle salle = new Salle();

		System.out.println("Recherche d'une salle");
		System.out.println("-----------------------");
		System.out.println("1. Rechercher par batiment");
		System.out.println("2. Rechercher par type de salle");
		System.out.println("3. Rechercher par numéro de salle");
		System.out.println("4. Rechercher par numéro d'étage");
		Scanner demandeChoix = new Scanner(System.in);
		int choix = demandeChoix.nextInt();

		switch (choix) {
		case 1:
			salle = rechercheSalleParBatiment();
			break;
		case 2:
			salle = rechercheSalleParType();
			break;
		case 3:
			salle = rechercheSalleParNumeroSalle();
			break;
		case 4:
			salle = rechercheSalleParNumeroEtage();
			break;
		}
		System.out.println("Salle sélectionnée : " + salle.toString());
		return salle;
	}

	//Recherche une salle par batiment
	public static Salle rechercheSalleParBatiment(){
		Batiment batiment = rechercheBatiment();
		Salle salle = new Salle();
		
		for(int i=0; i<batiment.getListeSalle().size(); i++){
			System.out.print(i+". ");
			System.out.println(batiment.getListeSalle().get(i).toString());
		}
		
		if(batiment.getListeSalle().size() > 1 ){
			int choix = -1;
			do{
				System.out.println("Quelle salle choisir ?");
				Scanner demandeChoix = new Scanner(System.in);
				choix = demandeChoix.nextInt();	
			}
			while(!entreeCorecte(choix, batiment.getListeSalle().size()));	
			salle = batiment.getListeSalle().get(choix);
		}
		else if(batiment.getListeSalle().size() == 1){
			salle = batiment.getListeSalle().get(0);
		}
		else if(batiment.getListeSalle().size() == 0){
			salle = null;
		}
		return salle;
	}
	
	//Recherche une salle par type de salle
	public static Salle rechercheSalleParType(){
		Salle salle = new Salle();
		TypeSalle type = getTypeSalle();
		ArrayList<Salle> liste = new ArrayList();
		
		//On ajoute toutes les salles dont le type est correct
		for(int i=0; i< listeSalles.size(); i++){
			if(listeSalles.get(i).getType().equals(type))
				liste.add(listeSalles.get(i));
		}
		
		//On boucle sur la liste contenant toutes les bonnes salles
		for(int i=0; i< liste.size(); i++){
			System.out.print(i+". ");
			System.out.println(liste.get(i).toString());
		}
		
		if(liste.size() > 1 ){
			int choix = -1;
			do{
				System.out.println("Quelle salle choisir ?");
				Scanner demandeChoix = new Scanner(System.in);
				choix = demandeChoix.nextInt();	
			}
			while(!entreeCorecte(choix, liste.size()));	
			salle = liste.get(choix);
		}
		else if(liste.size()==1){
			salle = liste.get(0);
		}
		else{
			salle = null;
		}
		return salle;
	}
	
	//Recherche une salle par numero de salle
	public static Salle rechercheSalleParNumeroSalle(){
		Salle salle = new Salle();
		System.out.println("Numéro de salle : ");

		Scanner demandeNumero = new Scanner(System.in);
		int numero = demandeNumero.nextInt();

		for (int i = 0; i < listeSalles.size(); i++) {
			if (listeSalles.get(i).getNo_salle() == numero)
				salle = listeSalles.get(i);
		}

		return salle;
	}
	
	//Recherche une salle par numero d'etage
	public static Salle rechercheSalleParNumeroEtage(){
		Salle salle = new Salle();
		System.out.println("Numéro d'étage : ");

		Scanner demandeNumero = new Scanner(System.in);
		int numero = demandeNumero.nextInt();

		for (int i = 0; i < listeSalles.size(); i++) {
			if (listeSalles.get(i).getNo_etage() == numero)
				salle = listeSalles.get(i);
		}

		return salle;
	}

	//Quelles actions faire avec une salle
	public void actionAvec(Salle salle){
		System.out.println("1. Ajouter du matériel à la salle");
		System.out.println("2. Modifier du matériel à la salle");
		System.out.println("3. Enlever du matériel à la salle");
		System.out.println("4. Supprimer la salle");
		Scanner demandeChoix = new Scanner(System.in);
		int choix = demandeChoix.nextInt();
		
		switch(choix){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}
	
	
	
	
	
}
		

