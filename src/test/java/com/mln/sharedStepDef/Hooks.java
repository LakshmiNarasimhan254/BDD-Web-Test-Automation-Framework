package com.mln.sharedStepDef;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.MyStorePF;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.Wrapper_Methods;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	WebDriver driver;
	DriverManager driverManager;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);
	
	public Hooks(TestContext context) {
		testContext = context;
	}

		
	

	
	@Before
	public void BeforeSteps(Scenario scenario) {
		strTestName= scenario.getName();
		System.out.println(scenario.getName());
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getWrapperMethods();
		wm.setsTestName(strTestName);
		wm.LaunchApp(driver,strUrl);
	
	}	
		
	
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
//	
//
	@After
	public void AfterSteps() {
		testContext.getDriverManager().closeDriver();
	}

}