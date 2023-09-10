package com.example.habrtest.tests;


import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import com.example.habrtest.pages.AuthorizationPage;
import com.example.habrtest.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MyExtension.class)
public class AuthorizationTest extends BaseTest {
    private MainPage mainPage;
    private AuthorizationPage authorizationPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.habr.com/");
        mainPage = new MainPage(getDriver());
        authorizationPage = new AuthorizationPage(getDriver());
    }

    @Test
    @DisplayName("Вывод на экран сообщения \"Введите корректный e-mail\" при вводе невалидного email")
    public void inputInvaluableEmail() {
        mainPage.clickIconProfileWithoutAuthorization();
        mainPage.clickSignIn();
        String email = "124";
        authorizationPage.inputEmail(email);
        authorizationPage.clickSubmit();
        assertFalse(authorizationPage.checkInvaluableMessageAfterEnteringEmail().isEmpty(), "Сообщение \"Введите корректный e-mail\" не найдено");
    }

    @Test
    @DisplayName("Вывод на экран сообщения \"Введите пароль\" при вводе пустого пароля")
    public void inputEmptyPassword() {
        mainPage.clickIconProfileWithoutAuthorization();
        mainPage.clickSignIn();
        String password = "";
        authorizationPage.inputPassword(password);
        authorizationPage.clickSubmit();
        assertFalse(authorizationPage.checkInvaluableMessageAfterEnteringPassword().isEmpty(), "Сообщение \"Введите пароль\" не найдено");
    }

    @Test
    @DisplayName("Авторизация в учётке")
    public void signIn() {
        mainPage.clickIconProfileWithoutAuthorization();
        mainPage.clickSignIn();
        authorizationPage.inputEmail(AuthorizationCreds.EMAIL.getValue());
        authorizationPage.inputPassword(AuthorizationCreds.PASSWORD.getValue());
        authorizationPage.clickSubmit();
        mainPage.clickIconProfile();
        String userName = mainPage.getUserName();
        assertEquals(userName, AuthorizationCreds.USERNAME.getValue(), "Логин пользователя не соответствует его учетке");
    }

    @ParameterizedTest(name = "#{index} - кликабельность кнопки {0}")
    @CsvSource({"0", "1", "2", "3", "4", "5"})
    @DisplayName("Кликабельность кнопок авторизации через социальные сети")
    public void checkSocialButtons(int num) {
        mainPage.clickIconProfileWithoutAuthorization();
        mainPage.clickSignIn();
        String nameButton = authorizationPage.getNameSocialButton(num);
        assertTrue(authorizationPage.checkIfSocialButtonIsClickable(num), String.format("Кнопка {0} неактивна", nameButton));
    }



}
