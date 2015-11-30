package gestion.salle;

import java.util.ArrayList;
import java.util.Scanner;

import fr.unantes.beans.Administrateur;
import fr.unantes.beans.Adresse;
import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class GestionnaireAdresse {
	

	// Lister toutes les adresses
	public ArrayList<Adresse> listeAdresse() {
		ArrayList<Adresse> listeAdresse = new ArrayList();
		try {
			DAO<Adresse> adresseDao = DAOFactory.getAdresseDAO();
			listeAdresse = adresseDao.list();
			
			for(int i=0; i<listeAdresse.size(); i++){
				listeAdresse.get(i).toString();
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return listeAdresse;
	}

	// Choisir une adresse selon le code postal
	public Adresse choisirAdresse(String codePostal) {
		Adresse adresse = new Adresse();

		// Liste avec toutes les adresses
		ArrayList<Adresse> listeAdresse = listeAdresse();
		// Liste avec les adresses dont le CP correspond
		ArrayList<Adresse> liste = new ArrayList(); 

		// On enlève d'abord les adresses dont le code postal ne correspond pas
		for (int i = 0; i < listeAdresse.size(); i++) {
			if (listeAdresse.get(i).getCode().equals(codePostal))
				liste.add(listeAdresse.get(i));
		}

		// On renvoie à l'utilisateur toutes les adresses dont le code postal est identique
		if (!liste.isEmpty()) {
			System.out.println("Adresses déjà existantes avec le même Code Postal : ");

			int quitter = 0;
			for (int i = 0; i < liste.size(); i++) {
				Adresse potentielle = liste.get(i);
				System.out.println("Adresse " + i + " :");
				potentielle.toString();
				quitter++;
			}
			System.out.println("---------------------------");
			System.out.println(quitter + ". Ajouter une nouvelle adresse");

			// On choisit l'action
			Scanner choixAdresse = new Scanner(System.in);
			int choix = choixAdresse.nextInt();
			if (choix != quitter && choix <= liste.size()) 
				adresse = liste.get(choix);
		} 

		return adresse;
	}
	
	
	// Ajouter une nouvelle adresse
	public Adresse ajoutAdresse() {
		
		// Adresse
		Adresse adresse = new Adresse();
		System.out.println("Saisissez une nouvelle adresse");
		System.out.println("------------------------------");
			
		// Code postal
		System.out.println("Code postal : ");
		Scanner demandeCodePostal = new Scanner(System.in);
		String codePostal = demandeCodePostal.next();
			
		//Adresse existante = choisirAdresse(codePostal);

		//Si le code postal n'a aucune correspondance on crée une nouvelle adresse
		//if(existante.getId() == 0){
				
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
		
		adresse.setNo(numero);
		adresse.setAdresse(adresse2);
		adresse.setCode(codePostal);
		adresse.setVille(ville);
		
			//}
			//else{
			//	adresse = existante;
			//}
			return adresse;
		}

}
