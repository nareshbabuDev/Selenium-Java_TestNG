package com.medecision.pages;

import java.sql.Time;
import java.util.*;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageChartPage extends SeleniumBase{
	String encounterchartfirstvvalue = null;
	List<String> careplan = null;
	List<String> patientSelect = new ArrayList<String>();
	List<String> requirename = new ArrayList<String>();
	List<String> requirenamevalue = new ArrayList<String>();
	String frequencybeforecount = null;
	String frequencyaftercount = null;
	String orgname = null;
	String firstchartvalue = null;

	public HomePageChartPage(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test =test;
	}
	public static void wait(int n) {
		try {
			Thread.sleep(n*(1000));
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public HomePageChartPage gotoEncounterchartandgetfirstvalue() {
		try {
			firstchartvalue = "(//*[contains(@style,'text-anchor: end;')])[1]";
			javascriptscrolltoelement(firstchartvalue);
			encounterchartfirstvvalue = driver.findElement(By.xpath(firstchartvalue)).getText();
			test.log(LogStatus.INFO, "Core service <font color = blue> '"+ encounterchartfirstvvalue + "'</font> is selected in the Encounter chart");
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage clickOk() {
		try {
		verifyelementpresentExplicitwait("//span[text()='OK']", 2);
		click("//span[text()='OK']");
		}
		catch(Exception e) {TakescreenShot(driver,test);}
		return this;
	}
	public HomePageChartPage selectchart() {
		try {
			System.out.println("Printing 2");
			verifyelementClickableExplicitwait("//*[@class='discreteBar'])[1]", 4);
			boolean chartbar = driver.findElements(By.xpath("(//*[@class='discreteBar'])[1]")).size() != 0;
			if(chartbar==true) {
				driver.findElement(By.xpath("(//*[@class='discreteBar'])[1]")).click();
				try {
				boolean okButton = verifyelementpresentExplicitwait("//span[text()='OK']", 4);
				if(okButton) {
				click("//span[text()='OK']");
				}}catch(Exception e) {}
				verifyelementpresentExplicitwait("//div[normalize-space(@aria-label)='Frequency Care Plan Encounter']//div[4]", 10);
				List<WebElement> patientID = driver.findElements(By.xpath("//div[normalize-space(@aria-label)='Frequency Care Plan Encounter']//div[4]"));
				for(WebElement pidsel : patientID) {
					patientSelect.add(pidsel.getText().trim());
				}
			}
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage selectchart1() {
		try {
			System.out.println("printing 1");
			verifyelementClickableExplicitwait("//*[@class='discreteBar'])[1]", 4);
			boolean chartbar = driver.findElements(By.xpath("(//*[@class='discreteBar'])[1]")).size() != 0;
			if(chartbar==true) {
//				verifyelementClickableExplicitwait("//*[@class='discreteBar'])[1]", 4);
				driver.findElement(By.xpath("(//*[@class='discreteBar'])[1]")).click();
				boolean okButton = verifyelementpresentExplicitwait("//span[text()='OK']", 4);
				if(okButton) {
				click("//span[text()='OK']");
				test.log(LogStatus.INFO, "Frequency is <font color=blue>'0'</font> as there is no existing encounters with core service <font color=blue>'"+ encounterchartfirstvvalue +"'</font> in chart");
				}
			}
		}catch(Exception e) {TakescreenShot(driver,test);}
		return this;
	}

	public HomePageChartPage selectthechartb(String pid) {
		try {
			for(int i=0;i<patientSelect.size();i++) {
				if(patientSelect.get(i).trim().equals(pid)) {
					verifyelementpresentExplicitwait("//div[normalize-space(text())='"+pid+"']", 5);
					click("//div[normalize-space(text())='"+pid+"']");
				}else {
					continue;
				}
			}
//			System.out.println("Running 3");
			test.log(LogStatus.INFO, "Select the patient id : " + pid);
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage selectthecharta(String pid) {
		try {
			for(int i=0;i<patientSelect.size();i++) {
				if(patientSelect.get(i).trim().equals(pid)) {
					verifyelementpresentExplicitwait("//div[normalize-space(text())='"+pid+"']", 5);
					click("//div[normalize-space(text())='"+pid+"']");
				}else {
					continue;
				}
			}
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage CarePlanAppNavigation() {
		try {
			System.out.println("careplan");
			boolean careplanApp =verifyelementpresentExplicitwait("//div[@id='appCareplan_menu']",10);
			if(careplanApp) {
			click("//div[@id='appCareplan_menu']");
			careplan = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(careplan.get(1));
			driver.manage().window().maximize();
			test.log(LogStatus.INFO, "Navigated to CarePlan App successfully");
			}
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Navigation to CarePlan App is failed");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public HomePageChartPage PatientSearch(String PID) throws Exception {
		try {
			verifyelementpresentExplicitwait("//div/a[contains(text(),'Search')]", 5);
			test.log(LogStatus.INFO, "Navigate to PatientSearch Page");
			driver.findElementByXPath("//div/a[contains(text(),'Search')]").click();
			boolean verify = verifyelementpresentExplicitwait("//td/input[@name='mrn']", 2);
			if(verify) {
			driver.findElementByXPath("//td/input[@name='mrn']").click();
			driver.findElementByXPath("//td/input[@name='mrn']").clear();
			test.log(LogStatus.INFO, "Enter the Patient Id in SearchField : <font color='blue'>'"+PID+"'</font>");
			driver.findElementByXPath("//td/input[@name='mrn']").sendKeys(PID);
			verifyelementpresentExplicitwait("//td/input[@name='lookup']", 2);
			driver.findElementByXPath("//td/input[@name='lookup']").click();
			}
			boolean a = verifyelementpresent("//td[@class='tdsectionheader' and contains(text(),'Demographics')]");
			if(a){
				test.log(LogStatus.PASS, "<font color='green'>PatientDemographicsPage is Navigated Successfully</font>");
			}else{
				test.log(LogStatus.FAIL, "<font color='red'>Navigation to PatientDemographicsPage is Failed!!</font>");
				TakescreenShot(driver,test);
				Assert.fail();
			}
			boolean b = verifyelementpresentExplicitwait("//li/a[text()='Encounter Information']", 10);
			if(b){
				test.log(LogStatus.INFO, "EncounterPage is Navigated Successfully");
				driver.findElementByXPath("//li/a[text()='Encounter Information']").click();
			}else{
				test.log(LogStatus.FAIL, "<font color='red'>Navigation to EncounterPage Failed!!</font>");
				TakescreenShot(driver,test);
				Assert.fail();
			}
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage getchartfrequencycountbefore() {
		try {
			frequencybeforecount = driver.findElement(By.xpath("//table[@class='fixed_headers']//tbody/tr/td")).getText();
			System.out.println("Before Count : " + frequencybeforecount);
			test.log(LogStatus.INFO, "Encounter bar frequency in chart before adding new encounter : <font color='blue'> '"+frequencybeforecount+"'</font>");
//			System.out.println("Running 4");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Didn't get frequency of the encounter bar");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public HomePageChartPage getchartfrequencycountafter() {
		try {
			frequencyaftercount = driver.findElement(By.xpath("//table[@class='fixed_headers']//tbody/tr/td")).getText();
			System.out.println("After Count : " + frequencyaftercount);
			test.log(LogStatus.INFO, "Encounter bar frequency in chart after adding new encounter : <font color='blue'> '"+frequencyaftercount+"'</font>");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Didn't get frequency of the encounter bar. Chart is not loading.");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public HomePageChartPage closefrequencypage() {
		try {
			click("//button[@id='btnConfirmNo']");
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage closeencounterpage() {
		try {
			click("//md-dialog[@aria-label='View Patients']//button[@aria-label='Cancel']");
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage closechartfreq() {
		try {
			closefrequencypage();
			closeencounterpage();
//			System.out.println("Running 5");
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage clickcreatenewinencounter() {
		try {
			verifyelementClickableExplicitwait("//a[text()='Create New']", 5);
			driver.findElement(By.xpath("//a[text()='Create New']")).click();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage selectAdmidDate() {
		try {
			verifyelementpresentExplicitwait("//input[@name='addate']//following::img[@name='imgCalendar']", 10);
			String date = currentDate("dd-MMM-yyyy");
			click("//input[@name='addate']//following::img[@name='imgCalendar']");
			verifyelementpresentExplicitwait("//input[@name='addate']", 3);
			WebElement admitdate = driver.findElement(By.xpath("//input[@name='addate']"));
			String Date = admitdate.getAttribute("value");
			System.out.println(Date+"gotdate");
			if(Date.isEmpty()) {
			 System.out.println("Date printing");
			 click("//input[@name='addate']");
			 admitdate.sendKeys(date);
			 new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
			}	
		}catch (Exception e) {
			System.out.println(e.getMessage());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage orgnameget() {
		orgname = driver.findElement(By.xpath("//td[contains(text(),'Primary Organization:')]//following::table[@class='dataVal']//td[normalize-space(text())]")).getText().trim();
		return this;
	}
	public void createencounterwithrequiredfields() {
		List<WebElement> names = driver.findElements(By.xpath("//select[@class='clientEncInput']"));
		for(WebElement name : names) {
			requirename.add(name.getAttribute("name"));
		}
		System.out.println(requirename);
		List<WebElement> names_1 = driver.findElements(By.xpath("//font[text()='*']//parent::td[normalize-space(text())]"));
		for(WebElement name : names_1) {
			requirenamevalue.add(name.getText().replaceAll(":", "").replaceAll("\\*", "").trim());
		}
		System.out.println(requirenamevalue);

	}
	public HomePageChartPage selecttarget() {
		try {
            verifyelementpresentExplicitwait("//select[@id='type']", 2);
			driver.findElement(By.xpath("//select[@id='type']")).click();
			verifyelementpresentExplicitwait("(//select[@id='type']/option)[2]", 2);
			driver.findElement(By.xpath("(//select[@id='type']/option)[2]")).click();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage selectvisittype() {
		try {
			verifyelementpresentExplicitwait("//select[@id='patientclass']", 2);
			driver.findElement(By.xpath("//select[@id='patientclass']")).click();
			verifyelementpresentExplicitwait("(//select[@id='patientclass']/option)[2]", 2);
			driver.findElement(By.xpath("(//select[@id='patientclass']/option)[2]")).click();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage selectencountertype() {
		try {
			verifyelementpresentExplicitwait("//select[@id='admittype']", 2);
			driver.findElement(By.xpath("//select[@id='admittype']")).click();
			verifyelementpresentExplicitwait("(//select[@id='admittype']/option)[2]", 2);
			driver.findElement(By.xpath("(//select[@id='admittype']/option)[2]")).click();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage selectencoreservice() {
		try {
			verifyelementpresentExplicitwait("//select[@id='service']", 2);
			driver.findElement(By.xpath("//select[@id='service']")).click();
			verifyelementpresentExplicitwait("//select[@id='service']/option[normalize-space(text())='\"+encounterchartfirstvvalue+\"']", 2);
			driver.findElement(By.xpath("//select[@id='service']/option[normalize-space(text())='"+encounterchartfirstvvalue+"']")).click();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage selectstatus() {
		try {
			verifyelementpresentExplicitwait("//select[@id='status']", 2);
			driver.findElement(By.xpath("//select[@id='status']")).click();
			verifyelementpresentExplicitwait("//select[@id='status']/option[@value='2']", 2);
			driver.findElement(By.xpath("//select[@id='status']/option[@value='2']")).click();
		}catch (Exception e) {
			System.out.println(e.getCause());
		}
		return this;
	}
	public HomePageChartPage program() {
		try {
			boolean b = driver.findElements(By.xpath("//table[@id='encounterProgram']//span[@class='red']")).size() != 0;
			if(b==true) {
				List<WebElement> programsselect = driver.findElements(By.xpath("//input[@type='checkbox']"));
				int a = new Random().nextInt(programsselect.size());
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", programsselect.get(a));
				programsselect.get(a).click();
			}
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage refreshbutton() {
		try {
			verifyelementpresentExplicitwait("//md-icon[@aria-label='refresh']", 2);
			driver.findElement(By.xpath("//md-icon[@aria-label='refresh']")).click();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage clickencountersavebutton() {
		try {
			WebElement clicksave = driver.findElement(By.xpath("//input[@value='Save']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", clicksave);
			clicksave.click();
			verifyelementpresentExplicitwait("//a[text()='Logoff']", 2);
			driver.findElement(By.xpath("//a[text()='Logoff']")).click();
			test.log(LogStatus.PASS, "<font color = green> New Encounter is successfully created </font>");
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "<font color = red> New Encounter is not successfully created </font>");
			TakescreenShot(driver,test);
			Assert.fail();
		}
		return this;
	}
	public HomePageChartPage verifyChart() {
		try {
			selectchart1();
		}catch(Exception e) {TakescreenShot(driver,test);}
		return this;
	}
	public HomePageChartPage getencounterfrequencyCountb(String pid) {
		System.out.println("printing 1");
		verifyelementClickableExplicitwait("//*[@class='discreteBar'])[1]", 4);
		boolean chartbar = driver.findElements(By.xpath("(//*[@class='discreteBar'])[1]")).size() != 0;
		if(chartbar==true) {
//			verifyelementClickableExplicitwait("//*[@class='discreteBar'])[1]", 4);
			driver.findElement(By.xpath("(//*[@class='discreteBar'])[1]")).click();
			boolean okButton = verifyelementpresentExplicitwait("//span[text()='OK']", 4);
			if(okButton) {
			click("//span[text()='OK']");
			test.log(LogStatus.INFO, "Frequency is '0' as there is no existing encounters with core service '"+ encounterchartfirstvvalue +"' in chart");
			}else {
				verifyelementpresentExplicitwait("//div[normalize-space(@aria-label)='Frequency Care Plan Encounter']//div[4]", 10);
				List<WebElement> patientID = driver.findElements(By.xpath("//div[normalize-space(@aria-label)='Frequency Care Plan Encounter']//div[4]"));
				for(WebElement pidsel : patientID) 
					patientSelect.add(pidsel.getText().trim());
				selectthechartb(pid);
				getchartfrequencycountbefore();
				closechartfreq();
			}
		}	
		return this;

	}
	public HomePageChartPage getencounterfrequencyCounta(String pid) {
		try {
			driver.switchTo().window(careplan.get(0));
			refreshbutton();
			Thread.sleep(2000);
			refreshbutton();
			javascriptscrolltoelement(firstchartvalue);
			selectchart();
			selectthecharta(pid);
			getchartfrequencycountafter();
			closechartfreq();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage beforeandaftercountcompare() {
		try {
			int beforecount = Integer.parseInt(frequencybeforecount);
			int aftercount = Integer.parseInt(frequencyaftercount);
			if((beforecount+1)==aftercount) {
				test.log(LogStatus.PASS, "<font color = green>Encounter bar frequency is updated</font>");
			}else {
				test.log(LogStatus.FAIL, "<font color = red>Encounter bar frequency is not updated</font>");
				TakescreenShot(driver,test);
				Assert.fail();
			}
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
	public HomePageChartPage createEncounter(String PID) throws Exception {
		try {
			CarePlanAppNavigation();PatientSearch(PID);
			clickcreatenewinencounter();selectAdmidDate();selecttarget();
			selectvisittype();selectencountertype();selectencoreservice();
			selectstatus();program();clickencountersavebutton();
		}catch (Exception e) {
			System.out.println(e.getCause());
			TakescreenShot(driver,test);
		}
		return this;
	}
}