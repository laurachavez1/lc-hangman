package edu.csumb.hangman.webclient;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * User story:
 *
 *   As a user I want to guess the letter for a word to eventually guess the correct the word
 *
 * Class name ends in IT so it runs with "mvn integration-test", not "mvn test".
 */
public class HangmanIT {
    // Requires chromedriver. See:
    // http://docs.seleniumhq.org/docs/03_webdriver.jsp#chromedriver
    // On Mac you can install it with "brew install googledriver" if you have Homebrew.
    WebDriver driver = new ChromeDriver();

    @Before
    public void setUp() {
        driver.get("http://localhost:8080/hangman.html");
    }

    @After
    public void tearDown() {
        driver.quit(); // close browser
    }

    
    /**
     * Acceptance test:
     * 
     *    Given I am on the hangman page
     *    When I submit multiple letters at one time
     *    Then any letters that exist in 'pattern' should be updated accordingly
     *          as well as old guesses.
     *
     */
    @Test
    public void testAppendGuess(){
        driver.findElement(By.id("newGuesses")).sendKeys("abc");
        driver.findElement(By.id("submit")).click();
        
        // Given pattern = "cat"
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.textToBe(By.id("oldGuesses"), "abc"));
        ExpectedConditions.textToBe(By.id("pattern"), "ca.");
    }

}
