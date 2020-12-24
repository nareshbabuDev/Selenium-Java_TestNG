package com.medecision.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserPasswordUpdatePage extends SeleniumBase   {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public UserPasswordUpdatePage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}
	public UserPasswordUpdatePage clickUserProfile() {		
		try {
		boolean verify1 = verifyelementpresentExplicitwait("//span[@class='username']");
		if(verify1) {			
			actionsclick("//span[@class='username']");
			boolean verify = verifyelementpresentExplicitwait("//span[contains(text(),'Preferences')]",5);
			if(verify) {
				test.log(LogStatus.INFO, "UserProfile Button cliked successfully");
			}else {
				test.log(LogStatus.FAIL, "Unable to click UserProfile Button");
				TakescreenShot(currentdriver,test);
			}
		}else {
				test.log(LogStatus.FAIL, "Navigation to UserProfile is failed");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}catch(Exception e) {TakescreenShot(currentdriver,test);Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage clickPreference() {		
		try {
		boolean verify1 = verifyelementpresentExplicitwait("//span[contains(text(),'Preferences')]", 5);
		if(verify1) {
			movetoelement("//span[contains(text(),'Preferences')]");
			Thread.sleep(2000);
			actionsclick("//span[contains(text(),'Preferences')]");
			Thread.sleep(2000);
			boolean verify2 = verifyelementpresentExplicitwait("(//td[contains(text(),'Preferences')]//following::input[@type='PASSWORD'])[1]", 5);
			if(verify2) {
			test.log(LogStatus.PASS, "Navigated to Preferences Page");	
			}
			else {
				test.log(LogStatus.FAIL, "Preferences Page Navigation Failed");	
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}else {
				test.log(LogStatus.FAIL, "Click on option 'Preference' is failed");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}catch(Exception e) {TakescreenShot(currentdriver,test);Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage clickPassword(String newPass) {		
		try {
		boolean verify1 = verifyelementpresentExplicitwait("(//td[contains(text(),'Preferences')]//following::input[@type='PASSWORD'])[1]", 5);
		if(verify1) {
			clearelementusingkeys("(//td[contains(text(),'Preferences')]//following::input[@type='PASSWORD'])[1]");
			clearAndType("(//td[contains(text(),'Preferences')]//following::input[@type='PASSWORD'])[1]", newPass);
			test.log(LogStatus.PASS, "Password entered in Password's Text box successfully");		
		}else {
				test.log(LogStatus.FAIL, "Password not entered in Password's Text box");
				TakescreenShot(currentdriver,test);
				clickClose();
				Assert.fail();
				
			}
		}catch(Exception e) {TakescreenShot(currentdriver,test);clickClose();
		Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage clickConfirmPassword(String newPass) {		
		try {
		boolean verify1 = verifyelementpresentExplicitwait("(//td[contains(text(),'Preferences')]//following::input[@type='PASSWORD'])[2]", 5);
		if(verify1) {
			clearelementusingkeys("(//td[contains(text(),'Preferences')]//following::input[@type='PASSWORD'])[2]");
			clearAndType("(//td[contains(text(),'Preferences')]//following::input[@type='PASSWORD'])[2]", newPass);
			test.log(LogStatus.PASS, "Password entered in Confirm Password's Text box successfully");		
		}else {
				test.log(LogStatus.FAIL, "Password not entered in Confirm Password's Text box");
				TakescreenShot(currentdriver,test);
				clickClose();
				Assert.fail();
			}
		}catch(Exception e) {TakescreenShot(currentdriver,test);clickClose();
		Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage clickSave() {		
		try {
		boolean verify1 = verifyelementpresentExplicitwait("(//td[contains(text(),'Preferences')]//following::div[text()='Save'])[1]", 5);
		if(verify1) {
			click("(//td[contains(text(),'Preferences')]//following::div[text()='Save'])[1]");
			Thread.sleep(5000);
			boolean verify2 = verifyelementpresentExplicitwait("//td[contains(text(),'Save successful')]",10);
			if(verify2) {
			test.log(LogStatus.PASS, "Save Successful Popup Appeared");
			}
			else {
				test.log(LogStatus.PASS, "Save Successful Popup Not Appeared");	
				clickClose();
				new TestDataVerification(driver, test).NavigatetoHome();
				Assert.fail();
			}
		}else {
				test.log(LogStatus.FAIL, "Save button is not clicked ");
				TakescreenShot(currentdriver,test);
				clickClose();
				new TestDataVerification(driver, test).NavigatetoHome();
				Assert.fail();
			}
		}catch(Exception e) {Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage clickOk() {		
		try {
		boolean verify1 = verifyelementpresentExplicitwait("//div[text()='OK']//parent::td[contains(@class,'button')]", 10);
		if(verify1) {
			click("//div[text()='OK']//parent::td[contains(@class,'button')]");
			test.log(LogStatus.PASS, "Password Updated SuccessFully");
		}else {
				test.log(LogStatus.FAIL, "Ok button is not clicked");
				TakescreenShot(currentdriver,test);
				clickClose();
				new TestDataVerification(driver, test).NavigatetoHome();
				Assert.fail();
			}
		}catch(Exception e) {Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage clickClose() {		
		try {

		boolean verify1 = verifyelementpresentExplicitwait("//div[contains(@eventproxy,'PreferencesWindow_0_closeButton') or contains(@eventproxy,'PreferencesWindow_1_closeButton')]", 5);
		if(verify1) {
			click("//div[contains(@eventproxy,'PreferencesWindow_0_closeButton') or contains(@eventproxy,'PreferencesWindow_1_closeButton')]");
			Thread.sleep(3000);
			boolean ve = verifyelementpresentExplicitwait("//div[contains(@eventproxy,'PreferencesWindow_0_closeButton') or contains(@eventproxy,'PreferencesWindow_1_closeButton')]", 2);
			if(!ve) {
				test.log(LogStatus.PASS, "Preference Page Closed SuccessFully");		
			}else {
				test.log(LogStatus.FAIL, "Unable to click Preference close Button");
				TakescreenShot(currentdriver,test);
				clickClose();
				Assert.fail();
			}
		}else {
				test.log(LogStatus.FAIL, "Unable to click Preference close Button");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}catch(Exception e) {TakescreenShot(currentdriver,test);Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage clickLogout() {		
		try {

		boolean verify1 = verifyelementpresentExplicitwait("//md-icon[@aria-label='logout']//parent::button", 20);
		if(verify1) {
			movetoelement("//md-icon[@aria-label='logout']//parent::button");
			locateElementbyXpath("//md-icon[@aria-label='logout']//parent::button").click();
			test.log(LogStatus.INFO, "Logoff Button Clicked");		
		}else {
				test.log(LogStatus.FAIL, "Unable to click Logoff Button");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}catch(Exception e) {TakescreenShot(currentdriver,test);Assert.fail();}
				return this;
		}
	
	public UserPasswordUpdatePage clickYes() {		
		try {

		boolean verify1 = verifyelementpresentExplicitwait("(//td[@class='buttonRounded'])[1]", 20);
		if(verify1) {
			locateElementbyXpath("(//td[@class='buttonRounded'])[1]").click();
			test.log(LogStatus.PASS, "Logout from Dashboard Successfully");		
		}else {
				test.log(LogStatus.FAIL, "Unable to click Yes Button");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}catch(Exception e) {TakescreenShot(currentdriver,test);Assert.fail();}
				return this;
		}
	public UserPasswordUpdatePage UserPassUpdate(String TestPass) {
		try {
			clickUserProfile().clickPreference().clickPassword(TestPass)
			.clickConfirmPassword(TestPass).clickSave().clickOk().clickClose();
		}catch(Exception e) {
			Assert.fail();
		}
		return this;
	}

}
