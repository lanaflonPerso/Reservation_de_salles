package fr.unantes.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.unantes.beans.Materiel;

public class MaterielDAO extends DAO<Materiel>{

	public MaterielDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Materiel create(Materiel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Materiel obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Materiel update(Materiel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Materiel find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Materiel> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
