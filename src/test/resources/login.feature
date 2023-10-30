Feature: Login Test

  @test
  Scenario Outline: Login with Credentials
    When user enters invalid username "<username>" and password "<password>"
    Then Click Login Button
    And Verify user gets Error Message "<error>"
    Examples:
      | username    | password    | error                                                        |
      | DRKEPKROPEK | EJROWEOW@12 | Username and password do not match any user in this service. |

