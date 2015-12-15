package fr.unantes.gestionnaires;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;

import fr.unantes.beans.MaterielFixe;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.interfaces.InterfaceLocaux;

public class GestionnaireLocaux implements InterfaceLocaux{
	
private static volatile GestionnaireLocaux instance = null;
	
	private ArrayList<Batiment> listeBatiments;
	private ArrayList<MaterielMobile> listeMateriauxMobiles; 
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
		listeMateriauxMobiles = new ArrayList<MaterielMobile>();
	}

	public ArrayList<Batiment> getListeBatiments() {
		return listeBatiments;
	}

	public void setListeBatimentsFixes(ArrayList<Batiment> listeBatiments) {
		this.listeBatiments = listeBatiments;
	}


	@Override
	public boolean adresseExists(String no, String adresse, String code, String ville) {
		// TODO Auto-generated method stub
		for (Batiment each : listeBatiments) {
			if (each.adresseExists(no, adresse, code, ville)) {
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
	public boolean batimentExists(int noBat) {
		// TODO Auto-generated method stub
		for(Batiment each : listeBatiments){
			if(each.getNoBat() == noBat){
				return true;
			}
		}
		return false;
	}

	@Override
	public Batiment getBatiment(int noBat) throws Exception {
		// TODO Auto-generated method stub
		for(Batiment each : listeBatiments){
			if(each.getNoBat() == noBat){
				return each;
			}
		}
		throw new Exception("Aucun batiment avec ce numéro");	
	}

	@Override
	public Batiment getBatiment(Adresse adresse) throws Exception {
		// TODO Auto-generated method stub
		for(Batiment each : listeBatiments){
			if(each.getAdresse().equals(adresse)){
				return each;
			}
		}
		throw new Exception("Aucun batiment avec cette adresse");
	}

	@Override
	public ArrayList<Batiment> getBatiments(String nom) {
		// TODO Auto-generated method stub
		ArrayList<Batiment> liste = new ArrayList<Batiment>();
		for(Batiment each : listeBatiments){
			if(each.getNom().equals(nom)){
				liste.add(each);
			}
		}
		return liste;
	}

	@Override
	public void ajouterBatiment(int noBat, String nom, Adresse adresse)
			throws Exception {
		// TODO Auto-generated method stub
		if (batimentExists(noBat)) {
			throw new Exception("Batiment déjà existant");
		}
		if(adresseExists(adresse.getNo(), adresse.getAdresse(), adresse.getCode(), adresse.getVille())){
			throw new Exception("Un batiment possède déjà cette adresse");
		}
		Batiment batiment = new Batiment(noBat, nom, adresse);
		listeBatiments.add(batiment);
	}

	@Override
	public void supprimerBatiment(int noBat) throws Exception {
		// TODO Auto-generated method stub
		if (!batimentExists(noBat)) {
			throw new Exception("Batiment inexistant");
		}
		listeBatiments.remove(getBatiment(noBat));
	}

	@Override
	public void modifierBatiment(int noBat, String nom) throws Exception {
		// TODO Auto-generated method stub
		Batiment batiment = this.getBatiment(noBat);
		batiment.setNom(nom);
	}
	
	@Override
	public boolean salleExists(int noEtage, int noSalle, int noBatiment) {
		// TODO Auto-generated method stub
		if(!batimentExists(noBatiment)){
			return false;
		}
		for(Batiment each : listeBatiments){
			if(each.salleExists(noSalle, noEtage)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Salle getSalle(int noEtage, int noSalle, int noBatiment) throws Exception {
		// TODO Auto-generated method stub
		if(batimentExists(noBatiment)){
			throw new Exception("Cette salle n'existe pas");
		}
		for(Batiment each : listeBatiments){
			return each.getSalle(noEtage, noSalle);
		}
		throw new Exception("Salle innexistante");
	}

	@Override
	public ArrayList<Salle> getSalles(TypeSalle type) {
		// TODO Auto-generated method stub
		ArrayList<Salle> liste = new ArrayList<Salle>();
		for(Batiment each : listeBatiments){
			liste.addAll(each.getSalles(type));
		}
		return liste;
	}

	@Override
	public ArrayList<Salle> getSallesParBatiment(int noBat) {
		// TODO Auto-generated method stub
		ArrayList<Salle> liste = new ArrayList<Salle>();
		try {
			liste.addAll(getBatiment(noBat).getListeSalle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return liste;
	}

	@Override
	public ArrayList<Salle> getSallesParEtage(int noEtage) {
		// TODO Auto-generated method stub
		ArrayList<Salle> liste = new ArrayList<Salle>();
		for(Batiment each : listeBatiments){
			liste.addAll(each.getSallesParEtage(noEtage));
		}
		return liste;
	}

	@Override
	public ArrayList<Salle> getSallesParNumero(int noSalle) {
		// TODO Auto-generated method stub
		ArrayList<Salle> liste = new ArrayList<Salle>();
		for(Batiment each : listeBatiments){
			liste.addAll(each.getSallesParNo(noSalle));
		}
		return liste;
	}

	@Override
	public void ajouterSalle(int noEtage, int noSalle, int noBat,
			int superficie, TypeSalle type) throws Exception {
		// TODO Auto-generated method stub
		if(!batimentExists(noBat)){
			throw new Exception("Batiment inexistant");
		}
		if(salleExists(noEtage, noSalle, noBat)){
			throw new Exception("Cette salle existe déjà");
		}
		if(superficie <= 0){
			throw new Exception("Superficie impossible");
		}

		Salle salle = new Salle(noEtage, noSalle, noBat, superficie, type);	
		getBatiment(noBat).ajouterSalle(salle);
	}

	@Override
	public void supprimerSalle(int noEtage, int noSalle, int noBatiment)
			throws Exception {
		// TODO Auto-generated method stub
		if(!salleExists(noEtage, noSalle, noBatiment)){
			throw new Exception("Cette salle n'existe pas");
		}
		getBatiment(noBatiment).supprimerSalle(getSalle(noEtage, noSalle, noBatiment));
	}

	@Override
	public void modifierSalle(int noEtage, int noSalle, int noBatiment,
			int superficie, TypeSalle type) {
		// TODO Auto-generated method stub
		try {
			Salle s  = getSalle(noEtage, noSalle, noBatiment);
			s.setSuperficie(superficie);
			s.setType(type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean materielExists(int condInv) {
		for(Batiment each : listeBatiments) {		
			if(each.materielExists(condInv)){
				return true;
			}
		}
		return false;
	}

	@Override
	public MaterielFixe getMaterielFixe(int codeInv){
		// TODO Auto-generated method stub
		for(Batiment each : listeBatiments){
			try {
				return each.getMateriel(codeInv);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public ArrayList<MaterielFixe> getMateriauxFixes(TypeMateriel type) {
		// TODO Auto-generated method stub
		ArrayList<MaterielFixe> res;
		res = new ArrayList<MaterielFixe>();
		for(Batiment each: listeBatiments){
			res.addAll(each.getMateriel(type));
		}
		return res;
		
	}

	@Override
	public ArrayList<MaterielFixe> getMateriauxFixes(Salle salle) {
		// TODO Auto-generated method stub
		ArrayList<MaterielFixe> res;
		res = new ArrayList<MaterielFixe>();
		for(Batiment each : listeBatiments){
			res.addAll(each.getMateriel(salle));
		}
		return res;
	}

	@Override
	public void ajouterMaterielFixe(int codeInv, String nom, Salle salle, TypeMateriel type)
			throws Exception {
		// TODO Auto-generated method stub
		if(materielExists(codeInv)){
			throw new Exception("Materiel déjà existant");
		}
		MaterielFixe materiel = new MaterielFixe(codeInv, nom, type);
		salle.ajoutMateriel(materiel);
	}

	@Override
	public void supprimerMaterielFixe(int codeInv) throws Exception {
		// TODO Auto-generated method stub
		if(!materielExists(codeInv)){
			throw new Exception("Ce materiel n'existe pas");
		}
		MaterielFixe materiel = getMaterielFixe(codeInv);
		materiel.getSalle().retirerMateriel(materiel);
	}

	

	@Override
	public MaterielMobile getMaterielMobile(int codeInv) {
		// TODO Auto-generated method stub
		MaterielMobile m = this.getMaterielMobile(codeInv);
		return m;
	}

	@Override
	public ArrayList<MaterielMobile> getMateriauxMobiles(TypeMateriel type) {
		// TODO Auto-generated method stub
		ArrayList<MaterielMobile> res;
		res = new ArrayList<MaterielMobile>();
		for(MaterielMobile mm: listeMateriauxMobiles){
			if (mm.getType().equals(type)){
				res.add(mm);
			}
		}
		return res;
		
	}



	@Override
	public void ajouterMaterielMobile(int codeInv, String nom,TypeMateriel type) throws Exception {
		// TODO Auto-generated method stub
		listeMateriauxMobiles.add(new MaterielMobile(codeInv,nom,type));
	}

	@Override
	public void supprimerMaterielMobile(int codeInv) throws Exception {
		// TODO Auto-generated method stub
		//boolean pour savoir si le materiel a été trouvé
		boolean trouve = false;
		for(int i=0;i<listeMateriauxMobiles.size();++i){
			if (listeMateriauxMobiles.get(i).getCodeInv() == codeInv){
				listeMateriauxMobiles.remove(i);
				trouve = true;
			}
		}
		//S'il n'a pas été trouvé on lève une exception
		if(!trouve){
			throw new Exception ("Auncun Materiel avec ce codeInv"); 
		}
		
	}

	




	

}
