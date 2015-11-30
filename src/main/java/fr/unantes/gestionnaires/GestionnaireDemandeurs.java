package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Titre;

public class GestionnaireDemandeurs {
	
	private ArrayList<Demandeur> listeDemandeurs = new ArrayList();
	
	/**
	 * 
	 * @param no_dem
	 * @return
	 */
	public boolean demandeurExists(int no_dem){
		boolean exists = false;
		for(int i=0; i<this.listeDemandeurs.size(); i++){
			if(this.listeDemandeurs.get(i).getNo_dem() == no_dem){
				exists = true;
			}
		}
		return exists;
	}
	
	/**
	 * @param titre
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Demandeur> rechercheDemandeurParTitre(Titre titre) throws Exception{
		ArrayList<Demandeur> liste = new ArrayList();
		
		for(int i=0; i<listeDemandeurs.size(); i++){
			if(titre.equals(listeDemandeurs.get(i).getTitre())){
				liste.add(listeDemandeurs.get(i));
			}
		}
		if(liste.isEmpty()){
			throw new Exception("Aucun demandeur avec ce titre");
		}
		return liste;
	}
	
	/**
	 * 
	 * @param no_dem
	 * @return
	 * @throws Exception
	 */
	public Demandeur rechrcheDemandeurParNumero(int no_dem) throws Exception{
		for(int i=0; i<listeDemandeurs.size(); i++){
			if(listeDemandeurs.get(i).getNo_dem() == no_dem){
				return listeDemandeurs.get(i);
			}
		}
		throw new Exception("Aucun demandeur avec ce numéro");
	}
	
	/**
	 * 
	 * @param no_dem
	 * @param nom
	 * @param adresse
	 * @param origine
	 * @param titre
	 * @throws Exception
	 */
	public void nouveauDemandeur(int no_dem, String nom, Adresse adresse, Origine origine, Titre titre) throws Exception{
		if(no_dem < 0){
			throw new Exception("Numéro incorrect");
		}
		else if(nom.equals("")){
			throw new Exception("nom vide");
		}
		else if(adresse.equals(null)){
			throw new Exception("Mais où habite t il ?");
		}
		else{
			Demandeur demandeur = new Demandeur(no_dem, nom, adresse, origine, titre);
			this.listeDemandeurs.add(demandeur);
		}
	}
	
	/**
	 * 
	 * @param demandeur
	 * @throws Exception
	 */
	public void supprimerDemandeur(Demandeur demandeur) throws Exception{
		if(!demandeurExists(demandeur.getNo_dem())){
			throw new Exception("Demandeur innexistant");
		}
		if(!demandeur.getListeReservation().isEmpty()){
			for(Reservation reservation : demandeur.getListeReservation()){
				//TODO : si l'evenement est en cours
			}
		}
		else{
			listeDemandeurs.remove(demandeur);
		}
	}
}
