package com.example.serchselenium.pages.mainpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://www.google.com/
public class MainPageGoogle implements MainPage {

    public MainPageGoogle(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[type='search']")
    private WebElement searchField;

    public void sentText(String text) {
        System.out.println("Search for entered information in Google");
        searchField.sendKeys(text);
        searchField.submit();
        System.out.println("Entered the text: " + text);
    }

}
