package com.medecision.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AddpatienttopanelPage extends SeleniumBase
{

	protected  RemoteWebDriver driver;
	static String date1;
	public AddpatienttopanelPage(RemoteWebDriver driver, ExtentTest test) {
		
		super(driver, test);
		this.driver = driver;
		this.test =test;
		
	}

	public AddpatienttopanelPage VerifyPatientPanelIsNotLoading() throws Exception
	{
		try {
		Thread.sleep(15000);
		boolean Verify = verifyelementpresentExplicitwait("//md-sidenav[contains(@id,'MyPatientList')]//div[contains(@class,'inner-loading')]",2);
		if(Verify)
		{
		test.log(LogStatus.FAIL, "Patient Panel Loading Continuesly");
		TakescreenShot(driver,test);
		Assert.fail();
		}
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to verify the Patient Panel Loading");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	
	public AddpatienttopanelPage patientsearch(String PatientID) throws Exception
	{
		try {
		if(verifyelementpresentExplicitwait("//i[contains(text(),'refresh')]//parent::div//parent::button")) {
		click("//i[contains(text(),'refresh')]//parent::div//parent::button");
		}else {
		click("//button[contains(@id,'MyPatientListReloadBtn')]");	
		}
		movetoelement("//input[contains(@placeholder,'Search')]");
		clearelementusingkeys("//input[contains(@placeholder,'Search')]");
		clearAndType("//input[contains(@placeholder,'Search')]",PatientID );
		enterkey("//input[contains(@placeholder,'Search')]");
		Thread.sleep(5000);
		boolean verify= verifyelementpresentExplicitwait("//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PatientID+"')]");
		if(verify)
		{
		test.log(LogStatus.INFO, "Patient Already present in the pantient panel, So removing the patient from patient panel");
		Thread.sleep(5000);
		patientremovalPP(PatientID);
		}
		else if(verifyelementpresentExplicitwait("//div[contains(@class,'list-item')]//span[contains(text(),'"+PatientID+"')]", 2)) {
			test.log(LogStatus.INFO, "Patient Already present in the pantient panel, So removing the patient from patient panel");
			Thread.sleep(5000);
			patientremovalPP(PatientID);
		}
		else
		{
		test.log(LogStatus.INFO, "Patient is not present in the pantient panel");
		}
	} catch (Exception e) {
		test.log(LogStatus.FAIL, "The Searched Patient Retrive Nothing");
		new TestDataVerification(driver,test).NavigatetoHome();
		TakescreenShot(driver,test);Assert.fail();
	}
		return this;
	}
	
	public boolean PatientIDVerification(String PID) throws Exception
	{
		try {
			if (verifyelementpresentExplicitwait("//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PID+"')]",2)) {
				return true;
			}else if(verifyelementpresentExplicitwait("//div[contains(@class,'list-item')]//span[contains(text(),'"+PID+"')]", 2)) {
				return true;
			}else {
				return false;
			}
		
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to verify the Patient in Patient Panel");
			TakescreenShot(driver,test);
			return false;
		}
		
	}
	public AddpatienttopanelPage patientremovalPP(String PatientID) throws Exception
	{
		try {
			if(verifyelementpresentExplicitwait("(//i[contains(text(),'more_vert')])[1]",5)) {
			click("(//i[contains(text(),'more_vert')])[1]");
			Thread.sleep(5000);
			verifyelementpresentExplicitwait("//div[contains(text(),'Remove')]",2);
			Thread.sleep(3000);
			click("//div[contains(text(),'Remove')]");
			Thread.sleep(2000);
			}else if(verifyelementpresentExplicitwait("(//button[contains(@aria-label,'Patient Menu')])[1]",2)) {
				click("(//button[contains(@aria-label,'Patient Menu')])[1]");
				Thread.sleep(2000);
				verifyelementpresentExplicitwait("//span[contains(text(),'Remove')]",2);
				Thread.sleep(2000);
				click("//span[contains(text(),'Remove')]");
			}
			if(verifyelementpresentExplicitwait("//div[contains(text(),'Confirmation')]//following::div[text()='Yes']",5)) {
				click("//div[contains(text(),'Confirmation')]//following::div[text()='Yes']");
				Thread.sleep(3000);
				test.log(LogStatus.INFO, "Patient Removal Popup Accepted");
				verifyelementpresentExplicitwait("//i[contains(text(),'refresh')]");
				click("//i[contains(text(),'refresh')]");
				Thread.sleep(2000);
				movetoelement("//input[contains(@placeholder,'Search')]");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				clearAndType("//input[contains(@placeholder,'Search')]",PatientID );
				enterkey("//input[contains(@placeholder,'Search')]");
				try{
					boolean verify = verifyelementpresentExplicitwait("//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PatientID+"')]",2);
					if (!verify) {
						test.log(LogStatus.PASS, "Patient Successfully Removed from the User's Patient Panel");
					}
					else {
					test.log(LogStatus.FAIL, "Patient Not Removed from the User's Patient Panel");
					clearelementusingkeys("//input[contains(@placeholder,'Search')]");
					new TestDataVerification(driver,test).NavigatetoHome();
					TakescreenShot(driver,test);Assert.fail();
					}
				}
				catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to remove the patient from the patient panel");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				new TestDataVerification(driver,test).NavigatetoHome();
				TakescreenShot(driver,test);Assert.fail();
				
				}
				
			}else if(verifyelementpresentExplicitwait("//button[contains(@id,'btnConfirmOk')]",5)){
				click("//button[contains(@id,'btnConfirmOk')]");
				Thread.sleep(3000);
				test.log(LogStatus.INFO, "Patient Removal Popup Accepted");
				verifyelementpresentExplicitwait("//button[contains(@id,'MyPatientListReloadBtn')]");
				click("//button[contains(@id,'MyPatientListReloadBtn')]");
				Thread.sleep(2000);
				movetoelement("//input[contains(@placeholder,'Search')]");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				clearAndType("//input[contains(@placeholder,'Search')]",PatientID );
				enterkey("//input[contains(@placeholder,'Search')]");
				try{
					boolean verify = verifyelementpresentExplicitwait("//div[contains(@class,'list-item')]//span[contains(text(),'"+PatientID+"')]",2);
					if (!verify) {
						test.log(LogStatus.PASS, "Patient Successfully Removed from the User's Patient Panel");
					}
					else {
					test.log(LogStatus.FAIL, "Patient Not Removed from the User's Patient Panel");
					clearelementusingkeys("//input[contains(@placeholder,'Search')]");
					new TestDataVerification(driver,test).NavigatetoHome();
					TakescreenShot(driver,test);Assert.fail();
					}
				}
				catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to remove the patient from the patient panel");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				new TestDataVerification(driver,test).NavigatetoHome();
				TakescreenShot(driver,test);Assert.fail();
				
				}
			}
		
		} 
		catch (Exception e) {
		test.log(LogStatus.FAIL, "Unable to Remove the Patient From Panel");
		new TestDataVerification(driver,test).NavigatetoHome();
		TakescreenShot(driver,test);Assert.fail();
	}

		return this;
	}
	

	
	public AddpatienttopanelPage AddtoPP() throws Exception
	{
		try {
			boolean Verify = verifyelementpresentExplicitwait("//div[contains(text(),'Add To')]",20);
			if(Verify)
			{
				click("//div[contains(text(),'Add To')]");
				Thread.sleep(5000);
				boolean verify = verifyelementpresentExplicitwait("//td[contains(text(),'added to your')]//following::div[text()='OK']//parent::td[contains(@class,'button')]",60);
				if(verify) {
					click("//td[contains(text(),'added to your')]//following::div[text()='OK']//parent::td[contains(@class,'button')]");
					Thread.sleep(5000);
					test.log(LogStatus.PASS, "Patient Added to the Patient Panel Popup Appeared");
				}else {
					test.log(LogStatus.FAIL, "Patient Added to the Patient Panel Popup Not Appeared");
					click("//div[text()='OK']//parent::td[contains(@class,'button')]");
					TakescreenShot(driver,test);Assert.fail();
				}
			}
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to Add patient to Patient Panel ");
				TakescreenShot(driver,test);Assert.fail();
			}

			return this;
	}
	public AddpatienttopanelPage  VerifyPatientShownInPP(String PatientID) {
		try {
			if(verifyelementpresentExplicitwait("//i[contains(text(),'refresh')]//parent::div//parent::button",5)) {
			click("//i[contains(text(),'refresh')]//parent::div//parent::button");
			movetoelement("//input[contains(@placeholder,'Search')]");
			clearelementusingkeys("//input[contains(@placeholder,'Search')]");
			clearAndType("//input[contains(@placeholder,'Search')]",PatientID );
			enterkey("//input[contains(@placeholder,'Search')]");
			Thread.sleep(3000);
			try{
				boolean verify = verifyelementpresentExplicitwait("//div[contains(@class,'text-faded demog')]//div[contains(text(),'"+PatientID+"')]",10);
				if (verify) {
					test.log(LogStatus.PASS, "Patient Shown in the User's Patient Panel");
				}else if(verifyelementpresentExplicitwait("//span[contains(text(),'"+PatientID+"')]",5)) {
					test.log(LogStatus.PASS, "Patient Shown in the User's Patient Panel");
				}
				else {
				test.log(LogStatus.FAIL, "Patient Not Shown in the User's Patient Panel");
				clearelementusingkeys("//input[contains(@placeholder,'Search')]");
				new TestDataVerification(driver,test).NavigatetoHome();
				TakescreenShot(driver,test);Assert.fail();
				}
			}
			catch(Exception e) {
			test.log(LogStatus.FAIL, "Patient Verification Failed !");
			clearelementusingkeys("//input[contains(@placeholder,'Search')]");
			new TestDataVerification(driver,test).NavigatetoHome();
			TakescreenShot(driver,test);Assert.fail();
			}
			}else if(verifyelementpresentExplicitwait("//button[contains(@id,'MyPatientListReloadBtn')]",5)){
			click("//button[contains(@id,'MyPatientListReloadBtn')]");	
			movetoelement("//input[contains(@placeholder,'Search')]");
			clearelementusingkeys("//input[contains(@placeholder,'Search')]");
			clearAndType("//input[contains(@placeholder,'Search')]",PatientID );
			enterkey("//input[contains(@placeholder,'Search')]");
			try{
				boolean verify = verifyelementpresentExplicitwait("//div[contains(@class,'list-item')]//span[contains(text(),'"+PatientID+"')]",2);
				if (verify) {
					test.log(LogStatus.PASS, "Patient Shown in the User's Patient Panel");
				}
				else {
					test.log(LogStatus.FAIL, "Patient Not Shown in the User's Patient Panel");
					clearelementusingkeys("//input[contains(@placeholder,'Search')]");
					new TestDataVerification(driver,test).NavigatetoHome();
					TakescreenShot(driver,test);Assert.fail();
				}
			}
			catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to remove the patient from the patient panel");
			clearelementusingkeys("//input[contains(@placeholder,'Search')]");
			new TestDataVerification(driver,test).NavigatetoHome();
			TakescreenShot(driver,test);Assert.fail();
			
			}
			}
			
			
	} catch (Exception e) {
		test.log(LogStatus.FAIL, "The Searched Patient Retrive Nothing");
		new TestDataVerification(driver,test).NavigatetoHome();
		TakescreenShot(driver,test);Assert.fail();
	}
	return this;
 }
	
	public AddpatienttopanelPage popup(String PID) throws Exception
	{
		Thread.sleep(1000);
		String p=driver.findElementByXPath("//tbody//td[@class='normal'][contains(text(),' added')]").getText();
		test.log(LogStatus.INFO, "Pop up displayed with mesaage as:"+p);
		
		driver.findElementByXPath("//div[text()='OK']").click();
		Thread.sleep(1000);
		return this;
	}
	
	public AddpatienttopanelPage verification(String PID) throws Exception
	{
		Thread.sleep(1000);
		driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
		Thread.sleep(1000);
		driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(PID);
		Thread.sleep(5000);
		boolean a= verifyelementpresent("//div[@class='text-faded demog']//div[text()='ID: "+PID+"']");
		if(a)
		{
			test.log(LogStatus.PASS, "Patient added to the patienl panel Successfully!!");
		}
		else
		{
			test.log(LogStatus.PASS, "Patient added to the patienl panel Failed!!");
		}
		
		return this;
	}

	public AddpatienttopanelPage Addtags(String PatientID) throws Exception
	{ 
		try {
		Thread.sleep(1000);
		click("//i[@class='q-icon material-icons' and contains(text(),'refresh')]");
		driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
		driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(PatientID);
		boolean a= verifyelementpresent("//div[@class='text-faded demog']//div[text()='ID: "+PatientID+"']");
		Thread.sleep(2000);
		if(a)
		{
			click("(//i[@class='q-icon material-icons q-item-icon'])/..");
			Thread.sleep(2000);
			javascriptscrolltoelement("//div[@class='q-item q-item-division relative-position' and contains(text(),'Manage Tags')]");
			click("//div[@class='q-item q-item-division relative-position' and contains(text(),'Manage Tags')]");
			Thread.sleep(2000);
			click("//div[@class='q-btn-inner row col items-center justify-center' and contains(text(),'Clear All')]");
			click("//div[@class='col row items-center group q-input-chips justify-start']");
			javascriptscrolltoelement("//div[@class='q-item-label' and contains(text(),'Asthma')]");
			click("//div[@class='q-item-label' and contains(text(),'Asthma')]");
			Thread.sleep(2000);
			javascriptscrolltoelement("//div[@class='q-item-label' and contains(text(),'Custom Tag')]");
			click("//div[@class='q-item-label' and contains(text(),'Custom Tag')]");
			click("//div[@class='q-btn-inner row col items-center justify-center' and contains(text(),'Save')]");
			
			test.log(LogStatus.PASS, "Tags added to patient  Successfully!!");
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Failed to add Tags!");	
			TakescreenShot(driver,test);Assert.fail();	
		}
		return this;
	}
	
	public AddpatienttopanelPage Verifytagsadded(String PatientID) throws Exception
	{
		try {
			Thread.sleep(1000);
			click("//i[@class='q-icon material-icons' and contains(text(),'refresh')]");
			driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
			driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(PatientID);
			Thread.sleep(2000);
			boolean a= verifyelementpresent("//div[@class='text-faded demog']//div[text()='ID: "+PatientID+"']");
			if(a)
			{
				verifyelementpresent("//div[@class='q-chip-main' and contains(text(),'ASTHMA_TAG')]");
				verifyelementpresent("//div[@class='q-chip-main' and contains(text(),'Custom Tag')]");
				test.log(LogStatus.PASS, "Tags are verified to the assigned patient in patientpanel!");
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Tags are not present to the assigned patient");	
			TakescreenShot(driver,test);Assert.fail();
		}
		return this;
	}
	
	public AddpatienttopanelPage Removetags(String PatientID) throws Exception
	{ 
		try {
		Thread.sleep(1000);
		click("//i[@class='q-icon material-icons' and contains(text(),'refresh')]");
		driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
		driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(PatientID);
		boolean a= verifyelementpresent("//div[@class='text-faded demog']//div[text()='ID: "+PatientID+"']");
		if(a)
		{
			click("(//i[@class='q-icon material-icons q-item-icon'])/..");
			Thread.sleep(2000);
			javascriptscrolltoelement("//div[@class='q-item q-item-division relative-position' and contains(text(),'Manage Tags')]");
			click("//div[@class='q-item q-item-division relative-position' and contains(text(),'Manage Tags')]");
			Thread.sleep(3000);
			click("//div[@class='q-btn-inner row col items-center justify-center' and contains(text(),'Clear All')]");
			click("//div[@class='q-btn-inner row col items-center justify-center' and contains(text(),'Save')]");
			test.log(LogStatus.PASS, "Tags removed to patient  Successfully!!");
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Failed to remove Tags!");	
			TakescreenShot(driver,test);Assert.fail();	
		}
		return this;
	}
	
	public AddpatienttopanelPage Verifytagsremoved(String PatientID) throws Exception
	{
		try {
			Thread.sleep(1000);
			click("//i[@class='q-icon material-icons' and contains(text(),'refresh')]");
			driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.BACK_SPACE));
			driver.findElementByXPath("//input[@placeholder='Search...']").sendKeys(PatientID);
			Thread.sleep(2000);
			boolean a= verifyelementpresent("//div[@class='text-faded demog']//div[text()='ID: "+PatientID+"']");
			if(a)
			{
				if(verifyelementpresent("//div[@class='q-chip-main' and contains(text(),'ASTHMA_TAG')]") && verifyelementpresent("//div[@class='q-chip-main' and contains(text(),'Custom Tag')]") ) 
					test.log(LogStatus.FAIL, "Tags are appearing to the patient after removing the tags");	
				
				else {
					test.log(LogStatus.PASS, "Tags are not appearing to the patient after removing the tags");
				}
				
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "Failed to verify tags are removed to the patient");	
			TakescreenShot(driver,test);Assert.fail();
		}
		return this;
	}
}
