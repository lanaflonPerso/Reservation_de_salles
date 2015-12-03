package fr.unantes.beans;

import fr.unantes.gestionnaires.GestionnaireTarifs;

public class TypeMateriel extends Tarif{
	
	public TypeMateriel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TypeMateriel(int id, String nom,float tarif) {
		super(id,nom,tarif);
		GestionnaireTarifs gestionnaireT = GestionnaireTarifs.getInstance();
		gestionnaireT.addPrixManifestation(libelle, (float) tarif);
	}
	
	@Override
	protected double calcul() {
		// TODO Auto-generated method stub
		return tarif;
	}
	
	

}
