package com.example.habrtest.tests;

import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import com.example.habrtest.pages.AuthorizationPage;
import com.example.habrtest.pages.MainPage;
import com.example.habrtest.pages.SettingsProfilePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyExtension.class)
public class SettingsProfileTest extends BaseTest {
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;
    private SettingsProfilePage settingsProfilePage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.habr.com/");
        mainPage = new MainPage(getDriver());
        authorizationPage = new AuthorizationPage(getDriver());
        settingsProfilePage = new SettingsProfilePage(getDriver());

        mainPage.clickIconProfileWithoutAuthorization();
        mainPage.clickSignIn();
        authorizationPage.inputEmail(AuthorizationCreds.EMAIL.getValue());
        authorizationPage.inputPassword(AuthorizationCreds.PASSWORD.getValue());
        authorizationPage.clickSubmit();
        mainPage.clickIconProfile();
        mainPage.clickSettingsProfile();
        mainPage.clickIconProfile();
        mainPage.clickSettingsProfile();
    }

    @Test
    @DisplayName("Загрузка аватара")
    public void uploadAvatar() {
        settingsProfilePage.loadProfileAvatar();
        assertTrue(settingsProfilePage.checkIfAvatarIsVisible(), "Аватар не загружен");
    }

    @Test
    @DisplayName("Валидность текста alert-уведомления после сохранения")
    public void checkAlertOnTheSettingsProfilePage() {
        settingsProfilePage.clickSaving();
        assertEquals(settingsProfilePage.getMessageFromAlert(), "Настройки обновлены успешно", "Аватар не загружен");
    }

}
