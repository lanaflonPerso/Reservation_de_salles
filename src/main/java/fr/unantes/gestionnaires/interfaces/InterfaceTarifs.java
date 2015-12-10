package fr.unantes.gestionnaires.interfaces;

import java.util.ArrayList;

import fr.unantes.beans.Reservation;
import fr.unantes.beans.Tarif;
import fr.unantes.beans.TarifEnumeration;

public interface InterfaceTarifs {

	/**
	 * vérifie si un tarif existe grâce à son code tarifaire
	 * @param code le code unique d'un tarif
	 * @return true si le tarif existe, false sinon
	 */
	public boolean tarifExists(int code);
	
	/**
	 * recherche le tarif correspondant au code tarifaire passé en paramètre
	 * @param code le code du tarif
	 * @return le tarif correspondant au code tarifaire
	 * @throws Exception si le code tarifaire ne correspond a aucun tarif
	 */
	public Tarif getTarif(int code) throws Exception;
	
	/**
	 * recherche les tarifs selon le type de tarif
	 * @param type le type de tarif à rechercher
	 * @return la liste des tarifs dont le type tarifaire ets égal à  type
	 */
	public ArrayList<Tarif> getTarifs(TarifEnumeration type);
	
	/**
	 * ajoute un tarif au système
	 * @param code le code tarifaire
	 * @param libelle le libelle du tarif
	 * @param tarif le prix 
	 * @param type le type de tarif
	 * @throws Exception si le tarif existe déjà ou si le prix est négatif
	 */
	public void ajouterTarif(int code, String libelle, double tarif, TarifEnumeration type) throws Exception;

	/**
	 * supprime un tarif à partie de son code tarifaire
	 * @param code le code tarifaire
	 * @throws Exception si le tarif n'existe pas
	 */
	public void supprimerTarif(int code) throws Exception;
	
	/**
	 * modifie le nom et le prix d'un tarif
	 * @param code le code tarifaire
	 * @param libelle libelle à modifier
	 * @param tarif montant du tarif à modifier
	 * @throws Exception si le tarif n'existe pas ou si le prix est négatif
	 */
	public void modifierTarif(int code, String libelle, double tarif) throws Exception;
	
	/**
	 * Calcul le prix d'une réseervation
	 * @param refResa la référence de la réservation
	 * @return le prix de la réservation
	 */
	public double calculTarif(Reservation reservation);


}
