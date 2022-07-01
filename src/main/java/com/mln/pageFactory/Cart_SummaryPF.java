package com.mln.pageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mln.utilities.Common_Utility;

public class Cart_SummaryPF {
	WebDriver driver;
	String strTestName;
	@FindBy(xpath= "//h1[@id='cart_title']")
	private WebElement txtCartSummary;

	@FindBy(xpath="//span[@id='summary_products_quantity']")
	private WebElement txtProductCount;

	@FindBy(xpath="//table[@id='cart_summary']")
	private WebElement tbleCartSummary;

	@FindBy(xpath="//p[@class='cart_navigation clearfix']/a[@title ='Proceed to checkout']")
	private WebElement btnProceedtoCheckout;

	@FindBy(xpath="//p[@class='cart_navigation clearfix']/a[@title ='Continue shopping']")
	private WebElement btnContShopping;

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

	private HashMap<String, String> getcartLineDetails(WebDriver driver)throws Exception {
		Iterator<WebElement> iter = cartItems(driver).iterator();
		HashMap<String,String>hm = new HashMap<String, String>();


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
			hm.put(iHashMapKey+"UNITPRICE", txtunitPrice);

			//WebElement txtPriceReduction = linedetails.findElement(By.xpath(".//td[4]//span[1]/span[2]"));
			//WebElement txtoldPrice = linedetails.findElement(By.xpath(".//td[4]//span[1]/span[3]"));


			String txtbxQuantity =linedetails.findElement(By.xpath(".//td[5]/input[1]")).getAttribute("value");
			hm.put(iHashMapKey+"QUANTITY", txtbxQuantity);

			String txttotalPerProduct =linedetails.findElement(By.xpath(".//td[6]/span")).getText();
			hm.put(iHashMapKey+"PRODUCTTOTAL", txttotalPerProduct);

			WebElement lnkdelete = linedetails.findElement(By.xpath(".//td[7]/div/a"));






		}


		return hm;
	}

	public WebElement getTxtCartSummary() throws Exception{
		return txtCartSummary;
	}


	public boolean verifyCartDetails(Common_Utility wmobj,String StrValue) throws Exception{
		boolean bResult = false;
		String[] StrValues=null;

		//try {
			if (!(StrValue.contains("|"))){
				StrValues = StrValue.split(",");
				for (String s : StrValues){
					if (s.contains(".")){
						s.replaceAll(".", "");
						String StrKeyValue = (s.split(":=")[0]).replace(" ", "").toUpperCase();
						String StrExpectedValue = (s.split(":=")[1]);
						//System.out.println(StrKeyValue);
						bResult= wmobj.verifyText(getcartLineDetails(driver).get(StrKeyValue),StrExpectedValue);					
					}
				}
			}else{	
				StrValues=StrValue.split(Pattern.quote("|"));
				String[] s1;
				for (String s :StrValues){
					s1= s.split(",");
					for (String s2 : s1){
						if (s2.contains(".")){
							String s3 = s2.replaceAll("\\.", "");
							//System.out.println(s2);
							String StrKeyValue = (s3.split(":=")[0]).replace(" ", "").toUpperCase();
							String StrExpectedValue = (s3.split(":=")[1]);
							//System.out.println("This is thE key value " +StrKeyValue);

							//System.out.println(getcartLineDetails(driver).get(StrKeyValue));
							bResult=wmobj.verifyText((getcartLineDetails(driver).get(StrKeyValue).trim()),StrExpectedValue);					
						}
					}


				}
			}
			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return bResult;



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
