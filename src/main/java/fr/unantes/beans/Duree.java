package fr.unantes.beans;

import fr.unantes.gestionnaires.GestionnaireTarifs;

public class Duree extends Tarif{

	private long millisecondes;
	
	
	public Duree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Duree(int code, String libelle, double tarif) {
		super(code, libelle, tarif);
		GestionnaireTarifs gestionnaireT = GestionnaireTarifs.getInstance();
		gestionnaireT.addPrixManifestation(libelle, (float) tarif);
		// TODO Auto-generated constructor stub
	}
	

	public Duree(long millisecondes) {
		super();
		this.millisecondes = millisecondes;
	}

	public long getMillisecondes() {
		return millisecondes;
	}

	public void setMillisecondes(long millisecondes) {
		this.millisecondes = millisecondes;
	}

	@Override
	public double calcul() {
		// TODO Auto-generated method stub
		return tarif;
	}

	
}
