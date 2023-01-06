package com.mln.sharedStepDef;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.Login_MyStoreExtPF;
import com.mln.pageFactory.Login_MyStorePF;
import com.mln.utilities.ExcelUtility;
import com.mln.utilities.CommonUtility;

import io.cucumber.java.en.Then;

public class Generic {
	
	
	    private static Logger logger = LogManager.getLogger(Generic.class);	
		WebDriver driver;
		DriverManager driverManager;
		Login_MyStoreExtPF login_mystoreExtPF;
		TestContext testContext;
		Login_MyStorePF login_mystorePF;
		String strTestName = "TC-1";
		CommonUtility wm;
		String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
		ExcelUtility em = new ExcelUtility(strTestName);
	
		
		public Generic(TestContext context) throws MalformedURLException {
			testContext = context;
			driver = testContext.getDriverManager().getDriver();
			login_mystoreExtPF = testContext.getPageFactoryManager().getlogin_mystoreExtPF();
			login_mystorePF = testContext.getPageFactoryManager().getlogin_mystorePF();
			wm =testContext.getCommon_Utility();
		}
	
	@Then("^the user should be navigated to (.*)$")
	public void the_user_should_be_navigated_Page(String strPageName) {
		boolean bResult = false;
		try {
			strPageName = strPageName.replace(" ","").trim().toUpperCase();
			strPageName= strPageName.replace("<", "");
			strPageName= strPageName.replace(">", "");
			switch(strPageName){

			case "LOGIN_MYSTORE":
				bResult= login_mystorePF.VerifyLogin_MyStore(wm);
				 
				break;
			case "LOGIN_MYSTOREEXT":
				bResult= login_mystoreExtPF.VerifyLogin_MyStoreExt(wm);
				break;
			}
		} catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			bResult=false;
			logger.info(e.getStackTrace());
		}finally{
			if (bResult==true){
				logger.info ("The user is naviaged to  " + strPageName);
				wm.UpdateExcelReport("The user is naviaged to  " + strPageName,"PASS");

			}else{
				logger.error ("The user is not naviaged to  " + strPageName);
				wm.UpdateExcelReport("The user is not naviaged to  " + strPageName,"FAIL");
			}
		}

	}
	
	@Then("user should be navigated to home page")
	public void user_should_be_navigated_to_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		//System.out.println("user should be navigated to home page");
	    //throw new io.cucumber.java.PendingException();
	}


}
