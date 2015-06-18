package nl.atd.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import nl.atd.model.Monteur;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class MonteurTest {
	private Monteur m1, m2, m3;

	@Before
	public void setUp() throws Exception {
		m1 = new Monteur("John van de Heuvel", 1001);
		m1.setGebruikersnaam("johngeb");
		m1.setWachtwoord("johnww");

		m2 = new Monteur("Kees van de Heuvel", 1002);
		m2.setGebruikersnaam("keesgeb");
		m2.setWachtwoord("keesww");

	}

	@Test
	public void testGetNaam() {
		String temp1 = "John van de Heuvel";
		String temp2 = "Kees van de Heuvel";

		assertEquals(temp1, m1.getNaam());
		assertEquals(temp2, m2.getNaam());
	}

	@Test
	public void testSetNaam() {
		String temp = "nieuwenaam";
		m1.setNaam(temp);

		// Vergelijken
		assertEquals(temp, m1.getNaam());
		// Nog een check op foute data
		assertFalse(temp.equals(m2.getNaam()));
	}

	@Test
	public void testGetGebruikersnaam() {
		String temp1 = "johngeb";
		String temp2 = "keesgeb";

		assertEquals(temp1, m1.getGebruikersnaam());
		assertEquals(temp2, m2.getGebruikersnaam());
	}

	@Test
	public void testSetGebruikersnaam() {
		String temp = "nieuweusername";
		m1.setGebruikersnaam(temp);

		// Vergelijken
		assertEquals(temp, m1.getGebruikersnaam());
		// Nog een check op foute data
		assertFalse(temp.equals(m2.getGebruikersnaam()));
	}

	@Test
	public void testGetWachtwoord() {
		String temp1 = "johnww";
		String temp2 = "keesww";

		assertEquals(temp1, m1.getWachtwoord());
		assertEquals(temp2, m2.getWachtwoord());
	}

	@Test
	public void testSetWachtwoord() {
		String temp = "456";
		m1.setWachtwoord(temp);

		// Vergelijken
		assertEquals(temp, m1.getWachtwoord());
		// Nog een check op foute data
		assertFalse(temp.equals(m2.getWachtwoord()));
	}

	@Test
	public void testEquals() {
		m3 = new Monteur("John van de Heuvel", 1001);
		m3.setGebruikersnaam("johngeb");
		m3.setWachtwoord("johnww");
		
		// Zouden precies hetzelfde moeten zijn
		assertTrue(m1.equals(m3));
		
		assertTrue(!(m1.equals(null)));
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
