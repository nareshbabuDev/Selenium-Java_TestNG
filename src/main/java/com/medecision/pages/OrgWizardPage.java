package com.medecision.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class OrgWizardPage extends SeleniumBase {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public OrgWizardPage(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver = driver;
		this.test =test;
	}
	
	public OrgWizardPage clickOrgWizard(){
		boolean verify = verifyelementpresentExplicitwait("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Organization')]//parent::md-tab-item", 10);
		if(verify)
			click("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Organization')]//parent::md-tab-item");
		else {
			test.log(LogStatus.FAIL, "Enrollment app is not loading ");
			Assert.fail();
			TakescreenShot(currentdriver,test);
		}
		return this;
	}
	public OrgWizardPage getPrimaryCMAorgName1(String org1,String org2) {
//		String PrimaryCMA="";
		boolean verify = verifyelementpresentExplicitwait("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]",120);
		if(verify) {
			test.log(LogStatus.INFO, "Organization wizard is loaded");
			String primaryOrg=getElementText("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]").trim().toLowerCase();
			primaryOrg = primaryOrg.replaceAll("\\s", "").toLowerCase();
			 org1 =org1.replaceAll("\\s", "");
			System.out.println(primaryOrg);
			System.out.println(org1);
			if(primaryOrg.equals(org1.toLowerCase())) {
					   click("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]");
					   boolean verify1 =verifyelementclickableExplicitwait("//div[@class='md-text ng-binding' and text()='"+org2+"']", 10);
					   if(verify1)
					   	   movetoelement("//div[@class='md-text ng-binding' and text()='"+org2+"']");
						   actionsclick("//div[@class='md-text ng-binding' and text()='"+org2+"']");
					       test.log(LogStatus.INFO, "CMA org is changed to secondary org <font color = blue>'"+org2+"'</font>");
					   try {
						    yesButton();}
						   catch(Exception e) {}
					   clearSecondaryCMA();
					   save();
					   closeIcon();
				   }
			}
		else {
			test.log(LogStatus.FAIL, "Organization wizard  is not loading ");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;	
	}
	public OrgWizardPage getPrimaryCMAorgName2(String org1,String org2) {
//		String PrimaryCMA="";
		boolean verify = verifyelementpresentExplicitwait("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]",120);
		if(verify) {
			test.log(LogStatus.INFO, "Organization wizard is loaded");
			String secondaryOrg=getElementText("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]").trim().toLowerCase();
			secondaryOrg = secondaryOrg.replaceAll("\\s", "");
			org2=org2.replaceAll("\\s", "");
			if(secondaryOrg.equals(org2.toLowerCase())) {
				   click("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]");
				   boolean verify1 =verifyelementclickableExplicitwait("//div[@class='md-text ng-binding' and text()='"+org1+"']", 10);
				   if(verify1)
				   	   movetoelement("//div[@class='md-text ng-binding' and text()='"+org1+"']");
					   actionsclick("//div[@class='md-text ng-binding' and text()='"+org1+"']");		       
				   try {
					    yesButton();}
					   catch(Exception e) {TakescreenShot(currentdriver,test);}
				   clearSecondaryCMA();
				   save();
				   closeIcon();
				   test.log(LogStatus.PASS, "CMA org is again changed to Primary org <font color = blue>'"+org1+"'</font>");
			   }
		}
		else {
			test.log(LogStatus.FAIL, "Organization wizard  is not loading ");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;	
	}
	public OrgWizardPage yesButton() {
		try {
			 boolean verify2 = verifyelementclickableExplicitwait("//span[text()='yes']", 2);
			   if(verify2)
			       click("//span[text()='yes']");
		}
		catch(Exception e) {TakescreenShot(currentdriver,test);}
		return this;
		
	}
	public OrgWizardPage clearSecondaryCMA() {
		try {
		  boolean verify = verifyelementClickableExplicitwait("//button[@class='md-chip-remove ng-scope']", 10);
		  if(verify)
			  click("//button[@class='md-chip-remove ng-scope']");
//		  else
//			  test.log(LogStatus.FAIL, "Secondary CMA is not removed successfully");
//		  	  Assert.fail();
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Secondary CMA is not removed successfully");
			TakescreenShot(currentdriver,test);
		  	Assert.fail();
		}
		return this;
	}
	public OrgWizardPage Ok() {
		boolean okButton = verifyelementpresentExplicitwait("//span[text()='OK']", 4);
		if(okButton) {
		click("//span[text()='OK']");
		}
		return this;
		}
	
	public OrgWizardPage save() {
	  boolean verify = verifyelementClickableExplicitwait("//*[@id='btnSaveOrg']//button", 5);
	  if (verify) {click("//*[@id='btnSaveOrg']//button");
	  System.out.println("Save button clicked");}
	  else {test.log(LogStatus.FAIL, "Save button is not clicked successfully");}
	  return this;
	}
	public OrgWizardPage closeIcon() {
	  boolean verify = verifyelementClickableExplicitwait("(//md-icon[@md-svg-icon='close' and @class='ng-scope'])[1]", 5);
	  if(verify) {click("(//md-icon[@md-svg-icon='close' and @class='ng-scope'])[1]");
	  System.out.println("Ok button clicked");}
	  else {test.log(LogStatus.FAIL, "Close Icon is not clicked successfully");}
	  return this;
	}
	public OrgWizardPage PatientID_Verification(String PatientID) throws InterruptedException {
		boolean flag=true;
		try {
//		enterPatientID(PatientID);
		boolean verify = verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]//following::div[text()='"+PatientID+"']",60);
		if(verify) {
			String id = getElementText("//table[contains(@class,'listTable')]//following::div[text()='"+PatientID+"']");
			if(id.trim().equals(PatientID)) {
//				test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
				movetoelement("//table[contains(@class,'listTable')]//following::div[text()='"+PatientID+"']");
				click("//table[contains(@class,'listTable')]//following::div[text()='"+PatientID+"']");
				actionsdoubleclick("//table[contains(@class,'listTable')]//following::div[text()='"+PatientID+"']");	
				Thread.sleep(2000);
		}else {
			test.log(LogStatus.FAIL, "Wrong Patient ID Entered");
			
			flag=false;
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		}else {
			test.log(LogStatus.FAIL, "Searched Patient ID Doesn't Match with any Records, verify the test data");
			flag=false;
			TakescreenShot(currentdriver,test);
//			Assert.fail();
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Search Page navigation Failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		finally {
			if(!flag) {
				TakescreenShot(currentdriver,test);
//				NavigatetoHome();
//				Assert.fail();
			}
		}
		return this;
	}
	
	public OrgWizardPage NavigatetoHome() {
		try {
		boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Search did not return')]",1);
		if(verify) {
			enterkey("//td[contains(text(),'Search did not return')]//following::div[text()='OK']");
			verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
			movetoelement("//div[contains(@id,'appHome_menu')]");
			actionsclick("//div[contains(@id,'appHome_menu')]");
			Thread.sleep(2000);
		}
		else {
			verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
			movetoelement("//div[contains(@id,'appHome_menu')]");
			actionsclick("//div[contains(@id,'appHome_menu')]");
			Thread.sleep(2000);
		}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Navigate Home Page");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
		
	}
	public OrgWizardPage demographicsPatientVerifyReflect(String PatientID, String org2) throws InterruptedException {
		boolean flag=true;
		
//		SearchPatientPage SearchPatientPage = new SearchPatientPage(currentdriver,test);
		try {
//		SearchPatientPage.clickSearchPatient();
		boolean verify = verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']",120);
		if(verify) {
		String id = getElementText("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
		if(id.trim().equals(PatientID)) {
//			test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
			boolean verify1 = verifyelementClickableExplicitwait("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']", 10);
			if(verify1) {
				movetoelement("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
				click("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
				actionsdoubleclick("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
				test.log(LogStatus.PASS, "Patient is able to be searched in <font color = blue>'"+org2+"'</font>org user's demographics after CMA org changes");
		}
			else {
				test.log(LogStatus.FAIL, "Patient is not able to be searched in <font color = blue>'"+org2+"'</font>org user's demographics after CMA org changes");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}else {
			test.log(LogStatus.FAIL, "Wrong Patient ID Entered");
			flag=false;
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		}else {
			test.log(LogStatus.FAIL, "Searched Patient ID Doesn't Match with any Records");
			flag=false;
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Search Page navigation Failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		finally {
			if(!flag) {
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}
		return this;
	}
	public OrgWizardPage demographicsPatientVerifyNotReflect(String PatientID, String org1) throws InterruptedException {
//		SearchPatientPage SearchPatientPage = new SearchPatientPage(currentdriver,test);
		try {
			boolean verify1 = verifyelementClickableExplicitwait("//td[contains(text(),'Search did not return any results')]", 10);
			if(verify1)
				verifyelementClickableExplicitwait("//div[text()='OK']", 5);
				click("//div[text()='OK']");
				test.log(LogStatus.PASS, "Patient is not able to be searched in <font color = blue>'"+org1+"'</font> user's demographics after org has been removed ");
			boolean verify = verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']",2);
			if(verify) {
				test.log(LogStatus.FAIL, "Patient is still able to be searched in <font color = blue>'"+org1+"'</font> user's demographics after org has been removed");	
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}	
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Search Page navigation Failed");
			TakescreenShot(currentdriver,test);
//			NavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	public OrgWizardPage closewindow() {
	 	List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    String parentWindow = windows.get(0); 
	    String childWindow = windows.get(1);
	    try {
	    driver.switchTo().window(childWindow).close();
	    driver.switchTo().window(parentWindow);
    	verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		actionsclick("//div[contains(@id,'appHome_menu')]");
		test.log(LogStatus.PASS,"Care Plan App Closed and Navigted to Home Page");
	    }catch(Exception e) {
	    	test.log(LogStatus.FAIL,"Care Plan App Not Closed, Unable to Navigte to Home Page");
	    	TakescreenShot(currentdriver,test);
	    	Assert.fail();
	    }
	    return this;
	}
	public OrgWizardPage SearchButtonReflect(String org2,String PatientID2,String TestOrgOne ) {
		try {
			enterkey("//input[@type='submit']");
			boolean NoResults = verifyelementpresentExplicitwait("//*[contains(text(),'No Results Found')]", 2);
			if(NoResults) {
				test.log(LogStatus.FAIL,"Patient is not able to be searched in <font color = blue>'"+org2+"'</font> org user's careplan after CMA org changes");
				closewindow();
				OrgChangeToPrimary(PatientID2,TestOrgOne,org2 );
				test.log(LogStatus.INFO,"<font color='blue'>Since patient not able to be searched, this test case is failed and reassigned patient to Primary org </font>");
//				new LoginPage(driver, test).enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			boolean Result =verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]",5);
			if(Result) {
				test.log(LogStatus.PASS,"Patient is able to be searched in <font color = blue>'"+org2+"'</font>org user's careplan after CMA org changes");
				closewindow();
			}

		}catch(Exception e) {TakescreenShot(currentdriver,test);}
		return this;	
	}
	public OrgWizardPage SearchButtonNotReflect(String org1,String org2,String PatientID2,String TestUserIDThree,String Url,String Password) {
		try {
			enterkey("//input[@type='submit']");
			boolean NoResults = verifyelementpresentExplicitwait("//*[contains(text(),'No Results Found')]", 7);
			if(NoResults) {
				test.log(LogStatus.PASS,"Patient is not able to be searched in <font color = blue>'"+org1+"'</font> org user's careplan after org has been removed");
				closewindow();
			}
			boolean Result =verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]",5);
			if(Result) {
				test.log(LogStatus.FAIL,"Patient is still able to be searched in <font color = blue>'"+org1+"'</font> org user's careplan after org has been removed");
				closewindow();
				new LoginPage(driver, test).enterUsername(TestUserIDThree,Url).enterPassword(Password).clickLogin(TestUserIDThree);
				OrgChangeToPrimary(PatientID2,org1,org2 );
				test.log(LogStatus.INFO,"<font color='blue'>Since patient able to be searched, this test case is failed and reassigned patient to Primary org </font>");
//				new LoginPage(driver, test).enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}

		}catch(Exception e) {TakescreenShot(currentdriver,test);}
		return this;	
	}
//}
	public OrgWizardPage clickDemographics() {
		try{
			verifyelementpresentExplicitwait("//div[contains(@id,'appEnrollment_menu')]",5);
			click("//div[contains(@id,'appEnrollment_menu')]");
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			boolean verify=verifyelementpresentExplicitwait("//input[contains(@name,'PatientId')]",5);
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Demographics app successfully");
			}else {
			test.log(LogStatus.FAIL,"Demographics app Navigation failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Demographics app");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}

	
//	LoginPage lp = new LoginPage(driver, test);
//	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
//	CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
//	SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
//	OrgWizardPage OrgWizardPage = new OrgWizardPage(driver, test);
	public OrgWizardPage CMAorgChangeToSecondOrg(String PatientID2, String TestOrgOne, String TestOrgTwo ) throws Exception  {
//		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		PatientID_Verification(PatientID2);
		CareteamAssignandRemovePage.enrollmentbtn();
		clickOrgWizard().getPrimaryCMAorgName1(TestOrgOne,TestOrgTwo);
		return this;
	}
	public OrgWizardPage VerifyAfterOrgRemoved(String PatientID2, String TestOrgOne, String TestOrgTwo,String TestUserIDThree,String Url,String Password ) throws Exception  {
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
//		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		demographicsPatientVerifyNotReflect(PatientID2,TestOrgOne);
		ModifyPatientPage.clickCarePlan().SearchPatientCarePlan(PatientID2);
		SearchButtonNotReflect(TestOrgOne,TestOrgTwo,PatientID2,TestUserIDThree,Url,Password);
		return this;
	}
	public OrgWizardPage VerifyPatientSearchable(String PatientID2 , String TestOrgTwo, String TestOrgOne) throws Exception  {
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
//		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		demographicsPatientVerifyReflect(PatientID2,TestOrgTwo);
		ModifyPatientPage.clickCarePlan().SearchPatientCarePlan(PatientID2);
		SearchButtonReflect(TestOrgTwo,PatientID2,TestOrgOne );
		return this;
	}
	public OrgWizardPage OrgChangeToPrimary(String PatientID2,String TestOrgOne,String TestOrgTwo ) throws Exception  {
//		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		PatientID_Verification(PatientID2);
		CareteamAssignandRemovePage.enrollmentbtn();
		clickOrgWizard().getPrimaryCMAorgName2(TestOrgOne,TestOrgTwo);
		return this;
	}
}
	

