package fr.unantes.gestionnaires;

import java.util.ArrayList;






import fr.unantes.beans.*;
import fr.unantes.gestionnaires.interfaces.InterfaceTarifs;

public class GestionnaireTarifs implements InterfaceTarifs{
	
private static volatile GestionnaireTarifs instance = null;
	
	private ArrayList<Tarif> listeTarif;
	
	public ArrayList<Tarif> getListeTarif() {
		return listeTarif;
	}

	public void setListeTarif(ArrayList<Tarif> listeTarif) {
		this.listeTarif = listeTarif;
	}
	
	
	public final static GestionnaireTarifs getInstance(){
		
		if (GestionnaireTarifs.instance == null){
			synchronized(GestionnaireTarifs.class){
				if(GestionnaireTarifs.instance==null){
					GestionnaireTarifs.instance = new GestionnaireTarifs();
				}
				
			}
		}
		return GestionnaireTarifs.instance;
	}
	
	private GestionnaireTarifs(){
		listeTarif = new ArrayList<Tarif>();
	}

	/**
	 * 
	 * @param code le code tarifaire
	 * @return true si le tarif existe déjà, false sinon
	 */
	@Override
	public boolean tarifExists(int code) {
		// TODO Auto-generated method stub
		for(Tarif each : listeTarif){
			if(each.getCode() == code){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param code le code tarifaire sur lequel rechercher.
	 * @return le tarif correspondant au code tarifaire.
	 * @throws Exception Si aucun tarife ne correspond au code tarifaire.
	 */
	@Override
	public Tarif getTarif(int code) throws Exception {
		// TODO Auto-generated method stub
		for(Tarif each : listeTarif){
			if(each.getCode() == code){
				return each;
			}
		}
		throw new Exception("Aucun tarif avec ce code tarifaire");
	}

	/**
	 * 
	 * @param type le type de tarif.
	 * @return la liste des tarifs ayant pour type le paramètre d'entrée.
	 */
	@Override
	public ArrayList<Tarif> getTarifs(TarifEnumeration type) {
		// TODO Auto-generated method stub
		ArrayList<Tarif> liste = new ArrayList<Tarif>();
		for(Tarif each : listeTarif){
			if(each.getClass().getName().equals(type.name())){
				liste.add(each);
			}
		}
		return liste;
	}

	/**
	 * 
	 * @param code code tarifaire
	 * @param libelle le nom du tarif
	 * @param tarif le prix 
	 * @param typeTarif le type de tarif à ajouter, correspond à l'enumeration de tous les types de tarifs
	 * @throws Exception si le tarif existe déjà ou si le prix est négatif
	 */
	@Override
	public void ajouterTarif(int code, String libelle, double tarif,
			TarifEnumeration type) throws Exception {
		// TODO Auto-generated method stub
		if(tarifExists(code)){
			throw new Exception("Code tarifaire déjà existant");
		}
		if(tarif < 0){
			throw new Exception("Prix négatif");
		}

		if(type.name().equals(TarifEnumeration.Duree.name())){
			listeTarif.add(new Duree(code, libelle, tarif));
		}
		if(type.name().equals(TarifEnumeration.Manifestation.name())){
			listeTarif.add(new Manifestation(code, libelle, tarif));
		}
		if(type.name().equals(TarifEnumeration.TypeMateriel.name())){
			listeTarif.add(new TypeMateriel(code, libelle, tarif));
		}
		if(type.name().equals(TarifEnumeration.TypeSalle.name())){
			listeTarif.add(new TypeSalle(code, libelle, tarif));
		}
		if(type.name().equals(TarifEnumeration.Titre.name())){
			listeTarif.add(new Titre(code, libelle, tarif));
		}
		if(type.name().equals(TarifEnumeration.Origine.name())){
			listeTarif.add(new Origine(code, libelle, tarif));
		}
	}

	/**
	 * 
	 * @param code le code tarifaire du tarif à supprimer.
	 * @throws Exception si le tarif n'existe pas.
	 */
	@Override
	public void supprimerTarif(int code) throws Exception {
		// TODO Auto-generated method stub
		if(!tarifExists(code)){
			throw new Exception("Tarif innexistant");
		}
		listeTarif.remove(getTarif(code));
	}

	/**
	 * Modifie le libelle et le prix d'un tarif en fonction de son code tarifaire.
	 * @param code le code tarifaire du tarif à modifier.
	 * @param libelle le libelle du tarif.
	 * @param tarif le prix du tarif.
	 * @throws Exception si le tarif n'existe pas ou si le prix est négatif.
	 */
	@Override
	public void modifierTarif(int code, String libelle, double tarif)
			throws Exception {
		// TODO Auto-generated method stub
		if(!tarifExists(code)){
			throw new Exception("Tarif innexistant");
		}
		if(tarif < 0){
			throw new Exception("Pris négatif");
		}
		getTarif(code).setLibelle(libelle);
		getTarif(code).setTarif(tarif);
	}

	/**
	 * Calcul le prix d'une réservation en fonction de ses tarifs.
	 * @param reservation la réservation a calculer le prix.
	 * @return le prix de la réservation.
	 */
	@Override
	public double calculTarif(Reservation reservation) {
		// TODO Auto-generated method stub
		return reservation.calculTarif();
	}
	


	
	
	
}
