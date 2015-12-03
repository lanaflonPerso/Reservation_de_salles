package fr.unantes.beans;

import fr.unantes.gestionnaires.GestionnaireTarifs;

public class TypeSalle extends Tarif{

	public TypeSalle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeSalle(int code, String libelle, double tarif) {
		super(code, libelle, tarif);
		GestionnaireTarifs gestionnaireT = GestionnaireTarifs.getInstance();
		gestionnaireT.addPrixManifestation(libelle, (float) tarif);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected double calcul() {
		// TODO Auto-generated method stub
		return tarif;
	}
	
	


}
