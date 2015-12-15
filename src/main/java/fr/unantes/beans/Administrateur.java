package fr.unantes.beans;

import java.util.ArrayList;



public class Administrateur extends Demandeur {

	private final String motDePasse = "admin";
	private final String login = "admin";

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




}
