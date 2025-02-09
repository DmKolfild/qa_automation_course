package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

// https://habr.com/ru/news/
public class AllNewsPage {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AllNewsPage.class));
    private final WebDriver driver;

    @FindBy(css = "h2 a")
    private List<WebElement> newsHeader;

    public AllNewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderNews(int num) {
        LOG.info("Получение заголовка новости с номером: " + num);
        return newsHeader.get(num).getText();
    }

    public void clickNews(int num) {
        LOG.info("Клик по заголовку новости с номером " + num);
        newsHeader.get(num).click();
    }

}
