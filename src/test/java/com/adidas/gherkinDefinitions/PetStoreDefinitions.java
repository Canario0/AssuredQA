package com.adidas.gherkinDefinitions;

import com.adidas.serenitySteps.PetStoreSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.Map;

public class PetStoreDefinitions {

    @Steps
    private PetStoreSteps restSteps;

    @When("^I request to do post operation with \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iRequestToDoPutWithTo(String headerField, String headerValue) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.setHeaderContentType(headerField, headerValue);
    }


    @And("^with body \"([^\"]*)\" including$")
    public void withBodyIncluding(String bodyPath, Map<String, String> values) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.bodyModify(bodyPath, values);
    }

    @Then("^I should get (\\d+) status code$")
    public void iShouldGetExpectedStatusCodeStatusCode(int statusCode) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.verifyStatusCode(statusCode);
    }

    @And("^response body contains$")
    public void responseBodyContains(Map<String, String> values) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.verifyBody(values);
    }


//    @When("^I request to do post operation with \"([^\"]*)\" to \"([^\"]*)\"$")
//    public void iRequestToDoPutOperationWithTo(String arg0, String arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @When("^I request to do post operation with \"([^\"]*)\" to \"([^\"]*)\"$")
//    public void iRequestToDoPostOperationWithTo(String arg0, String arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
}
