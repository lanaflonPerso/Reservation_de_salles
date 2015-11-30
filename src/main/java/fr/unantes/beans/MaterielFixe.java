package fr.unantes.beans;

public class MaterielFixe extends Materiel{

	private Salle salle;

	public MaterielFixe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterielFixe(int code_inv, String nom, TypeMateriel type) {
		super(code_inv, nom, type);
		// TODO Auto-generated constructor stub
	}

	public MaterielFixe(int code_inv, String nom, TypeMateriel type, Salle salle) {
		super(code_inv, nom, type);
		this.salle = salle;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	
	

}
