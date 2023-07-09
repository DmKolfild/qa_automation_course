package com.example.habrtest;

// Учетка на habr.com была специально создана для тестирования
public enum AuthorizationCreds {
    EMAIL("cicod82656@nasskar.com"),
    PASSWORD("testTestJavaSelenium"),
    USERNAME("@testTestJavaSelenium");

    private final String credValue;

    AuthorizationCreds(String dayNumber) {
        this.credValue = dayNumber ;
    }

    public String getValue() {
        return this.credValue;
    }
}