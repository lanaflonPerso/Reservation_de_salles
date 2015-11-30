package fr.unantes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.TypeSalle;

public class TypeSalleDAO extends DAO<TypeSalle>{

	public TypeSalleDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TypeSalle create(TypeSalle obj) {
		// TODO Auto-generated method stub
		try {
			
			//Insertion du type
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO type_salle (id, nom) VALUES(?,?)");

			prepare.setInt(1, obj.getId());
			prepare.setString(2, obj.getNom());
			prepare.executeUpdate();
		}
			catch(Exception e){
				e.getMessage();
				System.out.println("Erreur lors de l'insertion d'un nouveau type de salle");
			}
		return obj;
	}

	@Override
	public boolean delete(TypeSalle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeSalle update(TypeSalle obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeSalle find(int id) {
		// TODO Auto-generated method stub
		TypeSalle type = new TypeSalle();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select * from type_salle where id='" + id + "'");

			if (result.first()) {
				type = new TypeSalle(id, result.getString("nom"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type;
	}

	@Override
	public ArrayList<TypeSalle> list() {
		// TODO Auto-generated method stub
		ArrayList<TypeSalle> liste = new ArrayList();
		TypeSalle type = new TypeSalle();
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select * from type_salle");
			
			while(result.next()) {
				type = new TypeSalle(result.getInt("id"), result.getString("nom"));
				liste.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

}
