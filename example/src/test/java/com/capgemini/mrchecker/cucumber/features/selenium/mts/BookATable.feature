@MTS @ui @guest
Feature: Book a table
  As a guest I want to reserve a table so that I don't come to the restaurant in vain.

  Background:
    Given The My Thai start page has been opened
    And The "Book table" has been opened

  Scenario: Book a table: Booking without accepting terms not possible
    When I enter valid booking data
    And I do not accept the terms
    Then Booking a table is not possible

  Scenario Outline: Book a table for various number of persons
    When I enter valid booking information for a table for <number> persons
    And I accept the terms
    And I confirm the booking
    Then The table is successfully booked

    Examples:
      | number |
      | 1      |
      | 4      |
      | 8      |

  Scenario Outline: Book a table: incorrect input values in formular
    When I enter valid booking data
    And I accept the terms
    And I change <attribute> to <value>
    Then Booking a table is not possible

    Examples:
      | attribute | value     |
#      | email     |           |
      | email     | test      |
      | email     | test@test |
#      | name      |           |
      | persons   |           |
      | persons   | 0         |
      | persons   | 9         |
      | persons   | -1        |
#      | persons   | 2.3       |
      | persons   | X         |
