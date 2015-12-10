package fr.unantes.gestionnaires.interfaces;

import java.util.ArrayList;
import java.util.Date;

import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;

public interface InterfaceReservations {
	
	public void ajouterMateriel(MaterielMobile materiel, int refResa) throws Exception;
	
	public boolean reservationExists(int refResa);
	
	public Reservation getReservation(int refResa) throws Exception;
	
	public ArrayList<Reservation> getReservations(Salle salle);
	
	public ArrayList<Reservation> getReservations(Demandeur demandeur);
	
	public void reserver(long temps,  Demandeur demandeur, Salle salle, Date dateResa, Duree duree, Manifestation manifestation, double prix) throws Exception;

	public void annuler(int refResa) throws Exception;






}
