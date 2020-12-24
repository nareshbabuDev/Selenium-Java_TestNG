package com.medecision.utils;
import org.testng.ITestContext;  
import org.testng.ITestListener;  
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestListener extends DriverInitialize implements ITestListener{
	    @Override  
	    public void onTestStart(ITestResult result) {  
	    	
	    }  
	  
	    @Override  
	    public void onTestSuccess(ITestResult result) {  
	        // TODO Auto-generated method stub  
	    }  
	  
	    @Override  
	    public void onTestFailure(ITestResult result) {  
	    
	        

	    }  
	  
	    @Override  
	    public void onTestSkipped(ITestResult result) {  
	        // TODO Auto-generated method stub  
	    }  
	  
	    @Override  
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	        // TODO Auto-generated method stub  
  
	    }  
	  
	    @Override  
	    public void onStart(ITestContext context) {  

	          
	    }  
	  
	    @Override  
	    public void onFinish(ITestContext context) {  
	        // TODO Auto-generated method stub  
	          
	    }  


}
