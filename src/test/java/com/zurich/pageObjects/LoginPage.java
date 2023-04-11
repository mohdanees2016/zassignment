package com.zurich.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.zurich.base.TestBase;

public class LoginPage extends TestBase {



	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;

	@FindBy(name = "username")
	private WebElement username_EdtField;

	@FindBy(name = "password")
	private WebElement password_EdtField;

	@FindBy(xpath = "//*[@type='submit']")
	private WebElement login_Btn;

	public void setUserName(String uname) {
		input(driver, username_EdtField, uname);
	}

	public void setPassword(String pwd) {
		input(driver, password_EdtField, pwd);
	}

	public void clickLogin() {
		click(driver, login_Btn);
	}

	public AdminPage fnLogin(String username_Value, String password_Value) {
		setUserName(username_Value);
		setPassword(password_Value);
		clickLogin();
		return new AdminPage(driver);
	}

}
