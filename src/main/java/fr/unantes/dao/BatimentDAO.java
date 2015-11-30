package fr.unantes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Salle;

public class BatimentDAO extends DAO<Batiment>{

	public BatimentDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Batiment create(Batiment obj) {
		// TODO Auto-generated method stub
		try {
			
			//Insertion du bâtiment
			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO batiment (nom, fk_id_adresse) VALUES(?,?)");

			prepare.setString(1, obj.getNom());
			prepare.setInt(2, obj.getAdresse().getId());
			prepare.executeUpdate();
			
			/*
			//On crée les salles si elles n'existent pas
			for(Salle salle : obj.getListeSalle()){
				if(salle.getId() == 0){
					DAO<Salle> salleDAO = DAOFactory.getSalleDAO();
					salle = salleDAO.create(salle);
					
					//On les relies au batiment adequate
					PreparedStatement prepare2 = this.connect
							.prepareStatement("INSERT INTO batiment_salle (nom, adresse) VALUES(?,?)");

					prepare2.setInt(1, obj.getNo_bat());
					prepare2.setInt(2, salle.getId());
					prepare2.executeUpdate();
					
				}
			}
	*/
			obj = this.find(obj.getNo_bat());
		} catch (SQLException e) {
			e.getMessage();
			System.out.println("Erreur lors de l'insertion d'un nouveau batiment");
		}
		return obj;
	}

	@Override
	public boolean delete(Batiment obj) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement prepare = this.connect
					.prepareStatement("DELETE FROM batiment WHERE no_bat="
							+ obj.getNo_bat());
			prepare.executeUpdate();
			
			System.out.println("Batiment "+obj.getNom() + " supprimé");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Batiment update(Batiment obj) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement prepare = this.connect
					.prepareStatement("UPDATE batiment SET nom = '" + obj.getNom()
							+ "' WHERE no_bat = '" + obj.getNo_bat() + "';");
			prepare.executeUpdate();

			obj = this.find(obj.getNo_bat());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Batiment find(int id) {
		// TODO Auto-generated method stub
		Batiment batiment = new Batiment();
		Adresse adresse = new Adresse();
		//Salle salle = new Salle();
		ArrayList<Salle> listeSalle = new ArrayList();
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select batiment.no_bat, batiment.nom, adresse.id, adresse.no, adresse.adresse, adresse.code, adresse.ville from adresse, batiment where adresse.id = batiment.fk_id_adresse and batiment.no_bat='" + id + "'");

			if (result.first()) {
				adresse = new Adresse(result.getInt("id"), result.getString("no"), result.getString("adresse"), result.getString("code") ,result.getString("ville"));
				batiment = new Batiment(result.getInt("no_bat"), result.getString("nom"), adresse, listeSalle);

				//MAJ de la liste des salles
				ResultSet result2 = this.connect.createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY).executeQuery(
						"select salle.id, salle.no_etage, salle.no_salle, salle.no_bat, salle.superficie, salle.fk_id_type_salle from salle, batiment where batiment.no_bat = salle.no_bat and salle.no_bat = "+ batiment.getNo_bat() +"");

				while(result2.next()){
					Salle salle = new Salle();
					salle.setId(result2.getInt("id"));
					salle.setNo_etage(result2.getInt("no_etage"));
					salle.setNo_salle(result2.getInt("no_salle"));
					salle.setNo_bat(result2.getInt("no_bat"));
					salle.setSuperficie(result2.getInt("superficie"));
					batiment.ajoutSalle(salle);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return batiment;
	}

	@Override
	public ArrayList<Batiment> list() {
		// TODO Auto-generated method stub
		ArrayList<Batiment> listeBatiment = new ArrayList();
		ArrayList<Salle> listeSalle = new ArrayList();
		Adresse adresse = new Adresse();
		Batiment batiment = new Batiment();

		try {	
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(
					"select batiment.no_bat, batiment.nom, adresse.id, adresse.no, adresse.adresse, adresse.code, adresse.ville from adresse, batiment where adresse.id = batiment.fk_id_adresse;");
			
			while(result.next()) {
				adresse = new Adresse(result.getInt("id"), result.getString("no"), result.getString("adresse"), result.getString("code") ,result.getString("ville"));
				batiment = new Batiment(result.getInt("no_bat"), result.getString("nom"), adresse, listeSalle);
				listeBatiment.add(batiment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeBatiment;
	}

}
