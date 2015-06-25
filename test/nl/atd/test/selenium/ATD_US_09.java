package nl.atd.test.selenium;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.ConfigHelper;
import nl.atd.model.Artikel;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Klus;
import nl.atd.model.Monteur;
import nl.atd.model.Onderdeel;
import nl.atd.service.ArtikelService;
import nl.atd.service.AutoService;
import nl.atd.service.KlantService;
import nl.atd.service.KlusService;
import nl.atd.service.MonteurService;
import nl.atd.service.OnderdeelService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ATD_US_09 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String gebruikersnaam, wachtwoord;
	
	private static Artikel artikel1;
	private static Klant k1;
	private static Auto a1;
	private static Monteur m1;
	private Klus klus1;
	
	static ArtikelService artikelService = ServiceProvider.getArtikelService();
	static OnderdeelService onderdeelService = ServiceProvider.getOnderdeelService();
	static KlusService klusService = ServiceProvider.getKlusService();
	static KlantService klantService = ServiceProvider.getKlantService();
	static AutoService autoService = ServiceProvider.getAutoService();
	static MonteurService monteurService = ServiceProvider.getMonteurService();
	private static Calendar temp1;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Database configureren
		ConfigHelper.getProperties().put("installed", "true");
		ConfigHelper.getProperties().put("mysql.host", "localhost:8889");
		ConfigHelper.getProperties().put("mysql.database", "atd");
		ConfigHelper.getProperties().put("mysql.username", "root");
		ConfigHelper.getProperties().put("mysql.password", "root");

		// Aanmaken van klant
		k1 = new Klant("Max van Kuik");
		k1.setEmail("kuikvanmax@hotmail.com");
		k1.setGebruikersnaam("maxiiemaxx");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("123"));
		k1.setLaatsteBezoek(null);
		klantService.addKlant(k1);
		
		// Aanmaken van monteur
		m1 = new Monteur("Benco van Dam", 100001);
		m1.setGebruikersnaam("bencovandam");
		m1.setWachtwoord(AuthHelper.encryptWachtwoord("monteur1"));
		monteurService.addMonteur(m1);
		
		// Aanmaken van auto
		a1 = new Auto("Mercedes", "A180", 2015, null);
		a1.setKenteken("64ZSP1");
		autoService.addAuto(k1.getGebruikersnaam(), a1);
		
		// Aanmaken van data
		// Testen over aantal dagen, betekent dat data veranderd moet worden ivm de agenda
		temp1 = Calendar.getInstance();
		temp1.set(2015, Calendar.JUNE, 29, 15, 30);
		
		// Aanmaken van artikel
		artikel1 = new Artikel("Winterbanden", 55);
		artikel1.setCode("A1001");
		artikel1.setPrijs(49);
		artikelService.addArtikel(artikel1);
		
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/to4";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Gegevens kunnen hier aangepast worden aan het begin van de test
		gebruikersnaam = "henk";
		wachtwoord = "henkje101";	
		
		// Aanmaken van klus
		klus1 = new Klus(k1, a1);
		klus1.setMonteur(m1);
		klus1.setOmschrijving("Winterbanden vervangen door zomerbanden");
		klus1.setType("Onderhoud");
		klus1.setUren(4);
		klus1.setCalendar(temp1);
		klusService.addKlus(klus1);
		
	}

	@Test
	public void testATDUS09() {
		driver.get(baseUrl + "/login.jsp");

		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		// Dit moet aangepast worden bij een andere testdatum
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    driver.findElement(By.xpath("//div[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[3]/table/tbody/tr/td[2]/div/a/div[2]")).click();
	    driver.findElement(By.linkText("Onderdelen bewerken")).click();
	    driver.findElement(By.id("aantal")).clear();
	    driver.findElement(By.id("aantal")).sendKeys("1");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    assertTrue(isElementPresent(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td")));
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Verwijder alles uit DB 
		klusService.deleteAlleKlussen();
		klantService.deleteAlleKlanten();
		autoService.deleteAlleAutos();
		monteurService.deleteAlleMonteurs();
		artikelService.deleteAlleArtikelen();
		onderdeelService.deleteAlleOnderdelen();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}