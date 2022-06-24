package com.mln.managers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.mln.dataProviders.*;
import com.mln.enums.BrowserType;
import com.mln.enums.EnvironmentType;

import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverManager {


	private WebDriver driver;
	private static BrowserType browserType;
	private static EnvironmentType environmentType;


	public DriverManager() {
		browserType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		   switch (environmentType) {	    
	        case LOCAL : driver = createLocalDriver();
	        	break;
	        case REMOTE : driver = createRemoteDriver();
	        	break;
		   }
		   return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() {
        switch (browserType) {	    
        case FIREFOX : 
        	WebDriverManager.firefoxdriver().setup();
        	driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	WebDriverManager.chromedriver().setup();
        	driver = new ChromeDriver();
    		break;
        case INTERNETEXPLORER : 
        	WebDriverManager.iedriver().setup();
        	driver = new InternetExplorerDriver();
    		break;
    		
        case EDGE : 
        	WebDriverManager.edgedriver().setup();
        	driver = new EdgeDriver();
    		break;
        }

		if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
		}
		return driver;
	}
	
		public void closeDriver() {
		driver.close();
		driver.quit();
	}


}
