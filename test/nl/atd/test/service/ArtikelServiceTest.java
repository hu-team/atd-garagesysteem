package nl.atd.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import nl.atd.model.Artikel;
import nl.atd.service.ArtikelService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArtikelServiceTest {
	ArtikelService artikelService = ServiceProvider.getArtikelService();

	private Artikel artikel1, artikel2, artikel3, artikel4, artikel5, artikel6,
			artikel7, artikel8, artikel9, artikel10, artikel11, artikel12;

	@Before
	public void setUp() throws Exception {
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

		artikel4 = new Artikel("Koppelingset", 5);
		artikel4.setCode("A2001");
		artikel4.setPrijs(110);
		artikelService.addArtikel(artikel4);

		artikel5 = new Artikel("Veren voor Citroen", 12);
		artikel5.setCode("A2002");
		artikel5.setPrijs(35);
		artikelService.addArtikel(artikel5);

		artikel6 = new Artikel("D2R Xenon", 4);
		artikel6.setCode("A2003");
		artikel6.setPrijs(35);
		artikelService.addArtikel(artikel6);

		artikel7 = new Artikel("D2S Xenon", 9);
		artikel7.setCode("A3001");
		artikel7.setPrijs(35);
		artikelService.addArtikel(artikel7);

		artikel8 = new Artikel("D1S Xenon", 1);
		artikel8.setCode("A3002");
		artikel8.setPrijs(55);
		artikelService.addArtikel(artikel8);

	}

	@Test
	public void testGetAlleArtikelen() {
		artikel9 = artikelService.getAlleArtikelen().get(0);
		artikel10 = artikelService.getAlleArtikelen().get(1);
		artikel11 = artikelService.getAlleArtikelen().get(2);
		artikel12 = artikelService.getAlleArtikelen().get(3);

		// Artikel artikel1 ( aangemaakt in de setUp() ) zou gelijk moeten zijn aan
		// Artikel artikel9 ( Uit Database gehaald )
		// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		// artikel2 == artikel10
		// artikel3 == artikel11
		// artikel4 == artikel12

		assertEquals(artikel1, artikel9);
		assertEquals(artikel2, artikel10);
		assertEquals(artikel3, artikel11);
		assertEquals(artikel4, artikel12);

		// Check of als het niet klopt, het ook word gezien
		assertFalse(artikel1.equals(artikel12));
		assertFalse(artikel2.equals(artikel11));
		assertFalse(artikel3.equals(artikel10));
		assertFalse(artikel4.equals(artikel9));
	}

	@Test
	public void testGetArtikelByCode() {
		artikel9 = artikelService.getArtikelByCode(artikel8.getCode());
		artikel10 = artikelService.getArtikelByCode(artikel7.getCode());
		artikel11 = artikelService.getArtikelByCode(artikel6.getCode());

		// artikel9 zou gelijk moeten zijn aan artikel8, en:
		// artikel10 == artikel7
		// artikel11 == artikel6

		assertEquals(artikel9, artikel8);
		assertEquals(artikel10, artikel7);
		assertEquals(artikel11, artikel6);

		// Check of als het niet klopt, het ook word gezien
		assertFalse(artikel9.equals(artikel6));
		assertFalse(artikel10.equals(artikel8));
		assertFalse(artikel11.equals(artikel7));
	}

	@Test
	public void testGetArtikelByNaam() {
		artikel9 = artikelService.getArtikelByNaam(artikel4.getNaam()).get(0);
		artikel10 = artikelService.getArtikelByNaam(artikel5.getNaam()).get(0);
		artikel11 = artikelService.getArtikelByNaam(artikel6.getNaam()).get(0);

		// artikel9 zou gelijk moeten zijn aan artikel4, en:
		// artikel10 == artikel5
		// artikel11 == artikel6

		assertEquals(artikel9, artikel4);
		assertEquals(artikel10, artikel5);
		assertEquals(artikel11, artikel6);

		// Check of als het niet klopt, het ook word gezien
		assertFalse(artikel9.equals(artikel6));
		assertFalse(artikel10.equals(artikel6));
		assertFalse(artikel11.equals(artikel5));
	}
	
	@Test (expected = Exception.class)
	public void testGetArtikelByNaam1() {
		artikel9 = artikelService.getArtikelByNaam("bestaat_niet").get(0);
		// de artikelnaam test_naam, bestaat niet, dus exception
	}

	@Test
	public void testEditArtikel() {
		// Prijs veranderen van artikel 1
		artikel1.setPrijs(140);
		// Die wijziging updaten in de database
		artikelService.editArtikel(artikel1);
		// Artikel weer uit database halen
		artikel9 = artikelService.getAlleArtikelen().get(0);
		// Checken of het succesvol veranderd
		assertEquals(artikel1, artikel9);

		artikel2.voegAantalToe(15);
		artikelService.editArtikel(artikel2);
		artikel10 = artikelService.getAlleArtikelen().get(1);
		assertEquals(artikel2, artikel10);
	}

	@After
	public void tearDown() throws Exception {
		artikelService.deleteAlleArtikelen();
	}

}
