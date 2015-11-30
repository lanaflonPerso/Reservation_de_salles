package fr.unantes.test;

import static org.junit.Assert.assertTrue;

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
	
}
