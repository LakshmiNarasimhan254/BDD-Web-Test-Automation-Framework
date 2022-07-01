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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mln.utilities.Common_Utility;

public class FancyBoxFramePF {

	WebDriver driver;
	String strTestName;

	@FindBy(xpath="//h1[@itemprop ='name']")
	private WebElement txtName;

	@FindBy(xpath="//label[contains(text(),'Model')]/following-sibling::span")
	private WebElement txtModelName;

	@FindBy(xpath="//label[contains(text(),'Condition')]/following-sibling::span")
	private WebElement txtCondition;

	@FindBy(xpath="//div[@id='short_description_content']/p")
	private WebElement txtShrtDescription;

	@FindBy(xpath="//span[@id='our_price_display']")
	private WebElement txtPrice;

	@FindBy(xpath="//input[@id='quantity_wanted']")
	private WebElement txtbxQuantity;		

	@FindBy(xpath ="//select[@id='group_1']")
	private WebElement drpdnSize;

	@FindBy(xpath="//label[contains(text(),'Color')]/following-sibling::div/ul/li")
	private List <WebElement> colorOptions;

	@FindBy(xpath="//span[contains(text(),'Add to cart')]")
	private WebElement btnAddtoCart;


	public FancyBoxFramePF(WebDriver driver){
		this.driver =driver;
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
		w.until(ExpectedConditions.elementToBeClickable(By.xpath("//iframe[contains(@id,'fancybox-frame')]")));
		//System.out.println("hi");
		List<WebElement> frames = driver.findElements(By.xpath("//iframe"));
		for (WebElement we : frames ){
			//System.out.println(we.getAttribute("name"));
			if (we.getAttribute("name").contains("fancybox-frame")){
				driver.switchTo().frame(we.getAttribute("name"));
			}
		}

		PageFactory.initElements(driver, this);

	}


	public void clickAddtoCart(Common_Utility wmobj) throws Exception

	{
		wmobj.clickLnkBtn(btnAddtoCart);

	}

	public void enterQuantity(Common_Utility wmobj,String strQuanity) throws Exception

	{
		wmobj.setValue(txtbxQuantity, strQuanity);
	}

	public void selectSize(Common_Utility wmobj,String strSize) throws Exception

	{
		wmobj.selectValuebyText(drpdnSize, strSize);
	}

	public void selectSize(Common_Utility wmobj,int iColor) throws Exception

	{
		wmobj.clickLnkBtn(colorOptions.get(iColor));
	}
	public void selectColor(Common_Utility wmobj,String iColor) throws Exception 

	{
		Iterator<WebElement> iter = colorOptions.iterator();
		while (iter.hasNext()){
			if ((iter.next().getAttribute("value")).equalsIgnoreCase(iColor)){
				wmobj.clickLnkBtn(iter.next());
			}
		}

	}

	public boolean VerifyElementTxt(Common_Utility wmobj,String strElement, String Value) throws Exception{
	boolean bResult = false;
	{
		strElement =(strElement.replace(" ","").trim()).toUpperCase();

		try {
			switch(strElement){
			case "NAME":
				bResult=wmobj.verifyText(txtName, Value);
				break;

			case "MODELNAME":
				bResult=wmobj.verifyText(txtModelName, Value);
				break;
			case "CONDITION":
				bResult=wmobj.verifyText(txtModelName, Value);
				break;

			case "SHORTDESCRIPTION":
				bResult=wmobj.verifyText(txtShrtDescription,Value);
				break;

			case "PRICE":
				bResult=wmobj.verifyText(txtPrice,Value);
				break;
			default:
				//throw new IllegalStateException("No such field exists. Please check" );

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			bResult = false;
		}
	}
	return bResult;

}


public void EnterTxt(Common_Utility wmobj, String strElement, String Value) throws Exception{
	strElement =(strElement.replace(" ","").trim()).toUpperCase();

	switch(strElement){
	case "QUANTITY" :
		enterQuantity(wmobj, Value);
		break;
	case "SIZE" :
		selectSize(wmobj, Value);
		break;
	case "COLOR" :
	case "COLOUR" :
		selectColor(wmobj, Value);
		break;	

	default:
		throw new IllegalStateException("This is not a correct value: " + strElement);
	}
}

public WebElement getTxtName() {
	return txtName;
}






}