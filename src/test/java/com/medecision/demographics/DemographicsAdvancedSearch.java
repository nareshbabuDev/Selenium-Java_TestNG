package com.medecision.demographics;

import java.util.Map;

import org.testng.annotations.Test;

import com.medecision.pages.*;
import com.medecision.utils.DriverInitialize;

public class DemographicsAdvancedSearch extends DriverInitialize {
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
	public void DemographicsPatientAdvancedSearch(Map<String, String> map) throws InterruptedException {
		String PatientID = this.EnvBulkDats.PatientID.get(0);
		ModifyPatientPage ModifyPatientPage= new ModifyPatientPage(driver,test);
		SearchPatientPage SearchPatientPage= new SearchPatientPage(driver,test);
		TestDataVerification TestDataVerification = new TestDataVerification(driver, test);
		DemographicsAdvancedSearchPage dasp = new DemographicsAdvancedSearchPage(driver, test);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.enterPatientID(PatientID);
		SearchPatientPage.clickSearchPatient();
		dasp.GetPatientDetails().SearchwithLastName(PatientID).SearchwithFirstName(PatientID).SearchwithMiddleName(PatientID).SearchwithDOB(PatientID).SearchwithPatientDetails(PatientID);
	}
}