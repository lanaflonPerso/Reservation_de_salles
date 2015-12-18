package fr.unantes.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.unantes.beans.Demandeur;


public class DemandeurDAO extends DAO<Demandeur> {

	public DemandeurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Demandeur create(Demandeur obj) {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<Demandeur> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
