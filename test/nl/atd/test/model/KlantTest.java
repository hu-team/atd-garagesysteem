package nl.atd.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import nl.atd.model.Auto;
import nl.atd.model.Klant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KlantTest {
	private Klant k1, k2, k3;
	private Auto a1, a2, a3, a4;
	
	@Before
	public void setUp() throws Exception {
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxuser");
		k1.setWachtwoord("123");
		k1.setAdres("Straatweg 12");
		k1.setPostcode("9999XX");
		k1.setWoonplaats("Oss");
		k1.setTelefoonnummer("0318123123");

		k2 = new Klant("Benco van Dam");
		k2.setEmail("bencovandam@hotmail.com");
		k2.setGebruikersnaam("bencouser");
		k2.setWachtwoord("welkom");
		k2.setAdres("Straatweg 10");
		k2.setPostcode("9999XX");
		k2.setWoonplaats("Oss");
		k2.setTelefoonnummer("0318456456");

		a1 = new Auto("Mercedes", "A180", 2015, null);
		a1.setKenteken("GG101B");

		a2 = new Auto("Ford", "Focus", 2015, null);
		a2.setKenteken("GT421D");

		a3 = new Auto("Peugeot", "508", 2013, null);
		a3.setKenteken("1XVK22");

		a4 = new Auto("Porsche", "Panamera", 2014, null);
		a4.setKenteken("2ZZD51");
	}
	
	@Test
	public void testVoegAutoToe() {

	}

	@Test
	public void testGetAutos() {

	}

	@Test
	public void testGetNaam() {
		String temp1 = "Max van Kuik"; // zou gelijk moeten zijn aan de naam
										// van k1
		String temp2 = "Benco van Dam"; // zou gelijk moeten zijn aan de naam
										// van k2

		assertEquals(temp1, k1.getNaam());
		assertEquals(temp2, k2.getNaam());
	}

	@Test
	public void testSetNaam() {
		String temp = "nieuwenaam";
		k1.setNaam(temp);

		// Vergelijken
		assertEquals(temp, k1.getNaam());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getNaam()));
	}

	@Test
	public void testGetGebruikersnaam() {
		String temp1 = "maxuser";
		// zou gelijk moeten zijn aan de gebruikersnaamnaam van k1
		String temp2 = "bencouser";
		// zou gelijk moeten zijn aan de gebruikersnaamnaam van k2

		assertEquals(temp1, k1.getGebruikersnaam());
		assertEquals(temp2, k2.getGebruikersnaam());
	}

	@Test
	public void testSetGebruikersnaam() {
		String temp = "nieuweusername";
		k1.setGebruikersnaam(temp);

		// Vergelijken
		assertEquals(temp, k1.getGebruikersnaam());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getGebruikersnaam()));
	}

	@Test
	public void testGetWachtwoord() {
		// zou gelijk moeten zijn aan het wachtwoord van k1
		String temp1 = "maxuser";
		// zou gelijk moeten zijn aan het wachtwoord van k2
		String temp2 = "bencouser";

		assertEquals(temp1, k1.getGebruikersnaam());
		assertEquals(temp2, k2.getGebruikersnaam());
	}

	@Test
	public void testSetWachtwoord() {
		String temp = "456";
		k1.setWachtwoord(temp);

		// Vergelijken
		assertEquals(temp, k1.getWachtwoord());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getWachtwoord()));
	}

	@Test
	public void testGetEmail() {
		assertEquals("kuikvanmax@hotmail.com", k1.getEmail());
		assertEquals("bencovandam@hotmail.com", k2.getEmail());

		assertFalse("kuikvanmax@gmail.com".equals(k1.getEmail()));
	}

	@Test
	public void testSetEmail() {
		String temp = "nieuwemail@hotmail.com";
		k1.setEmail(temp);

		// Email vergelijken
		assertEquals(temp, k1.getEmail());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getEmail()));
	}

	@Test
	public void testGetLaatsteBezoek() {
		Calendar temp = Calendar.getInstance();

		// Calendar vergelijken, YEAR - MONTH - DAY_OF_MONTH
		assertEquals(temp.get(Calendar.YEAR),
				k1.getLaatsteBezoek().get(Calendar.YEAR));
		assertEquals(temp.get(Calendar.MONTH),
				k1.getLaatsteBezoek().get(Calendar.MONTH));
		assertEquals(temp.get(Calendar.DAY_OF_MONTH), k1.getLaatsteBezoek()
				.get(Calendar.DAY_OF_MONTH));

		// Nog een check op foute data
		assertFalse(2014 == k2.getLaatsteBezoek().get(Calendar.YEAR));
		assertFalse(14 == k2.getLaatsteBezoek().get(Calendar.MONTH));
		assertFalse(32 == k2.getLaatsteBezoek().get(Calendar.DAY_OF_MONTH));

	}

	@Test
	public void testSetLaatsteBezoek() {
		Calendar temp = Calendar.getInstance();
		temp.set(2015, Calendar.MAY, 16);

		k1.setLaatsteBezoek(temp);

		// Calendar vergelijken, YEAR - MONTH - DAY_OF_MONTH
		assertEquals(2015, k1.getLaatsteBezoek().get(Calendar.YEAR));
		assertEquals(5, k1.getLaatsteBezoek().get(Calendar.MONTH) + 1);
		assertEquals(16, k1.getLaatsteBezoek().get(Calendar.DAY_OF_MONTH));

		// Nog een check op foute data
		assertFalse(2014 == k2.getLaatsteBezoek().get(Calendar.YEAR));
		assertFalse(13 == k2.getLaatsteBezoek().get(Calendar.MONTH));
		assertFalse(32 == k2.getLaatsteBezoek().get(Calendar.DAY_OF_MONTH));

	}

	@Test
	public void testGetAdres() {
		// zou gelijk moeten zijn aan het adres van k1
		String temp1 = "Straatweg 12";
		// zou gelijk moeten zijn aan het adres van k2
		String temp2 = "Straatweg 10";

		assertEquals(temp1, k1.getAdres());
		assertEquals(temp2, k2.getAdres());
	}

	@Test
	public void testSetAdres() {
		String temp = "nieuwadres";
		k1.setAdres(temp);

		// Vergelijken
		assertEquals(temp, k1.getAdres());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getAdres()));
	}

	@Test
	public void testGetPostcode() {
		// zou gelijk moeten zijn aan de postcode van k1 & k2
		String temp1 = "9999XX";

		assertEquals(temp1, k1.getPostcode());
	}

	@Test
	public void testSetPostcode() {
		String temp = "nieuwepostcode";
		k1.setPostcode(temp);

		// Vergelijken
		assertEquals(temp, k1.getPostcode());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getPostcode()));
	}

	@Test
	public void testGetWoonplaats() {
		// zou gelijk moeten zijn aan de woonplaats van k1 & k2
		String temp1 = "Oss";

		assertEquals(temp1, k1.getWoonplaats());
		assertEquals(temp1, k2.getWoonplaats());
	}

	@Test
	public void testSetWoonplaats() {
		String temp = "nieuwepostcode";
		k1.setWoonplaats(temp);

		// Vergelijken
		assertEquals(temp, k1.getWoonplaats());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getWoonplaats()));
	}

	@Test
	public void testGetTelefoonnummer() {
		// zou gelijk moeten zijn aan het Telefoonnummer van k1
		String temp1 = "0318123123";
		String temp2 = "0318456456";

		assertEquals(temp1, k1.getTelefoonnummer());
		assertEquals(temp2, k2.getTelefoonnummer());
	}

	@Test
	public void testSetTelefoonnummer() {
		String temp = "nieuwetelnmmr";
		k1.setTelefoonnummer(temp);

		// Vergelijken
		assertEquals(temp, k1.getTelefoonnummer());
		// Nog een check op foute data
		assertFalse(temp.equals(k2.getTelefoonnummer()));
	}

	@Test
	public void testEquals() {
		k3 = new Klant("Max van Kuik");
		k3.setEmail("kuikvanmax@hotmail.com");
		k3.setGebruikersnaam("maxuser");
		k3.setWachtwoord("123");
		k3.setAdres("Straatweg 12");
		k3.setPostcode("9999XX");
		k3.setWoonplaats("Oss");
		k3.setTelefoonnummer("0318123123");

		// Zouden precies hetzelfde moeten zijn
		assertTrue(k1.equals(k3));
	}


	@After
	public void tearDown() throws Exception {
	}

}