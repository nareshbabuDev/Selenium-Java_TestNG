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

public class PatientRemovalPage extends SeleniumBase {
	protected RemoteWebDriver driver;

	public PatientRemovalPage(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		this.driver = driver;
		this.test = test;
	}

	public PatientRemovalPage Removepatients() throws Exception {
		boolean elementpresent = verifyelementpresent("//md-sidenav[contains(@id,'MyPatientList')]//*[contains(text(),'Showing')]");
		if (elementpresent) {
			Thread.sleep(2000);

			String patientcount = driver.findElement(By.xpath("//md-sidenav[contains(@id,'MyPatientList')]//*[contains(text(),'Showing')]")).getText();
			// String s = "Showing 30 of 30 patients";
			String str = patientcount.replace(" ", "");
			int begin = str.indexOf('g');
			int end = str.lastIndexOf('o');

			int Totalpatient = Integer.parseInt(str.substring(begin + 1, end));
           System.out.println(Totalpatient);
			for (int i = 1; i <= Totalpatient; i++) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//i[@class='q-icon material-icons q-item-icon'])/..")).click();

				Thread.sleep(2000);

				WebElement element = driver.findElement(By.xpath("//div[contains(text(),'Remove from Panel')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				element.click();
				driver.findElement(By.xpath("//div[contains(text(),'Yes')]/../..")).click();
			}

			boolean b = verifyelementpresent("//md-sidenav[contains(@id,'MyPatientList')]//*[contains(text(),'No')] ");
			if (b) {
				test.log(LogStatus.PASS, "Patients in PatientPanel removed Successfully!");
			} else {
				test.log(LogStatus.FAIL, "Patients Removal Failed");
				TakescreenShot(driver,test);
				Assert.fail();
			}

			
		} else {

			test.log(LogStatus.INFO, "No Patients found to remove");

		}
		
		return this;
	}

}
