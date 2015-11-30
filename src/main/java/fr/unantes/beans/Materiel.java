package fr.unantes.beans;

public abstract class Materiel {

	private int code_inv;
	private String nom;
	private TypeMateriel type;
	public Materiel(int code_inv, String nom, TypeMateriel type) {
		super();
		this.code_inv = code_inv;
		this.nom = nom;
		this.type = type;
	}
	public Materiel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCode_inv() {
		return code_inv;
	}
	public void setCode_inv(int code_inv) {
		this.code_inv = code_inv;
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
