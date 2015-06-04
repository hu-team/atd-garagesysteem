package nl.atd.test.service;

import static org.junit.Assert.*;

import nl.atd.helper.AuthHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.service.AutoService;
import nl.atd.service.KlantService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutoServiceTest {
	KlantService klantService = ServiceProvider.getKlantService();
	AutoService autoService = ServiceProvider.getAutoService();

	private Klant k1, k2, k3, k4;
	private Auto a1, a2, a3, a4, a5, a6, a7, a8;

	@Before
	public void setUp() throws Exception {
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxiiemaxx");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("123"));
		k1.setLaatsteBezoek(null);
		klantService.addKlant(k1);

		k2 = new Klant("Tom Valk");
		k2.setEmail("tomvalk@hotmail.com");
		k2.setGebruikersnaam("tomvalk");
		k2.setWachtwoord(AuthHelper.encryptWachtwoord("456"));
		k2.setLaatsteBezoek(null);
		klantService.addKlant(k2);

		a1 = new Auto("Mercedes", "A180", 2015, null);
		a1.setKenteken("GB1231");
		autoService.addAuto(k1.getGebruikersnaam(), a1);

		a2 = new Auto("Ford", "Mondeo", 2012, null);
		a2.setKenteken("12XVK1");
		autoService.addAuto(k2.getGebruikersnaam(), a2);

		a3 = new Auto("Volvo", "V60", 2014, null);
		a3.setKenteken("1ZDF53");
		autoService.addAuto(k1.getGebruikersnaam(), a3);

		a4 = new Auto("Volkswagen", "Golf", 2015, null);
		a4.setKenteken("GH4112");
		autoService.addAuto(k2.getGebruikersnaam(), a4);
	}

	@Test
	public void testGetAlleAutos() {
		a5 = autoService.getAlleAutos().get(0);
		a6 = autoService.getAlleAutos().get(1);

		// Auto a1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a3 ( Uit Database gehaald )

		// Auto a2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a4 ( Uit Database gehaald )

		assertEquals(a1, a5);
		assertEquals(a2, a6);

		// Check of als het niet klopt, het ook word gezien

		assertFalse(a1.equals(a6));
		assertFalse(a1.equals(a2));
		assertFalse(a5.equals(a6));
	}

	@Test
	public void testGetAutosVanKlant() {
		a5 = autoService.getAutosVanKlant("maxiiemaxx").get(0); // Mercedes A180
		a6 = autoService.getAutosVanKlant("maxiiemaxx").get(1); // Volvo V60

		a7 = autoService.getAutosVanKlant("tomvalk").get(0); // Ford Mondeo
		a8 = autoService.getAutosVanKlant("tomvalk").get(1); // Volkswagen Golf

		// Auto a1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a5 ( Uit Database gehaald )
		// Auto a3 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a6 ( Uit Database gehaald )

		assertEquals(a1, a5);
		assertEquals(a3, a6);

		// Auto a2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a7 ( Uit Database gehaald d.m.v. de gebruikersnaam )
		// Auto a4 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a8 ( Uit Database gehaald d.m.v. de gebruikersnaam )

		assertEquals(a2, a7);
		assertEquals(a4, a8);
	}

	@Test(expected = Exception.class)
	public void testGetAutosVanKlant1() {
		// John bestaat niet
		// En heeft dus geen autos, dus een Exception
		a5 = autoService.getAutosVanKlant("John").get(0);
	}

	@Test
	public void testGetAutoOpkenteken() {
		a5 = autoService.getAutoOpKenteken("GB1231"); // Mercedes A180

		// Auto a1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a5 ( Uit Database gehaald d.m.v. het kenteken )

		assertEquals(a1, a5);

		// Als kenteken niet bestaat
		// Wordt null gereturned, dus a6 zou null moeten zijn
		a6 = autoService.getAutoOpKenteken("GG9991");

		assertTrue(a6 == null);
	}

	// Voordat deze gemaakt kan worden moet eerst alle autos worden verwijderd
	// bij tearDown() uit database

	@Test
	public void testGetAuto() {
		// Zou de auto moeten returnen met id 1 (kenteken: GB1231)
		a5 = autoService.getAuto(autoService.getAutoIdOpKenteken("GB1231")); 
		
		assertEquals(a1, a5);
	}

	@Test
	public void testGetAlleKlantenAuto() {
		k3 = klantService.getAlleKlantenAuto().get(0);
		k4 = klantService.getAlleKlantenAuto().get(1);

		// Klant k1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Klant k3 ( Uit Database gehaald met de autos!!!! )

		// Klant k2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Klant k4 ( Uit Database gehaald met de autos!!!! )

		assertEquals(k1, k3);
		assertEquals(k2, k4);
	}

	@After
	public void tearDown() throws Exception {
		klantService.deleteAlleKlanten();
		autoService.deleteAlleAutos();
	}

}