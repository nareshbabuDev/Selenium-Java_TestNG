package com.medecision.pages;
import java.nio.charset.Charset;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.github.javafaker.Faker;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserUpdatePage extends SeleniumBase   {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	
	public UserUpdatePage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}
	public UserUpdatePage clickAdminApp() {
		try{
			verifyelementpresentExplicitwait("//div[contains(@id,'appAdmin_menu')]");
			click("//div[contains(@id,'appAdmin_menu')]");
			boolean verify=verifyelementpresentExplicitwait("//td[contains(text(),'Update User')]");
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Admin app successfully");
			}else {
			test.log(LogStatus.FAIL,"Admin App Navigation failed");
			TakescreenShot(currentdriver,test);
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Admin App");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
		}
		
		return this;
	}
	public UserUpdatePage UpdateUserXpath() {
		try {
			click("//td[contains(text(),'Update User')]");
			Thread.sleep(5000);
			boolean verify=verifyelementpresentExplicitwait("//input[contains(@name,'EmailId')]",90);
			if(verify) {
			test.log(LogStatus.INFO,"Navigated to the Admin app User Search Page");
			}else {
			test.log(LogStatus.FAIL,"Admin App User Search page Navigation failed");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Admin App User Search page Navigation failed");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
		}
		return this;
	}
	public UserUpdatePage EmailIdDialogBoxXpath(String UserID) {
		try {
			clearelementusingkeys("//input[contains(@name,'EmailId')]");
			clearAndType("//input[contains(@name,'EmailId')]", UserID);
			click("//input[contains(@name,'EmailId')]//following::div[contains(text(),'Search')]");
			test.log(LogStatus.INFO,"UserID Entered in User Search is:<font color=blue>"+UserID+"</font>");
			
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to enter the User in Admin Search box");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
		}
		return this;
	}
	public UserUpdatePage ClickSearchResult() {
		try {
			boolean verify=verifyelementpresentExplicitwait("(//input[contains(@name,'EmailId')]//following::table[contains(@class,'listTable')]//tr)[1]",120);
			if(verify) {
				click("(//input[contains(@name,'EmailId')]//following::table[contains(@class,'listTable')]//tr)[1]");
			
			}else {
			test.log(LogStatus.FAIL,"Searched user did not retrive Anything");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Admin App User Search page Navigation failed");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
		}
		return this;
	}
	public UserUpdatePage UpdateAddress1() {
		Faker faker = new Faker();
		String Address1 = faker.address().streetAddress();
		try {
			movetoelement("(//label[text()='Address 1']//following::input[@type='TEXT'])[1]");
			boolean verify=verifyelementpresentExplicitwait("(//label[text()='Address 1']//following::input[@type='TEXT'])[1]",120);
			if(verify) {
				clearelementusingkeys("(//label[text()='Address 1']//following::input[@type='TEXT'])[1]");
				clearAndType("(//label[text()='Address 1']//following::input[@type='TEXT'])[1]",Address1);
				boolean verify1 = verifyelementpresentExplicitwait("//img[contains(@aria-label,'Field is required')]", 1);
				if(!verify1) {
					test.log(LogStatus.PASS, "New Address Enterned in Address 1 Field:<font color=blue>"+Address1+"</font>");
				}else {
					test.log(LogStatus.FAIL, "Unable to enter the new address in Address 1 Field");
					TakescreenShot(currentdriver,test);
					NavigateHome();
					Assert.fail();
				}
			
			}else {
			test.log(LogStatus.FAIL,"Unable to move address 1 field");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable Update the Address 1");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
		}
		return this;
	}
	public UserUpdatePage SaveButton() {
		try {
			movetoelement("//div[text()='Save']");
			boolean verify=verifyelementpresentExplicitwait("//div[text()='Save']",120);
			if(verify) {
				click("//div[text()='Save']");
				Thread.sleep(10000);
			}else {
			test.log(LogStatus.FAIL,"Unable to Click Save Button");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Save Button Not Displayed");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
			
		}
		return this;
	}
	public UserUpdatePage OkButton() {
		try {
			boolean verify=verifyelementpresentExplicitwait("//td[contains(text(),'Save successful')]",120);
			if(verify) {
				test.log(LogStatus.INFO,"Save SuccessFul Popup Appeared");
				actionsclick("//div[text()='OK']//parent::td");
				Thread.sleep(5000);
			}else {
			test.log(LogStatus.FAIL,"Save SuccessFul Popup not Appeared");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Click Save Button");
			TakescreenShot(currentdriver,test);
			NavigateHome();
			Assert.fail();
		}
		return this;
	}
	public boolean VerifyUserUpdate() {
		boolean verify=verifyelementpresentExplicitwait("//td[text()='Save successful.']",2);
		try {
			if(!verify) {
				test.log(LogStatus.PASS,"User Updated SuccessFully");
				return true;
				
			}else {
			test.log(LogStatus.FAIL,"User Updation Failed");
			TakescreenShot(currentdriver,test);
			return false;
			}	
		}catch(Exception e){
			test.log(LogStatus.FAIL,"Save SuccessFul Popup not Closed");
			TakescreenShot(currentdriver,test);
			return false;
		}
	}
	public UserUpdatePage NavigateHome() {
		try {
			verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
			movetoelement("//div[contains(@id,'appHome_menu')]");
			click("//div[contains(@id,'appHome_menu')]");
		}catch(Exception e) {
			Assert.fail();
		}
		return this;
	}
	
	public UserUpdatePage UpdateUserAddress1 (String UserID) {
		boolean flag=false;
		try {
			clickAdminApp()
			.UpdateUserXpath()
			.EmailIdDialogBoxXpath(UserID)
			.ClickSearchResult()
			.UpdateAddress1()
			.SaveButton()
			.OkButton();
			boolean verify=VerifyUserUpdate();
			if(verify) {
				flag=true;
				NavigateHome();
			}else {flag=false;}
			
		}catch(Exception e) {
			flag=false;
		}
		finally {
			if(!flag) {
				NavigateHome();
				Assert.fail();
			}
		}
		return this;
		
	}
	String Supervisor;
	String FirstName;
	String LastName;
	String Name;
	public UserUpdatePage supervisor() {
		verifyelementClickableExplicitwait("//span[@class='username']//span", 10);
		String UserName1 = driver.findElement(By.xpath("//span[@class='username']//span")).getText();
		Supervisor = getElementText("//label[text()='Select User']//following::div[@class='selectItemText']");
		Name = UserName1;
		Supervisor = Supervisor.toLowerCase();
		if(Supervisor.contains(firstname)) {
			test.log(LogStatus.INFO, "'"+firstname+"' user is already set as a supervisor");
			movetoelement( "//label[text()='Select User']//following::div[@class='selectItemText']");
			click("//label[text()='Select User']//following::div[@class='selectItemText']");
			click("//*[@id='isc_PickListMenu_0_row_0']/td/div");
			test.log(LogStatus.INFO, "'"+firstname+"' user is deselected from the supervisor");
		}
		else {
			movetoelement( "//label[text()='Select User']//following::div[@class='selectItemText']");
			click("//label[text()='Select User']//following::div[@class='selectItemText']");
			verifyelementpresentExplicitwait("(//div[@role='presentation' and contains(text(),'rabha')])[1]",5);
			click("(//div[@role='presentation' and contains(text(),'rabha')])[1]");
			test.log(LogStatus.INFO, "'"+firstname+"' user is set as a supervisor");
			
		}
		return this;
		
	}
	public UserUpdatePage supervisor1() {
		verifyelementClickableExplicitwait("//span[@class='username']//span", 10);
		String UserName1 = driver.findElement(By.xpath("//span[@class='username']//span")).getText();
		Supervisor = getElementText("//label[text()='Select User']//following::div[@class='selectItemText']");
		Name = UserName1;
		Supervisor = getElementText("//label[text()='Select User']//following::div[@class='selectItemText']");
		Supervisor = Supervisor.toLowerCase();
		if(Supervisor.contains("firstname")) {
			test.log(LogStatus.INFO, "'"+firstname+"' user is already set as a supervisor");
		}
		else {
			movetoelement( "//label[text()='Select User']//following::div[@class='selectItemText']");
			click("//label[text()='Select User']//following::div[@class='selectItemText']");
			verifyelementpresentExplicitwait("(//div[@role='presentation' and contains(text(),''"+Username+"'')])[1]",5);
			click("(//div[@role='presentation' and contains(text(),'rabha')])[1]");
			test.log(LogStatus.INFO, "'"+firstname+"' user is set as a supervisor");
			
		}
		return this;
		
	}
	String Username;
	String firstname;
	public UserUpdatePage getUserName() {
		verifyelementClickableExplicitwait("//span[@class='username']//span", 10);
		String UserName = driver.findElement(By.xpath("//span[@class='username']//span")).getText();
		String[] words=UserName.split("\\s");
		String lastname = words[1];
		firstname = words[0];
		Username = lastname +","+firstname;
		System.out.println(Username);
		return this;
	}
	public UserUpdatePage SupervisorCheck() {
		System.out.println(Name);
		verifyelementClickableExplicitwait("//div[text()='My Patients']", 3);
		click("//div[text()='My Patients']");
		boolean Supervisor = verifyelementpresentExplicitwait("//span[@class='q-option-label' and text()= "+Name+"]",15);
		if(Supervisor) {
			test.log(LogStatus.INFO, ""+Name+" Supervisor is present in the patient panel view of the second user");
		}
		else {
			test.log(LogStatus.INFO, ""+Name+" Supervisor is not present in the patient panel view of the second user");
		}
		
		return this;
	}
	public UserUpdatePage checkSupervisor(String UserID) {
		try {
			clickAdminApp()
			.UpdateUserXpath()
			.EmailIdDialogBoxXpath(UserID)
			.ClickSearchResult()
			.supervisor()
			.SaveButton()
			.OkButton();
		}
	 catch(Exception e) {}
		
		return this;
	}
	public UserUpdatePage checkSupervisor1(String UserID) {
		try {
			clickAdminApp()
			.UpdateUserXpath()
			.EmailIdDialogBoxXpath(UserID)
			.ClickSearchResult()
			.supervisor1()
			.SaveButton()
			.OkButton();
		}
	 catch(Exception e) {}
		
		return this;
	}
	
}
