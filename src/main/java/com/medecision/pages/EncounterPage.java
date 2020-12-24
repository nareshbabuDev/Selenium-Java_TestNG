package com.medecision.pages;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class EncounterPage extends SeleniumBase
{

	protected  RemoteWebDriver driver;
	static String todaydate;
	public EncounterPage(RemoteWebDriver driver, ExtentTest test) {
		
		super(driver, test);
		this.driver = driver;
		this.test =test;
		
	}
	public EncounterPage SearchButton() {
		try {
			enterkey("//input[@type='submit']");
			boolean NoResults = verifyelementpresentExplicitwait("//*[contains(text(),'No Results Found')]", 1);
			if(NoResults) {
				test.log(LogStatus.FAIL,"Searched Patient Not Shown In CarePlan");
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}else {
				test.log(LogStatus.INFO,"Navigated to Patient Information Page");
			}

		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Searched Patient Not Shown In CarePlan");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();	
		}
		return this;	
	}
	public EncounterPage EncounterPageNavigation(String OrgName) {
		try {
			boolean verify =verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]",5);
			if(verify) {
				click("//a[contains(text(),'Encounter')]");
				Thread.sleep(5000);
				boolean verify2 = verifyelementpresentExplicitwait("//*[contains(text(),'No Encounters')]");
				if(verify2==false) {
//					click("(//tr[contains(@class,'prevnotesdata')]//td[contains(text(),'"+OrgName+"')]//following::a[@title='Edit Encounter'])[1]");
					click("(//tr[contains(@class,'prevnotesdata')]//following::a)[1]");

				}else {
					test.log(LogStatus.FAIL, "No Existing  Encounters Shown in the Encounter Page ");
					TakescreenShot(driver,test);
					closewindow();
					Assert.fail();
				}
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "No Existing  Encounters Shown in the Encounter Page ");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
			
			
		}
		return this;
	}
	public EncounterPage EncounterUpdate() {
		Date d =new Date();
		todaydate = new SimpleDateFormat("dd-MMM-yyyy").format(d);
		try {
		boolean verify =verifyelementpresentExplicitwait("//form[contains(@name,'submitencounter')]",120);
		if(verify) {
			try {
				test.log(LogStatus.INFO,"Navigated to Encounter Update Page");
				click("(//img[contains(@name,'imgCalendar')])[1]");
				clearelementusingkeys("(//img[contains(@name,'imgCalendar')])[1]");
				clearAndType("//input[contains(@name,'addate')]", todaydate);
				click("//*[contains(text(),'Done')]//parent::button");
				Thread.sleep(1000);
				click("(//img[contains(@name,'imgCalendar')])[2]");
				clearelementusingkeys("(//img[contains(@name,'imgCalendar')])[2]");
				clearAndType("//input[contains(@name,'disdate')]", todaydate);
				click("//*[contains(text(),'Done')]//parent::button");
				Thread.sleep(1000);
				
			}catch(Exception e) {
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
		}else {
			test.log(LogStatus.FAIL, "Encounter Edit Page Navigation Failed");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		}
		catch(Exception  e) {
			test.log(LogStatus.FAIL, "Encounter Edit Page Navigation Failed");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
	}
	public EncounterPage SaveButton() {
		try {
			movetoelement("//input[@value='Save']");
			click("//input[@value='Save']");
			Thread.sleep(10000);
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Click Save Button");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
	}
	public EncounterPage EncounterUpdateVerify() {
		try {
			boolean verify =verifyelementpresentExplicitwait("(//td[contains(text(),'"+todaydate+"')])[1]", 120);
			if(verify) {
				test.log(LogStatus.PASS,"Encounter Updated SuccessFully With Admit Date of:<font color='blue'>"+todaydate+"</font>");
			}else if(verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]")) {
				click("//a[contains(text(),'Encounter')]");
				Thread.sleep(1000);
				click("//a[contains(text(),'Demographics')]");
				Thread.sleep(1000);
				verifyelementpresentExplicitwait("(//td[contains(text(),'"+todaydate+"')])[1]", 120);
				test.log(LogStatus.PASS,"Encounter Updated SuccessFully With Admit Date of:<font color='blue'>"+todaydate+"</font>");
			}
			else {
				test.log(LogStatus.FAIL, "Updated Encouter not reflected in Encounter Home Page");
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Encounter Home Page Navigation Failed");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
	}
	
	public EncounterPage closewindow() {
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
	    }
	    return this;
	}
	public EncounterPage EncounterUpdateVerification(String PatientID,String Org) {
		try {
			ModifyPatientPage ModifyPatientPage= new ModifyPatientPage(driver,test);
			ModifyPatientPage.clickCarePlan().SearchPatientCarePlan(PatientID);
			SearchButton().EncounterPageNavigation(Org).EncounterUpdate().SaveButton().EncounterUpdateVerify().closewindow();
			
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Encounter Update Scenario Failed");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
	}
	public EncounterPage clicklookup()  throws InterruptedException{
		try {
			driver.findElement(By.xpath("//input[@type='submit']")).sendKeys(Keys.RETURN);
			test.log(LogStatus.INFO,"Navigated to Careplan Demographics page");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to click lookupbutton");
			new ModifyPatientPage(driver, test).closecareplannavigatetohome();
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public void programverifyincareplan(String ProgramName) throws InterruptedException {
		try {
		boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Programs')]", 3);
		Thread.sleep(1000);
		if(verify) {
		boolean verify1 =verifyelementpresentExplicitwait("//td[contains(text(),'"+ProgramName+"')]", 5);
		try {
		 if(verify1) {
		  String Add = driver.findElementByXPath("//td[contains(text(),'"+ProgramName+"')]").getText();
		  if(Add.trim().equals(ProgramName.trim())) 
		  {
			  test.log(LogStatus.PASS,"Added Program <b><font color='blue'>'"+ProgramName+"'</font></b> is updated successfully in CarePlan app");
		  }
		 }
		  else if(verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]",5)) {
		  Thread.sleep(1000);
		  click("//a[contains(text(),'Encounter')]");
		  verifyelementpresentExplicitwait("//a[contains(text(),'Demographics')]",5);
		  Thread.sleep(1000);
		  click("//a[contains(text(),'Demographics')]");
		  String Add1 = driver.findElementByXPath("//td[contains(text(),'"+ProgramName+"')]").getText();
		  if(Add1.trim().contains(ProgramName)) 
		  {
			  test.log(LogStatus.PASS,"Added Program <b><font color='blue'>'"+ProgramName+"'</font></b> is updated successfully in CarePlan app");
		  }else {
			  test.log(LogStatus.FAIL,"Added Program <b><font color='red'>'"+ProgramName+"'</font></b> is not updated in CarePlan app");
				  new ModifyPatientPage(driver, test).closecareplannavigatetohome();
				  TakescreenShot(driver,test);
				  Assert.fail(); 
			  }
		  }
		  else {
			  test.log(LogStatus.FAIL,"Added Program '"+ProgramName+"' is not updated successfully in CarePlan app");
			  new ModifyPatientPage(driver, test).closecareplannavigatetohome();
			  TakescreenShot(driver,test);
			  Assert.fail(); 
		  }
		
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Updated Program Not Reflected in CarePlan");
				new ModifyPatientPage(driver, test).closecareplannavigatetohome();
			}
		}else {
			test.log(LogStatus.INFO,"Program not Configured in the Careplan Page");	
			}
		}
			  catch(Exception e) {
					test.log(LogStatus.FAIL,"CarePlan Search Page Loading Timeout");
					TakescreenShot(driver,test);
					new ModifyPatientPage(driver, test).closecareplannavigatetohome();
					Assert.fail();
				}
		}
		
}
