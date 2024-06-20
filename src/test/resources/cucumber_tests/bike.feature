Feature: bike endpoints

  @WithAuthenticatedUser
  Scenario: should create bike
    When a call is made to /api/v1/bike endpoint with POST method and bike/create-request body
    Then the response status code is 201
    And bike exists in database

  @WithAuthenticatedUser
  Scenario Outline: should find bike
    When a call is made to /api/v1/bike?name=<name>&manufacturer=<manufacturer>&itemType=<itemType> endpoint with GET method
    Then the response status code is 200
    And response list contains data from file <expectedFile>

    Examples:
      | name | manufacturer | itemType | expectedFile                 |
      |      |              |          | bike/retrieve-response1.json |
      | Road | Cycles       | Wheel    | bike/retrieve-response1.json |
      | Road | Cycles       | Bell     | bike/retrieve-response2.json |