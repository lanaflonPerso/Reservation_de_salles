package fr.unantes.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import fr.unantes.beans.MaterielFixe;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;

public class TestSalle {
	
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
	
	@BeforeClass
	public static void init() throws Exception{
		System.out.println("Test de la classe Salle");
	}

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
	}

	@After
	public void tearDown() throws Exception{
	}
	

	@Test(expected = Exception.class)
	public void testSalleDispoDureeNegative() throws Exception{
		assertTrue(salle.disponible(date, -1));
	}
	
	@Test
	public void testSalleDispoAucuneReservation() throws Exception{
		salle.getListeReservation().clear();
		assertTrue(salle.disponible(date, 10));
	}
	
	//Test si une salle est disponible avant une autre réservation
	@Test
	public void testSalleDispoAvant() throws Exception{
		salle.ajoutReservation(reservation);
		Date date2 = new Date();
		date2.setTime((long) 1449145513008.0); //2millisecondes avant une autre reservation
		assertTrue(salle.disponible(date2, 1));
	}
	
	//Test de reservation qui déborde avant
	@Test
	public void testSalleNonDispo1() throws Exception{
		salle.ajoutReservation(reservation);
		Date date = new Date();
		date.setTime((long) 1449145513009.0); //03/12/2015 - 13:25:00 -1 milliseconde
		assertFalse(salle.disponible(date, 2));
	}
	
	//Test de réservation pour la même date et la même durée
	@Test
	public void testSalleNonDispoMemeDate() throws Exception{
		salle.ajoutReservation(reservation);
		assertFalse(salle.disponible(date, 36000000));
	}
	
	//Test de réservation avec un créneau qui est dans le crénau d'une autre réservation
	@Test
	public void testSalleNonDispo2() throws Exception{
		salle.ajoutReservation(reservation);
		Date date = new Date();
		date.setTime((long) 1449145513011.0); //03/12/2015 - 13:25:00 +1 milliseconde
		assertFalse(salle.disponible(date, 35999998));
	}
	
	//Test de reservation qui déborder après
	@Test
	public void testSalleNonDispo3() throws Exception{
		salle.ajoutReservation(reservation);
		long fin_prec = (long) (1449145513010.0 + 36000000.0 - 1);
		Date date = new Date();
		date.setTime(fin_prec - 1); //03/12/2015 - 23:25:00 -1milliseconde
		assertFalse(salle.disponible(date, fin_prec));
	}
	
	//Test de reservation après la fin d'une autre réservation
	@Test
	public void testSalleDispoApres() throws Exception{
		salle.ajoutReservation(reservation);
		Date date = new Date();
		long fin_prec = (long) (1449145513010.0 + 36000000.0 );
		date.setTime(fin_prec +1); //03/12/2015 - 23:25:00+1milliseconde
		assertTrue(salle.disponible(date, 36000000));
	}
	
	@Test
	public void testRetirerReservation() throws Exception{
		Reservation r = new Reservation();
		r.setSalle(salle);
		salle.getListeReservation().add(r);
		assertTrue(salle.getListeReservation().size()==1);
		salle.retirerReservation(r);
		assertTrue(salle.getListeReservation().size()==0);
	}
	
	@Test(expected = Exception.class)
	public void testRetirerReservationVide() throws Exception{
		salle.retirerReservation(reservation);
	}

	@Test
	public void testRetirerReservations() throws Exception{
		salle.ajoutReservation(reservation);
		assertTrue(salle.getListeReservation().get(0).equals(reservation));
		salle.retirerReservations();
	}
	
	@Test
	public void testMaterielExists(){
		assertFalse(salle.MaterielExists(1));
		MaterielFixe materiel = new MaterielFixe(1, "table", new TypeMateriel(1, "meuble", 2));
		salle.ajoutMateriel(materiel);
		assertTrue(salle.MaterielExists(1));
	}
	
	@Test(expected = Exception.class)
	public void testGetMaterielInexistant() throws Exception{
		salle.getMateriel(2);
	}
	
	@Test
	public void testGetMateriel() throws Exception{
		MaterielFixe materiel = new MaterielFixe(1, "table", new TypeMateriel(1, "meuble", 2));
		salle.ajoutMateriel(materiel);
		assertTrue(salle.getMateriel(1).equals(materiel));
	}
	
	@Test
	public void testGetListMateriel(){
		TypeMateriel type = new TypeMateriel(1, "meuble", 2);
		MaterielFixe materiel = new MaterielFixe(1, "table", type);
		assertTrue(salle.getListeMateriel(type).size() == 0);
		salle.ajoutMateriel(materiel);
		assertTrue(salle.getListeMateriel(type).size() == 1);
	}
	
	@Test
	public void testSalleEquals(){
		Salle s = new Salle();
		s.setNoEtage(2);
		s.setNoSalle(23);
		s.setNoBat(1);
		assertTrue(salle.equals(s));
	}
	

	
	@Test
	public void testRetirerAutreReservation() throws Exception{
		Reservation r = new Reservation();
		r.setSalle(salle);
		salle.getListeReservation().add(r);
		assertTrue(salle.getListeReservation().size()==1);
		salle.retirerReservation(reservation);
		assertTrue(salle.getListeReservation().size()==1);
	}
	
	@Test
	public void testCalculTarif(){
		assertTrue(salle.calculerTarif() == 4 );
		TypeMateriel type = new TypeMateriel(1, "meuble", 2);
		MaterielFixe materiel = new MaterielFixe(1, "table", type);
		salle.ajoutMateriel(materiel);
		assertTrue(salle.calculerTarif() == 6);
		MaterielFixe materiel2 = new MaterielFixe(2, "chaise", type);
		salle.ajoutMateriel(materiel2);
		assertTrue(salle.calculerTarif() == 8);
		salle.retirerMateriel(materiel);
		assertTrue(salle.calculerTarif() == 6 );
	}
	
}
