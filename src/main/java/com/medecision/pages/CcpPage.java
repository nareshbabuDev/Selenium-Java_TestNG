package com.medecision.pages;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CcpPage extends SeleniumBase
{

	protected  RemoteWebDriver driver;
	static String date1;
	public CcpPage(RemoteWebDriver driver, ExtentTest test) {
		
		super(driver, test);
		this.driver = driver;
		this.test =test;
		
	}

	public CcpPage CcpPageNavigation() {
		try {
				verifyelementpresentwithdelaytime("//a[contains(text(),'CCP')]", 10);
				driver.findElementByXPath("//a[contains(text(),'CCP')]").click();
				verifyelementpresentwithdelaytime("//td[contains(text(),'Care Plan Summary')]", 10);
				test.log(LogStatus.PASS, "<font color='green'>Navigated to CCP Page Successfully</font>");
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "<font color='red'>CCP Page Navigation Failed</font>");
				TakescreenShot(driver,test);
				test.log(LogStatus.FAIL, e.getLocalizedMessage());
			}
		return this;
	}
	
	public CcpPage CCPIssueLink()throws Exception {
		try {
			Thread.sleep(1000);
			driver.findElementByXPath("(//table[@class='ipccIssueListTable']//tr[@class='pagetext']//td/a[@class='issueLink'])[1]").click();
			Thread.sleep(1000);
			verifyelementpresentwithdelaytime("//td[contains(text(),'Update Issue')]", 10);
			test.log(LogStatus.PASS, "<font color='green'>Navigated to CCPEditPage Successfully</font>");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "<font color='red'>CCPEditPage Navigation Failed!!</font>");
			TakescreenShot(driver,test);
			test.log(LogStatus.FAIL, e.getLocalizedMessage());
		}
	return this;
   }
	
	public CcpPage UpdateCCP() throws Exception
	{
		Date d =new Date();
	    String todaydatetime = new SimpleDateFormat("MMM-dd-yyyy-HH-mm-ss").format(d);
	    String Issuetitle = "March '"+todaydatetime+"' ";
	    //Edit the Issue title 
	    System.out.println(todaydatetime);
	    Thread.sleep(1000);
		verifyelementpresentwithdelaytime("//input[@name='title']", 10);
		driver.findElementByXPath("//input[@name='title']").click();
		driver.findElementByXPath("//input[@name='title']").clear();
		Thread.sleep(1000);
		driver.findElementByXPath("//input[@name='title']").sendKeys(Issuetitle);
		Thread.sleep(1000);
		//Edit the Goal
		movetoelement("(//td[contains(text(),'Other Goals (1)')]/..//td[contains(@id,'goalSectionPlusMinus')])[1]");
		click("(//td[contains(text(),'Other Goals (1)')]/..//td[contains(@id,'goalSectionPlusMinus')])[1]");
		Thread.sleep(1000);
		driver.findElementByXPath("(//img[@title='Edit this goal'])[1]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//textarea[@name='goal_objectiveGoal']").sendKeys(Issuetitle);
		driver.findElementByXPath("//button[text()='Submit']").click();
		//Edit the Intervention
		movetoelement("(//td[contains(text(),'Other Interventions (1)')]/..//td[contains(@id,'interventionSectionPlusMinus')])[1]");
		click("(//td[contains(text(),'Other Interventions (1)')]/..//td[contains(@id,'interventionSectionPlusMinus')])[1]");
		Thread.sleep(1000);
		driver.findElementByXPath("(//img[@title='Edit this intervention'])[1]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//textarea[@name='interventionPlan1']").sendKeys(Issuetitle);
		movetoelement("//button[text()='Submit']");
		driver.findElementByXPath("//button[text()='Submit']").click();
		//Submit the issue
		verifyelementpresentwithdelaytime("//span[contains(text(),'Confirm: Save Issue')]", 10);
		Thread.sleep(3000);
		driver.findElementByXPath("//input[@value='OK']").click();
		Thread.sleep(1000);
		verifyelementpresentwithdelaytime("//td[contains(text(),'Care Plan Summary')]", 10);
		boolean Successful = verifyelementpresent("//td/a[@class='issueLink' and contains(text(),'"+Issuetitle+"')]]");
		if(Successful) {
			test.log(LogStatus.PASS, "<font color='green'>CCP-IssueTitle,Goal and Intervention Section is Updated Successfully</font>");
		}else{
			test.log(LogStatus.FAIL, "<font color='red'>CCP-IssueTitle,Goal and Intervention Section Updation Failed!!</font>");
			TakescreenShot(driver,test);
		}
		return this;
	}


}
