package com.inetbanking.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	//Constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name = "password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name = "btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(linkText = "Log out")
	@CacheLookup
	WebElement linkLogout;
	
	@FindBy(linkText = "Bank Project")
	@CacheLookup
	WebElement linkBankHome;
	
	public void setUserName(String userName) {
		txtUserName.sendKeys(userName);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void clickLogout() {
		linkLogout.click();
	}
	
	public void clickBankHome() {
		linkBankHome.click();
	}

}
