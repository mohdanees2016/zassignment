package com.zurich.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static ExtentReports generalExtentReport() {
		ExtentReports extentReport=new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir")+ "\\test-output\\extentReports\\extentReportZurich.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Zurich orange Test Automation Report");
		sparkReporter.config().setDocumentTitle("Zurich Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		
		
		Properties prop = new Properties();
			try {
				FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
				prop.load(ip);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception is " + e.getMessage());
			}
			
			extentReport.setSystemInfo("Application URL ", prop.getProperty("baseURL"));
			extentReport.setSystemInfo("Operating System ", System.getProperty("os.name"));
			extentReport.setSystemInfo("Java Version ", System.getProperty("java.version"));
			return extentReport;
		
	}

}
