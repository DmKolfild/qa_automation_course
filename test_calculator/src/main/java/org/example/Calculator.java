package org.example;

public class Calculator {

    public float add(float a, float b) {
        float result;
        result = a + b;
        System.out.printf("Сумма %.1f и %.1f равна %.1f\n", a, b, result);
        return result;
    }

    public float sub(float a, float b) {
        float result;
        result = a - b;
        System.out.printf("Разность %.1f и %.1f равна %.1f\n", a, b, result);
        return result;
    }

    public float mul(float a, float b) {
        float result;
        result = a * b;
        System.out.printf("Произведение %.1f и %.1f равно %.1f\n", a, b, result);
        return result;
    }

    public float div(float a, float b) {
        float result;
        result = a / b;
        System.out.printf("Частное %.1f и %.1f равно %.1f\n", a, b, result);
        return result;
    }

}
