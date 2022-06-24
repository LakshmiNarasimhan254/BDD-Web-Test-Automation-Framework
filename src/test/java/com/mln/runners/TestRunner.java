package com.mln.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/resources/Features/AddtoCartviaQuickView.feature", glue={"com.mln.sharedStepDef"},monochrome= false,
plugin={"pretty","html:target/HtmlReports/index.html","json:target/JsonReports/index.json", "junit:target/XmlReports/index.xml"},
tags = "@functional"
)

public class TestRunner {
  public static void main(String[] args) {
	System.out.println("am starting");
}
}
