package com.example.serchselenium.pages;

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

public class ResultsPageBing {

    private final WebDriver driver;

    @FindBy(css = "[type='search']")
    private WebElement searchField;

    @FindBy(css = "h2 > a[href]")
    private List<WebElement> results;

    public ResultsPageBing(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Click on the search result item and open this page
    public void clickElement(int num)  {
        driver.navigate().refresh();
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(results.get(num))));
        results.get(num).click();

        //Switch to window
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        System.out.printf("Click on the %d results: \n",  num);
    }

    // Get text from the search field
    public String getTextFromSearchField() {
        String value = searchField.getAttribute("value");
        System.out.println("Text in the search field: " + value);
        return value;
    }

}
