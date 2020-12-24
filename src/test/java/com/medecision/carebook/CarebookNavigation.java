package com.medecision.carebook;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.CarebookSearchPage;
import com.medecision.pages.HomePage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.MessageappPage;
import com.medecision.pages.ModifyPatientPage;
import com.medecision.pages.SearchPatientPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.pages.ViewPatientPage;
import com.medecision.utils.DriverInitialize;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CarebookNavigation extends DriverInitialize{

	String Url,TestOrgOne,TestOrgTwo,PopManagerUser,PowerUser,TestUserIDOne,Password,PatientID1,PatientID2,PatientID3,CT1,CT2;
	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName="CareBook Search";
	}
	//tc001
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true)
	public void T001_TestData_PatientConsentOrg_Verification(Map<String,String> map) throws Exception {
	LoginPage lp = new LoginPage(driver, test);
	TestUserIDOne =map.get("TestUserIDTwo");
	TestOrgOne=map.get("TestOrgOne");
	TestOrgTwo=map.get("TestOrgTwo");
	Url =map.get("Url");
	lp.enterUsername(TestUserIDOne,Url).enterPassword(map.get("Password")).clickLogin(TestUserIDOne);

	}
	@Test(groups = { "Smoke"} , dataProvider="fetchData")
	public void T008_CareBook_CareBookPageDetailsVerification(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	ModifyPatientPage ModifyPatientPage = new ModifyPatientPage(driver, test);
	CarebookSearchPage CarebookSearchPage = new CarebookSearchPage(driver,test);
	PatientID1=this.EnvBulkDats.PatientID.get(0);
	try {
	lp.HomePageVerification(TestUserIDOne);
	ModifyPatientPage.clickDemographics();
	CarebookSearchPage.CareBookDetailsVerification(PatientID1);
	}
	catch(Exception e) {
		Refresh();
		lp.enterUsername(TestUserIDOne,Url).enterPassword(map.get("Password")).clickLogin(TestUserIDOne);
	}
	}
	
	@AfterClass
	void StopReport() {
		CloseDriver();
	}
	
}
