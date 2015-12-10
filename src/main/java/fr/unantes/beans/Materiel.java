package fr.unantes.beans;

public abstract class Materiel {

	private int codeInv;
	private String nom;
	private TypeMateriel type;
	
	public Materiel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Materiel(int codeInv, String nom, TypeMateriel type) {
		super();
		this.codeInv = codeInv;
		this.nom = nom;
		this.type = type;
	}

	public int getCodeInv() {
		return codeInv;
	}

	public void setCodeInv(int codeInv) {
		this.codeInv = codeInv;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public TypeMateriel getType() {
		return type;
	}

	public void setType(TypeMateriel type) {
		this.type = type;
	}

	public double calculerTarif(){
		return this.type.getTarif();
	}
	
}
