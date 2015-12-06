package fr.unantes.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Demandeur;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TarifEnumeration;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireDemandeurs;
import fr.unantes.gestionnaires.GestionnaireTarifs;

public class TestGestionnaireDemandeurs {
	
	GestionnaireDemandeurs gestionnaire = GestionnaireDemandeurs.getInstance();
	static GestionnaireTarifs gestionnaireT = GestionnaireTarifs.getInstance();
	Demandeur demandeur;
	Adresse adresse;
	Origine origine;
	Titre titre;

	@BeforeClass
	public static void init() throws Exception{
		System.out.println("Test de la classe GestionnaireDemandeurs");
		gestionnaireT.ajoutTarif(1, "monsieur", 30, TarifEnumeration.Titre);
		gestionnaireT.ajoutTarif(2, "européen", 4, TarifEnumeration.Origine);
	}
	
	@Before
	public void setUp() throws Exception {
		adresse = new Adresse("13", "Boulevard Michelet Sciences", "44000", "Nantes");
		origine = (Origine) gestionnaireT.getListeTarif().get(1);
		titre = (Titre) gestionnaireT.getListeTarif().get(0);
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
		Demandeur demandeur2 = new Demandeur(2, "ugo", adresse, origine, titre);
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
	
	@Test
	public void testRechercheDemandeurParTitreNull() throws Exception{
		assertTrue(gestionnaire.rechercheDemandeurParTitre(new Titre(1,"Madame", 3)).size() == 0);
	}
	
	
	
}
