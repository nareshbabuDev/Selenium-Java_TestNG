package com.medecison.testng.api.base;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvPatientID {
	public ArrayList<String> PatientID;
	public ArrayList<String> ExistingCareTeam;
	public ArrayList<String> UserID;
	
	public ArrayList<String> CurrentEnvPIDUIDCT(String name){
		switch (name) {
		case "PatientID":
			return this.PatientID;
		case "ExistingCareTeam":
			return this.ExistingCareTeam;
		case "UserID":
			return this.UserID;
		default:
			return null;
	}
	}
	
}
