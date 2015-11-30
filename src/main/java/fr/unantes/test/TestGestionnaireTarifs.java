package fr.unantes.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Batiment;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Salle;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireTarifs;

public class TestGestionnaireTarifs {

	GestionnaireTarifs gestionnaire = new GestionnaireTarifs();
	TypeSalle typeSalle;
	Duree duree;
	Manifestation manifestation;
	Origine origine;
	TypeMateriel typeMateriel;
	Titre titre;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testCalculerTarif(){
		
	}
	
}
