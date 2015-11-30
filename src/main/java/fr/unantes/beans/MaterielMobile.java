package fr.unantes.beans;

public class MaterielMobile extends Materiel{
	
	private Reservation reservation;

	public MaterielMobile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterielMobile(int code_inv, String nom, TypeMateriel type) {
		super(code_inv, nom, type);
		// TODO Auto-generated constructor stub
	}

	public MaterielMobile(int code_inv, String nom, TypeMateriel type,
			Reservation reservation) {
		super(code_inv, nom, type);
		this.reservation = reservation;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	

}
