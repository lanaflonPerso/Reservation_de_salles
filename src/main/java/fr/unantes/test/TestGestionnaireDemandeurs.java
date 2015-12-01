package fr.unantes.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireDemandeurs;

public class TestGestionnaireDemandeurs {
	
	GestionnaireDemandeurs gestionnaire = new GestionnaireDemandeurs();
	Demandeur demandeur;
	Adresse adresse;
	Origine origine;
	Titre titre;

	@Before
	public void setUp() throws Exception {
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		origine = new Origine(1, "Européen", 2);
		titre = new Titre(1, "Monsieur", 2);
		demandeur = new Demandeur(1, "geoffrou", adresse, origine, titre);
		gestionnaire.getListeDemandeurs().add(demandeur);
	}

	@After
	public void tearDown() throws Exception {
		gestionnaire.getListeDemandeurs().clear();
	}

	@Test
	public void testAjoutDemandeur() throws Exception{
		gestionnaire.ajoutDemandeur(2, "ugo", adresse, origine, titre);	
		assertTrue(gestionnaire.getListeDemandeurs().size() == 2);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutMemeDemandeur() throws Exception{
		gestionnaire.ajoutDemandeur(1, "geoffrou", adresse, origine, titre);
		assertTrue(gestionnaire.getListeDemandeurs().size()==1);
	}
	
	@Test
	public void testsupprimerDemandeur() throws Exception{
		System.out.println("ici : ");
		gestionnaire.supprimerDemandeur(demandeur);
		assertTrue(gestionnaire.getListeDemandeurs().size()==0);
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerMemeDemandeur() throws Exception{
		gestionnaire.supprimerDemandeur(demandeur);
		gestionnaire.supprimerDemandeur(demandeur);
		assertTrue(gestionnaire.getListeDemandeurs().size()==0);
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerDemandeurInexistant() throws Exception{
		Demandeur demandeur2 = new Demandeur(2, "ugo", adresse, origine, titre);
		gestionnaire.supprimerDemandeur(demandeur2);
		assertTrue(gestionnaire.getListeDemandeurs().size()==1);
	}
	
	@Test(expected = Exception.class)
	public void testSupprimerDemandeurListeVide() throws Exception{
		gestionnaire.getListeDemandeurs().clear();
		gestionnaire.supprimerDemandeur(demandeur);
		assertTrue(gestionnaire.getListeDemandeurs().size()==0);
	}
	
	@Test
	public void testDemandeurExists() throws Exception{
		assertTrue(gestionnaire.demandeurExists(1));
	}
	
	@Test
	public void testDemandeurExistsPas() throws Exception{
		assertFalse(gestionnaire.demandeurExists(2));
	}
	
	@Test
	public void testRechercheDemandeurParNumero() throws Exception{
		assertFalse(gestionnaire.rechercheDemandeurParNumero(1).equals(null));
	}
	
	@Test(expected = Exception.class)
	public void testRechercheDemandeurParNumeroNull() throws Exception{
		assertTrue(gestionnaire.rechercheDemandeurParNumero(2).equals(null));
	}
	
	@Test
	public void testRechercheDemandeurParTitre() throws Exception{
		assertFalse(gestionnaire.rechercheDemandeurParTitre(titre).equals(null));
	}
	
	@Test(expected = Exception.class)
	public void testRechercheDemandeurParTitreNull() throws Exception{
		assertTrue(gestionnaire.rechercheDemandeurParTitre(new Titre(2,"Madame", 3)).equals(null));
	}
	
	
	
}
