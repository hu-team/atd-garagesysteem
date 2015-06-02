package nl.atd.test.service;

import static org.junit.Assert.fail;
import nl.atd.helper.AuthHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
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
	
	@Before
	public void setUp() throws Exception {
		// Aanmaken van klanten
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxiiemaxx");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("123"));
		k1.setLaatsteBezoek(null);
		//kservice.addKlant(k1);

		k2 = new Klant("Tom Valk");
		k2.setEmail("tomvalk@hotmail.com");
		k2.setGebruikersnaam("tomvalk");
		k2.setWachtwoord(AuthHelper.encryptWachtwoord("456"));
		k2.setLaatsteBezoek(null);
		//kservice.addKlant(k2);
		
		// Aanmaken van 
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	
	@After
	public void tearDown() throws Exception {
	}


}
