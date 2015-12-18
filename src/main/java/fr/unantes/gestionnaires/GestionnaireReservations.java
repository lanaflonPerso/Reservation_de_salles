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
		if(!(materiel.getReservation()==null)){
			throw new Exception("materiel déjà utilisé");
		}
		getReservation(refResa).ajouterMateriel(materiel);
	}
	
	/**
	 * Retire un materiel mobile à une réservation.
	 * @param materiel Le materiel à retirer.
	 * @param refResa la réservation.
	 */
	@Override
	public void retirerMateriel(MaterielMobile materiel, int refResa)
			throws Exception {
		// TODO Auto-generated method stub
		getReservation(refResa).retirerMateriel(materiel);
	}
	
	/**
	 * @param refResa le numero de reservation.
	 * @return la réservation recherchée.
	 * @exceptuion si la réservation n'existe pas.
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

	/**
	 * Méthode servant à obtenir toutes les réservations d'une salle.
	 * @param salle la salle en question.
	 * @return la liste des réservation de la salle.
	 */
	@Override
	public ArrayList<Reservation> getReservations(Salle salle) {
		// TODO Auto-generated method stub
		return salle.getListeReservation();
	}

	/**
	 * Méthode servant à obtenir toutes les réservations d'un demandeur.
	 * @param demandeur le demandeur en question.
	 * @return la liste des réservations de ce demanderu.
	 */
	@Override
	public ArrayList<Reservation> getReservations(Demandeur demandeur) {
		// TODO Auto-generated method stub
		return demandeur.getListeReservation();
	}

	/**
	 * 
	 * @param temps Le temps que dure la réservation.
	 * @param demandeur Le demandeur qui fait la réservation.
	 * @param salle La salle à réserver.
	 * @param dateResa la date à laquelle on veut passer la réservation.
	 * @param duree le tarif Duree de la réservation.
	 * @param manifestation le tarif Manifestation de la réservation.
	 * @throws Exception si salle indisponible, si le demandeur à déjà passé une réservatio ndans les 24h ou si la réservation existe déjà.
	 */
	@Override
	public Reservation reserver(long temps, Demandeur demandeur, Salle salle,
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
		demandeur.reserver(reservation);
		salle.ajoutReservation(reservation);
		reservation.reserver(salle);
		listeReservation.add(reservation);
		return reservation;
	}

	/**
	 * Méthode servant à annuler une réservation.
	 * @param refResa le code de la réservation à annuler.
	 * @throws Exception si la réservation n'existe pas.
	 */
	@Override
	public void annuler(int refResa) throws Exception {
		// TODO Auto-generated method stub
		if(!reservationExists(refResa)){
			throw new Exception("Cette réservation n'existe pas");
		}	
		Reservation reservation = getReservation(refResa);
		reservation.getSalle().retirerReservation(reservation);
		reservation.getDemandeur().annulerReservation(reservation);
		listeReservation.remove(reservation);
	
	}

	/**
	 * Méthode servant à savoir si une réservation existe selon son id.
	 * @param refResa le code de réservation.
	 * @return
	 */
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
