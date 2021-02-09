package com.inetbanking.testcases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageobjects.LoginPage;
import com.inetbanking.utilities.ExcelUtils;

public class TC_002_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String usr, String pwd) throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(usr);
		logger.info("Username is provided");
		loginPage.setPassword(pwd);
		logger.info("Password is provided");
		loginPage.clickLogin();
		logger.info("Login button is clicked");
		
		if(isAlertPresent()==true) {
			captureScreen(driver, "loginTest");
			driver.switchTo().alert().accept(); //close invalid user alert
			driver.switchTo().defaultContent(); //focus on main page
			Assert.assertTrue(false);
			logger.warn("Login failed");
		} else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			//loginPage.clickLogout();
			//driver.switchTo().alert().accept(); //close logout alert
			//driver.switchTo().defaultContent(); //focus on main page
			loginPage.clickBankHome();
		}
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testdata/LoginData.xlsx";
		int rowCount = ExcelUtils.getRowCount(path, "Sheet1");
		int colCount = ExcelUtils.getCellCount(path, "Sheet1", 1);
		
		String[][] loginData = new String[rowCount][colCount];
		for(int i = 1; i <= rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				loginData[i-1][j] = ExcelUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return loginData;
	}

}
