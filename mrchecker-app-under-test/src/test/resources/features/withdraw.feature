@all 
@Test 
Feature: Cash Withdrawal 
Scenario: Successful withdrawal from an account in credit 
    Given I have deposited $100 in my account 
    When I request $20 
    Then $20 should be dispensed 
    
Scenario: One more successful withdrawal from an account in credit 
    Given I have deposited $1000 in my account 
    When I request $1000 
    Then $0 should be dispensed 