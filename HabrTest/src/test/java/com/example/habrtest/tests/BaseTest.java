package com.example.habrtest.tests;

import com.example.habrtest.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() throws InterruptedException {
        getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static WebDriver getDriver() {
        if (driver == null)
            createDriver();
        if (driver.toString().contains("(null)"))
            createDriver();
        return driver;
    }

    private static void createDriver() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    public static String getUrlWithoutHash() {
        String url = driver.getCurrentUrl();
        assert url != null;
        int endIndex = url.length();
        if (url.contains("#"))
            endIndex = url.indexOf("#");
        return driver.getCurrentUrl().substring(0, endIndex);
    }

    public BookmarksPage getBookmarksPage() {
        return new BookmarksPage(getDriver());
    }

    public MainPage getMainPage() {
        return new MainPage(getDriver());
    }

    public AuthorizationPage getAuthorizationPage() {
        return new AuthorizationPage(getDriver());
    }

    public NewsPage getNewsPage() {
        return new NewsPage(getDriver());
    }

    public AllNewsPage getAllNewsPage() {
        return new AllNewsPage(getDriver());
    }

    public SearchPage getSearchPage() {
        return new SearchPage(getDriver());
    }

    public SettingsProfilePage getSettingsProfilePage() {
        return new SettingsProfilePage(getDriver());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
