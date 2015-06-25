package nl.atd.test.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import nl.atd.helper.ConfigHelper;
import nl.atd.service.KlantService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ATD_US_04 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String gebruikersnaam, wachtwoord;

	static KlantService klantService = ServiceProvider.getKlantService();

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
		klantService.deleteAlleKlanten();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/to4";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Gegevens kunnen hier aangepast worden aan het begin van de test
		// (Admin gegevens)
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

		// Navigeren naar de pagina: 'Klant toevoegen', via het menu item:
		// 'Klanten overzicht'
		driver.findElement(By.linkText("Klanten overzicht")).click();
		driver.findElement(By.linkText("Klant toevoegen")).click();
	}

	@Test
	public void testATDUS04_1() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("voornaam")).clear();
		driver.findElement(By.id("voornaam")).sendKeys("TestKlant");
		driver.findElement(By.id("achternaam")).clear();
		driver.findElement(By.id("achternaam")).sendKeys("TestAchternaam");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("TestEmail@test.com");
		driver.findElement(By.id("telefoonnr")).clear();
		driver.findElement(By.id("telefoonnr")).sendKeys("0318556231");
		driver.findElement(By.id("adres")).clear();
		driver.findElement(By.id("adres")).sendKeys("TestAdres");
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("postcode")).sendKeys("9999XX");
		driver.findElement(By.id("woonplaats")).clear();
		driver.findElement(By.id("woonplaats")).sendKeys("Amsterdam");
		driver.findElement(By.id("gebruikersnaam")).clear();
		driver.findElement(By.id("gebruikersnaam")).sendKeys("TestUsername");
		driver.findElement(By.id("wachtwoord")).clear();
		driver.findElement(By.id("wachtwoord")).sendKeys("Testww");
		driver.findElement(By.id("wachtwoord2")).clear();
		driver.findElement(By.id("wachtwoord2")).sendKeys("Testww");
		// als smtp server uitstaat deze erbij
		driver.findElement(By.id("welkomsmail")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		// Na klikken op 'Klant toevoegen', zou er een succes alert moeten
		// komen
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-success")));

		// Uitloggen en checken of ingelogd kan worden met het nieuwe klant
		// account
		driver.get(baseUrl + "/logout");

		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button"))
				.click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("TestUsername");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Testww");
		driver.findElement(
				By.cssSelector("div.button-login > button.btn.btn-primary"))
				.click();
		assertTrue(isElementPresent(By.linkText("Klus inplannen")));
	}

	@Test
	public void testATDUS04_2() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("voornaam")).clear();
		driver.findElement(By.id("voornaam")).sendKeys("TestKlant");
		driver.findElement(By.id("achternaam")).clear();
		driver.findElement(By.id("achternaam")).sendKeys("TestAchternaam");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("TestEmail@test.com");
		driver.findElement(By.id("telefoonnr")).clear();
		driver.findElement(By.id("telefoonnr")).sendKeys("ABCDEF");
		driver.findElement(By.id("adres")).clear();
		driver.findElement(By.id("adres")).sendKeys("TestAdres");
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("postcode")).sendKeys("9999XX");
		driver.findElement(By.id("woonplaats")).clear();
		driver.findElement(By.id("woonplaats")).sendKeys("Amsterdam");
		driver.findElement(By.id("gebruikersnaam")).clear();
		driver.findElement(By.id("gebruikersnaam")).sendKeys("TestUsername");
		driver.findElement(By.id("wachtwoord")).clear();
		driver.findElement(By.id("wachtwoord")).sendKeys("Testww");
		driver.findElement(By.id("wachtwoord2")).clear();
		driver.findElement(By.id("wachtwoord2")).sendKeys("Testww");
		// als smtp server uitstaat deze erbij
		driver.findElement(By.id("welkomsmail")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		// Na klikken op 'Klant toevoegen', zou er een danger alert moeten
		// komen
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}

	@Test
	public void testATDUS04_3() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("voornaam")).clear();
		driver.findElement(By.id("voornaam")).sendKeys("TestKlant");
		driver.findElement(By.id("achternaam")).clear();
		driver.findElement(By.id("achternaam")).sendKeys("TestAchternaam");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("TestEmail@test.com");
		driver.findElement(By.id("telefoonnr")).clear();
		driver.findElement(By.id("telefoonnr")).sendKeys("");
		driver.findElement(By.id("adres")).clear();
		driver.findElement(By.id("adres")).sendKeys("TestAdres");
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("postcode")).sendKeys("ASDAS");
		driver.findElement(By.id("woonplaats")).clear();
		driver.findElement(By.id("woonplaats")).sendKeys("Amsterdam");
		driver.findElement(By.id("gebruikersnaam")).clear();
		driver.findElement(By.id("gebruikersnaam")).sendKeys("TestUsername");
		driver.findElement(By.id("wachtwoord")).clear();
		driver.findElement(By.id("wachtwoord")).sendKeys("Testww");
		driver.findElement(By.id("wachtwoord2")).clear();
		driver.findElement(By.id("wachtwoord2")).sendKeys("Testww");
		// als smtp server uitstaat deze erbij
		driver.findElement(By.id("welkomsmail")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		// Na klikken op 'Klant toevoegen', zou er een danger alert moeten
		// komen
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}

	@Test
	public void testATDUS04_4() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("voornaam")).clear();
		driver.findElement(By.id("voornaam")).sendKeys("TestKlant");
		driver.findElement(By.id("achternaam")).clear();
		driver.findElement(By.id("achternaam")).sendKeys("TestAchternaam");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("TestEmail@test.com");
		driver.findElement(By.id("telefoonnr")).clear();
		driver.findElement(By.id("telefoonnr")).sendKeys("03125121");
		driver.findElement(By.id("adres")).clear();
		driver.findElement(By.id("adres")).sendKeys("TestAdres");
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("postcode")).sendKeys("9999XX");
		driver.findElement(By.id("woonplaats")).clear();
		driver.findElement(By.id("woonplaats")).sendKeys("Amsterdam");
		driver.findElement(By.id("gebruikersnaam")).clear();
		driver.findElement(By.id("gebruikersnaam")).sendKeys("TestUsername");
		driver.findElement(By.id("wachtwoord")).clear();
		driver.findElement(By.id("wachtwoord")).sendKeys("Testww");
		driver.findElement(By.id("wachtwoord2")).clear();
		driver.findElement(By.id("wachtwoord2")).sendKeys("Testww01");
		// als smtp server uitstaat deze erbij
		driver.findElement(By.id("welkomsmail")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		// Na klikken op 'Klant toevoegen', zou er een danger alert moeten
		// komen
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}

	@Test
	public void testATDUS04_5() throws Exception {
		// Alle gevraagde gegevens invullen
		driver.findElement(By.id("voornaam")).clear();
		driver.findElement(By.id("voornaam")).sendKeys("TestKlant");
		driver.findElement(By.id("achternaam")).clear();
		driver.findElement(By.id("achternaam")).sendKeys("TestAchternaam");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("TestEmail@test.com");
		driver.findElement(By.id("telefoonnr")).clear();
		driver.findElement(By.id("telefoonnr")).sendKeys("03125121");
		driver.findElement(By.id("adres")).clear();
		driver.findElement(By.id("adres")).sendKeys("TestAdres");
		driver.findElement(By.id("postcode")).clear();
		driver.findElement(By.id("postcode")).sendKeys("");
		driver.findElement(By.id("woonplaats")).clear();
		driver.findElement(By.id("woonplaats")).sendKeys("Amsterdam");
		driver.findElement(By.id("gebruikersnaam")).clear();
		driver.findElement(By.id("gebruikersnaam")).sendKeys("TestUsername");
		driver.findElement(By.id("wachtwoord")).clear();
		driver.findElement(By.id("wachtwoord")).sendKeys("Testww");
		driver.findElement(By.id("wachtwoord2")).clear();
		driver.findElement(By.id("wachtwoord2")).sendKeys("Testww");
		// als smtp server uitstaat deze erbij
		driver.findElement(By.id("welkomsmail")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		// Na klikken op 'Klant toevoegen', zou er een danger alert moeten
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
		// Toegevoegde klant weer verwijderen
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
