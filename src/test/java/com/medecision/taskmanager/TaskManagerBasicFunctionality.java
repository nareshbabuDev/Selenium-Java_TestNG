package com.medecision.taskmanager;

import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.LoginPage;
import com.medecision.pages.TaskManagerPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;

public class TaskManagerBasicFunctionality extends DriverInitialize{
	
	String UserID,Url,PatientID;
	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName="Task Manager Basic Functionality";
//		testcaseDec = null;
		/**
		"<font color='blue'>"
		+ "LP001 LoginPage Verification<br>"
		+ "TM001 Verify Over Due Task Count In Summary DashBoard And TaskManager Icon<br>"
		+ "TM002 Verify Created Task Reflected In TaskManager</font>";**/
	}
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true)
	public void TC001_TestData_Verification(Map<String,String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		UserID =map.get("TestUserIDOne");
		Url =map.get("Url");
		lp.enterUsername(UserID,Url).enterPassword(map.get("Password")).clickLogin(UserID);
		PatientID=this.EnvBulkDats.PatientID.get(0);
	
	}
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true)
	public void TM001_TaskManager_OverDueCount_Verification(Map<String,String> map) {
	LoginPage lp = new LoginPage(driver, test);
	TaskManagerPage TM = new TaskManagerPage(driver, test);
	TM.OverDueCountVerification();
	
	}
	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true)
	public void TM002_TaskManager_NewTask_Creation(Map<String,String> map) throws InterruptedException {
	LoginPage lp = new LoginPage(driver, test);
	TaskManagerPage TM = new TaskManagerPage(driver, test);
	lp.HomePageVerification(UserID);
//	TM.TaskManagerNavigation().CreateNewTask();
	
	}
	
	@AfterClass
	void StopReport() {
		CloseDriver();
	}
}
