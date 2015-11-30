package fr.unantes.dao;



import java.sql.Connection;
import fr.unantes.bdd.Connexion;


public class DAOFactory {

	protected static final Connection conn = Connexion.getInstance();
	
	public static DAO getAdresseDAO(){
	return new AdresseDAO(conn);
	}
	
	public static DAO getBatimentDAO(){
	return new BatimentDAO(conn);
	}
	
	public static DAO getSalleDAO(){
	return new SalleDAO(conn);
	}
	
	public static DAO getReservationDAO(){
	return new ReservationDAO(conn);
	}
	
	public static DAO getDemandeurDAO(){
	return new DemandeurDAO(conn);
	}
	
	public static DAO getTypeSalleDAO(){
		return new TypeSalleDAO(conn);
	}
	
	public static DAO getMaterielDAO(){
		return new MaterielDAO(conn);
	}
	
	public static DAO getOrigineDAO(){
		return new OrigineDAO(conn);
	}
	
	public static DAO getTitreDAO(){
		return new TitreDAO(conn);
	}
}
	
	

