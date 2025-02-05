package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

// https://habr.com/ru/news/<№ news>/
public class NewsPage {
    //private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(NewsPage.class));
    private final WebDriver driver;

    @FindBy(css = "h1.tm-title_h1")
    private WebElement newsHeader;

    public NewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderNews() {
        //LOG.info("Получение заголовка новости");
        return newsHeader.getText();
    }

}
