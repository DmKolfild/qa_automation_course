package com.example.habrtest.tests;

import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyExtension.class)
public class SettingsProfileTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");

        getMainPage().clickSignIn();
        getAuthorizationPage().inputEmail(AuthorizationCreds.EMAIL.getValue());
        getAuthorizationPage().inputPassword(AuthorizationCreds.PASSWORD.getValue());
        getAuthorizationPage().clickSubmit();
        getMainPage().clickIconProfile();
        getMainPage().clickSettingsProfile();
    }

    @Test
    @DisplayName("Загрузка аватара")
    public void uploadAvatar() {
        getSettingsProfilePage().loadProfileAvatar();
        assertTrue(getSettingsProfilePage().checkIfAvatarIsVisible(), "Аватар не загружен");
    }

    @Test
    @DisplayName("Валидность текста alert-уведомления после сохранения")
    public void checkAlertOnTheSettingsProfilePage() {
        getSettingsProfilePage().clickSaving();
        assertEquals("Настройки обновлены успешно", getSettingsProfilePage().getMessageFromAlert(), "Аватар не загружен");
    }

}
