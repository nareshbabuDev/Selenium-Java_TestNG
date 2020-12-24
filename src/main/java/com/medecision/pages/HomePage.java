package com.medecision.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends SeleniumBase   {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public HomePage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}
	
	public static void wait(int millisecond) {
		try {
			Thread.sleep(millisecond*(1000));
		}catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}	
	SoftAssert soft = new SoftAssert();
	
	public SearchPatientPage clickDemographics() throws InterruptedException {
		Thread.sleep(3000);
		click("(//div[text()='Demographics'])[2]");
		return new SearchPatientPage(driver, test);
	}
	public void  clickgotit() {
		try {
	    Thread.sleep(2000);
		click("//div[contains(text(),'Got')]");
		test.log(LogStatus.INFO, "Got It Button Clicked");
		}catch(Exception e) {
			test.log(LogStatus.INFO, "Got It Section Not Appeared");
			
		}
	}
	
	public HomePage EnterFirstName(String firstname) {
		click("//input[@placeholder='First name']");
		clearAndType("//input[@placeholder='First name']", firstname);
		return this;
	}
	public HomePage EnterLastName(String lastname) {
		click("//input[@placeholder='Last name*']");
		clearAndType("//input[@placeholder='Last name*']", lastname);
		return this;
	}
	public CarebookSearchPage clickCarebookbutton() {
		click("//input[@placeholder='Last name*']/following-sibling::md-icon[1]");
		return new CarebookSearchPage(driver, test) ;
	}
	public HomePage clickHome() {
		click("(//div[@id='appHome_menu']//div)[3]");
		return new HomePage(driver, test);
	}
	public  HomePage clickLogout() throws InterruptedException {
		Thread.sleep(3000);
		click("//*[@id='btnLogout']/md-icon");
		return this;
	}
	public LoginPage clickYes() throws InterruptedException {
		Thread.sleep(3000);
		click("//div[text()='Yes']");
		return new LoginPage(driver,test);
	}
		
}
