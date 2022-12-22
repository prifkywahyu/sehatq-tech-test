@login
Feature: Login
  All scenarios for login module

  @positive
  Scenario: User success login to SehatQ app
    Given user already in login page
    When user input email "uzumakiencep@gmail.com" and password "testing123"
    And user click button login
    Then user is successfully logged in

  @negative
  Scenario Outline: User failed login to SehatQ app
    Given user already in login page
    When user input email "<email>" and password "<password>"
    And user click button login
    Then user failed to login with error message
    Examples:
      | email                 | password  |
      | prifkywahyu@gmail.com | As4!asal  |
      | emailsaja@gmail.com   | p4Duka*$  |
      | testing.new@gmail.com | pass      |
      | @gmail.com            | Merauke1  |