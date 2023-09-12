package org.run;

import org.base.BaseClass;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/eBay.feature", glue="org.step", dryRun = false)
public class TestRunner extends BaseClass{
	
	@AfterClass
	public static void end() {
		driver.quit();

	}

}
