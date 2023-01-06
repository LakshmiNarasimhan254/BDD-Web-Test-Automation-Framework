package com.mln.cucumber;

import java.net.MalformedURLException;

import com.mln.managers.DriverManager;
import com.mln.managers.PageFactoryManager;
import com.mln.utilities.CommonUtility;

public class TestContext {
	private DriverManager driverManager;
	private PageFactoryManager pageFactoryManager;
	private CommonUtility common_Utility;
	
	public TestContext() throws MalformedURLException{
		driverManager = new DriverManager();
		pageFactoryManager = new PageFactoryManager(driverManager.getDriver());
		common_Utility = new CommonUtility(driverManager.getDriver());
		
	}
	

	public DriverManager getDriverManager() {
		return driverManager;
	}
	
	public PageFactoryManager getPageFactoryManager() {
		return pageFactoryManager;
	}
	
	public CommonUtility getCommon_Utility(String sTest) {
		return common_Utility;
	}

	public CommonUtility getCommon_Utility() {
		// TODO Auto-generated method stub
		return common_Utility;
	}

}