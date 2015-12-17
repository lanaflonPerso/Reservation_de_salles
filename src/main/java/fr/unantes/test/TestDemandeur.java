package fr.unantes.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.MaterielMobile;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;

public class TestDemandeur {

	Demandeur demandeur; 
	Reservation reservation;
	Adresse adresse;
	Origine origine;
	Titre titre;
	
	@Before
	public void setUp() throws Exception {
		adresse = new Adresse("1","rue","44000","Nantes");
		origine = new Origine(1,"européen",4);
		titre = new Titre(2,"directeur",4);
		demandeur = new Demandeur(1,"geoffrou",adresse,origine, titre);
		reservation = new Reservation();
		reservation.setListeMateriels(new ArrayList<MaterielMobile>());

	}

	@After
	public void tearDown() throws Exception{
		demandeur.getListeReservation().clear();
	}
	
	@Test
	public void testCalculTarif(){
		assertTrue(demandeur.calculTarif()==8);
		demandeur.setTitre(new Titre(3,"PDG",8));
		assertTrue(demandeur.calculTarif() == 12);
	}
	
	@Test(expected = Exception.class)
	public void testAnnulerReservationVide() throws Exception{
		demandeur.annulerReservation(reservation);
	}	
	
	@Test
	public void testAnnulerReservations() throws Exception{
		demandeur.annulerReservations();
		assertTrue(demandeur.getListeReservation().isEmpty());
	}
	
	@Test
	public void testReserver() throws Exception{
		demandeur.reserver(reservation);
		assertTrue(demandeur.getListeReservation().get(0).equals(reservation));
	}
	
	@Test(expected = Exception.class)
	public void testReserverSame() throws Exception{
		demandeur.reserver(reservation);
		demandeur.reserver(reservation);
	}
	
}
