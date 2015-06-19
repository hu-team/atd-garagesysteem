package nl.atd.test.model;

import static org.junit.Assert.*;
import nl.atd.model.Artikel;
import nl.atd.model.Onderdeel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OnderdeelTest {
	private Artikel a1, a2, a3;
	private Onderdeel ond1, ond2, ond3, ond4;
	
	@Before
	public void setUp() throws Exception {
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
	}

	@Test
	public void testGetArtikel() {
		// ond1 = a1
		// ond2 = a2
		// ond3 = a3

		assertEquals(a1, ond1.getArtikel());
		assertEquals(a2, ond2.getArtikel());
		assertEquals(a3, ond3.getArtikel());
	}
	
	@Test
	public void testGetAantal() {
		// ond1 aantal = 8
		// ond2 aantal = 3
		// ond3 aantal = 2

		assertEquals(8, ond1.getAantal());
		assertEquals(3, ond2.getAantal());
		assertEquals(2, ond3.getAantal());
	}
	
	@Test 
	public void testEquals() {
		ond4 = new Onderdeel(a1, 8);
		
		assertEquals(ond1, ond4);
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
