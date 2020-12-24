package com.medecision.utils;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PasswordReset {

	public ArrayList<String> envList;
	public String Password;
}
