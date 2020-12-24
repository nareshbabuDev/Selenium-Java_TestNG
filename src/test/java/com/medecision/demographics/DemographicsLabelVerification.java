package com.medecision.demographics;

import java.util.Map;

import org.testng.annotations.Test;

import com.medecision.pages.DemographicslabelPage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;

public class DemographicsLabelVerification extends DriverInitialize {
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

	@Test(groups = { "Smoke" }, dataProvider = "fetchData", alwaysRun = true, priority = 2)
	public void DemographicsLabelVerification(Map<String, String> map) throws InterruptedException {
		String PatientID = this.EnvBulkDats.PatientID.get(0);
		TestDataVerification TestDataVerification = new TestDataVerification(driver, test);
		String PatientUserName =map.get("PatientUserName");
		String County= map.get("County");
		String IdentifiedGender =map.get("IdentifiedGender");
		String PreferredName =map.get("PreferredName");
		DemographicslabelPage dlp = new DemographicslabelPage(driver, test);
		dlp.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID);
		dlp.CollectLabels(PatientUserName,County,IdentifiedGender,PreferredName).ModifyPage().CollectModifyLabels(PatientUserName,County,IdentifiedGender,PreferredName).CompareLabels();
	}
}