package com.example.serchselenium.pages.mainpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// https://www.bing.com/
public class MainPageBing implements MainPage {

    public MainPageBing(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[type='search']")
    private WebElement searchField;

    // Search for entered information
    public void sentText(String text) {
        System.out.println("Search for entered information in Bing");
        searchField.sendKeys(text);
        searchField.submit();
        System.out.println("Entered the text: " + text);
    }

}
