@ALL @STORE
Feature: User Cases of the Store Example

  @JSON @GET
  Scenario Outline: GET inventory of the store
    When I request to do GET operation with "<field>" to "<value>"
    And with URI "/store"
    And with path "/inventory"
    Then I should get <expectedStatusCode> status code
    And response contains "sold"
    Examples:
      | field        | value            | expectedStatusCode |
      | Content-Type | application/json | 200                |
#      | Content-Type | application/json | 400                |
#      | Content-Type | application/json | 500                |
  @JSON @POST
  Scenario Outline: POST a new order for the store
    When I request to do POST operation with "<field>" to "<value>"
    And with URI "/store/order"
    And with body "/requests/post/json/post_order_<expectedStatusCode>.json"
    Then I should get <expectedStatusCode> status code
    And response body equals to "/requests/post/json/expected_post_order_<expectedStatusCode>.json"
    Examples:
      | field        | value            | expectedStatusCode |
      | Content-Type | application/json | 200                |
      | Content-Type | application/json | 400                |