package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

// https://habr.com/ru/news/
public class AllNewsPage {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllNewsPage.class));
    private final WebDriver driver;

    public AllNewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
