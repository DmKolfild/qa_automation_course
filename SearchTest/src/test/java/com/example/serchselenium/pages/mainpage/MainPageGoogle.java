package com.example.serchselenium.pages.mainpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// https://www.google.com/
public class MainPageGoogle implements MainPage {
    private final Logger LOG = LoggerFactory.getLogger(MainPageGoogle.class);

    public MainPageGoogle(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='q']")
    private WebElement searchField;

    public void sentText(String text) {
        LOG.info("Search for entered information in Google");
        searchField.sendKeys(text);
        searchField.submit();
        LOG.info("Entered the text: {}", text);
    }

}
