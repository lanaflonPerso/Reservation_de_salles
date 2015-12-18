package fr.unantes.bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




public class Connexion {
	
	//private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost";
	private static String user = "root";
	private static String password = "root";
	private static Connection connect = null;

	//Connexion à la base de données
	private Connexion() {
		try {
			connect = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Retourne l'instance de la connexion
	public static Connection getInstance() {
		if (connect == null) {
			new Connexion();
		}
		return connect;
	}
	
	//Crée la base de données si elle n'existe pas
	public static void creationBDD() {
		Connection connect = getInstance();
		Statement stmt = null;

		try 
		{
			String gestion = "CREATE DATABASE gestion_salle;";
			stmt = connect.createStatement();
			System.out.println("Création de la base de données....");
			stmt.execute(gestion);
		} 
		catch (Exception e) 
		{
			System.out.println("Base de données déjà existante ! ");
		}

	}

	public static void insertionTables() {
		// La connexion
		Connection connect = getInstance();
		Statement stmt = null;

		try 
		{
			String bdd = "USE gestion_salle";
			
			String adresse = "CREATE table adresse(id integer auto_increment PRIMARY KEY, no varchar(10), adresse varchar(120), code varchar(5), ville varchar(100));";
			String salle = "CREATE table salle(id integer auto_increment PRIMARY KEY, no_etage integer, no_salle integer, no_bat integer, superficie integer, fk_id_type_salle integer);";
			String batiment = "CREATE table batiment(no_bat integer auto_increment PRIMARY KEY, nom varchar(100), fk_id_adresse integer);";
			String batiment_salle = "CREATE table batiment_salle(id integer auto_increment PRIMARY KEY, fk_id_batiment integer, fk_id_salle integer);";
			String demandeur = "CREATE table demandeur(no_dem integer auto_increment PRIMARY KEY, nom varchar(50), fk_id_adresse integer, fk_id_origine integer, fk_id_titre integer);";
			String materiel = "CREATE table materiel();";
			String reservation = "CREATE table reservation();";
			String type_salle = "CREATE table type_salle(id integer auto_increment PRIMARY KEY, nom varchar(100));";
			
			
			// execution des requêtes
			stmt = connect.createStatement();
			System.out.println("Utilisation de la base de données...");
			stmt.executeUpdate(bdd);
			
			System.out.println("Création des tables....");
			stmt.executeUpdate(adresse);
			stmt.executeUpdate(batiment);
			stmt.executeUpdate(batiment_salle);
			stmt.executeUpdate(demandeur);
			stmt.executeUpdate(materiel);
			stmt.executeUpdate(reservation);
			stmt.executeUpdate(salle);
			stmt.executeUpdate(type_salle);
			System.out.println("Tables crées avec succès");

		} 
		catch (SQLException se) 
		{
			// Handle errors for JDBC
			se.printStackTrace();
		} 
		catch (Exception e) 
		{
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		finally 
		{
			// finally block used to close resources
			try 
			{
				if (stmt != null)
					stmt.close();
			} 
			catch (SQLException se2) {}

		}

	}

}

