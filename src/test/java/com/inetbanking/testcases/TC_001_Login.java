package com.inetbanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPage;

public class TC_001_Login extends BaseClass {

	@Test
	public void loginTest() throws IOException {
		
		logger.info("URL is opened");
		 
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		
		logger.info("Username is entered");
		
		lp.setPassword(password);
		
		logger.info("Password is entered");
		
		lp.clickLogin();
		
		if(driver.getTitle().equals("GTPL Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		} else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}
}
