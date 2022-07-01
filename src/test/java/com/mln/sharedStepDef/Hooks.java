package com.mln.sharedStepDef;



import org.openqa.selenium.WebDriver;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.Common_Utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	WebDriver driver;
	DriverManager driverManager;
	TestContext testContext;
	String strTestName = "TC-1";
	Common_Utility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);
	
	public Hooks(TestContext context) {
		testContext = context;
	}

		
	

	
	@Before
	public void BeforeSteps(Scenario scenario) {
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