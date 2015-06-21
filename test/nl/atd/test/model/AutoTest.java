package nl.atd.test.model;

import static org.junit.Assert.*;

import java.util.Calendar;

import nl.atd.model.Auto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutoTest {
	private Auto a1, a2, a3, a4;
	private Calendar laatste = Calendar.getInstance();
	private Calendar eerste = Calendar.getInstance();

	@Before
	public void setUp() throws Exception {
		eerste.set(2015, Calendar.JUNE, 16);
		a1 = new Auto("Mercedes", "A180", 2015, eerste
				);
		a1.setKenteken("GB1231");

		a2 = new Auto("Ford", "Mondeo", 2012, null);
		a2.setKenteken("12XVK1");

		a3 = new Auto("Volvo", "V60", 2014, null);
		a3.setKenteken("1ZDF53");

		laatste.set(2014, Calendar.DECEMBER, 16);
		a4 = new Auto("Volkswagen", "Golf", 2015, laatste);
		a4.setKenteken("GH4112");
	}

	@Test
	public void testIsLaatsteBeurt() {
		// Beurt van a1 is pas geleden, dus true;
		assertTrue(a1.isLaatsteBeurtRedelijk());
		// Beurt van a4 is langer dan 6 maanden geleden, dus false;
		assertFalse(a4.isLaatsteBeurtRedelijk());
		// a3 heeft nog geen beurt gehad (null), dus false;
		assertFalse(a3.isLaatsteBeurtRedelijk());
	}

	@Test
	public void testGetMerk() {
		// a1 merk = Mercedes
		// a2 merk = Ford
		// a3 merk = Volvo

		assertEquals("Mercedes", a1.getMerk());
		assertEquals("Ford", a2.getMerk());
		assertEquals("Volvo", a3.getMerk());
	}

	@Test
	public void testGetModel() {
		// a1 merk = A180
		// a2 merk = Mondeo
		// a3 merk = V60

		assertEquals("A180", a1.getModel());
		assertEquals("Mondeo", a2.getModel());
		assertEquals("V60", a3.getModel());
	}

	@Test
	public void testGetBouwjaar() {
		// a1 bouwjaar = 2015
		// a2 bouwjaar = 2012
		// a3 bouwjaar = 2014

		assertEquals(2015, a1.getBouwjaar());
		assertEquals(2012, a2.getBouwjaar());
		assertEquals(2014, a3.getBouwjaar());
	}

	@Test
	public void testGetKenteken() {
		// a1 kenteken = GB1231
		// a2 kenteken = 12XVK1
		// a3 kenteken = 1ZDF53

		assertEquals("GB1231", a1.getKenteken());
		assertEquals("12XVK1", a2.getKenteken());
		assertEquals("1ZDF53", a3.getKenteken());
	}

	@Test
	public void testSetKenteken() {
		String temp = "ZZGGZZ";
		a1.setKenteken(temp);

		// Vergelijken
		assertEquals(temp, a1.getKenteken());
		// Nog een check op foute data
		assertFalse(temp.equals(a2.getKenteken()));
	}

	@After
	public void tearDown() throws Exception {
	}

}
