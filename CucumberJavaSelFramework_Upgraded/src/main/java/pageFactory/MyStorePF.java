package pageFactory;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.Wrapper_Methods;

public class MyStorePF {
	
	WebDriver driver;
	String strTestName;
	@FindBy(xpath= "//a[contains(text(),'Sign in')]")
	WebElement btnSignin;

	public MyStorePF(WebDriver driver , String strTestName){
		this.driver =driver;
		this.strTestName = strTestName;
		PageFactory.initElements(driver, this);
		if(!driver.getTitle().equalsIgnoreCase("My Store")){
			throw new IllegalStateException("This is not My Store Page. The Current Page is " + driver.getTitle());
		}
	}
	
	public MyStorePF(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
		if(!driver.getTitle().equalsIgnoreCase("My Store")){
			throw new IllegalStateException("This is not My Store Page. The Current Page is " + driver.getTitle());
		}
	}

	

	public void ClickSignin(Wrapper_Methods wmobj)
	{
		
		wmobj.clickLnkBtn(btnSignin);
	}
}