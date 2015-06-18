package nl.atd.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.atd.model.Artikel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArtikelTest {
	private Artikel a1, a2, a3, a4, a5;

	@Before
	public void setUp() throws Exception {
		a1 = new Artikel("Winterbanden", 55);
		a1.setCode("A1001");
		a1.setPrijs(49);

		a2 = new Artikel("Vooruit Mercedes", 8);
		a2.setCode("A1002");
		a2.setPrijs(112);

		a3 = new Artikel("Ruitensproeierpomp Renault", 2);
		a3.setCode("A1003");
		a3.setPrijs(15);

		a4 = new Artikel("Koppelingset", 5);
		a4.setCode("A2001");
		a4.setPrijs(110);

		a5 = new Artikel("Veren voor Citroen", 12);
		a5.setCode("A2002");
		a5.setPrijs(35);
	}

	@Test
	public void testGetNaam() {
		String temp1 = "Winterbanden";
		String temp2 = "Koppelingset";

		assertEquals(temp1, a1.getNaam());
		assertEquals(temp2, a4.getNaam());
	}

	@Test
	public void testSetNaam() {
		String temp = "nieuwenaam";
		a1.setNaam(temp);

		// Vergelijken
		assertEquals(temp, a1.getNaam());
		// Nog een check op foute data
		assertFalse(temp.equals(a2.getNaam()));
	}

	@Test
	public void testSetCode() {
		String temp = "nieuwecode";
		a1.setCode(temp);

		// Vergelijken
		assertEquals(temp, a1.getCode());
		// Nog een check op foute data
		assertFalse(temp.equals(a2.getCode()));
	}

	@Test
	public void testGetCode() {
		String temp1 = "A1001";
		String temp2 = "A1002";

		assertEquals(temp1, a1.getCode());
		assertEquals(temp2, a2.getCode());
	}

	@Test
	public void testVoegAantalToe() {
		// a1 aantal = 55
		// a2 aantal = 8
		a1.voegAantalToe(10);
		a2.voegAantalToe(-10);
		assertEquals(65, a1.getAantal());
		assertEquals(-2, a2.getAantal());

		// a4 aantal = 5
		// a5 aantal = 12
		a4.voegAantalToe(99);
		a5.voegAantalToe(-99);
		assertEquals(104, a4.getAantal());
		assertEquals(-87, a5.getAantal());
	}

	@Test
	public void testKanAantalGebruiken() {
		// a1 aantal = 55
		// a2 aantal = 8
		// a3 aantal = 2
		// a5 aantal = 12

		assertTrue(a1.kanAantalGebruiken(55));
		assertTrue(a2.kanAantalGebruiken(-12));
		assertTrue(a5.kanAantalGebruiken(0));

		assertFalse(a3.kanAantalGebruiken(3));
	}

	@Test
	public void testGebruikAantal() {
		// a1 aantal = 55
		// a2 aantal = 8
		// a3 aantal = 2
		
		// Gebruik 55, dus nu zou 0 moeten zijn
		assertTrue(a1.gebruikAantal(55));
		assertEquals(0, a1.getAantal());

		// Gebruik -12, dus nu zouden er 12 bij moeten zijn (totaal 20)
		assertTrue(a2.gebruikAantal(-12));
		assertEquals(20, a2.getAantal());

		// Gebruik 13 (teveel), dus blijft zelfde
		assertFalse(a3.gebruikAantal(13));
		assertEquals(2, a3.getAantal());
	}
	
	@Test 
	public void testGetAantal() {
		// a1 aantal = 55
		// a2 aantal = 8
		// a3 aantal = 2
	
		assertEquals(55, a1.getAantal());
		assertEquals(8, a2.getAantal());
		assertEquals(2, a3.getAantal());
	}
	
	@Test 
	public void testGetPrijs() {
		// a3 prijs = 15
		// a4 prijs = 110
		// a5 prijs = 35
	
		assertEquals(15, a3.getPrijs(), 0);
		assertEquals(110, a4.getPrijs(), 0);
		assertEquals(35, a5.getPrijs(), 0);
	}
	
	@Test
	public void testSetPrijs() {
		double temp = 99999.99;
		a1.setPrijs(temp);

		// Vergelijken
		assertEquals(temp, a1.getPrijs(), 0);
		// Nog een check op foute data
		assertFalse(temp == a2.getPrijs());
	}

	@After
	public void tearDown() throws Exception {
	}

}
