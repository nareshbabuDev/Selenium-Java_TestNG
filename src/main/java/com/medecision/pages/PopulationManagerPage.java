package com.medecision.pages;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PopulationManagerPage extends SeleniumBase {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	
	public PopulationManagerPage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}

	public PopulationManagerPage PopManagerSOP_Reports(String UserID,String Password,String Secondwindow,String Iframe) {
		boolean flag = false;
		try{
			if(Secondwindow.equals("Y")) {
				PopManagerNavigation(UserID,Password)
				.NavigateToSopPage()
				.newwindow2();
				 ViewReport()
				.Switchtoreportframe()
				.ReportVerification();
				closewindow2();
				closewindow();
			}else if(Iframe.equals("Y")) {
				PopManagerNavigationIframe()
//				.IframeMenuExpandIcon()
				.NavigateToSopPage()
				.SecondIframe()
				.RunReportNewframeConfig()
				.ReportVerificationIframe();
				 driver.switchTo().defaultContent();
			}else {
				PopManagerNavigation(UserID,Password)
				.NavigateToSopPage()
				.Switchtoframe()
				.ViewReport()
				.Switchtoreportframe()
				.ReportVerification();
				closewindow();
			}
			flag=true;
		}
		catch(Exception e) {
			flag=false;
		}finally {
			if(!flag) {
				if(Secondwindow.equals("Y")) {
					TakescreenShot(currentdriver,test);
					closewindow2();
					closewindow();
				}else if(Iframe.equals("Y")) {
					TakescreenShot(currentdriver,test);
					 driver.switchTo().defaultContent();
				}else {
					TakescreenShot(currentdriver,test);
					closewindow();
				}
			}
		}
		
		return this;
	}
	public PopulationManagerPage PopManagerNavigation(String UserID,String Password) {
		try{
			verifyelementpresentExplicitwait("//div[contains(@id,'appPopManager_menu')]");
			click("//div[contains(@id,'appPopManager_menu')]");
			newwindow();
			PopManagerLogin(UserID,Password);
			boolean verify=verifyelementpresentExplicitwait("//a[contains(@href,'content')]");			
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Poulation Manager app successfully");
			}else {
			test.log(LogStatus.FAIL,"Poulation Manager App Navigation failed");
			TakescreenShot(currentdriver,test);
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Poulation Manager App");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage NavigateToSopPage() {
		try{
			movetoelement("//a[contains(@href,'content')]");
			click("//a[contains(@href,'content')]");
			Thread.sleep(3000);
			movetoelement("//a[contains(text(),'Standard Operational Reports')]");
			click("//a[contains(text(),'Standard Operational Reports')]");
			Thread.sleep(3000);
			movetoelement("//a[contains(text(),'ProgramEnrollment')]");
			click("//a[contains(text(),'ProgramEnrollment')]");
			Thread.sleep(3000);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Content Button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage Switchtoframe() {
		try{
			switchToFrame("//iframe[contains(@name,'frame_0')]");
			Thread.sleep(3000);
			boolean verify = verifyelementpresentExplicitwait("//button[contains(text(),'View Report')]", 120);
			if(verify) {
				test.log(LogStatus.INFO,"Navigated to Sop Options Page");	
			}else {
				test.log(LogStatus.FAIL,"Unable to click Content Button");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Navigate Sop Options Page");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}

	public PopulationManagerPage ViewReport() {
		try{
			click("//button[contains(text(),'View Report')]");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "View Report Button Clicked");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click View Report Button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage ReportVerification() {
		try{
				boolean verify2 = verifyelementpresentExplicitwait("//td[contains(text(),'Program Enrollment')]", 180);
				if(verify2) {
					test.log(LogStatus.PASS, "Program Enrollment Report Viewed Successfully");
				}
				else {
					test.log(LogStatus.FAIL, "Report Not Loading");
					TakescreenShot(currentdriver,test);
					Assert.fail();
				}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click View Report Button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage IframeMenuExpandIcon() {
		try {
		click("//button[contains(@class,'pull-left')]");
		}catch(Exception e) {
		System.out.println(e);	
		}
		return this;
	}
	public PopulationManagerPage SecondIframe() {
		try{
//			int size = driver.findElements(By.tagName("iframe")).size();
//			System.out.println(size);
////			switchToFrame(2);
			switchToFrame("//iframe[contains(@name,'frame_0')]");
			Thread.sleep(3000);
			test.log(LogStatus.INFO, "Navigated to Reports Filter Page");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Navigate Reports Filer Page");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage Switchtoreportframe() {
		try{
			switchToFrame("//iframe[contains(@id,'reportContent')]");
			Thread.sleep(10000);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Navigate Report Frame");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage PopManagerNavigationIframe() {
		try{
			verifyelementpresentExplicitwait("//div[contains(@id,'appPopManager_menu')]");
			click("//div[contains(@id,'appPopManager_menu')]");
			Thread.sleep(2000);
			SwitchtoNewConfigframe();
			boolean verify=verifyelementpresentExplicitwait("//a[contains(@href,'content')]");			
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Poulation Manager app successfully");
			}else {
			test.log(LogStatus.FAIL,"Poulation Manager App Navigation Failed");
			TakescreenShot(currentdriver,test);
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Poulation Manager App");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage SwitchtoNewConfigframe() {
		try{
			switchToFrame("//iframe[contains(@id,'popmanager-iframe')]");
			Thread.sleep(10000);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Navigate Report Frame");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage RunReportNewframeConfig() {

			
		try{
			Point p = driver.findElementByXPath("//*[contains(text(),'Run Report')]//parent::button").getLocation();
			int x = p.getX();
			int y = p.getY();
			Dimension d = driver.findElementByXPath("//*[contains(text(),'Run Report')]//parent::button").getSize();
			int h = d.getHeight();
			int w = d.getWidth();
			Robot r = new Robot();
			r.mouseMove(x + (w/2), y+(h/2) +80);
			
			boolean verify2 = verifyelementpresentExplicitwait("//*[contains(text(),'Run Report')]", 180);
			if(verify2) {
				movetoelement("//*[contains(text(),'Run Report')]//parent::button");
				actionsclick("//*[contains(text(),'Run Report')]//parent::button");
				test.log(LogStatus.INFO, "Run Report Button Clicked");
			}
			else {
				test.log(LogStatus.FAIL, "Unable to click Run Report Button");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Run Report Button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage ReportVerificationIframe() {
		try{
				boolean verify2 = verifyelementpresentExplicitwait("//h3[contains(text(),'Program Enrollment')]", 180);
				if(verify2) {
					test.log(LogStatus.PASS, "Program Enrollment Report Viewed Successfully");
				}
				else {
					test.log(LogStatus.FAIL, "Report Not Loading");
					TakescreenShot(currentdriver,test);
					Assert.fail();
				}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click View Report Button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public PopulationManagerPage PopManagerLogin(String UserID,String Password) {
		try{
			boolean verify  = verifyelementpresentExplicitwait("//form[@name='login']");
			if(verify) {
			test.log(LogStatus.INFO,"Poulation Manager Login Page Appeared");
			clearelementusingkeys("//input[contains(@name,'username')]");
			clearAndType("//input[contains(@name,'username')]",UserID);
			clearelementusingkeys("//input[contains(@name,'password')]");
			clearAndType("//input[contains(@name,'password')]",Password);
			click("//button[contains(@type,'submit')]");
			}else {
				test.log(LogStatus.INFO,"Poulation Manager Login Page Not Appeared");
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Poulation Manager App");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	
	public void newwindow() {
	 	List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    String childWindow = windows.get(1); 
	    driver.switchTo().window(childWindow);
	    Dimension d = new Dimension(1920,1080);
		driver.manage().window().setSize(d);
	}
	public void newwindow2() {
	 	List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    String childWindow = windows.get(2); 
	    driver.switchTo().window(childWindow);
	    Dimension d = new Dimension(1920,1080);
		driver.manage().window().setSize(d);
	}
	public void closewindow() {
	 	List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    String parentWindow = windows.get(0); 
	    String childWindow = windows.get(1);
	    try {
	    driver.switchTo().window(childWindow).close();
	    driver.switchTo().window(parentWindow);
    	verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		actionsclick("//div[contains(@id,'appHome_menu')]");
	    }catch(Exception e) {
	    	test.log(LogStatus.FAIL,"Population Manager App Not Closed, Unable to Navigte to Home Page");
	    	TakescreenShot(currentdriver,test);
	    }
	}
	public void closewindow2() {
	 	List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    String parentWindow = windows.get(1); 
	    String childWindow = windows.get(2);
	    try {
	    driver.switchTo().window(childWindow).close();
	    driver.switchTo().window(parentWindow);
	    }catch(Exception e) {
	    	test.log(LogStatus.FAIL,"Population Manager App Not Closed, Unable to Navigte to Home Page");
	    	TakescreenShot(currentdriver,test);
	    }
	}
	public PopulationManagerPage PopManagerAppConfig() {
		test.log(LogStatus.SKIP, "Population Manager App Not Configured for this Environment");
		return this;
		
	}
}
