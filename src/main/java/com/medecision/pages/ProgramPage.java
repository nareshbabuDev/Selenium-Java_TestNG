package com.medecision.pages;

import java.util.List;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProgramPage  extends SeleniumBase {
	
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	String ChildProgram,ProgramStatus;
	
	public ProgramPage(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver = driver;
		this.test =test;
	}
	
	public ProgramPage CloseEnrollmentNavigatetoHome() {
		verifyelementpresentExplicitwait("(//md-dialog[contains(@aria-label,'Enrollment')]//md-icon[@md-svg-icon='close'])[1]");
		click("(//md-dialog[contains(@aria-label,'Enrollment')]//md-icon[@md-svg-icon='close'])[1]");
		boolean verify = verifyelementpresentExplicitwait("//button[contains(text(),'Discard Changes')]",5);
		if(verify) {
			click("//button[contains(text(),'Discard Changes')]");
		}
		verifyelementpresentExplicitwait("//button[contains(@aria-label,'Reset App')]",3);
		click("//button[contains(@aria-label,'Reset App')]");
		verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		click("//div[contains(@id,'appHome_menu')]");
		return this;
	}
	public ProgramPage AddBtn() {
		try{
			verifyelementpresentExplicitwait("//*[contains(@aria-label,'Add a new program')]//parent::button");
			click("//*[contains(@aria-label,'Add a new program')]//parent::button");
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable Click Add new program Button, Program Page is Down");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	public ProgramPage ProgramPageNavigation()
	{
		try{
			verifyelementpresentExplicitwait("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Programs')]//parent::md-tab-item");
			click("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Programs')]//parent::md-tab-item");
			boolean verify=verifyelementpresentExplicitwait("//*[@aria-label='Add a new program']//parent::button",180);
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Program Page successfully");
			}else {
			test.log(LogStatus.FAIL,"Program Page Navigation failed, Program Page is Down");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Navigate Program Page");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	public ProgramPage DeleteExistingProgram() {
		boolean flag=false;
		try {
		boolean verify = verifyelementpresentExplicitwait("(//form[contains(@ng-repeat,'ProgramTable')])[1]",2);
		if(verify) {
		int programlist = elementlist("//form[contains(@ng-repeat,'ProgramTable')]");
		System.out.println(programlist);
		for (int i=programlist;i<=programlist;i--) {
			if(i==0){break;}
			click("(//form[contains(@ng-repeat,'ProgramTable')]//following::button[@btn-icon='dots-vertical'])["+i+"]");
			verifyelementclickableExplicitwait("//span[contains(text(),'Delete')]//parent::button",180);
			Thread.sleep(3000);
			click("//span[contains(text(),'Delete')]//parent::button");
			verifyelementclickableExplicitwait("//button[@id='btnConfirmProgramDelete']",180);
			Thread.sleep(3000);
			click("//button[@id='btnConfirmProgramDelete']");
			verifyelementpresentExplicitwait("//*[contains(text(),'Successfully deleted')]",180);
			verifyelementclickableExplicitwait("//span[text()='OK']//parent::button",180);
			Thread.sleep(3000);
			click("//span[text()='OK']//parent::button");
			Thread.sleep(2000);
			flag=true;	
		}
		if(flag) {
			test.log(LogStatus.PASS,"Successfully Deleted the most recent progam");
		}else {
			test.log(LogStatus.FAIL,"Unable to Delete the most recent progam");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		}else{
			test.log(LogStatus.INFO,"No Existing Program Present for this Patient");	
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Delete Existing Programs");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	public ProgramPage AddNewProgram(String PatientID) {
		try {
			ModifyPatientPage ModifyPatientPage=new ModifyPatientPage(driver,test);
			EncounterPage EncounterPage=new EncounterPage(driver,test);
			ProgramPageNavigation().DeleteExistingProgram().AddBtn().SelectParentProgramName().SelectProgramName().SelectStatus().ClickSaveButton().CloseEnrollmentNavigatetoHome();
			try {
			String prgname=ChildProgram+"("+ProgramStatus+")";
			ModifyPatientPage.clickCarePlan();
			ModifyPatientPage.SearchPatientCarePlan(PatientID);
			EncounterPage.clicklookup().programverifyincareplan(prgname);
			ModifyPatientPage.closecareplannavigatetohomewithoutreset();
			}catch(Exception e) {
				ModifyPatientPage.closecareplannavigatetohomewithoutreset();
				Assert.fail();
			}

		}catch(Exception e) {
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;	
	}
	public ProgramPage SelectParentProgramName() {
		try {
			if(verifyelementpresentExplicitwait("//md-select[contains(@name,'parentProgramName')]",2)) {
				click("//md-select[contains(@name,'parentProgramName')]");	
				try {
					boolean verify=verifyelementpresentExplicitwait("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]", 180);
					if(verify) {
						movetoelement("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]");				
						String ParentProgram = getElementText("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]").trim();
						click("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]");
						test.log(LogStatus.INFO, "Selected Parent Program Name is:<font color='blue'>"+ParentProgram+"</font>");
						SelectConsent(ParentProgram);
					}else {
						test.log(LogStatus.FAIL, "Unable to Add Parent Program");
						TakescreenShot(currentdriver,test);
						CloseEnrollmentNavigatetoHome();
						Assert.fail();
					}
				}catch(Exception e) {
					test.log(LogStatus.FAIL, "Unable to Add Parent Program");
					TakescreenShot(currentdriver,test);
					CloseEnrollmentNavigatetoHome();
					Assert.fail();
				}

			}else {
				test.log(LogStatus.INFO, "Parent Program Not Configured For this Environment");
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Add Parent Program");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();	
		}
		return this;
	}
	public ProgramPage SelectProgramName() {
		boolean verifysubprogram=verifyelementpresentExplicitwait("(//button[contains(@class,'sub-program-button')])[1]",1);
		if(!verifysubprogram) {
		click("//md-select[contains(@name,'programName')]");
		try {
			boolean verify=verifyelementpresentExplicitwait("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]", 180);
			if(verify) {
				movetoelement("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]");
				ChildProgram = getElementText("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]").trim();
				click("(//div[contains(@class,'md-active')]//md-option[@aria-disabled='false'])[1]");
				test.log(LogStatus.INFO, "Selected Child Program Name is:<font color='blue'>"+ChildProgram+"</font>");
				SelectConsent(ChildProgram);
			}else {
				test.log(LogStatus.FAIL, "Unable to Add Child Program");
				TakescreenShot(currentdriver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Add Child Program");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		}else {
			test.log(LogStatus.INFO, "Child Program Shown as Simple Sub Program");
		}
		return this;
	}
	public ProgramPage SelectConsent(String Programname) {
		boolean verify = verifyelementpresentExplicitwait("//form[contains(@name,'programConsentPanel')]",5);
		try {
			if(verify) {
				test.log(LogStatus.INFO, "Consent PopUp Appeared for this Program:<font color='blue'>"+Programname+"</font>");
				try {
					Thread.sleep(3000);
					click("//md-select-value//div[contains(text(),'Select Status')]");
					verifyelementpresentExplicitwait("//div[contains(text(),'Yes')]//parent::md-option");
					Thread.sleep(2000);
					click("//div[contains(text(),'Yes')]//parent::md-option");
					boolean verify2 = verifyelementpresentExplicitwait("//md-select[contains(@name,'consenter')]",5);
					if(verify2) {
					movetoelement("//md-select[contains(@name,'consenter')]");
					click("//md-select[contains(@name,'consenter')]");
					verifyelementpresentExplicitwait("(//div[contains(@class,'md-active')]//md-option[contains(@ng-repeat,'ConsentPanel')])[1]");
					click("(//div[contains(@class,'md-active')]//md-option[contains(@ng-repeat,'ConsentPanel')])[1]");
					}
					click("//span[contains(text(),'Finish')]//parent::button");
					Thread.sleep(2000);
					test.log(LogStatus.PASS, "Consent Popup Accepted for this Program");
	
				}catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Unable to Accept the Consent Popup");
					TakescreenShot(currentdriver,test);
					click("//span[contains(text(),'Cancel')]//parent::button");
					CloseEnrollmentNavigatetoHome();
					Assert.fail();
				}
			}
			else {
				test.log(LogStatus.INFO, "Consent PopUp Not Appeared for this Program:<font color='blue'>"+Programname+"</font>");
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Accept the Consent Popup");
			TakescreenShot(currentdriver,test);
			click("//span[contains(text(),'Cancel')]//parent::button");
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	public ProgramPage SelectStatus() {
		click("(//md-select[contains(@name,'status')])[1]");
		try {
			boolean verify=verifyelementpresentExplicitwait("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]", 180);
			if(verify) {
				movetoelement("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]");
				ProgramStatus = getElementText("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]//div[contains(@class,'text')]").trim();
				click("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]");
				test.log(LogStatus.INFO, "Selected Program Status is:<font color='blue'>"+ProgramStatus+"</font>");
				SelectEndReason(ProgramStatus);
			}else {
				test.log(LogStatus.FAIL, "Unable to Add Child Program");
				TakescreenShot(currentdriver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Add Child Program");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	public ProgramPage SelectEndReason(String ProgramStatus) {
		boolean verify = verifyelementpresentExplicitwait("//md-select[@name='endReason']",2);
		try {
			if(verify) {
				test.log(LogStatus.INFO, "End Reason Appeared for this Program Status:</font color='blue'>"+ProgramStatus+"</font>");
				try {
					click("//md-select[@name='endReason']");
					verifyelementpresentExplicitwait("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]");
					movetoelement("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]");
					Thread.sleep(2000);
					click("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]");
					Thread.sleep(2000);
					boolean verify2=verifyelementpresentExplicitwait("(//div[contains(@class,'md-active')]//md-option[@aria-selected='false'])[1]",1);
					if(!verify2) {
						test.log(LogStatus.PASS, "End Reason Added for this Program Status");
					}else {
						test.log(LogStatus.FAIL, "Unable to Add End Reson for this Program Status");
						TakescreenShot(currentdriver,test);
						CloseEnrollmentNavigatetoHome();
						Assert.fail();
					}
	
				}catch(Exception e)
				{
					test.log(LogStatus.FAIL, "Unable to Add End Reson for this Program Status");
					TakescreenShot(currentdriver,test);
					CloseEnrollmentNavigatetoHome();
					Assert.fail();
				}
			}
			else {
				test.log(LogStatus.INFO, "End Reason Not Appeared for this Program Status:<font color='blue'>"+ProgramStatus+"</font>");
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Add End Reson for this Program Status");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;
	}
	public ProgramPage ClickSaveButton() {
		
		try {
			movetoelement("//*[@id='btnSaveProgram']");
			Thread.sleep(2000);
			click("//*[@id='btnSaveProgram']");
			Thread.sleep(10000);
			boolean verify=verifyelementpresentExplicitwait("//*[@id='btnSaveProgram']//child::button[@disabled='disabled']", 180);
			if(verify) {
				test.log(LogStatus.PASS, "New Program Added Successfully");
			}else {
				test.log(LogStatus.FAIL, "Unable to Save the Recent Program");
				TakescreenShot(currentdriver,test);
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Save the Recent Program");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;	
	}
	

}
