@ALL @STORE
Feature: User Cases of the Store Example

  @JSON
  @GET
  Scenario Outline: GET inventory of the store
    When I request to do GET operation with "<field>" to "<value>"
    And with path "/inventory"
    Then I should get <expectedStatusCode> status code
    And response contains "sold"
    Examples:
      | field        | value            | expectedStatusCode |
      | Content-Type | application/json | 200                |
#      | Content-Type | application/json | 400                |
#      | Content-Type | application/json | 500                |