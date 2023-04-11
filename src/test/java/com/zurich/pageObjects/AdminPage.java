package com.zurich.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurich.base.TestBase;

public class AdminPage extends TestBase {



	public AdminPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	WebDriver driver;

	@FindBy(xpath = "(//*[text()='Admin'])[1]")
	public WebElement admin_Link;

	@FindBy(xpath = "//*[text()=' Add ']")
	public WebElement add_btn;

	@FindBy(xpath = "//*[contains(text(),'User Role')]/parent::div/following-sibling::div//div[text()='-- Select --']")
	public WebElement userRole_drpdwn;


	@FindBy(xpath = "//*[contains(text(),'Employee Name')]/parent::div/following-sibling::div//input")
	public WebElement employeeName_Edt;

	@FindBy(xpath = "//*[contains(text(),'Status')]/parent::div/following-sibling::div//div[text()='-- Select --']")
	public WebElement status_drpdwn;

	@FindBy(xpath = "//*[contains(text(),'Username')]/parent::div/following-sibling::div//input")
	public WebElement username_Edt;

	@FindBy(xpath = "//*[text()='Password']/parent::div/following-sibling::div//input")
	public WebElement password_Edt;

	@FindBy(xpath = "//*[text()='Confirm Password']/parent::div/following-sibling::div//input")
	public WebElement confirmPassowrd_Edt;

	@FindBy(xpath = "//*[text()=' Cancel ']")
	public WebElement cancel_Btn;

	@FindBy(xpath = "//*[text()=' Save ']")
	public WebElement save_Btn;

	public void clickAdmin() {
		click(driver, admin_Link);
	}

	public void clickAdd() {
		click(driver, add_btn);
	}

	public void selectUserRole(String roleType) {
		click(driver, userRole_drpdwn);
		WebElement roleType_Ele= driver.findElement(By.xpath("//*[@role='listbox']//*[text()='"+ roleType + "']"));
		click(driver, roleType_Ele);
	}

	public void setEmployeeName(String empName_txt) {
		input(driver, employeeName_Edt, empName_txt);
		WebElement empName_Ele=driver.findElement(By.xpath("//*[@role='listbox']//*[text()='"+empName_txt+"']"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(driver, empName_Ele);
	}

	public void selectStatus(String statusOption) {
		click(driver, status_drpdwn);
		WebElement statusOption_Ele= driver.findElement(By.xpath("//*[@role='listbox']//*[text()='"+ statusOption + "']"));
		click(driver, statusOption_Ele);
	}

	public void setUsername(String userName_txt) {
		input(driver, username_Edt, userName_txt);

	}

	public void setPassword(String password_txt) {
		input(driver, password_Edt, password_txt);

	}

	public void setConfirmPassword(String confirmPassword_txt) {
		input(driver, confirmPassowrd_Edt, confirmPassword_txt);

	}

	public void clickSave() {
		click(driver, save_Btn);
	}

	public void addUser(String roleType, String empName_txt, String statusOption, String userName_txt, String password_txt, String confirmPassword_txt) {
		selectUserRole(roleType);
		setEmployeeName(empName_txt);
		selectStatus(statusOption);
		setUsername(userName_txt);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setPassword(password_txt);
		setConfirmPassword(confirmPassword_txt);
		clickSave();

	}




}
