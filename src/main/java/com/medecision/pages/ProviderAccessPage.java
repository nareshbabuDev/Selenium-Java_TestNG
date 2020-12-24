package com.medecision.pages;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProviderAccessPage extends SeleniumBase {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public ProviderAccessPage(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver = driver;
		this.test =test;
	}	
	public ProviderAccessPage clickProviderAccessWizard(){
		boolean verify = verifyelementpresentExplicitwait("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Provider Access')]//parent::md-tab-item", 10);
		if(verify) {
			click("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Provider Access')]//parent::md-tab-item");
		}
			else {
			test.log(LogStatus.FAIL, "Enrollment app is not loading ");
			TakescreenShot(currentdriver,test);
			Assert.fail();}
		return this;
	}
	public ProviderAccessPage clickToAddProvider(){
		boolean verify = verifyelementpresentExplicitwait("//h2[contains(text(),'Provider')]//following::input[@ng-if='!floatingLabel']", 10);
		if(verify)
			click("//h2[contains(text(),'Provider')]//following::input[@ng-if='!floatingLabel']");
			else {
			test.log(LogStatus.FAIL, "Adding an org to provider access is failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public ProviderAccessPage searchProvider(String org2){
		boolean verify = verifyelementpresentExplicitwait("//h2[contains(text(),'Provider')]//following::input[@ng-if='!floatingLabel']", 10);
		if(verify) {
			driver.findElement(By.xpath("//h2[contains(text(),'Provider')]//following::input[@ng-if='!floatingLabel']")).sendKeys(org2);
		}
			else {
			test.log(LogStatus.FAIL, "Given org is not displayed in search ");
			TakescreenShot(currentdriver,test);
			Assert.fail();}
		return this;
	}
	public ProviderAccessPage addProvider(String org2){
		boolean verify = verifyelementpresentExplicitwait("//span[@class='highlight' and contains(text(),'"+org2+"')]", 10);
		if(verify) {
			click("//span[@class='highlight' and contains(text(),'"+org2+"')]");
			test.log(LogStatus.PASS, "<font color = 'blue'> '"+org2+"' is successfully added to the provider access");
		}
		else {
			test.log(LogStatus.FAIL, "<font color = 'blue'> '"+org2+"' is not successfully added to the provider access");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		save();
		closeIcon();
		return this;
		
		
	}
	public ProviderAccessPage deleteProvider(String org2){
		boolean verify = verifyelementpresentExplicitwait("(//button//md-icon[@md-svg-icon='close' and @aria-label='close'])[1]", 10);
		if(verify) {
			click("(//button//md-icon[@md-svg-icon='close' and @aria-label='close'])[1]");
			test.log(LogStatus.PASS, "<font color ='blue'>'"+org2+"' is removed successfully</font>");
		}
		else {
			test.log(LogStatus.FAIL, "Org is not removed successfully");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
		save();
		closeIcon();
		return this;
	}
	public ProviderAccessPage save() {
		  boolean verify = verifyelementClickableExplicitwait("//*[@id='btnSaveProvider']/button/span", 5);
		  if(verify) {
		  click("//*[@id='btnSaveProvider']/button/span");}
		  else {
			  test.log(LogStatus.FAIL, "Save button not clicked");
			  TakescreenShot(currentdriver,test);
			  Assert.fail();}
		return this;
	}
	public ProviderAccessPage closeIcon() {
		  verifyelementClickableExplicitwait("(//md-icon[@md-svg-icon='close' and @class='ng-scope'])[1]", 5);
		  click("(//md-icon[@md-svg-icon='close' and @class='ng-scope'])[1]");
		return this;
	}
	public ProviderAccessPage demographicsPatientVerifyNotReflect(String PatientID, String org2) throws InterruptedException {
//		SearchPatientPage SearchPatientPage = new SearchPatientPage(currentdriver,test);
		try {
			boolean verify1 = verifyelementClickableExplicitwait("//td[contains(text(),'Search did not return any results')]", 10);
			if(verify1)
				verifyelementClickableExplicitwait("//div[text()='OK']", 5);
				click("//div[text()='OK']");
				test.log(LogStatus.PASS, "Patient is not able to be searched in <font color = blue>'"+org2+"'</font> user's demographics after org has been removed ");
			boolean verify = verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']",2);
			if(verify) {
				test.log(LogStatus.FAIL, "Patient is still able to be searched in <font color = blue>'"+org2+"'</font> user's demographics after org has been removed");	
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}	
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Search Page navigation Failed");
			NavigatetoHome();
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public ProviderAccessPage demographicsPatientVerifyReflect(String PatientID, String org2) throws InterruptedException {
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
				test.log(LogStatus.PASS,"Patient is able to be searched in <font color = blue>'"+org2+"'</font> org user's demographic after org is added to provider access");
				Thread.sleep(2000);
		}else {
			TakescreenShot(currentdriver,test);
			test.log(LogStatus.FAIL,"Patient is not able to be searched in <font color = blue>'"+org2+"'</font> org user's demographic even after org is added to provider access");
			flag=false;
//			NavigatetoHome();
			Assert.fail();
		}
		
		}else {
			test.log(LogStatus.FAIL, "Searched Patient ID Doesn't Match with any Records");
			flag=false;
//			NavigatetoHome();
			Assert.fail();
		}
		}
		catch(Exception e) {
			TakescreenShot(currentdriver,test);
			test.log(LogStatus.FAIL, "Search Page navigation Failed");
//			NavigatetoHome();
			Assert.fail();
		}
		finally {
			if(!flag) {
//				NavigatetoHome();
				Assert.fail();
			}
		}
		return this;
	}
	public ProviderAccessPage SearchButtonReflect(String org2,String PatientID2) {
		try {
			enterkey("//input[@type='submit']");
			boolean NoResults = verifyelementpresentExplicitwait("//*[contains(text(),'No Results Found')]", 2);
			if(NoResults) {
				test.log(LogStatus.FAIL,"Patient is not able to be searched in <font color = blue>'"+org2+"'</font> org user's careplan even after org is added to provider access");
				closewindow();
				OrgRemovedFromProviderAccess(PatientID2,org2 );
				test.log(LogStatus.FAIL,"This test case is failed and '"+org2+"'</font> is removed from provider access");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			boolean Result =verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]",5);
			if(Result) {
				test.log(LogStatus.PASS,"Patient is able to be searched in <font color = blue>'"+org2+"'</font>org user's careplan after org is added to provider access");
				closewindow();
			}

		}catch(Exception e) {TakescreenShot(currentdriver,test);}
		return this;	
	}
	public ProviderAccessPage SearchButtonNotReflect(String org2) {
		try {
			enterkey("//input[@type='submit']");
			boolean NoResults = verifyelementpresentExplicitwait("//*[contains(text(),'No Results Found')]", 2);
			if(NoResults) {
				test.log(LogStatus.PASS,"Patient is not able to be searched in <font color = blue>'"+org2+"'</font> org user's careplan after org has been removed");
				closewindow();
			}
			boolean Result =verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]",5);
			if(Result) {
				test.log(LogStatus.FAIL,"Patient is still able to be searched in <font color = blue>'"+org2+"'</font> org user's careplan after org has been removed");
				TakescreenShot(currentdriver,test);
				closewindow();
				Assert.fail();
			}

		}catch(Exception e) {TakescreenShot(currentdriver,test);}
		return this;	
	}
	public ProviderAccessPage NavigatetoHome() {
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
			TakescreenShot(currentdriver,test);
			test.log(LogStatus.FAIL, "Unable to Navigate Home Page");
			Assert.fail();
		}
		return this;
	} 
	public ProviderAccessPage Ok() {
	boolean okButton = verifyelementpresentExplicitwait("//span[text()='OK']", 4);
	if(okButton) {
	click("//span[text()='OK']");
	}
	return this;
	}
	public ProviderAccessPage closewindow() {
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
	    	TakescreenShot(currentdriver,test);
	    	test.log(LogStatus.FAIL,"Care Plan App Not Closed, Unable to Navigte to Home Page");
	    	Assert.fail();
	    }
	    return this;
	}

	public ProviderAccessPage AddOrgInProviderAccess(String PatientID2,String TestOrgTwo ) throws Exception  {
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		OrgWizardPage OrgWizardPage = new OrgWizardPage(driver, test);
		ModifyPatientPage.clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		OrgWizardPage.PatientID_Verification(PatientID2);
		CareteamAssignandRemovePage.enrollmentbtn();
		clickProviderAccessWizard().clickToAddProvider().searchProvider(TestOrgTwo).addProvider(TestOrgTwo);
		return this;
	}
	public ProviderAccessPage VerifyAfterOrgRemoved(String PatientID2, String TestOrgOne ) throws Exception  {
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		ModifyPatientPage.clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		demographicsPatientVerifyNotReflect(PatientID2,TestOrgOne);
		ModifyPatientPage.clickCarePlan().SearchPatientCarePlan(PatientID2);
		SearchButtonNotReflect(TestOrgOne);
		return this;
	}
	public ProviderAccessPage VerifyPatientSearchable(String PatientID2 , String TestOrgTwo) throws Exception  {
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		ModifyPatientPage.clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		demographicsPatientVerifyReflect(PatientID2,TestOrgTwo);
		ModifyPatientPage.clickCarePlan().SearchPatientCarePlan(PatientID2);
		SearchButtonReflect(TestOrgTwo,PatientID2);
		return this;
	}
	public ProviderAccessPage OrgRemovedFromProviderAccess(String PatientID2,String TestOrgTwo ) throws Exception  {
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		SearchPatientPage SearchPatientPage = new SearchPatientPage(driver,test);
		OrgWizardPage OrgWizardPage = new OrgWizardPage(driver, test);
		ModifyPatientPage.clickDemographics();
		SearchPatientPage.enterPatientID(PatientID2).clickSearchPatient();
		OrgWizardPage.PatientID_Verification(PatientID2);
		CareteamAssignandRemovePage.enrollmentbtn();
		clickProviderAccessWizard().clickToAddProvider().deleteProvider(TestOrgTwo);
		return this;
	}


}
