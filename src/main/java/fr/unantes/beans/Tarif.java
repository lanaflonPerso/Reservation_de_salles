package fr.unantes.beans;

public abstract class Tarif {

	protected int code;
	protected String libelle;
	protected double tarif;
	public Tarif() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tarif(int code, String libelle, double tarif) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.tarif = tarif;
		
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getTarif() {
		return tarif;
	}
	public void setTarif(double tarif) {
		this.tarif = tarif;
	}
	
	abstract protected double calcul();
}
