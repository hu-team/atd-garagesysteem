package nl.atd.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;

import nl.atd.model.Auto;
import nl.atd.model.Klant;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KlantTest {
	private Klant k1, k2;
	private Auto a1, a2, a3, a4;

	@Before
	public void setUp() throws Exception {
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxuser");

		k2 = new Klant("Benco van Dam");
		k2.setEmail("bencovandam@hotmail.com");
		k2.setGebruikersnaam("bencouser");
		
		a1 = new Auto("Mercedes", "A180", 2015, null);
		//auto.setKenteken(kenteken);
	}

	@Test
	public void testGetNaam() {
		String temp1 = "Max van Kuik"; // zou gelijken moeten zijn aan de naam
										// van k1
		String temp2 = "Benco van Dam"; // zou gelijken moeten zijn aan de naam
										// van k2

		assertEquals(temp1, k1.getNaam());
		assertEquals(temp2, k2.getNaam());

	}

	@Test
	public void testGetEmail() {
		assertEquals("kuikvanmax@hotmail.com", k1.getEmail());
		assertEquals("bencovandam@hotmail.com", k2.getEmail());

		assertFalse("kuikvanmax@gmail.com".equals(k1.getEmail()));
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
		assertFalse(2016 == k2.getLaatsteBezoek().get(Calendar.YEAR));
		assertFalse(6 == k2.getLaatsteBezoek().get(Calendar.MONTH));
		assertFalse(17 == k2.getLaatsteBezoek().get(Calendar.DAY_OF_MONTH));

	}
	
	@Test
	public void testVoegAutoToe() {
		
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
		assertFalse(2016 == k2.getLaatsteBezoek().get(Calendar.YEAR));
		assertFalse(6 == k2.getLaatsteBezoek().get(Calendar.MONTH));
		assertFalse(17 == k2.getLaatsteBezoek().get(Calendar.DAY_OF_MONTH));

	}

	@After
	public void tearDown() throws Exception {
	}

}
