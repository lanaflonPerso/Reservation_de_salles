package fr.unantes.beans;

import java.util.ArrayList;
import java.util.Date;

import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class Demandeur {
	
	private int no_dem;
	private String nom;
	private Adresse adresse;
	private Origine origine;
	private Titre titre;
	private ArrayList<Reservation> listeReservation;
	
	
	public Demandeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demandeur(int no_dem, String nom, Adresse adresse, Origine origine,
			Titre titre, ArrayList<Reservation> listeReservation) {
		super();
		this.no_dem = no_dem;
		this.nom = nom;
		this.adresse = adresse;
		this.origine = origine;
		this.titre = titre;
		this.listeReservation = listeReservation;
	}
	
	public Demandeur(int no_dem, String nom, Adresse adresse, Origine origine,
			Titre titre) {
		super();
		this.no_dem = no_dem;
		this.nom = nom;
		this.adresse = adresse;
		this.origine = origine;
		this.titre = titre;
	}

	public int getNo_dem() {
		return no_dem;
	}

	public void setNo_dem(int no_dem) {
		this.no_dem = no_dem;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Origine getOrigine() {
		return origine;
	}

	public void setOrigine(Origine origine) {
		this.origine = origine;
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
	}

	public ArrayList<Reservation> getListeReservation() {
		return listeReservation;
	}

	public void setListeReservation(ArrayList<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}
	
	public double tarifOrigine(){
		return origine.getTarif();
	}
	
	public double tarifTitre(){
		return titre.getTarif();
	}
	
}
