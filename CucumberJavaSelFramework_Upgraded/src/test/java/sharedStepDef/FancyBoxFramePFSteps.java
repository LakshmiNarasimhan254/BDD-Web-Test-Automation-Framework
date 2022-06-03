package sharedStepDef;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.TestContext;
import io.cucumber.java.en.*;
import managers.DriverManager;
import managers.FileReaderManager;
import pageFactory.FancyBoxFramePF;
import utilities.Excel_Utility;
import utilities.HashMap_Utility;
import utilities.Wrapper_Methods;

public class FancyBoxFramePFSteps {


	WebDriver driver;
	DriverManager driverManager;
	FancyBoxFramePF fancyBoxFramePF;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);


	public FancyBoxFramePFSteps(TestContext context) {
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getWrapperMethods();
		fancyBoxFramePF = testContext.getPageObjectManager().getfancyBoxFramePF();
		//wm.SwitchFramebyName(fancyBoxFramePF.getTxtName());
	}


	@And("^Verify if the (.*) Quickview is displayed$")
	public void verifypagequickview(String strFieldNameValue) {
		
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
	  	for (WebElement we : frames ){
	  		//System.out.println(we.getAttribute("name"));
	  		if (we.getAttribute("name").contains("fancybox-frame")){
	  			driver.switchTo().frame(we.getAttribute("name"));
	  		}
	  	}
		
		strFieldNameValue = strFieldNameValue.trim();
		strFieldNameValue= strFieldNameValue.replace("<", "");
		strFieldNameValue= strFieldNameValue.replace(">", "");
		//System.out.println(fancyBoxFramePF.getTxtName().getText());
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
		w.until(ExpectedConditions.visibilityOf(fancyBoxFramePF.getTxtName()));
		//System.out.println(fancyBoxFramePF.getTxtName().getText());
		//System.out.println(strFieldNameValue);


		if (!(fancyBoxFramePF.getTxtName().getText().equalsIgnoreCase(strFieldNameValue))){
			throw new IllegalStateException("Quick view window is not popped up");
		}

		//System.out.println("pass");
		

	}

	@Then("^Verify if the (.*) are displayed in Quickview$")
	public void VerifyDetailsinquickview(String strFieldNameValue) throws IOException{
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
	  	for (WebElement we : frames ){
	  		//System.out.println(we.getAttribute("name"));
	  		if (we.getAttribute("name").contains("fancybox-frame")){
	  			driver.switchTo().frame(we.getAttribute("name"));
	  		}
	  	}
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
				
			}
		}
	
	}



	@When("^Enter (.*) in Quickview$")
	public void EnterDetailsinquickview(String strFieldNameValue) {
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
	  	for (WebElement we : frames ){
	  		//System.out.println(we.getAttribute("name"));
	  		if (we.getAttribute("name").contains("fancybox-frame")){
	  			driver.switchTo().frame(we.getAttribute("name"));
	  		}
	  	}
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
			fancyBoxFramePF.EnterTxt(wm, strElement, strFieldNameValue);
		}
		
	}


@When("^Clicks on (.*) in Quickview")
public void clickonbuttoninquickview(String strFieldNameValue) {
	driver.switchTo().defaultContent();
	List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
  	for (WebElement we : frames ){
  		//System.out.println(we.getAttribute("name"));
  		if (we.getAttribute("name").contains("fancybox-frame")){
  			driver.switchTo().frame(we.getAttribute("name"));
  		}
  	}
	strFieldNameValue = strFieldNameValue.trim();
	strFieldNameValue= strFieldNameValue.replace("<", "");
	strFieldNameValue= strFieldNameValue.replace(">", "");
	strFieldNameValue= strFieldNameValue.replace(" ", "");
	strFieldNameValue =strFieldNameValue.toUpperCase();
	switch(strFieldNameValue){
	case "ADDTOCART":
		fancyBoxFramePF.clickAddtoCart(wm);
		break;
	default:
		throw new IllegalStateException("No such button exists");
		
	}
	
}



}
