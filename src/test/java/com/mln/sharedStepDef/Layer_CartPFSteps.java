package com.mln.sharedStepDef;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.Layer_CartPF;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.Common_Utility;

import io.cucumber.java.en.*;

public class Layer_CartPFSteps {

	private static Logger logger = LogManager.getLogger(Layer_CartPFSteps.class);
	WebDriver driver;
	DriverManager driverManager;
	Layer_CartPF layer_CartPF;
	TestContext testContext;
	String strTestName = "TC-1";
	Common_Utility wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);


	public Layer_CartPFSteps(TestContext context) {
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getCommon_Utility();
		layer_CartPF = testContext.getPageFactoryManager().getlayer_CartPF();
		//wm.SwitchFramebyName(fancyBoxFramePF.getTxtName());
	}


	@And("Verify if the Layared_Cart Page is displayed")
	public void enters_the_txt() {
		boolean bResult = false;
		//	layer_CartPF = testContext.getPageObjectManager().getlayer_CartPF();
		try {
			driver.switchTo().defaultContent();
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
			w.until(ExpectedConditions.visibilityOf(layer_CartPF.getBtnProceedtoCheckout()));

			if (layer_CartPF.getBtnProceedtoCheckout()!=null){
				bResult=true;
				//throw new IllegalStateException("The Layer cart checkout page is not displayed");
			}
		}  catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The Current Page is as expected");
				wm.UpdateExcelReport("The current page is Layared_Cart Page as expected.","PASS");
			}else{
				logger.error("The Current Page is not as expected");
				wm.UpdateExcelReport("The current page is not Layared_Cart Page.","FAIL");
			}
		}
	}

	@Then("^Verify the details (.*)  in Layared_Cart Page$")
	public void VerifyDetailsinlayaredcart(String strFieldNameValue) throws IOException{
		boolean bResult = false;
		String strElement ="";
		try {
			//layer_CartPF = testContext.getPageFactoryManager().getlayer_CartPF();
			strFieldNameValue = strFieldNameValue.trim();{
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
					//System.out.println(strElement +"  "+strFieldNameValue );
					bResult=layer_CartPF.VerifyElementTxt(wm, strElement, strFieldNameValue);
				}
			}
		}catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The " +  strElement + " has expected value " + strFieldNameValue);
				wm.UpdateExcelReport("The " +  strElement + " has expected value " + strFieldNameValue,"PASS");
			}else{
				logger.error("The " +  strElement + " does not have expected value " + strFieldNameValue);
				wm.UpdateExcelReport("The " +  strElement + " does not have expected value " + strFieldNameValue,"FAIL");
			}
		}
	}

	@When("^Clicks on (.*) in Layared_Cart Page")
	public void clickonButtoninLayaredcart(String strFieldNameValue) {
		//layer_CartPF = testContext.getPageFactoryManager().getlayer_CartPF();
		boolean bResult = false;
		try {
			strFieldNameValue = strFieldNameValue.trim();
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");
			strFieldNameValue= strFieldNameValue.replace(" ", "");
			strFieldNameValue =strFieldNameValue.toUpperCase();
			switch(strFieldNameValue){
			case "PROCEEDTOCHECKOUT":
				layer_CartPF.clickProceedtoCheckout(wm);
				bResult=true;
				break;
			case "CONTINUESHOPPING":
				layer_CartPF.clickContShopping(wm);
				bResult=true;
				break;
			default:
				throw new IllegalStateException("No such button exists");
			}
		} catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The " + strFieldNameValue + " was clicked as expected" );
				wm.UpdateExcelReport("The " + strFieldNameValue + " was clicked as expected" ,"PASS");
			}else{
				logger.error("The " + strFieldNameValue + " could not be  not clicked" );
				wm.UpdateExcelReport("The " + strFieldNameValue + " could not be  not clicked" ,"FAIL");
			}
		}
	}

	@Then("Verify if the successmessage is displayed in Layared_cart Page")
	public void Verifymessageinlayaredcart() throws IOException{
		//layer_CartPF = testContext.getPageFactoryManager().getlayer_CartPF();

		String Value = "Product successfully added to your shopping cart";
		boolean bResult = false;
		try {
			bResult = layer_CartPF.VerifySuccess(wm, Value);
		} catch (NoSuchFrameException e1) {
			bResult=false;
			logger.info(e1.getStackTrace());

		}catch (TimeoutException e2) {
			bResult=false;
			logger.info(e2.getStackTrace());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (bResult==true){
				logger.info ("The " + Value + " was as expected"  );
				wm.UpdateExcelReport("The " + Value + " was as expected" ,"PASS");
			}else
				logger.error("The " + Value + " was not as expected" );
			wm.UpdateExcelReport("The " + Value + " was not as expected" ,"FAIL");
			wm.takeScreen();
		}



	}

}
