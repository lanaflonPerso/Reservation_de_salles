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
		return null;
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
		return null;
	}

	@Override
	public ArrayList<TypeSalle> list() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
