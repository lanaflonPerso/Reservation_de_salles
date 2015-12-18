package fr.unantes.dao;



import java.sql.Connection;

import fr.unantes.bdd.Connexion;
import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeSalle;


public class DAOFactory {

	protected static final Connection conn = Connexion.getInstance();
	
	public static DAO<Adresse> getAdresseDAO(){
	return new AdresseDAO(conn);
	}
	
	public static DAO<Batiment> getBatimentDAO(){
	return new BatimentDAO(conn);
	}
	
	public static DAO<Salle> getSalleDAO(){
	return new SalleDAO(conn);
	}
	
	public static DAO<Reservation> getReservationDAO(){
	return new ReservationDAO(conn);
	}
	
	public static DAO<Demandeur> getDemandeurDAO(){
	return new DemandeurDAO(conn);
	}
	
	public static DAO<TypeSalle> getTypeSalleDAO(){
		return new TypeSalleDAO(conn);
	}
	
	public static DAO<Materiel> getMaterielDAO(){
		return new MaterielDAO(conn);
	}
	
	public static DAO<Origine> getOrigineDAO(){
		return new OrigineDAO(conn);
	}
	
	public static DAO<Titre> getTitreDAO(){
		return new TitreDAO(conn);
	}
}
	
	

