Feature: Hangman web page
  As a user I want to guess the letter for a word to eventually 
	guess the correct the word

  Scenario: Partially guess “cat”
    Given I am on the hangman page
    When I enter "abc"
    Then I see the pattern "ca."

  Scenario: Update old guesses
    Given I am on the hangman page
    When I enter "abc"
    Then I see old guesses contain "abc"
