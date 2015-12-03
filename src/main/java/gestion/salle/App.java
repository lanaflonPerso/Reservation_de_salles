package gestion.salle;

import java.util.ArrayList;
import java.util.Scanner;

import fr.unantes.bdd.Connexion;
import fr.unantes.beans.Administrateur;
import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;
import fr.unantes.dao.DAO;
import fr.unantes.dao.DAOFactory;
import fr.unantes.gestionnaires.GestionnaireDemandeurs;
import fr.unantes.gestionnaires.GestionnaireLocaux;
import fr.unantes.gestionnaires.GestionnaireReservations;
import fr.unantes.gestionnaires.GestionnaireTarifs;

public class App {


	public static void main(String[] args) {
		
		GestionnaireLocaux gestionnaireL = GestionnaireLocaux.getInstance();
		GestionnaireTarifs gestionnaireT = GestionnaireTarifs.getInstance();
		GestionnaireReservations gestionnaireR = GestionnaireReservations.getInstance();
		GestionnaireDemandeurs gestionnaireD = GestionnaireDemandeurs.getInstance();
		
		
		try {
			gestionnaireL.ajoutBatiment(1, "universite", new Adresse("1","1","44000","Nantes"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	
}
		

