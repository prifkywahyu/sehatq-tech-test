@register
Feature: Register
  All scenarios for register module

  @positive
  Scenario: User success register to SehatQ app
    Given user already in register page
    When user input full name, email and password
    And user check terms and conditions
    And user click button register
    Then user success redirect to OTP page

  @negative
  Scenario Outline: User failed register to SehatQ app
    Given user already in register page
    When user input full name "<name>", email "<email>" and password "<pass>"
    And user check terms and conditions
    And user click button register
    Then user failed to register with error message
    Examples:
      | name          | email                   | pass        |
      |               | new.email@sehatq.id     | in!Passw0rd |
      | Bara Nuruddin |                         | in!Passw0rd |
      | Bara Nuruddin | new.email@              | in!Passw0rd |
      | Bara Nuruddin | new.email@sehatq.id     |             |
      | Bara Nuruddin | new.email@sehatq.id     | P4s!        |
      | Bara Nuruddin | uzumakiencep@gmail.com  | in!Passw0rd |