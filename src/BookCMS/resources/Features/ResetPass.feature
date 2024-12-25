# Author: Phuong Anh
# Date: 27/08/2024
# Description: Reset Password screen testing scenario

@SmokeScenario
Feature: Reset Password page

  @ResetPass @UI
  Scenario: Case Reset password page UI
    Given Open reset password page with token
    When Reset password page UI
    When Reset password page title
    When Reset password logo
    When Reset password content
    When Reset password: Default password input
    When Reset password: Default confirm password input

  @ResetPass @InputPass
  Scenario Outline: Case Check password input
    Given Open reset password page with token
    And Reset pass: Click background screen
    And Click button Reset password
    And Reset pass: Click background screen
    Then Reset password: Pass format error message
    When Reset password: Enter password input value "<pass>"
    And Reset pass: Click background screen
    Then Reset password: Pass format error message

    Examples:
      | pass |
      | 123456789 |
      | Abc@123455555555555 |
      | Abc@123 |
      | dfdsd67675 |

  @ResetPass @InputConfirmPass
  Scenario Outline: Case Check confirm input
    Given Open reset password page with token
    And Reset pass: Click background screen
    And Click button Reset password
    And Reset pass: Click background screen
    Then Reset password: Confirm pass format error message
    When Reset password: Enter confirm password input value "<confirm>"
    And Reset pass: Click background screen
    Then Reset password: Confirm pass format error message

    Examples:
      | confirm |
      | dfdsfd |

  @ResetPass @EnterPassAndConfirmError
    Scenario Outline: Case check enter pass & confirm error
      Given Open reset password page with token
      When Reset password: Enter password input value "<pass>"
      When Reset password: Enter confirm password input value "<confirm>"
      And Reset pass: Click background screen
      Then Reset password: Confirm pass format error message

      Examples:
        | pass | confirm |
        | dfdsfd | 123 |

  @ResetPass @ClickLoginBack
  Scenario: Case Click link text Login back
    Given Open reset password page with token
    When Reset password: Default login back
    When Reset password: Click login back
    Then Reset password: Redirect to Login page


  @ResetPassSuccessful
  Scenario Outline: Case check reset pass successful
    Given Open reset password page with token
    When Reset password: Enter password input value "<pass>"
    When Reset password: Enter confirm password input value "<confirm>"
    And Reset pass: Click background screen
    And Click button Reset password
    Then Reset password successful

    Examples:
      | pass | confirm |
      | Abc@123456 | Abc@123456 |

  @LoginWithPassChanged
  Scenario Outline: Case check login with pass changed
    Given Open Chrome and login page
    When Login: Default email input
    When Login: Enter email input value "<e>"
    When Login: Default password input
    When Login: Enter password input value "<p>"
    And Click button Login
    Then Login successful
    And Login: Close browser

    Examples:
      | e | p |
      | phuonganh.tran@vmgmedia.vn | Abc@123456 |