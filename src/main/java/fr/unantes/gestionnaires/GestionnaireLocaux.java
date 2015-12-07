package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.MaterielFixe;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.interfaces.InterfaceLocaux;

public class GestionnaireLocaux implements InterfaceLocaux{
	
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


	@Override
	public boolean adresseExists(String no, String adresse, String code,
			String ville) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getAdresse().getAdresse().equals(adresse)
					&& listeBatiments.get(i).getAdresse().getCode().equals(code)
					&& listeBatiments.get(i).getAdresse().getNo().equals(no)
					&& listeBatiments.get(i).getAdresse().getVille().equals(ville)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Adresse getAdresse(String no, String adresse, String code,
			String ville) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterAdresse(String no, String adresse, String code,
			String ville) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean batimentExists(int no_bat) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (no_bat == listeBatiments.get(i).getNo_bat()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Batiment getBatiment(int no_bat) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNo_bat() == no_bat) {
				return listeBatiments.get(i);
			}
		}
		throw new Exception("Aucun batiment avec ce numéro");
	}

	@Override
	public Batiment getBatiment(Adresse adresse) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getAdresse().equals(adresse)) {
				return listeBatiments.get(i);
			}
		}
		throw new Exception("Aucun batiment avec cette adresse");
	}

	@Override
	public ArrayList<Batiment> getBatiments(String nom) {
		// TODO Auto-generated method stub
		ArrayList<Batiment> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNom().equals(nom)) {
				liste.add(listeBatiments.get(i));
			}
		}
		return liste;
	}

	@Override
	public void ajouterBatiment(int no_bat, String nom, Adresse adresse)
			throws Exception {
		// TODO Auto-generated method stub
		if (batimentExists(no_bat)) {
			throw new Exception("Batiment déjà existant");
		}
		if(adresseExists(adresse.getNo(), adresse.getAdresse(), adresse.getCode(), adresse.getVille())){
			throw new Exception("Un batiment possède déjà cette adresse");
		}
		Batiment batiment = new Batiment(no_bat, nom, adresse);
		listeBatiments.add(batiment);
	}

	@Override
	public void supprimerBatiment(int no_bat) throws Exception {
		// TODO Auto-generated method stub
		if (!batimentExists(no_bat)) {
			throw new Exception("Batiment inexistant");
		}
		listeBatiments.remove(getBatiment(no_bat));
	}

	@Override
	public void modifierBatiment(int no_bat, String nom) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean salleExists(int no_etage, int no_salle, int no_batiment) {
		// TODO Auto-generated method stub
		if(!batimentExists(no_batiment)){
			return false;
		}
		
		for(int i=0; i<listeBatiments.size(); i++){
			if(listeBatiments.get(i).getNo_bat() == no_batiment){
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

	@Override
	public Salle getSalle(int no_etage, int no_salle, int no_batiment)
			throws Exception {
		// TODO Auto-generated method stub
		if(batimentExists(no_batiment)){
			throw new Exception("Cette salle n'existe pas");
		}
		for(int i=0; i<listeBatiments.size(); i++){
			if(listeBatiments.get(i).getNo_bat() == no_batiment){
				Batiment batiment = listeBatiments.get(i);
				for(int j=0; j<batiment.getListeSalle().size(); j++){
					if(batiment.getListeSalle().get(j).getNo_etage() == no_etage 
							&& batiment.getListeSalle().get(j).getNo_salle() == no_salle){
						return batiment.getListeSalle().get(j);
					}
				}
			}
		}
		throw new Exception("Salle innexistante");
	}

	@Override
	public ArrayList<Salle> getSalles(TypeSalle type) {
		// TODO Auto-generated method stub
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getType().equals(type)){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		return liste;
	}

	@Override
	public ArrayList<Salle> getSallesParBatiment(int no_bat) {
		// TODO Auto-generated method stub
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

	@Override
	public ArrayList<Salle> getSallesParEtage(int no_etage) {
		// TODO Auto-generated method stub
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_etage() == no_etage){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		return liste;
	}

	@Override
	public ArrayList<Salle> getSallesParNumero(int no_salle) {
		// TODO Auto-generated method stub
		ArrayList<Salle> liste = new ArrayList();
		for (int i = 0; i < listeBatiments.size(); i++) {
			for(int j=0; j<listeBatiments.get(i).getListeSalle().size(); j++){
				if(listeBatiments.get(i).getListeSalle().get(j).getNo_salle() == no_salle){
					liste.add(listeBatiments.get(i).getListeSalle().get(j));
				}
			}
		}
		return liste;
	}

	@Override
	public void ajouterSalle(int no_etage, int no_salle, int no_bat,
			int superficie, TypeSalle type) throws Exception {
		// TODO Auto-generated method stub
		if(!batimentExists(no_bat)){
			throw new Exception("Batiment inexistant");
		}
		if(superficie <= 0){
			throw new Exception("Superficie impossible");
		}

		Salle salle = new Salle(no_etage, no_salle, no_bat, superficie, type);	
		
		ArrayList<Salle> listeSalle = getSallesParBatiment(no_bat);
		if(!listeSalle.isEmpty()){
			for(int i=0; i<listeSalle.size(); i++){
				if(listeSalle.get(i).compareSalle(salle)){
					throw new Exception("Cette salle existe déjà");
				}
			}			
		}
		
		Batiment batiment = getBatiment(no_bat);
		batiment.ajoutSalle(salle);
	}

	@Override
	public void supprimerSalle(int no_etage, int no_salle, int no_batiment)
			throws Exception {
		// TODO Auto-generated method stub
		if(!salleExists(no_etage, no_salle, no_batiment)){
			throw new Exception("Cette salle n'existe pas");
		}
		for (int i = 0; i < listeBatiments.size(); i++) {
			if (listeBatiments.get(i).getNo_bat() == no_batiment) {
				Salle salle = getSalle(no_etage, no_salle, no_batiment);
				listeBatiments.get(i).enleveSalle(salle);
			}
		}
	}

	@Override
	public void modifierSalle(int no_etage, int no_salle, int no_batiment,
			int superficie, TypeSalle type) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean materielExists(int code_inv) {
		// TODO Auto-generated method stub
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

	@Override
	public MaterielFixe getMateriel(int code_inv) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MaterielFixe> getMateriaux(TypeMateriel type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MaterielFixe> getMateriaux(Salle salle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouterMateriel(int code_inv, String nom, Salle salle, TypeMateriel type)
			throws Exception {
		// TODO Auto-generated method stub
		if(materielExists(code_inv)){
			throw new Exception("Materiel déjà existant");
		}
		MaterielFixe materiel = new MaterielFixe(code_inv, nom, type);
		salle.ajoutMateriel(materiel);
	}

	@Override
	public void supprimerMateriel(int code_inv) throws Exception {
		// TODO Auto-generated method stub
		if(!materielExists(code_inv)){
			throw new Exception("Ce materiel n'existe pas");
		}
		MaterielFixe materiel = getMateriel(code_inv);
		materiel.getSalle().retirerMateriel(materiel);
	}




	

}
