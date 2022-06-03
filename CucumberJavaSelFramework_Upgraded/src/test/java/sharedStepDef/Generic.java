package sharedStepDef;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import io.cucumber.java.en.Then;
import managers.DriverManager;
import managers.FileReaderManager;
import managers.PageObjectManager;
import pageFactory.Login_MyStoreExtPF;
import pageFactory.Login_MyStorePF;
import pageFactory.MyStorePF;
import utilities.Excel_Utility;
import utilities.Wrapper_Methods;

public class Generic {
	
	
    		
		WebDriver driver;
		DriverManager driverManager;
		Login_MyStoreExtPF login_mystoreExtPF;
		TestContext testContext;
		Login_MyStorePF login_mystorePF;
		String strTestName = "TC-1";
		Wrapper_Methods wm;
		String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
		Excel_Utility em = new Excel_Utility(strTestName);
	
		
		public Generic(TestContext context) {
			testContext = context;
			driver = testContext.getDriverManager().getDriver();
			login_mystoreExtPF = testContext.getPageObjectManager().getlogin_mystoreExtPF();
			login_mystorePF = testContext.getPageObjectManager().getlogin_mystorePF();
			wm =testContext.getWrapperMethods();
		}
	
	@Then("^the user should be navigated to (.*)$")
	public void the_user_should_be_navigated_Page(String strPageName) {
		strPageName = strPageName.replace(" ","").trim().toUpperCase();
		strPageName= strPageName.replace("<", "");
		strPageName= strPageName.replace(">", "");
		switch(strPageName){

		case "LOGIN_MYSTORE":
			 login_mystorePF.VerifyLogin_MyStore(wm);
			break;
		case "LOGIN_MYSTOREEXT":
				login_mystoreExtPF.VerifyLogin_MyStoreExt(wm);
			break;
		}
	}
	
	@Then("user should be navigated to home page")
	public void user_should_be_navigated_to_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		//System.out.println("user should be navigated to home page");
	    //throw new io.cucumber.java.PendingException();
	}


}
