package com.medecision.testsuites;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import com.medecision.pages.CarebookSearchPage;
import com.medecision.pages.CareteamAssignandRemovePage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.ModifyPatientPage;
import com.medecision.pages.TaskManagerPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.pages.UserUpdatePage;
import com.medecision.utils.DriverInitialize;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.LogStatus;


public class InsprintAutomation extends DriverInitialize{
	String Url,TestOrgOne,TestOrgTwo,PopManagerUser,PowerUser,TestUserIDOne,TestUserIDTwo,Password,PatientID1,PatientID2,PatientID3,CT1,CT2;
	//tc001
	@Test(groups = { "Regression"} , dataProvider="fetchData",priority = 1,alwaysRun=true)
	public void TC001_Login_DashboardLogin(Map<String,String> map) throws Exception {
	LoginPage lp = new LoginPage(driver, test);
	TestUserIDOne =map.get("TestUserIDOne");
	TestOrgOne=map.get("TestOrgOne");
	TestOrgTwo=map.get("TestOrgTwo");
	Url =map.get("Url");
	Password = map.get("Password");
	lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	//tc002
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC001_Login_DashboardLogin"},priority = 2)
	public void Sprint38_Dash_13023_13024_13052_13071_13078_DemographicsAppNewFieldsModification(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	PatientID1=this.EnvBulkDats.PatientID.get(0);
	try {
		lp.HomePageVerification(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID1);
		ModifyPatientPage.ModifyPage().PatientDetailsFieldUpdation(PatientID1);
		new CarebookSearchPage(driver,test).SwitchtoDefault();
	
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}
	//tc003
		@Test(groups = { "Smoke"} , dataProvider="fetchData",dependsOnMethods = {"TC001_Login_DashboardLogin"},priority = 3)
		public void Sprint38_Sprint40_13076_13077_13234_13405_Assign_CareTeam_with_Lead_Verify_in_Carebook_Panel_Remove_CareTeam_Verify_in_Panel(Map<String,String> map) throws InterruptedException {
		boolean Careteamleadverify=true;
		String PatientID = this.EnvBulkDats.PatientID.get(2);
		String CareTeamTest = map.get("CareTeamTest");
		LoginPage lp = new LoginPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
		try {
		if(CareTeamTest.toUpperCase().equals("Y")) {
		lp.HomePageVerification(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID);
		CareteamAssignandRemovePage.enrollmentbtn();
		CareteamAssignandRemovePage.AssignNewCareTeamAndVerification(this.EnvBulkDats.ExistingCareTeam,Careteamleadverify);
		new CarebookSearchPage(driver,test).searchByID(PatientID).CareTeamLeadCB();
		Careteamleadverify=false;
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID);
		CareteamAssignandRemovePage.enrollmentbtn();
		CareteamAssignandRemovePage.AssignNewCareTeamAndVerification(this.EnvBulkDats.ExistingCareTeam,Careteamleadverify);
		CareteamAssignandRemovePage.VerifyCTAssigedPatientShownInPP(PatientID).DeleteCTFromPP(PatientID).VerifyCTRemovedPatientRemovedFromIPP(PatientID);
		}
		else {
			CareteamAssignandRemovePage.CareTeamTestConfig();
		}
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
		}
	//tc004
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC001_Login_DashboardLogin"},priority = 4)
	public void Sprint39_Dash13109_searchByPIDinCarebook(Map<String,String> map) throws InterruptedException {
		LoginPage lp = new LoginPage(driver, test);
		CarebookSearchPage csp = new CarebookSearchPage(driver, test);
		PatientID1=this.EnvBulkDats.PatientID.get(2);
		try {
			lp.HomePageVerification(TestUserIDOne);
			boolean verify = lp.HomePageClick();
			if(verify) {
				csp.searchByID(PatientID1).SwitchtoDefault();
			}	
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
			
		}
	}
	
	//tc005
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC001_Login_DashboardLogin"},priority = 5)
	public void Sprint39_Dash13087_CareBookNavigationFromDemographicsPage(Map<String,String> map) throws InterruptedException {
		LoginPage lp = new LoginPage(driver, test);
		CarebookSearchPage csp = new CarebookSearchPage(driver, test);
		ModifyPatientPage mpp = new ModifyPatientPage(driver, test);
		TestDataVerification tdv = new TestDataVerification(driver, test);
		PatientID1=this.EnvBulkDats.PatientID.get(0);
		PatientID2=this.EnvBulkDats.PatientID.get(1);
		try {
			lp.HomePageVerification(TestUserIDOne);
			mpp.clickDemographics();
			tdv.PatientID_Verification(PatientID2);
			mpp.clickCarebookInDemogApp(PatientID2);
			csp.verifyPatient(PatientID2).SwitchtoDefault();
		
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
			lp.HomePageVerification(TestUserIDOne);
			mpp.clickDemographics();
			tdv.PatientID_Verification(PatientID2);
			mpp.clickCarebookInDemogApp(PatientID2);
			csp.verifyPatient(PatientID2).SwitchtoDefault();
		}
		}
		//tc006
		@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC001_Login_DashboardLogin"},priority = 5)
		public void Sprint39_Dash13509_VerifyAddedTaskInSupervisorTaskSummaryDashboard(Map<String,String> map) throws InterruptedException {
		LoginPage lp = new LoginPage(driver, test);
		TaskManagerPage TM = new TaskManagerPage(driver, test); 
		String TaskManagerConfig = map.get("TaskManagerConfig");
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		CarebookSearchPage CarebookSearchPage = new CarebookSearchPage(driver,test);
		TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
		UserUpdatePage uup = new UserUpdatePage(driver, test);
		TestUserIDTwo =map.get("TestUserIDTwo");
		PatientID1=this.EnvBulkDats.PatientID.get(2);
		try {
		if(TaskManagerConfig.toUpperCase().equals("Y")) {
			new LoginPage(driver, test).enterUsername(TestUserIDTwo,Url).enterPassword(Password).clickLogin(TestUserIDTwo);
			uup.getUserName();
			new LoginPage(driver, test).enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
			lp.HomePageVerification(TestUserIDOne);
			uup.checkSupervisor1(TestUserIDOne);
			lp.HomePageClick();
			TM.taskRefreshandGetCount();	
			ModifyPatientPage.clickDemographics();
			CarebookSearchPage.PatientID_Verification(PatientID1);
			ArrayList<String> LastFirstName=CarebookSearchPage.getFNLN();
			TestDataVerification.NavigatetoHome();
			TM.TaskManagerNavigation().CreateNewTask(LastFirstName.get(0),PatientID1);
			lp.HomePageClick();
			TM.taskRefreshandGetCount().compareCount();
			lp.enterUsername(TestUserIDTwo,Url).enterPassword(Password).clickLogin(TestUserIDTwo);
			uup.SupervisorCheck();
			TM.ChangeToMyTeamData().taskRefreshandGetCount().compareCountAsSupervisor();
			lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
			lp.HomePageVerification(TestUserIDOne);
			uup.checkSupervisor(TestUserIDOne);
			lp.enterUsername(TestUserIDTwo,Url).enterPassword(Password).clickLogin(TestUserIDTwo);
			uup.SupervisorCheck();
			new LoginPage(driver, test).enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
			test.log(LogStatus.INFO, "Logged into dashboard as user <font color= blue>'"+TestUserIDOne+"'</font> successsfully");
		}
		else {	
			TM.TaskmanagerConfig();
		}
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
		}

		
		
}

