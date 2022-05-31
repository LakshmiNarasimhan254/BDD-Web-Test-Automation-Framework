package pageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart_SummaryPF {
	WebDriver driver;
	String strTestName;
	@FindBy(xpath= "//h1[@id='cart_title']")
	WebElement txtCartSummary;

	@FindBy(xpath="//span[@id='summary_products_quantity']")
	WebElement txtProductCount;

	@FindBy(xpath="//table[@id='cart_summary']")
	WebElement tbleCartSummary;

	public Cart_SummaryPF(WebDriver driver){
		this.driver =driver;
		//WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
		// w.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("fancybox-frame1653936324222"));	
		PageFactory.initElements(driver, this);

	}

	public int totalRowsinCart(WebDriver driver){
		int totalRowsinCart = cartItems(driver).size();
		return totalRowsinCart;
	}

	public List<WebElement> cartItems(WebDriver driver){
		List<WebElement> productList = driver.findElements(By.xpath("//table[@id='cart_summary']//tbody/tr"));			
		return productList;
	}

	private HashMap<String, String> getcartLineDetails(WebDriver driver){
		Iterator<WebElement> iter = cartItems(driver).iterator();
		HashMap<String,String>hm = new HashMap<String, String>();
		//List<WebElement> productdetails  = new ArrayList();
		int iHashMapKey =0;
		while(iter.hasNext()){
			iHashMapKey= iHashMapKey+1;
			WebElement linedetails = iter.next();


			String lnkProductName= linedetails.findElement(By.xpath(".//td[1]/a")).getAttribute("Href");
			hm.put(iHashMapKey+"PRODUCTLINK", lnkProductName);	

			String lnkproductname= linedetails.findElement(By.xpath(".//td[2]/p/a")).getText();
			hm.put(iHashMapKey+"NAME", lnkproductname);

			String lnkSKU = linedetails.findElement(By.xpath(".//td[2]/small")).getText();
			hm.put(iHashMapKey+"SKU", lnkSKU.split(":")[1]);

			String lnkColorsize= linedetails.findElement(By.xpath(".//td[2]/small/a")).getText();
			String lnkColor =(lnkColorsize.split(",")[0]).split(":")[1];			
			hm.put(iHashMapKey+"COLOR", lnkColor);

			String lnkSize =(lnkColorsize.split(",")[1]).split(":")[1];
			hm.put(iHashMapKey+"SIZE", lnkSize);


			
			String txIinstock= linedetails.findElement(By.xpath(".//td[3]/span")).getText();
			hm.put(iHashMapKey+"AVAIL", txIinstock);
			
			String txtunitPrice = linedetails.findElement(By.xpath(".//td[4]//span[1]/span[1]")).getText();
			hm.put(iHashMapKey+"UNITPRICE", txIinstock);
			
			//WebElement txtPriceReduction = linedetails.findElement(By.xpath(".//td[4]//span[1]/span[2]"));
			//WebElement txtoldPrice = linedetails.findElement(By.xpath(".//td[4]//span[1]/span[3]"));
			
			
			String txtbxQuantity =linedetails.findElement(By.xpath(".//td[5]/input[1]")).getAttribute("value");
			hm.put(iHashMapKey+"QUANTITY", txtbxQuantity);
			
			String txttotalPerProduct =linedetails.findElement(By.xpath(".//td[6]/span")).getText();
			hm.put(iHashMapKey+"PRODUCTTOTAL", txttotalPerProduct);
			
			WebElement lnkdelete = linedetails.findElement(By.xpath(".//td[7]/div/a"));
			



		

		}

		Iterator<Entry<String, String>> hmiter =  hm.entrySet().iterator();
		while (hmiter.hasNext()){
		System.out.println(hmiter.next());
		}
		return hm;
	}

	public WebElement getTxtCartSummary() {
		return txtCartSummary;
	}
	
	
	public void verifyCartDetails(String StrValue){
		if (StrValue.contains(".")){
			StrValue.replace(".","");
				
		}
		getcartLineDetails(driver);
		
	}






}
