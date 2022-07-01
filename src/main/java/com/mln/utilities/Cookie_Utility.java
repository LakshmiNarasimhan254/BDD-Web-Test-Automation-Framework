package com.mln.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Cookie_Utility {

	
	public static void getmycookies(WebDriver driver){
		try {
			File file = new File("Cookies.data");
			file.createNewFile();
			FileWriter filewritter = new FileWriter(file);
			BufferedWriter bufferwrite = new BufferedWriter(filewritter);
			for(Cookie ck : driver.manage().getCookies())							
			{	bufferwrite.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()+";"+ck.getPath()+";"+ck.getExpiry()+";"+ck.isSecure()));		
			bufferwrite.newLine();	
			}
			bufferwrite.close();			
			filewritter.close();


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addmyCookies(WebDriver driver){
		try {
			File file = new File("Cookies.data");
			FileReader filereader = new FileReader(file);
			BufferedReader bufferreader = new BufferedReader(filereader);
			String[]cookie_data = bufferreader.readLine().split(";");
			
			String name = cookie_data[0];					
	        String value = cookie_data[1];					
	        String domain = cookie_data[2];					
	        String path = cookie_data[3];	
	       	String DateValue = cookie_data[4]; 
	       	String BooleanValue = cookie_data[4];
	       	
	       	Date expiry = null;
	       	Boolean isSecure = null;
	       	if (!DateValue.equals("null")){
	       		expiry = new Date(DateValue);	
	       	}
	       	
	       	if (!BooleanValue.equals("null")){
	       		isSecure  = new Boolean(BooleanValue);	
	       	}
	       	Cookie ck = new Cookie(name,value,domain,path,expiry,isSecure);			
	        //System.out.println(ck);
	        driver.manage().addCookie(ck); // This will add the stored cookie to your current session				
	       	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		

}
