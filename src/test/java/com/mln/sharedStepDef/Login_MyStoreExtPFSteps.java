package com.mln.sharedStepDef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.Login_MyStoreExtPF;
import com.mln.utilities.ExcelUtility;
import com.mln.utilities.CommonUtility;

import io.cucumber.java.en.*;

public class Login_MyStoreExtPFSteps {

	private static Logger logger = LogManager.getLogger(Login_MyStoreExtPFSteps.class);	
	WebDriver driver;
	DriverManager driverManager;
	Login_MyStoreExtPF login_mystoreExtPF;
	TestContext testContext;
	String strTestName = "TC-1";
	CommonUtility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	ExcelUtility em = new ExcelUtility(strTestName);


	public Login_MyStoreExtPFSteps(TestContext context) {
		testContext = context;
		//driver = testContext.getDriverManager().getDriver();
		wm = testContext.getCommon_Utility();
		login_mystoreExtPF = testContext.getPageFactoryManager().getlogin_mystoreExtPF();

	}


	@And("^User enters the value(.*) in Login_MyStoreExt Page$")
	public void enters_the_txt(String strFieldNameValue) {
		boolean bResult = false;
		String strElement ="";
		try{
			strFieldNameValue = strFieldNameValue.trim();
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");
			String[] strFieldNameValues = strFieldNameValue.split(",");
			//System.out.println(strFieldNameValues.length);
			HashMap<String, String> hm = wm.strArr_HM_usingSeparator(strFieldNameValues,":=");
			Iterator<Entry<String, String>> iterKeyVal =hm.entrySet().iterator();

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
		}catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The " + strFieldNameValue + " was entered in " +strElement  );
				wm.UpdateExcelReport("The " + strFieldNameValue + " was entered in " +strElement ,"PASS");
			}else{
				logger.error("The " + strFieldNameValue + " could not be entered in " +strElement );
				wm.UpdateExcelReport("The " + strFieldNameValue + " could not be entered in " +strElement ,"FAIL");
			}

		}


	}



	@And("^The user Clicks on (.*) in Login_MyStoreExt Page$")
	public void clicks_on_Login_MyStoreEXT_page(String strFieldName) {

		boolean bResult = false;

		strFieldName = strFieldName.replace(" ","").trim().toUpperCase();
		strFieldName= strFieldName.replace("<", "");
		strFieldName= strFieldName.replace(">", "");

		try{
			switch(strFieldName){
			case "MR":
				login_mystoreExtPF.SelectTitle(wm, "MR");
				bResult=true;
				break;
			case "MRS":
				login_mystoreExtPF.SelectTitle(wm, "MRS");
				bResult=true;
				break;
			case "REGISTER":
				login_mystoreExtPF.ClickRegister(wm);
				bResult=true;
				break;

			default:
				bResult=false;
				//throw new IllegalStateException("The Field value given is not correct");
			}
		}catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The " + strFieldName + " was clicked succesfully"  );
				wm.UpdateExcelReport("The " + strFieldName + " was clicked succesfully" ,"PASS");
			}else{
				logger.error("The " + strFieldName + " could not be clicked."  );
				wm.UpdateExcelReport("The " + strFieldName + " could not be clicked." ,"FAIL");
			}
		}




	}



}
