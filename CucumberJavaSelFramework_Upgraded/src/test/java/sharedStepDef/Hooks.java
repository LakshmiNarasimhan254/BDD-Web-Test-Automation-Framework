package sharedStepDef;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.DriverManager;
import managers.FileReaderManager;
import pageFactory.MyStorePF;
import utilities.Excel_Utility;
import utilities.Wrapper_Methods;

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
	

	@After
	public void AfterSteps() {
		testContext.getDriverManager().closeDriver();
	}

}