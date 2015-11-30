package fr.unantes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Titre;

public class DemandeurDAO extends DAO<Demandeur> {

	public DemandeurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Demandeur create(Demandeur obj) {
		// TODO Auto-generated method stub
		
		int no_dem;
		try {
			//On récupère le dernier id
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT no_dem from demandeur as no_dem;");

			if (result.last())
				no_dem = result.getInt("no_dem");
			else
				no_dem = 0;
			no_dem++;
			obj.setNo_dem(no_dem);

			//Ajout d'un demandeur en base
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO demandeur (no_dem, nom, fk_id_adresse, fk_id_origine, fk_id_titre) VALUES(?,?,?,?,?)");

			prepare.setInt(1, no_dem);
			prepare.setString(2, obj.getNom());
			prepare.setInt(3, obj.getAdresse().getId());
			prepare.setInt(4, obj.getOrigine().getId());
			prepare.setInt(5, obj.getTitre().getId());
			prepare.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
			System.out
					.println("Erreur lors de l'insertion d'un nouveau demandeur");
		}
		return obj;
	}

	@Override
	public boolean delete(Demandeur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Demandeur update(Demandeur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Demandeur find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList list() {
		// TODO Auto-generated method stub
		return null;
	}

}
