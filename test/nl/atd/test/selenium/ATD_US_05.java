package nl.atd.test.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.ConfigHelper;
import nl.atd.model.Auto;
import nl.atd.model.Klant;
import nl.atd.model.Monteur;
import nl.atd.service.AutoService;
import nl.atd.service.KlantService;
import nl.atd.service.KlusService;
import nl.atd.service.MonteurService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ATD_US_05 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String gebruikersnaam, wachtwoord;
	private static Klant k1;
	private static Auto a1;
	private static Monteur m1;

	static KlusService klusService = ServiceProvider.getKlusService();
	static KlantService klantService = ServiceProvider.getKlantService();
	static AutoService autoService = ServiceProvider.getAutoService();
	static MonteurService monteurService = ServiceProvider.getMonteurService();

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
		a1.setKenteken("GB1231");
		autoService.addAuto(k1.getGebruikersnaam(), a1);
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/to4";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Gegevens kunnen hier aangepast worden aan het begin van de test
		gebruikersnaam = "henk";
		wachtwoord = "henkje101";

		driver.get(baseUrl + "/login.jsp");

		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type")))
				.selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button"))
				.click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(
				By.cssSelector("div.button-login > button.btn.btn-primary"))
				.click();

		// Navigeren naar de pagina: 'Klus inplannen'
		driver.findElement(By.linkText("Klus inplannen")).click();
	}

	@Test
	public void testATDUS05_1() {
		// Gegevens van de klus invullen
		new Select(driver.findElement(By.name("monteur")))
				.selectByVisibleText("Benco van Dam");
		driver.findElement(By.name("type")).clear();
		driver.findElement(By.name("type")).sendKeys("APK");
		driver.findElement(By.name("uren")).clear();
		driver.findElement(By.name("uren")).sendKeys("10");
		driver.findElement(By.name("datum")).clear();
		driver.findElement(By.name("datum")).sendKeys("29-06-2015");
		driver.findElement(By.name("tijdstip")).clear();
		driver.findElement(By.name("tijdstip")).sendKeys("14:00");
		driver.findElement(By.name("tijdstip")).sendKeys(Keys.TAB,
				"APK Keuring");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

		// Check
		assertTrue(isElementPresent(By
				.xpath("//div[@id='calendar']/div[2]/div/table/tbody/tr/td/div[2]/div/div[3]/table/tbody/tr/td[2]/div/a/div[2]")));
	}

	@Test
	public void testATDUS05_2() {
		// Gegevens van de klus invullen
		new Select(driver.findElement(By.name("monteur")))
				.selectByVisibleText("Benco van Dam");
		driver.findElement(By.name("type")).clear();
		driver.findElement(By.name("type")).sendKeys("APK");
		driver.findElement(By.name("uren")).clear();
		driver.findElement(By.name("uren")).sendKeys("10");
		driver.findElement(By.name("datum")).clear();
		driver.findElement(By.name("datum")).sendKeys("29-06-2015");
		driver.findElement(By.name("tijdstip")).clear();
		driver.findElement(By.name("tijdstip")).sendKeys("14:00");
		driver.findElement(By.name("tijdstip")).sendKeys(Keys.TAB, "");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}

	@Test
	public void testATDUS05_3() {
		// Gegevens van de klus invullen
		new Select(driver.findElement(By.name("monteur")))
				.selectByVisibleText("Benco van Dam");
		driver.findElement(By.name("type")).clear();
		driver.findElement(By.name("type")).sendKeys("APK");
		driver.findElement(By.name("uren")).clear();
		driver.findElement(By.name("uren")).sendKeys("10");
		driver.findElement(By.name("datum")).clear();
		driver.findElement(By.name("datum")).sendKeys("29 juni 2015");
		driver.findElement(By.name("tijdstip")).clear();
		driver.findElement(By.name("tijdstip")).sendKeys("14:00");
		driver.findElement(By.name("tijdstip")).sendKeys(Keys.TAB,
				"APK Keuring");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}

	@Test
	public void testATDUS05_4() {
		// Gegevens van de klus invullen
		new Select(driver.findElement(By.name("monteur")))
				.selectByVisibleText("Benco van Dam");
		driver.findElement(By.name("type")).clear();
		driver.findElement(By.name("type")).sendKeys("APK");
		driver.findElement(By.name("uren")).clear();
		driver.findElement(By.name("uren")).sendKeys("-5");
		driver.findElement(By.name("datum")).clear();
		driver.findElement(By.name("datum")).sendKeys("29-06-2015");
		driver.findElement(By.name("tijdstip")).clear();
		driver.findElement(By.name("tijdstip")).sendKeys("14:00");
		driver.findElement(By.name("tijdstip")).sendKeys(Keys.TAB,
				"APK Keuring");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
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
