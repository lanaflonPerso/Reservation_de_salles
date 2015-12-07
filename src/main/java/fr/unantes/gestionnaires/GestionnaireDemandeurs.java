package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeSalle;

public class GestionnaireDemandeurs {
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
		listeDemandeurs = new ArrayList();
	}
	
	public ArrayList<Demandeur> getListeDemandeurs() {
		return listeDemandeurs;
	}

	public void setListeDemandeurs(ArrayList<Demandeur> listeDemandeurs) {
		this.listeDemandeurs = listeDemandeurs;
	}
	
	/**
	 * 
	 * @param no_dem le numero unique du demandeur
	 * @param nom ne nom du demandeur
	 * @param adresse l'adresse du demandeur
	 * @param origine l'origine du demandeur
	 * @param titre le titre du demandeur
	 * @throws Exception si le numero est negatif ou s'il correspond déjà a un autre demandeur
	 */
	public void ajoutDemandeur(int no_dem, String nom, Adresse adresse, Origine origine, Titre titre) throws Exception{
		if(no_dem < 0){
			throw new Exception("Numéro incorrect");
		}
		if(demandeurExists(no_dem)){
			throw new Exception("Ce demandeur existe déjà");
		}
			Demandeur demandeur = new Demandeur(no_dem, nom, adresse, origine, titre);
			this.listeDemandeurs.add(demandeur);
	}
	
	/**
	 * 
	 * @param demandeur
	 * @throws Exception
	 */
	public void supprimerDemandeur(int no_dem) throws Exception{
		if(!demandeurExists(no_dem)){
			throw new Exception("Demandeur innexistant");
		}
		listeDemandeurs.remove(rechercheDemandeur(no_dem));
	}
	
	/**
	 * 
	 * @param adresse la nouvelel adresse du demandeur
	 * @param no_dem le numer odu demandeur
	 * @throws Exception si le demandeur n'existe pas
	 */
	public void modifierDemandeur(Adresse adresse, int no_dem) throws Exception{
		if(!demandeurExists(no_dem)){
			throw new Exception("Demandeur innexistant");
		}
		rechercheDemandeur(no_dem).setAdresse(adresse);
	}


	
	/**
	 * @param titre le titre des demandeurs à rechercher
	 * @return la liste de demandeurs qui correpondent au titre passé en paramètre
	 */
	public ArrayList<Demandeur> rechercheDemandeurParTitre(Titre titre){
		ArrayList<Demandeur> liste = new ArrayList();
		
		for(int i=0; i<listeDemandeurs.size(); i++){
			if(titre.equals(listeDemandeurs.get(i).getTitre())){
				liste.add(listeDemandeurs.get(i));
			}
		}
		return liste;
	}
	
	/**
	 * @param origine l'origine des demandeurs a rechercher
	 * @return la liste de demandeurs qui correpondent a l'origine passée en paramètre
	 */
	public ArrayList<Demandeur> rechercheDemandeurParOrigine(Origine origine){
		ArrayList<Demandeur> liste = new ArrayList();
		
		for(int i=0; i<listeDemandeurs.size(); i++){
			if(origine.equals(listeDemandeurs.get(i).getOrigine())){
				liste.add(listeDemandeurs.get(i));
			}
		}
		return liste;
	}
	
	/**
	 * 
	 * @param no_dem le numéro de demandeur
	 * @return le demandeur correspondant au no_dem
	 * @throws Exception si aucun demandeur n'a ce numero
	 */
	public Demandeur rechercheDemandeur(int no_dem) throws Exception{
		for(int i=0; i<listeDemandeurs.size(); i++){
			if(listeDemandeurs.get(i).getNo_dem() == no_dem){
				return listeDemandeurs.get(i);
			}
		}
		throw new Exception("Aucun demandeur avec ce numéro");
	}
	

	/**
	 * 
	 * @param no_dem le numéro unique du demandeur
	 * @return true si le demandeur existe, false sinon
	 */
	public boolean demandeurExists(int no_dem){
		for(int i=0; i<this.listeDemandeurs.size(); i++){
			if(this.listeDemandeurs.get(i).getNo_dem() == no_dem){
				return true;
			}
		}
		return false;
	}

	
	
	
}
