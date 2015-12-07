package fr.unantes.beans;

import fr.unantes.gestionnaires.GestionnaireTarifs;

public class Duree extends Tarif{

	
	public Duree() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Duree(int code, String libelle, double tarif) {
		super(code, libelle, tarif);
		// TODO Auto-generated constructor stub
	}
	

	
	@Override
	public double calcul() {
		// TODO Auto-generated method stub
		return tarif;
	}

	
}
