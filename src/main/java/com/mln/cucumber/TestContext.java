package com.mln.cucumber;

import com.mln.managers.DriverManager;
import com.mln.managers.PageFactoryManager;
import com.mln.utilities.Common_Utility;

public class TestContext {
	private DriverManager driverManager;
	private PageFactoryManager pageFactoryManager;
	private Common_Utility common_Utility;
	
	public TestContext(){
		driverManager = new DriverManager();
		pageFactoryManager = new PageFactoryManager(driverManager.getDriver());
		common_Utility = new Common_Utility(driverManager.getDriver());
		
	}
	

	public DriverManager getDriverManager() {
		return driverManager;
	}
	
	public PageFactoryManager getPageFactoryManager() {
		return pageFactoryManager;
	}
	
	public Common_Utility getCommon_Utility(String sTest) {
		return common_Utility;
	}

	public Common_Utility getCommon_Utility() {
		// TODO Auto-generated method stub
		return common_Utility;
	}

}