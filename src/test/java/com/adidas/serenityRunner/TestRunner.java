package com.adidas.serenityRunner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


/**
 * Created by Usuario on 19/05/2017.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features/"},
//        plugin = { "pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        glue = {"com.adidas.gherkinDefinitions"}
)

public class TestRunner {

}
