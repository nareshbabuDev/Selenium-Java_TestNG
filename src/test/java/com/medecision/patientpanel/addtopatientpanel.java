package com.medecision.patientpanel;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.AddpatienttopanelPage;
import com.medecision.pages.HomePage;
import com.medecision.pages.HomePageChartPage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.ModifyPatientPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;

public class addtopatientpanel   extends DriverInitialize{

	String TestUserIDOne,Url,TestOrgOne,TestOrgTwo;
	String PatientID=null;
	String PID=null;
	String Password;

	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName=null;

	}	
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 1)
	public void LP001_DashBoard_Login(Map<String,String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
		TestUserIDOne =map.get("TestUserIDOne");
		TestOrgOne=map.get("TestOrgOne");
		TestOrgTwo=map.get("TestOrgTwo");
		Url =map.get("Url");
		Password = map.get("Password");
//		byte[] DecodedPass = Base64.decodeBase64(map.get("Password"));
//		Password = new String(DecodedPass);
		lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun = true, priority = 2)
	public void TC012_Add_Patient_To_Panel(Map<String,String> map) throws Exception {
	String PatientID1 = this.EnvBulkDats.PatientID.get(0);
	TestUserIDOne =map.get("TestUserIDOne");
	LoginPage lp = new LoginPage(driver, test);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	TestDataVerification TestDataVerification = new TestDataVerification(driver,test);
	AddpatienttopanelPage AddpatienttopanelPage = new AddpatienttopanelPage(driver,test);
	try {
	lp.HomePageVerification(TestUserIDOne);
	AddpatienttopanelPage.VerifyPatientPanelIsNotLoading().patientsearch(PatientID1);
	ModifyPatientPage.clickDemographics();
	TestDataVerification.PatientID_Verification(PatientID1);
	AddpatienttopanelPage.AddtoPP().VerifyPatientShownInPP(PatientID1);
	}
	catch(Exception e) {
		Refresh();
		lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}
	}
	
	
	
}


 


