package com.zurich.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zurich.utilities.Utilities;

public class TestBase {

	WebDriver driver;
	public Properties prop;

	public TestBase() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		try {
			FileInputStream ip=new FileInputStream(propFile);
			prop.load(ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to invoke browser
	 * @param browserName
	 * @return
	 */
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");

			driver=new ChromeDriver(options);
		}else if(browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-private");
			driver = new FirefoxDriver(options);
		}
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("baseURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		return driver;

	}

	/**
	 * Method used to wait for availability of element
	 * @param driver
	 * @param ele
	 */
	public void waitForVisibilityOf(WebDriver driver, WebElement ele) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		webDriverWait.until(ExpectedConditions.visibilityOf(ele));
	}


	/**
	 * Method to enter value
	 * @param driver
	 * @param locatorKey
	 * @param data
	 */
	public void input(WebDriver driver, WebElement locatorKey, String data) {
		waitForVisibilityOf(driver,locatorKey);
		locatorKey.sendKeys(data);
	}

	public void click(WebDriver driver, WebElement toclick) {

		waitForVisibilityOf(driver, toclick);
		toclick.click();
	}


	/**
	 * Method to close browser
	 */
	public void closeBrowser() {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			driver.close();
		}
	}


	/**
	 * 
	 * @param ele
	 * @return
	 */
	public boolean isPresent(WebElement ele) {
		boolean flag=ele.isDisplayed();
		return flag;
	}


	/**
	 * Method to generate random number
	 * @return
	 */
	public int generateRandomNumber() {
		Random random = new Random();
		int upperbound = 100;
		int int_random=random.nextInt(upperbound);
		return int_random;
	}

}
