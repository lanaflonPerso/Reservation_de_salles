package fr.unantes.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeSalle;


public class TestBatiment {

	Batiment batiment; 
	Adresse adresse;
	Salle salle;
	TypeSalle typeSalle;
	
	@Before
	public void setUp() throws Exception {
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		batiment = new Batiment(1, "Faculté", adresse);
		typeSalle = new TypeSalle(1,"reunion", 4);
		salle = new Salle(2, 23, 1, 20, typeSalle);
	}

	@After
	public void tearDown() throws Exception{
		batiment.getListeSalle().clear();
	}
	
	@Test
	public void testAjoutSalle() throws Exception{
		batiment.ajouterSalle(salle);
		assertTrue(batiment.getListeSalle().size() == 1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutSalleNoBatIncorrect() throws Exception{
		batiment.ajouterSalle(new Salle(2,23,2,20,typeSalle));
		assertTrue(batiment.getListeSalle().isEmpty());
	}
	
	@Test
	public void testSupprimerSalle() throws Exception{
		batiment.ajouterSalle(salle);
		batiment.supprimerSalle(salle);
		assertTrue(batiment.getListeSalle().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerSalleInexistante() throws Exception{
		batiment.supprimerSalle(salle);
		batiment.supprimerSalle(salle);
		assertTrue(batiment.getListeSalle().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testGetSalleInnexistante() throws Exception{
		batiment.getSalle(2, 23);
	}
	
	@Test
	public void testGetSalle() throws Exception{
		batiment.ajouterSalle(salle);
		assertTrue(batiment.getSalle(2, 23).equals(salle));
	}
	
	@Test
	public void testGetSalleParType() throws Exception{
		assertTrue(batiment.getSalles(typeSalle).size() == 0);
		batiment.ajouterSalle(salle);
		assertTrue(batiment.getSalles(typeSalle).size() == 1);
		assertTrue(batiment.getSalles(new TypeSalle(2,"fete", 4)).size() == 0);
	}
	
	@Test
	public void testSalleExists() throws Exception{
		batiment.ajouterSalle(salle);
		assertTrue(batiment.salleExists(23, 2));
		batiment.supprimerSalle(salle);
		assertFalse(batiment.salleExists(23, 2));
	}
}
