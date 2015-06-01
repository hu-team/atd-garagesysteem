package nl.atd.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	private Auto a1, a2, a3, a4, a5, a6, a7, a8;

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
		// aservice.addAuto(k1.getGebruikersnaam(), a1);

		a2 = new Auto("Ford", "Mondeo", 2012, null);
		a2.setKenteken("12XVK1");
		// aservice.addAuto(k2.getGebruikersnaam(), a2);

		a3 = new Auto("Volvo", "V60", 2014, null);
		a3.setKenteken("1ZDF53");
		// aservice.addAuto(k1.getGebruikersnaam(), a3);

		a4 = new Auto("Volkswagen", "Golf", 2015, null);
		a4.setKenteken("GH4112");
		// aservice.addAuto(k2.getGebruikersnaam(), a4);
	}

	@Test
	public void testGetAlleAutos() {
		a3 = aservice.getAlleAutos().get(0);
		a4 = aservice.getAlleAutos().get(1);

		// Auto a1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a3 ( Uit Database gehaald )

		// Auto a2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a4 ( Uit Database gehaald )

		assertEquals(a1, a3);
		assertEquals(a2, a4);
	}

	@Test
	public void testGetAutosVanKlant() {
		a5 = aservice.getAutosVanKlant("maxiiemaxx").get(0);
		a6 = aservice.getAutosVanKlant("maxiiemaxx").get(1);

		a7 = aservice.getAutosVanKlant("tomvalk").get(0);
		a8 = aservice.getAutosVanKlant("tomvalk").get(1);

		// Auto a1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a5 ( Uit Database gehaald )
		// Auto a3 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a6 ( Uit Database gehaald )

		assertEquals(a1, a5);
		assertEquals(a3, a6);
		
		// Auto a2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a7 ( Uit Database gehaald )
		// Auto a4 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Auto a8 ( Uit Database gehaald )
		
		assertEquals(a2, a7);
		assertEquals(a4, a8);
	}
	

	@After
	public void tearDown() throws Exception {
		a1 = null;
		a2 = null;
		a3 = null;
		a5 = null;
		a6 = null;
		a7 = null;
		a8 = null;

		// a1 & a2, verwijderen uit database ( of database legen)
		// TODO: verwijderen in de setup, truncate op tabel, ignore fk's
	}

}