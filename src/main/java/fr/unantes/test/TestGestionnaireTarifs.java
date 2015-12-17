package fr.unantes.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
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
import fr.unantes.beans.TarifEnumeration;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeMateriel;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireTarifs;

public class TestGestionnaireTarifs {

	GestionnaireTarifs gestionnaire = GestionnaireTarifs.getInstance();
	/*
	Titre titre;
	Origine origine;
	TypeMateriel typeMateriel;
	TypeSalle typeSalle;
	Manifestation manifestation;
	Duree duree;
	*/
	@Before
	public void setUp() throws Exception {
		/*
		titre = new Titre(1, "directeur", 2);
		origine = new Origine(1,"europeen", 2);
		typeMateriel = new TypeMateriel(1, "meuble", 2);
		typeSalle = new TypeSalle(1, "reunion", 2);
		manifestation = new Manifestation(1, "anniversaire", 2);
		duree = new Duree(1, "jour", 2);
		*/
	}

	@After
	public void tearDown() throws Exception {
		gestionnaire.getListeTarif().clear();
	}
	
	@Test
	public void testTarifExists() throws Exception{
		gestionnaire.ajouterTarif(1, "journee", 20, TarifEnumeration.Duree);
		assertTrue(gestionnaire.tarifExists(1));
	}
	
	@Test
	public void testTarifNotExists(){
		assertFalse(gestionnaire.tarifExists(1));
	}
	
	@Test
	public void testAjoutDuree()throws Exception{
		gestionnaire.ajouterTarif(1, "journee", 20, TarifEnumeration.Duree);
		assertTrue(gestionnaire.getListeTarif().get(0).getCode() == 1);
		assertTrue(gestionnaire.getListeTarif().get(0).getLibelle().equals("journee"));
		assertTrue(gestionnaire.getListeTarif().get(0).getTarif() == 20);
		assertTrue(gestionnaire.getListeTarif().get(0).getClass().equals(Duree.class));
	}
	
	@Test(expected = Exception.class)
	public void testAjoutDureeExistante() throws Exception{
		gestionnaire.ajouterTarif(1, "journee", 20, TarifEnumeration.Duree);
		gestionnaire.ajouterTarif(1, "journee", 20, TarifEnumeration.Duree);
	}
	
	@Test(expected = Exception.class)
	public void testAjoutDureeNegatif() throws Exception{
		gestionnaire.ajouterTarif(1, "journee", -1, TarifEnumeration.Duree);
	}
	
	@Test
	public void testCalculTarif() throws Exception{
		gestionnaire.ajouterTarif(1, "duree", 20, TarifEnumeration.Duree);
		gestionnaire.ajouterTarif(2, "titre", 20, TarifEnumeration.Titre);
		gestionnaire.ajouterTarif(3, "origine", 20, TarifEnumeration.Origine);
		gestionnaire.ajouterTarif(4, "manifestation", 20, TarifEnumeration.Manifestation);
		gestionnaire.ajouterTarif(5, "typeSalle", 20, TarifEnumeration.TypeSalle);
		gestionnaire.ajouterTarif(6, "typeMateriel", 20, TarifEnumeration.TypeMateriel);
		Duree duree = (Duree) gestionnaire.getListeTarif().get(0);
		Titre titre = (Titre) gestionnaire.getListeTarif().get(1);
		Origine origine = (Origine) gestionnaire.getListeTarif().get(2);
		Manifestation manifestation = (Manifestation) gestionnaire.getListeTarif().get(3);
		TypeSalle typeSalle =  (TypeSalle) gestionnaire.getListeTarif().get(4);
		TypeMateriel typeMateriel = (TypeMateriel) gestionnaire.getListeTarif().get(5);
		MaterielFixe materielF = new MaterielFixe(1, "table", typeMateriel);
		Salle salle = new Salle(1, 1, 1, 1, typeSalle);
		materielF.setSalle(salle);
		salle.ajoutMateriel(materielF);
		Demandeur demandeur = new Demandeur(1, "geoffrey", new Adresse("1","rue du foix", "37000", "Tours"), origine, titre);;
		Reservation reservation = new Reservation(1, new Date(), salle, 36000, duree, manifestation, demandeur);
		assertTrue(gestionnaire.calculTarif(reservation) == 120);
		salle.ajoutMateriel(materielF);
		assertTrue(gestionnaire.calculTarif(reservation) == 140);
		salle.retirerMateriel(materielF);
		assertTrue(gestionnaire.calculTarif(reservation) == 120);
	}
	
}
