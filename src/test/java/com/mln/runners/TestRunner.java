package com.mln.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
			features ="src/test/resources/features/AddtoCartviaQuickView.feature", 
			glue={"com.mln.sharedStepDef"},
			monochrome= true,
			plugin=
				{"pretty","html:target/HtmlReports/index.html" 
				,"json:target/JsonReports/index.json" 
				,"junit:target/XmlReports/index.xml"}
				//,tags = "@functional and @Smoked"
			)

public class TestRunner {
  
}
