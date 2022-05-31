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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Wrapper_Methods;

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
//  	 WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
//     w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[contains(@id,'fancybox-frame')]")));
	    
		driver.switchTo().frame(1);
		PageFactory.initElements(driver, this);
		
	}


	public void clickAddtoCart(Wrapper_Methods wmobj)

	{
		wmobj.clickLnkBtn(btnAddtoCart);

	}

	public void enterQuantity(Wrapper_Methods wmobj,String strQuanity)

	{
		wmobj.setValue(txtbxQuantity, strQuanity);
	}

	public void selectSize(Wrapper_Methods wmobj,String strSize)

	{
		wmobj.SelectValuebyText(drpdnSize, strSize);
	}

	public void selectSize(Wrapper_Methods wmobj,int iColor)

	{
		wmobj.clickLnkBtn(colorOptions.get(iColor));
	}
	public void selectColor(Wrapper_Methods wmobj,String iColor)

	{
		Iterator<WebElement> iter = colorOptions.iterator();
		while (iter.hasNext()){
			if ((iter.next().getAttribute("value")).equalsIgnoreCase(iColor)){
				wmobj.clickLnkBtn(iter.next());
			}
		}

	}

	public void VerifyElementTxt(Wrapper_Methods wmobj,String strElement, String Value) throws IOException

	{
		strElement =(strElement.replace(" ","").trim()).toUpperCase();

		switch(strElement){
		case "NAME":
			wmobj.verifyText(txtName, Value);
			break;

		case "MODELNAME":
			wmobj.verifyText(txtModelName, Value);
			break;
		case "CONDITION":
			wmobj.verifyText(txtModelName, Value);
			break;

		case "SHORTDESCRIPTION":
			wmobj.verifyText(txtShrtDescription,Value);
			break;

		case "PRICE":
			wmobj.verifyText(txtPrice,Value);
			break;
		default:
			throw new IllegalStateException("No such field exists. Please check" );


		}

	}

	
	public void EnterTxt(Wrapper_Methods wmobj, String strElement, String Value){
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