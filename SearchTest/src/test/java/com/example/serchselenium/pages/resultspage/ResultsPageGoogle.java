package com.example.serchselenium.pages.resultspage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultsPageGoogle implements ResultsPage {

    private WebDriver driver;

    @FindBy(css = "[type='search']")
    private WebElement searchField;

    @FindBy(css = "a > h3")
    private List<WebElement> results;

    public ResultsPageGoogle(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Click on the search result item and open this page
    @Override
    public void clickElement(int num)  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(results.get(num)));
        results.get(num).click();

        //Switch to window
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        System.out.printf("Click on the %d results: \n",  num);
    }

    // Get text from the search field
    @Override
    public String getTextFromSearchField() {
        String value = searchField.getAttribute("value");
        System.out.println("Text in the search field: " + value);
        return value;
    }

}
