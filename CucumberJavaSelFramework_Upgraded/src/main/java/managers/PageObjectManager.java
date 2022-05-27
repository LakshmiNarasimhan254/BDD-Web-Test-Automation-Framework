package managers;

import org.openqa.selenium.WebDriver;

import pageFactory.Login_MyStoreExtPF;
import pageFactory.Login_MyStorePF;
import pageFactory.MyStorePF;

public class PageObjectManager {
	
	private WebDriver driver;	
	private MyStorePF mystorePF;
	private Login_MyStorePF login_mystorePF;
	private Login_MyStoreExtPF login_mystoreExtPF;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;

	}
	
	public MyStorePF getmystorePF(){

		return (mystorePF == null) ? mystorePF = new MyStorePF(driver) : mystorePF;

	}
	
	public Login_MyStorePF getlogin_mystorePF(){

		return (login_mystorePF == null) ? login_mystorePF = new Login_MyStorePF(driver) : login_mystorePF;

	}

	public Login_MyStoreExtPF getlogin_mystoreExtPF(){

		return (login_mystoreExtPF == null) ? login_mystoreExtPF = new Login_MyStoreExtPF(driver) : login_mystoreExtPF;

	}

	

}
