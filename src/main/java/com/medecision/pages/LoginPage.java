package com.medecision.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.medecision.utils.DriverInitialize;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class LoginPage extends SeleniumBase   {
	DriverInitialize DriverInitialize= new DriverInitialize();
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public LoginPage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}

	public LoginPage enterUsername(String username,String Url) throws InterruptedException {
		try {
		currentdriver.navigate().to(Url);
		clearAndType("//input[@type='email']", username);
		test.log(LogStatus.INFO, "User ID Entered In the UserID Input Field:<font color='blue'><b>"+username+"</b></font>");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Environment is Down");
			TakescreenShot(currentdriver,test);
			DriverInitialize.extent.flush();
			System.exit(1);
		}
		return this;
	}
	public LoginPage enterPassword(String password) throws InterruptedException {
		clearAndType("//input[@type='password']", password);
		return this;
	}
	public HomePage clickLogin(String username) {
		try {
			movetoelement("//button[@type='submit']");	
			verifyelementpresentExplicitwait("//button[@type='submit']",120);
			click("//button[@type='submit']");
			if(verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",15)) {
			HomePageVerification(username);
	      	clickgotit();
			}else if(verifyelementpresentExplicitwait("//h3[contains(text(),'SAMHSA Redisclosure Warning')]",5)) {
				SamshaPopup();
				clickgotit();	
			}
			else if(verifyelementpresentExplicitwait("(//td[contains(text(),'SAMHSA Redisclosure Warning')])[1]",5)) {
				SamshaPopup();
				clickgotit();	
			}
			else {
				test.log(LogStatus.FAIL, "Unable to Accept Samsha Popup, Dashboard Login Failed !");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Dashboard Login Failed !");	
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return new HomePage(driver, test);
	}
	public LoginPage clickLoginWrongPassverify() {
		try {
			movetoelement("//button[@type='submit']");	
			verifyelementpresentExplicitwait("//button[@type='submit']",120);
			click("//button[@type='submit']");
			if(verifyelementpresentExplicitwait("//md-dialog[contains(@aria-label,'Login FailedInvalid')]",120)) {
			String Errormsg=getElementText("//md-dialog[contains(@aria-label,'Login FailedInvalid')]//p");
			test.log(LogStatus.PASS, "Login Failed Popup Appeared, Error message is:</br>"+Errormsg);	
			}
			else {
				test.log(LogStatus.FAIL, "Dashboard Login Failed !");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Dashboard Login Failed !");	
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public void  clickgotit() {
		try {
		
		click("//div[contains(text(),'Got')]");
		test.log(LogStatus.INFO, "Got It Button Clicked");
		}catch(Exception e) {
			test.log(LogStatus.INFO, "Got It Section Not Appeared");	
		}
	}
	public LoginPage SamshaPopup() {
		try {
			
				click("//label[contains(text(),'redisclosure warning')]");
				verifyelementpresentExplicitwait("//label[contains(text(),'redisclosure warning')]//following::div[contains(text(),'I Agree')]");
				click("//label[contains(text(),'redisclosure warning')]//following::div[contains(text(),'I Agree')]");
			
		}catch(Exception e) {
		System.out.println(e.getLocalizedMessage());
		TakescreenShot(currentdriver,test);
		
		}
		return this;
	}
	public LoginPage redirectLogin() {
		pageReload();
		return this;
	}
	public LoginPage SecondLogin() {
		test.log(LogStatus.WARNING, "Home Page Verification Failed, So Redirect Login Occured");
		return this;
	}
	public boolean HomePageClick(){
		try {
		actionsclick("//div[contains(@id,'appHome_menu')]");
		return true;
		}catch(Exception e) {
		test.log(LogStatus.WARNING, "Unhandlable Popup Appeared, So Relogin Initiated");
		TakescreenShot(currentdriver,test);
		return false;
		}
		
		
	}
	public boolean HomePageVerification(String username){
		boolean loadingpageverification=verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]");
		if(loadingpageverification) {
		boolean homepageverification =verifyelementClickableExplicitwait("//div[contains(@id,'appHome_menu')]",30);
		if (homepageverification) {
			test.log(LogStatus.PASS, "Navigated to the DashBoard SuccessFully With the User ID of:<font color='blue'>"+username+"</font>!!!");
			return true;
		}
		else {
			test.log(LogStatus.FAIL, "DashBoard Navigation Failed with the User ID of: <font color='red'>"+username+"</font>!!!");
			TakescreenShot(currentdriver,test);
			Assert.fail("FAILED");
			return false;
		}
		}
		else {
			test.log(LogStatus.FAIL, "DashBoard Navigation Failed with the User ID of: <font color='red'>"+username+"</font>!!!");
			TakescreenShot(currentdriver,test);
			Assert.fail("FAILED");
			return false;
		}
	}
}