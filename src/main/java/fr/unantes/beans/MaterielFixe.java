package fr.unantes.beans;

public class MaterielFixe extends Materiel{

	private Salle salle;

	public MaterielFixe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterielFixe(int codeInv, String nom, TypeMateriel type) {
		super(codeInv, nom, type);
		// TODO Auto-generated constructor stub
	}

	public MaterielFixe(Salle salle) {
		super();
		this.salle = salle;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

}
