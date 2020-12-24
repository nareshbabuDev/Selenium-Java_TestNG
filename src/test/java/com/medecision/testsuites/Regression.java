package com.medecision.testsuites;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.testng.annotations.Test;
import com.medecision.pages.*;
import com.medecision.utils.*;
import com.relevantcodes.extentreports.LogStatus;


public class Regression extends DriverInitialize{
	String Url,TestOrgOne,TestOrgTwo,PopManagerUser,PowerUser,TestUserIDOne,TestUserIDTwo,Password,PatientID1,PatientID2,PatientID3,CT1,CT2;
	//tc001
	@Test(groups = { "Regression"} , dataProvider="fetchData",priority = 1,alwaysRun=true)
	public void TC01_Login_DashboardLogin(Map<String,String> map) throws Exception {
	LoginPage lp = new LoginPage(driver, test);
	TestUserIDOne =map.get("TestUserIDOne");
	TestOrgOne=map.get("TestOrgOne");
	TestOrgTwo=map.get("TestOrgTwo");
	Url =map.get("Url");
	Password = map.get("Password");
	lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	
	}
	//tc002
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 2)
	public void CS_TC02_TaskManager_OverDueCount_Verification(Map<String,String> map) throws InterruptedException {
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
	//tc003
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 3)
	public void CS_TC03_TaskManager_NewTask_Creation(Map<String,String> map) throws InterruptedException {
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
	//tc004
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 4)
	public void CS_TC04_AdminApp_UserUpdate(Map<String,String> map) throws InterruptedException {
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
	//tc005
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 5)
	public void CS_TC05_CareTeamWizard_AssignNewCareTeam(Map<String,String> map) throws InterruptedException {
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
	
	//tc006
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 6)
	public void CS_TC06_DemographicsApp_AddressUpdate(Map<String,String> map) throws InterruptedException {
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
	//tc007
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 7)
	public void CS_TC07_CareBook_CareBookPageDetailsVerification(Map<String,String> map) throws InterruptedException {
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
	//tc008
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 8)
	public void CS_TC08_MessageApp_MesageSentAndReceiveFunctionality(Map<String,String> map) throws InterruptedException {
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
	//tc009
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 9)
	public void CS_TC09_CarePlan_EncounterUpdate(Map<String,String> map) throws InterruptedException {
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
	//tc010
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 10)
	public void CS_TC10_Program_AddNewProgram(Map<String,String> map) throws InterruptedException {
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
	//tc11
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 11)
	public void CS_TC11_AlertApp_TodaysDateAlertVerification(Map<String,String> map) throws InterruptedException {
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
	//tc12
	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 12)
	public void CS_TC12_Add_Patient_To_Panel(Map<String,String> map) throws Exception {
	PatientID1 = this.EnvBulkDats.PatientID.get(0);
	LoginPage lp = new LoginPage(driver, test);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	AddpatienttopanelPage AddpatienttopanelPage = new AddpatienttopanelPage(driver,test);
	CarebookSearchPage carebookSearchPage = new CarebookSearchPage(driver,test);
	try {
	lp.HomePageVerification(TestUserIDOne);
	AddpatienttopanelPage.VerifyPatientPanelIsNotLoading().patientsearch(PatientID1);
	ModifyPatientPage.clickDemographics();
	TestDataVerification.PatientID_Verification(PatientID1);
	AddpatienttopanelPage.AddtoPP().VerifyPatientShownInPP(PatientID1).patientremovalPP(PatientID1);
	carebookSearchPage.searchByID(PatientID1).AddPatientFromCarebook().SwitchtoDefault();
	AddpatienttopanelPage.VerifyPatientShownInPP(PatientID1).patientremovalPP(PatientID1);
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}


	//tc13
	@Test(groups = { "Regression"} , dataProvider="fetchData", dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 13)
	public void CS_TC13_PopulationManager_SOP_Reports(Map<String,String> map) throws Exception {
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

//	//tc14
	@Test(groups = { "Regression"} , dataProvider="fetchData", dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 14)
	public void S_TC14_PreferencesPage_PasswordUpdate(Map<String,String> map) throws Exception {
	LoginPage lp = new LoginPage(driver, test);	
	String TestPass="Test123#";
	UserPasswordUpdatePage UserPasswordUpdatePage = new UserPasswordUpdatePage(driver, test);
	try {
		lp.HomePageVerification(TestUserIDOne);
		UserPasswordUpdatePage.UserPassUpdate(TestPass);
		Refresh();
		lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLoginWrongPassverify();
		Refresh();
		lp.enterUsername(TestUserIDOne,Url).enterPassword(TestPass).clickLogin(TestUserIDOne);
		UserPasswordUpdatePage.UserPassUpdate(Password);
		Refresh();
		lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}
    //tc15
    @Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 15)
    public void S_TC15_PreferencesPage_AlertPreferences(Map<String,String> map) throws InterruptedException {
    LoginPage lp = new LoginPage(driver, test);
    PreferencesPage preferencepage = new PreferencesPage(driver, test);
    try {
        lp.HomePageVerification(TestUserIDOne);
        preferencepage.gotoAlertPreferncetab();
        preferencepage.getalertpreferencename();
        preferencepage.closepreference();
        try
        {
            lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
            lp.HomePageVerification(TestUserIDOne);
            preferencepage.gotoAlertPreferncetab();
            preferencepage.alertpreferenceuncheckthebox();
            preferencepage.alertpreferencecheckthebox();
            preferencepage.closepreference();
        }catch (Exception e) {
            Refresh();
            lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
        }
    }
    catch(Exception e) {
        Refresh();
        lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
    }
    }
    //tc016
    @Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 16)
	public void S_TC16_PreferencesPage_ReferenceCenterandTrainingVerification(Map<String,String> map) throws Exception {
    	LoginPage lp = new LoginPage(driver, test);
    	try {
		lp.HomePageVerification(TestUserIDOne);
		new PreferencesPage(driver, test).Resourcecenterverification().Trainingverification();
    	}catch (Exception e) {
            Refresh();
            lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
        }
	}
    @Test(groups = { "Smoke" }, dataProvider = "fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"}, priority = 17)
	public void S_TC17_DemographicsPage_PatientAdvancedSearch(Map<String, String> map) throws InterruptedException {
		String PatientID = this.EnvBulkDats.PatientID.get(0);
		ModifyPatientPage ModifyPatientPage= new ModifyPatientPage(driver,test);
		SearchPatientPage SearchPatientPage= new SearchPatientPage(driver,test);
		TestDataVerification TestDataVerification = new TestDataVerification(driver, test);
		DemographicsAdvancedSearchPage dasp = new DemographicsAdvancedSearchPage(driver, test);
		LoginPage lp = new LoginPage(driver, test);
		try {
		lp.HomePageVerification(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.enterPatientID(PatientID);
		SearchPatientPage.clickSearchPatient();
		dasp.GetPatientDetails().SearchwithLastName(PatientID).SearchwithFirstName(PatientID).SearchwithMiddleName(PatientID).SearchwithDOB(PatientID).SearchwithPatientDetails(PatientID);
		}catch (Exception e) {
            Refresh();
            lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
        }
		}
    //tc18
    @Test(groups = { "Regression" }, dataProvider = "fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"}, priority = 18)
	public void S_TC18_Demographics_LabelVerification(Map<String, String> map) throws InterruptedException {
		String PatientID = this.EnvBulkDats.PatientID.get(0);
		String County=map.get("County");
		String PatientUserName=map.get("PatientUserName");
		String IdentifiedGender =map.get("IdentifiedGender");
		String PreferredName =map.get("PreferredName");
		TestDataVerification TestDataVerification = new TestDataVerification(driver, test);
		DemographicslabelPage dlp = new DemographicslabelPage(driver, test);
		LoginPage lp = new LoginPage(driver, test);
		try {
		lp.HomePageVerification(TestUserIDOne);
		dlp.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID);
		dlp.CollectLabels(PatientUserName,County,IdentifiedGender,PreferredName).ModifyPage().CollectModifyLabels(PatientUserName,County,IdentifiedGender,PreferredName).CompareLabels();
		}catch (Exception e) {
            Refresh();
            lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
        }
	}
  	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 19)
  	public void S_TC19_VerifyCMAOrgChangeAndReflectionOfPatient(Map<String,String> map) throws Exception {
  		String PatientID2 = this.EnvBulkDats.PatientID.get(1);
  		TestOrgOne=map.get("TestOrgOne");
  		TestOrgTwo=map.get("TestOrgTwo");
  		String TestUserIDThree =map.get("TestUserIDThree");
  		LoginPage lp = new LoginPage(driver, test);
  		OrgWizardPage OrgWizardPage = new OrgWizardPage(driver, test);
  		try {
  			if(!TestUserIDThree.equals("N")) {
  				lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
  				lp.HomePageVerification(TestUserIDOne);
  				OrgWizardPage.CMAorgChangeToSecondOrg(PatientID2,TestOrgOne,TestOrgTwo ).VerifyAfterOrgRemoved(PatientID2,TestOrgOne,TestOrgTwo,TestUserIDThree,Url,Password );
  				new LoginPage(driver, test).enterUsername(TestUserIDThree,Url).enterPassword(Password).clickLogin(TestUserIDThree);
  				OrgWizardPage.VerifyPatientSearchable(PatientID2,TestOrgTwo ,TestOrgOne).OrgChangeToPrimary(PatientID2,TestOrgOne,TestOrgTwo );
  			}
  			else {
  				test.log(LogStatus.SKIP, "Consent user is not provided for this environment");
  			}
  			}
  		catch(Exception e) {
  			Refresh();
  			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
  		}
  	}
  	//tc020
  	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 20)
  	public void S_TC20_VerifyProviderAccessAddAndRemove(Map<String,String> map) throws Exception {
  		String PatientID2 = this.EnvBulkDats.PatientID.get(1);
  		TestOrgOne=map.get("TestOrgOne");
  		TestOrgTwo=map.get("TestOrgTwo");
  		String TestUserIDThree =map.get("TestUserIDThree");
  		LoginPage lp = new LoginPage(driver, test);
  		ProviderAccessPage ProviderAccessPage = new ProviderAccessPage(driver, test);
  		try {
  			if(!TestUserIDThree.equals("N")) {
  				lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
  				lp.HomePageVerification(TestUserIDOne);
  				ProviderAccessPage.AddOrgInProviderAccess(PatientID2,TestOrgTwo );
  				new LoginPage(driver, test).enterUsername(TestUserIDThree,Url).enterPassword(Password).clickLogin(TestUserIDThree);
  				lp.HomePageVerification(TestUserIDOne);
  				ProviderAccessPage.VerifyPatientSearchable(PatientID2 ,TestOrgTwo);
  				ProviderAccessPage.OrgRemovedFromProviderAccess(PatientID2,TestOrgTwo );
  				ProviderAccessPage.VerifyAfterOrgRemoved(PatientID2,TestOrgTwo );
  				Refresh();
  	  			lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
  			}
  			else {
  				test.log(LogStatus.SKIP, "Consent user is not provided for this environment");
  			}
  			}
  		catch(Exception e) {
  			Refresh();
  			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
  		}
  		}


////  	//tc019
  	@Test(groups = { "Regression"} , dataProvider="fetchData",dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 21)
	public void S_TC21_Demographics_PatientExternalID_Verification(Map<String,String> map) throws InterruptedException {
	String PatientID = this.EnvBulkDats.PatientID.get(2);
	String ExternalIDTest = map.get("ExternalIDTest");
	String ExternalID=map.get("ExternalID");
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	PatientExternalIdPage poid=new PatientExternalIdPage(driver,test);
	LoginPage lp = new LoginPage(driver, test);
	try {
	if(ExternalIDTest.toUpperCase().equals("Y")) {
	lp.HomePageVerification(TestUserIDOne);
	poid.clickDemographics();
	TestDataVerification.PatientID_Verification(PatientID);
	poid.ModifyPage().AddPatientOtherID(ExternalID).CloseExternalID();	
	}
	else {
		poid.NoExternalID();
	}
	}catch(Exception e) {
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
  	}
  	
	//tc022
	  @Test(groups = { "Regression" }, dataProvider = "fetchData", dependsOnMethods = {"TC01_Login_DashboardLogin"},priority = 22)
	  public void S_TC22_NotificationMonitoring_NotificationFailureVerification(Map<String, String> map) throws ClassNotFoundException, SQLException {
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
	  }
	  }


}
