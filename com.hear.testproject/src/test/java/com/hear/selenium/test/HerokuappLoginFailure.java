package com.hear.selenium.test;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//This class would test login failure

public class HerokuappLoginFailure {
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	WebDriver driver;

	@Before
	public void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		LOGGER.info("Setup driver");
	}

	@Test
	public void loginFailure() throws Exception {
		FileInputStream configValue = new FileInputStream("./resources/configValue.properties");
		Properties property = new Properties();
		property.load(configValue);
		driver.get(property.getProperty("loginUrl"));
		LOGGER.info("Launch URL");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys(property.getProperty("username"));
		LOGGER.info("Enter username");
		driver.findElement(By.name("password")).sendKeys(property.getProperty("wrongPassword"));
		LOGGER.info("Enter password");
		driver.findElement(By.cssSelector("#login > button")).submit();
		LOGGER.info("Submit button");
		String actualResult = driver.findElement(By.id("flash")).getText();
		actualResult = actualResult.replaceAll("[^a-zA-Z0-9 ]","");
		LOGGER.info(actualResult);
		String expectedResult = property.getProperty("failureMsg");
		Assert.assertEquals(expectedResult, actualResult);

	}

	@After
	public void closeBrowser() {
		driver.close();
		LOGGER.info("Close browser");
	}

}
