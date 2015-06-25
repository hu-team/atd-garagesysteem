package nl.atd.test.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import nl.atd.helper.ConfigHelper;
import nl.atd.service.ArtikelService;
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

public class ATD_US_06 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String gebruikersnaam, wachtwoord;

	static ArtikelService artikelService = ServiceProvider.getArtikelService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Database configureren
		ConfigHelper.getProperties().put("installed", "true");
		ConfigHelper.getProperties().put("mysql.host", "localhost:8889");
		ConfigHelper.getProperties().put("mysql.database", "atd");
		ConfigHelper.getProperties().put("mysql.username", "root");
		ConfigHelper.getProperties().put("mysql.password", "root");
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
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();

		// Navigeren naar de pagina: 'Artikel toevoegen', via het menu item:
		// 'Artikel overzicht'
		driver.findElement(By.linkText("Artikelen overzicht")).click();
		driver.findElement(By.linkText("Artikel toevoegen")).click();
	}

	@Test
	public void testATDUS01_1() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("naam")).clear();
		driver.findElement(By.id("naam")).sendKeys("TestArtikel");
		driver.findElement(By.id("code")).clear();
		driver.findElement(By.id("code")).sendKeys("A999999");
		driver.findElement(By.id("aantal")).clear();
		driver.findElement(By.id("aantal")).sendKeys("10");
		driver.findElement(By.id("prijs")).clear();
		driver.findElement(By.id("prijs")).sendKeys("15");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		// Na klikken op 'Artikel toevoegen', zou er een succes alert moeten
		// komen
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-success")));
	}
	@Test
	public void testATDUS01_2() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("naam")).clear();
		driver.findElement(By.id("naam")).sendKeys("TestArtikel");
		driver.findElement(By.id("code")).clear();
		driver.findElement(By.id("code")).sendKeys("999999");
		driver.findElement(By.id("aantal")).clear();
		driver.findElement(By.id("aantal")).sendKeys("-10");
		driver.findElement(By.id("prijs")).clear();
		driver.findElement(By.id("prijs")).sendKeys("16");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		// Na klikken op 'Artikel toevoegen', zou er een danger alert moeten
		// komen
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	@Test
	public void testATDUS01_3() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("naam")).clear();
		driver.findElement(By.id("naam")).sendKeys("TestArtikel");
		driver.findElement(By.id("code")).clear();
		driver.findElement(By.id("code")).sendKeys("999999");
		driver.findElement(By.id("aantal")).clear();
		driver.findElement(By.id("aantal")).sendKeys("10");
		driver.findElement(By.id("prijs")).clear();
		driver.findElement(By.id("prijs")).sendKeys("-17");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		// Na klikken op 'Artikel toevoegen', zou er een danger alert moeten
		// komen
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
		// Toegevoegd artikel weer verwijderen
		artikelService.deleteAlleArtikelen();
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