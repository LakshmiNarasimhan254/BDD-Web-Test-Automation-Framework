package com.mln.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mln.managers.FileReaderManager;
import com.mln.reports.Excel_Reports;



public class Common_Utility {

	private WebElement element = null; 
	private String strResult;
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

	public Common_Utility(WebDriver driver) {
		// TODO Auto-generated constructor stub
		adriver = driver;
	}
	public void launchApp(String url){
		// TODO Auto-generated method stub
		try {
			adriver.get(url);
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
			//takeScreen();
			//xlrpt.reportSnapshot(sFinalPath);
		}

	}
	public void setValue(WebElement element, String val) throws Exception  {
		//try {
			element.clear();
			element.sendKeys(val);
			//strResult = "Value " + val + " entered successfully" ;
			//xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
//		} catch (NoSuchElementException e) {
//			strResult ="The browser is not available." + e.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		}catch (WebDriverException e1){
//			strResult ="The browser is not available.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
//		} catch(Exception e2){
//			strResult ="An exception occured";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		}finally {
			//takeScreen();
			//xlrpt.reportSnapshot(sFinalPath);

//		}

	}
	public void clickLnkBtn(WebElement element) throws Exception{
		//try{
			element.click();
		//	strResult = "Button clicked successfully";
		//	xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
//		}catch (NoSuchElementException e) {
//			strResult ="The browser is not available." + e.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		} catch (WebDriverException e1){
//			strResult ="The browser is not available.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
//		} catch(Exception e2){
//			strResult ="An exception occured" + e2.getCause();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		} finally {
//			//takeScreen(/);
//			//xlrpt.reportSnapshot(sFinalPath);
//
//		}
	}
	public boolean verifyText(WebElement element, String sVal) throws Exception{
		boolean bResult = false;
		//try {
			String sValue = element.getText();
			//System.out.println(sValue);
			if(sVal.equals(sValue)){
				bResult = true;
				//strResult = "The Actual Text value " + sVal +" is displayed";
				//xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
			}
			else
			{	
				bResult = false;
				//strResult = "The Actual Text value " + sVal +" is not displayed but the value "+ sValue + " is displayed";
				//xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}	
//		}catch (NoSuchElementException e) {
//			bResult = false;
//			strResult ="The element is not found or not visible.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		} catch (WebDriverException e1){
//			bResult = false;
//			strResult ="The browser is not available.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		} catch(Exception e2){
//			bResult = false;
//			strResult ="An exception occured.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);				
//		} finally {
//
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//
//		}
		return bResult;
	}


	public boolean verifyText(String ActualVal, String ExpectedVal) throws Exception{
		boolean bResult = false;
		//try {
			//String sValue = element.getText();

			if(ActualVal.equals(ExpectedVal)){
				bResult = true;
				//strResult = "The Actual Text value " + ActualVal +" is displayed";
				//xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
			}
			else
			{	
				bResult = false;
				//strResult = "The Actual Text value " + ActualVal +" is not displayed";
				//xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}	
//		}catch (NoSuchElementException e) {
//			bResult = false;
//			strResult ="The element is not found or not visible.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		} catch (WebDriverException e1){
//			bResult = false;
//			strResult ="The browser is not available.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		} catch(Exception e2){
//			bResult = false;
//			strResult ="An exception occured.";
//			e2.printStackTrace();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);				
//		} finally {
//
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//
//		}
		return bResult;
		}

	public boolean verifyContainsText(WebElement element, String sVal) throws Exception{
		boolean bResult = false;
		//try {
			String sValue = element.getText().trim();
			//System.out.println(sValue);
			if(sValue.contains(sVal.trim())){
				bResult = true;

				//strResult = "The Actual Text value " + sVal +" is displayed";
				//xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
			}
			else
			{
				bResult = false;
				//strResult = "The Actual Text value " + sVal +" is not displayed but the value "+ sValue + " is displayed";
				//xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}	
//		}catch (NoSuchElementException e) {
//			bResult = false;
//			strResult ="The element is not found or not visible.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		} catch (WebDriverException e1){
//			bResult = false;
//			strResult ="The browser is not available.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		} catch(Exception e2){
//			bResult = false;
//			strResult ="An exception occured.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);				
//		} finally {
//
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//
//		}
		return bResult;
	}

	public void takeScreen() {
		try {

			File src = ((TakesScreenshot)adriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File( sFinalPath + i +".png"));
		} catch (IOException e) {
			strResult ="The Snapshot could not be captured";
		} catch (Exception e1){
			strResult ="An exception occured";
		} finally{
			i++;
		}
	}


	public boolean isElementPresent(WebElement element)throws Exception{
		boolean bResult =false;
		//try {

			if(element.isDisplayed()){
				bResult =true;
				//strResult = "The " + element.getAccessibleName() +" is displayed";
				//xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}
			else
			{
				bResult =false;
				//strResult = "The " + element.getAccessibleName() +" is not displayed";
				//xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
			}	
//		}catch (NoSuchElementException e) {
//			strResult ="The browser is not available." + e.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		} catch (WebDriverException e1){
//			strResult ="The browser is not available." + e1.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		} catch(Exception e2){
//			strResult ="An exception occured.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);				
//		} finally {
//
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//
//		}
		return bResult;
	}


	public void selectValuebyText(WebElement element, String val)throws Exception {
		//try {
			Select sel =new Select(element);
			//sel.deselectAll();
			sel.selectByVisibleText(val);
//			//strResult = "Value my name  " + val + " selected successfully" ;
//			//xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
//		}catch (NoSuchElementException e) {
//			//strResult ="The browser is not available." + e.getMessage();
//			//xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		}catch (WebDriverException e1){
//		//strResult ="The browser is not available." + e1.getRawMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
//		} catch(Exception e2){
//			strResult ="An exception occured";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		}finally {
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//		}
	}

	public void selectValuebyValue(WebElement element, String val) throws Exception {
	//	try {
			Select sel =new Select(element);
			//sel.deselectAll();
			sel.selectByValue(val);
//			strResult = "Value my name " + val + " selected successfully" ;
//			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
//		}catch (NoSuchElementException e) {
//			strResult ="The browser is not available." + e.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		}catch (WebDriverException e1){
//			strResult ="The browser is not available." + e1.getRawMessage();
//			//e1.printStackTrace();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
//		} catch(Exception e2){
//			strResult ="An exception occured";
//			e2.printStackTrace();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		}finally {
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//		}
	}

	public void selectValuebyIndex(WebElement element, int val) throws Exception{
		//try {
			Select sel =new Select(element);
			//sel.deselectAll();
			sel.selectByIndex(val);
			String Value = sel.getFirstSelectedOption().getText();
//			strResult = "The " + Value + " in the Index " + val +" is selected successfully" ;
//			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
//		}catch (NoSuchElementException e) {
//			strResult ="The browser is not available." + e.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		}catch (WebDriverException e1){
//			strResult ="The browser is not available.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
//		} catch(Exception e2){
//			strResult ="An exception occured";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		}finally {
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//		}
	}


	public void selectRadioButton(WebElement element)throws Exception{
		//try {
			if (element.isSelected()){

			}else{
				element.click();
			}						
//			strResult = "The Radio button is selected successfully" ;
//			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
//		}catch (NoSuchElementException e) {
//			strResult ="The browser is not available." + e.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		}catch (WebDriverException e1){
//			strResult ="The browser is not available.";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
//		} catch(Exception e2){
//			strResult ="An exception occured";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		}finally {
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//		}
	}
	public void deselectRadioButton(WebElement element)throws Exception {
		//try {
			if (!element.isSelected()){

			}else{
				element.click();
			}						
//			strResult = "The Radio button is deselected successfully" ;
//			xlrpt.reportEvent(strResult, "PASS",sFinalPath,iRun);
//		}catch (NoSuchElementException e) {
//			strResult ="The browser is not available." + e.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);
//		}catch (WebDriverException e1){
//			strResult ="The browser is not available." + e1.getMessage();
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);		
//		} catch(Exception e2){
//			strResult ="An exception occured";
//			xlrpt.reportEvent(strResult, "FAIL",sFinalPath,iRun);			
//		}finally {
//			//takeScreen();
//			//xlrpt.reportSnapshot(sFinalPath);
//		}
	}




	public static String getsTestName() {
		return sTestName;
	}




	public void setsTestName(String sTestName) {
		Common_Utility.sTestName = sTestName;
		testReportStructure(Common_Utility.sTestName);
	}

	public void testReportStructure(String sTest){

		try {
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

	public void switchFramebyName(String strFrame){
		adriver.switchTo().frame(strFrame);
	}

	public void switchFramebyName(WebElement We){
		adriver.switchTo().frame(We);
	}
	
	public  String getCurrentUrl(){
		adriver.getCurrentUrl();
		return adriver.getCurrentUrl();
	}
	
	public  String getTitle(){
		adriver.getTitle();
		return adriver.getTitle();
	}

	public  HashMap<String, String> strArr_HM_usingSeparator(String[] Hmdetails ,String strSeparator){
		int strArrayLen = Hmdetails.length;
		//System.out.println(strArrayLen);
		HashMap<String, String > hm = new HashMap<String,String>();
		for(int i=0;i<strArrayLen;i++){
			String strKey = Hmdetails[i].split(strSeparator)[0];
			String strValue = Hmdetails[i].split(strSeparator)[1];

			hm.put(strKey, strValue);
		}		
		return hm;
	}
	
	public void UpdateExcelReport(String strResult,String strStatus ){
		xlrpt.reportEvent(strResult, strStatus,sFinalPath,iRun);
		String ssSetting = FileReaderManager.getInstance().getConfigReader().getScreeShotSettings();
		if (ssSetting.equalsIgnoreCase("ALL")){
			takeScreen();
		}
		if (ssSetting.equalsIgnoreCase("PASSED") && strStatus.equalsIgnoreCase("PASS") ){
			takeScreen();
		}
		if (ssSetting.equalsIgnoreCase("FAILED") && strStatus.equalsIgnoreCase("FAIL") ){
			takeScreen();
		}
	}

}












