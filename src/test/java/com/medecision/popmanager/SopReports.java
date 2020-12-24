package com.medecision.popmanager;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

import com.medecision.pages.LoginPage;
import com.medecision.pages.PopulationManagerPage;
import com.medecision.pages.UserPasswordUpdatePage;
import com.medecision.utils.DriverInitialize;

public class SopReports extends DriverInitialize {
	String Url,TestOrgOne,TestOrgTwo,PopManagerUser,PowerUser,TestUserIDOne,TestUserIDTwo,Password,PatientID1,PatientID2,PatientID3,CT1,CT2;
	//tc001
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",alwaysRun=true)
	public void TC001_TestData_PatientConsentOrg_Verification(Map<String,String> map) throws Exception {
	LoginPage lp = new LoginPage(driver, test);
	TestUserIDOne =map.get("PopManagerUser");
	TestOrgOne=map.get("TestOrgOne");
	TestOrgTwo=map.get("TestOrgTwo");
	Url =map.get("Url");
	lp.enterUsername(TestUserIDOne,Url).enterPassword("Medd123#").clickLogin(TestUserIDOne);
	}
	
	@Test(groups = { "DailyProd"} , dataProvider="fetchData",alwaysRun=true)
	public void TC002_PopulationManager_SOP_Reports(Map<String,String> map) throws Exception {
		LoginPage lp = new LoginPage(driver, test);
		String Secondwindow,Iframe,PopManagerConfig;
		PopManagerConfig =map.get("PopManagerConfig").toUpperCase();
		PopulationManagerPage PopulationManagerPage = new PopulationManagerPage(driver,test);
		UserPasswordUpdatePage UserPasswordUpdatePage = new UserPasswordUpdatePage(driver, test);
		try {
		if(PopManagerConfig.equals("Y")) {
		Iframe =map.get("PopManagerIframe").toUpperCase();
		Secondwindow =map.get("PopManagerSecondWindow").toUpperCase();
		PopulationManagerPage.PopManagerSOP_Reports(TestUserIDOne,"Medd123#",Secondwindow,Iframe);
		UserPasswordUpdatePage.clickLogout().clickYes();
		}
		else {
			PopulationManagerPage.PopManagerAppConfig();
		}
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
	}
}
