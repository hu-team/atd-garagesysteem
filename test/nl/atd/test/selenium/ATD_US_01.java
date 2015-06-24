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

public class ATD_US_01 {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testATDUS01() throws Exception {
		driver.get(baseUrl + "/to4/login.jsp");
		new Select(driver.findElement(By.id("user-type")))
				.selectByVisibleText("Bedrijfsleider");
		driver.findElement(By.xpath("//div[@id='type-user']/div/div[3]/button"))
				.click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("henk");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("henkje101");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
		assertTrue(isElementPresent(By
				.xpath("//div[@id='sidebar-left']/div/ul/li[2]/a/span")));
		assertTrue(isElementPresent(By
				.xpath("//div[@id='sidebar-left']/div/ul/li[3]/a/span")));
		assertTrue(isElementPresent(By
				.xpath("//div[@id='sidebar-left']/div/ul/li[4]/a/span")));
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

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
