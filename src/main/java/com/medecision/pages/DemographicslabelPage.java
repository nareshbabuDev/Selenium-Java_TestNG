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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.medecision.pages.TestDataVerification;
import com.medecision.pages.ModifyPatientPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DemographicslabelPage extends SeleniumBase {
	protected RemoteWebDriver driver;

	public DemographicslabelPage(RemoteWebDriver driver, ExtentTest test) {

		super(driver, test);
		this.driver = driver;
		this.test = test;
	}

	public static List<String> demolabels;
	public static List<String> modlabels;
	
	public DemographicslabelPage clickDemographics() {
		try {
			verifyelementpresentExplicitwait("//div[contains(@id,'appEnrollment_menu')]");
			click("//div[contains(@id,'appEnrollment_menu')]");
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			boolean verify = verifyelementpresentExplicitwait("//input[contains(@name,'PatientId')]", 5);
			if (verify) {
				test.log(LogStatus.PASS, "Navigated to the Demographics app successfully");
			} else {
				test.log(LogStatus.FAIL, "Demographics app Navigation failed");
				TakescreenShot(driver,test);
				Assert.fail();
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click Demographics app");
			TakescreenShot(driver,test);
			click("//div[contains(@id,'appHome_menu')]");
			Assert.fail();
		}

		return this;
	}

	public DemographicslabelPage ModifyPage() {
		try {
			click("//div[contains(text(),'Modify')]");
			boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Modify')]");
			if (verify) {
				test.log(LogStatus.INFO, "Navigated to the Patient Details Modification Page");
			} else {
				test.log(LogStatus.FAIL, "Patient Details Modification Page Navigation failed");
				TakescreenShot(driver,test);Assert.fail();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Patient Details Modification Page Navigation failed");
			TakescreenShot(driver,test);
			click("//div[contains(@id,'appHome_menu')]");
			Assert.fail();
		}
		return this;
	}

	public DemographicslabelPage CollectLabels(String PatientUserName,String County,String IdentifiedGender,String PreferredName) {
		try {
			test.log(LogStatus.INFO, "Collecting labels in Demographics");
			//Thread.sleep(3000);
			demolabels = new ArrayList<String>();
			//This is for Production
			if(County.equalsIgnoreCase("Y")) {
				demolabels.add(getElementText("//label[contains (text(),('County'))]"));
			}
			if(PatientUserName.equalsIgnoreCase("Y")) {
		        demolabels.add(getElementText("//label[contains (text(),'Patient Username') or contains(text(),'Member Username')]"));
			}
			if(IdentifiedGender.equalsIgnoreCase("Y")) {
		        demolabels.add(getElementText("//label[contains (text(),'Patient Username') or contains(text(),'Member Username')]"));
			}
			if(PreferredName.equalsIgnoreCase("Y")) {
				demolabels.add(getElementText("//label[contains (text(),'Preferred Name')]"));
			}
			
			//PatientDemographics
			demolabels.add(getElementText("//label[contains (text(),'Patient ID')]"));
			demolabels.add(getElementText("(//label[contains (text(),'First Name')])[1]"));
			demolabels.add(getElementText("//label[contains (text(),'SSN')]"));
			demolabels.add(getElementText("//label[contains (text(),'Ethnicity')]"));
			demolabels.add(getElementText("//label[contains (text(),'Date of Birth')]"));
			demolabels.add(getElementText("//label[contains (text(),'Race')]"));
			demolabels.add(getElementText("(//label[contains (text(),'Middle Name')])[1]"));
			demolabels.add(getElementText("//label[contains (text(),'Gender')]"));
			demolabels.add(getElementText("//label[contains (text(),'Preferred Language')]"));
			demolabels.add(getElementText("(//label[contains (text(),'Last Name')])[1]"));
			demolabels.add(getElementText("//label[contains (text(),'Patient MRN') or contains (text(),'Member MRN')]"));
			demolabels.add(getElementText("//label[contains (text(),'Acuity Score')]"));
			//contactInformation
			demolabels.add(getElementText("//label[contains (text(),'Address 1')]"));
			demolabels.add(getElementText("//label[contains (text(),'Address 2')]"));
			demolabels.add(getElementText("(//label[contains (text(),'Telephone')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'Apartment/Suite')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'Apartment/Suite')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Telephone')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'City')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'City')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Email Address')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'State')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'State')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Zip Code')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'Zip Code')])[2]"));
			//EmergencyContact
			demolabels.add(getElementText("(//label[contains (text(),'First Name')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Middle Name')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Last Name')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Telephone')])[3]"));
			demolabels.add(getElementText("(//label[contains (text(),'Email Address')])[2]"));
			demolabels.add(getElementText("//label[contains (text(),'Relationship')]"));
			//Insurance
			demolabels.add(getElementText("//label[contains (text(),'Primary')]"));
			demolabels.add(getElementText("(//label[contains (text(),'Payer Class')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'Medicaid/Medicare/Payer ID')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'Payer Plan')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'Effective Date')])[1]"));
			demolabels.add(getElementText("(//label[contains (text(),'Expiry Date')])[1]"));
			demolabels.add(getElementText("//label[contains (text(),'Secondary')]"));
			demolabels.add(getElementText("(//label[contains (text(),'Payer Class')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Medicaid/Medicare/Payer ID')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Payer Plan')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Effective Date')])[2]"));
			demolabels.add(getElementText("(//label[contains (text(),'Expiry Date')])[2]"));
			
			test.log(LogStatus.PASS, "The Collected labels are:<font color='blue'>"+demolabels+"</font>");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to collect labels in demographics");
			TakescreenShot(driver,test);
			click("//div[contains(@id,'appHome_menu')]");
			Assert.fail();
		}
		return this;
	}
	
	public DemographicslabelPage CollectModifyLabels(String PatientUserName,String County,String IdentifiedGender,String PreferredName) {
		try {
			test.log(LogStatus.INFO, "Collecting labels in ModifyPage");
			modlabels = new ArrayList<String>();
			//this is for production
			if(County.equalsIgnoreCase("Y")) {
				modlabels.add(getElementText("//label[contains (text(),('County'))]"));
			}
			if(PatientUserName.equalsIgnoreCase("Y")) {
		        modlabels.add(getElementText("//label[contains (text(),'Patient Username') or contains(text(),'Member Username')]"));
			}
			if(IdentifiedGender.equalsIgnoreCase("Y")) {
		        modlabels.add(getElementText("//label[contains (text(),'Patient Username') or contains(text(),'Member Username')]"));
			}
			if(PreferredName.equalsIgnoreCase("Y")) {
				modlabels.add(getElementText("//label[contains (text(),'Preferred Name')]"));
			}
			//PatientDemographics
			modlabels.add(getElementText("//label[contains (text(),'Patient ID')]"));
			modlabels.add(getElementText("(//label[contains (text(),'First Name')])[1]").replaceAll("\\*",""));
			modlabels.add(getElementText("//label[contains (text(),'SSN')]"));
			modlabels.add(getElementText("//label[contains (text(),'Ethnicity')]"));
			modlabels.add(getElementText("//label[contains (text(),'Date of Birth')]").replaceAll("\\*",""));
			modlabels.add(getElementText("//label[contains (text(),'Race')]"));
			modlabels.add(getElementText("(//label[contains (text(),'Middle Name')])[1]"));
			modlabels.add(getElementText("//label[contains (text(),'Gender')]").replaceAll("\\*",""));
			modlabels.add(getElementText("//label[contains (text(),'Preferred Language')]"));
			modlabels.add(getElementText("(//label[contains (text(),'Last Name')])[1]").replaceAll("\\*",""));
			modlabels.add(getElementText("//label[contains (text(),'Patient MRN') or contains (text(),'Member MRN')]"));
			modlabels.add(getElementText("//label[contains (text(),'Acuity Score')]"));
			//contactInformation
			modlabels.add(getElementText("//label[contains (text(),'Address 1')]"));
			modlabels.add(getElementText("//label[contains (text(),'Address 2')]"));
			modlabels.add(getElementText("(//label[contains (text(),'Telephone')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'Apartment/Suite')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'Apartment/Suite')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Telephone')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'City')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'City')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Email Address')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'State')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'State')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Zip Code')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'Zip Code')])[2]"));
			//EmergencyContact
			modlabels.add(getElementText("(//label[contains (text(),'First Name')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Middle Name')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Last Name')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Telephone')])[3]"));
			modlabels.add(getElementText("(//label[contains (text(),'Email Address')])[2]"));
			modlabels.add(getElementText("//label[contains (text(),'Relationship')]"));
			//Insurance
			modlabels.add(getElementText("//label[contains (text(),'Primary')]"));
			modlabels.add(getElementText("(//label[contains (text(),'Payer Class')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'Medicaid/Medicare/Payer ID')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'Payer Plan')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'Effective Date')])[1]"));
			modlabels.add(getElementText("(//label[contains (text(),'Expiry Date')])[1]"));
			modlabels.add(getElementText("//label[contains (text(),'Secondary')]"));
			modlabels.add(getElementText("(//label[contains (text(),'Payer Class')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Medicaid/Medicare/Payer ID')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Payer Plan')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Effective Date')])[2]"));
			modlabels.add(getElementText("(//label[contains (text(),'Expiry Date')])[2]"));
			test.log(LogStatus.PASS, "The Collected labels in ModifyPage:<font color='blue'>"+modlabels+"</font>");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to collect labels in Modify Page");
			TakescreenShot(driver,test);
			click("//div[contains(@id,'appHome_menu')]");
			Assert.fail();
		}
		return this;
	}
	
	public DemographicslabelPage CompareLabels() {
		try {
			test.log(LogStatus.INFO, "Comparing the Labels");
			boolean a=demolabels.containsAll(modlabels);
		if(a==true) {
			test.log(LogStatus.PASS, "The Labels Present in demographics and modify page are Equal!");
		}
		else {
			test.log(LogStatus.FAIL, "The Labels Present in demographics and modify page are Different!");
		}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Failed to Compare Labels");
			TakescreenShot(driver,test);
			click("//div[contains(@id,'appHome_menu')]");
			Assert.fail();
		}
		return this;
	}

}
