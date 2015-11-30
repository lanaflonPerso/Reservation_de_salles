package fr.unantes.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.unantes.beans.Titre;

public class TitreDAO extends DAO<Titre>{

	public TitreDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Titre create(Titre obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Titre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Titre update(Titre obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Titre find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Titre> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
