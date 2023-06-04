package com.example.serchselenium.tests;

import com.example.serchselenium.pages.MainPageGoogle;
import com.example.serchselenium.pages.ResultsPageGoogle;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class SearchGoogleTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.google.ru/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Search availability")
    public void searchFieldTest() {
        // Search result using text
        String input = "Selenium";
        MainPageGoogle mp = new MainPageGoogle(driver);
        mp.sentText(input);

        ResultsPageGoogle rp = new ResultsPageGoogle(driver);

        assertEquals(input, rp.getTextFromSearchField(), "The text didn't match");
    }

    @Test
    @DisplayName("Relevance of the search results")
    public void relevanceSearchTest() {
        // Search result using text
        String input = "Selenium";
        MainPageGoogle mp = new MainPageGoogle(driver);
        mp.sentText(input);

        // Open a page
        ResultsPageGoogle rp = new ResultsPageGoogle(driver);
        rp.clickElement(0);

        String url = driver.getCurrentUrl();

        assertEquals("https://www.selenium.dev/", url, "Search results are not relevant");
    }

}
