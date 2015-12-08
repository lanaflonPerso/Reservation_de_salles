package fr.unantes.gestionnaires.interfaces;

import java.util.ArrayList;
import java.util.Date;

import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;

public interface InterfaceReservations {

	public boolean salleDispo(int no_etage, int no_salle, int no_bat, Date date, long duree) throws Exception;
	
	public void ajouterMateriel(int code_inv, int ref_resa) throws Exception;
	
	public boolean reservationExists(int ref_resa);
	
	public Reservation getReservation(int ref_resa);
	
	public ArrayList<Reservation> getReservations(int no_etage, int no_salle, int no_bat);
	
	public ArrayList<Reservation> getReservations(int no_dem);
	
	public void reserver(long temps,  Demandeur demandeur, Salle salle, Date date_resa, Duree duree, Manifestation manifestation, double prix) throws Exception;

	public void annuler(int ref_resa) throws Exception;






}
