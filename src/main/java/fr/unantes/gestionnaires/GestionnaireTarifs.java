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
	
	
	public static Map<String, Float> getTitre() {
		return Titre;
	}

	public static void setTitre(Map<String, Float> titre) {
		Titre = titre;
	}

	public static Map<String, Float> getOrigine() {
		return Origine;
	}

	public static void setOrigine(Map<String, Float> origine) {
		Origine = origine;
	}

	public static Map<String, Float> getTypeMateriel() {
		return TypeMateriel;
	}

	public static void setTypeMateriel(Map<String, Float> typeMateriel) {
		TypeMateriel = typeMateriel;
	}

	public static Map<String, Float> getTypeSalle() {
		return TypeSalle;
	}

	public static void setTypeSalle(Map<String, Float> typeSalle) {
		TypeSalle = typeSalle;
	}

	public static Map<String, Float> getManifestation() {
		return Manifestation;
	}

	public static void setManifestation(Map<String, Float> manifestation) {
		Manifestation = manifestation;
	}

	public static Map<String, Float> getDuree() {
		return Duree;
	}

	public static void setDuree(Map<String, Float> duree) {
		Duree = duree;
	}

	public static void setInstance(GestionnaireTarifs instance) {
		GestionnaireTarifs.instance = instance;
	}

	public static void addPrixTitre(String nom,float prix){
		Titre.put(nom, prix);
	}
	
	public static void addPrixOrigine(String nom,float prix){
		Origine.put(nom, prix);
	}
	
	public static void addPrixTypeMateriel(String nom,float prix){
		TypeMateriel.put(nom, prix);
	}
	
	public static void addPrixTypeSalle(String nom,float prix){
		TypeSalle.put(nom, prix);
	}
	
	public static void addPrixManifestation(String nom,float prix){
		Manifestation.put(nom, prix);
	}
	
	public static void addPrixDuree(String nom,float prix){
		Duree.put(nom, prix);
	}
	
	public static float getPrixTitre(String nom){
		return Titre.get(nom);
	}
	
	public static float getPrixOrigine(String nom){
		return Origine.get(nom);
	}
	
	public static float getPrixTypeMateriel(String nom){
		return TypeMateriel.get(nom);
	}
	
	public static float getPrixTypeSalle(String nom){
		return TypeSalle.get(nom);
	}
	
	public static float getPrixManifestation(String nom){
		return Manifestation.get(nom);
	}
	
	public static float getPrixDuree(String nom){
		return Duree.get(nom);
	}
	
	public static boolean contientTitre(String nom){
		return Titre.containsKey(nom);
	}
	
	public static boolean contientOrigine(String nom){
		return Origine.containsKey(nom);
	}
	
	public static boolean contientTypeMateriel(String nom){
		return TypeMateriel.containsKey(nom);
	}
	
	public static boolean contientTypeSalle(String nom){
		return TypeSalle.containsKey(nom);
	}
	
	public static boolean contientManifestation(String nom){
		return Manifestation.containsKey(nom);
	}
	
	public static boolean contientDuree(String nom){
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
	public static float calculTarif(Titre t,Origine o,TypeMateriel tm,TypeSalle ts,Manifestation manif,Duree dur){
		float res = Titre.get(t.getLibelle()) + Origine.get(o.getLibelle()) + TypeMateriel.get(tm.getLibelle()) + 
		TypeSalle.get(ts.getLibelle()) + Manifestation.get(manif.getLibelle()) + Duree.get(dur.getLibelle());
		return res;
	}
	
}
