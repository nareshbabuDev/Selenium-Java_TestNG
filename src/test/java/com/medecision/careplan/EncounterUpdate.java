package com.medecision.careplan;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.EncounterPage;
import com.medecision.pages.LoginPage;
import com.medecision.utils.DriverInitialize;

public class EncounterUpdate extends DriverInitialize{
	
	String UserID,Url;
	String PatientID=null;
	String PID=null;
	
	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName=null;
//		testcaseDec = "<font color='blue'>"
//		+ "LP001 LoginPage Verification<br>"
//		</font>";
	}
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 1)
	public void LP001_DashBoard_Login(Map<String,String> map) throws InterruptedException {
		LoginPage lp = new LoginPage(driver, test);
		PatientID=this.EnvBulkDats.PatientID.get(0);
		UserID =map.get("TestUserIDOne");
		Url =map.get("Url");
		lp.enterUsername(UserID,Url).enterPassword(map.get("Password")).clickLogin(UserID);
	
	}
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 2)
	public void EN001_Encounter_Update(Map<String,String> map) throws Exception {
//	PID = this.EnvBulkDats.PatientID.get(0);
//	LoginPage lp = new LoginPage(driver, test);
//	EncounterPage Ep = new EncounterPage(driver, test);
//	lp.HomePageVerification(UserID);
//	Ep.CarePlanAppNavigation();
//	Thread.sleep(1000);
//	Ep.PatientSearch(PID);
//	Thread.sleep(1000);
//	Ep.EncounterInformation();
//	Thread.sleep(1000);
//	Ep.UpdateEncounter(PID);
//	Thread.sleep(1000);
	}
	
	@AfterClass
	void StopReport() {
		CloseDriver();
	}
}
