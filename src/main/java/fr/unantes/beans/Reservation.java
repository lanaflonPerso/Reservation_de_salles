package fr.unantes.beans;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {
	private int refResa;
	private Date dateResa;
	private double montant;
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


	public Reservation(int refResa, Date dateResa, double montant,
			Salle salle, long temps, ArrayList<MaterielMobile> listeMateriels,
			Duree duree, Manifestation manifestation, Demandeur demandeur) {
		super();
		this.refResa = refResa;
		this.dateResa = dateResa;
		this.montant = montant;
		this.salle = salle;
		this.temps = temps;
		this.listeMateriels = listeMateriels;
		this.duree = duree;
		this.manifestation = manifestation;
		this.demandeur = demandeur;
	}
	
	public Reservation(int refResa, Date dateResa, double montant,
			Salle salle, long temps, Duree duree, Manifestation manifestation, Demandeur demandeur) {
		super();
		this.refResa = refResa;
		this.dateResa = dateResa;
		this.montant = montant;
		this.salle = salle;
		this.temps = temps;
		this.listeMateriels = listeMateriels;
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


	public double getMontant() {
		return montant;
	}


	public void setMontant(double montant) {
		this.montant = montant;
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


	public void ajouterMateriel(MaterielMobile materiel){
		this.listeMateriels.add(materiel);
	}
	
	public void retirerMateriel(MaterielMobile materiel){
		this.listeMateriels.remove(materiel);
	}
	
	public void annulerReservation(){
		this.salle.retirerReservation(this);
	}
	
	public double calculTarif(){
		return this.manifestation.getTarif() + this.duree.getTarif();
	}
}
