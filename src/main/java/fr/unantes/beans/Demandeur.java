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
		this.listeReservation = new ArrayList<Reservation>();
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
	
	/**
	 * Ajoute une réservation à la liste des réservations du demandeur.
	 * @param reservation la réservation à ajouter.
	 * @throws Exception si le demandeur possède déjà une réservation +/- dans les 24h.
	 */
	public void reserver(Reservation reservation) throws Exception{
		for(Reservation each : this.listeReservation){
			if(each.equals(reservation)){
				throw new Exception("Réservation déjà existante");
			}
			if(each.debut() <= reservation.debut() && each.debut() +86400000 >= reservation.debut()){
				throw new Exception("Vous ne pouvez pas réserver deux salles dans la même journée.");
			}
			if(each.debut() >= reservation.debut() && each.debut()+86400000 <= reservation.debut()+86400000){
				throw new Exception("Vous ne pouvez pas réserver deux salles dans la même journée.");
			}
			if(each.debut()<=reservation.debut()+86400000 && each.debut()+86400000 >= reservation.debut()+86400000){
				throw new Exception("Vous ne pouvez pas réserver deux salles dans la même journée.");
			}
		}
		this.listeReservation.add(reservation);
	}
	
	
	/**
	 * Annule la réservation passée en paramètre
	 * @param reservation la réservation à annuler
	 * @throws Exception si la réservation n'appartient pas au demandeur
	 */
	public void annulerReservation(Reservation reservation) throws Exception{
		if(this.listeReservation.isEmpty()){
			throw new Exception("Cette réservation n'appartient pas à ce demandeur");
		}
		boolean trouve = false;
		for(Reservation each : this.listeReservation){
			if(each.equals(reservation)){
				trouve = true;
				break;
			}
		}
		if(trouve){
			this.listeReservation.remove(reservation);
			reservation.annuler();
		}
		
	}
	
	/**
	 * Annule toutes les réservations du demandeur
	 * @throws Exception 
	 */
	public void annulerReservations() throws Exception{
		for(Reservation each : this.listeReservation){
			this.listeReservation.remove(each);
			each.annuler();
		}
	}
	
	/**
	 * Calcul le tarif qu'a ce demandeur
	 * @return le tarif calculé à partie de son origine et de son titre
	 */
	public double calculTarif(){
		return this.origine.getTarif() + this.titre.getTarif();
	}
}
