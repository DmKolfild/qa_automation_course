package com.example.habrtest.tests;

import com.example.habrtest.MyExtension;
import com.example.habrtest.pages.MainPage;
import com.example.habrtest.pages.SearchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MyExtension.class)
public class SearchTest extends BaseTest {
    private MainPage mainPage;
    private SearchPage searchPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.habr.com/");
        mainPage = new MainPage(getDriver());
        searchPage = new SearchPage(getDriver());
    }

    @Test
    @DisplayName("Поиск по пустой строке")
    public void searchEmpty() {
        mainPage.clickSearchButton();

        searchPage.waitWhenSearchIsVisible();
        String empty = "";
        String primalUrl = getUrlWithoutHash();
        searchPage.search(empty);
        assertEquals(primalUrl, getUrlWithoutHash(), "Осуществился поиск по пустой строке");
    }

    @Test
    @DisplayName("Поиск по невалидному слову (в выдаче нет статей)")
    public void searcUsingInvalidWord() {
        mainPage.clickSearchButton();
        searchPage.waitWhenSearchIsVisible();
        String invalidWord = "хомяк124$";
        searchPage.search(invalidWord);
        assertFalse(searchPage.getMessage(), "Сообщение не найдено");
    }
}
