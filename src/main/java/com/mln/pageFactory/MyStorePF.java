package com.mln.pageFactory;




import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mln.utilities.Common_Utility;

public class MyStorePF {
	
	WebDriver driver;
	String strTestName;
	@FindBy(xpath= "//a[contains(text(),'Sign in')]")
	private WebElement btnSignin;
	
	

	public MyStorePF(WebDriver driver , String strTestName){
		this.driver =driver;
		this.strTestName = strTestName;
		PageFactory.initElements(driver, this);
		
	}
	
	public MyStorePF(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
		if(!driver.getTitle().equalsIgnoreCase("My Store")){
			throw new IllegalStateException("This is not My Store Page. The Current Page is " + driver.getTitle());
		}
	}

	
	public void ClickSignin(Common_Utility wmobj) throws Exception
	{
		
		wmobj.clickLnkBtn(btnSignin);
	}
	
	public void ClicktoViewPopular(Common_Utility wmobj,String strProductName) throws Exception
		
	{
		String strXpath = "//ul[@id='homefeatured']//div/a[@title = '" + strProductName+ "']";
		WebElement PopularProduct = driver.findElement(By.xpath(strXpath));
		wmobj.clickLnkBtn(PopularProduct);
				
	}
	
}