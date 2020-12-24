package com.medecison.testng.api.base;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.annotations.IBeforeSuite;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.medecision.utils.DataLibrary;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProjectSpecificMethods extends SeleniumBase {
	public ProjectSpecificMethods(RemoteWebDriver driver, ExtentTest test) {
		super(driver, test);
		
	}

	String CurrentTestName ="1";
	String sessionid ="";
	ArrayList<String> Methodlist = new ArrayList<String>();

	@AfterMethod(alwaysRun=true)
	public void getsessionid(ITestResult MethodName) throws InterruptedException {
		CurrentTestName = MethodName.getMethod().getMethodName();
		Methodlist.add(CurrentTestName);
	}

	@AfterSuite(alwaysRun=true)
	public void afterMethod() throws IOException,InterruptedException {
//		Thread.sleep(90000);
		try {
		//for (int i=0;i<Methodlist.size();i++) {
		String command = "curl -X GET -u prabha2:0ef913f5-f276-467e-bfc3-fbaad07335f1 -o ./saucevideo/ --create-dirs mkdir https://saucelabs.com/rest/v1/prabha2/jobs/"+sessionid+"/assets/video.mp4 --output ./saucevideo/"+CurrentTestName+".mp4";
//		Process process = Runtime.getRuntime().exec(command);
//		process.getInputStream();
//		String command = "curl -X GET -u prabha2:0ef913f5-f276-467e-bfc3-fbaad07335f1 -o ./saucevideo/ --create-dirs mkdir https://saucelabs.com/rest/v1/prabha2/jobs/031bc34eb8cb440f8b282bc178bcfefe/assets/video.mp4 --output ./saucevideo/Test1.mp4";
		String path = System.getenv("CURL");
		String[] dirs = path.split( ";" );
		for( String dir: dirs ){
//		System.out.println(dir);
		Path toCurl = Paths.get(dir);
	    File curlFile = new File( toCurl.toString() );
//	    System.out.println(curlFile);
	    Process process = Runtime.getRuntime().exec(command);
		process.getInputStream();
		String Currentdir =System.getProperty("user.dir");
//		test.log(LogStatus.INFO, "CurrentTestSauceVide : <video controls width='250'><source src='"+Currentdir+"\\saucevideo\\"+CurrentTestName+".mp4' type='video/mp4'></video>");
//		test.log(LogStatus.INFO, "CurrentTestSauceVide : <video controls width='250'><source src='"+Currentdir+"\\saucevideo\\"+CurrentTestName+".mp4' type='video/mp4'></video>");
		}
		}catch(Exception e) {
		System.out.println(e);	
		}
	}

}
