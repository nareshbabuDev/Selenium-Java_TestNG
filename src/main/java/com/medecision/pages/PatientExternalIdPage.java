package com.medecision.pages;


import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PatientExternalIdPage extends SeleniumBase {
	protected RemoteWebDriver driver;

	public PatientExternalIdPage(RemoteWebDriver driver, ExtentTest test) {

		super(driver, test);
		this.driver = driver;
		this.test = test;
	}

	public PatientExternalIdPage clickDemographics() {
		try {
			verifyelementpresentExplicitwait("//div[contains(@id,'appEnrollment_menu')]");
			click("//div[contains(@id,'appEnrollment_menu')]");
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			boolean verify = verifyelementpresentExplicitwait("//input[contains(@name,'PatientId')]", 5);
			if (verify) {
				test.log(LogStatus.PASS, "Navigated to the Demographics app successfully");
			} else {
				test.log(LogStatus.FAIL, "Demographics app Navigation failed");
				TakescreenShot(driver,test);Assert.fail();
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click Demographics app");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}

	public PatientExternalIdPage ModifyPage() {
		try {
			click("//div[contains(text(),'Modify')]");
			boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'Modify')]");
			if (verify) {
				test.log(LogStatus.INFO, "Navigated to the Patient Details Modification Page");
			} else {
				test.log(LogStatus.FAIL, "Patient Details Modification Page Navigation failed");
				TakescreenShot(driver,test);Assert.fail();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Patient Details Modification Page Navigation failed");
			TakescreenShot(driver,test);Assert.fail();
		}
		return this;
	}

	public PatientExternalIdPage AddPatientOtherID(String ExternalID) throws InterruptedException {
		String Orgid;
		boolean verify =verifyelementpresent("//a[contains(text(),'Patient other IDs') or contains(text(),'Member other IDs')]");
		if(verify) {
			click("//a[contains(text(),'Patient other IDs') or contains(text(),'Member other IDs')]");
			Orgid = getElementText("(//div[contains(text(),'Org ID')]//following::td[@class='cell']//div)[1]");
			if(Orgid.equals(" ")) {
			new PatientExternalIdPage(driver,test).Add(ExternalID);
		}
			else {
				test.log(LogStatus.INFO, "External Id is already present good to delete!");
				new PatientExternalIdPage(driver,test).DeletePatientOtherID().Add(ExternalID);
			}
			}
		
		return this;
	}


	public PatientExternalIdPage Add(String ExternalID) throws InterruptedException {
		try {	String Orgid,Orgname;
		Thread.sleep(1000);
		click("//td[contains(text(),'External ID')]//following::td[contains(@class,'toolStripButton') and contains(text(),'Add ID')]");
		clearelementusingkeys("//input[contains(@name,'patientOtherIdItem')]");
		clearAndType("//input[contains(@name,'patientOtherIdItem')]",ExternalID);
		Thread.sleep(1000);
		click("//td[contains(text(),'Org not listed')]");
		clearelementusingkeys("//input[@name='orgName']");
		clearAndType("//input[@name='orgName']","Test");
		clearelementusingkeys("//input[@name='orgOid']");
		clearAndType("//input[@name='orgOid']",ExternalID+".25");
		//click("//td[@class='button']//div[contains(text(),'Add')]");
		click("//input[@name='orgOid']//following::div[contains(text(),'Add')]//parent::td[@class='button']");
		
//		click(locateElement("xpath", "(//span[contains(text(),'External ID')]//following::div[contains(@class,'checkbox cursor-pointer')])[1]"));
		Thread.sleep(2000);
		try {
		Orgname =getElementText("(//div[contains(text(),'Org Name')]//following::td[@class='cell']//div)[2]");
		Orgid = getElementText("(//div[contains(text(),'Org ID')]//following::td[@class='cell']//div)[1]");
		test.log(LogStatus.PASS, "Existing Patient External ID Edited with the<br>"
				+ "Organization Name of :<font color='blue'>"+Orgname.trim()+"</font><br>"
				+ "Organization ID of :<font color='blue'>"+Orgid.trim()+"</font><br>");
		}catch(Exception e) {
		test.log(LogStatus.FAIL, "Unable to Update the Patient External ID!!!");
		}
		new PatientExternalIdPage(driver,test).OkButton().UpdatePatientOtherID(ExternalID).DeletePatientOtherID();
	}
	catch(Exception e) {
		test.log(LogStatus.FAIL, "Unable to add external id");
		TakescreenShot(driver,test);Assert.fail();
	}
		return this;
	}

	public PatientExternalIdPage OkButton() {
		try {
			boolean verify = verifyelementpresent("//td[contains(text(),'New Patient Other ID successfully added.') or contains(text(),'New Member Other ID successfully added.') ]");
			if (verify) {
				test.log(LogStatus.INFO, "Save SuccessFul Popup Appeared");
				click("//div[text()='OK']");
			} else {
				test.log(LogStatus.FAIL, "Ok Button Not Appeared");
				TakescreenShot(driver,test);Assert.fail();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Click Save Button");
			TakescreenShot(driver,test);Assert.fail();
		}
		return this;
	}

	public PatientExternalIdPage DeletePatientOtherID() {
		try {
			boolean verify = verifyelementpresent(
					"(//div[contains(text(),'Org ID')]//following::td[@class='cell']//div)[1]");
			if (verify) {
				click("(//div[contains(text(),'Org ID')]//following::td[@class='cell']//div)[1]");
				click("//td[contains(text(),'Delete ID')]");
				click("//div[contains(text(),'OK')]");
				test.log(LogStatus.PASS, "External Id deleted successfully!");
			} else {
				test.log(LogStatus.FAIL, "There is no External ID for the patient");
				TakescreenShot(driver,test);Assert.fail();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to Delete Patient's ExternalId");
			TakescreenShot(driver,test);Assert.fail();
		}
		return this;
	}
	
	public PatientExternalIdPage UpdatePatientOtherID(String ExternalID) throws InterruptedException  {
		try {
			boolean verify = verifyelementpresent(
					"(//div[contains(text(),'Org ID')]//following::td[@class='cell']//div)[1]");
			if (verify) {
				click("(//div[contains(text(),'Org ID')]//following::td[@class='cell']//div)[1]");
				click("//td[contains(text(),'Update ID')]");
				driver.findElementByXPath("//input[contains(@name,'patientOtherIdItem')]").sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
				clearAndType("//input[contains(@name,'patientOtherIdItem')]",ExternalID+"89");
				click("//td[contains(text(),'External ID')]//following::td[@class='button']//div[contains(text(),'Save')]");
				click("//div[contains(text(),'OK')]");
				test.log(LogStatus.PASS, "External Id Updated successfully!");
				String UpdatedId=getElementText("(//div[contains(text(),'Patient ID')]//following::td[@class='cell']//div)[3]");
				test.log(LogStatus.PASS,"The Updated Patient External ID is :<font color='blue'>"+ UpdatedId.trim()+"</font><br>"
						);
			} else {
				test.log(LogStatus.FAIL, "Unable to Update Patient's ExternalID");
				TakescreenShot(driver,test);Assert.fail();
			}
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "There is no ExternalID for the patient to Update");
			TakescreenShot(driver,test);Assert.fail();
		}
		return this;
	}
	

	public PatientExternalIdPage NoExternalID() {
		test.log(LogStatus.PASS, "External ID is Not available for this environment!");
		return this;
	}
	public PatientExternalIdPage CloseExternalID() {
		try {
			boolean verify = verifyelementpresentExplicitwait("(//div[contains(@eventproxy,'PatientOtherIdsWindow_0_closeButton') or contains(@eventproxy,'PatientOtherIdsWindow_1_closeButton')])[1]");
			if (verify) {
				actionsclick("(//div[contains(@eventproxy,'PatientOtherIdsWindow_0_closeButton') or contains(@eventproxy,'PatientOtherIdsWindow_1_closeButton')])[1]");
				test.log(LogStatus.INFO, "External ID Page Closed");
			} else {
				test.log(LogStatus.FAIL, "Unable to close External ID Page");
				TakescreenShot(driver,test);Assert.fail();
			}

		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to close External ID Page");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}
}
