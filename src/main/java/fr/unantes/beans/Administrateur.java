package fr.unantes.beans;

import java.util.ArrayList;



public class Administrateur extends Demandeur {

	private final String PASSWORD = "admin";
	private final String LOGIN = "admin";

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

	/**
	 * Identifie l'administrateur
	 * @param login
	 * @param motDePasse
	 * @return
	 */
	public boolean identification(String login, String motDePasse) {
		if (login.equals(this.LOGIN) && motDePasse.equals(this.PASSWORD)) {
			return true;
		}
		return false;
	}

	public void supprimerSalle(Salle salle){

	}


}
