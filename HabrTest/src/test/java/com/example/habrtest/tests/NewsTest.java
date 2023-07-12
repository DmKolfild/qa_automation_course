package com.example.habrtest.tests;

import com.example.habrtest.MyExtension;
import com.example.habrtest.pages.AllNewsPage;
import com.example.habrtest.pages.MainPage;
import com.example.habrtest.pages.NewsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class NewsTest extends BaseTest {
    private MainPage mainPage;
    private NewsPage newsPage;
    private AllNewsPage allNewsPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.habr.com/");
        mainPage = new MainPage(getDriver());
        newsPage = new NewsPage(getDriver());
        allNewsPage = new AllNewsPage(getDriver());
    }

    @Test
    @DisplayName("Соответсвие превью новости c главной страницы и заголовка на странице новости")
    public void comparePreviewOnTheMainPageAndHeaderOfNews() {
        int numberOfNews = 0;
        String newsHeader = mainPage.getHeaderNews(numberOfNews);
        mainPage.clickNews(numberOfNews);
        assertEquals(newsHeader, newsPage.getHeaderNews(), "Заголовки не соответствуют");
    }

    @Test
    @DisplayName("Соответсвие превью новости cо страницы новостей и заголовка на странице новости")
    public void comparePreviewOnTheNewsPageAndHeaderOfNews() {
        int numberOfNews = 0;
        mainPage.clickNewsButton();
        String newsHeader = allNewsPage.getHeaderNews(numberOfNews);
        allNewsPage.clickNews(numberOfNews);
        assertEquals(newsHeader, newsPage.getHeaderNews(), "Заголовки не соответствуют");
    }

    @Test
    @DisplayName("Кнопка 'Новости' перенарправляет на страницу со всеми новостями")
    public void checkIfUrlOfAllNewsPageIsCorrect() {
        mainPage.clickNewsButton();
        String url = getUrlWithoutHash();
        assertEquals(url, "https://habr.com/ru/news/", "Url страницы некорректен");
    }

    @Test
    @DisplayName("Кнопка 'Все новости' перенаправляет на страницу со всеми новостями")
    public void checkIfRedirectionForAllNewsButtonIsCorrect() {
        mainPage.clickNextNewsButton();
        mainPage.clickAllNewsButton();
        String url = getUrlWithoutHash();
        assertEquals(url, "https://habr.com/ru/news/", "Url страницы некорректен");
    }

}
