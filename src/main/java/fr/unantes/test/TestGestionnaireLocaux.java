package fr.unantes.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireLocaux;


public class TestGestionnaireLocaux{
	GestionnaireLocaux gestionnaire = GestionnaireLocaux.getInstance();

	Adresse adresse;
	Adresse adresse2;
	Batiment batiment;
	TypeSalle typeSalle;
	Salle salle;

	@BeforeClass
	public static void init() throws Exception{
		System.out.println("Test de la classe GestionnaireLocaux");
		
	}
	
	@Before
	public void setUp() throws Exception {
		//Objets existants
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		batiment = new Batiment(1, "Faculté", adresse);
		typeSalle = new TypeSalle(1, "reunion", 30);
		salle = new Salle(2, 23, 1, 60, typeSalle);
		batiment.ajouterSalle(salle);
		gestionnaire.getListeBatiments().add(batiment);
		
		//Données à emprunter
		adresse2 = new Adresse("10", "Boulevard Amiral Courbet", "44000", "Nantes");
	}

	@After
	public void tearDown() throws Exception {
		gestionnaire.getListeBatiments().clear();
	}

	@Test
	public void testAjoutBatiment() throws Exception{
		gestionnaire.ajouterBatiment(2, "salle des fêtes", adresse2);	
		assertTrue(gestionnaire.getListeBatiments().size() == 2);
	}
		
	@Test(expected = Exception.class)
	public void testAjoutBatimentExistant() throws Exception {
		gestionnaire.ajouterBatiment(1, "Faculté", adresse2);
	}
	
	@Test
	public void testAjoutSalle() throws Exception{
		batiment.ajouterSalle(new Salle(2, 24, 1, 60, typeSalle));
	}

	@Test(expected = Exception.class)
	public void testAjoutSalleExistante() throws Exception{
		gestionnaire.ajouterSalle(2, 23, 1, 60, typeSalle);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test
	public void testAjoutSalleBatimentVide()throws Exception{
		gestionnaire.getListeBatiments().clear();
		gestionnaire.ajouterBatiment(2, "fac", adresse);
		gestionnaire.ajouterSalle(2, 23, 2, 60, typeSalle);
		assertTrue(gestionnaire.getListeBatiments().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutSalleBatimentInexistante() throws Exception{
		gestionnaire.ajouterSalle(1, 1, 100, 50, typeSalle);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutSalleSuperficieNegative() throws Exception{
		gestionnaire.ajouterSalle(1, 1, 1, -1, typeSalle);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutSalleSuperficieNull() throws Exception{
		gestionnaire.ajouterSalle(1, 1, 1, 0, typeSalle);
		assertTrue(batiment.getListeSalle().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutAdresseExistante() throws Exception {
		gestionnaire.ajouterBatiment(2, "salle des fêtes", adresse);
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
		assertTrue(batiment.equals(gestionnaire.getBatiments("Faculté").get(0)));
	}
	
	@Test
	public void testAucunRechercheBatimentParNom() throws Exception{
		assertTrue(gestionnaire.getBatiments("Batiment inexistant").isEmpty());
	}
	
	@Test
	public void testRechercheBatimentParNumero() throws Exception{
		assertTrue(batiment == gestionnaire.getBatiment(1));
	}
	
	@Test(expected = Exception.class)
	public void testAucunRechercheBatimentParNumero() throws Exception{
		assertTrue(batiment == gestionnaire.getBatiment(22));
	}
	
	@Test
	public void testRechercheBatimentParAdresse() throws Exception{
		assertTrue(batiment == gestionnaire.getBatiment(adresse));
	}
	
	@Test(expected = Exception.class)
	public void testAucunRechercheBatimentParAdresse() throws Exception{
		assertTrue(batiment == gestionnaire.getBatiment(adresse2));
	}
	
	@Test
	public void testRechercheSalleParBatiment() throws Exception{
		assertTrue(salle == gestionnaire.getSallesParBatiment(1).get(0));
	}
	
	@Test(expected = Exception.class)
	public void testAucunRechercheSalleParBatiment() throws Exception{
		assertTrue(gestionnaire.getSallesParBatiment(12).isEmpty());
	}
	
	@Test
	public void testRechercheSalleParEtage() throws Exception{
		assertTrue(salle == gestionnaire.getSallesParEtage(2).get(0));
	}
	
	@Test
	public void testAucunRechercheSalleParEtage() throws Exception{
		assertTrue(gestionnaire.getSallesParEtage(12).isEmpty());
	}
	
	@Test
	public void testRechercheSalleParNumero() throws Exception{
		assertTrue(salle == gestionnaire.getSallesParNumero(23).get(0));
	}
	
	@Test
	public void testAucunRechercheSalleParNumero() throws Exception{
		assertTrue(gestionnaire.getSallesParNumero(12).isEmpty());
	}
	
	@Test
	public void testRechercheSalleParType() throws Exception{
		assertTrue(salle == gestionnaire.getSalles(typeSalle).get(0));
	}
	
	@Test
	public void testAucunRechercheSalleParType() throws Exception{
		assertTrue(gestionnaire.getSalles(new TypeSalle()).isEmpty());
	}
	
	@Test
	public void testSupprimerSalle() throws Exception{
		gestionnaire.supprimerSalle(2, 23, 1);
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerSalleInnexistante() throws Exception{
		gestionnaire.supprimerSalle(1, 1, 1);
	}
	
}
