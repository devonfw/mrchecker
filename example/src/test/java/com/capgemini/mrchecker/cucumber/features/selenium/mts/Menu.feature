@all @mts @ui @guest
Feature: Browse the menu
  As a guest I want to browse the menu so that I can pick my meal.

  Background:
    Given The My Thai start page has been opened
    And The "Menu" has been opened

  Scenario Outline: Booking without accepting terms not possible
    When I type "<searchPhrase>" in the search box
    And I apply filters
    Then The search result contains meals with "<searchPhrase>"

    Examples:
      | searchPhrase |
      | chicken      |
      | basil        |
