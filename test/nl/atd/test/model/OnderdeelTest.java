package nl.atd.test.model;

import static org.junit.Assert.*;
import nl.atd.model.Artikel;
import nl.atd.model.Onderdeel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OnderdeelTest {
	private Artikel a1, a2, a3;
	private Onderdeel ond1, ond2, ond3;
	
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
	public void test() {
		fail("Not yet implemented");
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
