package fr.unantes.gestionnaires.interfaces;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Titre;

public interface InterfaceDemandeurs {

	/**
	 * 
	 * @param no_dem le numéro du demandeur à rechercher
	 * @return true si le demandeur existe, false sinon
	 */
	public boolean demandeurExists(int no_dem);
	
	/**
	 * 
	 * @param no_dem le demandeur à rechercher
	 * @return le demandeur correspondant ua numero
	 * @throws Exception si le demandeur n'existe pas
	 */
	public Demandeur getDemandeur(int no_dem) throws Exception;
	
	/**
	 *  Liste les demandeurs par titre
	 * @param titre le titre sur lequel rechercher
	 * @return la liste des demandeurs ayant pour titre le paramètre d'entrée
	 */
	public ArrayList<Demandeur> getDemandeurs(Titre titre);
	
	/**
	 *  Liste les demandeurs par origine
	 * @param origine l'origine sur lequel rechercher
	 * @return la liste des demandeurs ayant pour ogirine le paramètre d'entrée
	 */
	public ArrayList<Demandeur> getDemandeurs(Origine origine);
	
	/**
	 * 
	 * @param no_dem
	 * @param nom
	 * @param adresse
	 * @param origine
	 * @param titre
	 * @throws Exception si le demandeur est déjà existant
	 */
	public void ajouterDemandeur(int no_dem, String nom, Adresse adresse, Origine origine, Titre titre) throws Exception;
	
	/**
	 * 
	 * @param no_dem le numero du demandeur à supprimer
	 * @throws Exception si le demandeur n'existe pas
	 */
	public void supprimerDemandeur(int no_dem) throws Exception;
	
	/**
	 * 
	 * @param no_dem le numero du demandeur à modifier
	 * @param adresse l'adresse à modifier
	 * @throws Exception si le demandeur n'existe pas
	 */
	public void modifierDemandeur(int no_dem, Adresse adresse) throws Exception;
}
