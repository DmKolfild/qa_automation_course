package com.example.habrtest.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static String getUrlWithoutHash(){
        String url = driver.getCurrentUrl();
        int endIndex = url.length();
        if (url.contains("#"))
            endIndex = url.indexOf("#");
        return driver.getCurrentUrl().substring(0, endIndex);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
