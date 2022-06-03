package sharedStepDef;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.TestContext;
import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.FileReaderManager;
import pageFactory.Layer_CartPF;
import utilities.Excel_Utility;
import utilities.HashMap_Utility;
import utilities.Wrapper_Methods;

public class Layer_CartPFSteps {


	WebDriver driver;
	DriverManager driverManager;
	Layer_CartPF layer_CartPF;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);


	public Layer_CartPFSteps(TestContext context) {
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getWrapperMethods();
		layer_CartPF = testContext.getPageObjectManager().getlayer_CartPF();
		//wm.SwitchFramebyName(fancyBoxFramePF.getTxtName());
	}


	@And("Verify if the Layared_Cart Page is displayed")
	public void enters_the_txt() {
	//	layer_CartPF = testContext.getPageObjectManager().getlayer_CartPF();
		driver.switchTo().defaultContent();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
		w.until(ExpectedConditions.visibilityOf(layer_CartPF.getBtnProceedtoCheckout()));

		if (layer_CartPF.getBtnProceedtoCheckout()==null){
			throw new IllegalStateException("The Layer cart checkout page is not displayed");
		}		

	}

	@Then("^Verify the details (.*)  in Layared_Cart Page$")
	public void VerifyDetailsinlayaredcart(String strFieldNameValue) throws IOException{
		layer_CartPF = testContext.getPageObjectManager().getlayer_CartPF();
		strFieldNameValue = strFieldNameValue.trim();{
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
				layer_CartPF.VerifyElementTxt(wm, strElement, strFieldNameValue);
			}
		}
	}

	@When("^Clicks on (.*) in Layared_Cart Page")
	public void clickonButtoninLayaredcart(String strFieldNameValue) {
		layer_CartPF = testContext.getPageObjectManager().getlayer_CartPF();
		strFieldNameValue = strFieldNameValue.trim();
		strFieldNameValue= strFieldNameValue.replace("<", "");
		strFieldNameValue= strFieldNameValue.replace(">", "");
		strFieldNameValue= strFieldNameValue.replace(" ", "");
		strFieldNameValue =strFieldNameValue.toUpperCase();
		switch(strFieldNameValue){
		case "PROCEEDTOCHECKOUT":
			layer_CartPF.clickProceedtoCheckout(wm);
			break;
		case "CONTINUESHOPPING":
			layer_CartPF.clickContShopping(wm);
			break;
		default:
			throw new IllegalStateException("No such button exists");
		}
	}

	@Then("Verify if the successmessage is displayed in Layared_cart Page")
	public void Verifymessageinlayaredcart() throws IOException{
		layer_CartPF = testContext.getPageObjectManager().getlayer_CartPF();
		String Value = "Product successfully added to your shopping cart";
		layer_CartPF.VerifySuccess(wm, Value);
	}





}
