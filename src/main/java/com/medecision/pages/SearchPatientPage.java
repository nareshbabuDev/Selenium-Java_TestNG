package com.medecision.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchPatientPage extends SeleniumBase   {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public SearchPatientPage(RemoteWebDriver driver,ExtentTest test) {
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
	public SearchPatientPage enterPatientID(String PatientID) throws InterruptedException {
		try {
		clearelementusingkeys("//input[contains(@name,'PatientId')]");
		Thread.sleep(2000);
		javascriptexecutor("//input[contains(@name,'PatientId')]", PatientID);
		test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
		Thread.sleep(2000);
		}
		catch(Exception e) {
		test.log(LogStatus.FAIL,"Unable to enter the Patient ID");
		TakescreenShot(currentdriver,test);
		Assert.fail();	
		}
		return this;
	}
	public SearchPatientPage clickSearchPatient() throws InterruptedException {
		try {
			boolean verify = verifyelementClickableExplicitwait("(//div[contains(text(),'Search')])[1]", 5);
			if(verify) {
			movetoelement("(//div[contains(text(),'Search')])[1]");
			click("(//div[contains(text(),'Search')])[1]");
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable Send Enter Key to PatientID Field");
			TakescreenShot(currentdriver,test);
			Assert.fail();		
		}
		return this;
	}
	
	public ViewPatientPage selectPatient(String PatientID) {
		wait(5);
		doubleclickelement("//div[text()="+PatientID+"]");
		return new ViewPatientPage(driver, test);
	}
	
	public ViewPatientPage clickSelectPatient() throws InterruptedException {
		Thread.sleep(2000);		
		click("//div[contains(text(),'Select')]");
		return new ViewPatientPage(driver, test);
	}
	
	



		
}
