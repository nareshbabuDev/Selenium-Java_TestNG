package com.medecision.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.*;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.github.javafaker.Faker;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.util.Random;

public class ModifyPatientPage extends SeleniumBase   {
	protected  RemoteWebDriver currentdriver;
	protected ExtentTest test;
	TestDataVerification TestDataVerification = new TestDataVerification(currentdriver,test);
	Faker faker = new Faker();
	String Address1 = faker.address().streetAddress();
	boolean CountyConfig=false;
	String Prefname,IdefGender,PrefLang,StretAd2,StretAd22,SecCity,SecState,SecZip,SecTele,ExpDate;
	String Midname,ActScore,StrtAd1,Strad2,PrimCity,Primzip,PrimTele,PrimState,County,Primemail,Mrn,SSN;
	String Ethn,Race,EmFname,EmLname,EmMname,EmTele,Ememail,Relation,PrimPayerclass,PrimPayerplan,PrimMediId,PrimEffdate,SecPayerclass,SecPayerplan,SecMediId,SecEffdate,SecExpdate;
	LinkedHashMap<String, String> UpdatingValues =  new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> UpdatedValues =  new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> UpdatedCarebookValues =  new LinkedHashMap<String, String>();
	//constructor
	public ModifyPatientPage(RemoteWebDriver driver,ExtentTest test) {
	super(driver,test);
	this.currentdriver = driver;
	this.test =test;
	//faker input values
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	Date currentDate = new Date();
    // convert date to calendar
    Calendar c = Calendar.getInstance();
    c.setTime(currentDate);
    // manipulate date
    c.add(Calendar.YEAR,0);
    c.add(Calendar.MONTH,0);
    c.add(Calendar.DATE, -3); 
    Date currentDateMinusOne = c.getTime();
    System.out.println(dateFormat.format(currentDateMinusOne));
	Date d =new Date();
	ExpDate = new SimpleDateFormat("MM/dd/yyyy").format(d);
	Prefname=faker.name().firstName().replaceAll("[-+.^:,']","");
	StretAd2=faker.address().streetAddress().replaceAll("[-+.^:,']","");
	StretAd22=faker.address().streetAddress().replaceAll("[-+.^:,']","");
	SecCity=faker.address().cityName().replaceAll("[-+.^:,']","");
	SecZip=randomzipcode();
	SecTele=randomphonenumber().replaceAll("-", "");
	//old fields
	Midname=faker.name().firstName().replaceAll("[-+.^:,']","");
	ActScore=Integer.toString((int) ((Math.random() * 4) + 1))+".000";
	StrtAd1=faker.address().streetAddress().replaceAll("[-+.^:,']","");
	Strad2=faker.address().streetAddress().replaceAll("[-+.^:,']","");
	PrimCity=faker.address().cityName().replaceAll("[-+.^:,']","");
	Primzip=randomzipcode();
	PrimTele=randomphonenumber().replaceAll("-", "");
	Primemail=Midname.toLowerCase()+Prefname.toLowerCase()+"@test.com";
	Mrn="TestMrn"+Integer.toString((int) ((Math.random() * 4) + 1));
	EmFname=faker.name().firstName().replaceAll("[-+.^:,']","");
	EmLname=faker.name().lastName().replaceAll("[-+.^:,']","");
	EmMname=faker.name().firstName().replaceAll("[-+.^:,']","");
	EmTele=randomphonenumber().replaceAll("-", "");
	Ememail=EmFname.toLowerCase()+EmLname.toLowerCase()+"@test.com";
	PrimMediId="TEST00"+Integer.toString((int) ((Math.random() * 6) + 1));
	PrimEffdate=dateFormat.format(currentDateMinusOne);
	SecMediId="TEST11"+Integer.toString((int) ((Math.random() * 6) + 1));
	SecEffdate=dateFormat.format(currentDateMinusOne);
	SecExpdate=new SimpleDateFormat("MM/dd/yyyy").format(d);
	SSN=randomssn().replaceAll("-", "");
	//add it to map
	UpdatingValues.put("<span style='color: blue'>Preferred Name</span>",Prefname);
	UpdatingValues.put("<span style='color: blue'>Address 1 Appartment</span>",StretAd2);
	UpdatingValues.put("<span style='color: blue'>Address 2 Appartment</span>",StretAd22);
	UpdatingValues.put("<span style='color: blue'>Secondary City</span>",SecCity);
	UpdatingValues.put("<span style='color: blue'>Secondary Zip Code</span>",SecZip);
	UpdatingValues.put("<span style='color: blue'>Telephone 2</span>",SecTele);
	UpdatingValues.put("<span style='color: blue'>Primary Payer Expiry Date</span>",ExpDate);
	//old fields to map
	UpdatingValues.put("<span style='color: blue'>Middle Name</span>",Midname);
	UpdatingValues.put("<span style='color: blue'>SSN</span>",SSN.substring(SSN.length()-4));
	UpdatingValues.put("<span style='color: blue'>Acuity Score</span>",ActScore);
	UpdatingValues.put("<span style='color: blue'>Address 1</span>",StrtAd1);
	UpdatingValues.put("<span style='color: blue'>Address 2</span>",Strad2);
	UpdatingValues.put("<span style='color: blue'>City</span>",PrimCity);
	UpdatingValues.put("<span style='color: blue'>Zip Code</span>",Primzip);
	UpdatingValues.put("<span style='color: blue'>Telephone 1</span>",PrimTele);
	UpdatingValues.put("<span style='color: blue'>Email Address</span>",Primemail);
	UpdatingValues.put("<span style='color: blue'>MRN</span>",Mrn);
	UpdatingValues.put("<span style='color: blue'>Emergency First Name</span>",EmFname);
	UpdatingValues.put("<span style='color: blue'>Emergency Last Name</span>",EmLname);
	UpdatingValues.put("<span style='color: blue'>Emergency Middle Name</span>",EmMname);
	UpdatingValues.put("<span style='color: blue'>Emergency Telephone</span>",EmTele);
	UpdatingValues.put("<span style='color: blue'>Emergency Email</span>",Ememail);
	UpdatingValues.put("<span style='color: blue'>Primary PayerID</span>",PrimMediId);
	UpdatingValues.put("<span style='color: blue'>Primary Payer Effective Date</span>",PrimEffdate);
	UpdatingValues.put("<span style='color: blue'>Secondary PayerID</span>",SecMediId);
	UpdatingValues.put("<span style='color: blue'>Secondary Payer Effective Date</span>",SecEffdate);
	UpdatingValues.put("<span style='color: blue'>Secondary Payer Expiry Date</span>",SecExpdate);
	}
	// patient updation method
	public ModifyPatientPage PatientDetailsFieldUpdation(String PatientID) {
		try{
			UpdatePrefName().
			UpdateStreetAd2().
			UpdateStreetAd22().
			UpdateCity2().
			UpdateZip2().
			UpdateExpirydate().
			UpdateTeleph2().
			UpdateIdefGender().
			UpdatePreflang().
			UpdateSecState().
			//old fields
			UpdateMidName().
			UpdateActScore()
			.UpdateSSN()
			.UpdateStrtAd1() 
			.UpdateStrtAd2()
			.UpdatePrimCity()
			.UpdatePrimZip()
			.UpdatePrimTele()
			.UpdatePrimEmail()
			.UpdateMrn()
			.UpdateEmFname()
			.UpdateEmLname()
			.UpdateEmMname()
			.UpdateEmTele()
			.UpdateEmEmail()
			.UpdatePriPayerClass()
			.UpdatePriPayerplan()
			.UpdatePrimMedId()
			.UpdatePrimEffDate()
			.UpdateSecPayerClass()
			.UpdateSecPayerplan()
			.UpdateSecMedId() 
			.UpdateSecEffDate()
			.UpdateSecExpirydate()
			.UpdateState1()
			.UpdateCounty()
			.UpdateEthnicity()
			.UpdateRace()
			.UpdateRelation()
			.SaveButton().
			OkButton().
			UpdatedFieldsVerification().
			DemographicsUpdatedValuesAreEqual(UpdatingValues,UpdatedValues,true,"Modify Page","Updated Page");
			clickCarebookInDemogApp(PatientID);
			new CarebookSearchPage(driver,test).CarebookDemographicsnavigation();
			UpdatedFieldsCareBookVerification();
//			Set<String> values1 = new HashSet<>(UpdatedCarebookValues.values());
//			Set<String> values2 = new HashSet<>(UpdatingValues.values());
//			boolean equal = values1.equals(values2);
//			System.out.println(equal);
			DemographicsUpdatedValuesAreEqual(UpdatingValues,UpdatedCarebookValues,true,"Modify Page","Carebook Demographics Information Page");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint38 Dashboard 13023 13024 13052 13071 13078 DemographicsApp New Fields Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	//  fields updation -old dropdown fields
	public ModifyPatientPage UpdateSecPayerplan() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");		
		click("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		movetoelement("((//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Care')])[1]");		
		SecPayerplan = getElementText("((//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Care')])[1]");	
		click("((//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Care')])[1]");	
		UpdatingValues.put("<span style='color: blue'>Secondary Payer Plan</span>", SecPayerplan);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> Secondary Payer Plan Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateSecPayerClass() {
		try{
			verifyelementpresentExplicitwait("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
			movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");	
			String payerclass=getElementText("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
			switch(payerclass) {
			case "medicare":
				movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");	
				click("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
				movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				Thread.sleep(2000);
				click("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				UpdatingValues.put("<span style='color: blue'>Secondary Payer Class</span>", "Medicaid");
				break;
			case "medicaid":
				movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");	
				click("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
				movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicare']");
				Thread.sleep(2000);
				click("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicare']");
				UpdatingValues.put("<span style='color: blue'>Secondary Payer Class</span>", "Medicare");
				break;
			default:
				movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");	
				click("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
				movetoelement("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				Thread.sleep(2000);
				click("(//label[contains(text(),'Secondary')]//following::label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				UpdatingValues.put("<span style='color: blue'>Secondary Payer Class</span>", "Medicaid");
				break;
			}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> Secondary Payer Class Updation Failed</span>");
				TakescreenShot(currentdriver,test);
				TestDataVerification.NavigatetoHome();
				Assert.fail();
			}
		return this;
	}
	public ModifyPatientPage UpdatePriPayerplan() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");		
		click("(//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		movetoelement("((//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Care')])[1]");		
		PrimPayerplan = getElementText("((//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Care')])[1]");	
		click("((//label[contains(text(),'Payer Plan')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Care')])[1]");	
		UpdatingValues.put("<span style='color: blue'>Primary Payer Plan</span>", PrimPayerplan);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> Primary Payer Plan Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePriPayerClass() {
		try{
			verifyelementpresentExplicitwait("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
			movetoelement("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			String payerclass=getElementText("(//label[contains(text(),'Payer Class')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
			switch(payerclass) {
			case "medicare":
				click("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
				movetoelement("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				click("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				UpdatingValues.put("<span style='color: blue'>Primary Payer Class</span>", "Medicaid");
				break;
			case "medicaid":
				click("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
				movetoelement("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicare']");
				click("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicare']");
				UpdatingValues.put("<span style='color: blue'>Primary Payer Class</span>", "Medicare");
				break;
			default:
				click("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
				movetoelement("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				click("(//label[contains(text(),'Payer Class')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::td[contains(@class,'pickList')]//div[text()='Medicaid']");
				UpdatingValues.put("<span style='color: blue'>Primary Payer Class</span>", "Medicaid");
				break;
			}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> Primary Payer Class Updation Failed</span>");
				TakescreenShot(currentdriver,test);
				TestDataVerification.NavigatetoHome();
				Assert.fail();
			}
		return this;
	}
	public ModifyPatientPage UpdateRelation() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		String relation=getElementText("(//label[contains(text(),'Relation')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
		switch(relation) {
		case "father":
			movetoelement("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Mother')]");
			click("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Mother')]");
			UpdatingValues.put("<span style='color: blue'>Relation</span>", "Mother");
			break;
		case "mother":
			movetoelement("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Father')]");
			click("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Father')]");
			UpdatingValues.put("<span style='color: blue'>Relation</span>", "Father");
			break;
		default:
			movetoelement("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Father')]");
			click("(//label[contains(text(),'Relation')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Father')]");
			UpdatingValues.put("<span style='color: blue'>Relation</span>", "Father");
			break;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> RelationShip Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateRace() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		String Race=getElementText("(//label[contains(text(),'Race')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
		switch(Race) {
		case "white":
			movetoelement("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Asian')]");
			click("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Asian')]");
			UpdatingValues.put("<span style='color: blue'>Race</span>", "Asian");
			break;
		case "asian":
			movetoelement("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'White')]");
			click("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'White')]");
			UpdatingValues.put("<span style='color: blue'>Race</span>", "White");
			break;
		default:
			movetoelement("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Asian')]");
			click("(//label[contains(text(),'Race')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Asian')]");
			UpdatingValues.put("<span style='color: blue'>Race</span>", "Asian");
			break;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> Race Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateEthnicity() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		String Ethnicity=getElementText("(//label[contains(text(),'Ethnicity')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
		switch(Ethnicity) {
		case "unknown":
			movetoelement("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("((//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Hispanic')])[1]");
			click("((//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Hispanic')])[1]");
			UpdatingValues.put("<span style='color: blue'>Ethnicity</span>", "Hispanic or Latino");
			break;
		case "hispanic or latino":
			movetoelement("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Unknown')]");
			click("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Unknown')]");
			UpdatingValues.put("<span style='color: blue'>Ethnicity</span>", "Unknown");
			break;
		default:
			movetoelement("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("((//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Hispanic')])[1]");
			click("((//label[contains(text(),'Ethnicity')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Hispanic')])[1]");
			UpdatingValues.put("<span style='color: blue'>Ethnicity</span>", "Hispanic or Latino");
			break;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> Ethnicity Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateCounty() {
		try{
		boolean verify = verifyelementpresentExplicitwait("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",1);
		if(verify) {
		CountyConfig=true;
		movetoelement("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		String County=getElementText("(//label[contains(text(),'County')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
		switch(County) {
		case "orange":
			movetoelement("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Putnam')]");
			click("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Putnam')]");
			UpdatingValues.put("<span style='color: blue'>County</span>", "Putnam");
			break;
		case "putnam":
			movetoelement("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Orange')]");
			click("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Orange')]");
			UpdatingValues.put("<span style='color: blue'>County</span>", "Orange");
			break;
		default:
			movetoelement("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Putnam')]");
			click("(//label[contains(text(),'County')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Putnam')]");
			UpdatingValues.put("<span style='color: blue'>County</span>", "Putnam");
			break;
		}
		}else {
			test.log(LogStatus.INFO,"<span style='color: '>Demographics App --> County Not Configured for this Client</span>");
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> County Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateState1() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		String State1=getElementText("(//label[contains(text(),'State')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
		switch(State1) {
		case "al":
			movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			String State=getElementText("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]").trim();
			click("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			UpdatingValues.put("<span style='color: blue'>Primary State</span>", State);
			break;
		case "ak":
			movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[2]");
			State=getElementText("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[2]").trim();
			click("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[2]");
			UpdatingValues.put("<span style='color: blue'>Primary State</span>", State);
			break;
		default:
			movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			State=getElementText("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]").trim();
			click("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			UpdatingValues.put("<span style='color: blue'>Primary State</span>", State);
			break;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Demographics App --> Primary State Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	//  fields updation -old text fields
	public ModifyPatientPage UpdateSSN() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'ssnText')]",2);
		movetoelement("//input[contains(@name,'ssnText')]");
		clearelementusingkeys("//input[contains(@name,'ssnText')]");
		clearAndType("//input[contains(@name,'ssnText')]", SSN);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App -->  SSN Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateSecEffDate() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'secondaryEffectiveDate')]",2);
		movetoelement("//input[contains(@name,'secondaryEffectiveDate')]");
		clearelementusingkeys("//input[contains(@name,'secondaryEffectiveDate')]");
		clearAndType("//input[contains(@name,'secondaryEffectiveDate')]", SecEffdate);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Secondary Effective Date Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateSecMedId() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'secondaryMedicaidId')]",2);
		movetoelement("//input[contains(@name,'secondaryMedicaidId')]");
		clearelementusingkeys("//input[contains(@name,'secondaryMedicaidId')]");
		clearAndType("//input[contains(@name,'secondaryMedicaidId')]", SecMediId);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Secondary PayerID Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePrimEffDate() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'primaryEffectiveDate')]",2);
		movetoelement("//input[contains(@name,'primaryEffectiveDate')]");
		clearelementusingkeys("//input[contains(@name,'primaryEffectiveDate')]");
		clearAndType("//input[contains(@name,'primaryEffectiveDate')]", PrimEffdate);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Primary Effective Date Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePrimMedId() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'primaryMedicaidId')]",2);
		movetoelement("//input[contains(@name,'primaryMedicaidId')]");
		clearelementusingkeys("//input[contains(@name,'primaryMedicaidId')]");
		clearAndType("//input[contains(@name,'primaryMedicaidId')]", PrimMediId);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Primary PayerID Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateEmEmail() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'emergencyEmail')]",2);
		movetoelement("//input[contains(@name,'emergencyEmail')]");
		clearelementusingkeys("//input[contains(@name,'emergencyEmail')]");
		clearAndType("//input[contains(@name,'emergencyEmail')]", Ememail);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Emergency Email Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateEmTele() {
		try{
		verifyelementpresentExplicitwait("(//input[contains(@name,'lNameEmergency')]//following::label[contains(text(),'Telephone')]//following::input)[1]",2);
		movetoelement("(//input[contains(@name,'lNameEmergency')]//following::label[contains(text(),'Telephone')]//following::input)[1]");
		clearelementusingkeys("(//input[contains(@name,'lNameEmergency')]//following::label[contains(text(),'Telephone')]//following::input)[1]");
		clearAndType("(//input[contains(@name,'lNameEmergency')]//following::label[contains(text(),'Telephone')]//following::input)[1]", EmTele);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Emergency Telephone Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateEmMname() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'mNameEmergency')]",2);
		movetoelement("//input[contains(@name,'mNameEmergency')]");
		clearelementusingkeys("//input[contains(@name,'mNameEmergency')]");
		clearAndType("//input[contains(@name,'mNameEmergency')]", EmMname);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Emergency Middle Name Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateEmLname() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'lNameEmergency')]",2);
		movetoelement("//input[contains(@name,'lNameEmergency')]");
		clearelementusingkeys("//input[contains(@name,'lNameEmergency')]");
		clearAndType("//input[contains(@name,'lNameEmergency')]", EmLname);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Emergency Last Name Updation Failed</span>");
			TakescreenShot(currentdriver,test);
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateEmFname() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'fNameEmergency')]",2);
		movetoelement("//input[contains(@name,'fNameEmergency')]");
		clearelementusingkeys("//input[contains(@name,'fNameEmergency')]");
		clearAndType("//input[contains(@name,'fNameEmergency')]", EmFname);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Emergency First Name Updation Failed</span>");
			TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateMrn() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'Mrn')]",2);
		movetoelement("//input[contains(@name,'Mrn')]");
		clearelementusingkeys("//input[contains(@name,'Mrn')]");
		clearAndType("//input[contains(@name,'Mrn')]", Mrn);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App -->MRN Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePrimEmail() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'emailText')]",2);
		movetoelement("//input[contains(@name,'emailText')]");
		clearelementusingkeys("//input[contains(@name,'emailText')]");
		clearAndType("//input[contains(@name,'emailText')]", Primemail);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Primary Email Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePrimTele() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[1]",2);
		movetoelement("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[1]");
		clearelementusingkeys("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[1]");
		clearAndType("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[1]", PrimTele);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Primary Telephone Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	
	public ModifyPatientPage UpdateMidName() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'middleName')]",2);
		movetoelement("//input[contains(@name,'middleName')]");
		clearelementusingkeys("//input[contains(@name,'middleName')]");
		clearAndType("//input[contains(@name,'middleName')]", Midname);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Middle Name Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateActScore() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'acuityScore')]",2);
		movetoelement("//input[contains(@name,'acuityScore')]");
		clearelementusingkeys("//input[contains(@name,'acuityScore')]");
		clearAndType("//input[contains(@name,'acuityScore')]", ActScore);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Acuity Score Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateStrtAd1() {
		try{
		verifyelementpresentExplicitwait("//input[@name='streetAddress1']",2);
		movetoelement("//input[@name='streetAddress1']");
		clearelementusingkeys("//input[@name='streetAddress1']");
		clearAndType("//input[@name='streetAddress1']", StrtAd1);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Address 1 Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateStrtAd2() {
		try{
		verifyelementpresentExplicitwait("//input[@name='streetAddress1Second']",2);
		movetoelement("//input[@name='streetAddress1Second']");
		clearelementusingkeys("//input[@name='streetAddress1Second']");
		clearAndType("//input[@name='streetAddress1Second']", Strad2);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Address 2 Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePrimCity() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[1]",2);
		movetoelement("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[1]");
		clearelementusingkeys("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[1]");
		clearAndType("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[1]", PrimCity);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Primary City Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePrimZip() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[1]",2);
		movetoelement("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[1]");
		clearelementusingkeys("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[1]");
		clearAndType("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[1]", Primzip);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'> Demographics App --> Primary ZipCode Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	//  fields updation -new text fields
	public ModifyPatientPage UpdatePrefName() {
		try{
		verifyelementpresentExplicitwait("//input[contains(@name,'preferredName')]",2);
		movetoelement("//input[contains(@name,'preferredName')]");
		clearelementusingkeys("//input[contains(@name,'preferredName')]");
		clearAndType("//input[contains(@name,'preferredName')]", Prefname);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13078 Demographics App --> Preffered Name Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateStreetAd2() {
		try{
		verifyelementpresentExplicitwait("//input[@name='streetAddress2']",2);
		movetoelement("//input[@name='streetAddress2']");
		clearelementusingkeys("//input[@name='streetAddress2']");
		clearAndType("//input[@name='streetAddress2']", StretAd2);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13024 Demographics App --> Address 1 Secondary address 2 Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	
	public ModifyPatientPage UpdateStreetAd22() {
		try{
		verifyelementpresentExplicitwait("//input[@name='streetAddress2Second']",2);
		movetoelement("//input[@name='streetAddress2Second']");
		clearelementusingkeys("//input[@name='streetAddress2Second']");
		clearAndType("//input[@name='streetAddress2Second']", StretAd22);
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13024 Demographics App --> Address 2 Secondary address 2 Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateCity2() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[2]",2);
		movetoelement("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[2]");
		clearelementusingkeys("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[2]");
		clearAndType("(//label[contains(text(),'City')]//following::input[contains(@name,'newTextItem')])[2]", SecCity);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13024 Demographics App --> Secondary City Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateZip2() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[2]",2);
		movetoelement("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[2]");
		clearelementusingkeys("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[2]");
		clearAndType("(//label[contains(text(),'Zip')]//following::input[contains(@name,'newTextItem')])[2]", SecZip);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13024 Demographics App --> Secondary ZipCode Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateExpirydate() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'primaryExpiryDate')])[1]",2);
		movetoelement("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'primaryExpiryDate')])[1]");
		clearelementusingkeys("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'primaryExpiryDate')])[1]");
		clearAndType("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'primaryExpiryDate')])[1]", ExpDate);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13052 Demographics App -->Primary PayerClass Expiry Date Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateSecExpirydate() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'secondaryExpiryDate')])[1]",2);
		movetoelement("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'secondaryExpiryDate')])[1]");
		clearelementusingkeys("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'secondaryExpiryDate')])[1]");
		clearAndType("(//label[contains(text(),'Expiry Date')]//following::input[contains(@name,'secondaryExpiryDate')])[1]", SecExpdate);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13052 Demographics App -->Secondary PayerClass Expiry Date Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateTeleph2() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[2]",2);
		movetoelement("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[2]");
		clearelementusingkeys("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[2]");
		clearAndType("(//label[contains(text(),'Telephone')]//following::input[contains(@name,'newTextItem')])[2]", SecTele);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13023 Demographics App --> Secondary Telephone Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	// new fields updation -dropdown fields
	public ModifyPatientPage UpdateIdefGender() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		String IdefGenText=getElementText("(//label[contains(text(),'Identified')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
		switch(IdefGenText) {
		case "male":
			movetoelement("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Female')]");
			click("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Female')]");
			UpdatingValues.put("<span style='color: blue'>Identified Gender</span>", "Female");
			break;
		case "female":
			movetoelement("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Male')]");
			click("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Male')]");
			UpdatingValues.put("<span style='color: blue'>Identified Gender</span>", "Male");
			break;
		default:
			movetoelement("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Female')]");
			click("(//label[contains(text(),'Identified')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Female')]");
			UpdatingValues.put("<span style='color: blue'>Identified Gender</span>", "Female");
			break;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13071 Demographics App --> Identified Gender Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdateSecState() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]",2);
		movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]");
		String SecStateText=getElementText("(//label[contains(text(),'State')]//following::div[contains(@class,'selectItem')])[2]").trim().toLowerCase();
		switch(SecStateText) {
		case "al":
			movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]");
			click("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]");
			movetoelement("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			String State=getElementText("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]").trim();
			click("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			UpdatingValues.put("<span style='color: blue'>Secondary State</span>", State);
			break;
		case "ak":
			movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]");
			click("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]");
			movetoelement("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[2]");
			State=getElementText("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[2]").trim();
			click("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[2]");
			UpdatingValues.put("<span style='color: blue'>Secondary State</span>", State);
			break;
		default:
			movetoelement("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]");
			click("(//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[2]");
			movetoelement("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			State=getElementText("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]").trim();
			click("((//label[contains(text(),'State')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::tr[contains(@id,'PickListMenu')])[1]");
			UpdatingValues.put("<span style='color: blue'>Secondary State</span>", State);
			break;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13024 Demographics App -->Secondary State Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatePreflang() {
		try{
		verifyelementpresentExplicitwait("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]",2);
		movetoelement("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
		String PrefLangText=getElementText("(//label[contains(text(),'Preferred Language')]//following::div[contains(@class,'selectItem')])[1]").trim().toLowerCase();
		switch(PrefLangText) {
		case "english":
			movetoelement("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Arabic')]");
			click("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'Arabic')]");
			UpdatingValues.put("<span style='color: blue'>Preferred Language</span>", "Arabic");
			break;
		case "arabic":
			movetoelement("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'English')]");
			click("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'English')]");
			UpdatingValues.put("<span style='color: blue'>Preferred Language</span>", "English");
			break;
		default:
			movetoelement("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			click("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]");
			movetoelement("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'English')]");
			click("(//label[contains(text(),'Preferred Language')]//following::td[contains(@class,'comboBoxItemPickerCell')])[1]//following::table//div[contains(text(),'English')]");
			UpdatingValues.put("<span style='color: blue'>Preferred Language</span>", "English");
			break;
		}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint 38 Dash13024 Demographics App -->Preffered Language Updation Failed</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	//verify updated fields verification
	public ModifyPatientPage UpdatedFieldsVerification() {
		try{
			//new fields map
			UpdatedValues.put("<span style='color: blue'>Preferred Name</span>", getElementText("(//label[contains(text(),'Preferred Name')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Identified Gender</span>", getElementText("(//label[contains(text(),'Identified')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Preferred Language</span>", getElementText("(//label[contains(text(),'Preferred Language')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Address 1 Appartment</span>", getElementText("(//label[contains(text(),'Apartment')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Address 2 Appartment</span>", getElementText("(//label[contains(text(),'Apartment')]//following::div[contains(@class,'staticText')])[2]").trim());
			UpdatedValues.put("<span style='color: blue'>Telephone 2</span>", getElementText("(//label[contains(text(),'Telephone 2')]//following::div[contains(@class,'staticText')])[1]").trim().replaceAll("-", ""));
			UpdatedValues.put("<span style='color: blue'>Secondary City</span>", getElementText("(//label[contains(text(),'City')]//following::div[contains(@class,'staticText')])[2]").trim());
			UpdatedValues.put("<span style='color: blue'>Secondary Zip Code</span>", getElementText("(//label[contains(text(),'Zip')]//following::div[contains(@class,'staticText')])[2]").trim());
			UpdatedValues.put("<span style='color: blue'>Secondary State</span>", getElementText("(//label[contains(text(),'State')]//following::div[contains(@class,'staticText')])[2]").trim());
			UpdatedValues.put("<span style='color: blue'>Primary Payer Expiry Date</span>", getElementText("((//label[contains(text(),'Expiry')])[1]//following::div[contains(@class,'staticText')])[1]").trim());	
			
			//old fields map
			UpdatedValues.put("<span style='color: blue'>Middle Name</span>", getElementText("(//label[contains(text(),'Middle Name')]//following::div[contains(@class,'staticText')])[1]").trim());
			SSN = getElementText("(//label[contains(text(),'SSN')]//following::div[contains(@class,'staticText')])[1]").trim().replaceAll("-", "");
			UpdatedValues.put("<span style='color: blue'>SSN</span>", SSN.substring(SSN.length()-4));
			UpdatedValues.put("<span style='color: blue'>Acuity Score</span>", getElementText("(//label[contains(text(),'Acuity')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Address 1</span>", getElementText("(//label[contains(text(),'Address 1')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Address 2</span>", getElementText("(//label[contains(text(),'Address 2')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>City</span>", getElementText("(//label[contains(text(),'City')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Zip Code</span>", getElementText("(//label[contains(text(),'Zip')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Telephone 1</span>", getElementText("(//label[contains(text(),'Telephone 1')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Email Address</span>", getElementText("(//label[contains(text(),'Email')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>MRN</span>", getElementText("(//label[contains(text(),'MRN')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Emergency First Name</span>", getElementText("(//label[contains(text(),'Relationship')]/../../..//..//div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Emergency Last Name</span>", getElementText("(//label[contains(text(),'Relationship')]/../../..//..//div[contains(@class,'staticText')])[3]").trim());
			UpdatedValues.put("<span style='color: blue'>Emergency Middle Name</span>", getElementText("(//label[contains(text(),'Relationship')]/../../..//..//div[contains(@class,'staticText')])[2]").trim());
			UpdatedValues.put("<span style='color: blue'>Emergency Telephone</span>", getElementText("(//label[contains(text(),'Relationship')]/../../..//..//div[contains(@class,'staticText')])[4]").trim());
			UpdatedValues.put("<span style='color: blue'>Emergency Email</span>", getElementText("(//label[contains(text(),'Relationship')]/../../..//..//div[contains(@class,'staticText')])[5]").trim());
			UpdatedValues.put("<span style='color: blue'>Relation</span>", getElementText("(//label[contains(text(),'Relationship')]/../../..//..//div[contains(@class,'staticText')])[6]").trim());
			UpdatedValues.put("<span style='color: blue'>Primary PayerID</span>", getElementText("((//label[contains(text(),'Payer ID')])[1]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Primary Payer Effective Date</span>", getElementText("((//label[contains(text(),'Effective')])[1]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Secondary PayerID</span>", getElementText("((//label[contains(text(),'Payer ID')])[2]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Secondary Payer Effective Date</span>", getElementText("((//label[contains(text(),'Effective')])[2]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Secondary Payer Expiry Date</span>", getElementText("((//label[contains(text(),'Expiry')])[2]//following::div[contains(@class,'staticText')])[1]").trim());
			//drop down fields to map
			UpdatedValues.put("<span style='color: blue'>Secondary Payer Plan</span>", getElementText("((//label[contains(text(),'Payer Plan')])[2]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Secondary Payer Class</span>", getElementText("((//label[contains(text(),'Payer Class')])[2]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Primary Payer Plan</span>", getElementText("((//label[contains(text(),'Payer Plan')])[1]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Primary Payer Class</span>", getElementText("((//label[contains(text(),'Payer Class')])[1]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Race</span>", getElementText("(//label[contains(text(),'Race')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Ethnicity</span>", getElementText("(//label[contains(text(),'Ethnicity')]//following::div[contains(@class,'staticText')])[1]").trim());
			UpdatedValues.put("<span style='color: blue'>Primary State</span>", getElementText("(//label[contains(text(),'State')]//following::div[contains(@class,'staticText')])[1]").trim());
			if(CountyConfig)
				UpdatedValues.put("<span style='color: blue'>County</span>", getElementText("(//label[contains(text(),'County')]//following::div[contains(@class,'staticText')])[1]").trim());
			
			test.log(LogStatus.INFO, "Patient Updating Values in the Deomographics Modify Page<br/>"+UpdatingValues);
			test.log(LogStatus.INFO, "Patient Updated Values in Deomographics Patient Details View Page<br/>"+UpdatedValues);
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint38 Dashboard 13023 13024 13052 13071 13078 DemographicsApp  --> Updated Fields Not Reflected !!!</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage UpdatedFieldsCareBookVerification() {
		try{
			//new fields map
			UpdatedCarebookValues.put("<span style='color: blue'>Preferred Name</span>", getElementText("//span[contains(@id,'PreferredName')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Identified Gender</span>", getElementText("//span[contains(@id,'IdentifiedGender')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Address 1 Appartment</span>", getElementText("//span[contains(@id,'Address1SecondLine')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Address 2 Appartment</span>", getElementText("//span[contains(@id,'Address2SecondLine')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Telephone 2</span>", getElementText("//span[contains(@id,'telephone2')]").trim().replaceAll("-", ""));
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary City</span>", getElementText("//span[contains(@id,'city2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary Zip Code</span>", getElementText("//span[contains(@id,'zipcode2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary State</span>", getElementText("//span[contains(@id,'state2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Primary Payer Expiry Date</span>", getElementText("//span[contains(@id,'ExpiryDate1')]").trim());	
			
			//old fields map
			SSN=getElementText("//span[contains(@id,'ssn')]").trim();
			UpdatedCarebookValues.put("<span style='color: blue'>SSN</span>", SSN.substring(SSN.length()-4));
			UpdatedCarebookValues.put("<span style='color: blue'>Address 1</span>", getElementText("(//span[contains(@id,'Address1')])[1]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Address 2</span>", getElementText("//span[contains(@id,'address2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>City</span>", getElementText("(//span[contains(@id,'city')])[1]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Zip Code</span>", getElementText("(//span[contains(@id,'zipcode')])[1]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Telephone 1</span>", getElementText("(//span[contains(@id,'telephone')])[1]").trim().replaceAll("-", ""));
			UpdatedCarebookValues.put("<span style='color: blue'>Email Address</span>", getElementText("//span[contains(@id,'email')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>MRN</span>", getElementText("//span[contains(@id,'MRN')]").trim());
			//split em ln and fn
			String FNLN=getElementText("//span[contains(@id,'emcontact')]").trim();
			UpdatedCarebookValues.put("<span style='color: blue'>Emergency First Name</span>", FNLN.split("\\s+")[1]);
			UpdatedCarebookValues.put("<span style='color: blue'>Emergency Last Name</span>", FNLN.split("\\s+")[0]);
			UpdatedCarebookValues.put("<span style='color: blue'>Emergency Telephone</span>", getElementText("//span[contains(@id,'emphone')]").trim().replaceAll("-", ""));
			UpdatedCarebookValues.put("<span style='color: blue'>Primary PayerID</span>", getElementText("//span[contains(@id,'id1')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Primary Payer Effective Date</span>", getElementText("//span[contains(@id,'EffectiveDate1')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary PayerID</span>", getElementText("//span[contains(@id,'id2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary Payer Effective Date</span>", getElementText("//span[contains(@id,'EffectiveDate2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary Payer Expiry Date</span>", getElementText("//span[contains(@id,'ExpiryDate2')]").trim());
			//drop down fields to map
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary Payer Plan</span>", getElementText("//span[contains(@id,'plan2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Secondary Payer Class</span>", getElementText("//span[contains(@id,'payer2')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Primary Payer Plan</span>", getElementText("//span[contains(@id,'plan1')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Primary Payer Class</span>", getElementText("//span[contains(@id,'payer1')]").trim());
			UpdatedCarebookValues.put("<span style='color: blue'>Primary State</span>", getElementText("(//span[contains(@id,'state')])[1]").trim());
			//remove the missing fields to compare two maps
			UpdatingValues.remove("<span style='color: blue'>Middle Name</span>");
			UpdatingValues.remove("<span style='color: blue'>Acuity Score</span>");
			UpdatingValues.remove("<span style='color: blue'>Race</span>");
			UpdatingValues.remove("<span style='color: blue'>Ethnicity</span>");
			UpdatingValues.remove("<span style='color: blue'>Preferred Language</span>");
			UpdatingValues.remove("<span style='color: blue'>Emergency Middle Name</span>");
			UpdatingValues.remove("<span style='color: blue'>Emergency Email</span>");
			UpdatingValues.remove("<span style='color: blue'>Relation</span>");
			if(CountyConfig)
				UpdatingValues.remove("<span style='color: blue'>County</span>");
			test.log(LogStatus.INFO, "Patient Updating Values in the Deomographics Modify Page<br/>"+UpdatingValues);
			test.log(LogStatus.INFO, "Patient Updated Values in the Carebook Demographics Details Page<br/>"+UpdatedCarebookValues);
			

		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"<span style='color: red'>Sprint38 Dashboard 13023 13024 13052 13071 13078 DemographicsApp  --> Updated Fields Not in Carebook Reflected !!!</span>");
			TakescreenShot(currentdriver,test);TestDataVerification.NavigatetoHome();
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage clickDemographics() {
		try{
			verifyelementpresentExplicitwait("//div[contains(@id,'appEnrollment_menu')]");
			click("//div[contains(@id,'appEnrollment_menu')]");
			actionsclick("//*[contains(text(),'Reset')]//parent::button");
			boolean verify=verifyelementpresentExplicitwait("//input[contains(@name,'PatientId')]",5);
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Demographics app successfully");
			}else {
			test.log(LogStatus.FAIL,"Demographics app Navigation failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Demographics app");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	public ModifyPatientPage clickCarebookInDemogApp(String PatientID) {
		try{
			boolean verify1=verifyelementpresentExplicitwait("//div[contains(text(),'CareBook')]//parent::td[contains(@class,'button')]", 60);
			if(verify1) {
				movetoelement("//div[contains(text(),'CareBook')]//parent::td[contains(@class,'button')]");
				actionsclick("//div[contains(text(),'CareBook')]//parent::td[contains(@class,'button')]");
				test.log(LogStatus.PASS, "CareBook button is clicked successfully");
			}
			else {
				test.log(LogStatus.FAIL, "CareBook button is not clicked successfully");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
			boolean verify2=verifyelementpresentExplicitwait("//iframe[contains(@id,'carebook-iframe')]",120);
			if(verify2) {
				switchToFrame("//iframe[contains(@id,'carebook-iframe')]");	
			}
			boolean verify3 = verifyelementpresentExplicitwait("//div[contains(@id,'patientDetails')]/label[contains(text(),'"+PatientID+"')]", 120);
			if(verify3) {
			test.log(LogStatus.PASS,"Navigated to the Carebook app successfully");
			}else {
			test.log(LogStatus.FAIL,"Carebook App navigation Failed");
			TakescreenShot(currentdriver,test);
			driver.switchTo().defaultContent();
			Assert.fail();
			}
		
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Demographics app");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		
		return this;
	}
	
	public ModifyPatientPage ModifyPage() {
		try {
			click("//div[contains(text(),'Modify')]");
			boolean verify=verifyelementpresentExplicitwait("//td[contains(text(),'Modify')]");
			if(verify) {
			test.log(LogStatus.INFO,"Navigated to the Patient Details Modification Page");
			}else {
			test.log(LogStatus.FAIL,"Patient Details Modification Page Navigation failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Patient Details Modification Page Navigation failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public ModifyPatientPage Address1Update() {

		try {
			movetoelement("//input[@name = 'streetAddress1']");
			clearelementusingkeys("//input[@name = 'streetAddress1']");
			clearAndType("//input[@name = 'streetAddress1']", Address1);
			boolean verify1 = verifyelementpresentExplicitwait("//img[contains(@aria-label,'Field is required')]",1);
			if(!verify1) {
				test.log(LogStatus.PASS, "New Address Enterned in Address 1 Field:<font color='blue'>"+Address1+"</font>");
			}else {
				test.log(LogStatus.FAIL, "Unable to enter the new address in Address 1 Field");
				TakescreenShot(currentdriver,test);
				Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Move Address1 Field");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public ModifyPatientPage SaveButton() {
		try {
			Actions actions = new Actions(driver);
			WebElement Element = locateElementbyXpath("//div[contains(@eventproxy,'SaveButton')]");
			actions.moveToElement(Element).perform();
			boolean verify=verifyelementpresentExplicitwait("//div[contains(@eventproxy,'SaveButton')]",60);
			if(verify) {
				click("//div[contains(@eventproxy,'SaveButton')]");
			}else {
			test.log(LogStatus.FAIL,"Unable to Click Save Button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Save Button Not Displayed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public ModifyPatientPage OkButton() {
		try {
			boolean verify=verifyelementpresentExplicitwait("//td[contains(text(),'Save successful')]",180);
			if(verify) {
				test.log(LogStatus.INFO,"Save SuccessFul Popup Appeared");
				actionsclick("//div[text()='OK']");
			}else {
			test.log(LogStatus.FAIL,"Ok Button Not Appeared");
			TakescreenShot(currentdriver,test);
			Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Click Save Button");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public boolean VerifyDgUpdate() {
		try {
			boolean verify=verifyelementpresentExplicitwait("//div[contains(text(),'Modify')]",120);
			if(verify) {
			test.log(LogStatus.PASS,"Patient Address1 Updated Suceessfully");
			return true;
			}else {
			test.log(LogStatus.FAIL,"Patient Address1 Updation Failed");
			TakescreenShot(currentdriver,test);
			return false;
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Patient Address1 Updation Failed");
			TakescreenShot(currentdriver,test);
			return false;
		}
	}
	public ModifyPatientPage clickCarePlan() {
		try{
			verifyelementpresentExplicitwait("//div[contains(@id,'appCareplan_menu')]",120);
			click("//div[contains(@id,'appCareplan_menu')]");
			driver.switchTo().window("TREAT");
			Dimension d = new Dimension(1920,1080);
			driver.manage().window().setSize(d);
			boolean verify=verifyelementpresentExplicitwait("//*[@id='navbuttons_search']");
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Care Plan App successfully");
			}else {
			test.log(LogStatus.FAIL,"Care Plan App Navigation failed");
			TakescreenShot(currentdriver,test);
			closecareplannavigatetohome();
			Assert.fail();
			
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to click Care Plan App");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return  this;
	}
	public ModifyPatientPage SearchPatientCarePlan(String PatientID) throws InterruptedException {
		try{
			click("//*[@id='navbuttons_search']");
			boolean verify=verifyelementpresentExplicitwait("//input[@name='mrn']",120);
			if(verify) {
			test.log(LogStatus.INFO,"Navigated to the Care Plan Search Page successfully");
			clearelementusingkeys("//input[@name='mrn']");
			clearAndType("//input[@name='mrn']", PatientID);
			test.log(LogStatus.PASS,"Entered PatientID is:<font color='blue'>"+PatientID+"</font>");
			}else {
			test.log(LogStatus.FAIL,"Care Plan App Search Page Navigation failed");
			TakescreenShot(currentdriver,test);
			closecareplannavigatetohome();
			Assert.fail();
			}
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Care Plan Search Page Navigation Failed");
			TakescreenShot(currentdriver,test);
			Assert.fail();
		}
		return this;
	}
	public void clickLookup() throws InterruptedException {
	try {
		driver.findElement(By.xpath("//input[@type='submit']")).sendKeys(Keys.RETURN);
		verifyelementpresentExplicitwait("//*[contains(text(),'No Results Found')]", 1);
		boolean verify1 =verifyelementpresentExplicitwait("//td[contains(text(),'"+Address1+"')]", 5);
		try {
		 if(verify1) {
		  String Add = driver.findElementByXPath("//td[contains(text(),'"+Address1+"')]").getText();
		  if(Add.trim().equals(Address1)) 
		  {
			  test.log(LogStatus.PASS,"Address1 '"+Address1+"' is updated successfully in CarePlan app");
		  }
		 }
		  else if(verifyelementpresentExplicitwait("//a[contains(text(),'Encounter')]",5)) {
			  click("//a[contains(text(),'Encounter')]");
			  verifyelementpresentExplicitwait("//a[contains(text(),'Demographics')]",5);
			  click("//a[contains(text(),'Demographics')]");
			  String Add1 = driver.findElementByXPath("//td[contains(text(),'"+Address1+"')]").getText();
			  if(Add1.trim().equals(Address1)) 
			  {
				  test.log(LogStatus.PASS,"Address1 '"+Address1+"' is updated successfully in CarePlan app");
			  }else {
				  test.log(LogStatus.FAIL,"Address1 '"+Address1+"' is not updated successfully in CarePlan app");
				  TakescreenShot(currentdriver,test);
				  closecareplannavigatetohome();
				  Assert.fail(); 
			  }
		  }
		  else {
			  test.log(LogStatus.FAIL,"Address1 '"+Address1+"' is not updated successfully in CarePlan app");
			  TakescreenShot(currentdriver,test);
			  closecareplannavigatetohome();
			  Assert.fail(); 
		  }

		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Updated Address Field Not Reflected in CarePlan");
			TakescreenShot(currentdriver,test);
			closecareplannavigatetohome();
		}
		 }
		  catch(Exception e) {
				test.log(LogStatus.FAIL,"CarePlan Search Page Loading Timeout");
				TakescreenShot(currentdriver,test);
				closecareplannavigatetohome();
				Assert.fail();
			}
	}
	public void closecareplannavigatetohome()  throws InterruptedException {
		closewindow();
		verifyelementpresentExplicitwait("//button[contains(@aria-label,'Reset App')]", 5);
        click("//button[contains(@aria-label,'Reset App')]");
        verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		 movetoelement("//div[contains(@id,'appHome_menu')]");
		 actionsclick("//div[contains(@id,'appHome_menu')]");	
	}
	public void closecareplannavigatetohomewithoutreset()  throws InterruptedException {
		closewindow();
        verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		 movetoelement("//div[contains(@id,'appHome_menu')]");
		 actionsclick("//div[contains(@id,'appHome_menu')]");	
	}
	public void closewindow() throws InterruptedException {
		 	List<String> windows = new ArrayList<>(driver.getWindowHandles());
//		    for (String s: windows) { System.out.println("window: " + s);} 
		    String parentWindow = windows.get(0); 
		    String childWindow = windows.get(1); 
//		    driver.switchTo().window(parentWindow).close();
		    driver.switchTo().window(childWindow).close();
		    Thread.sleep(1000);
		    driver.switchTo().window(parentWindow);
		    Thread.sleep(1000);
	}
	public ModifyPatientPage PatientAddress1Update(String PatientID)  throws InterruptedException{
		boolean flag=false;
		try {
			ModifyPage()
			.Address1Update()
			.SaveButton()
			.OkButton();
			boolean verify=VerifyDgUpdate();
			if(verify) {
				clickCarePlan();
		         SearchPatientCarePlan(PatientID);
		         clickLookup();
		         closecareplannavigatetohome();
				flag=true;
			}
			else {flag=false;}

		}catch(Exception e) {
			flag=false;
		}
		finally {
			if(!flag) {
				closecareplannavigatetohome();
			}
		}
		return this;
	}
	
	//maps comparision
	public boolean DemographicsUpdatedValuesAreEqual(Map<String, String> mapA, Map<String, String> mapB,boolean iscontainstrue,String PagesNeedtoVerify,String TrueValuePage) {
		int count=0;
		for (Map.Entry<String, String> entry1 : mapA.entrySet() ){
			  String key = entry1.getKey();
			  String value1 = entry1.getValue();
			  String value2 = mapB.get(key);
			  
			  //map content contains method
		if(iscontainstrue) {
			  if(value1.contains(value2)) {
				  continue;
			  }
			  else {
				  count =+1;
				  test.log(LogStatus.FAIL, "The Values Reflected in the Demographics "+PagesNeedtoVerify+" Page Data is Mismatched!!!<br> The Value need to Reflected in the Demographics Card  "+TrueValuePage+" Page "
				  		+ "is:<br><font color='green'>"+key+":"+mapA.get(key)+"</font><br> Actual Reflected Value in the Demographics Card "+TrueValuePage+" Page is:<br><font color='red'>"+key+":"+mapB.get(key)+"</font>");
			  }
			 }
			//map content equals method
		else {
			  if(value1.equals(value2)) {
				  continue;
			  }
			  else {
				  count =+1;
				  test.log(LogStatus.FAIL, "The Values Reflected in the Demographics "+PagesNeedtoVerify+" Data is Mismatched!!!<br> The Value need to Reflected in the Demographics Card  "+TrueValuePage+" Page "
					  		+ "is:<br><font color='green'>"+key+":"+mapA.get(key)+"</font><br> Actual Reflected Value in the Demographics Card "+TrueValuePage+" Page is:<br><font color='red'>"+key+":"+mapB.get(key)+"</font>");
			  }
			}
		}
		if (count==0) {
			test.log(LogStatus.PASS, "<span style='color: green'>The Values Reflected in the Demographics "+PagesNeedtoVerify+" and "+TrueValuePage+"  Data is Same!!!</span>");
			return true;
		}else {
			test.log(LogStatus.FAIL, "<span style='color: red'>The Values Reflected in the Demographics "+PagesNeedtoVerify+" and "+TrueValuePage+" Data is Mismatched!!!</span>");
			return false;
		}
	    
	}
	}
