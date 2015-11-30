package fr.unantes.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.unantes.beans.Origine;

public class OrigineDAO extends DAO<Origine>{

	public OrigineDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Origine create(Origine obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Origine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Origine update(Origine obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Origine find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Origine> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
