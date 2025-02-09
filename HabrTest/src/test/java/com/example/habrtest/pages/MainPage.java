package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

//https://habr.com/ru/all/
public class MainPage {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(MainPage.class));
    private final WebDriver driver;

    @FindBy(css = "#news_block_1 h3 a")
    private List<WebElement> newsHeader;

//    @FindBy(css = "svg[data-test-id='menu-toggle-guest']")
//    private WebElement iconProfileButtonWithoutAuthorization;

    @FindBy(css = "[data-test-id='menu-toggle-user']")
    private WebElement iconProfileButton;

    @FindBy(xpath = "//*[contains(text(), \"Войти\")]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[contains(text(), \"Закладки\")]")
    private WebElement bookmarksPageButton;

    @FindBy(css = "[href=\"/ru/settings/profile/\"]")
    private WebElement settingsProfileButton;

    @FindBy(css = "[href='/ru/search/']")
    private WebElement searchButton;

    @FindBy(css = "a[href=\"/ru/news/\"]")
    private WebElement newsButton;

    @FindBy(xpath = "//*[contains(text(), \"Показать еще\")]")
    private WebElement nextNewsButton;

    @FindBy(xpath = "//*[contains(text(), \"Все новости\")]")
    private WebElement allNewsButton;

    @FindBy(css = ".tm-user-menu a[class*=\"username\"]")
    private WebElement userName;

    @FindBy(css = "h2 > a")
    private List<WebElement> titleArticle;

    @FindBy(css = "[title=\"Добавить в закладки\"]")
    private List<WebElement> addBookmarkButtons;

    @FindBy(css = "[title=\"Убрать из закладок\"]")
    private List<WebElement> removeBookmarkButtons;

    @FindBy(css = ".tm-rss-button")
    private WebElement rssButton;

    @FindBy(css = "[role=\"alert\"]")
    private WebElement alert;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHeaderNews(int num) {
        LOG.info("Получение заголовка новости с номером: " + num);
        return newsHeader.get(num).getText();
    }

    public String getTitleOfArticle(int num) {
        LOG.info("Получение заголовка статьи с номером: " + num);
        return titleArticle.get(num).getText();
    }

    public void addArticleInBookmarks(int num) {
        LOG.info("Добавление в закладки статьи с номером: " + num);
        // Если статья уже в закладках, то убираем её оттуда и снова добавляем, чтобы она была на верху списка
        if (addBookmarkButtons.get(num).isEnabled())
            addBookmarkButtons.get(num).click();
        else {
            removeBookmarkButtons.get(num).click();
            addBookmarkButtons.get(num).click();
        }
    }

    public void clickNews(int num) {
        LOG.info("Клик по заголовку новости с номером " + num);
        newsHeader.get(num).click();
    }

    public void clickRss() {
        LOG.info("Клик по кнопке 'Скопировать ссылку на RSS'");
        rssButton.click();
    }

    public void clickIconProfile() {
        LOG.info("Клик по иконке профиля");
        iconProfileButton.click();
    }

    public void clickSettingsProfile() {
        LOG.info("Клик по настройкам профиля");
        settingsProfileButton.click();
    }

    public void clickBookmarksPageButton() {
        LOG.info("Клик по кнопке 'Закладки'");
        bookmarksPageButton.click();
    }

    public void clickSignIn() {
        LOG.info("Клик по кнопке 'Войти'");
        signInButton.click();
    }

    public void clickNewsButton() {
        LOG.info("Клик по кнопке 'Новости'");
        newsButton.click();
    }

    public void clickNextNewsButton() {
        LOG.info("Клик по кнопке 'Показать еще'");
        nextNewsButton.click();
    }

    public void clickSearchButton () {
        LOG.info("Клик по кнопке поиска");
        searchButton.click();
    }

    public void clickAllNewsButton() {
        LOG.info("Клик по кнопке 'Все новости'");
        allNewsButton.click();
    }

    public String getUserName() {
        LOG.info("Получение имени пользователя");
        return userName.getText();
    }

    public String getMessageFromAlert() {
        LOG.info("Получение сообщения из всплывающего окошка alert");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(alert));
        return alert.getText();
    }

}
