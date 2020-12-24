package com.medecision.userprofile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.medecision.pages.LoginPage;
import com.medecision.pages.UserPasswordUpdatePage;
import com.medecision.utils.DriverInitialize;
import com.medecision.utils.EnvJsonData;
import com.relevantcodes.extentreports.LogStatus;

public class UserPasswordUpdate extends DriverInitialize {
	@Test(groups = { "UserPassword" }, dataProvider = "fetchData",alwaysRun = true)
	public void TC013_Preference_UserPasswordUpdate(Map<String, String> map) throws Exception {
		String UserID,Url;
		LoginPage lp = new LoginPage(driver, test);
		String Password = this.fetchPassReset().Password;
//		byte[] DecodedPass = Base64.decodeBase64(Password);
//		Password = new String(DecodedPass);
		Random random = new Random();
//		String newPass = "Medd#".concat(String.valueOf(random.ints(1000, 9999).findFirst().getAsInt()));
		String newPass ="Medgsi123#";
		for (String env : this.fetchPassReset().envList) {
			EnvJsonData envData = this.fetchEnvJson(env);
			UserID = envData.TestUserIDOne;
			Url = envData.Url;
			lp.enterUsername(UserID, Url).enterPassword(Password).clickLogin(UserID);
			lp.HomePageVerification(UserID);
			UserPasswordUpdatePage up = new UserPasswordUpdatePage(driver, test);
			try {
				up.clickUserProfile()
				.clickPreference()
				.clickPassword(newPass)
				.clickConfirmPassword(newPass)
				.clickSave()
				.clickOk()
				.clickClose()
				.clickLogout()
				.clickYes();
			} 
			catch (Exception e) {
				test.log(LogStatus.INFO,"<font color='red'>Password not updated for the Environment's " + env + "</font>");
			}
			}
		resetPassword(newPass);
	}
	@AfterClass
 	public void QuitDriver() {
	CloseDriver();
	}
}
