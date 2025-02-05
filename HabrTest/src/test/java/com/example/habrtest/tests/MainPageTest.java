package com.example.habrtest.tests;

import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import com.example.habrtest.pages.AuthorizationPage;
import com.example.habrtest.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class MainPageTest extends BaseTest {
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;

    @BeforeEach
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");
        mainPage = new MainPage(getDriver());
        authorizationPage = new AuthorizationPage(getDriver());

        mainPage.clickSignIn();
        authorizationPage.inputEmail(AuthorizationCreds.EMAIL.getValue());
        authorizationPage.inputPassword(AuthorizationCreds.PASSWORD.getValue());
        authorizationPage.clickSubmit();
    }

    @Test
    @DisplayName("Валидность текста alert-уведомления при клике по кнопке 'Скопировать ссылку на RSS'")
    public void checkAlertOnTheMainPage() {
        mainPage.clickRss();
        assertEquals(mainPage.getMessageFromAlert(), "Ссылка скопирована в буфер обмена", "Сообщение не валидно");
    }

}
