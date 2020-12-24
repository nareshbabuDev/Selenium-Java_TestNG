package com.medecision.utils;

import java.io.IOException;
import java.util.*;


public class DataLibrary {

	public static Object[][] ReadTestData(String JsonFile) throws IOException {
		Map<String, String> EnvironmentValues = new LinkedHashMap<String, String>();
		EnvironmentValues =new JsonCreation().EnvironmentValues(JsonFile);
		return new Object[][]{
	          {EnvironmentValues}
	      };
	}
	
	public static PasswordReset ReadPasswordResetData(String JsonFile) throws IOException {
		return new JsonCreation().passResetJson(JsonFile);
	}
	
	public static EnvJsonData ReadEnvJsonData(String JsonFile) throws IOException {
		return new JsonCreation().readEnvJson(JsonFile);
	}
	public static Map<String, Map<String, Object>> ReadEnvJsonFile() throws IOException {
		return new JsonCreation().readEnvJsonFile();
	}
}







