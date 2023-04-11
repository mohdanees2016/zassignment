package com.zurich.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReadListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	public void onStart(ITestContext context) {
		extentReport = ExtentReporter.generalExtentReport();
		
	}

	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started executing");
		
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, testName + " got successfully executed");
		
	}

	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " got failed");
	}


	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+ "\\test-output\\extentReports\\extentReportZurich.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}