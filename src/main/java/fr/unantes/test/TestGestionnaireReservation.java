package fr.unantes.test;

import static org.junit.Assert.*;

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
		long temps2 = 1; //1milliseconde
		date = new Date();
		date.setTime((long) 1449145513010.0);//03/12/2015 - 13:25:00
		manifestation = new Manifestation(1, "Anniversaire", 4);
		titre = new Titre(1,"Monsieur", 2);
		origine = new Origine(1, "Européen", 1);
		typeSalle = new TypeSalle(1,"reunion", 4);
		demandeur = new Demandeur(1, "Geoffrou", adresse, origine, titre);
		salle = new Salle(2, 23, 1, 20, typeSalle);
		reservation = new Reservation(1, date, 100.0, salle, temps, duree, manifestation, demandeur);
		salle.ajoutReservation(reservation);
	}

	@After
	public void tearDown() throws Exception{
		//reservation.getListeMateriels().clear();
		salle.getListeMateriel().clear();
		salle.getListeReservation().clear();
		gestionnaire.getListeReservation().clear();
	}
	

	
	@Test(expected = Exception.class)
	public void testSalleDispoDureeNegative() throws Exception{
		assertTrue(gestionnaire.salleDispo(salle, date, -1));
	}
	
	@Test
	public void testSalleDispoAucuneReservation() throws Exception{
		salle.getListeReservation().clear();
		assertTrue(gestionnaire.salleDispo(salle, date, 10));
	}
	
	//Test si une salle est disponible avant une autre réservation
	@Test
	public void testSalleDispoAvant() throws Exception{
		Date date2 = new Date();
		date2.setTime((long) 1449145513008.0); //2millisecondes avant une autre reservation
		assertTrue(gestionnaire.salleDispo(salle, date2, 1));
	}
	
	//Test de reservation qui déborde avant
	@Test
	public void testSalleNonDispo1() throws Exception{
		Date date = new Date();
		date.setTime((long) 1449145513009.0); //03/12/2015 - 13:25:00 -1 milliseconde
		assertFalse(gestionnaire.salleDispo(salle, date, 2));
	}
	
	//Test de réservation pour la même date et la même durée
	@Test
	public void testSalleNonDispoMemeDate() throws Exception{
		assertFalse(gestionnaire.salleDispo(salle, date, 36000000));
	}
	
	//Test de réservation avec un créneau qui est dans le crénau d'une autre réservation
	@Test
	public void testSalleNonDispo2() throws Exception{
		Date date = new Date();
		date.setTime((long) 1449145513011.0); //03/12/2015 - 13:25:00 +1 milliseconde
		assertFalse(gestionnaire.salleDispo(salle, date, 35999998));
	}
	
	//Test de reservation qui déborder après
	@Test
	public void testSalleNonDispo3() throws Exception{
		long fin_prec = (long) (1449145513010.0 + 36000000.0 - 1);
		Date date = new Date();
		date.setTime(fin_prec - 1); //03/12/2015 - 23:25:00 -1milliseconde
		assertFalse(gestionnaire.salleDispo(salle, date, fin_prec));
	}
	
	//Test de reservation après la fin d'une autre réservation
	@Test
	public void testSalleDispoApres() throws Exception{
		Date date = new Date();
		long fin_prec = (long) (1449145513010.0 + 36000000.0 );
		date.setTime(fin_prec +1); //03/12/2015 - 23:25:00+1milliseconde
		assertTrue(gestionnaire.salleDispo(salle, date, 36000000));
	}
	
	@Test
	public void testAjoutReservation() throws Exception{
		salle.getListeReservation().clear();
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation, 2.0);
		assertTrue(salle.getListeReservation().size() == 1);
	}
	
	@Test
	public void testAjoutReservations() throws Exception{
		salle.getListeReservation().clear();
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation, 2.0);
		assertTrue(salle.getListeReservation().size() == 1);
		gestionnaire.reserver(10, demandeur, salle, new Date(), duree, manifestation, 2.0);
		assertTrue(salle.getListeReservation().size() == 2);
	}
	
	@Test
	public void testAjoutReservationAnnulee() throws Exception{
		salle.getListeReservation().clear();
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation, 2.0);
		assertTrue(salle.getListeReservation().size() == 1);
		gestionnaire.annuler(salle.getListeReservation().get(0).getRef_resa());
		assertTrue(salle.getListeReservation().size() == 0);
		gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation, 2.0);
		assertTrue(salle.getListeReservation().size() == 1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutReservationN() throws Exception{
		gestionnaire.reserver(36000000, demandeur, salle, date, duree, manifestation, 2.0);
		assertTrue(salle.getListeReservation().size() == 1);
	}
	
}
