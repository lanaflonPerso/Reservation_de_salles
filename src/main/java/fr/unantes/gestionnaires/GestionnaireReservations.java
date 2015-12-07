package fr.unantes.gestionnaires;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeMateriel;

public class GestionnaireReservations {

	private static volatile GestionnaireReservations instance = null;

	private static ArrayList<Reservation> listeReservation;
	private static int ref_resa;
	
	
	
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
		ref_resa = 0;
		listeReservation = new ArrayList<Reservation>();
	}
	
	

	public static ArrayList<Reservation> getListeReservation() {
		return listeReservation;
	}

	public static void setListeReservation(ArrayList<Reservation> listeReservation) {
		GestionnaireReservations.listeReservation = listeReservation;
	}

	/**
	 * renvoie true si la salle est disponible pour une réservation à une date et pour une durée précise.
	 * @param salle la salle disponible ou non.
	 * @param date la date à laquelel on veut savoir la disponibilité.
	 * @param duree la durée pour laquelle on veut savoir si la salle est disponible.
	 * @return true si la salle est disponible, false sinon.
	 * On compare les dates et durées de réservation pour savoir si la salle possède déjà une réservation pour un créneau donné.
	 */
	public boolean salleDispo(Salle salle, Date date, long duree) throws Exception{
		if(duree < 0){
			throw new Exception("Durée négative");
		}
		ArrayList<Reservation> liste = salle.getListeReservation();
		if(liste.isEmpty()){
			return true;
		}
		
		//Créneaux de notre nouvelle réservation
		long debut_creation = date.getTime();
		long fin_creation = date.getTime() + duree;

		for(int i=0; i<liste.size(); i++){
			//Créneau d'une réservation déjà existante
			long fin_existante = liste.get(i).getDate_resa().getTime() + liste.get(i).getTemps();
			long debut_existante = liste.get(i).getDate_resa().getTime();

			//Si la nouvelle réservation se finit après le début d'une ancienne
			if(fin_creation >= debut_existante && debut_creation <= debut_existante){
				return false;
			}
			//Si la nouvelle réservation commence avant la fin d'une ancienne
			if(debut_creation <= fin_existante && fin_creation >= fin_existante){
				return false;
			}
			//Si la nouvelle réservation est en plein dans une réservation existante
			if(debut_creation >= debut_existante && fin_creation <= fin_existante){
				return false;
			}
		}	
		return true;
	}
	
	
	/**
	 * 
	 * @param ref_resa
	 * @param demandeur
	 * @param salle
	 * @param date_resa
	 * @param duree
	 * @param manifestation
	 * @param tarif
	 * @throws Exception
	 */
	public void reserver(long temps,  Demandeur demandeur, Salle salle, Date date_resa, Duree duree, Manifestation manifestation, double prix) throws Exception{
		if(!salleDispo(salle, date_resa, temps)){
			throw new Exception("Salle indisponible");
		}
		this.ref_resa ++;
		if(reservationExists(ref_resa)){
			throw new Exception("Réservation déjà existante");
		}
		Reservation reservation = new Reservation(ref_resa, date_resa, prix, salle, temps, duree, manifestation, demandeur);
		salle.ajoutReservation(reservation);
		listeReservation.add(reservation);
	}
	
	/**
	 * 
	 * @param idReserv le numero de reservation
	 * Renvoie la réservation 
	 */
	public Reservation rechercheReservation(int ref_resa) throws Exception{
		for (Reservation reserv: listeReservation){
			if(reserv.getRef_resa() == ref_resa){
				return reserv;
			}
		}
		throw new Exception("Aucune réservation avec ce numero");
	}
	
	/**
	 * 
	 * @param demandeur
	 * @return
	 */
	public ArrayList<Reservation> rechercheReservationParDemandeur(Demandeur demandeur){
		return demandeur.getListeReservation();
	}
	
	/**
	 * 
	 * @param salle
	 * @return
	 */
	public ArrayList<Reservation> rechercheReservationParSalle(Salle salle){
		return salle.getListeReservation();
	}
	
	/**
	 * 
	 * @param ref_resa
	 * @throws Exception
	 */
	public void annuler(int ref_resa) throws Exception{
		if(!reservationExists(ref_resa)){
			throw new Exception("Cette réservation n'existe pas");
		}	
		Reservation reservation = rechercheReservation(ref_resa);
		reservation.annuler();
		listeReservation.remove(reservation);
		
	}	
	
	/**
	 * 
	 * @param ref_resa
	 * @return
	 */
	public boolean reservationExists(int ref_resa){
		for(int i=0; i<listeReservation.size(); i++){
			if(listeReservation.get(i).getRef_resa() == ref_resa){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param code_inv le code du materiel 
	 * @return true si la materiel existe, false sinon
	 */
	public boolean materielExists(int code_inv){
		for(int i=0; i<listeReservation.size(); i++){
			for(int j=0; j<listeReservation.get(i).getListeMateriels().size(); j++){
				if(listeReservation.get(i).getListeMateriels().get(j).getCode_inv() == code_inv){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
	


	

	


	
	/**
	 * 
	 * @param reservation
	 * @param code_inv
	 * @throws Exception
	 */
	public void ajoutMaterielMobile(Reservation reservation, int code_inv) throws Exception{
		if(!materielExists(code_inv)){
			throw new Exception("Materiel inexistant");
		}
		//reservation.ajoutMateriel(materiel);
	}

	
}
