package nl.atd.test.selenium;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
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
	
	private String gebruikersnaam, wachtwoord;

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/to4";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Gegevens kunnen hier aangepast worden aan het begin van de test
		gebruikersnaam = "klantuser";
		wachtwoord = "monteur2";
	}

	@Test
	public void testATDUS12() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als klant
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Klant");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(wachtwoord);
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		
		// Klanten zouden de volgende knoppen moeten zien in het menu 
		assertTrue(isElementPresent(By.linkText("Klus inplannen")));
	    assertTrue(isElementPresent(By.linkText("Plek Reserveren")));
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

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}