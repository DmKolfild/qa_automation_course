package com.example.serchselenium.tests;

import com.example.serchselenium.pages.MainPageBing;
import com.example.serchselenium.pages.ResultsPageBing;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class SearchBingTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.bing.com/");
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
        MainPageBing mp = new MainPageBing(driver);
        mp.sentText(input);

        ResultsPageBing rp = new ResultsPageBing(driver);

        assertEquals(input, rp.getTextFromSearchField(), "The text didn't match");
    }

    @Test
    @DisplayName("Relevance of the search results")
    public void relevanceSearchTest() {
        // Search result using text
        String input = "Selenium";
        MainPageBing mp = new MainPageBing(driver);
        mp.sentText(input);

        // Open a page
        ResultsPageBing rp = new ResultsPageBing(driver);
        rp.clickElement(0);

        String url = driver.getCurrentUrl();

        assertEquals("https://www.selenium.dev/", url, "Search results are not relevant");
    }

}
