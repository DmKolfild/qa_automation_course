package com.example.serchselenium.pages.mainpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// https://www.bing.com/
public class MainPageBing implements MainPage {
    private final Logger LOG = LoggerFactory.getLogger(MainPageBing.class);

    public MainPageBing(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[type='search']")
    private WebElement searchField;

    // Search for entered information
    public void sentText(String text) {
        LOG.info("Search for entered information in Bing");
        searchField.sendKeys(text);
        searchField.submit();
        LOG.info("Entered the text: {}", text);
    }

}
