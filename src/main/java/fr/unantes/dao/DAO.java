package fr.unantes.dao;

import java.sql.Connection;
import java.util.ArrayList;

public abstract class DAO<T> {

	protected Connection connect = null;
	public DAO(Connection conn){
	this.connect = conn;
	}
	
	  public abstract T create(T obj);
	  public abstract boolean delete(T obj);
	  public abstract T update(T obj);
	  public abstract T find(int id);
	  public abstract ArrayList<T> list();

	}
