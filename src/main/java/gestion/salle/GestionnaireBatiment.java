package gestion.salle;

import java.util.ArrayList;
import java.util.Scanner;

import fr.unantes.beans.Administrateur;
import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class GestionnaireBatiment {
	
	private GestionnaireAdresse ga= new GestionnaireAdresse();
	private Administrateur admin = new Administrateur(); 
	
	
	//Ajout d'un nouveau batiment
	public Batiment ajoutBatiment(Adresse adresse) {
		Batiment batiment = new Batiment();

		// Nom du batiment
		System.out.println("Nom du bâtiment : ");
		Scanner demandeNom = new Scanner(System.in);
		String nom = demandeNom.nextLine();

		batiment.setNom(nom);
		batiment.setAdresse(adresse);

		// Renvoie visuel
		System.out.println("Ajout du batiment " + batiment.getNom() + " à l'adresse suivante :");
		batiment.getAdresse().toString();

		return batiment;
	}
	
	
	//Rechercher un batiment selon 3 cas possibles
	public Batiment rechercheBatiment(){
		Batiment batiment = new Batiment();
		
		System.out.println("Recherche d'un batiment");
		System.out.println("-----------------------");
		System.out.println("1. Rechercher par nom de batiment");
		System.out.println("2. Rechercher par numero de batiment");
		System.out.println("3. Rechercher un batiment avec son adresse");
		Scanner demandeChoix = new Scanner(System.in);
		int choix = demandeChoix.nextInt();
		
		switch(choix){
		case 1:
			batiment = rechercheBatimentParNom();
			break;
		case 2:
			System.out.println("Numéro du batiment : ");
			Scanner demandeNumero = new Scanner(System.in);
			int numero = demandeNumero.nextInt();
			batiment = getBatiment(numero);
			break;
		case 3: 
			break;
		}
		return batiment;
	}
	
	//Recherche d'un batiment grace a son nom
	public Batiment rechercheBatimentParNom(){
		Batiment batiment = new Batiment();
		
		System.out.println("Nom du batiment : ");
		Scanner demandeNom = new Scanner(System.in);
		String nom = demandeNom.nextLine();
		
		ArrayList<Batiment> liste = new ArrayList();
		
		for(int i=0; i<liste.size(); i++){
			if(liste.get(i).getNom().equals(nom))
				batiment = liste.get(i);
		}
		return batiment;
	}
	
	
	//Recherche d'un batiment grace a son adresse
	public Batiment rechercheBatimentParAdresse(Adresse adresse){
		ArrayList<Batiment> liste = new ArrayList();
		Batiment batiment = new Batiment();
		
		for(int i=0; i<liste.size(); i++){
			if(liste.get(i).getAdresse().getCode().equals(adresse.getCode())
					&& liste.get(i).getAdresse().getAdresse().equals(adresse.getAdresse())
					&& liste.get(i).getAdresse().getNo().equals(adresse.getNo())
					&& liste.get(i).getAdresse().getVille().equals(adresse.getVille()))
				batiment = liste.get(i);
		}
		return batiment;
	}
	
	//Supprimer un batiment
	public Batiment deleteBatiment(){
		Batiment batiment = new Batiment();
		System.out.println("Suppression d'un batiment");
		System.out.println("-------------------------");
		System.out.println("Quel batiment voulez-vous supprimer ?");
		ArrayList<Batiment> liste = new ArrayList();
		for(int i=0; i<liste.size(); i++){
			System.out.println(i+". "+liste.get(i).getNom());
		}
		
		Scanner choixBatiment = new Scanner(System.in);
		int choix = choixBatiment.nextInt();
		
		if(!liste.get(choix).equals(null)){
			batiment = liste.get(choix);
		}
		return batiment;	
	}
	
	
	//Demande a faire une action avec un batiment
	public void actionAvec(Batiment batiment){
		System.out.println("Que voulez-vous faire avec ce batiment ? ");
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
				admin.deleteBatiment(batiment);
			}
			break;
		case 2:
			System.out.println("Nouveau nom du batiment : ");
			Scanner demandeNom = new Scanner(System.in);
			String nom = demandeNom.nextLine();
			batiment.setNom(nom);
			
			admin.updateBatiment(batiment);
			break;

		}
		
	}

}
