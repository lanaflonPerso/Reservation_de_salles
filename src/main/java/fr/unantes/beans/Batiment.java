package fr.unantes.beans;

import java.util.ArrayList;


public class Batiment {
	
	private int noBat;
	private String nom;
	private Adresse adresse;
	private ArrayList<Salle> listeSalle;
	
	
	public Batiment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Batiment(int noBat, String nom, Adresse adresse, ArrayList<Salle> listeSalle) {
		super();
		this.noBat = noBat;
		this.nom = nom;
		this.adresse = adresse;
		this.listeSalle = listeSalle;
	}
	
	public Batiment(int noBat, String nom, Adresse adresse){
		super();
		this.noBat = noBat;
		this.nom = nom;
		this.adresse = adresse;
		this.listeSalle = new ArrayList<Salle>();
	}
	public int getNoBat() {
		return noBat;
	}
	public void setNoBat(int noBat) {
		this.noBat = noBat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ArrayList<Salle> getListeSalle() {
		return listeSalle;
	}

	public void setListeSalle(ArrayList<Salle> listeSalle) {
		this.listeSalle = listeSalle;
	}
	
	 /**
	  * Ajouter une salle au batiment
	  * @param salle
	  */
	  public void ajouterSalle(Salle salle){
		  if(this.listeSalle == null){
			  this.listeSalle = new ArrayList<Salle>();
		  }
		  this.listeSalle.add(salle);
	  }

	  /**
	   * Retirer une salle au batiment
	   * @param salle
	   */
	  public void supprimerSalle(Salle salle){
		salle.getListeMateriel().clear();
		salle.getListeReservation().clear();
	  	this.listeSalle.remove(salle);
	  }
	  
	  /**
	   * 
	   * @param no_etage
	   * @param no_salle
	   * @return
	   * @throws Exception
	   */
	  public Salle getSalle(int noEtage, int noSalle) throws Exception{
		  for(Salle each : this.listeSalle){
			  if(each.getNoSalle() == noSalle && each.getNoEtage() == noEtage){
				  return each;
			  }
		  }
		  throw new Exception("Salle inexistante");
	  }
	  
	  /**
	   * 
	   * @param type
	   * @return
	   */
	  public ArrayList<Salle> getSalles(TypeSalle type){
		  ArrayList<Salle> liste = new ArrayList<Salle>();
		  for(Salle each : this.listeSalle){
			  if(each.getType().equals(type)){
				  liste.add(each);
			  }
		  }
		  return liste;
	  }
	  
	  /**
	   * 
	   * @param etage
	   * @return
	   */
	  public ArrayList<Salle> getSallesParEtage(int noEtage){
		  ArrayList<Salle> liste = new ArrayList<Salle>();
		  for(Salle each : this.listeSalle){
			  if(each.getNoEtage() == noEtage){
				  liste.add(each);
			  }
		  }
		  return liste;
	  }
	  
	  /**
	   * 
	   * @param noSalle
	   * @return
	   */
	  public ArrayList<Salle> getSallesParNo(int noSalle){
		  ArrayList<Salle> liste = new ArrayList<Salle>();
		  for(Salle each : this.listeSalle){
			  if(each.getNoSalle() == noSalle){
				  liste.add(each);
			  }
		  }
		  return liste;
	  }
	  
	/**
	 * 
	 * @param noSalle
	 * @param noEtage
	 * @return
	 */
	public boolean salleExists(int noSalle, int noEtage){
		for(Salle each : this.listeSalle){
			if (each.getNoEtage() == noEtage && each.getNoSalle() == noSalle){
				return true;
			}
		}
		return false;
	}
		
	  /**
	   * 
	   * @param codeInv
	   * @return
	   */
	  public boolean materielExists(int codeInv){
		  for(Salle each : this.listeSalle){
			  if(each.MaterielExists(codeInv)){
				  return true;
			  }
		  }
		  return false;
	  }
	  
	  public MaterielFixe getMateriel(int codeInv) throws Exception{
		  for(Salle each : this.listeSalle){
			return each.getMateriel(codeInv);
		  }
		  throw new Exception("Aucun matériel avec ce codeInv");
	  }
	  
	  /**
	   * 
	   * @param no
	   * @param adresse
	   * @param code
	   * @param ville
	   * @return
	   */
	  public boolean adresseExists(String no, String adresse, String code,
			String ville){
		  if(this.adresse.adresseExists(no, adresse, code, ville)){
			  return true;
		  }
		  return false;
		  }

	public ArrayList<MaterielFixe> getMateriel(TypeMateriel type){
		// TODO Auto-generated method stub
		ArrayList<MaterielFixe> res;
		res = new ArrayList<MaterielFixe>();
		for(Salle s : listeSalle){
			res.addAll(s.getListeMateriel(type));
		}
		return res;
	}

	public ArrayList<MaterielFixe> getMateriel(Salle salle) {
		// TODO Auto-generated method stub
		ArrayList<MaterielFixe> res;
		res = new ArrayList<MaterielFixe>();
		for(Salle s : listeSalle){
			if(s.equals(salle)){
				res.addAll(s.getListeMateriel());
			}
		}
		return res;
	}
	  
	 
	  
	  
}
