package sharedStepDef;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.TestContext;
import io.cucumber.java.en.And;
import managers.DriverManager;
import managers.FileReaderManager;
import pageFactory.Cart_SummaryPF;
import utilities.Excel_Utility;
import utilities.Wrapper_Methods;

public class Cart_SummaryPFSteps {


	WebDriver driver;
	DriverManager driverManager;
	Cart_SummaryPF cart_SummaryPF;
	TestContext testContext;
	String strTestName = "TC-1";
	Wrapper_Methods wm;
	String strUrl = FileReaderManager.getInstance().getConfigReader().getApplicationUrl();
	Excel_Utility em = new Excel_Utility(strTestName);


	public Cart_SummaryPFSteps(TestContext context) {
		testContext = context;
		driver = testContext.getDriverManager().getDriver();
		wm = testContext.getWrapperMethods();
		cart_SummaryPF = testContext.getPageObjectManager().getcart_SummaryPF();

		//wm.SwitchFramebyName(fancyBoxFramePF.getTxtName());
	}

	@And("Verify if the user is in Cart_Summary Page")
	public void enters_the_txt() {
		System.out.println(driver.getCurrentUrl());
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));    
		w.until(ExpectedConditions.visibilityOf(cart_SummaryPF.getTxtCartSummary()));

		if (cart_SummaryPF.getTxtCartSummary()==null){
			throw new IllegalStateException("The  cart summary page is not displayed");
		}
			

	}
	
	
}
