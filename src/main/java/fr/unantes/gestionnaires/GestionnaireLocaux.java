package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.MaterielFixe;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;

public class GestionnaireLocaux {
	private static volatile GestionnaireLocaux instance = null;
	
	private static ArrayList<Batiment> listeBatiments;

	
	/**
	 * 
	 * @return une instance du gestionnaire de locaux si elle n'existe pas
	 */
	public final static GestionnaireLocaux getInstance(){
		
		if (GestionnaireLocaux.instance == null){
			synchronized(GestionnaireLocaux.class){
				if(GestionnaireLocaux.instance==null){
					GestionnaireLocaux.instance = new GestionnaireLocaux();
				}
				
			}
		}
		return GestionnaireLocaux.instance;
	}
	
	/**
	 * initialise les listes de données
	 */
	private GestionnaireLocaux(){
		listeBatiments = new ArrayList<Batiment>();
	}

	public static ArrayList<Batiment> getListeBatiments() {
		return listeBatiments;
	}

	public static void setListeBatiments(ArrayList<Batiment> listeBatiments) {
		GestionnaireLocaux.listeBatiments = listeBatiments;
	}



	/**
	 * 
	 * @param no
	 * @param adresse
	 * @param code
	 * @param ville
	 * @return true si l'adresse correspond déjà à un batiment
	 */
	public boolean adresseExists(String no, String adresse, String code, String ville) {
		boolean exists = false;
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getAdresse().getAdresse().equals(adresse)
					&& listeBatiments.get(i).getAdresse().getCode().equals(code)
					&& listeBatiments.get(i).getAdresse().getNo().equals(no)
					&& listeBatiments.get(i).getAdresse().getVille().equals(ville)) {
				exists = true;
			}
		}
		return exists;
	}
	
	
	/**
	 * @param no_bat
	 * @return true si le batiment existe
	 */
	public boolean batimentExists(int no_bat) {
		boolean exists = false;
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (no_bat == listeBatiments.get(i).getNo_bat()) {
				exists = true;
			}
		}
		return exists;
	}
	
	/**
	 * 
	 * @param no_etage l'etage de la salle
	 * @param no_salle le numero de la salle
	 * @param no_bat le numero du batiment
	 * @return true si la salel existe, false sinon
	 */
	public boolean salleExists(int no_etage, int no_salle, int no_bat){
		if(!batimentExists(no_bat)){
			return false;
		}
		
		for(int i=0; i<listeBatiments.size(); i++){
			if(listeBatiments.get(i).getNo_bat() == no_bat){
				Batiment batiment = listeBatiments.get(i);
				for(int j=0; j<batiment.getListeSalle().size(); j++){
					if(batiment.getListeSalle().get(j).getNo_etage() == no_etage
							&& batiment.getListeSalle().get(j).getNo_salle() == no_salle){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param code_inv le code du materiel 
	 * @return true si la materiel existe, false sinon
	 */
	public boolean materielExists(int code_inv){
		//On recherche dans toutes les salles de tous les batiments
		for(int i=0; i<listeBatiments.size(); i++){
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				for(int k=0; k<listeBatiments.get(j).getListeSalle().get(j).getListeMateriel().size(); k++){
					if(listeBatiments.get(j).getListeSalle().get(j).getListeMateriel().get(k).getCode_inv() == code_inv){
						return true;
					}
				}
			}
		}
		return false;
	}
	

	/**
	 * 
	 * @param nom le nom du batiment
	 * @return la liste des batiments ayant pour nom le paramètre
	 */
	public ArrayList<Batiment> rechercheBatimentParNom(String nom){
		ArrayList<Batiment> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNom().equals(nom)) {
				liste.add(listeBatiments.get(i));
			}
		}
		return liste;
	}

	/**
	 * @param no_bat
	 * @return le batiment dont le numéro est egal a no_bat
	 * @throws Exception si aucun batiment ne possède ce numéro
	 */
	public Batiment rechercheBatimentParNumero(int no_bat) throws Exception{
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNo_bat() == no_bat) {
				return listeBatiments.get(i);
			}
		}
		throw new Exception("Aucun batiment avec ce numéro");

	}

	/**
	 * @param adresse l'adresse du batiment a chercher
	 * @return le batiment qui possède l'adresse passée en paramètre
	 * @throws Exception si aucun batiment n'a cette adresse
	 */
	public Batiment rechercheBatimentParAdresse(Adresse adresse) throws Exception {
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getAdresse().equals(adresse)) {
				return listeBatiments.get(i);
			}
		}
		throw new Exception("Aucun batiment avec cette adresse");
	}

	/**
	 * @param no le numero du batiment
	 * @param nom le nom du batiment
	 * @param adresse l'adresse du batiment
	 * @throws Exception si le batiment existe déjà
	 */
	public void ajoutBatiment(int no, String nom, Adresse adresse) throws Exception {
		if (batimentExists(no)) {
			throw new Exception("Batiment déjà existant");
		}
		else if(adresseExists(adresse.getNo(), adresse.getAdresse(), adresse.getCode(), adresse.getVille())){
			throw new Exception("Un batiment possède déjà cette adresse");
		}
		else{
			Batiment batiment = new Batiment(no, nom, adresse);
			listeBatiments.add(batiment);
		}
	}

	/**
	 * @param batiment le batiment à supprimer
	 * @throws Exception si le batiment n'existe pas
	 */
	public void supprimerBatiment(Batiment batiment) throws Exception{
		if (!batimentExists(batiment.getNo_bat())) {
			throw new Exception("Batiment inexistant");
		}
		listeBatiments.remove(batiment);
	}

	/**
	 * 
	 * @param type le type de salle 
	 * @return la liste des salles dont le type de salle correspond à l'entrée
	 * @throws Exception si aucune salle n'a ce type
	 */
	public ArrayList<Salle> rechercheSalleParType(TypeSalle type) throws Exception{
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getType().equals(type)){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		if(liste.size() == 0){
			throw new Exception("Aucune salle de ce type");
		}
		return liste;
		
	}
	
	/**
	 * 
	 * @param no_bat le numéro du batiment
	 * @return la liste des salles dans le batiment d'entrée
	 */
	public ArrayList<Salle> rechercheSalleParBatiment(int no_bat){
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_bat() == no_bat){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		return liste;
	}
	
	/**
	 * 
	 * @param etage l'etage de la salle à rechercher
	 * @return la liste des salles à l'étage mis en entrée
	 * @throws Exception si aucune salel n'est à cet étage
	 */
	public ArrayList<Salle> rechercheSalleParEtage(int etage){
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_etage() == etage){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		return liste;
	}
	
	/**
	 * 
	 * @param numero le numero de la salle
	 * @return la liste des salles dont le numer correspond à l'entrée
	 * @throws Exceptionsi aucune salle n'a ce numero
	 */
	public ArrayList<Salle> rechercheSalleParNumero(int numero){
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_salle() == numero){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		return liste;
	}
	
	/**
	 * @param no_etage
	 * @param no_salle
	 * @param no_bat
	 * @param superficie
	 * @param type
	 * @throws Exception si le batiment n'existe pas, si la salle existe déjà ou si la superficie est nulle
	 */
	public void ajoutSalle(int no_etage, int no_salle, int no_bat, int superficie, TypeSalle type) throws Exception {
		Salle salle = new Salle(no_etage, no_salle, no_bat, superficie, type);	
		
		if(!batimentExists(no_bat)){
			throw new Exception("Batiment inexistant");
		}

		ArrayList<Salle> listeSalle = rechercheSalleParBatiment(no_bat);
		if(!listeSalle.isEmpty()){
			for(int i=0; i<listeSalle.size(); i++){
				if(listeSalle.get(i).compareSalle(salle)){
					throw new Exception("Cette salle existe déjà");
				}
			}			
		}
		if(superficie <= 0){
			throw new Exception("Superficie impossible");
		}
			Batiment batiment = rechercheBatimentParNumero(no_bat);
			batiment.ajoutSalle(salle);

	}

	/**
	 * @param salle la salle à supprimer
	 */
	public void supprimerSalle(Salle salle) throws Exception{
		if(!salleExists(salle.getNo_etage(), salle.getNo_salle(), salle.getNo_bat())){
			throw new Exception("Cette salle n'existe pas");
		}
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNo_bat() == salle.getNo_bat()) {
				listeBatiments.get(i).enleveSalle(salle);
			}
		}
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
		if(materielExists(code_inv)){
			throw new Exception("Materiel déjà existant");
		}
		MaterielFixe materiel = new MaterielFixe(code_inv, nom, type);
		salle.ajoutMateriel(materiel);
	}

	/**
	 * Méthode qui enlève un materiel fixe à une salle
	 * @param materiel le materiel fixe à supprimer
	 * @throws Exception si le materiel n'existe pas ou s'il est en cours d'utilisation
	 */
	public void supprimerMaterielFixe(MaterielFixe materiel) throws Exception{
		if(!materielExists(materiel.getCode_inv())){
			throw new Exception("Ce materiel n'existe pas");
		}
		materiel.getSalle().retirerMateriel(materiel);
	}
	
	

}
