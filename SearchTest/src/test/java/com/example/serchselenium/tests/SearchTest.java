package com.example.serchselenium.tests;

import com.example.serchselenium.pages.MainPage;
import com.example.serchselenium.pages.ResultsPage;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @ParameterizedTest(name = "#{index} - Find search in {0} ")
    @ValueSource(strings = {"https://www.bing.com/", "https://www.google.com/"})
    @DisplayName("Search availability")
    public void searchFieldTest(String url) {
        driver.get(url);

        String input = "Selenium";

        MainPage mp = new MainPage(driver);
        mp.sentText(input);

        ResultsPage rp = new ResultsPage(driver);

        assertEquals(input, rp.getTextFromSearchField(), "The text didn't match");
    }

    @Test
    @DisplayName("Relevance of the search results")
    public void relevanceSearchTest() {
        driver.get("https://www.bing.com/");

        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sentText(input);

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);

        String url = driver.getCurrentUrl();

        assertEquals("https://www.selenium.dev/", url, "Search results are not relevant");
    }

}
