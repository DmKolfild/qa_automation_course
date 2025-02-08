package com.example.habrtest.tests;


import com.example.habrtest.AuthorizationCreds;
import com.example.habrtest.MyExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MyExtension.class)
public class AuthorizationTest extends BaseTest {
    @BeforeEach
    @Override
    public void setUp() throws InterruptedException {
        super.setUp();
        getDriver().get("https://habr.com/ru/feed/");
    }

    @Disabled("Test is not actual, The message hasn't disappeared")
    @Test
    @DisplayName("Вывод на экран сообщения \"Введите корректный e-mail\" при вводе невалидного email")
    public void inputInvaluableEmail() throws InterruptedException {
        getMainPage().clickSignIn();
        String email = "124";
        getAuthorizationPage().inputEmail(email);
        getAuthorizationPage().clickSubmit();
        assertFalse(getAuthorizationPage().checkInvaluableMessageAfterEnteringEmail().isEmpty(), "Сообщение \"Введите корректный e-mail\" не найдено");
    }

    @Disabled("Test is not actual, The message hasn't disappeared")
    @Test
    @DisplayName("Вывод на экран сообщения \"Введите пароль\" при вводе пустого пароля")
    public void inputEmptyPassword() throws InterruptedException {
        getMainPage().clickSignIn();
        String password = "";
        getAuthorizationPage().inputPassword(password);
        getAuthorizationPage().clickSubmit();
        assertFalse(getAuthorizationPage().checkInvaluableMessageAfterEnteringPassword().isEmpty(), "Сообщение \"Введите пароль\" не найдено");
    }

    @Test
    @DisplayName("Авторизация в учётке")
    public void signIn() throws InterruptedException {
        getMainPage().clickSignIn();
        getAuthorizationPage().inputEmail(AuthorizationCreds.EMAIL.getValue());
        getAuthorizationPage().inputPassword(AuthorizationCreds.PASSWORD.getValue());
        getAuthorizationPage().clickSubmit();
        getMainPage().clickIconProfile();
        String userName = getMainPage().getUserName();
        assertEquals(userName, AuthorizationCreds.USERNAME.getValue(), "Логин пользователя не соответствует его учетке");
    }

    @ParameterizedTest(name = "#{index} - кликабельность кнопки {0}")
    @CsvSource({"0", "1", "2", "3", "4", "5"})
    @DisplayName("Кликабельность кнопок авторизации через социальные сети")
    public void checkSocialButtons(int num) {
        getMainPage().clickSignIn();
        String nameButton = getAuthorizationPage().getNameSocialButton(num);
        assertTrue(getAuthorizationPage().checkIfSocialButtonIsClickable(num), String.format("Кнопка {0} неактивна", nameButton));
    }


}
