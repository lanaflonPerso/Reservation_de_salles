package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;

import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;

import fr.unantes.beans.Titre;

import fr.unantes.gestionnaires.interfaces.InterfaceDemandeurs;

public class GestionnaireDemandeurs implements InterfaceDemandeurs{
	private static volatile GestionnaireDemandeurs instance = null;
	private ArrayList<Demandeur> listeDemandeurs;
	
	
	/**
	 * 
	 * @return une instance du gestionnaire de demandeurs si elle n'existe pas
	 */
	public final static GestionnaireDemandeurs getInstance(){
		
		if (GestionnaireDemandeurs.instance == null){
			synchronized(GestionnaireDemandeurs.class){
				if(GestionnaireDemandeurs.instance==null){
					GestionnaireDemandeurs.instance = new GestionnaireDemandeurs();
				}
				
			}
		}
		return GestionnaireDemandeurs.instance;
	}
	
	/**
	 * initialise les listes de données
	 */
	private GestionnaireDemandeurs(){
		listeDemandeurs = new ArrayList<Demandeur>();
	}
	
	public ArrayList<Demandeur> getListeDemandeurs() {
		return listeDemandeurs;
	}

	public void setListeDemandeurs(ArrayList<Demandeur> listeDemandeurs) {
		this.listeDemandeurs = listeDemandeurs;
	}

	/**
	 * 
	 * @param noDem le numéro unique du demandeur
	 * @return true si le demandeur existe, false sinon
	 */
	@Override
	public boolean demandeurExists(int noDem) {
		// TODO Auto-generated method stub
		for(Demandeur each : listeDemandeurs){
			if(each.getNoDem() == noDem){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param noDem le numéro de demandeur
	 * @return le demandeur correspondant au noDem
	 * @throws Exception si aucun demandeur n'a ce numero
	 */
	@Override
	public Demandeur getDemandeur(int noDem) throws Exception {
		// TODO Auto-generated method stub
		for(Demandeur each : listeDemandeurs){
			if(each.getNoDem() == noDem){
				return each;
			}
		}
		throw new Exception("Aucun demandeur avec ce numéro");
	}
	

	/**
	 * @param titre le titre des demandeurs à rechercher
	 * @return la liste de demandeurs qui correpondent au titre passé en paramètre
	 */
	@Override
	public ArrayList<Demandeur> getDemandeurs(Titre titre) {
		// TODO Auto-generated method stub
		ArrayList<Demandeur> liste = new ArrayList<Demandeur>();
		for(Demandeur each : listeDemandeurs){
			if(each.getTitre().equals(titre)){
				liste.add(each);
			}
		}
		return liste;
	}

	/**
	 * @param origine l'origine des demandeurs a rechercher
	 * @return la liste de demandeurs qui correpondent a l'origine passée en paramètre
	 */
	@Override
	public ArrayList<Demandeur> getDemandeurs(Origine origine) {
		// TODO Auto-generated method stub
		ArrayList<Demandeur> liste = new ArrayList<Demandeur>();
		for(Demandeur each : listeDemandeurs){
			if(each.getOrigine().equals(origine)){
				liste.add(each);
			}
		}
		return liste;
	}

	/**
	 * 
	 * @param noDem le numero unique du demandeur
	 * @param nom ne nom du demandeur
	 * @param adresse l'adresse du demandeur
	 * @param origine l'origine du demandeur
	 * @param titre le titre du demandeur
	 * @throws Exception si le numero est negatif ou s'il correspond déjà a un autre demandeur
	 */
	@Override
	public void ajouterDemandeur(int noDem, String nom, Adresse adresse,
			Origine origine, Titre titre) throws Exception {
		// TODO Auto-generated method stub
		if(noDem < 0){
			throw new Exception("Numéro incorrect");
		}
		if(demandeurExists(noDem)){
			throw new Exception("Ce demandeur existe déjà");
		}
			Demandeur demandeur = new Demandeur(noDem, nom, adresse, origine, titre);
			this.listeDemandeurs.add(demandeur);
	}

	/**
	 * 
	 * @param noDem
	 * @throws Exception
	 */
	@Override
	public void supprimerDemandeur(int noDem) throws Exception {
		// TODO Auto-generated method stub
		if(!demandeurExists(noDem)){
			throw new Exception("Demandeur innexistant");
		}
		Demandeur demandeur = getDemandeur(noDem);
		demandeur.annulerReservations();
		listeDemandeurs.remove(demandeur);
	}

	/**
	 * 
	 * @param adresse la nouvelle adresse du demandeur
	 * @param noDem le numero du demandeur
	 * @throws Exception si le demandeur n'existe pas
	 */
	@Override
	public void modifierDemandeur(int noDem, Adresse adresse) throws Exception {
		// TODO Auto-generated method stub
		if(!demandeurExists(noDem)){
			throw new Exception("Demandeur innexistant");
		}
		getDemandeur(noDem).setAdresse(adresse);
	}

	
	
}
