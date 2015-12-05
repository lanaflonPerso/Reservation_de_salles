package fr.unantes.test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireLocaux;
import fr.unantes.gestionnaires.GestionnaireReservations;
import fr.unantes.gestionnaires.GestionnaireTarifs;


public class TestGestionnaireLocaux{
	GestionnaireLocaux gestionnaire = GestionnaireLocaux.getInstance();
	GestionnaireTarifs gestionnaireT = GestionnaireTarifs.getInstance();
	GestionnaireReservations gestionnaireR = GestionnaireReservations.getInstance();
	Adresse adresse;
	Adresse adresse2;
	Batiment batiment;
	TypeSalle type;
	Salle salle;

	
	@Before
	public void setUp() throws Exception {
		//Objets existants
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		batiment = new Batiment(1, "Faculté", adresse);
		type = new TypeSalle(1, "salle de reunion", 4);
		salle = new Salle(2, 23, 1, 60, type);
		batiment.ajoutSalle(salle);
		gestionnaire.getListeBatiments().add(batiment);
		gestionnaire.getListeTypes().add(type);
		
		//Données à emprunter
		adresse2 = new Adresse("10", "Boulevard Amiral Courbet", "44000", "Nantes");
	}

	@After
	public void tearDown() throws Exception {
		gestionnaire.getListeBatiments().clear();
	}

	@Test
	public void testAjoutBatiment() throws Exception{
		gestionnaire.ajoutBatiment(2, "salle des fêtes", adresse2);	
		assertTrue(gestionnaire.getListeBatiments().size() == 2);
	}
		
	@Test(expected = Exception.class)
	public void testAjoutBatimentExistant() throws Exception {
		gestionnaire.ajoutBatiment(1, "Faculté", adresse2);
	}
	
	@Test
	public void testAjoutSalle() throws Exception{
		batiment.ajoutSalle(new Salle(2, 24, 1, 60, type));
	}

	@Test(expected = Exception.class)
	public void testAjoutSalleExistante() throws Exception{
		gestionnaire.ajoutSalle(2, 23, 1, 60, type);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test
	public void testAjoutSalleBatimentVide()throws Exception{
		gestionnaire.getListeBatiments().clear();
		gestionnaire.ajoutBatiment(2, "fac", adresse);
		gestionnaire.ajoutSalle(2, 23, 2, 60, type);
		assertTrue(gestionnaire.getListeBatiments().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutSalleBatimentInexistante() throws Exception{
		gestionnaire.ajoutSalle(1, 1, 100, 50, type);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutSalleSuperficieNegative() throws Exception{
		gestionnaire.ajoutSalle(1, 1, 1, -1, type);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutSalleSuperficieNull() throws Exception{
		gestionnaire.ajoutSalle(1, 1, 1, 0, type);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutAdresseExistante() throws Exception {
		gestionnaire.ajoutBatiment(2, "salle des fêtes", adresse);
	}
	
	
	@Test
	public void testBatimentExistant() {
		assertTrue(gestionnaire.batimentExists(1));
	}
	
	@Test
	public void testBatimentNonExistant(){
		assertFalse(gestionnaire.batimentExists(3));
	}
	
	@Test
	public void testAdresseExistante(){
		assertTrue(gestionnaire.adresseExists("13", "Boulevard Michelet Sciences", "44000", "Nantes"));
	}
	
	@Test
	public void testAdresseNonExistante1(){
		assertFalse(gestionnaire.adresseExists("12", "Boulevard Michelet Sciences", "44000", "Nantes"));
	}
	
	@Test
	public void testAdresseNonExistante2(){
		assertFalse(gestionnaire.adresseExists("13", "Rue du foix", "44000", "Nantes"));
	}
	
	@Test
	public void testAdresseNonExistante3(){
		assertFalse(gestionnaire.adresseExists("13", "Boulevard Michelet Sciences", "44300", "Nantes"));
	}
	
	@Test
	public void testAdresseNonExistante4(){
		assertFalse(gestionnaire.adresseExists("13", "Boulevard Michelet Sciences", "44000", "Tours"));
	}
	
	@Test
	public void testRechercheBatimentParNom() throws Exception{
		assertTrue(batiment.equals(gestionnaire.rechercheBatimentParNom("Faculté").get(0)));
	}
	
	@Test
	public void testAucunRechercheBatimentParNom() throws Exception{
		assertTrue(gestionnaire.rechercheBatimentParNom("Batiment inexistant").isEmpty());
	}
	
	@Test
	public void testRechercheBatimentParNumero() throws Exception{
		assertTrue(batiment == gestionnaire.rechercheBatimentParNumero(1));
	}
	
	@Test(expected = Exception.class)
	public void testAucunRechercheBatimentParNumero() throws Exception{
		assertTrue(batiment == gestionnaire.rechercheBatimentParNumero(22));
	}
	
	@Test
	public void testRechercheBatimentParAdresse() throws Exception{
		assertTrue(batiment == gestionnaire.rechercheBatimentParAdresse(adresse));
	}
	
	@Test(expected = Exception.class)
	public void testAucunRechercheBatimentParAdresse() throws Exception{
		assertTrue(batiment == gestionnaire.rechercheBatimentParAdresse(adresse2));
	}
	
	@Test
	public void testRechercheSalleParBatiment() throws Exception{
		assertTrue(salle == gestionnaire.rechercheSalleParBatiment(1).get(0));
	}
	
	@Test
	public void testAucunRechercheSalleParBatiment() throws Exception{
		assertTrue(gestionnaire.rechercheSalleParBatiment(12).isEmpty());
	}
	
	@Test
	public void testRechercheSalleParEtage() throws Exception{
		assertTrue(salle == gestionnaire.rechercheSalleParEtage(2).get(0));
	}
	
	@Test
	public void testAucunRechercheSalleParEtage() throws Exception{
		assertTrue(gestionnaire.rechercheSalleParEtage(12).isEmpty());
	}
	
	@Test
	public void testRechercheSalleParNumero() throws Exception{
		assertTrue(salle == gestionnaire.rechercheSalleParNumero(23).get(0));
	}
	
	@Test
	public void testAucunRechercheSalleParNumero() throws Exception{
		assertTrue(gestionnaire.rechercheSalleParNumero(12).isEmpty());
	}
	
	@Test
	public void testRechercheSalleParType() throws Exception{
		assertTrue(salle == gestionnaire.rechercheSalleParType(type).get(0));
	}
	
	@Test(expected = Exception.class)
	public void testAucunRechercheSalleParType() throws Exception{
		assertTrue(gestionnaire.rechercheSalleParType(new TypeSalle()).isEmpty());
	}
	
}
