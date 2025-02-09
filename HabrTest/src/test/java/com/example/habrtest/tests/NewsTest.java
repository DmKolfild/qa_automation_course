package com.example.habrtest.tests;

import com.example.habrtest.Helper;
import com.example.habrtest.MyExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class NewsTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");
    }

    @Test
    @DisplayName("Соответствие превью новости c главной страницы и заголовка на странице новости")
    public void comparePreviewOnTheMainPageAndHeaderOfNews() {
        int numberOfNews = 0;
        String newsHeader = getMainPage().getHeaderNews(numberOfNews);
        getMainPage().clickNews(numberOfNews);
        assertEquals(newsHeader, getNewsPage().getHeaderNews(), "Заголовки не соответствуют");
    }

    @Test
    @DisplayName("Соответствие превью новости cо страницы новостей и заголовка на странице новости")
    public void comparePreviewOnTheNewsPageAndHeaderOfNews() {
        int numberOfNews = 0;
        getMainPage().clickNewsButton();
        String newsHeader = getAllNewsPage().getHeaderNews(numberOfNews);
        getAllNewsPage().clickNews(numberOfNews);
        assertEquals(newsHeader, getNewsPage().getHeaderNews(), "Заголовки не соответствуют");
    }

    @Test
    @DisplayName("Кнопка 'Новости' перенаправляет на страницу со всеми новостями")
    public void checkIfUrlOfAllNewsPageIsCorrect() {
        getMainPage().clickNewsButton();
        String url = Helper.getUrlWithoutHash();
        assertEquals("https://habr.com/ru/news/", url, "Url страницы некорректен");
    }

    @Test
    @DisplayName("Кнопка 'Все новости' перенаправляет на страницу со всеми новостями")
    public void checkIfRedirectionForAllNewsButtonIsCorrect() {
        getMainPage().clickNextNewsButton();
        getMainPage().clickAllNewsButton();
        String url = Helper.getUrlWithoutHash();
        assertEquals("https://habr.com/ru/news/", url, "Url страницы некорректен");
    }

}
