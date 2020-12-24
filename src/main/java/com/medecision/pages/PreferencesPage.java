package com.medecision.pages;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.mongodb.ReadPreference;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PreferencesPage extends SeleniumBase {
	public PreferencesPage(RemoteWebDriver driver, ExtentTest test) {		
		super(driver, test);
		this.driver = driver;
		this.test =test;
	}
	public PreferencesPage clickepreferencemenu() {
		try {
			click("//span[@class='username']");
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Preference user menu clicked successfully");	
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click preference user menu");	
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage selectpreferenceoption() {
		try {
			click("//button/span[normalize-space(text())='Preferences']");
			Thread.sleep(2500);
			test.log(LogStatus.PASS, "Preference clicked successfully");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click preference");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage clickalertpreferencetab() {
		try {
			click("//table//tr/td[text()='Alert Preferences']");
			test.log(LogStatus.PASS, "Alert preference tab clicked successfully");
			Thread.sleep(2500);
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to click Alert preference tab");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage gotoAlertPreferncetab() {
		try {
			clickepreferencemenu();
			selectpreferenceoption();
			clickalertpreferencetab();
		}catch (Exception e) {
			e.printStackTrace();
			TakescreenShot(driver,test);
		}
		return this;
	}
	public PreferencesPage getalertpreferencename() {
		try {
			WebElement alertsize = driver.findElement(By.xpath("//tr[@role='listitem']"));
			String s1 = alertsize.getAttribute("aria-setsize");
			List<String> alertsname = new ArrayList<String>();
			int n = Integer.parseInt(s1);
			for(int i=1;i<=n;i++) {
				WebElement alert_name = driver.findElement(By.xpath("//tbody/tr[@aria-posinset='"+i+"']//span/b"));
				Actions action = new Actions(driver);
				action.moveToElement(alert_name).build().perform();
				alertsname.add(alert_name.getText());
				Thread.sleep(1000);
			}
			test.log(LogStatus.INFO, "Alerts names : " + alertsname);
			Thread.sleep(3000);
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to get alerts name");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage closepreference() {
		try {
			click("//div[contains(@eventproxy,'closeButton')]");
			Thread.sleep(2000);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return this;
	}
	public PreferencesPage selectonealertpreference() {
		try {
			String s1 = getElementText("//tbody/tr[@role='listitem']/td/div/span");
			click("//tbody/tr[@role='listitem']/td/div");
			test.log(LogStatus.INFO, "Click '" + s1 + "' alert in alert preferences");
			Thread.sleep(2500);
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click alert in alert preferences");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage clickcheckboxselect() {
		try {
			String s1 = getElementText("((//div[@class='listGrid'])[2]//tr/td[3])[1]");
			click("(//span[contains(@class,'checkbox')])[2]");
			test.log(LogStatus.INFO, "Uncheck the '" + s1 + "' alert checkbox");
			Thread.sleep(2500);
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to uncheck the  alert checkbox");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage clickcheckboxdeselect() {
		try {
			String s1 = getElementText("((//div[@class='listGrid'])[2]//tr/td[3])[1]");
			click("(//span[contains(@class,'checkbox')])[2]");
			test.log(LogStatus.INFO, "check the '" + s1 + "' alert checkbox");
			Thread.sleep(2500);
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Unable to check the  alert checkbox");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage clicksavebutton() {
		try {
			click("//div[@eventproxy='isc_ListGrid_2_body']//following::div[text()='Save']");
			test.log(LogStatus.INFO, "click save button");
			Thread.sleep(2500);
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click save button");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage clickoksavesuccessfull() {
		try {
			click("//div[@eventproxy='isc_globalWarn_body']//following::div[text()='OK']");
			test.log(LogStatus.INFO, "Successfully click OK button");
			Thread.sleep(3000);
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click OK button");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage alertpreferenceuncheckthebox() {
		try {
			selectonealertpreference();
			clickcheckboxselect();
			clicksavebutton();
			clickoksavesuccessfull();
			test.log(LogStatus.PASS, "successfully uncheck the checkbox");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to uncheck the checkbox");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public PreferencesPage alertpreferencecheckthebox() {
		try {
			selectonealertpreference();
			clickcheckboxdeselect();
			clicksavebutton();
			clickoksavesuccessfull();
			test.log(LogStatus.PASS, "successfully check the checkbox");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to check the checkbox");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	
	public PreferencesPage Resourcecenterverification() throws InterruptedException {

		if (verifyelementpresentExplicitwait("//span[@class='username']")) {
			test.log(LogStatus.INFO, "Preferences dropdown is Present");
			click("//span[@class='username']");
			Thread.sleep(3000);
			click("//span[contains(text(),'Resource Center')]");
			Thread.sleep(5000);
			try {
				if (verifyelementpresentExplicitwait("(//td[contains(text(),'Resource Center')]//following::tr[@role='listitem'])[1]",10)) {
					test.log(LogStatus.PASS, "Resource center is working fine and files are appearing");
					Thread.sleep(2000);
					click("//div[contains(@eventproxy,'ResourceCenterWindow_0_closeButton') or contains(@eventproxy,'ResourceCenterWindow_1_closeButton')]");
				} else {
					test.log(LogStatus.FAIL, "No files were found in Resource center");
					TakescreenShot(driver,test);
					click("//div[contains(@eventproxy,'ResourceCenterWindow_0_closeButton') or contains(@eventproxy,'ResourceCenterWindow_1_closeButton')]");
					Assert.fail();
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Unable to find files in resource center");
				click("//div[contains(@eventproxy,'ResourceCenterWindow_0_closeButton') or contains(@eventproxy,'ResourceCenterWindow_1_closeButton')]");
				TakescreenShot(driver,test);
				Assert.fail();
			}
		} else {
			test.log(LogStatus.FAIL, "Preferences dropdown is not appearing");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}

	public PreferencesPage Trainingverification() throws InterruptedException {

		if (verifyelementpresentExplicitwait("//span[@class='username']")) {
			test.log(LogStatus.INFO, "Preferences dropdown is Present");
			click("//span[@class='username']");
			Thread.sleep(3000);
			click("//span[contains(text(),'Training')]//parent::button");
			Thread.sleep(8000);
			test.log(LogStatus.INFO, "Switching to Training window");
			switchToWindow("Training - GSI Health -Google Chrome");
			try {
				if (verifyelementpresentExplicitwait("//a[ contains(text(),'Using the Population Manager')]")) {
					test.log(LogStatus.PASS, "Training Page is accessible");
					List<String> windows = new ArrayList<>(driver.getWindowHandles());
				    String parentWindow = windows.get(0); 
				    String childWindow = windows.get(1);
				    driver.switchTo().window(childWindow).close();
				    driver.switchTo().window(parentWindow);
			    	verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
					actionsclick("//div[contains(@id,'appHome_menu')]");
				} else {
					test.log(LogStatus.FAIL, "Training materials not available");
					TakescreenShot(driver,test);
					List<String> windows = new ArrayList<>(driver.getWindowHandles());
				    String parentWindow = windows.get(0); 
				    String childWindow = windows.get(1);
				    driver.switchTo().window(childWindow).close();
				    driver.switchTo().window(parentWindow);
			    	verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
					actionsclick("//div[contains(@id,'appHome_menu')]");
					
					Assert.fail();
				}
			} catch (Exception e) {
				test.log(LogStatus.FAIL, "Failed to open Training window");
				TakescreenShot(driver,test);
				Assert.fail();
			}
		} else {
			test.log(LogStatus.FAIL, "Preferences dropdown is not appearing");
			TakescreenShot(driver,test);
			Assert.fail();
		}

		return this;
	}

	
	
	
	
}