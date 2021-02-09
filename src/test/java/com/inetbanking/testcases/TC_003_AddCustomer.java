package com.inetbanking.testcases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.AddCustomerPage;
import com.inetbanking.pageobjects.LoginPage;

public class TC_003_AddCustomer extends BaseClass {
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("Username is provided");
		lp.setPassword(password);
		logger.info("Password is provided");
		lp.clickLogin();
		
		Thread.sleep(3000);
		
		AddCustomerPage newCust = new AddCustomerPage(driver);
		
		logger.info("Providing new customer details...");
		newCust.clickAddNewCustomer();
		newCust.setCustomerName("Fernando");
		newCust.setCustomerGender("male");
		newCust.setCustomerDOB("10", "15", "1985");
		Thread.sleep(3000);
		newCust.setCustomerAddress("Mexico");
		newCust.setCustomerCity("Monterrey");
		newCust.setCustomerState("NL");
		newCust.setCustomerPin("123456");
		newCust.setCustomerTelephone("9808883000");
		newCust.setCustomerEmail(rndEmail());
		newCust.clickSubmit();
		Thread.sleep(3000);
		
		logger.info("Validation started");
		if(driver.getPageSource().contains("Customer Registered Successfully!!!")) {
			Assert.assertTrue(true);
			logger.info("Test case passed");
		} else {
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
			logger.info("Test case failed");
		}
	}

}
