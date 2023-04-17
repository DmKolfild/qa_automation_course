package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("after all");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each");
    }

    @Test
    @DisplayName("Проверка суммирования 1")
    @Timeout(10)
    @Tag("sum")
    void sum1 () {
        Calculator calculator = new Calculator();
        float result = calculator.add(7, 23);
        Assertions.assertEquals(30, result, "Не верный ответ!!!");
    }

    @RepeatedTest(10)
    @Test
    @DisplayName("Проверка суммирования 2")
    @Timeout(10)
    void sum2() {
        Calculator calculator = new Calculator();
        float result = calculator.add(7, 23);
        Assertions.assertEquals(30, result, "Не верный ответ!!!");
    }

    @ParameterizedTest(name = "#{index} - сложение {0} и {1}, ожидаем {2}")
    @CsvSource({"1, 2, 3", "-1, 2, 1", "0, 0, 0"})
    @DisplayName("Проверка суммирования 3")
    @Tag("param")
    void sum3(int a, int b, int expectedResult) {
        Calculator calculator = new Calculator();
        float result = calculator.add(a, b);
        Assertions.assertEquals(expectedResult, result, "Не верный ответ!!!");
    }
}
