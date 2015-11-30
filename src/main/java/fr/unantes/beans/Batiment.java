package fr.unantes.beans;

import java.util.ArrayList;

import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;

public class Batiment {
	
	private int no_bat;
	private String nom;
	private Adresse adresse;
	private ArrayList<Salle> listeSalle;
	
	
	public Batiment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Batiment(int no_bat, String nom, Adresse adresse, ArrayList<Salle> listeSalle) {
		super();
		this.no_bat = no_bat;
		this.nom = nom;
		this.adresse = adresse;
		this.listeSalle = listeSalle;
	}
	
	public Batiment(int no_bat, String nom, Adresse adresse){
		super();
		this.no_bat = no_bat;
		this.nom = nom;
		this.adresse = adresse;
	}
	public int getNo_bat() {
		return no_bat;
	}
	public void setNo_bat(int no_bat) {
		this.no_bat = no_bat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ArrayList<Salle> getListeSalle() {
		return listeSalle;
	}

	public void setListeSalle(ArrayList<Salle> listeSalle) {
		this.listeSalle = listeSalle;
	}
	
	 //Ajoute une salle au batiment
	  public void ajoutSalle(Salle salle){
		  if(this.listeSalle == null){
			  this.listeSalle = new ArrayList<Salle>();
		  }
		  this.listeSalle.add(salle);
	  }

	  //Retire une salle au batiment
	  public void enleveSalle(Salle salle){
		salle.getListeMateriel().clear();
		salle.getListeReservation().clear();
	  	this.listeSalle.remove(salle);
	  }
	  
	  
	  
	  
	  
	 
}
