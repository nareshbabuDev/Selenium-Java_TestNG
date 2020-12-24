package com.medecision.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AlertVerificationPage extends SeleniumBase   {
	String TodayDate;
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	public AlertVerificationPage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}
//	public static void wait(int millisecond) {
		public AlertVerificationPage clickAlertApp() {

			try{
				verifyelementpresentExplicitwait("//div[contains(@id,'appAlerts_menu')]");
				click("//div[contains(@id,'appAlerts_menu')]");
				boolean verify=verifyelementpresentExplicitwait("//td[contains(@class,'toolStripButton') and contains(text(),'Alerts')]");
				if(verify) {
				test.log(LogStatus.PASS,"Navigated to the Alerts app successfully");
				}else {
				test.log(LogStatus.FAIL,"Alert App Navigation failed");
				TakescreenShot(currentdriver,test);Assert.fail();
				}
				
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL,"Unable to click Alert App");
				TakescreenShot(currentdriver,test);Assert.fail();
			}
			
			return this;

		}
		
	public boolean verifyAlert() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
	   LocalDateTime now = LocalDateTime.now();  
	   System.out.println(dtf.format(now));
	   TodayDate = dtf.format(now);
		try{
		boolean TodayAlert = verifyelementpresentExplicitwait("//div[text()='Date Received']//following::div[contains(text(),'"+TodayDate+"')][1]",10);
		if(TodayAlert){
			test.log(LogStatus.PASS,"Alerts are received for today's date:<font color='blue'> "+TodayDate+"</font>");
			return true;
		}else {
			test.log(LogStatus.FAIL,"Alerts are not received for today's date");
			TakescreenShot(currentdriver,test);Assert.fail();
			return false;
		}
		}
		catch(Exception e) {
				test.log(LogStatus.FAIL,"Alerts are not received for today's date");
				TakescreenShot(currentdriver,test);Assert.fail();
				return false;
			}
	}
		
		public AlertVerificationPage AlertVerify() {
			boolean flag=false;
			try {
				clickAlertApp();
				boolean verify =verifyAlert();
				if(verify) {
					verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
					movetoelement("//div[contains(@id,'appHome_menu')]");
					actionsclick("//div[contains(@id,'appHome_menu')]");
					flag=true;
				}else {
					flag=false;
				}
				
			}catch(Exception e) {
				flag=false;
				
			}
			finally {
				if(!flag) {
					verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
					movetoelement("//div[contains(@id,'appHome_menu')]");
					actionsclick("//div[contains(@id,'appHome_menu')]");
				}
			}
		return this;
		}
		public AlertVerificationPage AlertAppConfig() {
			test.log(LogStatus.SKIP, "Alert App Not Configured for this Environment");
			return this;
			
		}
		
}
		

