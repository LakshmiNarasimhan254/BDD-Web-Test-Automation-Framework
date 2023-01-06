package com.mln.sharedStepDef;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.Login_MyStorePF;
import com.mln.utilities.ExcelUtility;
import com.mln.utilities.CommonUtility;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class Login_MyStorePFSteps {


	private static Logger logger = LogManager.getLogger(Login_MyStoreExtPFSteps.class);	
	WebDriver driver;
	DriverManager driverManager;
	Login_MyStorePF login_mystorePF;
	TestContext testContext;
	String strTestName = "TC-1";
	CommonUtility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	ExcelUtility em = new ExcelUtility(strTestName);



	public Login_MyStorePFSteps(TestContext context) {
		testContext = context;
		//driver = testContext.getDriverManager().getDriver();
		login_mystorePF = testContext.getPageFactoryManager().getlogin_mystorePF();
		wm =testContext.getCommon_Utility();
	}


	@Given("^User is in loginpage$")
	public void user_is_in_loginpage()throws Throwable  {
		login_mystorePF.VerifyLogin_MyStore(wm);

	}
	@When("^User enters username and password$")
	public void user_enters_username_and_password(DataTable usercredentials)throws Throwable  {

		// Write code here that turns the phrase above into concrete actions
		List<Map<String,String>> data = usercredentials.asMaps(String.class, String.class); 
		String struseremail = data.get(0).get("username");
		String strpassword = data.get(0).get("password");

		login_mystorePF.EnterEmailaddress(wm, struseremail);
		login_mystorePF.EnterPassword(wm,strpassword );

		//System.out.println("User enters username and password");
		//throw new io.cucumber.java.PendingException();
	}

	@And("^clicks on login$")
	public void click_on_login() {
		boolean bResult = false;
		try {
			login_mystorePF.ClickSignin(wm);
		} catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The login was clicked succesfully"  );
				wm.UpdateExcelReport("The login was clicked succesfully" ,"PASS");
			}else{
				logger.error("The login could not be clicked"  );
				wm.UpdateExcelReport("The login could not be clicked" ,"FAIL");
			}
		}

	}



	@And("^Enters the (.*) in (.*) in Login_MyStore Page$")
	public void enters_the_txt_Login_MyStore(String Value,String strFieldName) {
		boolean bResult = false;
		try{

			String stxtboxvalue ="";
			if(Value.startsWith("ExcelData")){
				stxtboxvalue=Value.split("|")[1];

			}
			strFieldName = strFieldName.replace(" ","").trim().toUpperCase();
			strFieldName= strFieldName.replace("<", "");
			strFieldName= strFieldName.replace(">", "");
			switch(strFieldName){
			case "EMAILADDRESS":
				stxtboxvalue =em.getCellValueString(1, 3);

				break;
			}
			//stxtboxvalue =em.getCellValueString(1, 3);

			login_mystorePF.EnterTxtCreateEmail(wm, stxtboxvalue);
			bResult = true;
		} catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The " + Value +" was entered succesfully in " + strFieldName   );
				wm.UpdateExcelReport("The " + Value +" was entered succesfully in " + strFieldName ,"PASS");
			}else{
				logger.error("The " + Value +" could not be  entered  in " + strFieldName  );
				wm.UpdateExcelReport("The " + Value +" could not be entered in " + strFieldName ,"FAIL");
				//wm.takeScreen();
			}
		}
	}


	@And("^Clicks on (.*) in Login_MyStore Page$")
	public void clicks_on_Login_MyStore_page(String strFieldName) {
		boolean bResult = false;
		try{
			strFieldName = strFieldName.replace(" ","").trim().toUpperCase();
			strFieldName= strFieldName.replace("<", "");
			strFieldName= strFieldName.replace(">", "");

			switch(strFieldName){
			case "CREATEEMAIL":
				login_mystorePF.ClickCreateAccount(wm);
				bResult=true;
				break;
			case "LOGIN" :
				login_mystorePF.ClickSignin(wm);
				bResult=true;
				break;

			default:
				bResult=false;
				//throw new IllegalStateException("The Field value given is not correct");
			}
		}catch(NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The " +strFieldName + " was clicked successfully"  );
				wm.UpdateExcelReport("The " +strFieldName + " was clicked successfully","PASS");
			}else{
				logger.error("The " +strFieldName + " could not be  clicked"  );
				wm.UpdateExcelReport("The " +strFieldName + " could not be  clicked","FAIL");
			}
		}
	}

}


