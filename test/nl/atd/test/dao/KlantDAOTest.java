package nl.atd.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import nl.atd.dao.KlantDAO;
import nl.atd.helper.AuthHelper;
import nl.atd.model.Klant;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KlantDAOTest {
	private Klant k1;
	private Klant k2;

	private ArrayList<Klant> klanten = new ArrayList<Klant>();

	private KlantDAO klantDAO = new KlantDAO();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxiiemaxx");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("123"));
		klanten.add(k1);

		k2 = new Klant("Tom Valk");
		k2.setEmail("tomvalk@hotmail.com");
		k2.setGebruikersnaam("tomvalk");
		k2.setWachtwoord(AuthHelper.encryptWachtwoord("456"));
		klanten.add(k2);
	}

	@Test
	public void testGetAlleKlanten() {

		ArrayList<Klant> actualKlanten = klantDAO.getAlleKlanten(false);
		
		Klant k3 = actualKlanten.get(0);
		
		
		assertEquals(k3.getGebruikersnaam(), k1.getGebruikersnaam());

		// assertEquals("Check of naam klopt", "Max van Kuik", k1.getNaam());
		// assertEquals("Check of email klopt", "kuikvanmax@hotmail.com",
		// k1.getEmail());
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
