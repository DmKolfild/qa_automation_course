package com.example.habrtest;

import io.qameta.allure.Step;
import org.slf4j.Logger;

public class AllureLogger {
    private final Logger logger;

    public AllureLogger(Logger logger) {
        this.logger = logger;
    }

    @Step("{logText}")
    public void info(String logText) {
        logger.info(logText);
    }

    @Step("{logText}")
    public void infoWithScreenshot(String logText) {
        logger.info(logText);
        AllureAttachmentsManager.screenshot();
    }

}
