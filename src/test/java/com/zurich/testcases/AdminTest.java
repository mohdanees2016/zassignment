package com.zurich.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.zurich.base.TestBase;
import com.zurich.pageObjects.AdminPage;
import com.zurich.pageObjects.LoginPage;
import com.zurich.utilities.JDBCDatabaseUtilities;

public class AdminTest extends TestBase {

	LoginPage loginPage;
	AdminPage adminPage;
	JDBCDatabaseUtilities DatabaseUtilities=new JDBCDatabaseUtilities();

	public AdminTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}


	@Test(dataProvider = "addUserTestData")
	public void createAddUser(String userRole, String EmployeeName, String Status, String Username, String Password, String confirmPassword) {
		loginPage=new LoginPage(driver);
		loginPage.fnLogin("Admin", "admin123");
		adminPage=new AdminPage(driver);
		adminPage.clickAdmin();
		adminPage.clickAdd();
		adminPage.addUser(userRole, EmployeeName, Status,Username , Password, confirmPassword);
		Assert.assertTrue(isPresent(adminPage.add_btn), "User has been added successfully");

	}

	@DataProvider(name = "addUserTestData")
	public Object[][] addUserData() {

		String[][] data=DatabaseUtilities.testData("select * from adduser;");
		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		DatabaseUtilities.updateData("adduser", "Username", "TestAni" + generateRandomNumber());
	}


}
