package com.medecision.alertapp;
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

import com.medecision.pages.AlertVerificationPage;
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


public class AlertVerification extends DriverInitialize{

	String username,Url;
	String PatientID=null;
	String Address1=null;
	String UserID;
	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName="Alert app verification";
	}

	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true)
	public void LP001_DashBoard_Login(Map<String,String> map) throws InterruptedException {
		LoginPage lp = new LoginPage(driver, test);
		UserID =map.get("TestUserIDOne");
		Url =map.get("Url");
		lp.enterUsername(UserID,Url).enterPassword(map.get("Password")).clickLogin(UserID);
		PatientID=this.EnvBulkDats.PatientID.get(0);
	
	}
	
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 2)
	public void AV001_AlertVerification(Map<String,String> map) throws Exception {
//		LoginPage lp = new LoginPage(driver, test);
//		lp.HomePageVerification(UserID);
//		AlertVerificationPage av = new AlertVerificationPage(driver, test);
//		av.clickAlertApp();
//		av.TodayDate();
//		av.verifyAlert();
	
	}
	
	@AfterClass
	void StopReport() {
		CloseDriver();
	}
	
}
