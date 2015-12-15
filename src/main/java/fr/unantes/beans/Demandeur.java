package fr.unantes.beans;

import java.util.ArrayList;


public class Demandeur {
	
	private int noDem;
	private String nom;
	private Adresse adresse;
	private Origine origine;
	private Titre titre;
	private ArrayList<Reservation> listeReservation;
	
	
	public Demandeur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demandeur(int noDem, String nom, Adresse adresse, Origine origine,
			Titre titre, ArrayList<Reservation> listeReservation) {
		super();
		this.noDem = noDem;
		this.nom = nom;
		this.adresse = adresse;
		this.origine = origine;
		this.titre = titre;
		this.listeReservation = listeReservation;
	}

	public Demandeur(int noDem, String nom, Adresse adresse,
			Origine origine, Titre titre) {
		// TODO Auto-generated constructor stub
		super();
		this.noDem = noDem;
		this.nom = nom;
		this.adresse = adresse;
		this.origine = origine;
		this.titre = titre;
	}

	public int getNoDem() {
		return noDem;
	}

	public void setNoDem(int noDem) {
		this.noDem = noDem;
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
	
	public void annulerReservation(Reservation reservation){
		this.listeReservation.remove(reservation);
	}
	
	public void annulerReservations(){
		for(Reservation each : this.listeReservation){
			each.annulerReservation();
		}
	}
	
	public double calculTarif(){
		return this.origine.getTarif() + this.titre.getTarif();
	}
}
