package com.mln.sharedStepDef;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.Login_MyStorePF;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.Wrapper_Methods;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class Login_MyStorePFSteps {



	WebDriver driver;
	DriverManager driverManager;
	Login_MyStorePF login_mystorePF;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);



	public Login_MyStorePFSteps(TestContext context) {
		testContext = context;
		//driver = testContext.getDriverManager().getDriver();
		login_mystorePF = testContext.getPageObjectManager().getlogin_mystorePF();
		wm =testContext.getWrapperMethods();
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
		// Write code here that turns the phrase above into concrete actions
		login_mystorePF.ClickSignin(wm);
		//System.out.println("clicks on login");
		//throw new io.cucumber.java.PendingException();
	}



	@And("^Enters the (.*) in (.*) in Login_MyStore Page$")
	public void enters_the_txt_Login_MyStore(String Value,String strFieldName) {
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

	}


	@And("^Clicks on (.*) in Login_MyStore Page$")
	public void clicks_on_Login_MyStore_page(String strFieldName) {
		strFieldName = strFieldName.replace(" ","").trim().toUpperCase();
		strFieldName= strFieldName.replace("<", "");
		strFieldName= strFieldName.replace(">", "");
		
		switch(strFieldName){
		case "CREATEEMAIL":
			login_mystorePF.ClickCreateAccount(wm);
			break;
		case "LOGIN" :
			login_mystorePF.ClickSignin(wm);
			break;

		default:
			throw new IllegalStateException("The Field value given is not correct");
		}
	}

}


