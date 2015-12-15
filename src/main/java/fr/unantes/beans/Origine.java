package fr.unantes.beans;



public class Origine extends Tarif{
	
	
	
	public Origine() {
		super();
		
	}

	public Origine(int code, String libelle, double tarif) {
		super(code, libelle, tarif);
	}
	
	
	@Override
	public double calcul() {
		
		return tarif;
	}
	
	
	
	

	
}
