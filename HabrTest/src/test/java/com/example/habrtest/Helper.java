package com.example.habrtest;

import com.example.habrtest.tests.BaseTest;

public class Helper {
    public static String getUrlWithoutHash() {
        String url = BaseTest.getDriver().getCurrentUrl();
        assert url != null;
        int endIndex = url.length();
        if (url.contains("#"))
            endIndex = url.indexOf("#");
        return url.substring(0, endIndex);
    }
}
