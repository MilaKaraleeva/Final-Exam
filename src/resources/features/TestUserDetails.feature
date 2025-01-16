Feature: Test User Details

  Background:
   Given User is under login page

  Scenario: The user would like to navigate to the "Account Details" and verify the correct email was used.
    And The user is successfully logged in to the web page
    When The user navigates to Account Details
    And User Validate correct tab
    Then The email address displayed under the Email Address textbox matches the expected email
    And Clicks on the Logout button



  Scenario: The user would like to enter first and last names, make sure they are correct in the input boxes, and save.
    And The user is successfully logged in to the web page
    When The user navigates to Account Details
    And User enters valid first name in the First name textbox
    And User enters valid last name in the Last name textbox
    Then The first name displayed under the First name textbox matches the entered name
    And The last name displayed under the Last name textbox matches the entered name
    When User clicks on the Save changes button
    Then A success message is displayed confirming the changes were saved
    And Clicks on the Logout button


  Scenario: User logs out successfully
    And The user is successfully logged in to the web page
    When The user navigates to Account Details
    When Clicks on the Logout button
    Then User is redirected to the login page
