package fr.unantes.beans;

import fr.unantes.gestionnaires.GestionnaireTarifs;

public class Manifestation extends Tarif{

	public Manifestation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manifestation(int code, String libelle, double tarif) {
		super(code, libelle, tarif);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double calcul() {
		// TODO Auto-generated method stub
		return tarif;
	}
	
	

}
