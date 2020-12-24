package com.medecision.datavalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.CareteamAssignandRemovePage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;

public class TestDataValidation extends DriverInitialize{
	String Url,TestOrgOne,TestOrgTwo,PopManagerUser,PowerUser,TestUserIDOne,Password,PatientID1,PatientID2,PatientID3,CT1,CT2;
	ArrayList<String> EmailIDs;
	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName="Test Data Current State Validation";
	}
	
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true)
	public void T001_TestData_PatientConsentOrg_Verification(Map<String,String> map) throws Exception {
	LoginPage lp = new LoginPage(driver, test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	TestUserIDOne =map.get("TestUserIDOne");
	TestOrgOne=map.get("TestOrgOne");
	TestOrgTwo=map.get("TestOrgTwo");
	Url =map.get("Url");
	lp.enterUsername(TestUserIDOne,Url).enterPassword(map.get("Password")).clickLogin(TestUserIDOne);
	for (String PatientID : this.EnvBulkDats.PatientID) {
	TestDataVerification.PatientID_Verification(PatientID).Org_Verification(TestOrgOne,TestOrgTwo).Provider_Verification().CareTeamTab().CareTeam_Delete(PatientID,this.EnvBulkDats.ExistingCareTeam);
	}
	}
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true)
	public void T002_TestData_CareTeam_Verification(Map<String,String> map) throws Exception {
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
//	TestDataVerification.closeCT().HomePageClick();
	}
	@AfterClass
	void StopReport() {
		CloseDriver();
	}
}
