package com.example.habrtest.tests;

import com.example.habrtest.MyExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MyExtension.class)
public class SearchTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");
    }

    @Test
    @DisplayName("Поиск по пустой строке")
    public void searchEmpty() {
        getMainPage().clickSearchButton();

        getSearchPage().waitWhenSearchIsVisible();
        String empty = "";
        String primalUrl = getUrlWithoutHash();
        getSearchPage().search(empty);
        assertEquals(primalUrl, getUrlWithoutHash(), "Осуществился поиск по пустой строке");
    }

    @Test
    @DisplayName("Поиск по невалидному слову (в выдаче нет статей)")
    public void searchUsingInvalidWord() {
        getMainPage().clickSearchButton();
        getSearchPage().waitWhenSearchIsVisible();
        String invalidWord = "хомяк124$";
        getSearchPage().search(invalidWord);
        assertFalse(getSearchPage().getMessage(), "Сообщение не найдено");
    }
}
