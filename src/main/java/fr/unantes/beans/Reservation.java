package fr.unantes.beans;

import java.util.ArrayList;
import java.util.Date;

public class Reservation {
	private String ref_resa;
	private Date date_resa;
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


	public Reservation(String ref_resa, Date date_resa, double montant,
			Salle salle, long temps, ArrayList<MaterielMobile> listeMateriels,
			Duree duree, Manifestation manifestation, Demandeur demandeur) {
		super();
		this.ref_resa = ref_resa;
		this.date_resa = date_resa;
		this.montant = montant;
		this.salle = salle;
		this.temps = temps;
		this.listeMateriels = listeMateriels;
		this.duree = duree;
		this.manifestation = manifestation;
		this.demandeur = demandeur;
	}
	
	public Reservation(String ref_resa, Date date_resa, double montant,
			Salle salle, long temps, Duree duree, Manifestation manifestation, Demandeur demandeur) {
		super();
		this.ref_resa = ref_resa;
		this.date_resa = date_resa;
		this.montant = montant;
		this.salle = salle;
		this.temps = temps;
		this.listeMateriels = listeMateriels;
		this.duree = duree;
		this.manifestation = manifestation;
		this.demandeur = demandeur;
	}


	public String getRef_resa() {
		return ref_resa;
	}


	public void setRef_resa(String ref_resa) {
		this.ref_resa = ref_resa;
	}


	public Date getDate_resa() {
		return date_resa;
	}


	public void setDate_resa(Date date_resa) {
		this.date_resa = date_resa;
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

	

	public Demandeur getDemandeur() {
		return demandeur;
	}


	public void setDemandeur(Demandeur demandeur) {
		this.demandeur = demandeur;
	}


	public void setManifestation(Manifestation manifestation) {
		this.manifestation = manifestation;
	}

	public void ajoutMateriel(MaterielMobile materiel){
		this.listeMateriels.add(materiel);
	}
	
	public double calculTarif(){
		return this.manifestation.getTarif() + this.duree.getTarif() + montant;
	}
}
