package nl.atd.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.ConfigHelper;
import nl.atd.model.Artikel;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Monteur;
import nl.atd.model.Onderdeel;
import nl.atd.service.ArtikelService;
import nl.atd.service.AutoService;
import nl.atd.service.KlantService;
import nl.atd.service.KlusService;
import nl.atd.service.MonteurService;
import nl.atd.service.OnderdeelService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OnderdeelServiceTest {
	ArtikelService artikelService = ServiceProvider.getArtikelService();
	KlusService klusService = ServiceProvider.getKlusService();
	KlantService klantService = ServiceProvider.getKlantService();
	AutoService autoService = ServiceProvider.getAutoService();
	MonteurService monteurService = ServiceProvider.getMonteurService();
	OnderdeelService onderdeelService = ServiceProvider.getOnderdeelService();

	private Klant k1, k2;
	private Auto a1, a2, a3, a4;
	private Monteur m1, m2;
	private Klus klus1, klus2;
	private Onderdeel ond1, ond2, ond3, ond4, ond5, ond6;
	private Artikel artikel1, artikel2, artikel3;

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
		// Aanmaken van klanten
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

		// Aanmaken van monteurs
		m1 = new Monteur("Benco van Dam", 100001);
		m1.setGebruikersnaam("bencovandam");
		m1.setWachtwoord(AuthHelper.encryptWachtwoord("monteur1"));
		monteurService.addMonteur(m1);

		m2 = new Monteur("Gerrit Dijkstra", 100002);
		m2.setGebruikersnaam("gerritdijkstra");
		m2.setWachtwoord(AuthHelper.encryptWachtwoord("monteur2"));
		monteurService.addMonteur(m2);

		// Aanmaken van autos
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

		// Aanmaken van klussen
		klus1 = new Klus(k1, a1);
		klus1.setMonteur(m1);
		klus1.setOmschrijving("Winterbanden vervangen door zomerbanden");
		klus1.setType("Onderhoud");
		klus1.setUren(4);
		klusService.addKlus(klus1);

		klus2 = new Klus(k1, a3);
		klus2.setMonteur(m1);
		klus2.setOmschrijving("APK");
		klus2.setType("APK");
		klus2.setUren(2);
		klusService.addKlus(klus2);

		// Aanmaken van artikelen
		artikel1 = new Artikel("Winterbanden", 55);
		artikel1.setCode("A1001");
		artikel1.setPrijs(49);
		artikelService.addArtikel(artikel1);

		artikel2 = new Artikel("Vooruit Mercedes", 8);
		artikel2.setCode("A1002");
		artikel2.setPrijs(112);
		artikelService.addArtikel(artikel2);

		artikel3 = new Artikel("Ruitensproeierpomp Renault", 2);
		artikel3.setCode("A1003");
		artikel3.setPrijs(15);
		artikelService.addArtikel(artikel3);

		// Aanmaken van onderdelen
		ond1 = new Onderdeel(artikel1, 8);
		onderdeelService.addOnderdeel(ond1, klus1);

		ond2 = new Onderdeel(artikel2, 3);
		onderdeelService.addOnderdeel(ond2, klus1);

		ond3 = new Onderdeel(artikel3, 2);
		onderdeelService.addOnderdeel(ond3, klus2);

	}

	@Test
	public void testGetAlleOnderdelen() {
		ond4 = onderdeelService.getAlleOnderdelen().get(0);
		ond5 = onderdeelService.getAlleOnderdelen().get(1);
		ond6 = onderdeelService.getAlleOnderdelen().get(2);

		// Onderdeel ond1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Onderdeel ond4 ( Uit Database gehaald )
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		// ond2 == ond5
		// ond3 == ond6

		assertEquals(ond1, ond4);
		assertEquals(ond2, ond5);
		assertEquals(ond3, ond6);

		// Check of als het niet klopt, het ook word gezien
		assertFalse(ond1.equals(ond6));
		assertFalse(ond1.equals(ond6));
		assertFalse(ond3.equals(ond4));
		assertFalse(ond3.equals(ond5));
	}
	
	@Test
	public void testGetAlleOnderdelenVanKlus() {
		ArrayList<Onderdeel> listKlus1UitDB = onderdeelService.getAlleOnderdelenVanKlus(klus1);
		ArrayList<Onderdeel> listKlus2UitDB = onderdeelService.getAlleOnderdelenVanKlus(klus2);
		
		ArrayList<Onderdeel> list1 = klus1.getOnderdelen();
		ArrayList<Onderdeel> list2 = klus2.getOnderdelen();
			
		// listKlus1UitDB zou gelijk moeten zijn aan list1
		// listKlus2UitDB zou gelijk moeten zijn aan list2
			
		assertEquals(listKlus1UitDB, list1);
		assertEquals(listKlus2UitDB, list2);
		
	}
	
	
	@After
	public void tearDown() throws Exception {
		klusService.deleteAlleKlussen();
		klantService.deleteAlleKlanten();
		monteurService.deleteAlleMonteurs();
		autoService.deleteAlleAutos();
		artikelService.deleteAlleKlanten();
		onderdeelService.deleteAlleOnderdelen();
	}

}
