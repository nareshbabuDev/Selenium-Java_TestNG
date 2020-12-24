package com.medecision.program;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.HomePage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.ProgramPage;
import com.medecision.pages.SearchPatientPage;
import com.medecision.pages.ViewPatientPage;
import com.medecision.utils.DriverInitialize;

public class Program extends DriverInitialize {
	
	String UserID,Url;
	String PatientID;
	
	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName="Program";
		testcaseDec = "<font color='blue'>"
		+ "LP001_LoginPage<br></font>"
		+"DG002_SearchAPatient<br></font>"
		+ "PG002_AddProgram<br></font>";
	}
//
//	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 1)
//	public void LP001_DashBoard_Login(Map<String,String> map) throws InterruptedException {
//		LoginPage lp = new LoginPage(driver, test);
//		UserID =map.get("TestUserIDOne");
//		Url =map.get("Url");
//		lp.enterUsername(UserID,Url).enterPassword(map.get("Password")).clickLogin(UserID);
//		PatientID=this.EnvBulkDats.PatientID.get(0);
//	}
//	
//	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 2)
//	public void DG002_SearchAPatient(Map<String,String> map) throws InterruptedException {
//		HomePage Hp = new HomePage(driver,test);
//		Hp.clickDemographics();
//		SearchPatientPage Spp = new SearchPatientPage(driver, test);
//		Spp.clickSearchPatient();
//		Spp.selectPatient(PatientID);
//		ViewPatientPage Vpp = new ViewPatientPage(driver,test);
//		Vpp.clickEnrollment();
//	}
//	
//	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 3)
//	public void PG002_AddProgram(Map<String,String> map) throws InterruptedException {
//		ProgramPage Pg = new ProgramPage(driver, test);
//		Pg.clickProgramTab();
//		Pg. deleteProgram(map.get("parentName"),map.get("programName"),map.get("status"));
//		Pg.addProgram(map.get("parentName"),map.get("consentStatus"),map.get("consenterValue"),map.get("programName"),map.get("status"),map.get("endReason"));
//	}
	
	@AfterClass
	void StopReport() {
		CloseDriver();
	}

}