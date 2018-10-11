@all
@Registration
Feature: Registration
Scenario: Register with known user credentials
Given I'm on the registration page as unlogged user
When I fill registration form with valid random data
Then I should see an error message: Error: Username already exists
Scenario: Register with known user credentials
Given I'm on the registration page as unlogged user
When I fill registration form with valid random data
Then I should see an error message: Error: Username already exists