package fr.unantes.gestionnaires;

import java.util.ArrayList;
import java.util.Date;


import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.gestionnaires.interfaces.InterfaceReservations;

public class GestionnaireReservations implements InterfaceReservations{

	private static volatile GestionnaireReservations instance = null;

	private ArrayList<Reservation> listeReservation;
	private int refResa;
	
	/**
	 * MÃ©thode qui crÃ©er une instance de GestionReservation s'il n'en existe pas et retourne l'instance existante sinon (il ne peut y avoir qu'une seul instance de la classe GestionReservation 
	 * @return instance de la class GestionReservation
	 * 
	 */
	public final static GestionnaireReservations getInstance(){
		
		if (GestionnaireReservations.instance == null){
			synchronized(GestionnaireReservations.class){
				if(GestionnaireReservations.instance==null){
					GestionnaireReservations.instance = new GestionnaireReservations();
				}			
			}
		}
		return GestionnaireReservations.instance;
	}
	
	private GestionnaireReservations(){
		refResa = 0;
		listeReservation = new ArrayList<Reservation>();
	}

	public ArrayList<Reservation> getListeReservation() {
		return listeReservation;
	}

	public void setListeReservation(ArrayList<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}


	/**
	 * Ajouter un matériel mobile à une réservation
	 * @param materiel le materiel a ajouter
	 * @param refResa le code de réservation
	 * @exception si le materiel possède déjà une réservation
	 */
	@Override
	public void ajouterMateriel(MaterielMobile materiel, int refResa) throws Exception {
		// TODO Auto-generated method stub
		if(!materiel.getReservation().equals(null)){
			throw new Exception("materiel déjà utilisé");
		}
		getReservation(refResa).ajouterMateriel(materiel);
	}
	
	/**
	 * 
	 * @param refResa le numero de reservation.
	 * Renvoie la réservation .
	 */
	@Override
	public Reservation getReservation(int refResa) throws Exception{
		// TODO Auto-generated method stub
		for(Reservation each : listeReservation){
			if(each.getRefResa() == refResa){
				return each;
			}
		}
		throw new Exception("Aucune réservation avec ce numero");
	}

	@Override
	public ArrayList<Reservation> getReservations(Salle salle) {
		// TODO Auto-generated method stub
		return salle.getListeReservation();
	}

	@Override
	public ArrayList<Reservation> getReservations(Demandeur demandeur) {
		// TODO Auto-generated method stub
		return demandeur.getListeReservation();
	}

	/**
	 * 
	 * @param temps
	 * @param demandeur
	 * @param salle
	 * @param dateResa
	 * @param duree
	 * @param manifestation
	 * @param prix
	 * @throws Exception
	 */
	@Override
	public void reserver(long temps, Demandeur demandeur, Salle salle,
			Date dateResa, Duree duree, Manifestation manifestation)
			throws Exception {
		// TODO Auto-generated method stub
		if(!salle.disponible(dateResa, temps)){
			throw new Exception("Salle indisponible");
		}
		this.refResa ++;
		if(reservationExists(refResa)){
			throw new Exception("Réservation déjà existante");
		}
		Reservation reservation = new Reservation(refResa, dateResa, salle, temps, duree, manifestation, demandeur);
		salle.ajoutReservation(reservation);
		listeReservation.add(reservation);
	}

	/**
	 * @param refResa
	 */
	@Override
	public void annuler(int refResa) throws Exception {
		// TODO Auto-generated method stub
		if(!reservationExists(refResa)){
			throw new Exception("Cette réservation n'existe pas");
		}	
		Reservation reservation = getReservation(refResa);
		reservation.annuler();
		listeReservation.remove(reservation);
	
	}

	@Override
	public boolean reservationExists(int refResa) {
		// TODO Auto-generated method stub
		for(Reservation each : listeReservation){
			if(each.getRefResa() == refResa){
				return true;
			}
		}
		return false;
	}

	
}
