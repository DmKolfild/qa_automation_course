package com.example.habrtest.tests;

import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import com.example.habrtest.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class BookmarksTest extends BaseTest{
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;
    private BookmarksPage bookmarksPage;

    @BeforeEach
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");
        mainPage = new MainPage(getDriver());
        authorizationPage = new AuthorizationPage(getDriver());
        bookmarksPage = new BookmarksPage(getDriver());

        mainPage.clickSignIn();
        authorizationPage.inputEmail(AuthorizationCreds.EMAIL.getValue());
        authorizationPage.inputPassword(AuthorizationCreds.PASSWORD.getValue());
        authorizationPage.clickSubmit();
    }

    @Test
    @DisplayName("Добавление статьи в закладки")
    public void addingArticleInBookmarks() {
        int numArticle = 0;
        String titleOnTheMainPage = mainPage.getTitleOfArticle(numArticle);
        mainPage.addArticleInBookmarks(numArticle);
        mainPage.clickIconProfile();
        mainPage.clickBookmarksPageButton();
        assertEquals(bookmarksPage.getTitle(numArticle), titleOnTheMainPage, "Статьи нет в избранном");
    }
}
