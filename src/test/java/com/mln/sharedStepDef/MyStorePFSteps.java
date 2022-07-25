package com.mln.sharedStepDef;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.MyStorePF;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.Common_Utility;


import io.cucumber.java.en.*;
	

public class MyStorePFSteps {

	private static Logger logger = LogManager.getLogger(MyStorePFSteps.class);
	DriverManager driverManager;
	MyStorePF mystorePF;
	TestContext testContext;
	String strTestName = "TC-1";
	Common_Utility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);





	public MyStorePFSteps(TestContext context) {
		testContext = context;
		wm = testContext.getCommon_Utility();


	}




	@Given("The user is in MyStore Page")
	public void the_user_is_in_my_store_page() {
		Boolean bResult = false;
		try {
			mystorePF = testContext.getPageFactoryManager().getmystorePF();
			if(wm.getTitle().equalsIgnoreCase("My tore")){
				bResult= true;
				
			}
		} catch (Exception e) {
				logger.info(e.getStackTrace());
				//throw new IllegalStateException("This is not My Store Page. The Current Page is " + wm.getTitle());
				
		}finally{
			if (bResult==true){
				logger.info ("The Current Page is " + wm.getTitle() + " and is  as expected.");
				wm.UpdateExcelReport("The Current Page is " + wm.getTitle() + " and is  as expected.","PASS");
			}else{
				logger.error("The Current Page is " + wm.getTitle() + " and is not as expected.");
				wm.UpdateExcelReport("The Current Page is " + wm.getTitle() + " and is not as expected.","PASS");
			}
		}

	}
	@When("^Clicks on (.*) in MyStore Page$")
	public void the_user_clicks_on_MyStore_Page(String strFieldName) {
		Boolean bResult = false;
		String UserInput = ""; 
		try {
			strFieldName= strFieldName.replace("<", "");
			UserInput= strFieldName.replace(">", "");
			strFieldName = UserInput.replace(" ","").trim().toUpperCase();		
				switch(strFieldName){
				case "SIGNIN":
					mystorePF.ClickSignin(wm);
					 bResult = true;
					break;
				case "BLOUSE":
					mystorePF.ClicktoViewPopular(wm, "Blouse");
					 bResult = true;
					break;
				case "PRINTEDDRESS":
					mystorePF.ClicktoViewPopular(wm, "Printed Dress");
					bResult = true;
					break;
				default:
					 bResult = false;
					 break;
				}
		} catch (Exception e) {
			
			logger.info(e.getStackTrace());
		}finally{
			if (bResult==true){
				logger.info ("The " + UserInput+ " was  clicked succesfully.");
				wm.UpdateExcelReport("The " + UserInput+ " was  clicked succesfully.","PASS");
			}else{
				logger.error("The " + UserInput+ " could not be clicked ");
				wm.UpdateExcelReport("The " + UserInput+ " could not be clicked.","FAIL");
			}
		}
			
	}

	}
