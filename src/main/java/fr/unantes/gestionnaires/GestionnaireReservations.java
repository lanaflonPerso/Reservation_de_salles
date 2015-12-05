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

	private static ArrayList<Reservation> listeReserv;
	private static String refReserv;
	
	
	
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
		refReserv = "1";
		listeReserv = new ArrayList<Reservation>();
		
	}

	
	/**
	 * 
	 * @param code_inv le code du materiel 
	 * @return true si la materiel existe, false sinon
	 */
	public boolean materielExists(int code_inv){
		//On recherche dans toutes les salles de tous les batiments
		for(int i=0; i<listeReserv.size(); i++){
			for(int j=0; j<listeReserv.get(i).getListeMateriels().size(); j++){
				if(listeReserv.get(i).getListeMateriels().get(j).getCode_inv() == code_inv){
					return true;
				}
			}
		}
		return false;
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
			//Si la nouvelel réservation est en plein dans une réservation existante
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
	public void reserver(String ref_resa, long temps,  Demandeur demandeur, Salle salle, Date date_resa, Duree duree, Manifestation manifestation, double prix) throws Exception{
		if(!salleDispo(salle, date_resa, temps)){
			throw new Exception("Salle indisponible");
		}
		Reservation reservation = new Reservation(ref_resa, date_resa, prix, salle, temps, duree, manifestation, demandeur);
		salle.ajoutReservation(reservation);
		listeReserv.add(reservation);
	}
	

	/**
	 * 
	 * @param idReserv le numero de reservation
	 * Renvoie la réservation 
	 */
	public Reservation ConsultationReserv(String idReserv) throws Exception{
		for (Reservation reserv: listeReserv){
			if(reserv.getRef_resa().equals(idReserv)){
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
	public ArrayList<Reservation> ConsultationReserv(Demandeur demandeur){
		ArrayList<Reservation> liste = new ArrayList();
		for(Reservation reserv :demandeur.getListeReservation()){
			liste.add(reserv);
		}
		return liste;
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public ArrayList<Reservation> ConsultationReserv(Date date){
		ArrayList<Reservation> liste = new ArrayList();
		for (Reservation reserv: listeReserv){
			if(reserv.getDate_resa().equals(date)){
				liste.add(reserv);
			}
		}
		return liste;
	}
	
	/**
	 * 
	 * @param salle
	 * @return
	 */
	public ArrayList<Reservation> ConsultationReserv(Salle salle){
		return salle.getListeReservation();
	}

	/**
	 * 
	 * @param demandeur
	 * @param reservation
	 * @throws Exception
	 */
	public void AnnulationReserv(Demandeur demandeur, Reservation reservation) throws Exception{
		for(int i=0; i<demandeur.getListeReservation().size(); i++){
			if(demandeur.getListeReservation().get(i).equals(reservation)){
				demandeur.annulerReservation(reservation);
			}
		}
		throw new Exception("Cette réservation n'appartient pas à ce demandeur");
		
	}	
	
	/**
	 * 
	 * @param code_inv
	 * @param nom
	 * @param type
	 * @throws Exception
	 */
	public void ajoutMaterielMobile(int code_inv, String nom, Reservation reservation, TypeMateriel type) throws Exception{
		if(materielExists(code_inv)){
			throw new Exception("Materiel déjà existant");
		}
		MaterielMobile materiel = new MaterielMobile(code_inv, nom, type);
		reservation.ajoutMateriel(materiel);
	}

	
}
