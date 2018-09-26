Feature: Basic API feature

#  @influxdb
  Scenario Outline: Get an existing user
    When I request to get a user by ID "<userID>"
    Then I should get <expectedStatusCode> status code
    And The value for the "<key>" after get operation should be "<value>"

    Examples:
      | userID | key  | value    | expectedStatusCode |
      | 2      | name | Whiskers | 200                |
      | 3      | name | Rover    | 200                |

  Scenario Outline: Get an existing user
    When I request to get a user by ID "<userID>"
    Then I should get <expectedStatusCode> status code

    Examples:
      | userID | expectedStatusCode |
      | 2      | 200                |
      | 3      | 200                |
      | 4      | 404                |
      | 5      | 404                |