package com.zurich.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.zurich.base.TestBase;
import com.zurich.pageObjects.AdminPage;
import com.zurich.pageObjects.LoginPage;

public class LoginTest extends TestBase {

	LoginPage loginPage;
	AdminPage adminPage;

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod()
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}


	@Test
	public void verifySuccessfulLogin() {
		loginPage=new LoginPage(driver);
		adminPage= loginPage.fnLogin("Admin", "admin123");
		Assert.assertTrue(isPresent(adminPage.admin_Link),"Login unsuccessful");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
