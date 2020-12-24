package com.medecision.messageapp;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.LoginPage;
import com.medecision.pages.MessageappPage;
import com.medecision.pages.SearchPatientPage;
import com.medecision.utils.DriverInitialize;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Messageapp   extends DriverInitialize{

	String UserID,Url;
	String PatientID=null;
	String address=null;

	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName=null;
//		testcaseDec = "<font color='blue'>"
//		+ "LP001_LoginPage<br></font>"
//		+ "MA002_MessageApp<br></font>";
	}	
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 1)
	public void LP001_DashBoard_Login(Map<String,String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		UserID =map.get("TestUserIDOne");
		Url =map.get("Url");
		lp.enterUsername(UserID,Url).enterPassword(map.get("Password")).clickLogin(UserID);
		PatientID=this.EnvBulkDats.PatientID.get(0);
	}
	
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 2)
	public void MA002_MessageApp(Map<String,String> map) throws Exception {
//	address=map.get("toaddress");
//	LoginPage lp = new LoginPage(driver, test);
//	lp.HomePageVerification(UserID);
//	new MessageappPage(driver,test).msgappicon().msgappverification().msgapptoaddress(address).msgappsubject().sendbtn().refreshbtn().mailverfication();
//	
	}
	
	@AfterClass
	void StopReport() {
		CloseDriver();
	}
	
}
