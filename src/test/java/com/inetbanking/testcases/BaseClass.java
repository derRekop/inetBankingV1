package com.inetbanking.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL = readConfig.getApplicationURL();
	public String userName = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		
		logger = Logger.getLogger("eBanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		switch(browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", readConfig.getFireFoxPath());
			driver = new FirefoxDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", readConfig.getEdgePath());
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default: System.out.println("Browser not supported");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}
	
	public void captureScreen(WebDriver driver, String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String tstName = testName + "_Screen_" + timeStamp + ".png";
		
		
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tstName);
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String rndEmail() {
		String email = RandomStringUtils.randomAlphabetic(8) + "@test.com";
		return email;
	}
	
	public String rndNumber() {
		String rndNum = RandomStringUtils.randomNumeric(5);
		return rndNum;
	}

}
