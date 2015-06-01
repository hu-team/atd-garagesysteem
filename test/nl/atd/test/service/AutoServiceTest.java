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
	KlantService kservice = ServiceProvider.getKlantService();
	AutoService aservice = ServiceProvider.getAutoService();

	private Klant k1, k2, k3, k4;
	private Auto a1, a2, a3, a4;

	@Before
	public void setUp() throws Exception {
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxiiemaxx");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("123"));
		k1.setLaatsteBezoek(null);
		kservice.addKlant(k1);

		k2 = new Klant("Tom Valk");
		k2.setEmail("tomvalk@hotmail.com");
		k2.setGebruikersnaam("tomvalk");
		k2.setWachtwoord(AuthHelper.encryptWachtwoord("456"));
		k2.setLaatsteBezoek(null);
		kservice.addKlant(k2);

		a1 = new Auto("Mercedes", "A180", 2015, null);
		a1.setKenteken("GB1231");
		aservice.addAuto(k1.getNaam(), a1);

		a2 = new Auto("Ford", "Mondeo", 2012, null);
		a2.setKenteken("12XVK1");
		aservice.addAuto(k2.getNaam(), a2);
	}
	
	@Test
	public void testGetAlleAutos() {
		a3 = aservice.getAlleAutos().get(0);
		a4 = aservice.getAlleAutos().get(0);
		
		// Auto a1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a3 ( Uit Database gehaald )

		// Auto a2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a4 ( Uit Database gehaald )

		assertEquals(k1, k3);
		assertEquals(k2, k4);
	}

	@After
	public void tearDown() throws Exception {
	}

}
