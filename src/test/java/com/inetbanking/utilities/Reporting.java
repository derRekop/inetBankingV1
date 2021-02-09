package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This is the Listener Class used to generate the Extent Report
 * 
 * @author fvargas
 *
 */

public class Reporting extends TestListenerAdapter {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportName = "Test_Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
		try {
			sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/spark-config.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sparkReporter.config().setDocumentTitle("InetBanking Test Project");
		sparkReporter.config().setReportName("Automated Test Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("hostname", "local host");
		extent.setSystemInfo("environment", "QA");
		extent.setSystemInfo("user", "Fernando");
		
	}
	
	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";
		
		File f = new File(screenshotPath);
		
		if(f.exists()) {
			logger.fail("Screenshot: " + logger.addScreenCaptureFromPath(screenshotPath));
		}
	}
	
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
