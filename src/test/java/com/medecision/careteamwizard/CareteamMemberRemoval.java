package com.medecision.careteamwizard;

import java.util.Map; 

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.CareteamAssignandRemovePage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.ModifyPatientPage;
import com.medecision.pages.PatientRemovalPage;
import com.medecision.pages.ProgramPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;
import com.relevantcodes.extentreports.LogStatus;

public class CareteamMemberRemoval extends DriverInitialize {
	String TestUserIDOne, TestOrgOne, TestOrgTwo, Password, UserID, Url;
	String PatientID = null;
	String PID = null;
	String careteamname = null;

	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName = null;
		// testcaseDec = "<font color='blue'>";
		// + "LP001_LoginPage<br></font>"
		// + "CT001_CareTeam_Assign_Remove<br></font>";
	}

	@Test(groups = { "Smoke" }, dataProvider = "fetchData", alwaysRun = true, priority = 1)
	public void LP001_DashBoard_Login(Map<String, String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		TestUserIDOne = map.get("TestUserIDOne");
		Url = map.get("Url");
		lp.enterUsername(TestUserIDOne, Url).enterPassword(map.get("Password")).clickLogin(TestUserIDOne);
		PatientID = this.EnvBulkDats.PatientID.get(0);
	}


	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 2)
	public void Sprint38_Dash13077_Autoremovalofapatientfrompanelwhencareteamisremoved(Map<String,String> map) throws InterruptedException {
	String PatientID = this.EnvBulkDats.PatientID.get(0);
	boolean Careteamleadverify=false;
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
	TestDataVerification.PatientID_Verification(PatientID);
	CareteamAssignandRemovePage.enrollmentbtn();
	CareteamAssignandRemovePage.AssignNewCareTeamAndVerification(this.EnvBulkDats.ExistingCareTeam,Careteamleadverify);
	}else {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID);
		CareteamAssignandRemovePage.enrollmentbtn();
		CareteamAssignandRemovePage.AssignNewCareTeamAndVerification(this.EnvBulkDats.ExistingCareTeam,Careteamleadverify);
	}
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
	
	
	
	

}
