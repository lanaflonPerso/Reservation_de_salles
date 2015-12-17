package fr.unantes.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireReservations;

public class TestGestionnaireReservation {
	
	GestionnaireReservations gestionnaire = GestionnaireReservations.getInstance();
	Adresse adresse;
	Batiment batiment;
	Date date;
	Duree duree;
	Duree duree2;
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
		duree = new Duree(1, "Demi journée", 4);
		long temps = 36000000; // 10heures
		duree2 = new Duree(1, "une milliseconde", 4);
		date = new Date();
		date.setTime((long) 1449145513010.0);//03/12/2015 - 13:25:00
		manifestation = new Manifestation(1, "Anniversaire", 4);
		titre = new Titre(1,"Monsieur", 2);
		origine = new Origine(1, "Européen", 1);
		typeSalle = new TypeSalle(1,"reunion", 4);
		demandeur = new Demandeur(1, "Geoffrou", adresse, origine, titre);
		salle = new Salle(2, 23, 1, 20, typeSalle);
		reservation = new Reservation(1, date, salle, temps, duree, manifestation, demandeur);
		salle.ajoutReservation(reservation);
	}

	@After
	public void tearDown() throws Exception{
		//reservation.getListeMateriels().clear();
		salle.getListeMateriel().clear();
		salle.getListeReservation().clear();
		gestionnaire.getListeReservation().clear();
	}

	@Test
	public void testAjoutReservation() throws Exception{
		salle.getListeReservation().clear();
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
	}
	
	@Test
	public void testAjoutReservations() throws Exception{
		salle.getListeReservation().clear();
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
		gestionnaire.reserver(10, demandeur, salle, new Date(), duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 2);
	}
	
	@Test
	public void testAjoutReservationAnnulee() throws Exception{
		salle.getListeReservation().clear();
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
		gestionnaire.annuler(salle.getListeReservation().get(0).getRefResa());
		assertTrue(salle.getListeReservation().size() == 0);
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutReservationN() throws Exception{
		gestionnaire.reserver(36000000, demandeur, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
	}
	
}
