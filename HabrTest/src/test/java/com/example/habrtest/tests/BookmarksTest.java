package com.example.habrtest.tests;

import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class BookmarksTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");

        getMainPage().clickSignIn();
        getAuthorizationPage().inputEmail(AuthorizationCreds.EMAIL.getValue());
        getAuthorizationPage().inputPassword(AuthorizationCreds.PASSWORD.getValue());
        getAuthorizationPage().clickSubmit();
    }

    @Test
    @DisplayName("Добавление статьи в закладки")
    public void addingArticleInBookmarks() {
        int numArticle = 0;
        String titleOnTheMainPage = getMainPage().getTitleOfArticle(numArticle);
        getMainPage().addArticleInBookmarks(numArticle);
        getMainPage().clickIconProfile();
        getMainPage().clickBookmarksPageButton();
        assertEquals(getBookmarksPage().getTitle(numArticle), titleOnTheMainPage, "Статьи нет в избранном");
    }
}
