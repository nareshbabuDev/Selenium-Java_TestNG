package com.medecision.pages;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.medecison.selenium.api.base.SeleniumBase;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class MessageappPage extends SeleniumBase

{
	protected  RemoteWebDriver driver;
	public static String MessageInput,toaddress;
	public MessageappPage(RemoteWebDriver driver, ExtentTest test) {
		
		super(driver, test);
		this.driver = driver;
		this.test =test;
		
	}
	public MessageappPage MesageAppNavigation()
	{
		try{
			verifyelementpresentExplicitwait("//div[contains(@id,'appMessages_menu')]");
			click("//div[contains(@id,'appMessages_menu')]");
			newwindow();
			boolean verify=verifyelementpresentExplicitwait("//button[contains(text(),'New')]",180);
			if(verify) {
			test.log(LogStatus.PASS,"Navigated to the Message app successfully");
			}else {
			test.log(LogStatus.FAIL,"Message App Navigation failed");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
			}
			
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Navigate Message App");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
	}
	
	
	public MessageappPage GettoAddress()
	{
		try {
			verifyelementpresentExplicitwait("//div[contains(@class,'loginfo-label')]", 5);
			toaddress=getElementText("//div[contains(@class,'loginfo-label')]").trim();
			test.log(LogStatus.INFO,"Message to Address is:<font color='blue'>"+toaddress+"</font>");
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to get to address");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;	
	}
	public MessageappPage CreateNewMsg() {
		DateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
	    Date date = new Date();
	    MessageInput = "Test_"+dateFormat.format(date);
		try {
			boolean verify = verifyelementpresentExplicitwait("//button[contains(text(),'New')]", 2);
			if(verify) {
			click("//button[contains(text(),'New')]");
			try {
			boolean verifynewmsgpage = verifyelementpresentExplicitwait("//div[text()='To:']/../..//following-sibling::td/textarea", 180);
			if(verifynewmsgpage) {
				clearAndType("//div[text()='To:']/../..//following-sibling::td/textarea", toaddress);
				clearAndType("//div[contains(text(),'Subject')]/..//following-sibling::td/input", MessageInput);
				test.log(LogStatus.INFO,"Composed Message is:<font color='blue'>"+MessageInput+"</font>");
			}
			else {
				test.log(LogStatus.FAIL,"Unable to click the To address text area");
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL,"Unable to Navigate Compose Mail Page");
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
			}else {
				test.log(LogStatus.FAIL,"Message App is Down");
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable Sent New Message");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
	}
	public MessageappPage SendBtn() {
		try {
			verifyelementpresentExplicitwait("//*[contains(text(),'Send')]//parent::button", 5);
			click("//*[contains(text(),'Send')]//parent::button");
			Thread.sleep(10000);
			test.log(LogStatus.INFO,"Send Button Clicked, Message Sent");
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Click Send Button");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
	}
	
	public MessageappPage MessageVerification() throws Exception
	{
		try {
			boolean verify = verifyelementpresentExplicitwait("//a[contains(text(),'Refresh')]", 120);
			if(verify) {
			for(int i=1;i<=5;i++) {
			click("//a[contains(text(),'Refresh')]");
			}
			Thread.sleep(10000);
			try {
			boolean verifynewmsgpage = verifyelementpresentExplicitwait("(//tr[contains(@class,'msgtable-unseen')]//td[contains(text(),'"+MessageInput+"')])[1]", 120);
			if(verifynewmsgpage) {
				String MsgReceived = getElementText("(//tr[contains(@class,'msgtable-unseen')]//td[contains(text(),'"+MessageInput+"')])[1]").trim();
				if(MsgReceived.equals(MessageInput)) {
					test.log(LogStatus.PASS,"Message Received Successfully");
				}else {
					test.log(LogStatus.FAIL,"Sent Message Not Received in Receiver's Inbox");
					TakescreenShot(driver,test);
					closewindow();
					Assert.fail();
				}
			}
			else {
				test.log(LogStatus.FAIL,"No Unread Message Shown in Message App");
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
			}
			catch(Exception e) {
				test.log(LogStatus.FAIL,"No Unread Message Shown in Message App");
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
			}
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Click Refresh Button");
			TakescreenShot(driver,test);
			closewindow();
			Assert.fail();
		}
		return this;
		
	}
	public MessageappPage MarkAsRead() {
		try {
			verifyelementpresentExplicitwait("(//tr[contains(@class,'msgtable-unseen')]//td//input)[1]", 10);
			click("(//tr[contains(@class,'msgtable-unseen')]//td//input)[1]");
			Thread.sleep(3000);
			click("//button[contains(text(),'Mark as Read')]");
			Thread.sleep(3000);
			test.log(LogStatus.PASS,"Received Messaage Marked as read");
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Unable to Click Marke as read Button");
			TakescreenShot(driver,test);
			closewindow();
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
	public void closewindow() {
	 	List<String> windows = new ArrayList<>(driver.getWindowHandles());
	    String parentWindow = windows.get(0); 
	    String childWindow = windows.get(1);
	    try {
	    driver.switchTo().window(childWindow).close();
	    driver.switchTo().window(parentWindow);
    	verifyelementpresentExplicitwait("//div[contains(@id,'appHome_menu')]",60);
		actionsclick("//div[contains(@id,'appHome_menu')]");
		test.log(LogStatus.PASS,"Message App Closed and Navigted to Home Page");
	    }catch(Exception e) {
	    	test.log(LogStatus.FAIL,"Message App Not Closed, Unable to Navigte to Home Page");
	    	TakescreenShot(driver,test);
	    }
	}
	
	public MessageappPage MesageSentAndReceiveFunctionality() {
		boolean flag=false;
		try {
			MesageAppNavigation()
			.GettoAddress()
			.CreateNewMsg()
			.SendBtn()
			.MessageVerification()
			.MarkAsRead();
			closewindow();
			flag =true;
		}
		catch(Exception e) {
			flag=false;
		}
		finally {
			if(!flag) {
				TakescreenShot(driver,test);
				closewindow();
				Assert.fail();
			}
		}
		return this;
	}
	
}
