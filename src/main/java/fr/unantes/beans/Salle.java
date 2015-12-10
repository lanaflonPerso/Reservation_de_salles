package fr.unantes.beans;

import java.util.ArrayList;

import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

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



	public void ajoutMateriel(MaterielFixe materiel){
		this.listeMateriel.add(materiel);
	}
	
	public void retirerMateriel(MaterielFixe materiel){
		this.listeMateriel.remove(materiel);		
	}
	
	public void ajoutReservation(Reservation reservation){
		this.listeReservation.add(reservation);
	}
	
	public void retirerReservation(Reservation reservation){
		this.listeReservation.remove(reservation);
	}
	
	public String toString(){
		return "salle n° "+ this.noEtage + this.noSalle + " du batiment " + this.noBat;
	}
	
	public double calculerTarif(){
		double tarif = 0;
		for(int i=0; i<this.listeMateriel.size(); i++){
			tarif = tarif + this.listeMateriel.get(i).calculerTarif();
		}
		return this.type.getTarif() + tarif;
	}
	
	public boolean compareSalle(Salle s){
		return (this.noSalle == s.getNoSalle() && this.noBat == s.getNoBat() && this.noEtage == s.getNoEtage());
	}
	
	public boolean MaterielExists(int codeInv){
		for(MaterielFixe each : this.listeMateriel){
			if (each.getCodeInv() == codeInv){
				return true;
			}
		}
		return false;
	}
	
	public MaterielFixe getMateriel(int codeInv) throws Exception{
		for(MaterielFixe each : this.listeMateriel){
			if (each.getCodeInv() == codeInv){
				return each;
			}
		}
		throw new Exception("Aucun matériel avec ce codeInv");
	}


}