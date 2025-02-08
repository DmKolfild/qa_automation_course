package com.example.serchselenium.pages.resultspage;

public interface ResultsPage {
    // Click on the search result item and open this page
    void openThePage(int num);

    // Get text from the search field
    String getTextFromSearchField();

}
