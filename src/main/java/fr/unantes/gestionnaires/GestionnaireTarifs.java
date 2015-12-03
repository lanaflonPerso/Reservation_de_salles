package fr.unantes.gestionnaires;

import java.util.HashMap;
import java.util.Map;


import fr.unantes.beans.*;

public class GestionnaireTarifs {
	
	private static volatile GestionnaireTarifs instance = null;
	
	//Pour chaque sous classe de Tarif on attribut une hashMap qui va contenir le nom de la classe associé à sa valeur
	private static Map<String,Float> Titre;
	private static Map<String,Float> Origine;
	private static Map<String,Float> TypeMateriel;
	private static Map<String,Float> TypeSalle;
	private static Map<String,Float> Manifestation;
	private static Map<String,Float> Duree;
	
	
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
		Titre = new HashMap<String, Float>();
		Origine = new HashMap<String, Float>();
		TypeMateriel = new HashMap<String, Float>();
		TypeSalle = new HashMap<String, Float>();
		Manifestation = new HashMap<String, Float>();
		Duree = new HashMap<String, Float>();
	}
	
	
	public Map<String, Float> getTitre() {
		return Titre;
	}

	public void setTitre(Map<String, Float> titre) {
		Titre = titre;
	}

	public Map<String, Float> getOrigine() {
		return Origine;
	}

	public void setOrigine(Map<String, Float> origine) {
		Origine = origine;
	}

	public Map<String, Float> getTypeMateriel() {
		return TypeMateriel;
	}

	public void setTypeMateriel(Map<String, Float> typeMateriel) {
		TypeMateriel = typeMateriel;
	}

	public Map<String, Float> getTypeSalle() {
		return TypeSalle;
	}

	public void setTypeSalle(Map<String, Float> typeSalle) {
		TypeSalle = typeSalle;
	}

	public Map<String, Float> getManifestation() {
		return Manifestation;
	}

	public void setManifestation(Map<String, Float> manifestation) {
		Manifestation = manifestation;
	}

	public Map<String, Float> getDuree() {
		return Duree;
	}

	public void setDuree(Map<String, Float> duree) {
		Duree = duree;
	}

	public void setInstance(GestionnaireTarifs instance) {
		GestionnaireTarifs.instance = instance;
	}

	public void addPrixTitre(String nom,float prix){
		Titre.put(nom, prix);
	}
	
	public void addPrixOrigine(String nom,float prix){
		Origine.put(nom, prix);
	}
	
	public void addPrixTypeMateriel(String nom,float prix){
		TypeMateriel.put(nom, prix);
	}
	
	public void addPrixTypeSalle(String nom,float prix){
		TypeSalle.put(nom, prix);
	}
	
	public void addPrixManifestation(String nom,float prix){
		Manifestation.put(nom, prix);
	}
	
	public void addPrixDuree(String nom,float prix){
		Duree.put(nom, prix);
	}
	
	public float getPrixTitre(String nom){
		return Titre.get(nom);
	}
	
	public float getPrixOrigine(String nom){
		return Origine.get(nom);
	}
	
	public float getPrixTypeMateriel(String nom){
		return TypeMateriel.get(nom);
	}
	
	public float getPrixTypeSalle(String nom){
		return TypeSalle.get(nom);
	}
	
	public float getPrixManifestation(String nom){
		return Manifestation.get(nom);
	}
	
	public float getPrixDuree(String nom){
		return Duree.get(nom);
	}
	
	public boolean contientTitre(String nom){
		return Titre.containsKey(nom);
	}
	
	public boolean contientOrigine(String nom){
		return Origine.containsKey(nom);
	}
	
	public boolean contientTypeMateriel(String nom){
		return TypeMateriel.containsKey(nom);
	}
	
	public boolean contientTypeSalle(String nom){
		return TypeSalle.containsKey(nom);
	}
	
	public boolean contientManifestation(String nom){
		return Manifestation.containsKey(nom);
	}
	
	public boolean contientDuree(String nom){
		return Duree.containsKey(nom);
	}
	
	/**
	 * On calcul un tarif à partir des diffenrentes sous classe de tarif servant à determiner un tarif 
	 * 
	 * @param Titre
	 * @param Origine
	 * @param TypeMateriel
	 * @param TypeSalle
	 * @param Manifestation
	 * @param Duree
	 * @return Float res
	 * 
	 * */
	public float calculTarif(Titre t,Origine o,TypeMateriel tm,TypeSalle ts,Manifestation manif,Duree dur){
		float res = Titre.get(t.getLibelle()) + Origine.get(o.getLibelle()) + TypeMateriel.get(tm.getLibelle()) + 
		TypeSalle.get(ts.getLibelle()) + Manifestation.get(manif.getLibelle()) + Duree.get(dur.getLibelle());
		return res;
	}
	
}
