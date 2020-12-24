package com.medecision.pages;


import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CarebookSearchPage extends SeleniumBase   {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public CarebookSearchPage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}
	
	
	//carebook search
	public CarebookSearchPage PatientID_Verification(String PatientID) throws InterruptedException {
		TestDataVerification TestDataVerification= new TestDataVerification(currentdriver,test);
		boolean flag=true;
		SearchPatientPage SearchPatientPage = new SearchPatientPage(currentdriver,test);
		try {
		TestDataVerification.enterPatientID(PatientID);
		SearchPatientPage.clickSearchPatient();
		boolean verify = verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']",15);
		if(verify) {
		String id = getElementText("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
		if(id.trim().equals(PatientID)) {
			test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
			
		}else {
			test.log(LogStatus.FAIL, "Wrong Patient ID Entered");
			TakescreenShot(currentdriver,test);
			flag=false;
		}
		
		}else {
			test.log(LogStatus.FAIL, "Searched Patient ID Doesn't Match with any Records");
			TakescreenShot(currentdriver,test);
			flag=false;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Search Page navigation Failed");
			flag=false;
			TakescreenShot(currentdriver,test);
		}
		finally {
			if(!flag) {
				TestDataVerification.NavigatetoHome();
				Assert.fail();
			}
		}
		return this;
	}
	public ArrayList<String> getFNLN() {
		ArrayList <String> FNLN=new ArrayList<String>();
		try {
		FNLN.add(getElementText("(//table[contains(@class,'listTable')]//div)[2]").trim());
		FNLN.add(getElementText("(//table[contains(@class,'listTable')]//div)[3]").trim());
		verifyelementpresentExplicitwait("//*[contains(text(),'Reset')]//parent::button",60);
		click("//*[contains(text(),'Reset')]//parent::button");
		return FNLN;
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Get Patient First and Last Name");
			verifyelementpresentExplicitwait("//*[contains(text(),'Reset')]//parent::button",60);
			click("//*[contains(text(),'Reset')]//parent::button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			return null;
		}
		
	}
	
	public CarebookSearchPage CBsearch(String PatientID) {
		ArrayList <String> FNLN = getFNLN();
		try {
		clearelementusingkeys("//input[contains(@placeholder,'First name')]");
		clearelementusingkeys("//input[contains(@placeholder,'Last name')]");
		clearAndType("//input[contains(@placeholder,'First name')]", FNLN.get(1));
		clearAndType("//input[contains(@placeholder,'Last name')]", FNLN.get(0));
		click("//md-icon[contains(@md-svg-icon,'magnify') and contains(@ng-show,'carebookSearch')]");
		try {
			boolean verify =verifyelementpresentExplicitwait("//*[contains(text(),'Carebook')]//following::div[text()[normalize-space()='"+PatientID+"']]", 120);
			if(verify) {
				click("//*[contains(text(),'Carebook')]//following::div[text()[normalize-space()='"+PatientID+"']]");
				actionsdoubleclick("//*[contains(text(),'Carebook')]//following::div[text()[normalize-space()='"+PatientID+"']]");	
				Thread.sleep(10000);
			}else {
				test.log(LogStatus.FAIL, "Unable to click the Search Result");
				boolean verify1=verifyelementpresentExplicitwait("//span[contains(text(),'No matching records')]",1);
				if(verify1) {
					click("//button/md-icon[@md-svg-icon='close']");
					test.log(LogStatus.INFO, "carebook Search Result Page Closed");
					 verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
					 movetoelement("//div[contains(@id,'appHome_menu')]");
					 actionsclick("//div[contains(@id,'appHome_menu')]");
					 TakescreenShot(currentdriver,test);
					 Assert.fail();
				}
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Search Results Retrive Nothing");
			boolean verify1=verifyelementpresentExplicitwait("//span[contains(text(),'No matching records')]", 2);
			if(verify1) {
				click("//button/md-icon[@md-svg-icon='close']");
				test.log(LogStatus.INFO, "carebook Search Result Page Closed");
			}
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Carebook Navigation Failed");
			boolean verify1=verifyelementpresentExplicitwait("//span[contains(text(),'No matching records')]", 2);
			if(verify1) {
				click("//button/md-icon[@md-svg-icon='close']");
				test.log(LogStatus.INFO, "carebook Search Result Page Closed");
			}
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public CarebookSearchPage CBAlertsVerify(String PatientID) {
		try {
		boolean verify=verifyelementpresentExplicitwait("//iframe[contains(@id,'carebook-iframe')]",120);
		if(verify) {
		try {
		switchToFrame("//iframe[contains(@id,'carebook-iframe')]");	
		Thread.sleep(10000);
		System.out.println("Hai");
		boolean verify1 = verifyelementpresentExplicitwait("//h3[text()[normalize-space()='ALERTS']]", 120);
		if(verify1) {
			verifyPatient(PatientID);
			System.out.println("Hai 1");
			movetoelement("//h3[text()[normalize-space()='ALERTS']]");
			click("//h3[text()[normalize-space()='ALERTS']]");
			boolean verify2 = verifyelementpresentExplicitwait("(//tbody[contains(@id,'Alerts')]//td)[2]", 60);
			if(verify2) {
				String todaysalert = getElementText("(//tbody[contains(@id,'Alerts')]//td)[2]");
				test.log(LogStatus.PASS, "Alerts Shown in the Carebook Alerts Section, Last Alert Received Date is:<font color='blue'>"+todaysalert.trim()+"</font>");
			}
			else if(!verify2){
				if(verifyelementpresentExplicitwait("//td[contains(text(),'No Alerts')]", 2)) {
					test.log(LogStatus.PASS, "No Alerts Shown in the Carebook Page");	
				}else {
				click("//h3[text()[normalize-space()='ALERTS']]");
				verifyelementpresentExplicitwait("(//tbody[contains(@id,'Alerts')]//td)[2]", 120);
				String todaysalert = getElementText("(//tbody[contains(@id,'Alerts')]//td)[2]");
				test.log(LogStatus.PASS, "Alerts Shown in the Carebook Alerts Section, Last Alert Received Date is:<font color='blue'>"+todaysalert.trim()+"</font>");
				}
				}
			else {
				test.log(LogStatus.FAIL, "Alerts Not Shown in the Carebook Page");
				TakescreenShot(currentdriver,test);
				driver.switchTo().defaultContent();
				Assert.fail();
			}
		}
		else {
			test.log(LogStatus.FAIL, "Unable to navigate Carebook Alerts page");
			TakescreenShot(currentdriver,test);
			driver.switchTo().defaultContent();
			Assert.fail();
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to navigate Carebook Iframe page");
			TakescreenShot(currentdriver,test);
			driver.switchTo().defaultContent();
			Assert.fail();
		}
		}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "CareBook Details Page is not Loading or Down");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public CarebookSearchPage SwitchtoDefault() {
		boolean flag=false;
		try {
			driver.switchTo().defaultContent();			
	         verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
			 movetoelement("//div[contains(@id,'appHome_menu')]");
			 actionsclick("//div[contains(@id,'appHome_menu')]");
			flag=true;
		}catch(Exception e) {
			TakescreenShot(currentdriver,test);
			flag=false;
		}finally {
			if(!flag) {
				TakescreenShot(currentdriver,test);
				driver.switchTo().defaultContent();
		         verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
				 movetoelement("//div[contains(@id,'appHome_menu')]");
				 actionsclick("//div[contains(@id,'appHome_menu')]");
			}
		}
		return this;
	}

	
	public CarebookSearchPage CarebookDemographicsnavigation() {
		try {
			verifyelementpresentExplicitwait("//input[contains(@value,'Update')]//parent::legend[contains(text(),'Information')]", 120);
			click("//input[contains(@value,'Update')]//parent::legend[contains(text(),'Information')]");
			test.log(LogStatus.INFO, "Navigated to Carebook Demographics Section");

		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Carebook Demographics Section Navigation Failed");
			TakescreenShot(currentdriver,test);
			driver.switchTo().defaultContent();
			Assert.fail();
		}
		
		return this;
	}
	public CarebookSearchPage verifyPatient(String PatientID) {
		try {
			boolean verify1 = verifyelementpresentExplicitwait("//div[contains(@id,'patientDetails')]/label[contains(@id,'DetailsLabel')]", 120);
			String patientid = driver.findElementByXPath("//div[contains(@id,'patientDetails')]/label[contains(text(),'"+PatientID+"')]").getText();
			if(patientid.contains(PatientID)) {
				test.log(LogStatus.PASS,"Patient Details displayed in Carebook app is:<font color='blue'>"+patientid+"</font>");
			}
			else {
				test.log(LogStatus.FAIL,"Patient details not displayed  in Carebook app");
				TakescreenShot(currentdriver,test);
				SwitchtoDefault();
				Assert.fail();
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Patient details verification failed in Carebook app");
			TakescreenShot(currentdriver,test);
			SwitchtoDefault();
			Assert.fail();
		}
		
		return this;
	}	
	public CarebookSearchPage searchByID(String PatientID) {
		try {
			boolean verify1 = verifyelementpresentExplicitwait("//input[contains(@placeholder,'Payer ID or Patient ID') or contains(@placeholder,'Payer ID or Member ID') ]", 120);
			if(verify1) {
				clearelementusingkeys("//input[contains(@placeholder,'Payer ID or Patient ID') or contains(@placeholder,'Payer ID or Member ID') ]");
				clearAndType("//input[contains(@placeholder,'Payer ID or Patient ID') or contains(@placeholder,'Payer ID or Member ID') ]",PatientID);
				Thread.sleep(2000);
				enterkey("//input[contains(@placeholder,'Payer ID or Patient ID') or contains(@placeholder,'Payer ID or Member ID') ]");
				Thread.sleep(10000);
				test.log(LogStatus.INFO, "PatientID is successfully entered into 'Payer ID or Patient ID*' field and searched ");
			}
			else {
				test.log(LogStatus.FAIL, "Unable to enter PatientID in 'Payer ID or Patient ID*' field");
				TakescreenShot(currentdriver,test);
			}
			boolean verify2 = verifyelementpresentExplicitwait("//*[contains(text(),'Carebook')]//following::div[text()[normalize-space()='"+PatientID+"']]", 60);
		    if(verify2) {
		    	actionsdoubleclick("//*[contains(text(),'Carebook')]//following::div[text()[normalize-space()='"+PatientID+"']]");
		    	test.log(LogStatus.INFO, "Patient is successfully selected from the result");
			}
		    else {
		    	test.log(LogStatus.FAIL, "Unable to select the Patient from the result");
		    	TakescreenShot(currentdriver,test);
		    }
			try {
				boolean verify=verifyelementpresentExplicitwait("//iframe[contains(@id,'carebook-iframe')]",120);
				if(verify) {
					switchToFrame("//iframe[contains(@id,'carebook-iframe')]");	
					verifyPatient(PatientID);
					
			}
			}
				catch(Exception e) {
					test.log(LogStatus.FAIL, "Patient is not successfully selected from the result");
					TakescreenShot(currentdriver,test);
					SwitchtoDefault();
					Assert.fail();
				}
			}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to click PatientID input field");
			TakescreenShot(currentdriver,test);
			SwitchtoDefault();
			Assert.fail();}
		return this;
	}
	public CarebookSearchPage AddPatientFromCarebook() {
		try {
			try {
				boolean verify =verifyelementpresentExplicitwait("//a[contains(text(),'Add')]", 30);
				if(verify) {
					test.log(LogStatus.INFO, "Add to Patient Panel button Shown in Carebook ");
					click("//a[contains(text(),'Add')]");
					Thread.sleep(5000);
					boolean verify1 =verifyelementpresentExplicitwait("//span[contains(text(),'added')]", 10);
					if(verify1) {
						test.log(LogStatus.PASS, "Patient Added to the patient panel from Carebook");
					}else {
						test.log(LogStatus.FAIL, "Unable to Add the Patient to Patient panel from Carebook");
						TakescreenShot(currentdriver,test);
						SwitchtoDefault();
						Assert.fail();
					}
				}else {
					test.log(LogStatus.FAIL, "Patient Already Present in the Patient Panel");
					TakescreenShot(currentdriver,test);
					SwitchtoDefault();
					Assert.fail();
				}
			}catch(Exception e){
			test.log(LogStatus.FAIL, "Unable to click Add to Patient Panel button in Carebook");
			TakescreenShot(currentdriver,test);
			SwitchtoDefault();
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to click Add to Patient Panel button in Carebook");
			TakescreenShot(currentdriver,test);
			SwitchtoDefault();
			Assert.fail();
		}
		return this;
	}
	public CarebookSearchPage ResetButtonClick() {
		boolean verify = verifyelementClickableExplicitwait("//*[contains(text(),'Reset')]//parent::button", 20);
		if(verify)
			click("//*[contains(text(),'Reset')]//parent::button");
		else {
			test.log(LogStatus.FAIL, "Reset button not clicked");
		}
		return this;
	}
	public CarebookSearchPage CareTeamLeadCB() {
		try {
			boolean verify = verifyelementpresentExplicitwait("//legend[contains(text(),'Care Team')]", 60);
			if(verify) {
				click("//legend[contains(text(),'Care Team')]");
				verifyelementpresentExplicitwait("//span[contains(text(),'Care Team lead')]", 60);
				String Leadname=getElementText("//span[contains(text(),'Care Team lead')]").trim();
				test.log(LogStatus.PASS, "Care Team Lead Reflected in the CareBook and the Lead Name is:"+Leadname);
				SwitchtoDefault();
			}else {
				test.log(LogStatus.FAIL, "Care Team Lead Not Reflected in the CareBook");
				TakescreenShot(currentdriver,test);
				SwitchtoDefault();
				Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to click CareTeam button in Carebook");
			TakescreenShot(currentdriver,test);
			SwitchtoDefault();
			Assert.fail();
		}
		return this;	
	}
	public CarebookSearchPage CareBookDetailsVerification(String PatientID) {
		try {
			PatientID_Verification(PatientID)
			.CBsearch(PatientID)
			.CBAlertsVerify(PatientID)
			.SwitchtoDefault();
		}catch(Exception e) {
			SwitchtoDefault();
		}
		return this;
	}

}
