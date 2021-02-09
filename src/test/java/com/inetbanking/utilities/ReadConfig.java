package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties p;
	
	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		try {
			FileInputStream fis = new FileInputStream(src);
			p = new Properties();
			p.load(fis);
		} catch(Exception e) {
			System.out.println("Exception is: " + e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		String url = p.getProperty("baseURL");
		return url;
	}
	
	public String getUserName() {
		String userName = p.getProperty("userName");
		return userName;
	}
	
	public String getPassword() {
		String pass = p.getProperty("password");
		return pass;
	}
	
	public String getChromePath() {
		String path = p.getProperty("chromepath");
		return path;
	}
	
	public String getEdgePath() {
		String path = p.getProperty("edgepath");
		return path;
	}
	
	public String getFireFoxPath() {
		String path = p.getProperty("firefoxpath");
		return path;
	}

}
