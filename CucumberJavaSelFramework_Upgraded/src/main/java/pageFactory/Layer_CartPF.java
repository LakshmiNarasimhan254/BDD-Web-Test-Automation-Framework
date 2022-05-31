package pageFactory;


import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import utilities.Wrapper_Methods;

public class Layer_CartPF {

	WebDriver driver;
	String strTestName;

	@FindBy(xpath="//h2/I/parent::h2")
	WebElement txtSuccessCart;

	@FindBy(xpath="//span[@id='layer_cart_product_title']")
	WebElement txtName;
	
	@FindBy(xpath="//span[@id='layer_cart_product_attributes']")
	WebElement txtColorSize;
	
	@FindBy(xpath="//strong[contains(text(),'Quantity')]")
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


	public WebElement getBtnProceedtoCheckout() {
		return btnProceedtoCheckout;
	}


	


	public void VerifyElementTxt(Wrapper_Methods wmobj,String strElement, String Value) throws IOException

	{
		strElement =(strElement.replace(" ","").trim()).toUpperCase();

		switch(strElement){
		case "NAME":
			wmobj.verifyText(txtName, Value);
			break;	
		case "COLOR":
			wmobj.verifyContainsText(txtColorSize, Value);
			break;
		case "SIZE":
			wmobj.verifyContainsText(txtColorSize, Value);
			break;
		case "QUANTITY":
			wmobj.verifyText(txtQuantity,Value);
			break;
		case "PRICE":
			wmobj.verifyText(txtPrice,Value);
			break;
		default:
			throw new IllegalStateException("No such field exists. Please check" );


		}
		
		

	}
	
	public void VerifySuccess(Wrapper_Methods wmobj, String Value) throws IOException{
	
		wmobj.verifyText(txtSuccessCart, Value);
	}

	public void clickProceedtoCheckout(Wrapper_Methods wmobj)

	{
		wmobj.clickLnkBtn(btnProceedtoCheckout);

	}

	public void clickContShopping(Wrapper_Methods wmobj)

	{
		wmobj.clickLnkBtn(btnContShopping);

	}





}