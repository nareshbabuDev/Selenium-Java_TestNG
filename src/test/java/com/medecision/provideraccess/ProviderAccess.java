package com.medecision.provideraccess;



import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.medecision.pages.AddpatienttopanelPage;
import com.medecision.pages.CareteamAssignandRemovePage;
import com.medecision.pages.EncounterPage;
import com.medecision.pages.HomePage;
import com.medecision.pages.HomePageChartPage;
import com.medecision.pages.LoginPage;
import com.medecision.pages.ModifyPatientPage;
import com.medecision.pages.OrgWizardPage;
import com.medecision.pages.ProviderAccessPage;
import com.medecision.pages.SearchPatientPage;
//import com.medecision.pages.ProviderAccessPage;
import com.medecision.pages.TestDataVerification;
import com.medecision.utils.DriverInitialize;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProviderAccess extends DriverInitialize {
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
	public void PP_VerifyCMAOrgChangeAndReflectionOfPatient(Map<String,String> map) throws Exception {
		String PatientID2 = this.EnvBulkDats.PatientID.get(1);
		TestOrgOne=map.get("TestOrgOne");
		TestOrgTwo=map.get("TestOrgTwo");
		String TestUserIDThree =map.get("TestUserIDThree");
		LoginPage lp = new LoginPage(driver, test);
		ProviderAccessPage ProviderAccessPage = new ProviderAccessPage(driver, test);
		try {
		if(!TestUserIDThree.equals("N")) {
		System.out.println("Entered");
		lp.HomePageVerification(TestUserIDOne);
		new HomePage(driver,test).clickgotit();
		ProviderAccessPage.AddOrgInProviderAccess(PatientID2,TestOrgTwo );
		new LoginPage(driver, test).enterUsername(TestUserIDThree,Url).enterPassword(Password).clickLogin(TestUserIDThree);
		ProviderAccessPage.VerifyPatientSearchable(PatientID2 ,TestOrgTwo);
		ProviderAccessPage.OrgRemovedFromProviderAccess(PatientID2,TestOrgTwo );
		ProviderAccessPage.VerifyAfterOrgRemoved(PatientID2,TestOrgTwo );
		new LoginPage(driver, test).enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		test.log(LogStatus.INFO, "Logged into dashboard as user <font color= blue>'"+TestUserIDOne+"'</font> successfully");
		}
		else {
			test.log(LogStatus.SKIP, "Consent user is not provided for this environment");
		}
		}
		catch(Exception e) {
			Refresh();
			lp.SecondLogin().enterUsername(TestUserIDOne,Url).enterPassword(Password).clickLogin(TestUserIDOne);
		}
}
	@AfterClass
	void StopReport() {
		CloseDriver();
	}

}
