package nl.atd.test.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import nl.atd.helper.ConfigHelper;
import nl.atd.service.MonteurService;
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

public class ATD_US_02 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String gebruikersnaam, wachtwoord;
  
  MonteurService monteurService = ServiceProvider.getMonteurService();
  
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
  }

  @Test
  public void testATDUS02() throws Exception {
    driver.get(baseUrl + "/login.jsp");
   
    // Inloggen als bedrijfsleider
    new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
    driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys(gebruikersnaam);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(wachtwoord);
    driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
    
    // Navigeren naar de pagina: 'Monteur toevoegen', via het menu item: 'Monteurs overzicht'
    driver.findElement(By.linkText("Monteurs overzicht")).click();
    driver.findElement(By.linkText("Monteur toevoegen")).click();
    
    // Alle gevraagde gegevens invullen
    driver.findElement(By.id("voornaam")).clear();
    driver.findElement(By.id("voornaam")).sendKeys("TestMonteur");
    driver.findElement(By.id("achternaam")).clear();
    driver.findElement(By.id("achternaam")).sendKeys("TestAchternaam");
    driver.findElement(By.id("salarisnummer")).clear();
    driver.findElement(By.id("salarisnummer")).sendKeys("999999");
    driver.findElement(By.id("gebruikersnaam")).clear();
    driver.findElement(By.id("gebruikersnaam")).sendKeys("TestUsername");
    driver.findElement(By.id("wachtwoord")).clear();
    driver.findElement(By.id("wachtwoord")).sendKeys("testww");
    driver.findElement(By.id("wachtwoord2")).clear();
    driver.findElement(By.id("wachtwoord2")).sendKeys("testww");
    
    // Na klikken op 'Monteur toevoegen', zou er een succes alert moeten komen
    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
    assertTrue(isElementPresent(By.cssSelector("div.alert.alert-success")));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    
    // Toegevoegde monteur weer verwijderen
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
