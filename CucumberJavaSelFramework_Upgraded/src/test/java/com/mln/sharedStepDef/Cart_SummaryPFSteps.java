package com.mln.sharedStepDef;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mln.cucumber.TestContext;
import com.mln.managers.DriverManager;
import com.mln.managers.FileReaderManager;
import com.mln.pageFactory.Cart_SummaryPF;
import com.mln.utilities.Excel_Utility;
import com.mln.utilities.HashMap_Utility;
import com.mln.utilities.Wrapper_Methods;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cart_SummaryPFSteps {


	WebDriver driver;
	DriverManager driverManager;
	Cart_SummaryPF cart_SummaryPF;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);


	public Cart_SummaryPFSteps(TestContext context) {
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getWrapperMethods();
		cart_SummaryPF = testContext.getPageObjectManager().getcart_SummaryPF();

		//wm.SwitchFramebyName(fancyBoxFramePF.getTxtName());
	}

	@And("Verify if the user is in Cart_Summary Page")
	public void enters_the_txt() {
		//System.out.println(driver.getCurrentUrl());
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
		w.until(ExpectedConditions.visibilityOf(cart_SummaryPF.getTxtCartSummary()));

		if (cart_SummaryPF.getTxtCartSummary()==null){
			throw new IllegalStateException("The  cart summary page is not displayed");
		}


	}
	@Then("^Verify if the (.*) are displayed in Cart_Summary Page$")
	public void VerifyDetailsinCartSummary(String strFieldNameValue) throws IOException{
		driver.switchTo().defaultContent();
		strFieldNameValue = strFieldNameValue.trim();{
			strFieldNameValue= strFieldNameValue.replace("<", "");
			strFieldNameValue= strFieldNameValue.replace(">", "");

			//System.out.println(strFieldNameValues.length);
			//			HashMap<String, String> hm = HashMap_Utility.strArr_HM_usingSeparator(strFieldNameValues,":=");
			//			Iterator<Entry<String, String>> iterKeyVal =hm.entrySet().iterator();
			//			String strElement;

		
				if(strFieldNameValue.contains(("ExcelData"))){				
					strFieldNameValue=strFieldNameValue.replace("ExcelData|", "");
					//System.out.println(strFieldNameValue);
					strFieldNameValue=em.getCellValueStringSearchtxt(strTestName, strFieldNameValue);
				}
				cart_SummaryPF.verifyCartDetails(wm, strFieldNameValue);
			}


		}
	
	@When("^Clicks on (.*) in Cart_Summary Page")
	public void clickonButtoninCartSummary(String strFieldNameValue) {
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
	}
	

}


