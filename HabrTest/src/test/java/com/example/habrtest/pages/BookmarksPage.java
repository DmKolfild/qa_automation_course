package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

// https://habr.com/en/users/<user name>/bookmarks/publications/
public class BookmarksPage {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(BookmarksPage.class));
    private final WebDriver driver;

    @FindBy(css = "h2 > a")
    private List<WebElement> titleArticle;

    public BookmarksPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle(int num) {
        return titleArticle.get(num).getText();
    }
}
