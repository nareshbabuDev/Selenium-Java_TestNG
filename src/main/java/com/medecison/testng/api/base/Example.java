package com.medecison.testng.api.base;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
public class Example {
	


	    public static void main(String args[]) throws IOException {
	        String orig = "Medd#5080";

	        //encoding  byte array into base 64
	        byte[] encoded = Base64.encodeBase64URLSafe(orig.getBytes());     
	      
	        System.out.println("Original String: " + orig );
	        System.out.println("Base64 Encoded String : " + new String(encoded));
	      
	        //decoding byte array into base64
	        byte[] decoded = Base64.decodeBase64("TWVkZCM1MDgw");      
	        System.out.println("Base 64 Decoded  String : " + new String(decoded));

	    }

}
