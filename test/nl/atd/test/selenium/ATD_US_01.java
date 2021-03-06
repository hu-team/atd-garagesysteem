package nl.atd.test.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ATD_US_01 {
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
		gebruikersnaam = "henk";
		wachtwoord = "henkje101";
	}
	
	@Test
	public void testATDUS01_1() throws Exception {
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

		// De bedrijfsleider zou de volgende knoppen moeten zien in het menu
		assertTrue(isElementPresent(By.linkText("Monteurs overzicht")));
		assertTrue(isElementPresent(By.linkText("Auto overzicht")));
		assertTrue(isElementPresent(By.linkText("Factuur overzicht")));
		assertTrue(isElementPresent(By.linkText("Klanten overzicht")));
		assertTrue(isElementPresent(By.linkText("Artikelen overzicht")));
		// Knop Parkeerplekken overzicht
		assertTrue(isElementPresent(By
				.xpath("//div[@id='sidebar-left']/div/ul/li[4]/a")));
	}
	
	@Test
	public void testATDUS01_2() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
	    driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("henk");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("pipo");
	    driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
	    assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	@Test
	public void testATDUS01_3() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("henk");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	@Test
	public void testATDUS01_4() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("pipo");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("henkje101");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	@Test
	public void testATDUS01_5() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("pipo");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("pipo");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	@Test
	public void testATDUS01_6() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("pipo");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	@Test
	public void testATDUS01_7() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("henkje101");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS01_8() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("pipo");
		driver.findElement(By.cssSelector("div.button-login > button.btn.btn-primary")).click();
		assertTrue(isElementPresent(By.cssSelector("div.alert.alert-danger")));
	}
	
	@Test
	public void testATDUS01_9() throws Exception {
		driver.get(baseUrl + "/login.jsp");
		
		// Inloggen als bedrijfsleider
		new Select(driver.findElement(By.id("user-type"))).selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button")).click();
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