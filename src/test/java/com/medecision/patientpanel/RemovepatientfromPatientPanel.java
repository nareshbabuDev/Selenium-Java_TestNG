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
import com.medecision.pages.PatientRemovalPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;

public class RemovepatientfromPatientPanel extends DriverInitialize {
	String TestUserIDOne, Url, TestOrgOne, TestOrgTwo;
	String PatientID = null;
	String PID = null;
    String Password;

	@BeforeTest(alwaysRun = true)
	public void setData() {
		testcaseName = null;
	}

	@Test(groups = { "Smoke" }, dataProvider = "fetchData", alwaysRun = true, priority = 1)
	public void LP001_DashBoard_Login(Map<String, String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		TestDataVerification TestDataVerification = new TestDataVerification(driver, test);
		TestUserIDOne = map.get("TestUserIDOne");
		TestOrgOne = map.get("TestOrgOne");
		TestOrgTwo = map.get("TestOrgTwo");
		Url = map.get("Url");
		//byte[] DecodedPass = Base64.decodeBase64(map.get("Password"));
		 Password = map.get("Password");
		lp.enterUsername(TestUserIDOne, Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}

	@Test(groups = { "Smoke" }, dataProvider = "fetchData", alwaysRun = true, priority = 2)
	public void TC_Remove(Map<String, String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		lp.HomePageVerification(TestUserIDOne);
		try {
			new PatientRemovalPage(driver, test).Removepatients();
		} catch (Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne, Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
	}

	
}
