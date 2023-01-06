package com.mln.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features ="src/test/resources/features", 
		glue={"com.mln.sharedStepDef"},
		monochrome= true,
		plugin=
	{
			"pretty","html:target/HtmlReports/index.html" 
			,"json:target/JsonReports/index.json" 
			//,"com.avenstack.extenreports.cucumber.adapter.ExtentCucumberAdapter"
			,"junit:target/XmlReports/index.xml"
			,"timeline:test-output-thread/"
			,"rerun:target/failedrerun.txt"
			}
		,tags = "@functional"
	
		)

public class TestRunner {

}
