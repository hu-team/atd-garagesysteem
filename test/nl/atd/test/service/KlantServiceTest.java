package nl.atd.test.service;

import static org.junit.Assert.assertEquals;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.ConfigHelper;
import nl.atd.model.Klant;
import nl.atd.service.KlantService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KlantServiceTest {
	KlantService kservice = ServiceProvider.getKlantService();
	private Klant k1, k2, k3, k4;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ConfigHelper.getProperties().put("installed", "true");
		ConfigHelper.getProperties().put("mysql.host", "localhost:8889");
		ConfigHelper.getProperties().put("mysql.database", "atd");
		ConfigHelper.getProperties().put("mysql.username", "root");
		ConfigHelper.getProperties().put("mysql.password", "root");
	}
	
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
	}

	@Test
	public void testGetAlleKlanten() {
		k3 = kservice.getAlleKlanten().get(0);
		k4 = kservice.getAlleKlanten().get(1);

		// Klant k1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Klant k3 ( Uit Database gehaald )

		// Klant k2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Klant k4 ( Uit Database gehaald )

		assertEquals(k1, k3);
		assertEquals(k2, k4);
	}

	@Test
	public void testGetKlantByGebruikersnaam() {
		k3 = kservice.getKlantByGebruikersnaam("maxiiemaxx");
		k4 = kservice.getKlantByGebruikersnaam("tomvalk");

		assertEquals("k1 zou gelijk moeten zijn aan k3", k1, k3);
		assertEquals("k2 zou gelijk moeten zijn aan k3", k1, k3);
	}

	@Test(expected = Exception.class)
	public void testGetKlantByGebruikersnaam1() {
		k3 = kservice.getKlantByGebruikersnaam("bestaat_niet");

		// gebruikersnaam 'bestaat_niet' bestaat niet en klopt dus niet
		assertEquals(k2.getGebruikersnaam(), k3.getGebruikersnaam());
	}

	@After
	public void tearDown() throws Exception {
		kservice.deleteAlleKlanten();
	}

}
