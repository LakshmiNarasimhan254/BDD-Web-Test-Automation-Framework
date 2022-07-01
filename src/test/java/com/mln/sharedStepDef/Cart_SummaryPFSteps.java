package com.mln.sharedStepDef;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.Cart_SummaryPF;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.Common_Utility;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cart_SummaryPFSteps {

	private static Logger logger = LogManager.getLogger(Layer_CartPFSteps.class);
	WebDriver driver;
	DriverManager driverManager;
	Cart_SummaryPF cart_SummaryPF;
	TestContext testContext;
	String strTestName = "TC-1";
	Common_Utility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);


	public Cart_SummaryPFSteps(TestContext context) {
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getCommon_Utility();
		cart_SummaryPF = testContext.getPageFactoryManager().getcart_SummaryPF();

		//wm.SwitchFramebyName(fancyBoxFramePF.getTxtName());
	}

	@And("Verify if the user is in Cart_Summary Page")
	public void enters_the_txt() {
		boolean bResult = false;
		try{
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
			w.until(ExpectedConditions.visibilityOf(cart_SummaryPF.getTxtCartSummary()));

			if (cart_SummaryPF.getTxtCartSummary()!=null){
				//throw new IllegalStateException("The  cart summary page is not displayed");
				bResult=true;
			}
		}catch (TimeoutException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (Exception e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		}finally{
			if (bResult==true){
				logger.info ("The user is in Cart_Summary Page");
				wm.UpdateExcelReport("The user is in Cart_Summary Page", "PASS");
			}else{
				logger.error("The user is not in Cart_Summary Page");
				wm.UpdateExcelReport("The user is not in Cart_Summary Page", "FAIL");
			//wm.takeScreen();
			}
		}



	}
	@Then("^Verify if the (.*) are displayed in Cart_Summary Page$")
	public void VerifyDetailsinCartSummary(String strFieldNameValue) throws IOException{
		boolean bResult = false;

		try {
			driver.switchTo().defaultContent();
			strFieldNameValue = strFieldNameValue.trim();
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");


			if(strFieldNameValue.contains(("ExcelData"))){				
				strFieldNameValue=strFieldNameValue.replace("ExcelData|", "");
				//System.out.println(strFieldNameValue);
				strFieldNameValue=em.getCellValueStringSearchtxt(strTestName, strFieldNameValue);
			}
			bResult =cart_SummaryPF.verifyCartDetails(wm, strFieldNameValue);
		} catch (TimeoutException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (Exception e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		}finally{
			if (bResult==true){
				logger.info ("The Details are as expected in the cart details ");
				wm.UpdateExcelReport("The Details are as expected in the cart", "PASS");
			}else{
				logger.error("The Details are not as expected in the cart details ");
			wm.UpdateExcelReport("The Details are not as expected in the cart details", "FAIL");
			//wm.takeScreen();
			}
		}



	}

	@When("^Clicks on (.*) in Cart_Summary Page")
	public void clickonButtoninCartSummary(String strFieldNameValue) {
		boolean bResult = false;
		try{
			driver.switchTo().defaultContent();
			strFieldNameValue = strFieldNameValue.trim();
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");
			strFieldNameValue= strFieldNameValue.replace(" ", "");
			strFieldNameValue =strFieldNameValue.toUpperCase();
			switch(strFieldNameValue){
			case "PROCEEDTOCHECKOUT":
				cart_SummaryPF.clickProceedtoCheckout(wm);
				break;
			case "CONTINUESHOPPING":
				cart_SummaryPF.clickContShopping(wm);
				break;
			default:
				throw new IllegalStateException("No such button exists");
			}
		}catch (TimeoutException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (Exception e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		}finally{
			if (bResult==true){
				logger.info ("The " + strFieldNameValue+  " was clicked succesfully ");
				wm.UpdateExcelReport("The " + strFieldNameValue+  " was clicked succesfully ", "PASS");
			}else{
				logger.error("The " + strFieldNameValue+  " could not be clicked succesfully ");
				wm.UpdateExcelReport("The " + strFieldNameValue+  " could not be clicked succesfully ", "FAIL");
			//wm.takeScreen();
			}
		}
	}


}


