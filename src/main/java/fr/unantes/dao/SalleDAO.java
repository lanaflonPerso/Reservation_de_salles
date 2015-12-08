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
		return null;
	}

	@Override
	public boolean delete(Salle obj) {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public ArrayList<Salle> list() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
