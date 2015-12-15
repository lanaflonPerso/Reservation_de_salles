package fr.unantes.beans;



public class Titre extends Tarif{
	
	int tarif;
	
	public Titre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Titre(int code, String libelle, double tarif) {
		super(code, libelle, tarif);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double calcul() {
		return tarif;
	}
   
}
