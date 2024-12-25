# Author: Phuong Anh
# Date: 22/09/2024
# Description: Person popup testing scenario

@SmokeScenario
Feature: Person Popup UI & function

  @PersonPopup @UI
  Scenario: Check person popup UI
    Given Popup Person: Open popup
    When Popup person UI
    Then Popup person UI: User
    Then Popup person UI: Reset password
    Then Popup person UI: Button Logout

  @PersonPopup @ClickScreen
  Scenario: Check click out of Person popup
    Given Popup Person: Open popup
    When Popup Person: Click background screen
    Then Close popup person

  @PersonPopup @ClickResetPass
  Scenario: Check open popup Reset pass
    Given Popup Person: Open popup
    When Popup person: Click Reset password
    Then Popup Reset password UI

  @PersonPopup @ClickLogout
  Scenario: Check click and logout web
    Given Popup Person: Open popup
    When Popup person: Click button Logout
    Then Logout successful

  @ResetPassPopup @UI
  Scenario: Popup Reset pass: Check UI
    Given Popup Person: Open popup
    When Popup person: Click Reset password
    And Popup Reset pass: Click popup
    Then Popup Reset password UI
    Then Popup Reset password detail UI

  @ResetPassPopup @FocusInput
  Scenario: Popup Reset pass: Check focus input
    Given Popup Person: Open popup
    When Popup person: Click Reset password
    And Password current: Click input
    Then Password current: Check input
    When Password new: Click input
    Then Password new: Check input
    When Confirm pass: Click input
    Then Confirm pass: Check input

  @ResetPassPopup @RequiredInput
  Scenario: Popup Reset pass: Check required input
    Given Popup Person: Open popup
    When Popup person: Click Reset password
    And Popup Reset pass: Click button Reset pass
    Then Password current: Check required input
    Then Password new: Check required input
    Then Confirm pass: Check required input

  @ResetPassPopup @EnteredInput
  Scenario Outline: Popup Reset pass: Check entered input
    Given Popup Person: Open popup
    And Popup person: Click Reset password
    When Password current: Check entering "<p1>" into input
    And Password new: Check entering "<p2>" into input
    And Confirm pass: Check entering "<p3>" into input

    Examples:
      | p1 | p2 | p3 |
      | gsdfsdfhjkhg | hfhsgfask | sjkyuie |
  
  @ResetPassPopup @HideInput
  Scenario Outline: Popup Reset pass: Check input click hidden button
    Given Popup Person: Open popup
    And Popup person: Click Reset password
    When Password current: Click icon hide
    Then Password current: Check input "<p1>" when clicking hidden button
    And Password new: Check input "<p2>" when clicking hidden button
    When Confirm pass: Click icon hide
    And Confirm pass: Check input "<p3>" when clicking hidden button
    

    Examples:
      | p1 | p2 | p3 |
      | gsdfsdfhjkhg | hfhsgfask | sjkyuie |

  @ResetPassPopup @PassCurrentIncorrect
  Scenario Outline: Popup Reset pass: Password current incorrect
    Given Popup Person: Open popup
    And Popup person: Click Reset password
    When Popup Reset pass: Enter "<p1>" input password current
    And Popup Reset pass: Enter "<p2>" input password new
    And Popup Reset pass: Enter "<p2>" input confirm
    And Popup Reset pass: Click button Reset pass
    Then Password current: Incorrect password

    Examples:
      | p1 | p2 |
      | hgfsadh6555 | Abc@123456 |

  @ResetPassPopup @FuntionPerformedSuccessfully
  Scenario Outline: Popup Reset pass: function performed successfully
    Given Popup Person: Open popup
    And Popup person: Click Reset password
    When Popup Reset pass: Enter "<p1>" input password current
    And Popup Reset pass: Enter "<p2>" input password new
    And Popup Reset pass: Enter "<p2>" input confirm
    And Popup Reset pass: Click button Reset pass
    Then Popup Reset pass: function performed successfully
    And Popup Person: Close browser

    Examples:
      | p1 | p2 |
      | Abc@123456 | Pa@123456 |