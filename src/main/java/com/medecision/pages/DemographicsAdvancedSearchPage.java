package com.medecision.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.medecision.pages.DemographicsCardPage;
//import org.medecision.pages.PatientOtherIdPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.medecision.pages.TestDataVerification;
import com.medecision.pages.*;
import com.medecision.pages.ModifyPatientPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DemographicsAdvancedSearchPage extends SeleniumBase {
	protected RemoteWebDriver driver;

	public DemographicsAdvancedSearchPage(RemoteWebDriver driver, ExtentTest test) {

		super(driver, test);
		this.driver = driver;
		this.test = test;
	}

	public List<String> demosearchvalues;
	SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
	
	
	
	public DemographicsAdvancedSearchPage GetPatientDetails() {
		demosearchvalues = new ArrayList<String>();
		try {
		demosearchvalues.add(getElementText("(//table[contains(@class,'listTable')]//div)[2]").trim());
		demosearchvalues.add(getElementText("(//table[contains(@class,'listTable')]//div)[3]").trim());
		demosearchvalues.add(getElementText("(//table[contains(@class,'listTable')]//div)[4]").trim());
		demosearchvalues.add(getElementText("(//table[contains(@class,'listTable')]//div)[5]").trim());
		actionsclick("//*[contains(text(),'Reset')]//parent::button");
		
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to collect Patient Details");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}
	
	
	public DemographicsAdvancedSearchPage SearchwithLastName(String PatientID) {
		try {
			clearelementusingkeys("//input[contains(@name,'lastName')]");
			clearAndType("//input[contains(@name,'lastName')]",demosearchvalues.get(0));
			actionsclick("(//div[contains(text(),'Search')])[1]");
			Thread.sleep(1000);
			boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Search did not return')]",2);
			if(!verify) {
				test.log(LogStatus.PASS,"Patient Entered inthe search field with lastname:<font color='blue'><b>"+demosearchvalues.get(0)+"</b></font> is Present in the table ");
			}
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Search Patient Lastname");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}
	
	
	public DemographicsAdvancedSearchPage SearchwithFirstName(String PatientID) {
		try {
			clearelementusingkeys("//input[contains(@name,'firstName')]");
			clearAndType("//input[contains(@name,'firstName')]",demosearchvalues.get(1));
			actionsclick("(//div[contains(text(),'Search')])[1]");
			Thread.sleep(1000);
			boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Search did not return')]",2);
			if(!verify) {
				test.log(LogStatus.PASS,"Patient Entered inthe search field with firstname:<font color='blue'><b>"+demosearchvalues.get(1)+"</b></font> is Present in the table ");
			}
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Search Patient with Firstname");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}
	
	
	public DemographicsAdvancedSearchPage SearchwithMiddleName(String PatientID) {
		try {
			if(demosearchvalues.get(2).length()<=0) {
			test.log(LogStatus.INFO, "Middle Name is Empty for this Patient");	
			}else {
			clearelementusingkeys("//input[contains(@name,'middleName')]");
			clearAndType("//input[contains(@name,'middleName')]",demosearchvalues.get(2));
			actionsclick("(//div[contains(text(),'Search')])[1]");
			Thread.sleep(1000);
			boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Search did not return')]",2);
			if(!verify) {
				test.log(LogStatus.PASS,"Patient Entered inthe search field with middlename:<font color='blue'><b>"+demosearchvalues.get(2)+"</b></font> is Present in the table ");
			}
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			}
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Search Patient with Middlename");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}
	
	public DemographicsAdvancedSearchPage SearchwithDOB(String PatientID) {
		try {
			clearelementusingkeys("//input[contains(@name,'dateOfBirth')]");
			clearAndType("//input[contains(@name,'dateOfBirth')]",demosearchvalues.get(3));
			actionsclick("(//div[contains(text(),'Search')])[1]");
			Thread.sleep(1000);
			boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Search did not return')]",2);
			if(!verify) {
				test.log(LogStatus.PASS,"Patient Entered inthe search field with DOB:<font color='blue'><b>"+demosearchvalues.get(3)+"</b></font> is Present in the table ");
			}
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Search Patient with DateOFBirth");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}
	
	
	public DemographicsAdvancedSearchPage SearchwithPatientDetails(String PatientID) {
		try {
			clearelementusingkeys("//input[contains(@name,'lastName')]");
			clearAndType("//input[contains(@name,'lastName')]",demosearchvalues.get(0));
			clearelementusingkeys("//input[contains(@name,'firstName')]");
			clearAndType("//input[contains(@name,'firstName')]",demosearchvalues.get(1));
			clearelementusingkeys("//input[contains(@name,'middleName')]");
			clearAndType("//input[contains(@name,'middleName')]",demosearchvalues.get(2));
			clearelementusingkeys("//input[contains(@name,'dateOfBirth')]");
			clearAndType("//input[contains(@name,'dateOfBirth')]",demosearchvalues.get(3));
			actionsclick("(//div[contains(text(),'Search')])[1]");
			Thread.sleep(1000);
			boolean verify = verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']",30);
			if(verify) {
				test.log(LogStatus.PASS,"Patient Entered inthe search field with Patient Details:<font color='blue'><b>"+demosearchvalues.get(0)+demosearchvalues.get(1)+demosearchvalues.get(2)+demosearchvalues.get(3)+"</b></font> is Present in the table ");
			}
			actionsclick("//*[contains(text(),'Reset')]//parent::button");

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Search Patient with PatientDetails");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}
}
