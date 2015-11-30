package fr.unantes.beans;

import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;
import gestion.salle.App;

public class Adresse {

	private String no;
	private String adresse;
	private String code;
	private String ville;
	
	
	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Adresse( String no, String adresse, String code, String ville) {
		super();
		this.no = no;
		this.adresse = adresse;
		this.code = code;
		this.ville = ville;
	}

	

	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}
	

	public String toString(){
		String s ="--------------------\n"
				+this.no+" "+this.adresse
				+"\n"+this.code+" "+this.ville;
		return s;
	}
	
	//Crée une adresse en base
	public Adresse insert(){
		try{
			DAO<Adresse> adresseDao = DAOFactory.getAdresseDAO();
			adresseDao.create(this);
		}
		catch(Exception e){
			e.getMessage();
		}
		return this;
	}
	
	//Supprime une adresse en base
	public void delete(){
		try{
			DAO<Adresse> adresseDao = DAOFactory.getAdresseDAO();
			adresseDao.delete(this);
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	
	//Met a jout une adresse en base
	public Adresse update(){
		try{
			DAO<Adresse> adresseDao = DAOFactory.getAdresseDAO();
			adresseDao.update(this);
		}
		catch(Exception e){
			e.getMessage();
		}
		return this;
	}
	
	//Selectionne une adresse en base selon son ID
	public Adresse select(){
		try{
			DAO<Adresse> adresseDao = DAOFactory.getAdresseDAO();
			//adresseDao.find(this.getId());
		}
		catch(Exception e){
			e.getMessage();
		}
		return this;
	}
	
	
	
	
}
