package fr.unantes.beans;

import java.util.ArrayList;

import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class Administrateur extends Demandeur {

	private final String motDePasse = "a";
	private final String login = "a";

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrateur(int no_dem, String nom, Adresse adresse,
			Origine origine, Titre titre,
			ArrayList<Reservation> listeReservation) {
		super(no_dem, nom, adresse, origine, titre, listeReservation);
		// TODO Auto-generated constructor stub
	}

	// Identifier l'administrateur
	public boolean identification(String login, String motDePasse) {
		boolean result;
		if (login.equals(this.login) && motDePasse.equals(this.motDePasse)) {
			result = true;
			System.out.println("Vous êtes admin");
		} else {
			result = false;
			System.out.println("Vous n'êtes pas admin");
		}
		return result;
	}

	// Ajout d'un batiment en base
	public Batiment ajoutBatiment(Batiment batiment) {
		try {
			DAO<Batiment> batimentDao = DAOFactory.getBatimentDAO();
			batimentDao.create(batiment);

		} catch (Exception e) {
			e.getMessage();
		}
		return batiment;
	}
	
	//Supression d'un batiment en base
	public void deleteBatiment(Batiment batiment) {
		try {
			DAO<Batiment> batimentDao = DAOFactory.getBatimentDAO();
			batimentDao.delete(batiment);

		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	//Modification d'un batiment en base
	public void updateBatiment(Batiment batiment){
		try{
			DAO<Batiment> batimentDao = DAOFactory.getBatimentDAO();
			batimentDao.update(batiment);
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	
	// Ajout d'une salle en base
	public Salle ajoutSalle(Salle salle) {
		try {
			DAO<Salle> salleDao = DAOFactory.getSalleDAO();
			salleDao.create(salle);

		} catch (Exception e) {
			e.getMessage();
		}
		return salle;
	}
	
	// Suppression d'une salle en base
	public Salle deleteSalle(Salle salle) {
		try {
			DAO<Salle> salleDao = DAOFactory.getSalleDAO();
			salleDao.delete(salle);

		} catch (Exception e) {
			e.getMessage();
		}
		return salle;
	}
	
	//Ajout d'un nouveau type de salle
	public TypeSalle ajoutType(TypeSalle type){
		try{
			DAO<TypeSalle> typeDao = DAOFactory.getTypeSalleDAO();
			ArrayList<TypeSalle> liste = typeDao.list();
			boolean existe = false;
			
			for(int i=0; i<liste.size(); i++){
				if(liste.get(i).getNom().equals(type.getNom())){
					System.out.println("Ce type de salle existe déjà");
					existe = true;
				}
			}
			
			if(existe == false){
				typeDao.create(type);
			}
		}
		catch(Exception e){
			e.getMessage();
		}
		return type;
	}
	


}
