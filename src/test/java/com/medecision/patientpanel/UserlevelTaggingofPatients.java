package com.medecision.patientpanel;
import java.util.Map;

import org.testng.annotations.Test;

import com.medecision.pages.AddpatienttopanelPage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.ModifyPatientPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;


public class UserlevelTaggingofPatients extends DriverInitialize{
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
	}	
	
	@Test(groups = { "Smoke" }, dataProvider = "fetchData", alwaysRun = true, priority = 2)
	public void AddUserLevelTaggingofPatients(Map<String, String> map) throws Exception {
		PatientID = this.EnvBulkDats.PatientID.get(0);
		TestDataVerification TestDataVerification = new TestDataVerification(driver, test);
		ModifyPatientPage ModifyPatientPage= new ModifyPatientPage(driver,test);
		AddpatienttopanelPage APPP=	new AddpatienttopanelPage(driver,test);
		APPP.patientsearch(PatientID);
		ModifyPatientPage.clickDemographics();
		TestDataVerification.PatientID_Verification(PatientID);
		APPP.AddtoPP().VerifyPatientShownInPP(PatientID).Addtags(PatientID).Verifytagsadded(PatientID).Removetags(PatientID).Verifytagsremoved(PatientID);
	}
	
	
	
	
	
	
	

}
