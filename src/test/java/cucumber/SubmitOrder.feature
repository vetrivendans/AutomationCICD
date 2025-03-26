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
Feature: Submit an order from Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @SubmitOrder
  Scenario Outline: Postive scenario to submit an order
    Given Logged in with <userName> and <password> to application
    When I add product <productName> to the cart
    And I checkout the <productName> from cart
    And I submit the order
    Then I verify the "Thankyou for the order." message is displayed

    Examples: 
      | userName  				| password 			| productName |
      |testing@user.com		|Password1			|ZARA COAT 3	|
