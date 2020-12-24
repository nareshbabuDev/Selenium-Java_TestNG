package com.medecision.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.medecison.testng.api.base.EnvPatientID;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DriverInitialize{
	public  RemoteWebDriver driver;
	public  ExtentReports extent;
	public  ExtentTest test;
	public String testcaseName, testcaseDec, author, category;
	public String EnvironmentName;
	public String CurrentSuiteName;
	public  EnvPatientID EnvBulkDats;
	public String classname;
	String defaulttestsuitename="testng-customsuite";
	
	//read the environment details 
	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return DataLibrary.ReadTestData(EnvironmentName);
	}
		
	public PasswordReset fetchPassReset() throws IOException {
		return DataLibrary.ReadPasswordResetData(EnvironmentName);
	}
	
	public EnvJsonData fetchEnvJson(String env) throws IOException {
		return DataLibrary.ReadEnvJsonData(env);
	}
	
	public Map<String, Map<String, Object>> readEnvJsonFile() throws IOException {
		return DataLibrary.ReadEnvJsonFile();
	}

	@BeforeClass(alwaysRun=true)
	public void Beforeclass(ITestContext SuiteName) throws InterruptedException {
		//get env name
		String CurrentSuiteNamePath = SuiteName.getCurrentXmlTest().getSuite().getFileName();
		System.out.println(CurrentSuiteNamePath);
		String name=CurrentSuiteNamePath.split("\\.")[0];
		String CurrentSuiteNameArray [] =name.split("\\\\");
		CurrentSuiteName=CurrentSuiteNameArray[CurrentSuiteNameArray.length-1];
		String CurrentEnvName =CurrentSuiteName.split("_")[0];
		try {
			if(CurrentSuiteName.equals(defaulttestsuitename)) {
				EnvironmentName="DEV";
			}
			else if(CurrentEnvName==null) {
				EnvironmentName="ESCROW";
			}else {
				EnvironmentName=CurrentEnvName.toUpperCase();
			}
			}catch(Exception e) {
			System.out.println("Environment Name is Empty");
		}
		//get class name
		String[] classArray =this.getClass().getName().split("\\.");
		classname =classArray[classArray.length-1];
		String BrowserType = System.getProperty("runmodeselection");
		try {
		if(BrowserType==null) {
			driver =StartBrowser("chrome",CurrentSuiteName);
			
		}else {
			driver = StartBrowser(BrowserType,CurrentSuiteName);
		}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"The Browser Could not be Launched. Hence Failed");
		}
		Date d =new Date();
	    String todaydatetime = new SimpleDateFormat("MMM-dd-yyyy_HH_mm_ss_SSS").format(d);
		extent = new ExtentReports("./reports/"+EnvironmentName+"_"+classname+"_"+todaydatetime+".html",true,DisplayOrder.NEWEST_FIRST);
		
		
	}
	@BeforeClass(alwaysRun = true)
	public void readcurrentenvbulkdatas() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream file = null;
		try {
			file = new FileInputStream("./DataFiles/EnvironmentTestData.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Map<String, Map<String, Object>> jsonMaps = null;
		try {
			jsonMaps = objectMapper.readValue(file, new TypeReference<Map<String, Map<String, Object>>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		EnvPatientID ENVPIDs = new EnvPatientID();
		try {
			ENVPIDs = new ObjectMapper().readValue(
					new ObjectMapper().writeValueAsString(jsonMaps.get(EnvironmentName).get("MultipleDatas")),
					EnvPatientID.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		EnvBulkDats = ENVPIDs;
	}	

	@BeforeMethod(alwaysRun = true)
	public void report(ITestResult MethodName) throws IOException, InterruptedException {		
		extent.loadConfig(new File("./reports/reportconfig/ExtentReportXml.xml"));
		test = extent.startTest("<font color='#000000'><b>"+MethodName.getMethod().getMethodName()+"</b</font>",EnvironmentName);
    }
	

	public  RemoteWebDriver StartBrowser(String browser, String CurrentSuiteName) {
		 final String USERNAME = "prabha2";
		 final String ACCESS_KEY = "0ef913f5-f276-467e-bfc3-fbaad07335f1";
		 final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
		 ChromeOptions options = new ChromeOptions();
		 String Browser = browser.toLowerCase();
		 try {
			switch (Browser)
	        {
	            case "chrome":
	            	System.setProperty("webdriver.chrome.driver","./drivers/chromedriver_67/chromedriver.exe");
			        driver = new ChromeDriver();
			        System.out.println("Session id: "+driver.getSessionId().toString());
	                break;
	            case "chrome(headless)":
	            	System.setProperty("webdriver.chrome.driver","./drivers/chromedriver_67/chromedriver.exe");
			        options.addArguments("--headless");
			        driver = new ChromeDriver(options);
	                break;
	            case "sauce":
					final LoggingPreferences logPreferences = new LoggingPreferences();
					logPreferences.enable(LogType.BROWSER, Level.OFF);
					logPreferences.enable(LogType.CLIENT, Level.OFF);
					logPreferences.enable(LogType.DRIVER, Level.OFF);
					logPreferences.enable(LogType.PERFORMANCE, Level.OFF);
					logPreferences.enable(LogType.PROFILER, Level.OFF);
					logPreferences.enable(LogType.SERVER, Level.OFF);
					ChromeOptions cap = new ChromeOptions(); 
					cap.setCapability("platform", "Windows 10");
					cap.setCapability("version", "83.0");
					cap.setCapability("build", "Selenium Automation");
					cap.setCapability("name", "Automation Test");
					cap.setCapability("idleTimeout","3000");
					cap.setCapability("maxDuration" ,"10800");
					cap.setCapability("screenResolution","1920x1080");
					cap.setCapability("recordVideo", false);
					cap.setCapability("recordScreenshots", false);
					cap.setCapability("recordLogs", false);
					cap.setCapability("public", "private");
					cap.setCapability(CapabilityType.LOGGING_PREFS, logPreferences);
					driver = new RemoteWebDriver(new URL(URL), cap);
					SessionId session = ((RemoteWebDriver)driver).getSessionId();
					System.out.println("Session id: " + session.toString());
					break;
	            case "firefox":
	            	System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
	                break;
	            case "ie":
	            	System.setProperty("webdriver.ie.driver","./drivers/IEDriverServer.exe");
					driver = new InternetExplorerDriver();
	            default:
	                System.out.println("Cannot Start the Browser Driver");
	                break;
	        }
			Dimension d = new Dimension(1920,1080);
			driver.manage().window().setSize(d);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
		} 
		catch (Exception e) {
			
			throw new RuntimeException();
		}

	}
	//password rewrite
	public void resetPassword(String newPass) throws IOException {
		Map<String, Map<String, Object>> jsonMaps = readEnvJsonFile();
		// Replace passwordReset Json
		 byte[] EncodedPassword = Base64.encodeBase64(newPass.getBytes());
		String mainpayload = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(jsonMaps);
		JSONObject jsonTest = new JSONObject(mainpayload);
		String passwordResetJson = new ObjectMapper().writerWithDefaultPrettyPrinter()
				.writeValueAsString(jsonMaps.get("PasswordReset"));
		JSONObject passwordResetObj = new JSONObject(passwordResetJson);
		passwordResetObj.put("Password", newPass);
		jsonTest.put("PasswordReset", passwordResetObj);

		// json beautifier
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(jsonTest.toString()).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(json);

		// write json file
		try (FileWriter file1 = new FileWriter("./DataFiles/EnvironmentTestData.json")) {
			file1.write(prettyJson);
			System.out.println("Successfully updated");
		}
		}
		public void Refresh() {
			driver.get(driver.getCurrentUrl());
		}

		@AfterMethod(alwaysRun = true)
		public void endreport() throws IOException {
	    	
	    	extent.endTest(test); 
	    	if (test.getRunStatus().equals(LogStatus.UNKNOWN))
	    	{
	    	test.log(LogStatus.UNKNOWN, "Report result is unknown !!!");
	    	}
	    	
		}
	 	@AfterClass
	 	public void CloseDriver() {
			
			extent.flush();
			driver.close();
			driver.quit();
		}
	 	
	 	
		
}
