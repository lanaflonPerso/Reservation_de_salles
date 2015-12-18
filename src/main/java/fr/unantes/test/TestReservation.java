package fr.unantes.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.unantes.beans.Adresse;
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

public class TestReservation {

	Demandeur demandeur; 
	Reservation reservation;
	Salle salle;
	MaterielMobile materiel;
	
	@BeforeClass
	public static void init() throws Exception{
		System.out.println("Test de la classe Reservation");
	}
	
	@Before
	public void setUp() throws Exception {
		salle = new Salle(1,1,1,1, new TypeSalle(1,"reunion",4));
		demandeur = new Demandeur(1,"geoffrou",new Adresse("1","rue","44000","Nantes"),new Origine(1,"européen",4), new Titre(2,"directeur",4));
		reservation = new Reservation(1, new Date(), salle, 10, new Duree(2,"peu",4), new Manifestation(3,"anniversaire",4), demandeur);
		materiel = new MaterielMobile(1,"table", new TypeMateriel(4,"meuble", 4));
		demandeur.getListeReservation().add(reservation);
		salle.getListeReservation().add(reservation);
	}

	@After
	public void tearDown() throws Exception{
		demandeur.getListeReservation().clear();
		salle.getListeReservation().clear();
		materiel.setReservation(null);
	}
	
	@Test
	public void testAjoutMateriel() throws Exception{
		reservation.ajouterMateriel(materiel);
		assertTrue(reservation.getListeMateriels().get(0).equals(materiel));
	}
	
	@Test(expected = Exception.class)
	public void testAjoutMaterielSame() throws Exception{
		reservation.ajouterMateriel(materiel);
		reservation.ajouterMateriel(materiel);
	}
	
	@Test
	public void testRetirerMateriel() throws Exception{
		reservation.ajouterMateriel(materiel);
		reservation.retirerMateriel(materiel);
	}
	
	@Test
	public void testReserver() throws Exception{
		reservation.reserver(salle);
		assertTrue(reservation.getSalle().equals(salle));
		assertTrue(salle.getListeReservation().contains(reservation));
	}
	
	
	@Test
	public void testAnnuler() throws Exception{
		reservation.reserver(salle);
		reservation.annuler();
	}
	
	@Test
	public void testCalculTarif(){
		assertTrue(reservation.calculTarif() == 20);
	}
	
}
