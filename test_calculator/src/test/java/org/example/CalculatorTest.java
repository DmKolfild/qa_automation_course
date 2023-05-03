package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculatorTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests is started");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test is started");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Tests is done");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test is done");
    }

    @ParameterizedTest(name = "#{index} - add {0} и {1}, expect {2}")
    @CsvSource({"1, 3, 4", "-3, -4, -7", "2.5, 5.23, 7.73"})
    @DisplayName("Checking the add method")
    @Timeout(10)
    @Tag("add")
    void add(float firstArg, float secondArg, float expectedResult) {
        Calculator calculator = new Calculator();
        float result = calculator.add(firstArg, secondArg);
        Assertions.assertEquals(expectedResult, result, "Wrong answer!!!");
    }

    @ParameterizedTest(name = "#{index} - sub {0} и {1}, expect {2}")
    @CsvSource({"10, 3, 7", "-3, -4, 1", "2.5, 5.7, -3.2"})
    @DisplayName("Checking the sub method")
    @Timeout(10)
    @Tag("sub")
    void sub(float firstArg, float secondArg, float expectedResult) {
        Calculator calculator = new Calculator();
        float result = calculator.sub(firstArg, secondArg);
        Assertions.assertEquals(expectedResult, result, "Wrong answer!!!");
    }

    @ParameterizedTest(name = "#{index} - mul {0} и {1}, expect {2}")
    @CsvSource({"4, 3, 12", "-12, -1, 12", "2.5, 3.0, 7.5"})
    @DisplayName("Checking the mul method")
    @Timeout(10)
    @Tag("mul")
    void mul(float firstArg, float secondArg, float expectedResult) {
        Calculator calculator = new Calculator();
        float result = calculator.mul(firstArg, secondArg);
        Assertions.assertEquals(expectedResult, result, "Wrong answer!!!");
    }

    @ParameterizedTest(name = "#{index} - div {0} и {1}, expect {2}")
    @CsvSource({"8, 2, 4", "1, -4, -0.25", "2.5, 2.5, 1"})
    @DisplayName("Checking the div method")
    @Timeout(10)
    @Tag("div")
    void div(float firstArg, float secondArg, float expectedResult) {
        Calculator calculator = new Calculator();
        float result = calculator.div(firstArg, secondArg);
        Assertions.assertEquals(expectedResult, result, "Wrong answer!!!");
    }
}
