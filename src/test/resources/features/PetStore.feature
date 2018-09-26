Feature: User Cases of the Pet Store Example

  Scenario Outline: POST a new pet for the store
    When I request to put a new pet by "<petID>" and "<petName>"
    And set "<field>" to "<value>"
    And with "<body>"
    Then I should get <expectedStatusCode> status code
    And with "<responseID>" equals to "<petID>"
    And with "<responseName>" equals to "<petName>"
    Examples:
      | petID | petName | field | value | body | expectedStatusCode | responseID | responseName |


#    Examples:
#      | userID | key  | value    | expectedStatusCode |
#      | 2      | name | Whiskers | 200                |
#      | 3      | name | Rover    | 200                |

#  Scenario Outline: Get an existing user
#    When I request to get a user by ID "<userID>"
#    Then I should get <expectedStatusCode> status code
#
#    Examples:
#      | userID | expectedStatusCode |
#      | 2      | 200                |
#      | 3      | 200                |
#      | 4      | 404                |
#      | 5      | 404                |