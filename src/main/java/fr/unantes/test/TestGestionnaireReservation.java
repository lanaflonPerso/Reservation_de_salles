package fr.unantes.test;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.Materiel;
import fr.unantes.beans.MaterielFixe;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireReservations;
import fr.unantes.gestionnaires.GestionnaireTarifs;

public class TestGestionnaireReservation {
	
	GestionnaireReservations gestionnaire = GestionnaireReservations.getInstance();
	GestionnaireTarifs gestionnairet = GestionnaireTarifs.getInstance();
	Adresse adresse;
	Batiment batiment;
	Date date;
	Duree duree;
	Manifestation manifestation;
	Titre titre;
	Origine origine;
	TypeSalle typeSalle;
	Demandeur demandeur;
	Salle salle;
	Reservation reservation;
	
	@Before
	public void setUp() throws Exception {
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		batiment = new Batiment(1, "Faculté", adresse);
		date = new Date();
		date.setYear(2000);
		date.setMonth(1);
		date.setDate(1);
		date.setHours(20);
		duree = new Duree(1, "Un jour entier", 4);
		manifestation = new Manifestation(1, "Anniversaire", 4);
		titre = new Titre(1,"Monsieur", 2);
		origine = new Origine(1, "Européen", 1);
		typeSalle = new TypeSalle(1,"reunion", 4);
		demandeur = new Demandeur(1, "Geoffrou", adresse, origine, titre);
		salle = new Salle(2, 23, 1, 20, typeSalle);
		reservation = new Reservation("1A448", date, 100.0, salle, duree, manifestation);
	}

	@After
	public void tearDown() throws Exception{
		reservation.getListeMateriels().clear();
		salle.getListeMateriel().clear();
		salle.getListeReservation().clear();
	}
	
	@Test
	public void testAjoutReservation(){
		gestionnaire.reserveSalle(demandeur, salle, manifestation, duree);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutReservationSallePrise(){
		gestionnaire.reserveSalle(demandeur, salle, manifestation, duree);
		Demandeur demandeur2 = new Demandeur(2, "Philippe", adresse, origine, titre);
		gestionnaire.reserveSalle(demandeur2, salle, manifestation, duree);
	}
	
	@Test
	public void testAjoutReservationAutreSalle(){
		gestionnaire.reserveSalle(demandeur, salle, manifestation, duree);
		Demandeur demandeur2 = new Demandeur(2, "Estelle", adresse, origine, titre);
		Salle salle2 = new Salle(2, 24, 1, 20, typeSalle);
		gestionnaire.reserveSalle(demandeur2, salle2, manifestation, duree);
	}
	
	@Test(expected = Exception.class)
	public void testAnnulerReservationNulle(){
		gestionnaire.AnnulationReserv(demandeur, reservation);
	}
	
	@Test
	public void testAnnulerReservation(){
		gestionnaire.reserveSalle(demandeur, salle, manifestation, duree);
		gestionnaire.AnnulationReserv(demandeur, reservation);
	}
	
}
