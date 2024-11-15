@WebLogin
Feature: Login functionality

  Scenario Outline: Test login with various credentials
    Given User opens the login page
    When User types username <username> into the Username field
    And User types password <password> into the Password field
    And User clicks the Submit button
    Then User verifies the message <expected_message> is displayed

    Examples: 
      | username      | password          | expected_message          |
      | student       | Password123       | Logged In Successfully    |
      | incorrectUser | Password123       | Your username is invalid! |
      | student       | incorrectPassword | Your password is invalid! |
