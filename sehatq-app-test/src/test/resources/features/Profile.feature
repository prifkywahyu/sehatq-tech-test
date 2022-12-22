@profile
Feature: Edit Profile
  All scenarios for edit profile module

  @positive
  Scenario: User success to edit profile data
    Given user success to logged in
    When user already in edit profile page
    And user edit full name, height, weight and address
    And user click button save profile
    Then user is successfully saved new profile

  @negative
  Scenario Outline: User failed to edit profile data
    Given user success to logged in
    When user already in edit profile page
    And user edit full name "<name>", height "<height>", weight "<weight>" and address "<address>"
    And user click button save profile
    Then user failed edit profile with error message
    Examples:
      | name        | height  | weight  | address                 |
      |             | 190     | 82      | Jalan Terus Saja No. 19 |
      | Mam4n Do!y  | 190     | 82      | Jalan Terus Saja No. 19 |
      | Zulkarnaen  |         | 59      | Jalan Terus Saja No. 19 |
      | Zulkarnaen  | 1350    | 59      | Jalan Terus Saja No. 19 |
      | Abraham     | 182     |         | Jalan Terus Saja No. 19 |
      | Abraham     | 201     | 1350    | Jalan Terus Saja No. 19 |
      | Udin Zaman  | 182     | 91      |                         |
      | Sudrajat    | 166     | 78      | Di                      |