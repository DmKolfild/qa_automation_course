package com.example.serchselenium;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest(name = "#{index} - Find search in {0} ")
    @ValueSource(strings = {"https://www.bing.com/", "https://www.google.com/"})
    @DisplayName("Search availability")
    public void search(String url) {
        driver.get(url);

        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("[type='search']"));
        searchField.sendKeys(input);
        searchField.submit();

        WebElement searchPageField = driver.findElement(By.cssSelector("[type='search']"));
        assertEquals(input, searchPageField.getAttribute("value"));
    }

    @Test
    @DisplayName("Relevance of the search results")
    public void relevanceSearch() {
        driver.get("https://www.bing.com/");

        String input = "Selenium";
        WebElement searchField = driver.findElement(By.cssSelector("[type='search']"));
        searchField.sendKeys(input);
        searchField.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(By.cssSelector("h2 > a[href]"), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(By.cssSelector("h2 > a[href]"))
        ));
        List<WebElement> results = driver.findElements(By.cssSelector("h2 > a[href]"));
        clickElement(results, 0);

        //Switch to window
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        String url = driver.getCurrentUrl();

        assertEquals("https://www.selenium.dev/", url, "Search results are not relevant");
    }

    public void clickElement(List<WebElement> results, int num) {
        results.get(num).click();
    }
}
