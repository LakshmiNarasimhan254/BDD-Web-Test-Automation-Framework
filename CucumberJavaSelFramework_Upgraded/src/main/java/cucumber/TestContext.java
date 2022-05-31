package cucumber;

import managers.PageObjectManager;
import utilities.Wrapper_Methods;
import managers.DriverManager;

public class TestContext {
	private DriverManager driverManager;
	private PageObjectManager pageObjectManager;
	private Wrapper_Methods wrappermethods;
	
	public TestContext(){
		driverManager = new DriverManager();
		pageObjectManager = new PageObjectManager(driverManager.getDriver());
		wrappermethods = new Wrapper_Methods(driverManager.getDriver());
		
	}
	

	public DriverManager getDriverManager() {
		return driverManager;
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	
	public Wrapper_Methods getWrapperMethods(String sTest) {
		return wrappermethods;
	}

	public Wrapper_Methods getWrapperMethods() {
		// TODO Auto-generated method stub
		return wrappermethods;
	}

}