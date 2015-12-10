package fr.unantes.gestionnaires.interfaces;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Titre;

public interface InterfaceDemandeurs {

	/**
	 * 
	 * @param noDem le numéro du demandeur à rechercher
	 * @return true si le demandeur existe, false sinon
	 */
	public boolean demandeurExists(int noDem);
	
	/**
	 * 
	 * @param noDem le demandeur à rechercher
	 * @return le demandeur correspondant ua numero
	 * @throws Exception si le demandeur n'existe pas
	 */
	public Demandeur getDemandeur(int noDem) throws Exception;
	
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
	 * @param noDem le numéro du demandeur à créer.
	 * @param nom le nom du demandeur.
	 * @param adresse l'adresse du demandeur.
	 * @param origine l'origine du demandeur.
	 * @param titre le titre du demandeur.
	 * @throws Exception si le demandeur est déjà existant
	 */
	public void ajouterDemandeur(int noDem, String nom, Adresse adresse, Origine origine, Titre titre) throws Exception;
	
	/**
	 * 
	 * @param noDem le numero du demandeur à supprimer
	 * @throws Exception si le demandeur n'existe pas
	 */
	public void supprimerDemandeur(int noDem) throws Exception;
	
	/**
	 * 
	 * @param noDem le numero du demandeur à modifier
	 * @param adresse l'adresse à modifier
	 * @throws Exception si le demandeur n'existe pas
	 */
	public void modifierDemandeur(int noDem, Adresse adresse) throws Exception;
}
