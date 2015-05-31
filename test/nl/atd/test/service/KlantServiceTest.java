package nl.atd.test.service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import nl.atd.dao.KlantDAO;
import nl.atd.helper.AuthHelper;
import nl.atd.model.Klant;
import nl.atd.service.KlantService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class KlantServiceTest {
	KlantService kservice = ServiceProvider.getKlantService();
	private Klant k1, k2, k3, k4;

	private ArrayList<Klant> klanten = new ArrayList<Klant>();
	private ArrayList<Klant> actualKlanten = new ArrayList<Klant>();

	@Before
	public void setUp() throws Exception {
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxiiemaxx");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("123"));
		k1.setLaatsteBezoek(null);
		klanten.add(k1);

		k2 = new Klant("Tom Valk");
		k2.setEmail("tomvalk@hotmail.com");
		k2.setGebruikersnaam("tomvalk");
		k2.setWachtwoord(AuthHelper.encryptWachtwoord("456"));
		k2.setLaatsteBezoek(null);
		klanten.add(k2);
		
		actualKlanten = kservice.getAlleKlanten();
		k3 = actualKlanten.get(0);
		k4 = actualKlanten.get(1);
	}

	@Test
	public void testGetAlleKlanten() {
		// Klant k1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan Klant k3 ( Uit Database gehaald ) 
		// Klant k2 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan Klant k4 ( Uit Database gehaald )
		
		//assertEquals("Check of naam klopt", k3.getNaam(), k1.getNaam());
		//assertEquals("Check of naam klopt", k3.getEmail(), k1.getEmail());
		//assertEquals("Check of naam klopt", k3.getLaatsteBezoek(), k1.getLaatsteBezoek());
		
		assertTrue(k1.equals(k3));
		
		//assertEquals(k1, k3);
		
		//assertEquals(k3.getGebruikersnaam(), k1.getGebruikersnaam());

		// assertEquals("Check of naam klopt", "Max van Kuik", k1.getNaam());
		// assertEquals("Check of email klopt", "kuikvanmax@hotmail.com",
		// k1.getEmail());
	}

	@After
	public void tearDown() throws Exception {
	}


}
