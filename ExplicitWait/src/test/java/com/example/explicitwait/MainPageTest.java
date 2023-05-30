package com.example.explicitwait;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void visible() {
        // Open website
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        // Click on the button when the button is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#visibleAfter")));
        WebElement visibleButton = driver.findElement(By.cssSelector("#visibleAfter"));
        visibleButton.click();

        assertTrue(visibleButton.isDisplayed(), "The button wasn't found");
    }

    @Test
    public void price() throws InterruptedException {
        // Open website
        driver.get("http://suninjuly.github.io/explicit_wait2.html");

        // Click on the button when the price is equal to 100$
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("price"), "$100"));
        WebElement buttonBook = driver.findElement(By.id("book"));
        buttonBook.click();

        // Get the input value for calculation
        String calcValue = driver.findElement(By.cssSelector("#input_value")).getText();
        String answer = calculate(calcValue);

        // Send the answer
        WebElement inputAnswer = driver.findElement(By.cssSelector("#answer"));
        inputAnswer.sendKeys(answer);
        WebElement buttonSubmit = driver.findElement(By.cssSelector("#solve"));
        buttonSubmit.click();

        // Get a good message from the alert form
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();

        assertTrue(message.contains("Congrats, you've passed the task!"), "It's the wrong answer!");
    }

    // Function for the calculation
    public String calculate (String value){
        return String.valueOf(Math.log(Math.abs(12 * Math.sin(Double.parseDouble(value)))));
    }
}
