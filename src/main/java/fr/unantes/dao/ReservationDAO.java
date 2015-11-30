package fr.unantes.dao;

import java.sql.Connection;
import java.util.ArrayList;

import fr.unantes.beans.Reservation;

public class ReservationDAO extends DAO<Reservation> {

	public ReservationDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reservation create(Reservation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reservation update(Reservation obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reservation> list() {
		// TODO Auto-generated method stub
		return null;
	}
}

