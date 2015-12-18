package fr.unantes.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
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
	MaterielMobile materiel;
	
	@BeforeClass
	public static void init() throws Exception{
		System.out.println("Test de la classe GestionnaireReservations");
	}
	
	@Before
	public void setUp() throws Exception {
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		batiment = new Batiment(1, "Faculté", adresse);
		duree = new Duree(1, "Demi journée", 4);
		duree2 = new Duree(2, "une milliseconde", 4);
		date = new Date();
		date.setTime((long) 1449145513010.0);//03/12/2015 - 13:25:00
		manifestation = new Manifestation(3, "Anniversaire", 4);
		titre = new Titre(4,"Monsieur", 2);
		origine = new Origine(5, "Européen", 1);
		typeSalle = new TypeSalle(6,"reunion", 4);
		demandeur = new Demandeur(1, "Geoffrou", adresse, origine, titre);
		salle = new Salle(2, 23, 1, 20, typeSalle);
		materiel = new MaterielMobile(1, "stylo", new TypeMateriel(7, "fournitures", 2));
	}

	@After
	public void tearDown() throws Exception{
		salle.getListeMateriel().clear();
		salle.getListeReservation().clear();
		gestionnaire.getListeReservation().clear();
	}

	@Test
	public void testAjoutReservation() throws Exception{
		salle.getListeReservation().clear();
		Reservation r = gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
		assertTrue(r.getSalle().equals(salle));
		assertTrue(demandeur.getListeReservation().get(0).equals(r));
	}
	
	@Test
	public void testAjoutReservations() throws Exception{
		salle.getListeReservation().clear();
		Reservation r1 = gestionnaire.reserver(10, demandeur, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
		assertTrue(r1.getSalle().equals(salle));
		assertTrue(demandeur.getListeReservation().get(0).equals(r1));
		assertTrue(gestionnaire.getListeReservation().size() == 1);
		Reservation r2 = gestionnaire.reserver(10, demandeur, salle, new Date(), duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 2);
		assertTrue(r2.getSalle().equals(salle));
		assertTrue(demandeur.getListeReservation().get(1).equals(r2));
		assertTrue(gestionnaire.getListeReservation().size() == 2);
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
	public void testAjoutReservationSalleNonDispo() throws Exception{
		Demandeur demandeur2 = new Demandeur(2, "Ugo", adresse, origine, titre);
		gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		gestionnaire.reserver(2, demandeur2, salle, date, duree, manifestation);
		assertTrue(salle.getListeReservation().size() == 1);
	}
	
	//Un demandeur ne peut pas avoir deux réservations dans les mêmes 24h
	@Test(expected = Exception.class)
	public void testAjoutReservationTwice() throws Exception{
		gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		gestionnaire.reserver(2, demandeur, new Salle(), date, duree, manifestation);
	}
	
	@Test(expected = Exception.class)
	public void testAnnulationInnexistante() throws Exception{
		gestionnaire.annuler(1);
	}
	
	@Test
	public void testAnnulation() throws Exception{
		Reservation r = gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		assertTrue(demandeur.getListeReservation().size() == 1);
		assertTrue(salle.getListeReservation().size() == 1);
		assertTrue(gestionnaire.getListeReservation().size() == 1);
		gestionnaire.annuler(r.getRefResa());
		assertTrue(demandeur.getListeReservation().size() == 0);
		assertTrue(salle.getListeReservation().size() == 0);
		assertTrue(gestionnaire.getListeReservation().size() == 0);
	}
	
	@Test
	public void testReservationExists() throws Exception{
		Reservation r = gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		assertTrue(gestionnaire.reservationExists(r.getRefResa()));
		assertFalse(gestionnaire.reservationExists(-1));
	}
	
	@Test
	public void testAjouterMateriel() throws Exception{
		Reservation r = gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		gestionnaire.ajouterMateriel(materiel, r.getRefResa());
		assertTrue(r.getListeMateriels().get(0).equals(materiel));
		assertTrue(materiel.getReservation() == r);
	}
	
	@Test(expected = Exception.class)
	public void testAjouterMaterielOccupe() throws Exception{
		materiel.setReservation(new Reservation());
		Reservation r = gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		gestionnaire.ajouterMateriel(materiel, r.getRefResa());
	}
	
	@Test
	public void testRetirerMateriel() throws Exception{
		Reservation r = gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		gestionnaire.ajouterMateriel(materiel, r.getRefResa());
		assertTrue(r.getListeMateriels().get(0).equals(materiel));
		assertTrue(materiel.getReservation() == r);
		gestionnaire.retirerMateriel(materiel, r.getRefResa());
		assertTrue(r.getListeMateriels().size()==0);
		assertTrue(materiel.getReservation() == null);
	}
	
	@Test(expected = Exception.class)
	public void testRetirerAutreMateriel() throws Exception{
		Reservation r = gestionnaire.reserver(1, demandeur, salle, date, duree, manifestation);
		gestionnaire.retirerMateriel(materiel, r.getRefResa());
	}
}
