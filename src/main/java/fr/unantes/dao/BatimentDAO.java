package fr.unantes.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.unantes.beans.Batiment;


public class BatimentDAO extends DAO<Batiment>{

	public BatimentDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Batiment create(Batiment obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Batiment obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Batiment update(Batiment obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Batiment find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Batiment> list() {
		// TODO Auto-generated method stub
		return null;
	}
}
