package com.medecision.homepagechart;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.AddpatienttopanelPage;
import com.medecision.pages.HomePage;
import com.medecision.pages.HomePageChartPage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;

public class HomePanelChart extends DriverInitialize {
	String UserID,Url,TestUserIDOne,TestOrgOne,TestOrgTwo,Password;
	String PatientID=null;
	String PID=null;
	String careteamname=null;

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
//		byte[] DecodedPass = Base64.decodeBase64(map.get("Password"));
//		Password = new String(DecodedPass);
		Password = map.get("Password");
		lp.enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
	}

	@Test(groups = { "Smoke"} , dataProvider="fetchData",alwaysRun=true,priority = 2)
	public void PP_Encounter_Chart(Map<String,String> map) throws Exception {
		String PatientID1 = this.EnvBulkDats.PatientID.get(0);
		LoginPage lp = new LoginPage(driver, test);
		lp.HomePageVerification(UserID);
		new HomePage(driver,test).clickgotit();
		new HomePageChartPage(driver,test).gotoEncounterchartandgetfirstvalue().getencounterfrequencyCountb(PatientID1).createEncounter(PatientID1)
		.getencounterfrequencyCounta(PatientID1).beforeandaftercountcompare();
	}

	@AfterClass
	void StopReport() {
		CloseDriver();
	}
}