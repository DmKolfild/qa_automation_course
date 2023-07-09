package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

// https://habr.com/ru/search/?<поисковая информация>
public class SearchPage {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(SearchPage.class));
    private final WebDriver driver;

    @FindBy(css = "input[placeholder=\"Поиск\"]")
    private WebElement inputSearch;

    @FindBy(xpath = "//*[contains(text(), 'К сожалению, здесь пока нет ни одной публикации')]")
    private List<WebElement> message;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean getMessage() {
        LOG.info("Получение информации с сообщением об отсутвии публикаций");
        return message.isEmpty();
    }

    public void search(String text) {
        LOG.info("Поиск по полученному тексту");
        inputSearch.click();
        inputSearch.sendKeys(text);
        inputSearch.submit();
    }

}
