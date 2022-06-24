package com.mln.dataProviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.mln.enums.BrowserType;
import com.mln.enums.EnvironmentType;

public class ConfigFileReader {
	private Properties properties = new Properties();
	private final String propertyFilePath= "config/Config.properties";
	


	public String getProperties(String strKey){
		InputStream input;
		try {
			input = new FileInputStream(propertyFilePath);
			properties.load(input);
			return properties.getProperty(strKey);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strKey;
			}


	public  void setProperties(String strKey,String strKeyValue){
		OutputStream output;
		try {
			output = new FileOutputStream(System.getProperty("user.dir")+ "/src/test/java/config/config.properties");
			properties.setProperty(strKey, strKeyValue);
			properties.save(output, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	
	public String getDriverPath(){
		String driverPath = getProperties("driverPath");
		if(driverPath!= null) return driverPath;
		else throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key:driverPath");		
	}
	
	
	public long getImplicitlyWait() {		
		String implicitlyWait = getProperties("implicitlyWait");
		if(implicitlyWait != null) {
			try{
				return Long.parseLong(implicitlyWait);
			}catch(NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;		
	}

	public String getApplicationUrl() {
		String url = getProperties("url");
		if(url != null) return url;
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
	}

	public BrowserType getBrowser() {
		String browserName = getProperties("browser");
		if(browserName == null || browserName.equals("chrome")) return BrowserType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return BrowserType.FIREFOX;
		else if(browserName.equals("iexplorer")) return BrowserType.INTERNETEXPLORER;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public EnvironmentType getEnvironment() {
		String environmentName = getProperties("environment");
		if(environmentName == null || environmentName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
		else if(environmentName.equals("remote")) return EnvironmentType.REMOTE;
		else throw new RuntimeException("Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}

	public Boolean getBrowserWindowSize() {
		String windowSize = getProperties("windowMaximize");
		if(windowSize != null) return Boolean.valueOf(windowSize);
		return true;
	}

}
