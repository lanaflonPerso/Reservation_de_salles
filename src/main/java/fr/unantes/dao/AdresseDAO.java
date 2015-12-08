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
		return null;
	}

	@Override
	public boolean delete(Adresse obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Adresse update(Adresse obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adresse find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Adresse> list() {
		// TODO Auto-generated method stub
		return null;
	}


}
