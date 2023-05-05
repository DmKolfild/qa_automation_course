package com.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.habr.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void newsSimilarHeaders() {
        // Scroll to the news block
        WebElement element = driver.findElement(By.id("news_block_1"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Get the first news
        WebElement news = driver.findElement(By.cssSelector("#news_block_1 li:first-child h3"));
        // Get the header of this news
        String newsHeader = news.getText();
        // Click on this news
        news.click();

        // Get the header of the article
        WebElement headerNews = driver.findElement(By.cssSelector("h1.tm-title_h1"));
        assertEquals(newsHeader, headerNews.getText(), "Headers are not equal");
    }

    @Test
    public void signInIncorrectEmail() {
        // Find the profile icon and click
        WebElement iconProfileButton = driver.findElement(By.cssSelector("svg[data-test-id=\"menu-toggle-guest\"]"));
        iconProfileButton.click();

        // Find the button "Войти"
        WebElement loginButton = driver.findElement(By.xpath("//*[contains(text(), \"Войти\")]"));
        loginButton.click();

        // Enter email and submit
        WebElement email = driver.findElement(By.id("email_field"));
        email.sendKeys("123");
        email.submit();

        List<WebElement> massageIncorrectEmail = driver.findElements(By.xpath("//*[contains(text(), \"Введите корректный e-mail\")]"));
        assertFalse(massageIncorrectEmail.isEmpty(), "Massage 'Введите корректный e-mail' isn't fought");
    }

}
