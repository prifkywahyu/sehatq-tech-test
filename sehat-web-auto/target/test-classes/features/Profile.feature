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
      | *$#!        | 178     | 68      | Jalan Terus Saja No. 19 |
      | 1380        | 190     | 82      | Jalan Terus Saja No. 19 |
      | K           | 167     | 61      | Jalan Terus Saja No. 19 |
      | Feriyanto   | Kamu    | 70      | Jalan Terus Saja No. 19 |
      | Budiman     | ()^^$   | 65      | Jalan Terus Saja No. 19 |
      | Jamaluddin  | 9       | 55      | Jalan Terus Saja No. 19 |
      | Zulkarnaen  | 1350    | 59      | Jalan Terus Saja No. 19 |
      | Menardik    | 182     | Kamu    | Jalan Terus Saja No. 19 |
      | Bambang     | 165     | ()^^$   | Jalan Terus Saja No. 19 |
      | Tomori      | 159     | 9       | Jalan Terus Saja No. 19 |
      | Abraham     | 201     | 1350    | Jalan Terus Saja No. 19 |
      | Udin Zaman  | 182     | 91      |                         |
      | Sudrajat    | 166     | 78      | Di                      |