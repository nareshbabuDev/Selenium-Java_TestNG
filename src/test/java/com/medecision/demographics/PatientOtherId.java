package com.medecision.demographics;

import java.util.Map;

import org.testng.annotations.Test;

import com.medecision.pages.PatientExternalIdPage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;

public class PatientOtherId extends DriverInitialize {
	String TestUserIDOne, TestOrgOne, TestOrgTwo, Password, UserID, Url;
	String PatientID = null;
	String PID = null;
	String careteamname = null;

	@Test(groups = { "Smoke" }, dataProvider = "fetchData", alwaysRun = true, priority = 1)
	public void LP001_DashBoard_Login(Map<String, String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		TestUserIDOne = map.get("TestUserIDOne");
		Url = map.get("Url");
		lp.enterUsername(TestUserIDOne, Url).enterPassword(map.get("Password")).clickLogin(TestUserIDOne);
		PatientID = this.EnvBulkDats.PatientID.get(0);
	}

	
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 2)
	public void PatientOtherId(Map<String,String> map) throws InterruptedException {
	String PatientID = this.EnvBulkDats.PatientID.get(0);
	String ExternalIDTest = map.get("ExternalIDTest");
	String ExternalID=map.get("ExternalID");
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	PatientExternalIdPage poid=new PatientExternalIdPage(driver,test);
	if(ExternalIDTest.toUpperCase().equals("Y")) {
	poid.clickDemographics();
	TestDataVerification.PatientID_Verification(PatientID);
	poid.ModifyPage().AddPatientOtherID(ExternalID);
	}
	else {
		poid.NoExternalID();
	}
	
}
}
