package com.medecision.testsuites;

import java.util.Map;

import org.testng.annotations.Test;

import com.medecision.pages.LoginPage;
import com.medecision.pages.PatientRemovalPage;
import com.medecision.utils.DriverInitialize;

public class QuickScripts extends DriverInitialize {
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
	@Test(groups = { "Regression"} , dataProvider="fetchData", dependsOnMethods = {"TC001_TestData_PatientConsentOrg_Verification","TC002_TestData_CareTeam_Verification"})
	public void TC002_PatientPanel_RemoveAllPatientsFromPanel(Map<String, String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		lp.HomePageVerification(TestUserIDOne);
		try {
			new PatientRemovalPage(driver, test).Removepatients();
		} catch (Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne, Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
	}
}
