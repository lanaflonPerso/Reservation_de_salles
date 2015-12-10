package fr.unantes.gestionnaires.interfaces;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.MaterielFixe;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;

public interface InterfaceLocaux {
	
	/**
	 * 
	 * @param no le numero de la rue
	 * @param adresse le nom de la rue
	 * @param code le code postal
	 * @param ville la ville de l'adresse
	 * @return true si l'adresse existe, false sinons
	 */
	public boolean adresseExists(String no, String adresse, String code, String ville);
	
	/**
	 * 
	 * @param no le numero de la rue
	 * @param adresse le nom de la rue
	 * @param code le code postal
	 * @param ville la ville de l'adresse
	 * @return l'adresse correpondant aux paramètres
	 * @throws Exception si l'adresse n'existe pas
	 */
	public Adresse getAdresse(String no, String adresse, String code, String ville) throws Exception;
	
	/**
	 * 
	 * @param no le numero de la rue
	 * @param adresse le nom de la rue
	 * @param code le code postal
	 * @param ville la ville de l'adresse
	 * @throws Exception si l'adresse est déjà liée à un batiment
	 */
	public void ajouterAdresse(String no, String adresse, String code, String ville) throws Exception;
	
	
	/**
	 * 
	 * @param noBat le numer odu batiment
	 * @return true si le batiment existe, false sinon
	 */
	public boolean batimentExists(int noBat);
	
	/**
	 * 
	 * @param noBat le numer odu batiment
	 * @return le batiment qui correspond au numero
	 * @throws Exception si aucun batiment ne correspond
	 */
	public Batiment getBatiment(int noBat) throws Exception;
	
	/**
	 * 
	 * @param adresse l'adresse du batiment
	 * @return le batiment qui est a l'adresse
	 * @throws Exception si aucun batiment ne correspond
	 */
	public Batiment getBatiment(Adresse adresse) throws Exception;
	
	/**
	 * 
	 * @param nom le nom des batiments a rechercher
	 * @return la liste des batiments ayant pour nom le nom passé en paramètre
	 */
	public ArrayList<Batiment> getBatiments(String nom);
	
	/**
	 * 
	 * @param no_bat le numero du batiment
	 * @param nom le nom du batiment
	 * @param adresse l'adresse du batiment
	 * @throws Exception si un batiment possède déjà ce numero ou cette adresse
	 */
	public void ajouterBatiment(int noBat, String nom, Adresse adresse) throws Exception;
	
	/**
	 * 
	 * @param no_bat le numero du batiment à supprimer
	 * @throws Exception si le batiment n'existe pas
	 */
	public void supprimerBatiment(int noBat) throws Exception;
	
	/**
	 * 
	 * @param noBat le numero du batiment
	 * @param nom le nom du batiment à modifier
	 * @throws Exception si aucun batiment ne possède ce numero
	 */
	public void modifierBatiment(int noBat, String nom) throws Exception;
	
	/**
	 * 
	 * @param noEtage l'etage de la salle
	 * @param noSalle le numero de la salle
	 * @param noBatiment le numero du batiment
	 * @return true si la salle existe, false sinon
	 */
	public boolean salleExists(int noEtage, int noSalle, int noBatiment);
	
	/**
	 * 
	 * @param noEtage l'etage de la salle
	 * @param noSalle le numero de la salle
	 * @param noBatiment le numero du batiment
	 * @return la salle correspondant aux trois numeros passés en paramètre formants la clée
	 * @throws Exception si aucune salle ne correspond
	 */
	public Salle getSalle(int noEtage, int noSalle, int noBatiment) throws Exception;
	
	/**
	 * 
	 * @param type le type de salle sur lequel rechercher
	 * @return la liste des salles ayant pour type de salle le type passé en paramètre
	 */
	public ArrayList<Salle> getSalles(TypeSalle type);
	
	/**
	 * 
	 * @param noBat le numero de batiment dans lequel rechercher
	 * @return la liste des salles du batiment ayant pour numero no_bat
	 */
	public ArrayList<Salle> getSallesParBatiment(int noBat);
	
	/**
	 * 
	 * @param noEtage le numero d'étage à rechercher
	 * @return la liste des salles étants à l'etage no_etage
	 */
	public ArrayList<Salle> getSallesParEtage(int noEtage);
	
	/**
	 * 
	 * @param no_salle le numer ode salle à rechercher
	 * @return la liste des salles ayant pour numero de salle no_salle
	 */
	public ArrayList<Salle> getSallesParNumero(int noSalle);
	
	/**
	 * 
	 * @param noEtage le numero d'etage de la salle
	 * @param noSalle le numero de salle
	 * @param noBat le numéro de batiment dans lequel ajouter la salle
	 * @param superficie la superficie de la salle
	 * @param type le type de salle
	 * @throws Exception si la superficie de la salle est nulle ou négative, si le batiment n'existe pas ou si la salle existe déjà
	 */
	public void ajouterSalle(int noEtage, int noSalle, int noBat, int superficie, TypeSalle type) throws Exception;
	
	/**
	 * 
	 * @param noEtage l'etage de la salle à supprimer
	 * @param noSalle le numéro de la salle à supprimer
	 * @param noBatiment le numéro de batiment dans lequel on supprime la salle
	 * @throws Exception si la salle n'existe pas
	 */
	public void supprimerSalle(int noEtage, int noSalle, int noBatiment) throws Exception;
	
	/**
	 * 
	 * @param noEtage l'etage de la salle à modifier
	 * @param noSalle le numer ode salle à modifier
	 * @param noBatiment le numér ode batiment dans lequel on modifie la salle
	 * @param superficie nouvelle superficie de la salle 
	 * @param type nouveau type de salle
	 */
	public void modifierSalle(int noEtage, int noSalle, int noBatiment, int superficie, TypeSalle type);
	
	/**
	 * 
	 * @param codeInv
	 * @return true si le matériel existe, false sinon
	 */
	public boolean materielExists(int codeInv);
	
	/**
	 * 
	 * @param codeInv le code du matériel à rechercher
	 * @return le matériel fixe qui correspond au code_inv
	 * @throws Exception si le matériel n'existe pas
	 */
	public MaterielFixe getMaterielFixe(int codeInv) throws Exception;
	
	/**
	 * 
	 * @param codeInv le code du matériel à rechercher
	 * @return le matériel fixe qui correspond au codeInv
	 * @throws Exception si le matériel n'existe pas
	 */
	public MaterielMobile getMaterielMobile(int codeInv) throws Exception;
	
	/**
	 * 
	 * @param type le type de matériel sur lequel rechercher
	 * @return la liste de matériaux fixes qui ont pour type de matériel le type passé en paramètre
	 */
	public ArrayList<MaterielFixe> getMateriauxFixes(TypeMateriel type);
	
	/**
	 * 
	 * @param salle la salle dans laquelle rechercher les matériaux
	 * @return la liste des matériaux fixes dans la salle passée en paramètre
	 */
	public ArrayList<MaterielFixe> getMateriauxFixes(Salle salle);

	/**
	 * 
	 * @param type le type de matériel sur lequel rechercher
	 * @return la liste de matériaux mobiles qui ont pour type de matériel le type passé en paramètre
	 */
	public ArrayList<MaterielMobile> getMateriauxMobiles(TypeMateriel type);
	
	/**
	 * 
	 * @param code_inv le code du matériel à ajouter
	 * @param nom ne nom du matériel
	 * @param salle la salle dans laquelle ajouter le matériel fixe
	 * @param type le type de matériel
	 * @throws Exception si le matériel existe déjà
	 */
	public void ajouterMaterielFixe(int code_inv, String nom, Salle salle, TypeMateriel type) throws Exception;
	
	/**
	 * 
	 * @param code_inv le code du matériel à supprimer
	 * @throws Exception si le matériel n'existe pas
	 */
	public void supprimerMaterielFixe(int codeInv) throws Exception;
	
	/**
	 * 
	 * @param codeInv le code du matériel à ajouter
	 * @param nom ne nom du matériel
	 * @param type le type de matériel
	 * @throws Exception si le matériel existe déjà
	 */
	public void ajouterMaterielMobile(int codeInv, String nom, TypeMateriel type) throws Exception;
	
	/**
	 * 
	 * @param code_inv le code du matériel à supprimer
	 * @throws Exception si le matériel n'existe pas
	 */
	public void supprimerMaterielMobile(int codeInv) throws Exception;


}
