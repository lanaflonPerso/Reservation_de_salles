package fr.unantes.beans;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {
	private int refResa;
	private Date dateResa;
	private Salle salle;
	private long temps;
	private ArrayList<MaterielMobile> listeMateriels;
	private Duree duree;
	private Manifestation manifestation;
	private Demandeur demandeur;
	
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reservation(int refResa, Date dateResa,
			Salle salle, long temps, ArrayList<MaterielMobile> listeMateriels,
			Duree duree, Manifestation manifestation, Demandeur demandeur) {
		super();
		this.refResa = refResa;
		this.dateResa = dateResa;
		this.salle = salle;
		this.temps = temps;
		this.listeMateriels = listeMateriels;
		this.duree = duree;
		this.manifestation = manifestation;
		this.demandeur = demandeur;
	}
	
	public Reservation(int refResa, Date dateResa,
			Salle salle, long temps, Duree duree, Manifestation manifestation, Demandeur demandeur) {
		super();
		this.refResa = refResa;
		this.dateResa = dateResa;
		this.salle = salle;
		this.temps = temps;
		this.listeMateriels = new ArrayList<MaterielMobile>();
		this.duree = duree;
		this.manifestation = manifestation;
		this.demandeur = demandeur;
	}

	

	public int getRefResa() {
		return refResa;
	}


	public void setRefResa(int refResa) {
		this.refResa = refResa;
	}


	public Date getDateResa() {
		return dateResa;
	}


	public void setDateResa(Date dateResa) {
		this.dateResa = dateResa;
	}


	public Salle getSalle() {
		return salle;
	}


	public void setSalle(Salle salle) {
		this.salle = salle;
	}


	public long getTemps() {
		return temps;
	}


	public void setTemps(long temps) {
		this.temps = temps;
	}


	public ArrayList<MaterielMobile> getListeMateriels() {
		return listeMateriels;
	}


	public void setListeMateriels(ArrayList<MaterielMobile> listeMateriels) {
		this.listeMateriels = listeMateriels;
	}


	public Duree getDuree() {
		return duree;
	}


	public void setDuree(Duree duree) {
		this.duree = duree;
	}


	public Manifestation getManifestation() {
		return manifestation;
	}


	public void setManifestation(Manifestation manifestation) {
		this.manifestation = manifestation;
	}


	public Demandeur getDemandeur() {
		return demandeur;
	}


	public void setDemandeur(Demandeur demandeur) {
		this.demandeur = demandeur;
	}

	/**
	 * Méthode qui permet d'ajouter un materiel à une réservation.
	 * @param materiel le materiel à ajouter.
	 * @throws Exception si le materiel est indisponible.
	 */
	public void ajouterMateriel(MaterielMobile materiel) throws Exception{
		if(!materiel.disponible()){
			throw new Exception("materiel indisponible");
		}
		materiel.setReservation(this);
		this.listeMateriels.add(materiel);
	}
	
	/**
	 * Permet de retirer un materiel à une réservation.
	 * @param materiel le materiel à retirer.
	 * @throws Exception
	 */
	public void retirerMateriel(MaterielMobile materiel) throws Exception{
		if(this.listeMateriels.isEmpty()){
			throw new Exception("Cette réservation ne contient aucun materiel");
		}
		boolean retirer = false;
		for(MaterielMobile each : this.listeMateriels){
			if(materiel.equals(each)){
				retirer = true;
			}
		}
		if(retirer){
			materiel.setReservation(null);
			this.listeMateriels.remove(materiel);
		}
	}
	
	public void reserver(Salle salle) throws Exception{
		if(salle.disponible(this.dateResa, this.temps)){
			salle.ajoutReservation(this);
		}
	}
	
	/**
	 * Annule une réservation.
	 * @throws Exception 
	 */
	public void annuler() throws Exception{
		for(MaterielMobile each : this.listeMateriels){
			each.setReservation(null);
		}
	}
	
	/**
	 * Calcul le début de la réservation en millisecondes depuis 1970.
	 * @return le debut de la réservation (en millisecondes)
	 */
	public long debut(){
		return this.dateResa.getTime();
	}
	
	/**
	 * Calcule le tarif de la réservation 
	 * @return le tarif total de la réservation
	 */
	public double calculTarif(){
		return this.manifestation.getTarif() + this.duree.getTarif() + this.salle.calculerTarif() + this.demandeur.calculTarif();
	}
}
