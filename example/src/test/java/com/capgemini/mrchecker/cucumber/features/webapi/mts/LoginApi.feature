@api @waiter
Feature: API Login
  As a waiter I want to login in order to get the access token

  Scenario Outline: Login as "<username>"
    Given The user is logged-out
    When I login with "<username>" and "<password>"
    Then The response code is "200"
    And  The "Authorization" header matches "Bearer [a-zA-Z0-9._\-]{359}"

    Examples:
      | username | password |
      | waiter   | waiter   |