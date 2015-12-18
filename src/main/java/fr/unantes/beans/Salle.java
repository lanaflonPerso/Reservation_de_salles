package fr.unantes.beans;

import java.util.ArrayList;
import java.util.Date;



public class Salle {
	
	private int noEtage;
	private int noSalle;
	private int noBat;
	private int superficie;
	private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
	private ArrayList<MaterielFixe> listeMateriel = new ArrayList<MaterielFixe>();
	private TypeSalle type;
	
	
	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public Salle(int noEtage, int noSalle, int noBat, int superficie,
			ArrayList<Reservation> listeReservation,
			ArrayList<MaterielFixe> listeMateriel, TypeSalle type) {
		super();
		this.noEtage = noEtage;
		this.noSalle = noSalle;
		this.noBat = noBat;
		this.superficie = superficie;
		this.listeReservation = listeReservation;
		this.listeMateriel = listeMateriel;
		this.type = type;
	}
	
	public Salle(int noEtage, int noSalle, int noBat, int superficie,
			 TypeSalle type) {
		super();
		this.noEtage = noEtage;
		this.noSalle = noSalle;
		this.noBat = noBat;
		this.superficie = superficie;
		this.type = type;
		this.listeMateriel = new ArrayList<MaterielFixe>();
		this.listeReservation = new ArrayList<Reservation>();
	}

	

	public int getNoEtage() {
		return noEtage;
	}



	public void setNoEtage(int noEtage) {
		this.noEtage = noEtage;
	}



	public int getNoSalle() {
		return noSalle;
	}



	public void setNoSalle(int noSalle) {
		this.noSalle = noSalle;
	}



	public int getNoBat() {
		return noBat;
	}



	public void setNoBat(int noBat) {
		this.noBat = noBat;
	}



	public int getSuperficie() {
		return superficie;
	}



	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}



	public ArrayList<Reservation> getListeReservation() {
		return listeReservation;
	}



	public void setListeReservation(ArrayList<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}



	public ArrayList<MaterielFixe> getListeMateriel() {
		return listeMateriel;
	}



	public void setListeMateriel(ArrayList<MaterielFixe> listeMateriel) {
		this.listeMateriel = listeMateriel;
	}



	public TypeSalle getType() {
		return type;
	}



	public void setType(TypeSalle type) {
		this.type = type;
	}

	
	/**
	 * renvoie true si la salle est disponible pour une réservation à une date et pour une durée précise.
	 * @param date la date à laquelle on veut savoir la disponibilité.
	 * @param duree la durée pour laquelle on veut savoir si la salle est disponible.
	 * @return true si la salle est disponible, false sinon.
	 * On compare les dates et durées de réservation pour savoir si la salle possède déjà une réservation pour un créneau donné.
	 */
	public boolean disponible(Date date, long duree) throws Exception{
		if(duree < 0){
			throw new Exception("Durée négative");
		}
		if(this.listeReservation.isEmpty()){
			return true;
		}
		
		//Créneaux de notre nouvelle réservation
		long debutCreation = date.getTime();
		long finCreation = date.getTime() + duree;

		for(Reservation each : this.listeReservation){
			//Créneau d'une réservation déjà existante
			long finExistante = each.debut() + each.getTemps();
			long debutExistante = each.debut();

			//Si la nouvelle réservation se finit après le début d'une ancienne
			if(finCreation >= debutExistante && debutCreation <= debutExistante){
				return false;
			}
			//Si la nouvelle réservation commence avant la fin d'une ancienne
			if(debutCreation <= finExistante && finCreation >= finExistante){
				return false;
			}
			//Si la nouvelle réservation est en plein dans une réservation existante
			if(debutCreation >= debutExistante && finCreation <= finExistante){
				return false;
			}
		}	
		return true;
	}
	
	public void ajoutReservation(Reservation reservation){
		this.listeReservation.add(reservation);
	}
	
	/**
	 * Retire une réservation à cette salle.
	 * @param reservation la réservation à retirer.
	 * @throws Exception si la réservation ne concerne pas cette salle.
	 */
	public void retirerReservation(Reservation reservation) throws Exception{
		if(this.listeReservation.isEmpty()){
			throw new Exception("Aucune réservation pour cette salle");
		}
		boolean trouve = false;
		for(Reservation each : this.listeReservation){
			if(each.equals(reservation)){
				trouve = true;
				break;
			}
		}
		if(trouve){
			this.listeReservation.remove(reservation);
		}
		
	}
	
	/**
	 * Méthode servant à vider la salle de ses réservations avant supression.
	 * @throws Exception
	 */
	public void retirerReservations() throws Exception{
		if(!this.listeReservation.isEmpty()){
			for(Reservation each : this.listeReservation){
				each.annuler();
			}
		}
		
	}
	
	public void ajoutMateriel(MaterielFixe materiel){
		this.listeMateriel.add(materiel);
	}
	
	public void retirerMateriel(MaterielFixe materiel){
		this.listeMateriel.remove(materiel);		
	}

	/**
	 * 
	 * @param codeInv le code du materiel.
	 * @return true si le materiel existe, false sinon.
	 */
	public boolean MaterielExists(int codeInv){
		for(MaterielFixe each : this.listeMateriel){
			if (each.getCodeInv() == codeInv){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param codeInv le code du materiel à rechercher.
	 * @return le materiel recherché.
	 * @throws Exception si le materiel n'existe pas.
	 */
	public MaterielFixe getMateriel(int codeInv) throws Exception{
		for(MaterielFixe each : this.listeMateriel){
			if (each.getCodeInv() == codeInv){
				return each;
			}
		}
		throw new Exception("Aucun matériel avec ce codeInv");
	}
	

	/**
	 * Recherche tous les materiaux selon leurs types.
	 * @param type2 le type de materiel fixe à rechercher.
	 * @return la liste des materieux fixes de la salle ayant pour type de materiel type2.
	 */
	public ArrayList<MaterielFixe> getListeMateriel(TypeMateriel type2) {
		// TODO Auto-generated method stub
		ArrayList<MaterielFixe> res;
		res = new ArrayList<MaterielFixe>();
		for (MaterielFixe mf : listeMateriel){
			if (mf.getType().equals(type2) ){
				res.add(mf);
			}
		}
		return (res);
	}

	/**
	 * Regarde si deux salles sont égales.
	 * @param s la salle à comparer.
	 * @return true si la salle est identique, false sinon.
	 */
	public boolean equals(Salle s){
		return (s.getNoSalle() == noSalle && s.getNoEtage() == noEtage && s.getNoBat() == noBat );
	}
	
	/**
	 * Calcul le tarif d'une salle grâce à son type et ses materiaux fixes.
	 * @return le tarif de la salle.
	 */
	public double calculerTarif(){
		double tarif = 0;
		for(int i=0; i<this.listeMateriel.size(); i++){
			tarif = tarif + this.listeMateriel.get(i).calculerTarif();
		}
		return this.type.getTarif() + tarif;
	}


}