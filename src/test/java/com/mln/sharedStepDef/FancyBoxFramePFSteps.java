package com.mln.sharedStepDef;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.FancyBoxFramePF;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.Common_Utility;

import io.cucumber.java.en.*;

public class FancyBoxFramePFSteps {

	private static Logger logger = LogManager.getLogger(MyStorePFSteps.class);
	WebDriver driver;
	DriverManager driverManager;
	FancyBoxFramePF fancyBoxFramePF;
	TestContext testContext;
	String strTestName = "TC-1";
	Common_Utility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);


	public FancyBoxFramePFSteps(TestContext context) {
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getCommon_Utility();
		fancyBoxFramePF = testContext.getPageFactoryManager().getfancyBoxFramePF();
		//wm.SwitchFramebyName(fancyBoxFramePF.getTxtName());
	}


	@And("^Verify if the (.*) Quickview is displayed$")
	public void verifypagequickview(String strFieldNameValue) {
		Boolean bResult= false;
		strFieldNameValue = strFieldNameValue.trim();
		strFieldNameValue= strFieldNameValue.replace("<", "");
		strFieldNameValue= strFieldNameValue.replace(">", "");

		driver.switchTo().defaultContent();

		try {
			List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
			for (WebElement we : frames ){

				if (we.getAttribute("name").contains("fancybox-frame")){
					driver.switchTo().frame(we.getAttribute("name"));
					bResult=true;
				}else{
					bResult=false;
				}
			}	

			//Waiting for the Quickview Display
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
			w.until(ExpectedConditions.visibilityOf(fancyBoxFramePF.getTxtName()));


			if ((fancyBoxFramePF.getTxtName().getText().equalsIgnoreCase(strFieldNameValue))){
				bResult=true;
			}
		} catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		}finally{
			if (bResult==true){
				logger.info ("The " + strFieldNameValue+ " QuickView was  displayed succesfully");
				wm.UpdateExcelReport("The " + strFieldNameValue+ " QuickView was  displayed succesfully", "PASS");
			}else{
				logger.error("The " + strFieldNameValue+ "QuickView was not displayed");
				wm.UpdateExcelReport("The " + strFieldNameValue+ "QuickView was not displayed", "FAIL");
			}
		}




	}

	@Then("^Verify if the (.*) are displayed in Quickview$")
	public void VerifyDetailsinquickview(String strFieldNameValue) throws IOException{
		boolean bResult =false;
		String strElement = "";
		try {
			driver.switchTo().defaultContent();
			List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
			for (WebElement we : frames ){

				if (we.getAttribute("name").contains("fancybox-frame")){
					driver.switchTo().frame(we.getAttribute("name"));
				}
			}
			strFieldNameValue = strFieldNameValue.trim();
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");
			String[] strFieldNameValues = strFieldNameValue.split(",");			
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
				bResult= fancyBoxFramePF.VerifyElementTxt(wm, strElement, strFieldNameValue);



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
				logger.info ("The value " + strFieldNameValue+ " is same as value in  " + strElement);
				wm.UpdateExcelReport("The value " + strFieldNameValue+ " is same as value in  " + strElement, "PASS");

			}else{
				logger.error ("The value " + strFieldNameValue+ " is not same as value in  " + strElement);
				wm.UpdateExcelReport("The value " + strFieldNameValue+ " is not same as value in  " + strElement, "FAIL");
			}
		}



	}






	@When("^Enter (.*) in Quickview$")
	public void EnterDetailsinquickview(String strFieldNameValue) {
		boolean bResult =false;
		String strElement ="";
		try {
			strFieldNameValue = strFieldNameValue.trim();
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");
			String[] strFieldNameValues = strFieldNameValue.split(",");
			driver.switchTo().defaultContent();
			List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
			for (WebElement we : frames ){
				//System.out.println(we.getAttribute("name"));
				if (we.getAttribute("name").contains("fancybox-frame")){
					driver.switchTo().frame(we.getAttribute("name"));
				}
			}

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

				fancyBoxFramePF.EnterTxt(wm, strElement, strFieldNameValue);
				bResult=true;
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
				logger.info ("The value " + strFieldNameValue+ " entered in  " + strElement);
				wm.UpdateExcelReport("The value " + strFieldNameValue+ " entered in  " + strElement, "PASS");

			}else{
				logger.error ("The value " + strFieldNameValue+ " were not entered in   " + strElement);
				wm.UpdateExcelReport("The value " + strFieldNameValue+ " were not entered in   " + strElement, "FAIL");
			}
		}
	}


	@When("^Clicks on (.*) in Quickview")
	public void clickonbuttoninquickview(String strFieldNameValue) {
		boolean bResult =false;
		try {
			strFieldNameValue = strFieldNameValue.trim();
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");
			strFieldNameValue= strFieldNameValue.replace(" ", "");
			strFieldNameValue =strFieldNameValue.toUpperCase();
			driver.switchTo().defaultContent();
			List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
			for (WebElement we : frames ){
				//System.out.println(we.getAttribute("name"));
				if (we.getAttribute("name").contains("fancybox-frame")){
					driver.switchTo().frame(we.getAttribute("name"));
				}
			}

			switch(strFieldNameValue){
			case "ADDTOCART":
				fancyBoxFramePF.clickAddtoCart(wm);
				bResult =true;
				break;
			default:
				bResult =false;
				//throw new IllegalStateException("No such button exists");

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
				logger.info ("The " + strFieldNameValue+ " was clicked succesfully");
				wm.UpdateExcelReport("The " + strFieldNameValue+ " was clicked succesfully","PASS");

			}else{
				logger.error ("The " + strFieldNameValue+ " could not be clicked");
				wm.UpdateExcelReport("The " + strFieldNameValue+ " could not be clicked","FAIL");
			}
		}

	}



}
