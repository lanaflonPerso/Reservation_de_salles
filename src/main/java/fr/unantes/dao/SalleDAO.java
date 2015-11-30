package fr.unantes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeSalle;

public class SalleDAO extends DAO<Salle>{

	public SalleDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Salle create(Salle obj) {
		// TODO Auto-generated method stub
		int id;
		try {
			//On récupère le dernier id
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT id from salle as id;");

			if (result.last())
				id = result.getInt("id");
			else
				id = 0;
			id++;
			obj.setId(id);
			
			//Ajout d'une salle en base
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO salle (id, no_etage, no_salle, no_bat, superficie, fk_id_type_salle) VALUES(?,?,?,?,?,?)");

			prepare.setInt(1, id);
			prepare.setInt(2, obj.getNo_etage());
			prepare.setInt(3, obj.getNo_salle());
			prepare.setInt(4, obj.getNo_bat());
			prepare.setInt(5, obj.getSuperficie());
			prepare.setInt(6, obj.getType().getId());
			prepare.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
			System.out.println("Erreur lors de l'insertion d'une nouvelle salle");
		}
		return obj;
	}

	@Override
	public boolean delete(Salle obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement prepare = this.connect
					.prepareStatement("DELETE FROM salle WHERE id="
							+ obj.getId());
			prepare.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Salle update(Salle obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Salle find(int id) {
		// TODO Auto-generated method stub
		Salle salle = new Salle();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select  * from salle where id ='" + id + "'");

			if (result.first()) {
				salle = new Salle();
				salle.setId(id);
				salle.setNo_etage(result.getInt("no_etage"));
				salle.setNo_bat(result.getInt("no_bat"));
				salle.setNo_salle(result.getInt("no_salle"));
				salle.setSuperficie(result.getInt("superficie"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return salle;
	}

	@Override
	public ArrayList<Salle> list() {
		// TODO Auto-generated method stub
		
		ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
		ArrayList<Materiel> listeMateriel = new ArrayList<Materiel>();
		TypeSalle type = new TypeSalle();
		
		Salle salle = new Salle();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select * from salle");
			
			if (result.next()) {
				salle = new Salle(result.getInt("id"), result.getInt("no_etage"),
						result.getInt("no_salle"), result.getInt("no_batiment"),
						result.getInt("superficie"),listeReservation,listeMateriel, type);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
