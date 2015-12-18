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
	
	/**
	 * Methode servant à ajouter un materiel mobile à une réservation.
	 * @param materiel le materiel à ajouter.
	 * @param refResa le code de réservation.
	 * @throws Exception si le materiel est déjà utilisé pour une autre réservation.
	 */
	public void ajouterMateriel(MaterielMobile materiel, int refResa) throws Exception;
	
	/**
	 * Methode servant à enlever un materiel à une réservation.
	 * @param materiel le materiel à enlever.
	 * @param refResa le code de réservation.
	 * @throws Exception si le materiel n'appartient pas à cette réservation.
	 */
	public void retirerMateriel(MaterielMobile materiel, int refResa) throws Exception;
	
	/**
	 * Méthode servant à savoir si une réservation existe selon son id.
	 * @param refResa le code de réservation.
	 * @return
	 */
	public boolean reservationExists(int refResa);
	
	/**
	 * Méthode servant à obtenir une réservation à partir de son code de réservation.
	 * @param refResa le code de réservation.
	 * @return la réservation correspondant au code de réservation.
	 * @throws Exception si la réservation n'existe pas.
	 */
	public Reservation getReservation(int refResa) throws Exception;
	
	/**
	 * Méthode servant à obtenir toutes les réservations d'une salle.
	 * @param salle la salle en question.
	 * @return la liste des réservation de la salle.
	 */
	public ArrayList<Reservation> getReservations(Salle salle);
	
	/**
	 * Méthode servant à obtenir toutes les réservations d'un demandeur.
	 * @param demandeur le demandeur en question.
	 * @return la liste des réservations de ce demanderu.
	 */
	public ArrayList<Reservation> getReservations(Demandeur demandeur);
	
	/**
	 * Méthode servant à créer une réservation.
	 * @param temps la durée de la réservation.
	 * @param demandeur le demandeur qui passe la réservation.
	 * @param salle la salle à réserver.
	 * @param dateResa la date de la réservation.
	 * @param duree le tarif selon la durée.
	 * @param manifestation le tarif selon la manifestation.
	 * @return la réservation créée.
	 * @throws Exception si la salle est indisponible ou si le demandeur à déjà passé une réservation dans les 24h ou si la réservation existe déjà.
	 */
	public Reservation reserver(long temps,  Demandeur demandeur, Salle salle, Date dateResa, Duree duree, Manifestation manifestation) throws Exception;

	/**
	 * Méthode servant à annuler une réservation.
	 * @param refResa le code de la réservation à annuler.
	 * @throws Exception si la réservation n'existe pas.
	 */
	public void annuler(int refResa) throws Exception;






}
