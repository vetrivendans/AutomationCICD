#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Incorrect credentials error validation
  I want to use this template for my feature file

  @ErrorValidations
  Scenario Outline: Incorrect password should throw an error message
    Given I landed on Ecommerce Page
    When Logged in with <userName> and <password> to application
    Then I verify the error "Incorrect email or password." message is displayed

    Examples: 
      | userName  				| password 						|
      |testing@user.com		|invalidPassword			|
