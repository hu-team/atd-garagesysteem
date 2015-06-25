package nl.atd.test.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.ConfigHelper;
import nl.atd.model.Klant;
import nl.atd.service.KlantService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ATD_US_12 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private static Klant k1;
	static KlantService klantService = ServiceProvider.getKlantService();

	// Gegevens kunnen hier aangepast worden aan het begin van de test	
	private String gebruikersnaam = "klantuser";
	private String wachtwoord = "monteur2";
	
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
		k1.setGebruikersnaam("klantuser");
		k1.setWachtwoord(AuthHelper.encryptWachtwoord("monteur2"));
		k1.setLaatsteBezoek(null);
		klantService.addKlant(k1);

	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/to4";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testATDUS12() throws Exception {
		driver.get(baseUrl + "/login.jsp");

		// Inloggen als klant
		new Select(driver.findElement(By.id("user-type")))
				.selectByVisibleText("Klant");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button"))
				.click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(
				By.cssSelector("div.button-login > button.btn.btn-primary"))
				.click();

		// Klanten zouden de volgende knoppen moeten zien in het menu
		assertTrue(isElementPresent(By.linkText("Klus inplannen")));
		assertTrue(isElementPresent(By.linkText("Plek reserveren")));
		assertTrue(isElementPresent(By.linkText("Mijn gegevens wijzigen")));
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
		// Klant verwijderen uit DB
		klantService.deleteAlleKlanten();
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