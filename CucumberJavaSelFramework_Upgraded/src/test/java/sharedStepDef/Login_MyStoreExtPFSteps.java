package sharedStepDef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;

import cucumber.TestContext;
import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.FileReaderManager;

import pageFactory.Login_MyStoreExtPF;


import utilities.Excel_Utility;
import utilities.HashMap_Utility;
import utilities.Wrapper_Methods;

public class Login_MyStoreExtPFSteps {


	WebDriver driver;
	DriverManager driverManager;
	Login_MyStoreExtPF login_mystoreExtPF;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);
	
	
	public Login_MyStoreExtPFSteps(TestContext context) {
		testContext = context;
		//driver = testContext.getDriverManager().getDriver();
		 wm = testContext.getWrapperMethods();
		login_mystoreExtPF = testContext.getPageObjectManager().getlogin_mystoreExtPF();
		
	}


	@And("^User enters the value(.*) in Login_MyStoreExt Page$")
	public void enters_the_txt(String strFieldNameValue) {
		strFieldNameValue = strFieldNameValue.trim();
		strFieldNameValue= strFieldNameValue.replace("<", "");
		strFieldNameValue= strFieldNameValue.replace(">", "");
		String[] strFieldNameValues = strFieldNameValue.split(",");
		//System.out.println(strFieldNameValues.length);
		HashMap<String, String> hm = HashMap_Utility.strArr_HM_usingSeparator(strFieldNameValues,":=");
		Iterator<Entry<String, String>> iterKeyVal =hm.entrySet().iterator();
		String strElement;
		while(iterKeyVal.hasNext()){
			Entry<String, String> enkeyVal = iterKeyVal.next();
			strElement= enkeyVal.getKey();
			strFieldNameValue= enkeyVal.getValue();;
			if(strFieldNameValue.startsWith("ExcelData")){				
				strFieldNameValue=strFieldNameValue.replace("ExcelData|", "");
				//System.out.println(strFieldNameValue);
				strFieldNameValue=em.getCellValueStringSearchtxt(strTestName, strFieldNameValue);
			}
			//System.out.println(strElement +"  "+strFieldNameValue );
			login_mystoreExtPF.EnterTxt(wm, strElement, strFieldNameValue);
		}

	}



	@And("^The user Clicks on (.*) in Login_MyStoreExt Page$")
	public void clicks_on_Login_MyStoreEXT_page(String strFieldName) {
		strFieldName = strFieldName.replace(" ","").trim().toUpperCase();
		strFieldName= strFieldName.replace("<", "");
		strFieldName= strFieldName.replace(">", "");
		

		switch(strFieldName){
		case "MR":
			login_mystoreExtPF.SelectTitle(wm, "MR");
			break;
		case "MRS":
			login_mystoreExtPF.SelectTitle(wm, "MRS");
			break;
		case "REGISTER":
			login_mystoreExtPF.ClickRegister(wm);
			break;

		default:
			throw new IllegalStateException("The Field value given is not correct");
		}



	}



}
