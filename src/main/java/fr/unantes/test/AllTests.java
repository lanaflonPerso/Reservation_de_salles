package fr.unantes.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestBatiment.class,
	TestSalle.class,
	TestDemandeur.class,
	TestReservation.class,
	TestGestionnaireLocaux.class,
	TestGestionnaireDemandeurs.class,
	TestGestionnaireReservation.class,
	TestGestionnaireTarifs.class
})
public class AllTests {

}
