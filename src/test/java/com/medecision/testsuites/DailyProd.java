package com.medecision.testsuites;

import java.awt.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.adminapp.UserUpdate;
import com.medecision.pages.AlertVerificationPage;
import com.medecision.pages.CarebookSearchPage;
import com.medecision.pages.CareteamAssignandRemovePage;
import com.medecision.pages.EncounterPage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.MessageappPage;
import com.medecision.pages.ModifyPatientPage;
import com.medecision.pages.PopulationManagerPage;
import com.medecision.pages.ProgramPage;
import com.medecision.pages.TaskManagerPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.pages.UserPasswordUpdatePage;
import com.medecision.pages.UserUpdatePage;
import com.medecision.utils.DatabaseConfig;
import com.medecision.utils.DriverInitialize;
import com.medecision.utils.EnvJsonData;
import com.medecision.utils.NotificationAudit;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class DailyProd extends DriverInitialize{
	String Url,TestOrgOne,TestOrgTwo,PopManagerUser,PowerUser,TestUserIDOne,TestUserIDTwo,Password,PatientID1,PatientID2,PatientID3,CT1,CT2;
	//tc001
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",alwaysRun=true)
	public void C_TC001_TestData_PatientConsentOrg_Verification(Map<String,String> map) throws Exception {
	LoginPage lp = new LoginPage(driver, test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	TestUserIDOne =map.get("TestUserIDOne");
	TestOrgOne=map.get("TestOrgOne");
	TestOrgTwo=map.get("TestOrgTwo");
	Url =map.get("Url");
	Password = map.get("Password");
	lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver,test);
	ModifyPatientPage.clickDemographics();
	for (String PatientID : this.EnvBulkDats.PatientID) {
	TestDataVerification.PatientID_Verification(PatientID).Org_Verification(TestOrgOne,TestOrgTwo).Provider_Verification().CareTeamTab().CareTeam_Delete(PatientID,this.EnvBulkDats.ExistingCareTeam);
	}
	}
//	//tc002
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification"})
	public void C_TC002_TestData_CareTeam_Verification(Map<String,String> map) throws Exception {
	ArrayList<String> EmailIDs = new ArrayList<String>();
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	PatientID1=this.EnvBulkDats.PatientID.get(0);
	TestUserIDOne =map.get("TestUserIDOne");
	Url =map.get("Url");
	EmailIDs.add(map.get("TestUserIDOne"));
	EmailIDs.add(map.get("TestUserIDTwo"));
	TestDataVerification.PatientID_Verification(PatientID1);
	CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver,test);
	CareteamAssignandRemovePage.enrollmentbtn().CareTeamPageNavigation();
	for (String ExistingCareTeam : this.EnvBulkDats.ExistingCareTeam) {
	TestDataVerification.CareTeam_Verification(ExistingCareTeam,map.get("TestOrgOne"),map.get("TestOrgTwo"),EmailIDs);
	}
	TestDataVerification.CloseEnrollmentNavigatetoHome();
	}
	//tc003
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC003_TaskManager_OverDueCount_Verification(Map<String,String> map) throws InterruptedException {
	TaskManagerPage TM = new TaskManagerPage(driver, test);
	LoginPage lp = new LoginPage(driver, test);
	String TaskManagerConfig = map.get("TaskManagerConfig");
	try {
	if(TaskManagerConfig.toUpperCase().equals("Y")) {
	lp.HomePageVerification(TestUserIDOne);
	TM.OverDueCountVerification();
	}
	else {
		TM.TaskmanagerConfig();
	}
	}catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	
	}
	//tc004
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC004_TaskManager_NewTask_Creation(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	TaskManagerPage TM = new TaskManagerPage(driver, test);
	String TaskManagerConfig = map.get("TaskManagerConfig");
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	CarebookSearchPage CarebookSearchPage = new CarebookSearchPage(driver,test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	PatientID1=this.EnvBulkDats.PatientID.get(0);
	try {
	if(TaskManagerConfig.toUpperCase().equals("Y")) {
		lp.HomePageVerification(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		CarebookSearchPage.PatientID_Verification(PatientID1);
		ArrayList<String> LastFirstName=CarebookSearchPage.getFNLN();
		TestDataVerification.NavigatetoHome();
		TM.TaskManagerNavigation().CreateNewTask(LastFirstName.get(0),PatientID1);
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
	//tc005
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC005_AdminApp_UserUpdate(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	UserUpdatePage UserUpdatePage = new UserUpdatePage(driver,test);
	try {
		lp.HomePageVerification(TestUserIDOne);
		boolean verify = lp.HomePageClick();
		if(verify) {
			UserUpdatePage.UpdateUserAddress1(TestUserIDOne);
		}else {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
			UserUpdatePage.UpdateUserAddress1(TestUserIDOne);
		}
		
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}
	//tc006
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC006_CareTeamWizard_AssignNewCareTeam(Map<String,String> map) throws InterruptedException {
	boolean Careteamleadverify=false;
	PatientID1 = this.EnvBulkDats.PatientID.get(0);
	CT1 = this.EnvBulkDats.ExistingCareTeam.get(0);
	String CareTeamTest = map.get("CareTeamTest");
	LoginPage lp = new LoginPage(driver, test);
	CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	try {
	if(CareTeamTest.toUpperCase().equals("Y")) {
	lp.HomePageVerification(TestUserIDOne);
	boolean verify = lp.HomePageClick();
	if(verify) {
	ModifyPatientPage.clickDemographics();
	TestDataVerification.PatientID_Verification(PatientID1);
	CareteamAssignandRemovePage.enrollmentbtn();
	CareteamAssignandRemovePage.AssignNewCareTeamAndVerification(this.EnvBulkDats.ExistingCareTeam,Careteamleadverify);
	}else {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID1);
		CareteamAssignandRemovePage.enrollmentbtn();
		CareteamAssignandRemovePage.AssignNewCareTeamAndVerification(this.EnvBulkDats.ExistingCareTeam,Careteamleadverify);
	}
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
	
	//tc007
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC007_DemographicsApp_AddressUpdate(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	PatientID1=this.EnvBulkDats.PatientID.get(0);
	try {
		lp.HomePageVerification(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID1);
		ModifyPatientPage.PatientAddress1Update(PatientID1);
	
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}
	//tc008
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC008_CareBook_CareBookPageDetailsVerification(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	CarebookSearchPage CarebookSearchPage = new CarebookSearchPage(driver,test);
	PatientID1=this.EnvBulkDats.PatientID.get(0);
	try {
	lp.HomePageVerification(TestUserIDOne);
	ModifyPatientPage.clickDemographics();
	CarebookSearchPage.CareBookDetailsVerification(PatientID1);
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}
	//tc009
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC009_MessageApp_MesageSentAndReceiveFunctionality(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	MessageappPage MessageappPage = new MessageappPage(driver, test);
	try {
	lp.HomePageVerification(TestUserIDOne);
	MessageappPage.MesageSentAndReceiveFunctionality();
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}
	//tc010
		@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
		public void C_TC010_CarePlan_EncounterUpdate(Map<String,String> map) throws InterruptedException {
		PatientID1 = this.EnvBulkDats.PatientID.get(0);
		TestOrgOne = map.get("TestOrgOne");
		LoginPage lp = new LoginPage(driver, test);
		EncounterPage EncounterPage = new EncounterPage(driver, test);
		try {
		lp.HomePageVerification(TestUserIDOne);
		EncounterPage.EncounterUpdateVerification(PatientID1,TestOrgOne);
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
	}
	//tc11
		@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
		public void C_TC011_Program_AddNewProgram(Map<String,String> map) throws InterruptedException {
		PatientID1 = this.EnvBulkDats.PatientID.get(0);
		LoginPage lp = new LoginPage(driver, test);
		CareteamAssignandRemovePage CareteamAssignandRemovePage = new CareteamAssignandRemovePage(driver, test);
		ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
		TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
		ProgramPage ProgramPage = new ProgramPage(driver, test);
		try {
		lp.HomePageVerification(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID1);
		CareteamAssignandRemovePage.enrollmentbtn();
		ProgramPage.AddNewProgram(PatientID1);
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
	}	
	//tc12
		@Test(groups = { "DailyProd"} , dataProvider="fetchData",dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
		public void C_TC012_AlertApp_TodaysDateAlertVerification(Map<String,String> map) throws InterruptedException {
		LoginPage lp = new LoginPage(driver, test);
		AlertVerificationPage AlertVerificationPage = new AlertVerificationPage(driver, test);
		String AlertAppConfig = map.get("AlertAppConfig");
		try {
		if(AlertAppConfig.toUpperCase().equals("Y")) {
			lp.HomePageVerification(TestUserIDOne);
			AlertVerificationPage.AlertVerify();
		}
		else {	
			AlertVerificationPage.AlertAppConfig();
		}
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
		}
	//tc13
	@Test(groups = { "DailyProd"} , dataProvider="fetchData", dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
	public void C_TC013_PopulationManager_SOP_Reports(Map<String,String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		String Secondwindow,Iframe,PopManagerConfig;
		PopManagerConfig =map.get("PopManagerConfig").toUpperCase();
		PopulationManagerPage PopulationManagerPage = new PopulationManagerPage(driver,test);
		UserPasswordUpdatePage UserPasswordUpdatePage = new UserPasswordUpdatePage(driver, test);
		try {
		if(PopManagerConfig.equals("Y")) {
		Iframe =map.get("PopManagerIframe").toUpperCase();
		Secondwindow =map.get("PopManagerSecondWindow").toUpperCase();
		TestUserIDTwo =map.get("TestUserIDTwo");
		Refresh();
		lp.enterUsername(TestUserIDTwo,Url).enterPassword(Password).clickLogin(TestUserIDTwo);
		PopulationManagerPage.PopManagerSOP_Reports(TestUserIDOne,Password,Secondwindow,Iframe);
		UserPasswordUpdatePage.clickLogout().clickYes();
		}
		else {
			PopulationManagerPage.PopManagerAppConfig();
		}
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
	}
	//tc14
    @Test(groups = { "DailyProd" }, dataProvider = "fetchData", dependsOnMethods = {"C_TC001_TestData_PatientConsentOrg_Verification","C_TC002_TestData_CareTeam_Verification"})
    public void C_TC014_NotificationMonitoring_NotificationFailureVerification(Map<String, String> map) throws ClassNotFoundException, SQLException {
        String sqlip,schemaname,sqluser,sqlpass;
        sqlip = map.get("sqlIp");
        schemaname ="gsialerting";
        sqluser = map.get("sqlUser");
        sqlpass =map.get("sqlPass");
    	DatabaseConfig NotificationVerify = new DatabaseConfig(test);
        try {
        	NotificationVerify.NotificationFailureVerification(sqlip, schemaname, sqluser, sqlpass); 
        } catch (Exception e) {
            e.printStackTrace();
            NotificationVerify.DbConnectionError();
            Assert.fail();
        }
    }

}
