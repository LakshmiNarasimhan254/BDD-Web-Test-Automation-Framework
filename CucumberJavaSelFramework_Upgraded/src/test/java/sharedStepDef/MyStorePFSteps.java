package sharedStepDef;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.FileReaderManager;

import pageFactory.MyStorePF;
import utilities.Excel_Utility;
import utilities.Wrapper_Methods;


public class MyStorePFSteps {

	WebDriver driver;
	DriverManager driverManager;
	MyStorePF mystorePF;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);



	

	public MyStorePFSteps(TestContext context) {
		System.out.println(strUrl);
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getWrapperMethods();
		//wm.LaunchApp(driver,strUrl);
		//mystorePF = testContext.getPageObjectManager().getmystorePF();
		
		
	}


//	@Before(value="@functional")
//	public void OpenBrowser(){
//		driver = testContext.getDriverManager().getDriver();
//		System.out.println("This is executed before the first step");
//	}

	//	@After(value="@functional")
	//	public void CloseBrowser(){
	//		System.out.println("This is executed after  the final  step");
	//		wm.CloseApp(driver);	
	//	}


	@Given("The user is in MyStore Page")
	public void the_user_is_in_my_store_page() {
		//wm.LaunchApp(driver,strUrl);		
		mystorePF = testContext.getPageObjectManager().getmystorePF();
		
	}
	@When("^Clicks on (.*) in MyStore Page$")
	public void the_user_clicks_on_MyStore_Page(String strFieldName) {
		strFieldName = strFieldName.replace(" ","").trim().toUpperCase();
		strFieldName= strFieldName.replace("<", "");
		strFieldName= strFieldName.replace(">", "");
		System.out.println(strFieldName);


		switch(strFieldName){
		case "SIGNIN":
			mystorePF.ClickSignin(wm);
			break;
		default:
			throw new IllegalStateException("The Field value given is not correct");
		}		
	}

}
