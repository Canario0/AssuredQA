package com.adidas.serenitySteps;

import com.adidas.support.ServicesSupport;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.ResourceBundle;

import static net.serenitybdd.rest.SerenityRest.rest;

public class PetStoreSteps {

    private ResourceBundle config = ResourceBundle.getBundle("config/config");
    private ServicesSupport servicesSupport = new ServicesSupport();
    private final String endpoint = config.getString("URI");
    private RequestSpecification spec = rest().baseUri(endpoint);
    private String path;

    @Step("When I request to do put operation with \"<field>\"  to \"<value>\"")
    public void setHeaderContentType(String method,String field, String value) {

        Serenity.setSessionVariable("method").to(method);
        switch (field) {
            case "Content-Type":
                spec.when().contentType(value);
                if (value.equals("application/xml"))
                    spec.accept(value);
                break;
            default:
                break;
        }
    }

    @Step("And with URI \"<string>\"")
    public void setPath(String path){
       this.path=path;
    }

    @Step("And with body \"/requests/(post|get|put)/json/(post|get|put)_(pet|order)_<expectedStatusCode>.(json|xml)\"")
    public void bodySend(String jsonBodyFile) {

        try {
            InputStream is = this.getClass().getResourceAsStream(jsonBodyFile);
            String body = servicesSupport.jsonInputStreamToJsonObject(is);
//            values.forEach((key, val) ->
//                    body.put(key, val)
//            );

            spec = spec.body(body);
            Response response = servicesSupport.executeRequest(spec, Serenity.sessionVariableCalled("method"), endpoint + path);
            Serenity.setSessionVariable("response").to(response);
//            Serenity.setSessionVariable("body").to(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Step("Then I should get <expectedStatusCode> status code")
    public void verifyStatusCode(int expectedStatusCode) {
        Response res = Serenity.sessionVariableCalled("response");
        Assert.assertEquals("status code doesn't match", expectedStatusCode, res.getStatusCode());
    }

    @Step("And response body equals to \"/requests/(post|get|put)/json/(post|get|put)_(pet|order)_<expectedStatusCode>.(json|xml)\"")
    public void verifyBody(String expectedJsonBodyFile) {
        try {
            Response res = Serenity.sessionVariableCalled("response");
            InputStream is = this.getClass().getResourceAsStream(expectedJsonBodyFile);
            String body = servicesSupport.jsonInputStreamToJsonObject(is);
            Assert.assertEquals("The body " + format(body) + " doesn't match with " + format(res.getBody().print()), format(res.getBody().print()), format(body));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String format(String input) {
        return input.replace("\n", "").replace("\r", "").replace(" ", "");
    }

    @Step("And find all the pets with status \"<statusValue>\"")
    public void findByStatus(String statusValue) {
        Response response = servicesSupport.executeRequest(spec, Serenity.sessionVariableCalled("method"), endpoint + path + String.format("/findByStatus?status=%s", statusValue));
        Serenity.setSessionVariable("response").to(response);
    }

    /**
     * Method to verify an status code received from the scenario
     *
     * @param res           Response object from a previous operation
     * @param operation     The operation that was done in a previous step received from Cucumber
     * @param key           Attribute name received from the scenario as a parameter
     * @param expectedValue Expected value of the attribute received from the scenario as a parameter
     */
    public void verifyValueFromKey(Response res, String operation, String key, String expectedValue) {

        String currentValue = "";

        switch (operation.toLowerCase()) {
            case "get":
            case "post":
            case "update":
                currentValue = res.getBody().jsonPath().getString(key);
                break;
            case "delete":
                currentValue = res.getBody().jsonPath().getString("data." + key);
                break;
            default:
                break;
        }

        Assert.assertNotEquals("Value for " + key + " doesn't match", expectedValue, currentValue);
    }

    @Step("And is not empty")
    public void isNotEmpty() {
        Response res = Serenity.sessionVariableCalled("response");
        if (res.getContentType().equals("application/json"))
            Assert.assertTrue("The response is empty", res.jsonPath().getList("").size() > 0);
        else
            Assert.assertTrue("The response is empty", res.xmlPath().getList("pets").size() > 0);
    }

    @Step
    public void deleteById(String id) {
        Response response = servicesSupport.executeRequest(spec, Serenity.sessionVariableCalled("method"), endpoint + path + String.format("/%s", id));
        Serenity.setSessionVariable("response").to(response);
    }

    @Step
    public void getInventory(String path) {
        Response response = servicesSupport.executeRequest(spec, Serenity.sessionVariableCalled("method"), endpoint + this.path + path);
        Serenity.setSessionVariable("response").to(response);
    }

    @Step
    public void containsKey(String key) {
        Response res = Serenity.sessionVariableCalled("response");
        Assert.assertTrue("The response does not contains any key equals to " + key,(new JSONObject((Map<?,?>) res.getBody().jsonPath().getJsonObject("$")).has(key)));
    }

    @Step
    public void findOrderById(String id) {
        Response response = servicesSupport.executeRequest(spec, Serenity.sessionVariableCalled("method"), endpoint + path + "/" + id);
        Serenity.setSessionVariable("response").to(response);
    }
}