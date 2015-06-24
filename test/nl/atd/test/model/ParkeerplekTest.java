package nl.atd.test.model;

import static org.junit.Assert.assertEquals;
import nl.atd.model.Parkeerplek;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParkeerplekTest {
	private Parkeerplek p1, p2, p3, p4, p5;
	
	@Before
	public void setUp() throws Exception {
		p1 = new Parkeerplek('F', 3);
		p2 = new Parkeerplek('A', 1);
		p3 = new Parkeerplek('C', 3);
		p4 = new Parkeerplek('B', 11);
	}

	@Test
	public void testGetRij() {
		// p1 rij = F
		// p2 rij = A
		// p3 rij = C
		// p4 rij = B
	
		assertEquals('F', p1.getRij());
		assertEquals('A', p2.getRij());
		assertEquals('C', p3.getRij());
		assertEquals('B', p4.getRij());
	}

	@Test
	public void testGetPlek() {
		// p1 plek = 3
		// p2 plek = 2
		// p3 plek = 3
		// p4 plek = 11
		
		assertEquals(3, p1.getPlek());
		assertEquals(1, p2.getPlek());
		assertEquals(3, p3.getPlek());
		assertEquals(11, p4.getPlek());
	}
	
	@Test
	public void testEquals() {
		p5 = new Parkeerplek('F', 3);		
		assertEquals(p1, p5);
	}
	
	@After
	public void tearDown() throws Exception {
	}
}
