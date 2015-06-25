package nl.atd.test.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import nl.atd.helper.AuthHelper;
import nl.atd.helper.ConfigHelper;
import nl.atd.model.Monteur;
import nl.atd.service.MonteurService;
import nl.atd.service.ServiceProvider;

import org.junit.After;
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

public class ATD_US_03 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String gebruikersnaam, wachtwoord;

	private static Monteur m1;
	static MonteurService monteurService = ServiceProvider.getMonteurService();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Database configureren
		ConfigHelper.getProperties().put("installed", "true");
		ConfigHelper.getProperties().put("mysql.host", "localhost:8889");
		ConfigHelper.getProperties().put("mysql.database", "atd");
		ConfigHelper.getProperties().put("mysql.username", "root");
		ConfigHelper.getProperties().put("mysql.password", "root");
		
		// Aanmaken van monteur
		m1 = new Monteur("Benco van Dam", 100001);
		m1.setGebruikersnaam("bencovandam");
		m1.setWachtwoord(AuthHelper.encryptWachtwoord("monteur1"));
		monteurService.addMonteur(m1);
		
	}
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/to4";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Gegevens kunnen hier aangepast worden aan het begin van de test
		gebruikersnaam = "bencovandam";
		wachtwoord = "monteur1";
	
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als monteur
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Monteur");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
	}

	@Test
	public void testATDUS03_1() throws Exception {

		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		// Monteurs zouden de volgende knoppen moeten zien in het menu 
		assertTrue(isElementPresent(By.linkText("Klanten overzicht")));
	    assertTrue(isElementPresent(By.linkText("Artikelen overzicht")));
	}

	@Test
	public void testATDUS03_2() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("fout_ww");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS03_3() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS03_4() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("fout_geb");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS03_5() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("fout_geb");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("fout_ww");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS03_6() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("fout_geb");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS03_7() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS03_8() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("foutww");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS03_9() throws Exception {
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
		
		// Monteur verwijderen
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