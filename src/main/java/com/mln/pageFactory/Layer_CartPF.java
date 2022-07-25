package com.mln.pageFactory;


import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mln.utilities.Common_Utility;

public class Layer_CartPF {

	WebDriver driver;
	String strTestName;

	@FindBy(xpath="//h2/I/parent::h2")
	WebElement txtSuccessCart;

	@FindBy(xpath="//span[@id='layer_cart_product_title']")
	WebElement txtName;
	
	@FindBy(xpath="//span[@id='layer_cart_product_attributes']")
	WebElement txtColorSize;
	
	@FindBy(xpath="//span[@id='layer_cart_product_quantity']")
	WebElement txtQuantity;	
	
	@FindBy(xpath="//span[@id='layer_cart_product_price']")
	WebElement txtPrice;

	@FindBy(xpath="//label[contains(text(),'Condition')]/following-sibling::span")
	WebElement txtCondition;

	

	@FindBy(xpath ="//span[@class='ajax_cart_product_txt ']")
	private WebElement txtCartItemmsg;


	@FindBy(xpath="//span[@title='Continue shopping']")
	private WebElement btnContShopping;

	
	@FindBy(xpath="//a[@title='Proceed to checkout']")
	private WebElement btnProceedtoCheckout;

	public Layer_CartPF(WebDriver driver){
		this.driver =driver;
		//WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
	   // w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("fancybox-frame1653936324222"));
	  		driver.switchTo().defaultContent();
		PageFactory.initElements(driver, this);
		
	}


	public WebElement getBtnProceedtoCheckout() throws Exception {
		return btnProceedtoCheckout;
	}


	


	public boolean VerifyElementTxt(Common_Utility wmobj,String strElement, String Value) throws Exception{
		boolean bResult = false;
	
		try {
			strElement =(strElement.replace(" ","").trim()).toUpperCase();

			switch(strElement){
			case "NAME":
				bResult=wmobj.verifyText(txtName, Value);
				break;	
			case "COLOR":
				bResult=wmobj.verifyContainsText(txtColorSize, Value);
				break;
			case "SIZE":
				bResult=wmobj.verifyContainsText(txtColorSize, Value);
				break;
			case "QUANTITY":
				bResult=wmobj.verifyText(txtQuantity,Value);
				break;
			case "PRICE":
				bResult=wmobj.verifyText(txtPrice,Value);
				break;
			default:
				bResult =false;
				//throw new IllegalStateException("No such field exists. Please check" );
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return bResult;
		
	}

	
	
	public boolean VerifySuccess(Common_Utility wmobj, String Value) throws Exception{
	  boolean bResult = false;
	 return bResult=wmobj.verifyText(txtSuccessCart, Value);
	}

	public void clickProceedtoCheckout(Common_Utility wmobj) throws Exception

	{
		wmobj.clickLnkBtn(btnProceedtoCheckout);

	}

	public void clickContShopping(Common_Utility wmobj) throws Exception

	{
		wmobj.clickLnkBtn(btnContShopping);

	}





}