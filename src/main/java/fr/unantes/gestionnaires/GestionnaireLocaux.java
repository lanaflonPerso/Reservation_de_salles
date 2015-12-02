package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;

public class GestionnaireLocaux {
	private static volatile GestionnaireLocaux instance = null;
	
	private static ArrayList<Batiment> listeBatiments;
	private static ArrayList<TypeSalle> listeTypes;
	
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
		listeTypes = new ArrayList<TypeSalle>();
	}

	public static ArrayList<Batiment> getListeBatiments() {
		return listeBatiments;
	}

	public static void setListeBatiments(ArrayList<Batiment> listeBatiments) {
		GestionnaireLocaux.listeBatiments = listeBatiments;
	}

	public static ArrayList<TypeSalle> getListeTypes() {
		return listeTypes;
	}

	public static void setListeTypes(ArrayList<TypeSalle> listeTypes) {
		GestionnaireLocaux.listeTypes = listeTypes;
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
	 * @param nom
	 * @return le batiment dont le nom est égal à nom
	 * @throws Exception
	 */
	public Batiment rechercheBatimentParNom(String nom) throws Exception{
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNom().equals(nom)) {
				return listeBatiments.get(i);
			}
		}
		throw new Exception("Aucun batiment avec ce nom");
	}

	/**
	 * @param no_bat
	 * @return le batiment dont le numéro est egal a no_bat
	 * @throws Exception
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
	 * @param adresse
	 * @return le batiment qui possède l'adresse passée en paramètre
	 * @throws Exception 
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
	 * @param no
	 * @param nom
	 * @param adresse
	 * @throws Exception
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
	 * @param batiment
	 * @throws Exception
	 */
	public void supprimerBatiment(Batiment batiment) throws Exception{
		if (!batimentExists(batiment.getNo_bat())) {
			throw new Exception("Batiment inexistant");
		}
		listeBatiments.remove(batiment);
	}
	
	/**
	 * @param type
	 * @return true si le type de salle existe
	 */
	public boolean typeExists(int code) {
		boolean exists = false;
		for (int i = 0; i < listeTypes.size(); i++) {
			if (listeTypes.get(i).getCode() == code) {
				exists = true;
			}
		}
		return exists;

	}
	
	
/**
 * 
 * @param code
 * @param libelle
 * @param tarif
 * @throws Exception
 */
	public void ajoutType(int code, String libelle, double tarif) throws Exception{
		if(tarif < 0){
			throw new Exception("Le tarif ne peut pas être inférieur à 0.");
		}
		if (typeExists(code)) {
			throw new Exception("Code de type de salle déjà existant.");
		}
		TypeSalle type = new TypeSalle(code, libelle, tarif);
		listeTypes.add(type);
	}

	/**
	 * 
	 * @param type
	 * @return la liste des salles dont le type de salle correspond à l'entrée
	 * @throws Exception
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
	 * @param no_bat
	 * @return la liste des salles dans le batiment d'entrée
	 * @throws Exception
	 */
	public ArrayList<Salle> rechercheSalleParBatiment(int no_bat) throws Exception{
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_bat() == no_bat){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		if(liste.size() == 0){
			throw new Exception("Aucune salle dans ce batiment");
		}
		return liste;
	}
	
	/**
	 * 
	 * @param etage
	 * @return la liste des salles à l'étage mis en entrée
	 * @throws Exception
	 */
	public ArrayList<Salle> rechercheSalleParEtage(int etage) throws Exception{
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_etage() == etage){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		if(liste.size() == 0){
			throw new Exception("Aucune salle à cet étage");
		}
		return liste;
	}
	
	/**
	 * 
	 * @param numero
	 * @return la liste des salles dont le numer correspond à l'entrée
	 * @throws Exception
	 */
	public ArrayList<Salle> rechercheSalleParNumero(int numero) throws Exception{
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_salle() == numero){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		if(liste.size() == 0){
			throw new Exception("Aucune salle avec ce numéro");
		}
		return liste;
	}
	
	/**
	 * @param no_etage
	 * @param no_salle
	 * @param no_bat
	 * @param superficie
	 * @param type
	 * @throws Exception 
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
	 * @param salle
	 */
	public void supprimerSalle(Salle salle){
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNo_bat() == salle.getNo_bat()) {
				listeBatiments.get(i).enleveSalle(salle);
			}
		}
	}



}
