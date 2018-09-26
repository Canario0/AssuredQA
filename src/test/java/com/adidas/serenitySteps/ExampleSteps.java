package com.adidas.serenitySteps;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import io.restassured.response.Response;

import static net.serenitybdd.rest.SerenityRest.rest;
import com.adidas.config.ServicesConfiguration;
import com.adidas.support.ServicesSupport;
import java.io.InputStream;

import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import org.junit.Assert;

public class ExampleSteps{

    private ServicesSupport servicesSupport = new ServicesSupport();
    private RequestSpecification spec = rest().baseUri(ServicesConfiguration.URI).contentType(ContentType.JSON).when();

    public String getEndpoint() {
        return endpoint;
    }

    private String endpoint = ServicesConfiguration.USERS;

    /**
     * Performs a GET operation with an ID provided by parameter from the scenario
     * @param id The ID of a user
     * */
    @Step
    public void getUserById(String id) {

        String endpoint = getEndpoint() + "/" + id;
        Response response = servicesSupport.executeRequest(spec,"GET", endpoint);
        Serenity.setSessionVariable("response").to(response);
    }

    /**
     * Performs a POST operation that will create a new user
     * */
    @Step
    public void createUser(){

        try {
            InputStream is = this.getClass().getResourceAsStream("/requests/create_user.json");
            JSONObject body = servicesSupport.jsonInputStreamToJsonObject(is);
            spec = spec.body(body.toMap());
            Response response = servicesSupport.executeRequest(spec,"POST", endpoint);
            Serenity.setSessionVariable("response").to(response);
        }
        catch(Exception e){
            e.getMessage();
        }
    }

    /**
     * Performs a PUT operation with an ID from the scenario as a parameter
     * @param id The ID of a user
     * */
    @Step
    public void updateUserById(String id) {

        try {
            InputStream is = this.getClass().getResourceAsStream("/requests/update_user.json");
            String endpoint = getEndpoint() + "/" + id;
            JSONObject body = servicesSupport.jsonInputStreamToJsonObject(is);
            spec = spec.body(body.toMap());
            Response response = servicesSupport.executeRequest(spec,"PUT", endpoint);
            Serenity.setSessionVariable("response").to(response);
        }
        catch(Exception e){
            e.getMessage();
        }
    }

    /**
     * Performs a DELETE operation with an ID provided by parameter from the scenario
     * @param id The ID of a user
     * */
    @Step
    public void deleteUserById(String id) {

        String endpoint = getEndpoint() + "/" + id;
        Response response = servicesSupport.executeRequest(spec,"DELETE", endpoint);
        Serenity.setSessionVariable("response").to(response);
    }

    /**
     * Method to verify an status code received from the scenario
     * @param expectedStatusCode Expected status code in the response
     * */
    @Step
    public void verifyStatusCode(int expectedStatusCode){

        Response res = Serenity.sessionVariableCalled("response");
        Assert.assertEquals("status code doesn't match", expectedStatusCode, res.getStatusCode());
    }

    /**
     * Method to verify an status code received from the scenario
     * @param res Response object from a previous operation
     * @param operation The operation that was done in a previous step received from Cucumber
     * @param key Attribute name received from the scenario as a parameter
     * @param expectedValue Expected value of the attribute received from the scenario as a parameter
     * */
    @Step
    public void verifyValueFromKey(Response res, String operation, String key, String expectedValue) {

        String currentValue = "";

        switch (operation.toLowerCase()) {
            case "get":
                currentValue = res.getBody().jsonPath().getString(key);
                break;
            case "post":
                currentValue = res.getBody().jsonPath().getString(key);
                break;
            case "update":
                currentValue = res.getBody().jsonPath().getString(key);
                break;
            case "delete":
                currentValue = res.getBody().jsonPath().getString("data." + key);
                break;
            default:
                break;
        }

        Assert.assertEquals("Value for " + key + " doesn't match", expectedValue, currentValue);
    }
}