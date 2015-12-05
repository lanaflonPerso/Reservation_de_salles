package fr.unantes.beans;

import java.util.ArrayList;

import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class Salle {
	
	private int no_etage;
	private int no_salle;
	private int no_bat;
	private int superficie;
	private ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
	private ArrayList<MaterielFixe> listeMateriel = new ArrayList<MaterielFixe>();
	private TypeSalle type;
	
	
	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Salle(int no_etage, int no_salle, int no_bat,
			int superficie, ArrayList<Reservation> listeReservation,
			ArrayList<MaterielFixe> listeMateriel, TypeSalle type) {
		super();
		this.no_etage = no_etage;
		this.no_salle = no_salle;
		this.no_bat = no_bat;
		this.superficie = superficie;
		this.listeReservation = listeReservation;
		this.listeMateriel = listeMateriel;
		this.type = type;
	}

	

	public Salle(int no_etage, int no_salle, int no_bat, int superficie,
			TypeSalle type) {
		super();
		this.no_etage = no_etage;
		this.no_salle = no_salle;
		this.no_bat = no_bat;
		this.superficie = superficie;
		this.type = type;
	}


	public int getNo_etage() {
		return no_etage;
	}


	public void setNo_etage(int no_etage) {
		this.no_etage = no_etage;
	}


	public int getNo_salle() {
		return no_salle;
	}


	public void setNo_salle(int no_salle) {
		this.no_salle = no_salle;
	}


	public int getNo_bat() {
		return no_bat;
	}


	public void setNo_bat(int no_bat) {
		this.no_bat = no_bat;
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
	
	public void retirerMateriel(Materiel materiel){
		this.listeMateriel.remove(materiel);		
	}
	
	public void ajoutReservation(Reservation reservation){
		this.listeReservation.add(reservation);
	}
	
	public void retirerReservation(Reservation reservation){
		this.listeReservation.remove(reservation);
	}
	
	public String toString(){
		return "salle n° "+ this.no_etage + this.no_salle + " du batiment " + this.no_bat;
	}
	
	public double calculerTarif(){
		double tarif = 0;
		for(int i=0; i<this.listeMateriel.size(); i++){
			tarif = tarif + this.listeMateriel.get(i).calculerTarif();
		}
		return this.type.getTarif() + tarif;
	}
	
	public boolean compareSalle(Salle s){
		return (this.no_salle == s.getNo_salle() && this.no_bat == s.getNo_bat() && this.no_etage == s.getNo_etage());
	}
	


}