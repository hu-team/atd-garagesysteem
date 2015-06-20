package nl.atd.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;

import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Reservering;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReserveringTest {
	private Klant k1, k2;
	private Auto a1, a2;
	private Reservering r1, r2;

	@Before
	public void setUp() throws Exception {
		// Aanmaken van klanten
		k1 = new Klant("Max van Kuik");
		k2 = new Klant("Tom Valk");

		// Aanmaken van autos
		a1 = new Auto("Mercedes", "A180", 2015, null);
		a2 = new Auto("Ford", "Mondeo", 2012, null);

		// Aanmaken van reserveringen
		r1 = new Reservering(k1, a1);
		r2 = new Reservering(k2, a2);
	}

	@Test
	public void testSetVan() {
		Calendar temp = Calendar.getInstance();
		temp.setTimeInMillis(1234567);
		r1.setVan(temp);
		// Vergelijken
		assertEquals(temp, r1.getVan());
		// Nog een check op foute data
		assertFalse(temp.equals(r2.getVan()));		
	}

	@Test
	public void testSetTot() {
		Calendar temp = Calendar.getInstance();
		temp.setTimeInMillis(7654321);
		r2.setTot(temp);
		// Vergelijken
		assertEquals(temp, r2.getTot());
		// Nog een check op foute data
		assertFalse(temp.equals(r1.getTot()));
	}
	
	@Test 
	public void testGetVan() {
		// a3 prijs = 15
	
		//assertEquals(15, a3.getPrijs(), 0);
		//assertEquals(110, a4.getPrijs(), 0);
		//assertEquals(35, a5.getPrijs(), 0);
	}

	@After
	public void tearDown() throws Exception {
	}
}
