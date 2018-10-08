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

    @When("^I request to do (POST|GET|DELETE|PUT) operation with \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iRequestToDoPutWithTo(String method, String headerField, String headerValue) {
        // Write code here that turns the phrase above into concrete actions
        restSteps.setHeaderContentType(method,headerField, headerValue);
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

//    @When("^I request to do a get operation with \"([^\"]*)\" to \"([^\"]*)\"$")
//    public void iRequestToDoAGetOperationWithTo(String headerField, String headerValue) {
//        restSteps.setHeaderContentType("GET",headerField, headerValue);
//    }

    @And("^find all the pets with status \"([^\"]*)\"$")
    public void findAllThePetsWithStatus(String statusValue) {
        restSteps.findByStatus(statusValue);
    }

    @And("^is not empty$")
    public void isNotEmpty(){
        restSteps.isNotEmpty();
    }

    @And("^delete pet with id equal to \"([^\"]*)\"$")
    public void deletePetWithIdEqualTo(String id) {
        restSteps.deleteById(id);
    }

    @And("^with path \"([^\"]*)\"$")
    public void withPath(String path) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        restSteps.getInventory(path);
    }

    @And("^response contains \"([^\"]*)\"$")
    public void responseContains(String key){
        // Write code here that turns the phrase above into concrete actions
        restSteps.containsKey(key);
    }

    @And("^with URI \"([^\"]*)\"$")
    public void withURI(String uri){
        restSteps.setPath(uri);
    }

//    @When("^I request to do PUT operation with \"([^\"]*)\" to \"([^\"]*)\"$")
//    public void iRequestToDoPUTOperationWithTo(String headerField, String headerValue) {
//        restSteps.setHeaderContentType("PUT",headerField, headerValue);
//    }

//    @When("^I request to do a DELETE operation with \"([^\"]*)\" to \"([^\"]*)\"$")
//    public void iRequestToDoADELETEOperationWithTo(String headerField, String headerValue){
//        restSteps.setHeaderContentType("DELETE",headerField, headerValue);
//    }
}
