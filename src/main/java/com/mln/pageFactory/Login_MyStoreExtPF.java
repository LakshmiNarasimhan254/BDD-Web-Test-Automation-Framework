package com.mln.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mln.utilities.Wrapper_Methods;

public class Login_MyStoreExtPF {

	WebDriver driver;
	String strTestName;

	@FindBy(xpath="//input[@id='id_gender1']")
	private WebElement rdbtnMr;

	@FindBy(xpath="//input[@id='id_gender1']")
	private WebElement rdbtnMrs;

	@FindBy(xpath ="//input[@id='customer_firstname']")
	private WebElement txtbxFirstName;

	@FindBy(xpath ="//input[@id='customer_lastname']")
	private WebElement txtbxLastName;

	@FindBy(xpath ="//input[@id='email']")
	private WebElement txtEmail;

	@FindBy(xpath ="//input[@id='passwd']")
	private WebElement txtbxPassword; 

	@FindBy(xpath = "//select[@id='days']")
	private WebElement drpdnDate;

	@FindBy(xpath ="//select[@id='months']")
	private WebElement drpdnMonth;

	@FindBy(xpath ="//select[@id='years']")
	private WebElement drpdnYear;

	@FindBy(xpath ="/input[@id='newsletter']")
	private WebElement chkbxNewsletter;

	@FindBy(xpath = "//label[contains(text(),'Receive special offers from our partners!')]")
	private WebElement chkbxSplOffers;

	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement txtbxAddFirstName;


	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement txtbxAddLastName;

	@FindBy(xpath ="//input[@id='company']")
	private WebElement txtbxAddCompany;

	@FindBy(xpath="//input[@id='address1']")
	private WebElement txtbxAddAddressline1;

	@FindBy(xpath="//input[@id='address2']")
	private WebElement txtbxAddAddressline2;

	@FindBy(xpath ="//input[@id='city']")
	private WebElement txtbxAddCity;

	@FindBy(xpath ="//select[@id='id_state']")
	private WebElement drpdnAddState;

	@FindBy(xpath ="//input[@id='postcode']")
	private WebElement txtbxZipCode;

	@FindBy(xpath ="//select[@id='id_country']")
	private WebElement drpdnAddCountry;

	@FindBy(xpath ="//textarea[@id='other']")
	private WebElement drpdnAddAdditonalInformation;

	@FindBy(xpath ="//input[@id='phone']")
	private WebElement txtbxAddHomePhone;

	@FindBy(xpath ="//input[@id='phone_mobile']")
	private WebElement txtbxAddMobilePhone;

	@FindBy(xpath="//input[@id='alias']")
	private WebElement txtbxAddAlias;

	@FindBy(xpath="//button[@id='submitAccount']")
	private WebElement btnRegister;



	public Login_MyStoreExtPF(WebDriver driver , String strTestName){
		this.driver =driver;
		this.strTestName = strTestName;
		PageFactory.initElements(driver, this);

		if(!driver.getTitle().equalsIgnoreCase("Login - My Store")){
			throw new IllegalStateException("This is not My Login - My Store Page. The Current Page is " + driver.getTitle());
		}
	}

	public Login_MyStoreExtPF(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);

		if(!driver.getTitle().equalsIgnoreCase("Login - My Store")){
			throw new IllegalStateException("This is not My Login - My Store Page. The Current Page is " + driver.getTitle());
		}
	}

	public void EnterTxt(Wrapper_Methods wmobj, String strElement, String Value){
		strElement =(strElement.replace(" ","").trim()).toUpperCase();
		
		switch(strElement){
		case "FIRSTNAME" :
			wmobj.setValue(txtbxFirstName, Value);
			break;
		case "LASTNAME" :
			wmobj.setValue(txtbxLastName, Value);
			break;
		case "EMAIL" :
			wmobj.setValue(txtEmail, Value);
			break;	
		case "PASSWORD" :
			wmobj.setValue(txtbxPassword, Value);
			break;	
		case "ADDRESSFIRSTNAME" :
			wmobj.setValue(txtbxAddFirstName, Value);
			break;
		case "ADDRESSLASTNAME" :
			wmobj.setValue(txtbxAddLastName, Value);
			break;
		case "COMPANY" :
			wmobj.setValue(txtbxAddCompany, Value);
			break;			
		case "ADDRESSLINE1" :
			wmobj.setValue(txtbxAddAddressline1, Value);
			break;
		case "ADDRESSLINE2" :
			wmobj.setValue(txtbxAddAddressline2, Value);
			break;

		case "CITY" :
			wmobj.setValue(txtbxAddCity, Value);
			break;
		case "ZIP/POSTALCODE" :
			wmobj.setValue(txtbxZipCode, Value);
			break;
		case "ADDITIONALINFORMATION" :
			wmobj.setValue(drpdnAddAdditonalInformation, Value);
			break;
		case "HOMEPHONE" :
			wmobj.setValue(txtbxAddHomePhone, Value);
			break;

		case "MOBILEPHONE" :
			wmobj.setValue(txtbxAddMobilePhone, Value);
			break;

		case "ASSIGNANADDRESSALIASFORFUTUREREFERENCE" :
			wmobj.setValue(txtbxAddAlias, Value);
			break;
		case"DATEOFBIRTH":
			SelectDOB(wmobj,Value);
			break;
		case"STATE":
			SelectState(wmobj,Value);
			break;
		case"COUNTRY":
			SelectCountry(wmobj,Value);
			break;
		default:
			throw new IllegalStateException("This is not a correct value: " + strElement);
		}
	}
	public void SelectDOB(Wrapper_Methods wmobj, String Value){
		Value = Value.replace(" ","").trim();
		String[] Date = Value.split("-");
		String strDay = Date[0];
		String strMonth = Date[1];
		String strYear = Date[2];
		
		wmobj.SelectValuebyValue(drpdnDate, strDay);
		if (Date[1].length() == 2){
			if  (Date[1].startsWith("0")){
				strMonth =Date[1].substring(1);				
			}
		}
		wmobj.SelectValuebyValue(drpdnMonth, strMonth);
		wmobj.SelectValuebyValue(drpdnYear, strYear);
	}


	public void SelectState(Wrapper_Methods wmobj, String Value){
		Value = Value.trim();		
		wmobj.SelectValuebyText(drpdnAddState, Value);	
	}

	public void SelectCountry(Wrapper_Methods wmobj, String Value){
		Value = Value.trim();		
		wmobj.SelectValuebyText(drpdnAddCountry, Value);	
	}

	public void SelectTitle(Wrapper_Methods wmobj, String Value){
		Value = (Value.trim()).toUpperCase();
		if(Value == "MR"){
			wmobj.SelectRadioButton(rdbtnMr);
		}
		else if (Value == "MRS"){
			wmobj.SelectRadioButton(rdbtnMrs);

		}else {
			throw new IllegalStateException("This is not a correct value: " + Value);
		}
	}
	public void ClickRegister(Wrapper_Methods wmobj){
		wmobj.clickLnkBtn(btnRegister);
	}




	public void VerifyLogin_MyStoreExt(Wrapper_Methods wmobj)
	{
		wmobj.isElementPresent(btnRegister);
	}

}
