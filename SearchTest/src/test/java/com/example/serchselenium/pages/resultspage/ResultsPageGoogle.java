package com.example.serchselenium.pages.resultspage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResultsPageGoogle implements ResultsPage {
    private final Logger LOG = LoggerFactory.getLogger(ResultsPageBing.class);

    private final WebDriver driver;

    @FindBy(css = "[name='q']")
    private WebElement searchField;

    @FindBy(css = "a > h3")
    private List<WebElement> results;

    public ResultsPageGoogle(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Click on the search result item and open this page
    @Override
    public void openThePage(int num) {
        LOG.info("Открытие {}-й страницы из поисковой выдачи",  num);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(results.get(num)));
        results.get(num).click();

        LOG.info("Switch the page");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        System.out.printf("Click on the %d results: \n", num);
    }

    // Get text from the search field
    @Override
    public String getTextFromSearchField() {
        LOG.info("Получение введенного текста из строки поиска");
        String value = searchField.getAttribute("value");
        LOG.info("Текст из строки поиска: {}", value);
        return value;
    }

}
