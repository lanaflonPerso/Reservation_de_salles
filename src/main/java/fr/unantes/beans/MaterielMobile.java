package fr.unantes.beans;

public class MaterielMobile extends Materiel{
	
	private Reservation reservation;

	public MaterielMobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterielMobile(int codeInv, String nom, TypeMateriel type) {
		super(codeInv, nom, type);
		// TODO Auto-generated constructor stub
	}

	public MaterielMobile(Reservation reservation) {
		super();
		this.reservation = reservation;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	


	

}
