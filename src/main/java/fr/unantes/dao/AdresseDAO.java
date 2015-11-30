package fr.unantes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.unantes.bdd.Connexion;
import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Salle;

public class AdresseDAO extends DAO<Adresse> {

	public AdresseDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Adresse create(Adresse obj) {
		// TODO Auto-generated method stub
		int id;
		try {
			//On récupère le dernier id
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE).executeQuery(
					"SELECT id from adresse as id;");

			if (result.last())
				id = result.getInt("id");
			else
				id = 0;
			id++;
			obj.setId(id);

			//Ajout d'une adresse en base
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO adresse (id, no, adresse, code, ville) VALUES(?,?,?,?,?)");

			prepare.setInt(1, id);
			prepare.setString(2, obj.getNo());
			prepare.setString(3, obj.getAdresse());
			prepare.setString(4, obj.getCode());
			prepare.setString(5, obj.getVille());
			prepare.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
			System.out
					.println("Erreur lors de l'insertion d'une nouvelle adresse");
		}
		return obj;
	}

	@Override
	public boolean delete(Adresse obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement prepare = this.connect
					.prepareStatement("DELETE FROM adresse WHERE id="
							+ obj.getId());
			prepare.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Adresse update(Adresse obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("UPDATE adresse SET no = '" + obj.getNo()
							+ "', adresse='" + obj.getAdresse() + "', code ='"
							+ obj.getCode() + "', ville = '" + obj.getVille()
							+ "' WHERE id = '" + obj.getId() + "';");
			prepare.executeUpdate();

			obj = this.find(obj.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Adresse find(int id) {
		// TODO Auto-generated method stub
		Adresse adresse = new Adresse();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select * from adresse where id='" + id + "'");

			if (result.first()) {
				adresse = new Adresse(id, result.getString("no"),
						result.getString("adresse"), result.getString("code"),
						result.getString("ville"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adresse;
	}

	@Override
	public ArrayList<Adresse> list() {
		// TODO Auto-generated method stub
		ArrayList<Adresse> listeAdresse = new ArrayList();
		Adresse adresse = new Adresse();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select * from adresse");
			
			while(result.next()) {
				adresse = new Adresse(result.getInt("id"), result.getString("no"), result.getString("adresse"), result.getString("code"), result.getString("ville"));
				listeAdresse.add(adresse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeAdresse;
	}

}
