package com.medecision.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.medecision.pages.TestDataVerification;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CareteamAssignandRemovePage extends SeleniumBase {
	protected RemoteWebDriver driver;
	public static String CareTeam;

	public CareteamAssignandRemovePage(RemoteWebDriver driver, ExtentTest test) {

		super(driver, test);
		this.driver = driver;
		this.test = test;

	}

	public CareteamAssignandRemovePage CareTeamTestConfig() throws Exception {
		test.log(LogStatus.SKIP, "Care Team Test Not Applicable For this ENV");
		return this;
	}

	public CareteamAssignandRemovePage AssignNewCareTeamAndVerification(ArrayList<String> CT,boolean Careteamleadverify) {
		try {
			if(!Careteamleadverify) {
			CareTeamPageNavigation().CareTeamDelete(CT).ClickSaveButton().CloseEnrollmentNavigatetoHome();
			}else {
			CareTeamPageNavigation().CareTeamDelete(CT).ClickSaveButton().CareTeamLeadVerification().CloseEnrollmentNavigatetoHome();
			
			}
		} catch (Exception e) {
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	
	public CareteamAssignandRemovePage CareTeamLeadVerification() {
		try {
		test.log(LogStatus.INFO, "Care Team Lead Verification");
		boolean verify=verifyelementpresentExplicitwait("//md-icon[contains(@class, 'flex-nogrow')]", 1);
		if(verify) {
			test.log(LogStatus.INFO, "Already One user Appointed as Leader, So removing the Careteam Lead");
			try {
				verifyelementpresentExplicitwait("(//md-icon[contains(@class, 'flex-nogrow')]//following::md-icon[contains(@aria-label,'dots-vertical')]//parent::button[contains(@class, 'flex-nogrow')])[1]", 2);
				click("(//md-icon[contains(@class, 'flex-nogrow')]//following::md-icon[contains(@aria-label,'dots-vertical')]//parent::button[contains(@class, 'flex-nogrow')])[1]");
				Thread.sleep(2000);
				actionsclick("//span[contains(text(),'Remove as lead')]");
				Thread.sleep(2000);
				click("//span[text()='yes']//parent::button");
				Thread.sleep(3000);
				verifyelementpresentExplicitwait("(//md-icon[contains(@class, 'flex-nogrow')]//following::md-icon[contains(@aria-label,'dots-vertical')]//parent::button[contains(@class, 'flex-nogrow')])[2]", 2);
				click("(//md-icon[contains(@class, 'flex-nogrow')]//following::md-icon[contains(@aria-label,'dots-vertical')]//parent::button[contains(@class, 'flex-nogrow')])[2]");
				Thread.sleep(2000);
				click("//span[contains(text(),'Make lead')]");
				ClickSaveButton();
				test.log(LogStatus.PASS, "User Two Marked as Lead");
				
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Careteam Lead Assign Failed");
				TakescreenShot(driver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
		}else {
			try {
				verifyelementpresentExplicitwait("(//md-icon[contains(@aria-label,'dots-vertical')]//parent::button[contains(@class, 'flex-nogrow')])[1]", 2);
				click("(//md-icon[contains(@aria-label,'dots-vertical')]//parent::button[contains(@class, 'flex-nogrow')])[1]");
				Thread.sleep(2000);
				click("//span[contains(text(),'Make lead')]");
				ClickSaveButton();
				test.log(LogStatus.PASS, "User One Marked as Lead");
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Careteam Lead Assign Failed");
				TakescreenShot(driver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
		}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Careteam Lead Verification Failed");
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	
	public CareteamAssignandRemovePage CloseEnrollmentNavigatetoHome()  {
		verifyelementpresentExplicitwait(
				"(//md-dialog[contains(@aria-label,'Enrollment')]//md-icon[@md-svg-icon='close'])[1]");
		click("(//md-dialog[contains(@aria-label,'Enrollment')]//md-icon[@md-svg-icon='close'])[1]");
		boolean verify = verifyelementpresentExplicitwait("//button[contains(text(),'Discard Changes')]", 3);
		if (verify) {
			click("//button[contains(text(),'Discard Changes')]");
		}
		verifyelementpresentExplicitwait("//button[contains(@aria-label,'Reset App')]", 60);
		click("//button[contains(@aria-label,'Reset App')]");
		verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]", 10);
		click("//div[contains(@id,'appHome_menu')]");
		return this;
	}

	public CareteamAssignandRemovePage CareTeamPageNavigation() {
		try {
			verifyelementpresentExplicitwait(
					"//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Care Team')]//parent::md-tab-item");
			click("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Care Team')]//parent::md-tab-item");
			boolean verify = verifyelementpresentExplicitwait(
					"//*[contains(@aria-label,'Add new team member') or contains(@aria-label,'Add New Member')]//parent::button",
					90);
			if (verify) {
				test.log(LogStatus.PASS, "Navigated to the CareTeam Page successfully");
			} else {
				test.log(LogStatus.FAIL, "CareTeam Page Navigation failed");
				TakescreenShot(driver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Navigate CareTeam Page");
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}

	public String SelectCareTeam(ArrayList<String> CareTeamList, String CTinUI) {
		String CareTeam;
		if (CTinUI.equals(CareTeamList.get(0).toLowerCase())) {
			CareTeam = CareTeamList.get(1);
		} else if (CTinUI.equals(CareTeamList.get(1).toLowerCase())) {
			CareTeam = CareTeamList.get(0);
		} else {
			CareTeam = CareTeamList.get(0);
		}
		return CareTeam.toUpperCase();
	}

	public CareteamAssignandRemovePage CareTeamDelete(ArrayList<String> CareTeamList) {
		try {
			boolean verify = verifyelementpresentExplicitwait(
					"//input[contains(@class,'ng-empty') and contains(@name,'careTeam')]", 2);
			if (verify == false) {
				String CTinUI = driver.findElementByXPath("//input[contains(@name,'careTeam')]").getAttribute("value")
						.trim().toLowerCase();
				String CareTeamAssign = SelectCareTeam(CareTeamList, CTinUI);
				verifyelementpresentExplicitwait("(//md-icon[@class='ng-scope'][@aria-label='dots-vertical'])[1]");
				click("(//md-icon[@class='ng-scope'][@aria-label='dots-vertical'])[1]");
				verifyelementpresentExplicitwait("//md-icon[contains(@md-svg-icon,'delete')]//parent::button");
				Thread.sleep(2000);
				click("//md-icon[contains(@md-svg-icon,'delete')]//parent::button");
				verifyelementpresentExplicitwait("//button[contains(@class,'md-confirm-button')]");
				Thread.sleep(2000);
				click("//button[contains(@class,'md-confirm-button')]");
				test.log(LogStatus.INFO,
						"Existing Careteam  <font color='blue'>" + CTinUI + "</font>  deleted successfully");
				AssignNewCareTeam(CareTeamAssign);
			} else {
				test.log(LogStatus.INFO, "No Existing Care Team Present For this Patient");
				AssignNewCareTeam(CareTeamList.get(0));
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Delete the Existing Careteam");
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}

	public CareteamAssignandRemovePage AssignNewCareTeam(String CT) {
		try {
			boolean verify = verifyelementpresentExplicitwait(
					"//input[contains(@class,'ng-empty') and contains(@name,'careTeam')]", 2);
			if (verify) {
				assigncareteam(CT);
				test.log(LogStatus.INFO, "Assigning Care Team is:<font color='blue'>" + CT + "</font>");
			} else {
				test.log(LogStatus.FAIL, "Unable to Assign a new Careteam");
				TakescreenShot(driver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Assign a new Careteam");
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}

	public CareteamAssignandRemovePage ClickSaveButton() {
		movetoelement("//*[@id='btnSaveCareTeam']//child::button");
		click("//*[@id='btnSaveCareTeam']//child::button");
		
		try {
			Thread.sleep(5000);
			boolean verify = verifyelementpresentExplicitwait(
					"//*[@id='btnSaveCareTeam']//child::button[@disabled='disabled']", 240);
			if (verify) {
				test.log(LogStatus.PASS, "New Care Team Changes Saved Successfully");
			} else {
				test.log(LogStatus.FAIL, "Unable to Save the Care Team Changes");
				TakescreenShot(driver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Save the Care Team Changes");
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}

	public CareteamAssignandRemovePage enrollmentbtn() throws Exception {
		try {
			boolean verify = verifyelementpresentExplicitwait("//div[contains(text(),'Enrollment')]//parent::td", 120);
			if (verify) {
				actionsclick("//div[contains(text(),'Enrollment')]//parent::td");
				test.log(LogStatus.INFO, "Enrollment button is clicked");
			} else {
				test.log(LogStatus.FAIL, "Enrollment Page Navigation Failed");
				TakescreenShot(driver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Enrollment Button Not Appeared");
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}

	public CareteamAssignandRemovePage assigncareteam(String careteamname) throws Exception {
		try {
			test.log(LogStatus.INFO, "Search the Existing CareTeam");
			verifyelementpresentExplicitwait("//input[contains(@aria-label,'New Team')]");
			clearelementusingkeys("//input[contains(@aria-label,'New Team')]");
			driver.findElementByXPath("//input[contains(@aria-label,'New Team')]").sendKeys(careteamname);
			verifyelementpresentExplicitwait("(//span[contains(@class,'highlight')])[1]", 240);
			driver.findElementByXPath("(//span[contains(@class,'highlight')])[1]").click();
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Select the Existing Careteam");
			TakescreenShot(driver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}


	public CareteamAssignandRemovePage  VerifyCTAssigedPatientShownInPP(String PatientID) {
			try {
				verifyelementpresentExplicitwait("//i[contains(text(),'refresh')]//parent::div//parent::button");
				click("//i[contains(text(),'refresh')]//parent::div//parent::button");
				movetoelement("//input[contains(@placeholder,'Search')]");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				clearAndType("//input[contains(@placeholder,'Search')]",PatientID );
				try{
					boolean verify = verifyelementpresentExplicitwait("//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PatientID+"')]",10);
					if (verify) {
						test.log(LogStatus.PASS, "Care Team Assigned Patient Automatically Added to the User's Patient Panel");
					}
					else {
					test.log(LogStatus.FAIL, "Careteam Assigned Patient not appeared in the Patientpanel");
					clearelementusingkeys("//input[contains(@placeholder,'Search')]");
					new TestDataVerification(driver,test).NavigatetoHome();
					TakescreenShot(driver,test);
					Assert.fail();
					}
				}
			catch(Exception e) {
				test.log(LogStatus.FAIL, "Careteam Assigned Patient not appeared in the Patientpanel");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				new TestDataVerification(driver,test).NavigatetoHome();
				TakescreenShot(driver,test);
				Assert.fail();
				
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "The Searched Patient Retrive Nothing");
			new TestDataVerification(driver,test).NavigatetoHome();
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}

	public CareteamAssignandRemovePage DeleteCTFromPP(String PatientID) {
		try {
			verifyelementpresent("(//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PatientID+"')]//following::i[contains(text(),'more')]//parent::div)[1]");
			Thread.sleep(2000);
			click("(//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PatientID+"')]//following::i[contains(text(),'more')]//parent::div)[1]");
			verifyelementpresentExplicitwait("//div[contains(@class,'animate-popup-down')]//following::div[contains(text(),'Care Team')]");
			Thread.sleep(2000);
			click("//div[contains(@class,'animate-popup-down')]//following::div[contains(text(),'Care Team')]");
			try {
			new TestDataVerification(driver, test).CareTeamDelete().CloseCT();
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to Delete the assigned Care Team");
				TakescreenShot(driver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
			
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click Show more Icon in Patient Panel");
			new TestDataVerification(driver,test).NavigatetoHome();
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
		}
	public CareteamAssignandRemovePage VerifyCTRemovedPatientRemovedFromIPP(String PatientID) {
		try {
			verifyelementpresentExplicitwait("//i[contains(text(),'refresh')]//parent::div//parent::button");
			click("//i[contains(text(),'refresh')]//parent::div//parent::button");
			movetoelement("//input[contains(@placeholder,'Search')]");
			clearelementusingkeys("//input[contains(@placeholder,'Search')]");
			clearAndType("//input[contains(@placeholder,'Search')]",PatientID );
			try{
				boolean verify = verifyelementpresentExplicitwait("//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PatientID+"')]",2);
				if (!verify) {
					test.log(LogStatus.PASS, "Patient Automatically Removed from the User's Patient Panel, When the user remove the Care team for the patient");
				}
				else {
				test.log(LogStatus.FAIL, "Patient Not Removed from the User's Patient Panel, When the user remove the Care team for the patient");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				new TestDataVerification(driver,test).NavigatetoHome();
				TakescreenShot(driver,test);
				Assert.fail();
				}
			}
			catch(Exception e) {
			test.log(LogStatus.FAIL, "Careteam Assigned Patient not appeared in the Patientpanel");
			clearelementusingkeys("//input[contains(@placeholder,'Search')]");
			new TestDataVerification(driver,test).NavigatetoHome();
			TakescreenShot(driver,test);
			Assert.fail();
			
		}
	} catch (Exception e) {
		test.log(LogStatus.FAIL, "The Searched Patient Retrive Nothing");
		new TestDataVerification(driver,test).NavigatetoHome();
		TakescreenShot(driver,test);
		Assert.fail();
	}
		return this;
		}
}
