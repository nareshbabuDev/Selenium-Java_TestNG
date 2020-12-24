package com.medecision.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ViewPatientPage extends SeleniumBase   {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	
	public ViewPatientPage(RemoteWebDriver driver,ExtentTest test) {
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
	public void clickModify() throws InterruptedException {
		//test.log(LogStatus.PASS,"Enter the PatientID");
		//driver.navigate().to(Url);
		Thread.sleep(3000);
		try {
		movetoelement("//div[contains(text(),'Modify')]");
		click("//div[contains(text(),'Modify')]");
		Thread.sleep(3000);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
		
	}
	
	public ViewPatientPage clickOk() throws InterruptedException {
		//test.log(LogStatus.PASS,"Enter the PatientID");
		//driver.navigate().to(Url);
		Thread.sleep(3000);
		//click("//div[text()='OK']");
		doubleclickelement("(//div[@class='imgButton']//img)[3]");
		//test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
		return this;
	}
	
	public ViewPatientPage verifyAddress1() {
		try {
		verifyExactText("//div[text()='add mod']","add mod");
		test.log(LogStatus.PASS,"Address1 is updated successfully in dashboard");
		}
		catch(Exception e) {
			System.out.println(e);
			test.log(LogStatus.FAIL,"Address1 is not updated successfully dashboard");
			TakescreenShot(currentdriver,test);
		}
		
		return this;
	}
	
	public void clickEnrollment() throws InterruptedException {
		//test.log(LogStatus.PASS,"Enter the PatientID");
		//driver.navigate().to(Url);
		Thread.sleep(3000);
		try {
		movetoelement("//*[normalize-space(text())='Enrollment']");
		click("//*[normalize-space(text())='Enrollment']");
		Thread.sleep(3000);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		//test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
		
	}

	
	public ViewPatientPage clickLogout() {
		click("//*[@id='btnLogout']/md-icon");
		return this;
	}
	public ViewPatientPage clickYes() throws InterruptedException {
		Thread.sleep(3000);
		click("(//div[@role='button']//td)[5]");
		return this;
	}
	}
