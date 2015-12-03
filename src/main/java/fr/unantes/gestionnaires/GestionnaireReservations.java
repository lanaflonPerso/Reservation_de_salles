package fr.unantes.gestionnaires;

import java.util.ArrayList;
import java.util.Date;

import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;

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
	 * MÃ©thode qui calcul le prix d'une reservation spÃ©cifique celon les diffÃ©rents paramÃ¨tre passÃ©s en paramÃ¨tre
	 * @param demand Demandeur qui souhaite calculer le prix d'une reservation 
	 * @param salle Salle qui le demandeur choisis 
	 * @param manif Type de manifestation
	 * @param duree Duree de la manifestation
	 * @param mater MatÃ©riel utilisÃ© 
	 * @return un float reprÃ©sentant le prix d'une reservation en fonction des paramÃ¨tres passÃ©s en entrÃ©
	 */
	public double calculPrixReserv(Demandeur demand,Salle salle,Manifestation manif, Duree duree){
		double prix = demand.tarifOrigine() + demand.tarifTitre();
		//TODO : compléter
		return prix;
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
		
		//Si aucune reservation pour cette salle
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
		Reservation reservation = new Reservation(ref_resa, date_resa, prix, salle, temps, duree, manifestation);
		salle.ajoutReservation(reservation);
		listeReserv.add(reservation);
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	private boolean dispoSalle(Salle s){		
		for(Reservation r:listeReserv){
			if(r.getSalle().compareSalle(s)){
				return false;
			}
		}	
		return true;
	}
	
	
	/**
	 * MÃ©thode qui reserve une salle pour une date et un demandeur passÃ© en paramÃ¨tre
	 * @param demand Demandeur qui souhaite effectuer une reservation
	 * @param salle Salle qui le demandeur souhaite reserver
	 * @param manif type de manifestation
	 * @param duree Duree de la reservation
	 * @param mater MatÃ©riel utilisÃ©
	 * @return une nouvelle reservation si les diffÃ©rentes contraintes ont Ã©tÃ© respectÃ©es et null avec un message d'erreur sinon
	 */
	public Reservation reserveSalle(Demandeur demand,Salle salle,Manifestation manif, Duree duree, long temps){
		if(dispoSalle(salle)){
			
			Reservation reservation = new Reservation(refReserv, new Date(), calculPrixReserv(demand,salle,manif,duree), salle, temps, duree, manif);
		//	refReserv++; //TODO cast
			return reservation;
		
		}
		System.err.println("La salle demandÃ©e n'est pas disponible pour ces dates  ");
		return null;	
	}
	
	
	
	public void ConsultationReserv(String idReserv){
		for (Reservation reserv: listeReserv){
			if(reserv.getRef_resa().equals(idReserv)){
				reserv.toString();
				break;
			}
		}
		System.out.println("Pas de reservation avec ce numÃ©ro de reservation");
		
	}
	
	/**
	 * 
	 * @param d
	 */
	public void ConsultationReserv(Demandeur d){
		for(Reservation reserv :d.getListeReservation()){
			reserv.toString();
		}
		
	}
	
	public void ConsultationReserv(Date d){
		for (Reservation reserv: listeReserv){
			if(reserv.getDate_resa() == d){
				
			}
		}
	}
	
	public void ConsultationReserv(Salle s){
		for (Reservation reserv: listeReserv){
			if (reserv.getSalle() == s){
				reserv.toString();
			}
		}
	}
	
	
	
	public void AnnulationReserv(Demandeur d, Reservation r){
		d.getListeReservation();
		if(!listeReserv.remove(r)){
			System.out.println("ProblÃ¨me: ce n'est pas votre reservation");
		}
		
	}

	
}
