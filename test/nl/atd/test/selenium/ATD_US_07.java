package nl.atd.test.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import nl.atd.helper.ConfigHelper;
import nl.atd.model.Artikel;
import nl.atd.service.ArtikelService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ATD_US_07 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String gebruikersnaam, wachtwoord;
	private static Artikel a1;

	static ArtikelService artikelService = ServiceProvider.getArtikelService();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Database configureren
		ConfigHelper.getProperties().put("installed", "true");
		ConfigHelper.getProperties().put("mysql.host", "localhost:8889");
		ConfigHelper.getProperties().put("mysql.database", "atd");
		ConfigHelper.getProperties().put("mysql.username", "root");
		ConfigHelper.getProperties().put("mysql.password", "root");

		a1 = new Artikel("Winterbanden", 15);
		a1.setCode("A1001");
		a1.setPrijs(49);
		artikelService.addArtikel(a1);
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/to4";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Gegevens kunnen hier aangepast worden aan het begin van de test
		gebruikersnaam = "henk";
		wachtwoord = "henkje101";
	}

	@Test
	public void testATDUS07() {
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
	    driver.findElement(By.cssSelector("a.btn.btn-success")).click();
	    driver.findElement(By.id("aantal")).clear();
	    driver.findElement(By.id("aantal")).sendKeys("20");
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    
	    try {
	        assertEquals("20", driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[3]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}

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
