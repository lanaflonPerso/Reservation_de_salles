package fr.unantes.beans;


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
	
	public boolean adresseExists(String no, String adresse, String code,
			String ville){
		if(this.no.equals(no)
				&& this.adresse.equals(adresse)
				&& this.code.equals(code)
				&& this.ville.equals(ville)){
			return true;
		}
		return false;
	}
	
}
