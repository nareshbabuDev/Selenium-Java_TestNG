package com.medecision.pages;

import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TaskManagerPage extends SeleniumBase {

	
	
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	
	public TaskManagerPage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	}
	public TaskManagerPage TaskmanagerConfig() throws Exception{
		test.log(LogStatus.SKIP, "Task Manager Not Configured for this Environment");
		return this;
	}
	public TaskManagerPage TaskManagerNavigation() {
		try {
			click("//div[contains(@id,'appTaskList_menu')]");
			try {
			verifyelementpresentwithdelaytime("//md-icon[@md-svg-icon='plus']", 10);
			test.log(LogStatus.PASS, "Navigated to Task Manager Page Successfully");
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Task Manager Navigation Failed");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "DashBoard Navigation Failed !");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			return this;	
	}
	public TaskManagerPage OverDueCountVerification() {
		try {
			Thread.sleep(2000);
		String BadgeCount =getElementText("//div[contains(@id,'appTaskList_badge')]").trim();
		Thread.sleep(2000);
		String OverDueCount =getElementText("//div[contains(@id,'overdueTaskCount')]").trim();
		int count= Integer.parseInt(BadgeCount);
		int count2 =Integer.parseInt(OverDueCount);
		if (count==count2) {
			test.log(LogStatus.PASS, "TaskManager Badge Count and Over Due Task Count In Summary DashBoard is Equal<br>"
					+ "Task Manager Badge Count:<font color='blue'>"+BadgeCount+"</font><br>"
					+ "Over Due Task Count In Summary DashBoard:<font color='blue'>"+OverDueCount+"</font>");
		}else {
			test.log(LogStatus.FAIL, "Task Count is Not Equal !!");
			TakescreenShot(currentdriver,test);
		}
			
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Task Counts not Appeared on the TaskManager Icon");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
		
	}
	Date d =new Date();
	String todaydatetime = new SimpleDateFormat("MMM-dd-yyyy-HH-mm-ss").format(d);
	public TaskManagerPage CreateNewTask(String LN,String PID)  throws InterruptedException {
		try {
			click("//md-icon[@md-svg-icon='plus']");
			try {
			verifyelementpresentwithdelaytime("//input[contains(@name,'title')]", 10);
			test.log(LogStatus.INFO, "Navigated to Task Add Page");
			verifyelementpresentExplicitwait("//input[contains(@name,'title')]");
			click("//input[contains(@name,'title')]");
			clearAndType("//input[contains(@name,'title')]", "Test Task Title_"+todaydatetime);
			verifyelementpresentExplicitwait("//textarea[contains(@name,'detail')]");
			click("//textarea[contains(@name,'detail')]");
			clearAndType("//textarea[contains(@name,'detail')]", "Test Task Description_"+todaydatetime);
			clearAndType("//md-input-container//child::input[contains(@aria-label,'Search')]", LN);
			try {
				verifyelementpresentExplicitwait("//span[contains(text(),'"+PID+"')]//parent::md-autocomplete-parent-scope",180);
				click("//span[contains(text(),'"+PID+"')]//parent::md-autocomplete-parent-scope");
				test.log(LogStatus.INFO, "Task Creating with PatientID of:<font color='blue'>"+PID+"</font> <br> Patient Last Name is:<font color='blue'>"+LN+"</font>");
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Unable to Add the Patient for this Task");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			try {
			verifyelementpresentExplicitwait("//button[contains(@id,'btnOk')]");	
			actionsclick("//button[contains(@id,'btnOk')]");
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			verifyelementpresentExplicitwait("//md-icon[@md-svg-icon='plus']//following::md-icon[@md-svg-icon='refresh']");
			click("//md-icon[@md-svg-icon='plus']//following::md-icon[@md-svg-icon='refresh']");
			Thread.sleep(5000);
			verifyelementpresentExplicitwait("(//div[contains(@class,'task-item-text')])[1]//h3");
			String value =getElementText("(//div[contains(@class,'task-item-text')])[1]//h3");
			String DateTimeArray [] =value.split("_");
			String DateTime=DateTimeArray[DateTimeArray.length-1];
			if(DateTime.contains(todaydatetime)) {
				test.log(LogStatus.PASS, "Created Task Appeared in the Task Manager Page<br>"
						+ "Task Title is :<font color=blue>"+"Test Task Title_"+todaydatetime+"</font>");
			}else {
				TakescreenShot(currentdriver,test);
				test.log(LogStatus.FAIL, "Created Task Not Appeared in the Task Manager Page");
				
				Assert.fail();
			}
			}catch(Exception e) {
				TakescreenShot(currentdriver,test);
				test.log(LogStatus.FAIL, "Unable to Add the Task !!");
				
				Assert.fail();
			}
			}catch(Exception e) {
				TakescreenShot(currentdriver,test);
				test.log(LogStatus.FAIL, "Unable to Navigate Task Add Page");
				
				Assert.fail();
			}
		return this;
	}
	ArrayList<String> list=new ArrayList<String>();
	String Count;
	int count;
	public TaskManagerPage taskRefreshandGetCount() {
		try {
			verifyelementpresentExplicitwait("(//span[@class='ng-binding ng-scope' and text()='Task Summary Dashboard'])[1]",180);
			actionsclick("(//span[@class='ng-binding ng-scope' and text()='Task Summary Dashboard'])[1]");
			test.log(LogStatus.PASS, "Navigated to Task Summary Dashboard");
			}catch(Exception e) {
			test.log(LogStatus.FAIL, "Navigation to Task Summary Dashboard is failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
		try {
			verifyelementpresentExplicitwait("//md-icon[@md-svg-icon='refresh' and @aria-label='refresh']",10);
			actionsclick("//md-icon[@md-svg-icon='refresh' and @aria-label='refresh']");
			test.log(LogStatus.INFO, "Refresh button is clicked");
			}catch(Exception e) {
			test.log(LogStatus.FAIL, "Refresh button is not clicked");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
		try {
			System.out.println("Entered");
			verifyelementpresentExplicitwait("//div[@class='q-card-title' and contains(text(),'All')]",10);
			String Count =getElementText("//div[@class='q-card-title' and contains(text(),'All')]").trim();
			Count = Count.replaceAll("[^\\d]", " ");
			Count = Count.trim();
			count = Integer.parseInt(Count);
			System.out.println("Entered2");
			System.out.println(Count);
			list.add(Count);
			test.log(LogStatus.INFO, "Count of All task is <font color='blue'>'"+Count+"' </font>");
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Task Summary Dashboard is down");
				TakescreenShot(currentdriver,test);
				Assert.fail();
				}
		
		return this;
	}
	int i,j,k;
	public TaskManagerPage compareCount() {
		i=Integer.parseInt(list.get(0));
		j = Integer.parseInt(list.get(1));
		if(i<j) {
			test.log(LogStatus.PASS, "<font color='blue'>Added Task is successfully reflected in Task Summary Dashboard</font>");
		}
		else {
			test.log(LogStatus.FAIL, "Added Task is not successfully reflected in Task Summary Dashboard");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public TaskManagerPage ChangeToMyTeamData() {
		verifyelementpresentExplicitwait("//span[@id='dropdown1']//md-icon[@md-svg-icon='menu']",5);
		click("//span[@id='dropdown1']//md-icon[@md-svg-icon='menu']");
		verifyelementpresentExplicitwait("//md-checkbox//div//following::div//span[normalize-space(text())='Select All']",10);
		boolean verify1 = verifyelementpresentExplicitwait("//md-checkbox//div//following::div//span[normalize-space(text())='Select All']");
		if(verify1) {
			click("//md-checkbox//div//following::div//span[normalize-space(text())='Select All']");
			test.log(LogStatus.INFO, "Changed MyView'sData to show MyTeam'sData Task Dashboard");
			click("//span[text()='Apply']");
		}
		else {
			test.log(LogStatus.FAIL, "MyTeam'sData Task Dashboard is not selected");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
//		}catch(Exception e) {}
		return this;
	}
	public TaskManagerPage compareCountAsSupervisor() {
		j=Integer.parseInt(list.get(1));
		k = Integer.parseInt(list.get(2));
		if(j==k) {
			test.log(LogStatus.PASS, "<font color='blue'>Added Task is successfully reflected in the Task Summary Dashboard of the Supervisor user</font>");
		}
		else {
			test.log(LogStatus.FAIL, "<font color = 'red'>Added Task is not successfully reflected in Task Summary Dashboard of the Supervisor user</font>");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	

}
