package com.medecision.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
public class JsonCreation  {
	@SuppressWarnings("unchecked")
	public Map<String, String> EnvironmentValues(String Envname) throws IOException {
	Map<String, String> EnvValuesMap = new LinkedHashMap<String, String>();
	Envname=Envname.toUpperCase();
	ObjectMapper mapper = new ObjectMapper();
//	JsonNode rootNode = mapper.createObjectNode();
//
//	JsonNode childNode1 = mapper.createObjectNode();
//	((ObjectNode) childNode1).put("Url", "https://qa.gsihealth.net");
//	((ObjectNode) childNode1).put("Uname", "automationone@gsihealth.com");
//	((ObjectNode) childNode1).put("pwd", "Test123#");
//	((ObjectNode) childNode1).put("PatientID", "5");
//
//	((ObjectNode) rootNode).set("QA", childNode1);
//
//	JsonNode childNode2 = mapper.createObjectNode();
//	((ObjectNode) childNode2).put("Url", "https://qb.gsihealth.net");
//	((ObjectNode) childNode2).put("Uname", "automationone@gsihealth.com");
//	((ObjectNode) childNode2).put("pwd", "Test123#");
//
//	((ObjectNode) rootNode).set("QB", childNode2);
//
//	JsonNode childNode3 = mapper.createObjectNode();
//	((ObjectNode) childNode3).put("Url", "https://stg.gsihealth.net");
//	((ObjectNode) childNode3).put("Uname", "automationone@gsihealth.com");
//	((ObjectNode) childNode3).put("pwd", "Test123#");
//	
//	((ObjectNode) rootNode).set("STG", childNode3);
//	
//	JsonNode childNode4 = mapper.createObjectNode();
//	((ObjectNode) childNode4).put("Url", "https://escrow.gsihealth.net");
//	((ObjectNode) childNode4).put("Uname", "esautomationone@test.com");
//	((ObjectNode) childNode4).put("pwd", "Test123#");
//	((ObjectNode) childNode4).put("toaddress", "esautomationone_test@escrowdirect.gsihealth.net");
//	
//	((ObjectNode) childNode4).put("PatientID", "1200");
//	((ObjectNode) childNode4).put("Address1", "add mod");
//	((ObjectNode) rootNode).set("ESCROW", childNode4);
	InputStream file = null;
	try {
		file = new FileInputStream("./DataFiles/EnvironmentTestData.json");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	Map<String, Object> jsonMaps = null;
	try {
		jsonMaps = mapper.readValue(file, new TypeReference<Map<String, Object>>() {});
	} catch (JsonParseException e) {
		e.printStackTrace();
	} catch (JsonMappingException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	EnvValuesMap.putAll((Map<String,String>) jsonMaps.get(Envname));
	EnvValuesMap.putAll((Map<String,String>) jsonMaps.get("PasswordReset"));
	return EnvValuesMap;
}
//	public static void main(String args[]) throws IOException {
//		Map<String, String> EnvValuesMap = new LinkedHashMap<String, String>();
//		EnvValuesMap =new JsonCreation().EnvironmentValues("escrow");
//		System.out.println(EnvValuesMap);
//	}

public PasswordReset passResetJson(String env) throws IOException {
		Map<String, Map<String, Object>> jsonMaps = readEnvJsonFile();
		PasswordReset passwordReset = new PasswordReset();
		try {
			passwordReset = new ObjectMapper().readValue(
					new ObjectMapper().writeValueAsString(jsonMaps.get("PasswordReset")),
					PasswordReset.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return passwordReset;
	}
	
	public EnvJsonData readEnvJson(String env) throws IOException {
		Map<String, Map<String, Object>> jsonMaps = readEnvJsonFile();
		EnvJsonData passwordReset = new EnvJsonData();
		try {
			passwordReset = new ObjectMapper().readValue(
					new ObjectMapper().writeValueAsString(jsonMaps.get(env)),
					EnvJsonData.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return passwordReset;
	}
	
	public Map<String, Map<String, Object>> readEnvJsonFile(){
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
		return jsonMaps;
	}
}
