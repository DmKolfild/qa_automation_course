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
public class MainPageTest extends BaseTest {
    private MainPage mainPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.habr.com/");
        mainPage = new MainPage(getDriver());
    }

    @Test
    @DisplayName("Валидность текста alert-уведомления при клике по кнопке 'Скопировать ссылку на RSS'")
    public void checkAlertOnTheMainPage() {
        mainPage.clickRss();
        assertEquals(mainPage.getMessageFromAlert(), "Ссылка скопирована в буфер обмена", "");
    }

}
