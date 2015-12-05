package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Materiel;
import fr.unantes.beans.MaterielFixe;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeMateriel;


public class GestionnaireMateriels {
	
	private static volatile GestionnaireMateriels instance = null;
	private ArrayList<MaterielFixe> listeFixe;
	private ArrayList<MaterielMobile> listeMobile;
	private ArrayList<TypeMateriel> listeType;
	
	
	/**
	 * MÃ©thode qui crÃ©er une instance de GestionnaireMateriels s'il n'en existe pas et retourne l'instance existante sinon (il ne peut y avoir qu'une seul instance de la classe GestionnaireMateriels 
	 * @return instance de la class GestionnaireMateriels
	 * 
	 */
	public final static GestionnaireMateriels getInstance(){
		
		if (GestionnaireMateriels.instance == null){
			synchronized(GestionnaireMateriels.class){
				if(GestionnaireMateriels.instance==null){
					GestionnaireMateriels.instance = new GestionnaireMateriels();
				}
				
			}
		}
		return GestionnaireMateriels.instance;
	}
	

	private GestionnaireMateriels(){
		listeFixe = new ArrayList<MaterielFixe>();
		listeMobile = new ArrayList<MaterielMobile>();
		listeType = new ArrayList<TypeMateriel>();
	}
	
	/**
	 * 
	 * @param code_inv le code du materiel
	 * @return true si le materiel existe, false sinon
	 * @throws Exception
	 */
	public boolean MaterielExists(int code_inv) throws Exception{
		for(int i=0; i<listeFixe.size(); i++){
			if(listeFixe.get(i).getCode_inv() == code_inv){
				return true;
			}
		}
		for(int i=0; i<listeMobile.size(); i++){
			if(listeMobile.get(i).getCode_inv() == code_inv){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param salle
	 * @param code_inv
	 * @param nom
	 * @param type
	 * @throws Exception
	 */
	public void ajoutMaterielFixe(Salle salle, int code_inv, String nom, TypeMateriel type) throws Exception{
		if(MaterielExists(code_inv)){
			throw new Exception("Materiel déjà existant");
		}
		MaterielFixe materiel = new MaterielFixe(code_inv, nom, type);
		salle.ajoutMateriel(materiel);
		listeFixe.add(materiel);
	}
	
	/**
	 * 
	 * @param code_inv
	 * @param nom
	 * @param type
	 * @throws Exception
	 */
	public void ajoutMaterielMobile(int code_inv, String nom, TypeMateriel type) throws Exception{
		if(MaterielExists(code_inv)){
			throw new Exception("Materiel déjà existant");
		}
		MaterielMobile materiel = new MaterielMobile(code_inv, nom, type);
		listeMobile.add(materiel);
	}
	
	/**
	 * Méthode qui enlève un materiel fixe à une salle et au système
	 * @param materiel le materiel fixe à supprimer
	 * @throws Exception si le materiel n'existe pas ou s'il est en cours d'utilisation
	 */
	public void supprimerMaterielFixe(MaterielFixe materiel) throws Exception{
		if(!MaterielExists(materiel.getCode_inv())){
			throw new Exception("Ce materiel n'existe pas");
		}
		materiel.getSalle().retirerMateriel(materiel);
		listeFixe.remove(materiel);
	}
	
	public void supprimerMaterielMobile(MaterielMobile materiel) throws Exception{
		if(!MaterielExists(materiel.getCode_inv())){
			throw new Exception("Ce materiel n'existe pas");
		}
		listeMobile.remove(materiel);
	}

}
