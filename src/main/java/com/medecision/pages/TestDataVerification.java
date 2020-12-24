package com.medecision.pages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.apache.commons.collections4.CollectionUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;

public class TestDataVerification extends SeleniumBase{
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	
	public TestDataVerification(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.currentdriver = driver;
		this.test =test;
	}
	
	public TestDataVerification enterPatientID(String PatientID) throws InterruptedException {
		try {
		boolean verify =verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]",50);
		if(verify) {
		clearelementusingkeys("//input[contains(@name,'PatientId')]");
		javascriptexecutor("//input[contains(@name,'PatientId')]", PatientID);
		}
		else {
			clearelementusingkeys("//input[contains(@name,'PatientId')]");
			javascriptexecutor("//input[contains(@name,'PatientId')]", PatientID);	
		}
		}
		catch(Exception e) {
		test.log(LogStatus.FAIL, "Unable to Navigate Patient Search Page");
		TakescreenShot(currentdriver,test);
		NavigatetoHome();
		Assert.fail();
		}
		return this;
	}


	public TestDataVerification CareTeamTab() throws Exception
	{
		try{
			verifyelementpresentExplicitwait("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Care Team')]//parent::md-tab-item");
			click("//span[contains(@id,'patientEnrollmentTab') and contains(text(),'Care Team')]//parent::md-tab-item");
			boolean verify=verifyelementpresentExplicitwait("//*[contains(@aria-label,'Add new team member') or contains(@aria-label,'Add New Member')]//parent::button",90);
			if(verify) {
			test.log(LogStatus.INFO,"Navigated to the CareTeam Page successfully");
			}else {
			test.log(LogStatus.FAIL,"CareTeam Page Navigation failed");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Navigate CareTeam Page");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		return this;

	}
	public TestDataVerification ProviderAccessTab() throws Exception
	{

		try{
			verifyelementpresentExplicitwait("//md-tab-item//span[contains(text(),'Provider Access')]");
			click("//md-tab-item//span[contains(text(),'Provider Access')]");
			boolean verify=verifyelementpresentExplicitwait("//md-chips[contains(@id,'dynamicTypeAhead')]");
			if(verify) {
			test.log(LogStatus.INFO,"Navigated to the Provider Access Tab successfully");
			}else {
			test.log(LogStatus.FAIL,"Provider Access Tab Navigation failed");
			TakescreenShot(currentdriver,test);
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Provider Access Tab");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public TestDataVerification CareTeamDelete() throws Exception
	{
		try {
		click("//button[contains(@id,'directMenu')]");
		Thread.sleep(2000);
		verifyelementpresentExplicitwait("//md-icon[contains(@md-svg-icon,'delete')]//parent::button");
		click("//md-icon[contains(@md-svg-icon,'delete')]//parent::button");
		Thread.sleep(2000);
		verifyelementpresentExplicitwait("//button[contains(@class,'md-confirm-button')]");
		click("//button[contains(@class,'md-confirm-button')]");
		Thread.sleep(2000);
		test.log(LogStatus.INFO, "Existing Careteam  deleted successfully");
		}catch(Exception e) {
		test.log(LogStatus.FAIL, "Unable Delete the Existing Careteam");
		TakescreenShot(currentdriver,test);
		CloseEnrollmentNavigatetoHome();
		Assert.fail();
		}
		return this;
	}
	public TestDataVerification CloseCT() {
		try {
		verifyelementpresentExplicitwait("(//md-icon[@md-svg-icon='close']//parent::button)[1]",60);
		click("(//md-icon[@md-svg-icon='close']//parent::button)[1]");
		Thread.sleep(2000);
		boolean verify = verifyelementpresentExplicitwait("//*[contains(text(),'Discard Changes')]//parent::button",5);
		if(verify) {
			click("//*[contains(text(),'Discard Changes')]//parent::button");
			Thread.sleep(2000);
		}
		verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		click("//div[contains(@id,'appHome_menu')]");
		Thread.sleep(2000);
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to close the Enrollment Tab");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public TestDataVerification CloseEnrollmentNavigatetoHome() {
		try {
		verifyelementpresentExplicitwait("(//md-icon[@md-svg-icon='close']//parent::button)[1]",60);
		click("(//md-icon[@md-svg-icon='close']//parent::button)[1]");
		Thread.sleep(2000);
		boolean verify = verifyelementpresentExplicitwait("//*[contains(text(),'Discard Changes')]//parent::button",5);
		if(verify) {
			click("//*[contains(text(),'Discard Changes')]//parent::button");
			Thread.sleep(2000);
		}
		verifyelementpresentExplicitwait("//*[contains(text(),'Reset')]//parent::button",60);
		click("//*[contains(text(),'Reset')]//parent::button");
		Thread.sleep(2000);
		verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		click("//div[contains(@id,'appHome_menu')]");
		Thread.sleep(2000);
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to close the Enrollment Tab");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public TestDataVerification NavigatetoHome() {
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
	public TestDataVerification PatientID_Verification(String PatientID) throws InterruptedException {
		boolean flag=true;
		
		SearchPatientPage SearchPatientPage = new SearchPatientPage(currentdriver,test);
		try {
		enterPatientID(PatientID);
		SearchPatientPage.clickSearchPatient();
		boolean verify = verifyelementpresentExplicitwait("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']",90);
		if(verify) {
		String id = getElementText("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
		if(id.trim().equals(PatientID)) {
			test.log(LogStatus.INFO,"Patient ID Entered In the PatientID Input Field:<font color='blue'><b>"+PatientID+"</b></font>");
			movetoelement("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
			click("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");
			actionsdoubleclick("//table[contains(@class,'listTable')]//div[text()='"+PatientID+"']");	
			Thread.sleep(2000);
		}else {
			test.log(LogStatus.FAIL, "Wrong Patient ID Entered");
			TakescreenShot(currentdriver,test);
			flag=false;
			NavigatetoHome();
			Assert.fail();
		}
		
		}else {
			test.log(LogStatus.FAIL, "Searched Patient ID Doesn't Match with any Records");
			TakescreenShot(currentdriver,test);
			flag=false;
			NavigatetoHome();
			Assert.fail();
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Search Page navigation Failed");
			TakescreenShot(currentdriver,test);
			NavigatetoHome();
			Assert.fail();
		}
		finally {
			if(!flag) {
				TakescreenShot(currentdriver,test);
				NavigatetoHome();
				Assert.fail();
			}
		}
		return this;
	}
	public TestDataVerification Org_Verification(String Org1,String Org2) throws Exception,InterruptedException {
		boolean flag=true;
		String SecOrg=null;
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(currentdriver,test);
		CareteamAssignandRemovePage.enrollmentbtn();
		try {
			boolean verify = verifyelementpresentExplicitwait("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]",120);
			if(verify) {
				String primaryOrg=getElementText("//md-select[contains(@ng-change,'primaryCmaChange')]//span/div[contains(@class,'text')]").trim().toLowerCase();
				if(primaryOrg.equals(Org1.toLowerCase()) || primaryOrg.equals(Org2.toLowerCase())) {
					test.log(LogStatus.INFO, "This Patient From <font color='blue'>"+primaryOrg.toUpperCase()+"</font>");
					boolean verify1 = verifyelementpresentExplicitwait("//md-chips[contains(@id,'secondryTypeahead')]//following::md-chip-template/span",1);
					if(verify1) {
					SecOrg = getElementText("//md-chips[contains(@id,'secondryTypeahead')]//following::md-chip-template/span");
					flag=false;
					}
					else {flag=true;}
					if(flag){
						test.log(LogStatus.PASS, "In Org Wizard Page, No Consent Org given to this Patient");
					}
					else {
					test.log(LogStatus.FAIL,"Consent Org Given to this Patient, Consent Org is:<b>"+SecOrg.trim()+"</b>");
					TakescreenShot(currentdriver,test);
					flag=false;
					}
				}
				else {
					flag=false;
					test.log(LogStatus.FAIL, "This Patient Org Doesn't Match with Test Org");
					TakescreenShot(currentdriver,test);
				}
			}
		}catch(Exception e) {
			test.log(LogStatus.INFO, "Unable to Navigate Entrollment Wizard");
			flag=false;
		}
		finally {
			if(!flag) {
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
			}
		}
		
		return this;
	}
	public TestDataVerification Provider_Verification() throws Exception,InterruptedException {
		boolean flag=true;
		try {
		ProviderAccessTab();
		boolean verify1 = verifyelementpresentExplicitwait("(//md-chips[contains(@id,'dynamicTypeAhead')]//md-chip[contains(@ng-class,'md-readonly')])[1]",120);
		if(verify1) {
		int consentorglist =elementlist("//md-chips[contains(@id,'dynamicTypeAhead')]//md-chip[contains(@ng-class,'md-readonly')]");
		for(int i=1;i<=consentorglist;i++) {
		String Color = GetBackgroundColor("(//md-chips[contains(@id,'dynamicTypeAhead')]//md-chip[contains(@ng-class,'md-readonly')])["+i+"]");
		if(!( (Color.toLowerCase().equals("#ff5722") || (Color.toLowerCase().equals("#ff8a65")) ))) {
			flag=false;
		}
		}
		}
		if(flag){
			test.log(LogStatus.PASS, "In Provider Access Page ,No Consent Org given to this Patient");
		}
		else {
			test.log(LogStatus.FAIL,"In Provider Access Page Secondary Org Given as Consent");
			TakescreenShot(currentdriver,test);
		}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to Navigate Provider Access Page");
			TakescreenShot(currentdriver,test);
			flag=false;
		}
		finally {
			if(!flag) {
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
			}
		}
		return this;
	}
	public TestDataVerification CareTeam_Delete(String PatientID,ArrayList <String> CT) {
		boolean flag = true,flag1=true;
		try {
		boolean verify = verifyelementpresentExplicitwait("//input[contains(@class,'ng-empty') and contains(@name,'careTeam')]",5);
		if (verify==false) {
		String CTas = currentdriver.findElementByXPath("//input[contains(@name,'careTeam')]").getAttribute("value");
		CTas=CTas.toUpperCase();
		for(int i=0;i<=CT.size()-1;i++) {
		if(!(CT.contains(CTas))) {
			test.log(LogStatus.INFO, "Exsting Care Team not matched with test Data, Existing Care Team Assinged for this "+PatientID+" is:<font color='blue'>"+CTas+"</font>");
			CareTeamDelete();
			flag=false;
			break;
		}
		else {
			flag=true;
		}
		}
		if(flag) {
			test.log(LogStatus.PASS, "Test Care Team Assigned for this Patient, Patient ID is:<font color ='blue'>"+PatientID+"</font>");	
		}
		}else {
			test.log(LogStatus.PASS, "No Care Team Assigned for this Patient:"+PatientID);
			flag=true;
		}
		try {
			verifyelementpresentExplicitwait("(//md-icon[@md-svg-icon='close']//parent::button)[1]",60);
			click("(//md-icon[@md-svg-icon='close']//parent::button)[1]");
			Thread.sleep(3000);
			boolean verify1 = verifyelementpresentExplicitwait("//*[contains(text(),'Discard Changes')]//parent::button",5);
			if(verify1) {
				click("//*[contains(text(),'Discard Changes')]//parent::button");
				Thread.sleep(2000);
			}
			verifyelementpresentExplicitwait("//*[contains(text(),'Reset')]//parent::button",120);
			click("//*[contains(text(),'Reset')]//parent::button");
			Thread.sleep(3000);
		}catch(Exception e) {
			flag1=false;
		}

		}
		catch(Exception e) {
		test.log(LogStatus.FAIL, "Care Team Navigation Failed !");
		TakescreenShot(currentdriver,test);
		flag=false;
		}
		finally {
			if(!flag1) {
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
			}
		}
		
		return this;
	}
	public TestDataVerification CareTeam_Verification(String CT,String Org1,String Org2,ArrayList<String> Users) throws Exception,InterruptedException {
		List <String> CareTeamMembers = new ArrayList<String>();
		ArrayList<String> UsesrList=Split(Users, "@");
		ArrayList<String> UIUsesrList = new ArrayList<String>();
		boolean flag=true;
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(currentdriver,test);
		try {
		CareteamAssignandRemovePage.assigncareteam(CT);
		boolean verify1 = verifyelementpresentwithdelaytime("(//md-icon[@aria-label='domain']/../md-truncate)[1]",2);
		if(verify1) {
		int consentorglist =elementlist("//md-icon[@aria-label='email']/../md-truncate");
		for(int i=1;i<=consentorglist;i++) {
		String OtherOrgUser = getElementText("(//md-icon[@aria-label='email']/../md-truncate)["+i+"]").split("_")[0].toLowerCase();
		UIUsesrList.add(OtherOrgUser);
		}
		if(UIUsesrList.size()>1)
		{
		for(String username:CollectionUtils.removeAll(UIUsesrList,UsesrList )) {
			String OtherOrgName = getElementText("(//md-icon[@aria-label='email']/../md-truncate[contains(text(),'"+username+"')]//preceding::md-icon[@aria-label='domain']/../md-truncate)[1]");
			String OtherOrgUserEmailID =getElementText("(//md-icon[@aria-label='email']/../md-truncate[contains(text(),'"+username+"')])[1]");
			CareTeamMembers.add("UserOrg:<font color='brown'><b>"+OtherOrgName+"<b></font><br>"
					+ "UserEmailID:<font color='brown'><b>"+OtherOrgUserEmailID+"<b></font><br>");
			flag=false;
		}
		}else {
			test.log(LogStatus.FAIL, "One of the Test Org User Missing in the Test Care Team");
			TakescreenShot(currentdriver,test);
			CloseEnrollmentNavigatetoHome();
			Assert.fail();
		}
		if(flag) {
			test.log(LogStatus.PASS,"Test Org User Only Present in the Given care Team:"+CT.toUpperCase());
		}
		else {
			test.log(LogStatus.FAIL,"In "+CT.toUpperCase()+" Care Team, Except Test User Other User Given as Consent:<br>"
					+CareTeamMembers);
			TakescreenShot(currentdriver,test);
			flag=false;		}

		}
		}
		catch(Exception e) {
			test.log(LogStatus.INFO, "Unable to Navigate CareTeam Wizard Page");
			flag=false;		
		}finally {
			if(!flag) {
				CloseEnrollmentNavigatetoHome();
				Assert.fail();
				}
		}
		return this;
	}
	ArrayList<String>  Split(ArrayList<String> listvalues,String Regexval) {
		ArrayList<String>users = new ArrayList<String>();
		for (int i=0;i<=listvalues.size()-1;i++) {
			users.add(listvalues.get(i).split(Regexval)[0].toLowerCase());
		}
		return users;
		
	}

	
}
