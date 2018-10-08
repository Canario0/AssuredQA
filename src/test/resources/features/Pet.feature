@ALL @PET
Feature: User Cases of the Pet Store Example

  @JSON @POST
  Scenario Outline: POST a new pet for the store
    When I request to do POST operation with "<field>" to "<value>"
    And with URI "/pet"
    And with body "/requests/post/json/post_pet_<expectedStatusCode>.json"
    Then I should get <expectedStatusCode> status code
    And response body equals to "/requests/post/json/expected_post_pet_<expectedStatusCode>.json"
    Examples:
      | field        | value            | expectedStatusCode |
      | Content-Type | application/json | 200                |
      | Content-Type | application/json | 400                |
      | Content-Type | application/json | 500                |

  @JSON @PUT
  Scenario Outline: PUT a new pet for the store
    When I request to do PUT operation with "<field>" to "<value>"
    And with URI "/pet"
    And with body "/requests/post/json/post_pet_<expectedStatusCode>.json"
    Then I should get <expectedStatusCode> status code
    And response body equals to "/requests/post/json/expected_post_pet_<expectedStatusCode>.json"
    Examples:
      | field        | value            | expectedStatusCode |
      | Content-Type | application/json | 200                |
      | Content-Type | application/json | 400                |
      | Content-Type | application/json | 500                |

  @JSON @GET
  Scenario Outline: Get all pets with given status
    When I request to do GET operation with "<field>" to "<value>"
    And with URI "/pet"
    And find all the pets with status "<statusValue>"
    Then I should get <expectedStatusCode> status code
    And is not empty
    Examples:
      | field        | value            | statusValue | expectedStatusCode |
      | Content-Type | application/json | available   | 200                |
      | Content-Type | application/json | sold        | 200                |
      | Content-Type | application/json | pending     | 200                |

  @JSON @DELETE
  Scenario Outline: Delete pet with given id
    When I request to do DELETE operation with "<field>" to "<value>"
    And with URI "/pet"
    And delete pet with id equal to "<idValue>"
    Then I should get <expectedStatusCode> status code
    Examples:
      | field        | value            | idValue | expectedStatusCode |
      | Content-Type | application/json | 0       | 404                |
      | Content-Type | application/json | 50000    | 200                |
      | Content-Type | application/json | a       | 404                |


  @XML @POST
  Scenario Outline: POST a new pet for the store
    When I request to do POST operation with "<field>" to "<value>"
    And with URI "/pet"
    And with body "/requests/post/xml/post_pet_<expectedStatusCode>.xml"
    Then I should get <expectedStatusCode> status code
    And response body equals to "/requests/post/xml/expected_post_pet_<expectedStatusCode>.xml"
    Examples:
      | field        | value           | expectedStatusCode |
      | Content-Type | application/xml | 200                |
      | Content-Type | application/xml | 400                |

  @XML @PUT
  Scenario Outline: PUT a new pet for the store
    When I request to do PUT operation with "<field>" to "<value>"
    And with URI "/pet"
    And with body "/requests/post/xml/put_pet_<expectedStatusCode>.xml"
    Then I should get <expectedStatusCode> status code
    And response body equals to "/requests/post/xml/expected_put_pet_<expectedStatusCode>.xml"
    Examples:
      | field        | value           | expectedStatusCode |
      | Content-Type | application/xml | 200                |
      | Content-Type | application/xml | 400                |

  @XML @GET
  Scenario Outline: Get all pets with given status
    When I request to do GET operation with "<field>" to "<value>"
    And with URI "/pet"
    And find all the pets with status "<statusValue>"
    Then I should get <expectedStatusCode> status code
    And is not empty
    Examples:
      | field        | value           | statusValue | expectedStatusCode |
      | Content-Type | application/xml | available   | 200                |
      | Content-Type | application/xml | sold        | 200                |
      | Content-Type | application/xml | pending     | 200                |

  @XML @DELETE
  Scenario Outline: Delete pet with given id
    When I request to do DELETE operation with "<field>" to "<value>"
    And with URI "/pet"
    And delete pet with id equal to "<idValue>"
    Then I should get <expectedStatusCode> status code
    Examples:
      | field        | value           | idValue | expectedStatusCode |
      | Content-Type | application/xml | 0       | 404                |
      | Content-Type | application/xml | 700000  | 200                |
      | Content-Type | application/xml | a       | 404                |