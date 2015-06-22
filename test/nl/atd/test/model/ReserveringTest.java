package nl.atd.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Parkeerplek;
import nl.atd.model.Reservering;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ReserveringTest {
	private static Klant k1, k2;
	private static Calendar temp1, temp2, temp3, temp4;
	private static Auto a1, a2, a3, a4;
	private static Parkeerplek p1, p2, p3, p4;
	private Reservering r1, r2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Aanmaken van autos
		a1 = new Auto("Mercedes", "A180", 2015, null);
		a2 = new Auto("Ford", "Mondeo", 2012, null);
		a2.setKenteken("12XVK1");

		a3 = new Auto("Volvo", "V60", 2014, null);
		a3.setKenteken("1ZDF53");

		a4 = new Auto("Volkswagen", "Golf", 2015, null);
		a4.setKenteken("GH4112");

		// Aanmaken van klanten
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxuser");
		k1.setWachtwoord("123");
		k1.setAdres("Straatweg 12");
		k1.setPostcode("9999XX");
		k1.setWoonplaats("Oss");
		k1.setTelefoonnummer("0318123123");
		k1.voegAutoToe(a1);

		k2 = new Klant("Tom Valk");
		k2.setEmail("bencovandam@hotmail.com");
		k2.setGebruikersnaam("bencouser");
		k2.setWachtwoord("welkom");
		k2.setAdres("Straatweg 10");
		k2.setPostcode("9999XX");
		k2.setWoonplaats("Oss");
		k2.setTelefoonnummer("0318456456");
		k1.voegAutoToe(a2);

		// Aanmaken van parkeerplekken
		p1 = new Parkeerplek('F', 3);
		p2 = new Parkeerplek('A', 1);
		p3 = new Parkeerplek('F', 3);
		p4 = new Parkeerplek('B', 11);

		// Aanmaken van data
		temp1 = Calendar.getInstance();
		temp1.setTimeInMillis(1234567);

		temp2 = Calendar.getInstance();
		temp2.setTimeInMillis(7654321);

		temp3 = Calendar.getInstance();
		temp3.setTimeInMillis(456123);

		temp4 = Calendar.getInstance();
		temp4.setTimeInMillis(321654);
	}

	@Before
	public void setUp() throws Exception {
		// Aanmaken van reserveringen
		r1 = new Reservering(k1, a1);
		r1.setTot(temp1);
		r1.setVan(temp2);
		r1.setParkeerplek(p1);

		r2 = new Reservering(k2, a2);
		r2.setTot(temp2);
		r2.setVan(temp1);
		r2.setParkeerplek(p2);
	}

	@Test
	public void testSetVan() {
		r1.setVan(temp3);

		// Vergelijken
		assertEquals(temp3, r1.getVan());
		// Nog een check op foute data
		assertFalse(temp2.equals(r2.getVan()));
	}

	@Test
	public void testSetTot() {
		r2.setTot(temp4);
		// Vergelijken
		assertEquals(321654, r2.getTot().getTimeInMillis());
		// Nog een check op foute data
		assertFalse(temp2.equals(r1.getTot()));
	}

	@Test
	public void testGetVan() {
		// r1 van = 7654321
		// r2 van = 1234567

		assertEquals(temp2.getTimeInMillis(), r1.getVan().getTimeInMillis());
		assertEquals(temp1.getTimeInMillis(), r2.getVan().getTimeInMillis());
		// Nog een check op foute data
		assertFalse(999999 == r2.getTot().getTimeInMillis());
		assertFalse(00 == r1.getTot().getTimeInMillis());
	}

	@Test
	public void testGetTot() {
		// r1 tot = 1234567
		// r2 tot = 7654321

		assertEquals(temp1.getTimeInMillis(), r1.getTot().getTimeInMillis());
		assertEquals(temp2.getTimeInMillis(), r2.getTot().getTimeInMillis());
		// Nog een check op foute data
		assertFalse(111111 == r2.getTot().getTimeInMillis());
		assertFalse(0 == r1.getTot().getTimeInMillis());
	}

	@Test
	public void testGetKlant() {
		// r1 klant = k1
		// r2 klant = k2

		assertEquals(k1.getNaam(), r1.getKlant().getNaam());
		assertEquals(k1, r1.getKlant());
		assertEquals("9999XX", r2.getKlant().getPostcode());
		// Nog een check op foute data
		assertFalse("Benco van de Heuvel".equals(r2.getKlant().getNaam()));
		assertFalse("0318123123".equals(r2.getKlant().getTelefoonnummer()));
	}

	@Test
	public void testGetAuto() {
		// r1 auto = a1
		// r2 auto = k2

		assertEquals(a1.getMerk(), (r1.getKlant().getAutos().get(0)).getMerk());
		assertEquals(a2, r2.getAuto());
		assertEquals(2012, r2.getAuto().getBouwjaar());
		// Nog een check op foute data
		assertFalse("Mercedes".equals(r2.getAuto().getMerk()));
		assertFalse("a180".equals(r1.getAuto().getModel()));
	}

	@Test
	public void testSetKlant() {
		// r1 klant = k1
		// r2 klant = k2

		r1.setKlant(k2);
		r2.setKlant(k1);

		// Is nu dus (als het goed is) omgedraaid
		// r1 klant = k2
		// r2 klant = k1

		assertEquals(k1.getNaam(), r2.getKlant().getNaam());
		assertEquals(k2, r1.getKlant());
		assertTrue("Max van Kuik".equals(r2.getKlant().getNaam()));
		// Nog een check op foute data
		assertFalse("0318456456".equals(r2.getKlant().getTelefoonnummer()));
	}

	@Test
	public void testSetAuto() {
		// r1 auto = a1
		// r2 auto = a2

		r1.setAuto(a2);
		r2.setAuto(a1);

		// Is nu dus (als het goed is) omgedraaid
		// r1 auto = a2
		// r2 auto = a1

		assertEquals(a1.getModel(), r2.getAuto().getModel());
		assertEquals(a2, r1.getAuto());
		assertTrue("Ford".equals(r1.getAuto().getMerk()));
		// Nog een check op foute data
		assertFalse(a1.getBouwjaar() == r1.getAuto().getBouwjaar());
	}

	@Test
	public void testGetParkeerplek() {
		// r1 pplek = p1
		// r2 pplek = p2

		assertEquals(p1.getRij(), r1.getParkeerplek().getRij());
		assertEquals(p2.getPlek(), r2.getParkeerplek().getPlek());
		assertEquals(1, r2.getParkeerplek().getPlek());
		// Nog een check op foute data
		assertFalse("2".equals(r2.getParkeerplek().getPlek()));
	}

	@Test
	public void testSetParkeerplek() {
		// r1 pplek = p1
		// r2 pplek = p2

		r1.setParkeerplek(p3);
		r2.setParkeerplek(p4);

		// Zijn nu dus (als het goed is) nieuwe plekken
		// r1 pplek = p3
		// r2 pplek = p4
		// NOTE: p1 is gelijk aan pt3

		assertEquals(p1, r1.getParkeerplek());
		assertEquals(p4.getPlek(), r2.getParkeerplek().getPlek());
		assertEquals(11, r2.getParkeerplek().getPlek());
		// Nog een check op foute data
		assertFalse("2".equals(r2.getParkeerplek().getPlek()));
	} 
	
	@Test
	public void testGetTotaalPrijs() {
		// 86 400 000 ms = 1 dag
		// temp 1 = 5,5 dagen
		// temp 2 = 11 dagen
		
		temp1.setTimeInMillis(475000000);
		temp2.setTimeInMillis(950000000);
		
		// r1 totaalprijs = -75 euro
		// r2 totaalprijs = 75 euro
		assertEquals(-75.0, r1.getTotaalPrijs(), 0);
		assertEquals(75, r2.getTotaalPrijs(), 0);
		
		temp3.setTimeInMillis(1900000000);
		r1.setTot(temp3);
		// temp 3 = 22 dagen (tot)
		// r1 totaalprijs = 150.0 euro
		assertEquals(150.0, r1.getTotaalPrijs(), 01);
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
