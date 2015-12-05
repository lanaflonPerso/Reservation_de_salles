package fr.unantes.test;

import static org.junit.Assert.*;

import java.util.Map;

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

	GestionnaireTarifs gestionnaire = GestionnaireTarifs.getInstance();
	Titre titre;
	Origine origine;
	TypeMateriel typeMateriel;
	TypeSalle typeSalle;
	Manifestation manifestation;
	Duree duree;
	
	@Before
	public void setUp() throws Exception {
		titre = new Titre(1, "directeur", 2);
		origine = new Origine(1,"europeen", 2);
		typeMateriel = new TypeMateriel(1, "meuble", 2);
		typeSalle = new TypeSalle(1, "reunion", 2);
		manifestation = new Manifestation(1, "anniversaire", 2);
		duree = new Duree(1, "jour", 2);
	}

	@After
	public void tearDown() throws Exception {
		gestionnaire.getTitre().clear();
		gestionnaire.getOrigine().clear();
		gestionnaire.getTypeMateriel().clear();
		gestionnaire.getTypeSalle().clear();
		gestionnaire.getManifestation().clear();
		gestionnaire.getDuree().clear();
	}
	
	@Test
	public void testAddPrixTitre(){
		gestionnaire.addPrixTitre("directeur", 2);
		assertTrue(gestionnaire.getPrixTitre("directeur") == 2);
		assertTrue(gestionnaire.contientTitre("directeur"));
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixTitreNegatif(){
		gestionnaire.addPrixOrigine("directeur", -1);
		assertTrue(gestionnaire.getTitre().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixTitreDouble(){
		gestionnaire.addPrixOrigine("directeur", 2);
		gestionnaire.addPrixOrigine("directeur", 2);
		assertTrue(gestionnaire.getTitre().size() == 1);
	}
	
	@Test
	public void testAddPrixOrigine(){
		gestionnaire.addPrixOrigine("européen", 2);
		assertTrue(gestionnaire.getPrixOrigine("européen") == 2);
		assertTrue(gestionnaire.contientOrigine("européen"));
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixOrigineNegatif(){
		gestionnaire.addPrixOrigine("européen", -1);
		assertTrue(gestionnaire.getOrigine().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixOrigineDouble(){
		gestionnaire.addPrixOrigine("européen", 2);
		gestionnaire.addPrixOrigine("européen", 2);
		assertTrue(gestionnaire.getOrigine().size() == 1);
	}
	
	@Test
	public void testAddPrixTypeMateriel(){
		gestionnaire.addPrixTypeMateriel("meuble", 2);
		assertTrue(gestionnaire.getPrixTypeMateriel("meuble") == 2);
		assertTrue(gestionnaire.contientTypeMateriel("meuble"));
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixTypeMaterielNegatif(){
		gestionnaire.addPrixTypeMateriel("meuble", -1);
		assertTrue(gestionnaire.getTypeMateriel().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixTypeMaterielDouble(){
		gestionnaire.addPrixTypeMateriel("meuble", 2);
		gestionnaire.addPrixTypeMateriel("meuble", 2);
		assertTrue(gestionnaire.getTypeMateriel().size()==1);
	}
	
	@Test
	public void testAddPrixTypeSalle(){
		gestionnaire.addPrixTypeSalle("reunion", 2);
		assertTrue(gestionnaire.getPrixTypeSalle("reunion") == 2);
		assertTrue(gestionnaire.contientTypeSalle("reunion"));
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixTypeSalleNegatif(){
		gestionnaire.addPrixTypeSalle("reunion", -1);
		assertTrue(gestionnaire.getTypeSalle().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixTypeSalleDouble(){
		gestionnaire.addPrixTypeSalle("reunion", 2);
		gestionnaire.addPrixTypeSalle("reunion", 2);
		assertTrue(gestionnaire.getTypeSalle().size() == 1);
	}
	

	
	@Test
	public void testAddPrixManifestation(){
		gestionnaire.addPrixManifestation("anniversaire", 2);
		assertTrue(gestionnaire.getPrixManifestation("anniversaire") == 2);
		assertTrue(gestionnaire.contientManifestation("anniversaire"));
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixManifestationNegatif(){
		gestionnaire.addPrixManifestation("anniversaire", -1);
		assertTrue(gestionnaire.getManifestation().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixManifestationDouble(){
		gestionnaire.addPrixManifestation("anniversaire", 2);
		gestionnaire.addPrixManifestation("anniversaire", 2);
		assertTrue(gestionnaire.getManifestation().size() == 1);
	}
	

	@Test
	public void testAddPrixDuree(){
		gestionnaire.addPrixDuree("jour", 2);
		assertTrue(gestionnaire.getPrixDuree("jour") == 2);
		assertTrue(gestionnaire.contientDuree("jour"));
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixDureeNegatif(){
		gestionnaire.addPrixDuree("jour", -1);
		assertTrue(gestionnaire.getDuree().isEmpty());
	}
	
	@Test(expected = Exception.class)
	public void testAddPrixDureeDouble(){
		gestionnaire.addPrixDuree("jour", 2);
		gestionnaire.addPrixDuree("jour", 2);
		assertTrue(gestionnaire.getDuree().size() == 1);
	}
	
	@Test
	public void calculTarif(){
		
	}
	
	
}
