package gestion.salle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import fr.unantes.beans.Administrateur;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeSalle;
import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class GestionnaireSalle {

	private GestionnaireBatiment gb = new GestionnaireBatiment();
	private Map<String, String> erreurs = new HashMap<String, String>();
	private Administrateur admin = new Administrateur();
	
	public void ecrireSalle(Salle salle){
		System.out.println(salle.getId()+". "+salle.getNo_bat()+"-"+salle.getNo_etage()+"-"+salle.getNo_salle()+", Superficie : "+salle.getSuperficie());
	}
	
	
	public Salle ajoutSalle() {
		Salle salle = new Salle();

		System.out.println("Création d'une nouvelle salle");
		System.out.println("-----------------------------");

		// Batiment
		System.out.println("Numéro du bâtiment : ");
		ArrayList<Batiment> listeBatiment = gb.listeBatiment();		
		System.out.println("Liste de tous nos batiments : ");
		for(int i=0; i<listeBatiment.size(); i++){
			System.out.println(listeBatiment.get(i).getNo_bat()+". "+listeBatiment.get(i).getNom());
		}
		Scanner demandeBatiment = new Scanner(System.in);
		int batiment = demandeBatiment.nextInt();
		
		// Type
		System.out.println("Type de salle : ");
		ArrayList<TypeSalle> listeTypeSalle = listeTypeSalle();
		Scanner demandeType = new Scanner(System.in);
		int type = demandeType.nextInt();
		TypeSalle typeSalle = find(type);
		
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
		salle.setNo_bat(batiment);
		salle.setSuperficie(superficie);
		salle.setType(typeSalle);
		
		return salle;
	}
	
	//Ajout d'un nouveau type de salle
	public TypeSalle ajoutType(){
		TypeSalle type = new TypeSalle();
		
		System.out.println("Ajouter un type de salle");
		System.out.println("------------------------");
		System.out.println("Nom du type de salle : ");
		Scanner choixNom = new Scanner(System.in);
		String nom = choixNom.nextLine();
		
		type.setNom(nom);
		
		return type;
	}
	
	
	// Voir tous les types de salles
	public ArrayList<TypeSalle> listeTypeSalle() {
		ArrayList<TypeSalle> liste = new ArrayList();
		try {
			DAO<TypeSalle> typeDao = DAOFactory.getTypeSalleDAO();
			liste = typeDao.list();
			
			for(int i=0; i<liste.size(); i++){
				System.out.println(liste.get(i).getId() + ". " + liste.get(i).getNom());
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return liste;
	}
	
	//Chercher un type de salle
	public TypeSalle find(int id){
		TypeSalle type = new TypeSalle();
		try {
			DAO<TypeSalle> typeDao = DAOFactory.getTypeSalleDAO();
			type = typeDao.find(id);
		} catch (Exception e) {
			e.getMessage();
		}
		return type;
	}
	
	public Salle rechercheSalle(){
		Salle salle = new Salle();

		System.out.println("Recherche d'une salle");
		System.out.println("---------------------");
		System.out.println("1. Rechercher par batiment");
		System.out.println("2. Rechercher par numero d'étage");
		System.out.println("3. Rechercher par numero de salle ");
		System.out.println("3. Rechercher par type de salle");
		Scanner demandeChoix = new Scanner(System.in);
		int choix = demandeChoix.nextInt();
		
		switch(choix){
		case 1:
			salle = rechercheSalleParBatiment(gb.rechercheBatiment());
			break;
		case 2:
			System.out.println("Numéro du batiment : ");
			Scanner demandeEtage = new Scanner(System.in);
			int etage = demandeEtage.nextInt();

			break;
		case 3: 
			break;
		}
		return salle;
	}
	 
	//Recherche d'une salle par batiment
	public Salle rechercheSalleParBatiment(Batiment batiment){
		Salle salle = new Salle();

		for(int i=0; i<batiment.getListeSalle().size(); i++){
			ecrireSalle(batiment.getListeSalle().get(i));
		}
		
		if(batiment.getListeSalle().size() >1 ){
			System.out.println("Quelle salle choisir ?");
			Scanner demandeChoix = new Scanner(System.in);
			int choix = demandeChoix.nextInt();
			salle = getSalle(choix);
		}
		else if(batiment.getListeSalle().size() == 1){
			salle = batiment.getListeSalle().get(0);
		}
		else if(batiment.getListeSalle().size() == 0){
			salle = null;
		}
		return salle;
	}
	
	
	//Demande a faire une action avec une salle
		public void actionAvec(Salle salle){
			System.out.println("Que voulez-vous faire avec cette salle ? ");
			System.out.println("1. Supprimer la salle");
			System.out.println("2. Modifier la salle");
			System.out.println("Autre. Retour au menu");
			Scanner demandeChoix = new Scanner(System.in);
			int choix = demandeChoix.nextInt();
			
			switch(choix){
			case 1:
				System.out.println("Si vous supprimez cette salle, le materiel qui lui est attaché sera supprimé aussi, êtes-vous sur de votre choix ?");
				System.out.println("1. Oui");
				System.out.println("2. Non");
				Scanner demandeSupp = new Scanner(System.in);
				int choixSupp = demandeSupp.nextInt();
				if(choixSupp == 1){
					admin.deleteSalle(salle);
				}
				break;
			case 2:
				System.out.println("Nouveau nom du batiment : ");
				Scanner demandeNom = new Scanner(System.in);
				String nom = demandeNom.nextLine();
				
				break;
			}
		}
			
		//Selectionne une salle
		public Salle getSalle(int id){
			Salle salle = new Salle();
			try{
				DAO<Salle> salleDao = DAOFactory.getSalleDAO();
				salle = salleDao.find(id);
			}
			catch(Exception e){
				e.getMessage();
			}
			return salle;
		}
			
	//Verifie si la salle est faisable
	public void validationSalle(Salle salle) throws Exception{
		boolean result = true;
		if(salle.getSuperficie() < 0)
			throw new Exception( "Un peu petite la salle non ?" );
		if(salle.getType().equals(null))
			throw new Exception( "Ce type de salle n'existe pas" );
		if(salle.getNo_etage() < -4)
			throw new Exception( "L'étage est un peu trop bas" );
	}
	

    

}
