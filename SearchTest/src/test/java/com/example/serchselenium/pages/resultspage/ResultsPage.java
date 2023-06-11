package com.example.serchselenium.pages.resultspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public interface ResultsPage {
    // Click on the search result item and open this page
    void clickElement(int num);

    // Get text from the search field
    String getTextFromSearchField();

}
