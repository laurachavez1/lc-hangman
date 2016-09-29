package feature.webclient;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HangmanWebclientSteps {
    WebDriver driver = new ChromeDriver();

    @After
    public void tearDown() {
        driver.quit(); // close browser
    }

    @Given("^I am on the (\\w+) page$")
    public void i_am_on_the_hangman_page(String pageName) throws Throwable {
        driver.get("http://localhost:8080/" + pageName + ".html");
    }

    @When("^I enter \"([^\"]*)\"$")
    public void i_enter(String guess) throws Throwable {
        driver.findElement(By.id("newGuesses")).sendKeys(guess);
        driver.findElement(By.id("submit")).click();
    }

    @Then("^I see the pattern \"([^\"]*)\"$")
    public void i_see_updated_pattern(String pattern) throws Throwable {
        // The greeting is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.textToBe(By.id("pattern"), pattern));
    }

    @Then("^I see old guesses contain \"([^\"]*)\"$")
    public void i_see_updated_old_guesses(String guesses) throws Throwable {
        // The greeting is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10))
            .until(ExpectedConditions.textToBePresentInElementLocated(By.id("oldGuesses"), guesses));
    }
}
