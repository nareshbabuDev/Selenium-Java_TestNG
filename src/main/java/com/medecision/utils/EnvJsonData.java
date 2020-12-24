package com.medecision.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvJsonData {

	public String PopManagerUser;
	public String TestUserIDTwo;
	public String TestUserIDOne;
	public String Url;
}
