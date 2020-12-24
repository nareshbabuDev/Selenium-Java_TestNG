package com.medecision.careteamwizard;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.CareteamAssignandRemovePage;
import com.medecision.pages.LoginPage;
import com.medecision.utils.DriverInitialize;


public class Careteam extends DriverInitialize {
	String UserID,Url;
	String PatientID=null;
	String PID=null;
	String careteamname=null;

	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName=null;
		//testcaseDec = "<font color='blue'>";
//		+ "LP001_LoginPage<br></font>"
//		+ "CT001_CareTeam_Assign_Remove<br></font>";
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
	public void CT_CareTeam_Assign_Remove(Map<String,String> map) throws Exception {
//	PID=this.EnvBulkDats.PatientID.get(0);
//	careteamname=this.EnvBulkDats.ExistingCareTeam.get(0);;
//	LoginPage lp = new LoginPage(driver, test);
//	lp.HomePageVerification(UserID);
//	new CareteamAssignandRemovePage(driver,test).demographics().psearch(PID).enrollmentbtn().careteamtab().existingcareteamname().assigncareteam(careteamname).savecareteam().saveverifiction().deleting();
//	
	}
	
	@AfterClass
	void StopReport() {
		CloseDriver();
	}


}
