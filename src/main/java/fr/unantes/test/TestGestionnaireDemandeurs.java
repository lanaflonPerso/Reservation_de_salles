package fr.unantes.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Titre;
import fr.unantes.gestionnaires.GestionnaireDemandeurs;

public class TestGestionnaireDemandeurs {
	
	GestionnaireDemandeurs gestionnaire = GestionnaireDemandeurs.getInstance();
	Demandeur demandeur;
	Adresse adresse;
	Origine origine;
	Titre titre;

	@BeforeClass
	public static void init() throws Exception{
		System.out.println("Test de la classe GestionnaireDemandeurs");
	}
	
	@Before
	public void setUp() throws Exception {
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		origine = new Origine(1,"européen", 2);
		titre = new Titre(1, "monsieur", 2);
		demandeur = new Demandeur(1, "geoffrou", adresse, origine, titre);
		gestionnaire.getListeDemandeurs().add(demandeur);
	}

	@After
	public void tearDown() throws Exception {
		gestionnaire.getListeDemandeurs().clear();
	}

	@Test
	public void testAjoutDemandeur() throws Exception{
		gestionnaire.ajouterDemandeur(2, "ugo", adresse, origine, titre);	
		assertTrue(gestionnaire.getListeDemandeurs().size() == 2);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutMemeDemandeur() throws Exception{
		gestionnaire.ajouterDemandeur(1, "geoffrou", adresse, origine, titre);
		assertTrue(gestionnaire.getListeDemandeurs().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutDemandeurNumIncorrect() throws Exception{
		gestionnaire.ajouterDemandeur(-1, "geoffrou", adresse, origine, titre);
	}
	
	@Test
	public void testsupprimerDemandeur() throws Exception{
		gestionnaire.supprimerDemandeur(1);
		assertTrue(gestionnaire.getListeDemandeurs().size()==0);
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerMemeDemandeur() throws Exception{
		gestionnaire.supprimerDemandeur(1);
		gestionnaire.supprimerDemandeur(1);
		assertTrue(gestionnaire.getListeDemandeurs().size()==0);
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerDemandeurInexistant() throws Exception{
		gestionnaire.supprimerDemandeur(2);
		assertTrue(gestionnaire.getListeDemandeurs().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerDemandeurListeVide() throws Exception{
		gestionnaire.getListeDemandeurs().clear();
		gestionnaire.supprimerDemandeur(1);
		assertTrue(gestionnaire.getListeDemandeurs().size()==0);
	}
	
	@Test
	public void testModifierDemandeur() throws Exception{
		Adresse a = new Adresse("1","rue de l'ecole","44000","Nantes");
		gestionnaire.modifierDemandeur(1,a);
		assertTrue(gestionnaire.getListeDemandeurs().get(0).getAdresse().equals(a));
	}
	
	@Test(expected = Exception.class)
	public void testModifierDemandeurInexistant() throws Exception{
		gestionnaire.modifierDemandeur(3, new Adresse("1","rue de l'ecole","44000","Nantes"));
	}
	
	@Test
	public void testRechercheDemandeurParTitre() throws Exception{
		assertTrue(gestionnaire.getDemandeurs(titre).size() == 1);
	}
	
	@Test
	public void testRechercheDemandeurParTitreNull() throws Exception{
		assertTrue(gestionnaire.getDemandeurs(new Titre(1,"Madame", 3)).size() == 0);
	}
	
	@Test
	public void testRechercheDemandeurParOrigine() throws Exception{
		assertTrue(gestionnaire.getDemandeurs(origine).size()==1);
	}
	
	@Test
	public void testRechercheDemandeurParOrigineNull() throws Exception{
		assertTrue(gestionnaire.getDemandeurs(new Origine(1,"européen", 3)).size() == 0);
	}
	
	@Test
	public void testRechercheDemandeur() throws Exception{
		assertTrue(gestionnaire.getDemandeur(1).equals(demandeur));
	}
	
	@Test(expected = Exception.class)
	public void testRechercheDemandeurNull() throws Exception{
		assertTrue(gestionnaire.getDemandeur(2).equals(null));
	}
	
	@Test
	public void testDemandeurExists() throws Exception{
		assertTrue(gestionnaire.demandeurExists(1));
	}
	
	@Test
	public void testDemandeurExistsPas() throws Exception{
		assertFalse(gestionnaire.demandeurExists(2));
	}
	

	

	
	
	
}
