package fr.unantes.gestionnaires.interfaces;

import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.MaterielFixe;
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
	 * @param no_bat le numer odu batiment
	 * @return true si le batiment existe, false sinon
	 */
	public boolean batimentExists(int no_bat);
	
	/**
	 * 
	 * @param no_bat le numer odu batiment
	 * @return le batiment qui correspond au numero
	 * @throws Exception si aucun batiment ne correspond
	 */
	public Batiment getBatiment(int no_bat) throws Exception;
	
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
	public void ajouterBatiment(int no_bat, String nom, Adresse adresse) throws Exception;
	
	/**
	 * 
	 * @param no_bat le numero du batiment à supprimer
	 * @throws Exception si le batiment n'existe pas
	 */
	public void supprimerBatiment(int no_bat) throws Exception;
	
	/**
	 * 
	 * @param no_bat le numero du batiment
	 * @param nom le nom du batiment à modifier
	 * @throws Exception si aucun batiment ne possède ce numero
	 */
	public void modifierBatiment(int no_bat, String nom) throws Exception;
	
	/**
	 * 
	 * @param no_etage l'etage de la salle
	 * @param no_salle le numero de la salle
	 * @param no_batiment le numero du batiment
	 * @return true si la salle existe, false sinon
	 */
	public boolean salleExists(int no_etage, int no_salle, int no_batiment);
	
	/**
	 * 
	 * @param no_etage l'etage de la salle
	 * @param no_salle le numero de la salle
	 * @param no_batiment le numero du batiment
	 * @return la salle correspondant aux trois numeros passés en paramètre formants la clée
	 * @throws Exception si aucune salle ne correspond
	 */
	public Salle getSalle(int no_etage, int no_salle, int no_batiment) throws Exception;
	
	/**
	 * 
	 * @param type le type de salle sur lequel rechercher
	 * @return la liste des salles ayant pour type de salle le type passé en paramètre
	 */
	public ArrayList<Salle> getSalles(TypeSalle type);
	
	/**
	 * 
	 * @param no_bat le numero de batiment dans lequel rechercher
	 * @return la liste des salles du batiment ayant pour numero no_bat
	 */
	public ArrayList<Salle> getSallesParBatiment(int no_bat);
	
	/**
	 * 
	 * @param no_etage le numero d'étage à rechercher
	 * @return la liste des salles étants à l'etage no_etage
	 */
	public ArrayList<Salle> getSallesParEtage(int no_etage);
	
	/**
	 * 
	 * @param no_salle le numer ode salle à rechercher
	 * @return la liste des salles ayant pour numero de salle no_salle
	 */
	public ArrayList<Salle> getSallesParNumero(int no_salle);
	
	/**
	 * 
	 * @param no_etage le numero d'etage de la salle
	 * @param no_salle le numero de salle
	 * @param no_bat le numéro de batiment dans lequel ajouter la salle
	 * @param superficie la superficie de la salle
	 * @param type le type de salle
	 * @throws Exception si la superficie de la salle est nulle ou négative, si le batiment n'existe pas ou si la salle existe déjà
	 */
	public void ajouterSalle(int no_etage, int no_salle, int no_bat, int superficie, TypeSalle type) throws Exception;
	
	/**
	 * 
	 * @param no_etage l'etage de la salle à supprimer
	 * @param no_salle le numéro de la salle à supprimer
	 * @param no_batiment le numéro de batiment dans lequel on supprime la salle
	 * @throws Exception si la salle n'existe pas
	 */
	public void supprimerSalle(int no_etage, int no_salle, int no_batiment) throws Exception;
	
	/**
	 * 
	 * @param no_etage l'etage de la salle à modifier
	 * @param no_salle le numer ode salle à modifier
	 * @param no_batiment le numér ode batiment dans lequel on modifie la salle
	 * @param superficie nouvelle superficie de la salle 
	 * @param type nouveau type de salle
	 */
	public void modifierSalle(int no_etage, int no_salle, int no_batiment, int superficie, TypeSalle type);
	
	/**
	 * 
	 * @param code_inv
	 * @return true si le matériel existe, false sinon
	 */
	public boolean materielExists(int code_inv);
	
	/**
	 * 
	 * @param code_inv le code du matériel à rechercher
	 * @return le matériel fixe qui correspond au code_inv
	 * @throws Exception si le matériel n'existe pas
	 */
	public MaterielFixe getMateriel(int code_inv) throws Exception;
	
	/**
	 * 
	 * @param type le type de matériel sur lequel rechercher
	 * @return la liste de matériaux fixes qui ont pour type de matériel le type passé en paramètre
	 */
	public ArrayList<MaterielFixe> getMateriaux(TypeMateriel type);
	
	/**
	 * 
	 * @param salle la salle dans laquelle rechercher les matériaux
	 * @return la liste des matériaux fixes dans la salle passée en paramètre
	 */
	public ArrayList<MaterielFixe> getMateriaux(Salle salle);
	
	/**
	 * 
	 * @param code_inv le code du matériel à ajouter
	 * @param nom ne nom du matériel
	 * @param salle la salle dans laquelle ajouter le matériel fixe
	 * @param type le type de matériel
	 * @throws Exception si le matériel existe déjà
	 */
	public void ajouterMateriel(int code_inv, String nom, Salle salle, TypeMateriel type) throws Exception;
	
	/**
	 * 
	 * @param code_inv le code du matériel à supprimer
	 * @throws Exception si le matériel n'existe pas
	 */
	public void supprimerMateriel(int code_inv) throws Exception;


}
