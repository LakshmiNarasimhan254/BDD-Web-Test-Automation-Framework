package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import reports.Excel_Reports;

public class Wrapper_Methods {

	private WebElement element = null; 
	private String strResult;
	//String sAbsPath = 	System.getProperty("user.dir")+"/";
	String sAbsPath= System.getProperty("user.dir") +"\\";
	private static String sFinalPath;
	private static String sTestName;
	private int i = 1;
	int  iRun =1;
	DateFormat dfDate = new SimpleDateFormat("dd-MM-yyyy");
	Date sDate = new Date();
	String dDate = dfDate.format(sDate);
	WebDriver adriver;
	Excel_Reports xlrpt;

	public Wrapper_Methods(WebDriver driver, String sTest){

		try {
			sFinalPath = sAbsPath + "screenshots\\" + sTest + "\\" + dDate + "\\";
			File file = new File(sFinalPath);
			File[] fFile = file.listFiles();
			if (!file.exists()) {
				file.mkdirs();
				iRun = 1;
			}
			else { 
				iRun = fFile.length + 1;
			}
			sFinalPath = sAbsPath + "screenshots/" + sTest + "/" + dDate + "/Run" + iRun + "/snap" ;

			setsTestName(sTest);
			adriver = driver;
			//xlrpt = new Excel_Reports(sTest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	

	
	public Wrapper_Methods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		adriver = driver;
	}




	




	public void LaunchApp(WebDriver driver, String url){
		// TODO Auto-generated method stub
		try {
			driver.get(url);
			strResult ="The URL: "+url+ " is loaded succesfully ";

			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			strResult ="The browser has not been launched";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		}
		catch(Exception e1){
			strResult ="An exception occured";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		}
		finally{
			takeScreen(driver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);
		}

	}
	public void setValue(WebElement element, String val) {
		try {
			element.clear();
			element.sendKeys(val);
			strResult = "Value " + val + " entered successfully" ;
			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
		} catch (NoSuchElementException e) {
			strResult ="The browser is not available." + e.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		}catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
		} catch(Exception e2){
			strResult ="An exception occured";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		}finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}

	}
	public void clickLnkBtn(WebElement element){
		try{
			element.click();
			strResult = "Button clicked successfully";
			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
		}catch (NoSuchElementException e) {
			strResult ="The browser is not available." + e.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		} catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
		} catch(Exception e2){
			strResult ="An exception occured" + e2.getCause();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		} finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}
	}
	public void verifyText(WebElement element, String sVal) throws IOException{
		try {
			String sValue = element.getText();
			System.out.println(sValue);
			if(sVal == sValue){
				strResult = "The Actual Text value " + sVal +" is displayed";
				xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
			}
			else
			{
				strResult = "The Actual Text value " + sVal +" is not displayed";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}	
		}catch (NoSuchElementException e) {
			strResult ="The element is not found or not visible.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		} catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		} catch(Exception e2){
			strResult ="An exception occured.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);				
		} finally {

			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}
	}
	
	public void verifyContainsText(WebElement element, String sVal) throws IOException{
		try {
			String sValue = element.getText();
			System.out.println(sValue);
			if(sValue.contentEquals(sVal)){
				strResult = "The Actual Text value " + sVal +" is displayed";
				xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
			}
			else
			{
				strResult = "The Actual Text value " + sVal +" is not displayed";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}	
		}catch (NoSuchElementException e) {
			strResult ="The element is not found or not visible.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		} catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		} catch(Exception e2){
			strResult ="An exception occured.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);				
		} finally {

			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}
	}

	public void takeScreen(WebDriver driver,String sPath1) {
		try {

			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File( sPath1 + i +".png"));
		} catch (IOException e) {
			strResult ="The Snapshot could not be captured";
		} catch (Exception e1){
			strResult ="An exception occured";
		} finally{
			i++;
		}
	}


	public void isElementPresent(WebElement element){
		try {

			if(element.isDisplayed()){
				strResult = "The " + element.getAccessibleName() +" is displayed";
				xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}
			else
			{
				strResult = "The " + element.getAccessibleName() +" is not displayed";
				xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}	
		}catch (NoSuchElementException e) {
			strResult ="The browser is not available." + e.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		} catch (WebDriverException e1){
			strResult ="The browser is not available." + e1.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		} catch(Exception e2){
			strResult ="An exception occured.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);				
		} finally {

			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);

		}
	}


	public void SelectValuebyText(WebElement element, String val) {
		try {
			Select sel =new Select(element);
			//sel.deselectAll();
			sel.selectByVisibleText(val);
			strResult = "Value my name  " + val + " selected successfully" ;
			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
		}catch (NoSuchElementException e) {
			strResult ="The browser is not available." + e.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		}catch (WebDriverException e1){
			strResult ="The browser is not available." + e1.getRawMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
		} catch(Exception e2){
			strResult ="An exception occured";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		}finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);
		}
	}

	public void SelectValuebyValue(WebElement element, String val) {
		try {
			Select sel =new Select(element);
			//sel.deselectAll();
			sel.selectByValue(val);
			strResult = "Value my name " + val + " selected successfully" ;
			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
		}catch (NoSuchElementException e) {
			strResult ="The browser is not available." + e.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		}catch (WebDriverException e1){
			strResult ="The browser is not available." + e1.getRawMessage();
			//e1.printStackTrace();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
		} catch(Exception e2){
			strResult ="An exception occured";
			e2.printStackTrace();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		}finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);
		}
	}
	
	public void SelectValuebyIndex(WebElement element, int val) {
		try {
			Select sel =new Select(element);
			//sel.deselectAll();
			sel.selectByIndex(val);
			String Value = sel.getFirstSelectedOption().getText();
			strResult = "The " + Value + " in the Index " + val +" is selected successfully" ;
			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
		}catch (NoSuchElementException e) {
			strResult ="The browser is not available." + e.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		}catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
		} catch(Exception e2){
			strResult ="An exception occured";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		}finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);
		}
	}
	
	
	public void SelectRadioButton(WebElement element) {
		try {
				if (element.isSelected()){
					
				}else{
					element.click();
				}						
			strResult = "The Radio button is selected successfully" ;
			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
		}catch (NoSuchElementException e) {
			strResult ="The browser is not available." + e.getMessage();
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
		}catch (WebDriverException e1){
			strResult ="The browser is not available.";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
		} catch(Exception e2){
			strResult ="An exception occured";
			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
		}finally {
			takeScreen(adriver,sFinalPath);
			//xlrpt.reportSnapshot(sFinalPath);
		}
	}
		public void DeselectRadioButton(WebElement element) {
			try {
					if (!element.isSelected()){
						
					}else{
						element.click();
					}						
				strResult = "The Radio button is deselected successfully" ;
				xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
			}catch (NoSuchElementException e) {
				strResult ="The browser is not available." + e.getMessage();
				xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}catch (WebDriverException e1){
				strResult ="The browser is not available." + e1.getMessage();
				xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
			} catch(Exception e2){
				strResult ="An exception occured";
				xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
			}finally {
				takeScreen(adriver,sFinalPath);
				//xlrpt.reportSnapshot(sFinalPath);
			}
		}




		public static String getsTestName() {
			return sTestName;
		}




		public void setsTestName(String sTestName) {
			Wrapper_Methods.sTestName = sTestName;
			TestReportStructure(Wrapper_Methods.sTestName);
		}

		public void TestReportStructure(String sTest){

			try {
				//sFinalPath = sAbsPath + "screenshots\\" + sTest + "\\" + dDate + "\\";
				sFinalPath = sAbsPath + "screenshots\\" + dDate  + "\\" + sTest+ "\\" ;
				File file = new File(sFinalPath);
				File[] fFile = file.listFiles();
				if (!file.exists()) {
					file.mkdir();
					iRun = 1;
				}
				else { 
					iRun = fFile.length + 1;
				}
				sFinalPath = sAbsPath + "screenshots\\" + dDate  + "\\" + sTest+  "\\Run" + iRun + "\\snap" ;
				System.out.println(sFinalPath);
				xlrpt = new Excel_Reports(sTest);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}	

		public void webelementWait(WebElement We){
			WebDriverWait wait = new WebDriverWait(adriver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(We));
		}
		
		public void SwitchFramebyName(String strFrame){
			adriver.switchTo().frame(strFrame);
		}
		
		public void SwitchFramebyName(WebElement We){
			adriver.switchTo().frame(We);
		}

		
}












