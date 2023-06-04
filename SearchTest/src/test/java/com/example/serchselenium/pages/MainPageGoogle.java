package com.example.serchselenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://www.google.com/
public class MainPageGoogle {

    @FindBy(css = "[type='search']")
    private WebElement searchField;

    public MainPageGoogle(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Search for entered information
    public void sentText(String text) {
        searchField.sendKeys(text);
        searchField.submit();
        System.out.println("Entered the text: " + text);
    }

}
