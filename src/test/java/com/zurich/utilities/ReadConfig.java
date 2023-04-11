package com.zurich.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	 Properties prop;
	 ReadConfig readConfig = new ReadConfig();
     public String url=readConfig.getApplicationURL();
	
	public ReadConfig() {
		
		   prop = new Properties();
			try {
				 FileInputStream ip=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
				prop.load(ip);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception is " + e.getMessage());
			}
	}
	
	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String getBrowser() {
		String browser = prop.getProperty("browser");
		return browser;
	}

}
