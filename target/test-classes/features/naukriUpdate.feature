Feature: Automate Naukri Profile Update

  @LocationUpdate
  Scenario: Update Location Preference
    Given I navigate to the Naukri login page
    When I enter username and password
    And I update the location preference
    Then My profile should be updated successfully

    
  @ResumeUpdate
  Scenario: Upload a New Resume
    Given I Scroll till i find Upload Container
    And I upload a new resume
    Then My profile should be updated successfully
    