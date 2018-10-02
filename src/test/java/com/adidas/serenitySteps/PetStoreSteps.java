package com.adidas.serenitySteps;

import com.adidas.support.ServicesSupport;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import static net.serenitybdd.rest.SerenityRest.rest;

public class PetStoreSteps {

    private ResourceBundle config = ResourceBundle.getBundle("config/config");
    private ServicesSupport servicesSupport = new ServicesSupport();
    private final String endpoint = config.getString("URI");
    private RequestSpecification spec = rest().baseUri(endpoint);

    @Step("When I request to do put operation with \"<field>\"  to \"<value>\"")
    public void setHeaderContentType(String field, String value) {
        switch (field) {
            case "Content-Type":
                spec.when().contentType(value);
                if(value.equals("application/xml"))
                    spec.accept(value);
                break;
            default:
                break;
        }
    }

    @Step("And with body \"/requests/post/json/post_pet_<expectedStatusCode>.(json|xml)\"")
    public void bodySend(String jsonBodyFile) {

        try {
            InputStream is = this.getClass().getResourceAsStream(jsonBodyFile);
            String body = servicesSupport.jsonInputStreamToJsonObject(is);
//            values.forEach((key, val) ->
//                    body.put(key, val)
//            );

            spec = spec.body(body);
            Response response = servicesSupport.executeRequest(spec, "POST", endpoint + config.getString("USERS"));
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

    @Step("And response body equals to \"/requests/post/json/expected_post_pet_<expectedStatusCode>.(json|xml)\"")
    public void verifyBody(String expectedJsonBodyFile) {
        try {
            Response res = Serenity.sessionVariableCalled("response");
            InputStream is = this.getClass().getResourceAsStream(expectedJsonBodyFile);
            String body = servicesSupport.jsonInputStreamToJsonObject(is);
            Assert.assertEquals("The body " + format(body) + " doesn't match with " + format(res.getBody().print()), format(res.getBody().print()), format(body));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String format(String input){
        return input.replace("\n", "").replace("\r", "").replace(" ", "");
    }

    @Step("And find all the pets with status \"<statusValue>\"")
    public void findByStatus(String statusValue){
        Response response = servicesSupport.executeRequest(spec, "GET", endpoint + config.getString("USERS")+ String.format("/findByStatus?status=%s",statusValue));
        Serenity.setSessionVariable("response").to(response);
    }

//    public void

//    @Step
//    public void createUser() {
//
//        try {
//            InputStream is = this.getClass().getResourceAsStream("/requests/create_user.json");
//            JSONObject body = servicesSupport.jsonInputStreamToJsonObject(is);
//            spec = spec.body(body.toMap());
//            Response response = servicesSupport.executeRequest(spec, "POST", endpoint);
//            Serenity.setSessionVariable("response").to(response);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
//
//    /**
//     * Performs a PUT operation with an ID from the scenario as a parameter
//     *
//     * @param id The ID of a user
//     */
//    @Step
//    public void updateUserById(String id) {
//
//        try {
//            InputStream is = this.getClass().getResourceAsStream("/requests/update_user.json");
////            String endpoint = getEndpoint() + "/" + id;
//            JSONObject body = servicesSupport.jsonInputStreamToJsonObject(is);
//            spec = spec.body(body.toMap());
//            Response response = servicesSupport.executeRequest(spec, "PUT", endpoint);
//            Serenity.setSessionVariable("response").to(response);
//        } catch (Exception e) {
//            e.getMessage();
//        }
//    }
//
//    /**
//     * Performs a DELETE operation with an ID provided by parameter from the scenario
//     *
//     * @param id The ID of a user
//     */
//    @Step
//    public void deleteUserById(String id) {
//
////        String endpoint = getEndpoint() + "/" + id;
//        Response response = servicesSupport.executeRequest(spec, "DELETE", endpoint);
//        Serenity.setSessionVariable("response").to(response);
//    }
//
//    /**
//     * Method to verify an status code received from the scenario
//     *
//     * @param expectedStatusCode Expected status code in the response
//     */
//    @Step
//    public void verifyStatusCode(int expectedStatusCode) {
//
//        Response res = Serenity.sessionVariableCalled("response");
//        Assert.assertEquals("status code doesn't match", expectedStatusCode, res.getStatusCode());
//    }

    /**
     * Method to verify an status code received from the scenario
     *
     * @param res           Response object from a previous operation
     * @param operation     The operation that was done in a previous step received from Cucumber
     * @param key           Attribute name received from the scenario as a parameter
     * @param expectedValue Expected value of the attribute received from the scenario as a parameter
     */
    private void verifyValueFromKey(Response res, String operation, String key, String expectedValue) {

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

        Assert.assertEquals("Value for " + key + " doesn't match", expectedValue, currentValue);
    }
}