package com.mln.managers;

import org.openqa.selenium.WebDriver;

import com.mln.pageFactory.Cart_SummaryPF;
import com.mln.pageFactory.FancyBoxFramePF;
import com.mln.pageFactory.Layer_CartPF;
import com.mln.pageFactory.Login_MyStoreExtPF;
import com.mln.pageFactory.Login_MyStorePF;
import com.mln.pageFactory.MyStorePF;


public class PageFactoryManager {
	
	private WebDriver driver;	
	private MyStorePF mystorePF;
	private FancyBoxFramePF fancyBoxFramePF;
	private Login_MyStoreExtPF login_mystoreExtPF;
	private Login_MyStorePF login_mystorePF;
	private Layer_CartPF layer_CartPF;
	private Cart_SummaryPF cart_SummaryPF;
	
	public PageFactoryManager(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	public MyStorePF getmystorePF(){
		driver.switchTo().defaultContent();
		return (mystorePF == null) ? mystorePF = new MyStorePF(driver) : mystorePF;

	}
	
	public Login_MyStorePF getlogin_mystorePF(){
		driver.switchTo().defaultContent();
		return (login_mystorePF == null) ? login_mystorePF = new Login_MyStorePF(driver) : login_mystorePF;

	}

	public Login_MyStoreExtPF getlogin_mystoreExtPF(){
		driver.switchTo().defaultContent();
		return (login_mystoreExtPF == null) ? login_mystoreExtPF = new Login_MyStoreExtPF(driver) : login_mystoreExtPF;

	}
	
	public FancyBoxFramePF getfancyBoxFramePF(){
		
		return (fancyBoxFramePF == null) ? fancyBoxFramePF = new FancyBoxFramePF(driver) : fancyBoxFramePF;

	}
	
	public Layer_CartPF getlayer_CartPF(){
		driver.switchTo().defaultContent();
		return (layer_CartPF == null) ? layer_CartPF = new Layer_CartPF(driver) : layer_CartPF;

	}
	public Cart_SummaryPF getcart_SummaryPF(){
		driver.switchTo().defaultContent();
		return (cart_SummaryPF == null) ? cart_SummaryPF = new Cart_SummaryPF(driver) : cart_SummaryPF;

	}

	

}
