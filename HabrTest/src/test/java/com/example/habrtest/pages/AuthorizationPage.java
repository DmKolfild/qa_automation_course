package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

// https://account.habr.com/login/
public class AuthorizationPage {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AuthorizationPage.class));
    private final WebDriver driver;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(css = "[type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(), 'Введите корректный e-mail')]")
    private List<WebElement> EmailMessage;

    @FindBy(xpath = "//*[contains(text(), 'Введите пароль')]")
    private List<WebElement> PasswordMessage;

    @FindBy(css = ".socials-buttons__button")
    private List<WebElement> socialButtons;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputEmail(String email) {
        LOG.info("Ввод email");
        inputEmail.sendKeys(email);
    }

    public void inputPassword(String password) {
        LOG.info("Ввод паспорта");
        inputPassword.sendKeys(password);
    }

    public void clickSubmit() throws InterruptedException {
        LOG.info("Клик по кнопке 'Подтвердить'");
        submitButton.click();
        TimeUnit.SECONDS.sleep(30);
    }

    public List<WebElement> checkInvaluableMessageAfterEnteringEmail() {
        LOG.info("Получение сообщения после ввода невалидного email");
        return EmailMessage;
    }

    public List<WebElement> checkInvaluableMessageAfterEnteringPassword() {
        LOG.info("Получение сообщения после ввода невалидного пароля");
        return PasswordMessage;
    }

    public Boolean checkIfSocialButtonIsClickable(int num) {
        return socialButtons.get(num).isEnabled();
    }

    public String getNameSocialButton(int num) {
        return socialButtons.get(num).getAttribute("title");
    }

}
