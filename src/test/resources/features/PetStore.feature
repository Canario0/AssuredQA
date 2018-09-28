Feature: User Cases of the Pet Store Example

  Scenario Outline: POST a new pet for the store
    When I request to do post operation with "<field>" to "<value>"
    And with body "/requests/put_pet.json" including
      | id   | 19956    |
      | name | Pikotaro |
    Then I should get <expectedStatusCode> status code
    And response body contains
      | id   | 19956    |
      | name | Pikotaro |
    Examples:
      | field        | value            | expectedStatusCode |
      | Content-Type | application/json | 200                |
