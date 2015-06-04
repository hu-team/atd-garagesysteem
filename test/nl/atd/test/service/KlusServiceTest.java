package nl.atd.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Monteur;
import nl.atd.service.AutoService;
import nl.atd.service.KlantService;
import nl.atd.service.KlusService;
import nl.atd.service.MonteurService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KlusServiceTest {
	KlusService klusService = ServiceProvider.getKlusService();
	KlantService klantService = ServiceProvider.getKlantService();
	AutoService autoService = ServiceProvider.getAutoService();
	MonteurService monteurService = ServiceProvider.getMonteurService();

	private Klant k1, k2, k3, k4;
	private Auto a1, a2, a3, a4;
	private Monteur m1, m2;
	private Klus klus1, klus2, klus3, klus4;

	@Before
	public void setUp() throws Exception {
		// Aanmaken van klanten
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxiiemaxx");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("123"));
		k1.setLaatsteBezoek(null);
		// kservice.addKlant(k1);

		k2 = new Klant("Tom Valk");
		k2.setEmail("tomvalk@hotmail.com");
		k2.setGebruikersnaam("tomvalk");
		k2.setWachtwoord(AuthHelper.encryptWachtwoord("456"));
		k2.setLaatsteBezoek(null);
		// kservice.addKlant(k2);

		// Aanmaken van monteurs
		m1 = new Monteur("Benco van Dam", 100001);
		m1.setGebruikersnaam("bencovandam");
		m1.setWachtwoord(AuthHelper.encryptWachtwoord("monteur1"));
		// monteurService.addMonteur(m1);

		m2 = new Monteur("Gerrit Dijkstra", 100002);
		m2.setGebruikersnaam("gerritdijkstra");
		m2.setWachtwoord(AuthHelper.encryptWachtwoord("monteur2"));
		// monteurService.addMonteur(m2);

		// Aanmaken van autos
		a1 = new Auto("Mercedes", "A180", 2015, null);
		a1.setKenteken("GB1231");
		// autoService.addAuto(k1.getGebruikersnaam(), a1);

		a2 = new Auto("Ford", "Mondeo", 2012, null);
		a2.setKenteken("12XVK1");
		// autoService.addAuto(k2.getGebruikersnaam(), a2);

		a3 = new Auto("Volvo", "V60", 2014, null);
		a3.setKenteken("1ZDF53");
		// autoService.addAuto(k1.getGebruikersnaam(), a3);

		a4 = new Auto("Volkswagen", "Golf", 2015, null);
		a4.setKenteken("GH4112");
		// autoService.addAuto(k2.getGebruikersnaam(), a4);

		// Aanmaken van klussen
		klus1 = new Klus(k1, a1);
		klus1.setMonteur(m1);
		klus1.setOmschrijving("Winterbanden vervangen door zomerbanden");
		klus1.setType("Onderhoud");
		klus1.setUren(4);
		//klusService.addKlus(klus1);

		klus2 = new Klus(k1, a3);
		klus2.setMonteur(m1);
		klus2.setOmschrijving("APK");
		klus2.setType("APK");
		klus2.setUren(2);
		//klusService.addKlus(klus2);

	}

	@Test
	public void testGetKlussen() {
		klus3 = klusService.getKlussen().get(0);
		klus4 = klusService.getKlussen().get(1);

		// Klus klus1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Klus klus3 ( Uit Database gehaald )

		// Klus klus2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Klus klus4 ( Uit Database gehaald )

		System.out.println(klus1.getCalendar().get(Calendar.YEAR));
		System.out.println(klus1.getCalendar().get(Calendar.DAY_OF_YEAR));
		System.out.println(klus1.getCalendar().get(Calendar.MINUTE));
		System.out.println(klus3.getCalendar().get(Calendar.YEAR));
		System.out.println(klus3.getCalendar().get(Calendar.DAY_OF_YEAR));
		System.out.println(klus3.getCalendar().get(Calendar.MINUTE));
		
		assertEquals(klus1, klus3);
		assertEquals(klus2, klus4);

		// dit klopt nog niet, calendar uit de database moet gefixt worden

		// Check of als het niet klopt, het ook word gezien

		// assertFalse(a1.equals(a6));
	}
	
	@Test
	public void testGetKlusIdOpKlus() {
		klus3 = klusService.getKlussen().get(0);
		klus4 = klusService.getKlussen().get(1);
		
		int idUitDb = klusService.getKlusIdOpKlus(klus3);
		int testID = klusService.getKlusIdOpKlus(klus1);
		
		System.out
				.println("UitDB ID ->>" + idUitDb + "TEST id ->" + testID + "");
		assertEquals(idUitDb, testID);
	}

	@After
	public void tearDown() throws Exception {
		// query werkt nog niet 
		//klusService.deleteAlleKlussen();
	}

}
