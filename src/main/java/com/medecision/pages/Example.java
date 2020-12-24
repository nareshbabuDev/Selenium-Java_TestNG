package com.medecision.pages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.*;
import org.apache.commons.codec.binary.Base64;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Example {
	public static void main(String args[]) {
//	byte[] DecodedPass = Base64.decodeBase64("TWVkZCM1MDgw");
//	System.out.println(new String (DecodedPass));
		ArrayList<String> UIUsesrList = new ArrayList<String>();
		UIUsesrList.add("a");
		System.out.println(UIUsesrList.size());
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date currentDate = new Date();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        // manipulate date
        c.add(Calendar.YEAR,0);
        c.add(Calendar.MONTH,0);
        c.add(Calendar.DATE, -1); 
        Date currentDatePlusOne = c.getTime();
        System.out.println(dateFormat.format(currentDatePlusOne));
	}
	
}
