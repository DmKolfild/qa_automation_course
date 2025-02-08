package com.example.habrtest.tests;

import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class MainPageTest extends BaseTest {
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
    @DisplayName("Валидность текста alert-уведомления при клике по кнопке 'Скопировать ссылку на RSS'")
    public void checkAlertOnTheMainPage() {
        getMainPage().clickRss();
        assertEquals("Ссылка скопирована в буфер обмена", getMainPage().getMessageFromAlert(), "Сообщение не валидно");
    }

}
