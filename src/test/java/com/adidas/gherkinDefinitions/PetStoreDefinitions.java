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

    /*
                                  POST
     */

    @When("^I request to do post operation with \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iRequestToDoPutWithTo(String headerField, String headerValue) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.setHeaderContentType(headerField, headerValue);
    }

    @And("^with body \"([^\"]*)\"$")
    public void withBodyIncluding(String jsonBodyFile) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.bodySend(jsonBodyFile);
    }

    @Then("^I should get (\\d+) status code$")
    public void iShouldGetExpectedStatusCodeStatusCode(int statusCode) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.verifyStatusCode(statusCode);
    }

    @And("^response body equals to \"([^\"]*)\"$")
    public void responseBodyContains(String expectedJsonBody) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.verifyBody(expectedJsonBody.replace("\n", ""));
    }

    /*
                                GET
     */

    @When("^I request to do a get operation with \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iRequestToDoAGetOperationWithTo(String headerField, String headerValue) {
        restSteps.setHeaderContentType(headerField, headerValue);
    }

    @And("^find all the pets with status \"([^\"]*)\"$")
    public void findAllThePetsWithStatus(String statusValue) {
        restSteps.findByStatus(statusValue);
    }

//    @And("^is not empty$")
//    public void isNotEmpty(){
//    }

//    @And("^is not empty$")
//    public void isNotEmpty() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
}
