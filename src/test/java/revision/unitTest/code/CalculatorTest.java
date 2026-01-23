package revision.unitTest.code;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    // @BeforeEach uruchamia sie przed kazdym uruchomieniem
    @BeforeEach
    void methodSetUp() {
        calculator = new Calculator();
        System.out.printf("Calling @BeforeEach %s%n", calculator);
    }

    // @AfterEach uruchamia sie po kazdym uruchomieniu
    @AfterEach
    void methodTearDown() {
        System.out.println("Calling @AfterEach");
    }

    // @BeforeEach uruchamia się tylko na początku klasy testowej
    @BeforeAll
    static void classSetUp() {
        System.out.println("Calling @BeforeAll");
    }

    // @BeforeEach uruchamia się tylko na końcu klasy testowej
    @AfterAll
    static void classTearDown() {
        System.out.println("Calling @AfterAll");
    }

    @Test
    @DisplayName("Simple calculator addition should work correctly")
    void testAdd() {

        System.out.println("Calling testAdd");

        // given - dane wejsciowe
        int left = 5;
        int right = 7;
        Integer expected = 12;

        // when
        Integer result = calculator.add(left, right);

        // then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void testSubtract() {

        System.out.println("Calling testSubtract");

        // given
        int left = 5;
        int right = 7;
        Integer expected = -2;
        // when
        Integer result = Calculator.subtract(left, right);
        // then
        Assertions.assertEquals(expected, result);

    }

    @Test
    void testMultiply() {

        System.out.println("Calling testMultiply");

        // given
        int left = 5;
        int right = 7;
        Integer expected = 35;
        // when
        Integer result = Calculator.multiply(left, right);
        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testDivide() {

        System.out.println("Calling testDivide");

        // given
        int left = 10;
        int right = 5;
        Integer expected = 2;
        // when
        Integer result = Calculator.divide(left, right);
        // then
        Assertions.assertEquals(expected, result);
    }

    /*
        Example assertEquals() + message
     */
    @Test
    void someTest() {
        Assertions.assertEquals(1, 1, createMessage(1));
        Assertions.assertEquals(1, 1, () -> createMessage(2));
        Assertions.assertEquals(1, 1, () -> createMessage(3));
    }

    private String createMessage(int i) {
        System.out.println("Evaluating failure message: " + i);
        return "failure message";
    }

    /*
        assertAll - wykonuje wszystkie testy nawet jak bedzie błąd w trakcie
        @Disabled - wyłącza test
     */
    @Test
    @Disabled("Test doesn't work")
    void calculatorTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("test1", "test1", createMessage(1)),
                () -> Assertions.assertEquals("test1", "test2", () -> createMessage(2)),
                () -> Assertions.assertEquals("test1", "test4", () -> createMessage(3))
        );
    }

    /*
        Sprawdzanie wyjątków
     */
    @Test
    void exceptionTest() {

        // given - dane wejsciowe
        String left = "5";
        String right = "test";
        Integer expected = 12;

        // when, then
        Throwable exception = Assertions.assertThrows(NumberFormatException.class, () -> Calculator.add(left, right));
        Assertions.assertEquals("For input string: \"" + right + "\"", exception.getMessage());
    }

    /*
        AssertJ - biblioteka JUnit
     */
    @Test
    void assertJTest() {

        int left = 2;
        int right = 3;
        int result = calculator.add(left, right);

        assertThat(result)
                .isEqualTo(5)
                .isGreaterThan(0)
                .isLessThan(10);

    }


    /*
        Testy parametrized - mamy kilka testów, które testują ten sam fragment kodu, ale
        chcemy wywołać je z innymi parametrami i spodziewamy się wtedy innego wyniku
     */
    @ParameterizedTest
    @MethodSource(value = "testData")
    void testParametrizedAdding(int[] testData) {

        // given
        int left = testData[0];
        int right = testData[1];
        Integer expected = testData[2];

        // when
        Integer result = calculator.add(left, right);

        // then
        Assertions.assertEquals(expected, result);
    }


    // @SuppressWarnings - wymuszanie że metoda zostanie użyta
    @SuppressWarnings("unused")
    public static int[][] testData() {
        return new int[][]{
                {1, 1, 2},
                {2, 3, 5},
                {9, 8, 17},
                {2, 19, 21}
        };
    }

    @ParameterizedTest
    @MethodSource
    void testParametrizedAdding(int left, int right, int expected) {
        // given, when
        Integer result = calculator.add(left, right);
        // then
        Assertions.assertEquals(expected, result);
    }

    @SuppressWarnings("unused")
    public static Stream<Arguments> testParametrizedAdding() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 3, 5),
                Arguments.of(9, 8, 17),
                Arguments.of(2, 19, 21)
        );
    }

}