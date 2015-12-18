package gestion.salle;

import java.util.Date;

import fr.unantes.beans.Adresse;
import fr.unantes.beans.Duree;
import fr.unantes.beans.Manifestation;
import fr.unantes.beans.Origine;
import fr.unantes.beans.Reservation;
import fr.unantes.beans.Salle;
import fr.unantes.beans.TarifEnumeration;
import fr.unantes.beans.Titre;
import fr.unantes.beans.TypeSalle;
import fr.unantes.gestionnaires.GestionnaireDemandeurs;
import fr.unantes.gestionnaires.GestionnaireLocaux;
import fr.unantes.gestionnaires.GestionnaireReservations;
import fr.unantes.gestionnaires.GestionnaireTarifs;

public class App {


	public static void main(String[] args) {
		
		GestionnaireLocaux gestionnaireL = GestionnaireLocaux.getInstance();
		GestionnaireTarifs gestionnaireT = GestionnaireTarifs.getInstance();
		GestionnaireReservations gestionnaireR = GestionnaireReservations.getInstance();
		GestionnaireDemandeurs gestionnaireD = GestionnaireDemandeurs.getInstance();

		try {
			gestionnaireT.ajouterTarif(1, "reunion", 65, TarifEnumeration.TypeSalle);
			gestionnaireT.ajouterTarif(2, "europeen", 10, TarifEnumeration.Origine);
			gestionnaireT.ajouterTarif(3, "etudiant", 20, TarifEnumeration.Titre);
			gestionnaireT.ajouterTarif(4, "journee", 10, TarifEnumeration.Duree);
			gestionnaireT.ajouterTarif(5, "anniversaire", 20, TarifEnumeration.Manifestation);
			gestionnaireL.ajouterBatiment(1, "fac", new Adresse("1","Boulevard Michelet","44000","Nantes"));
			gestionnaireL.ajouterSalle(2, 23,1 , 60, new TypeSalle(1, "reunion", 65));
			gestionnaireD.ajouterDemandeur(1, "geoffrou", new Adresse("1","Boulevard a","44000","Nantes"), new Origine(2,"europeen", 10), new Titre(3, "etudiant", 20));
			gestionnaireR.reserver(360000, gestionnaireD.getDemandeur(1), gestionnaireL.getSalle(2, 23, 1), new Date(), new Duree(4, "journee", 10), new Manifestation(5, "anniversaire", 10));
			
			
			for(Salle each : gestionnaireL.getSallesParBatiment(1)){
				System.out.println("tarif de la salle "+ each.getNoSalle()+" : " + each.calculerTarif()+"€");
			}
			
			for(Reservation each : gestionnaireR.getListeReservation()){
				System.out.println("La réservation de " + each.getDemandeur().getNom() + " du " + each.getDateResa() + " est de " + each.calculTarif() + "€");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	
}
		

