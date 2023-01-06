package com.mln.sharedStepDef;



import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.utilities.ExcelUtility;
import com.mln.utilities.CommonUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	WebDriver driver;
	DriverManager driverManager;
	TestContext testContext;
	String strTestName = "TC-1";
	CommonUtility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	ExcelUtility em = new ExcelUtility(strTestName);
	
	public Hooks(TestContext context) {
		testContext = context;
	}

		
	

	
	@Before
	public void BeforeSteps(Scenario scenario) throws MalformedURLException {
		strTestName= scenario.getName();
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getCommon_Utility();
		wm.setsTestName(strTestName);
		wm.launchApp(strUrl);
	
	}	
		
	

	@After
	public void AfterSteps(Scenario scenario) {
		testContext.getDriverManager().closeDriver();
	}

}