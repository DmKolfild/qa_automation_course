package com.example.habrtest.pages;

import com.example.habrtest.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;

// https://habr.com/ru/auth/settings/profile/
public class SettingsProfilePage {
    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(SettingsProfilePage.class));
    private final WebDriver driver;

    @FindBy(css = "[accept='image/*']")
    private WebElement loadButton;

    @FindBy(css = ".user-avatar img")
    private WebElement avatar;

    @FindBy(css = "[type=\"submit\"]")
    private WebElement savingButton;

    @FindBy(css = "[role=\"alert\"]")
    private WebElement alert;

    public SettingsProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loadProfileAvatar() {
        File file = new File(""); // создаёт пустой файл с пустым именем в каталоге проекта
        String path = file.getAbsolutePath() + "/src/test/java/com/example/habrtest/files/profile.jpg";

        loadButton.sendKeys(path);
    }

    public Boolean checkIfAvatarIsVisible() {
        LOG.infoWithScreenshot("Проверка, что аватар загружен");
        return avatar.isDisplayed();
    }

    public void clickSaving() {
        LOG.info("Клик по кнопке 'Сохранить изменения'");
        savingButton.click();
    }

    public String getMessageFromAlert() {
        LOG.info("Получение сообщения из всплывающего окошка alert");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(alert));
        return alert.getText();
    }

}
