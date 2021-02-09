package com.inetbanking.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	WebDriver driver;
	
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.LINK_TEXT, using = "New Customer")
	@CacheLookup
	WebElement lnkAddNewCustomer;
	
	@FindBy(how = How.NAME, using = "name")
	@CacheLookup
	WebElement txtCustomerName;
	
	@FindBy(how = How.NAME, using = "rad1")
	@CacheLookup
	WebElement rdGender;
	
	@FindBy(how = How.ID_OR_NAME, using = "dob")
	@CacheLookup
	WebElement txtDOB;
	
	@FindBy(how = How.NAME, using = "addr")
	@CacheLookup
	WebElement txtAddress;
	
	@FindBy(how = How.NAME, using = "city")
	@CacheLookup
	WebElement txtCity;
	
	@FindBy(how = How.NAME, using = "state")
	@CacheLookup
	WebElement txtState;
	
	@FindBy(how = How.NAME, using = "pinno")
	@CacheLookup
	WebElement txtPin;
	
	@FindBy(how = How.NAME, using = "telephoneno")
	@CacheLookup
	WebElement txtTelephone;
	
	@FindBy(how = How.NAME, using = "emailid")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.NAME, using = "sub")
	@CacheLookup
	WebElement btnSubmit;
	
	public void clickAddNewCustomer() {
		lnkAddNewCustomer.click();
	}
	
	public void setCustomerName(String name) {
		txtCustomerName.sendKeys(name);
	}
	
	public void setCustomerGender(String gender) {
		rdGender.click();
	}
	
	public void setCustomerDOB(String mm, String dd, String yy) {
		txtDOB.sendKeys(mm);
		txtDOB.sendKeys(dd);
		txtDOB.sendKeys(yy);
	}
	
	public void setCustomerAddress(String address) {
		txtAddress.sendKeys(address);
	}
	
	public void setCustomerCity(String city) {
		txtCity.sendKeys(city);
	}
	
	public void setCustomerState(String state) {
		txtState.sendKeys(state);
	}
	
	public void setCustomerPin(String pin) {
		txtPin.sendKeys(String.valueOf(pin));
	}
	
	public void setCustomerTelephone(String phone) {
		txtTelephone.sendKeys(phone);
	}
	
	public void setCustomerEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void clickSubmit() {
		btnSubmit.click();
	}

}
