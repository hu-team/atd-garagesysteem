package nl.atd.test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import nl.atd.model.Artikel;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Monteur;
import nl.atd.model.Onderdeel;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KlusTest {
	private static Klant k1, k2;
	private static Auto auto1, auto2, auto3, auto4;
	private static Monteur m1, m2;
	private static Artikel a1, a2, a3;
	private static Onderdeel ond1, ond2, ond3;
	private static Calendar temp1, temp2;
	private Klus klus1, klus2;

	private final ArrayList<Onderdeel> legeOndArray = new ArrayList<Onderdeel>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Aanmaken van klanten
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxuser");
		k1.setWachtwoord("123");
		k1.setAdres("Straatweg 12");
		k1.setPostcode("9999XX");
		k1.setWoonplaats("Oss");
		k1.setTelefoonnummer("0318123123");

		k2 = new Klant("Tom Valk");
		k2.setEmail("bencovandam@hotmail.com");
		k2.setGebruikersnaam("bencouser");
		k2.setWachtwoord("welkom");
		k2.setAdres("Straatweg 10");
		k2.setPostcode("9999XX");
		k2.setWoonplaats("Oss");
		k2.setTelefoonnummer("0318456456");

		// Aanmaken van autos
		auto1 = new Auto("Mercedes", "A180", 2015, null);
		auto1.setKenteken("GK444R");

		auto2 = new Auto("Ford", "Mondeo", 2012, null);
		auto2.setKenteken("12XVK1");

		auto3 = new Auto("Volvo", "V60", 2014, null);
		auto3.setKenteken("1ZDF53");

		auto4 = new Auto("Volkswagen", "Golf", 2015, null);
		auto4.setKenteken("GH4112");

		// Aanmaken van monteurs
		m1 = new Monteur("John van de Heuvel", 1001);
		m1.setGebruikersnaam("johngeb");
		m1.setWachtwoord("johnww");

		m2 = new Monteur("Kees van de Heuvel", 1002);
		m2.setGebruikersnaam("keesgeb");
		m2.setWachtwoord("keesww");

		// Aanmaken van artikelen
		a1 = new Artikel("Winterbanden", 55);
		a1.setCode("A1001");
		a1.setPrijs(49);

		a2 = new Artikel("Vooruit Mercedes", 8);
		a2.setCode("A1002");
		a2.setPrijs(112);

		a3 = new Artikel("Ruitensproeierpomp Renault", 2);
		a3.setCode("A1003");
		a3.setPrijs(15);

		// Aanmaken van onderdelen
		ond1 = new Onderdeel(a1, 8);

		ond2 = new Onderdeel(a2, 3);

		ond3 = new Onderdeel(a3, 2);
		
		// Aanmaken van data
		temp1 = Calendar.getInstance();
		temp1.set(2015, Calendar.JUNE, 16);

		temp2 = Calendar.getInstance();
		temp2.set(2015, Calendar.MAY, 29);
	}

	@Before
	public void setUp() throws Exception {
		// Aanmaken van klussen
		klus1 = new Klus(k1, auto1);
		klus1.setMonteur(m1);
		klus1.setOmschrijving("Omschrijving");
		klus1.setType("Type");
		klus1.setUren(10);
		klus1.addOnderdeel(ond1);
		klus1.addOnderdeel(ond2);

		klus2 = new Klus(k2, auto2);
		klus2.setMonteur(m1);
		klus2.setOmschrijving("Omschrijving van klus2");
		klus2.setType("Type van klus2");
		klus2.setUren(50);
		klus2.addOnderdeel(ond3);
	}
	
	/**
	 * testSetKlaar, test ook de methode: nl.atd.model.KLus.isKlaar()
	 */
	@Test
	public void testSetKlaar() {
		klus1.setKlaar(true);
		klus2.setKlaar(false);

		assertTrue(klus1.isKlaar());
		assertFalse(klus2.isKlaar());
	}

	/**
	 * testAddOnderdeel, test ook de methode: nl.atd.model.KLus.getOnderdelen()
	 */
	@Test
	public void testAddOnderdeel() {
		// klus1 heeft onderdelen 1 en 2
		// klus2 heeft alleen alleen onderdeel 3

		assertEquals(ond1, klus1.getOnderdelen().get(0));
		assertEquals(ond2, klus1.getOnderdelen().get(1));
		assertEquals(ond3, klus2.getOnderdelen().get(0));

		// Checken op foute data
		assertFalse(ond3.equals(klus1.getOnderdelen().get(0)));
		assertFalse(ond1.equals(klus2.getOnderdelen().get(0)));
	}

	@Test
	public void testVerwijderOnderdeel() {
		klus1.verwijderOnderdeel(ond1);
		klus1.verwijderOnderdeel(ond2);

		// klus1 zou geen onderdelen meer moeten hebben
		assertEquals(legeOndArray, klus1.getOnderdelen());
	}

	@Test
	public void testGetOnderdeel() {
		// klus 1 zou 2 onderdelen moeten hebben,
		assertEquals(2, klus1.getOnderdelen().size());
		// klus2 zou 1 onderdeel moeten hebbebn
		assertEquals(1, klus2.getOnderdelen().size());
	}

	@Test
	public void testSetOnderdeel() {
		ArrayList<Onderdeel> nieuweOnderdelen = new ArrayList<Onderdeel>();
		nieuweOnderdelen.add(ond3);
		nieuweOnderdelen.add(ond2);

		klus1.setOnderdelen(nieuweOnderdelen);
		// Nu zou klus1 de onderdelen 3 en 2 moeten hebben
		assertEquals(ond3, klus1.getOnderdelen().get(0));
		assertEquals(ond2, klus1.getOnderdelen().get(1));
		// Check of de oude onderdelen weg zijn
		assertEquals(2, klus1.getOnderdelen().size());
	}

	@Test
	public void testGetUren() {
		// klus1 uren = 10
		// klus2 uren = 50

		assertEquals(10, klus1.getUren());
		assertEquals(50, klus2.getUren());
	}

	@Test
	public void testSetUren() {
		int temp = 100;
		klus1.setUren(temp);

		// Vergelijken
		assertEquals(temp, klus1.getUren());
		// Nog een check op foute data
		assertFalse(temp == klus2.getUren());
	}

	@Test
	public void testAddUren() {
		klus1.addUren(9);
		klus2.addUren(12);
		// klus1 uren = 19
		// klus2 uren = 62

		assertEquals(19, klus1.getUren());
		assertEquals(62, klus2.getUren());
	}

	/**
	 * testSetCalendar, test ook de methode: nl.atd.model.KLus.getCalendar()
	 */
	@Test
	public void testSetCalendar() {
		klus1.setCalendar(temp1);

		// Vergelijken
		assertEquals(temp1, klus1.getCalendar());
		// Nog een check op foute data
		assertFalse(temp2.equals(klus2.getCalendar()));
	}

	@Test
	public void testGetAuto() {
		// klus1 auto = auto1
		// klus2 auto = auto2

		assertEquals(auto1, klus1.getAuto());
		assertEquals(auto2, klus2.getAuto());
	}

	@Test
	public void testGetKlant() {
		// klus1 klant = k1
		// klus2 klant = k2

		assertEquals(k1, klus1.getKlant());
		assertEquals(k2, klus2.getKlant());
	}

	@Test
	public void testGetType() {
		// klus1 type = Type
		// klus2 type = "Type van klus2"

		assertEquals("Type", klus1.getType());
		assertEquals("Type van klus2", klus2.getType());
	}

	@Test
	public void testSetType() {
		String temp = "nieuweType";
		klus1.setType(temp);

		// Vergelijken
		assertEquals(temp, klus1.getType());
		// Nog een check op foute data
		assertFalse(temp.equals(klus2.getType()));
	}

	@Test
	public void testGetOmschrijving() {
		// klus1 omschrijving = Omschrijving
		// klus2 omschrijving = "Omschrijving van klus 2"

		assertEquals("Omschrijving", klus1.getOmschrijving());
		assertEquals("Omschrijving van klus2", klus2.getOmschrijving());
	}

	@Test
	public void testSetOmschrijving() {
		String temp = "nieuweOmschrijving";
		klus1.setOmschrijving(temp);

		// Vergelijken
		assertEquals(temp, klus1.getOmschrijving());
		// Nog een check op foute data
		assertFalse(temp.equals(klus2.getOmschrijving()));
	}

	@Test
	public void testGetMonteur() {
		// klus1 monteur = m1
		// klus2 monteur = m1

		assertEquals(m1, klus1.getMonteur());
		assertEquals(m1, klus2.getMonteur());
	}
	
	@Test
	public void testSetMonteur() {
		klus1.setMonteur(m2);

		// Vergelijken
		assertEquals(m2, klus1.getMonteur());
		// Nog een check op foute data
		assertFalse(m1.equals(klus1.getMonteur()));
	}

	@After
	public void tearDown() throws Exception {
	}
}
