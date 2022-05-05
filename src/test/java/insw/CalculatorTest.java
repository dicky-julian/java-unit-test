package insw;

import insw.generation.CalculatorTestGenerator;
import org.junit.jupiter.api.*;
import org.opentest4j.TestAbortedException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

// @DisplayName("Calculation test")
// add test description automatically with test generator
// add generator's file generation/CalculatorTestGenerator.java
@DisplayNameGeneration(CalculatorTestGenerator.class)
public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    // it will execute first before all method runs.
    @BeforeAll
    public static void beforeAll() {
        System.out.println("it will execute first before all method runs.");
    }

    // it will execute last after all method ends.
    @AfterAll
    public static void afterAll() {
        System.out.println("it will execute last after all method ends.");
    }

    // it will execute before each method runs.
    @BeforeEach
    public void beforeEach() {
        System.out.println("it will execute before each method runs.");
    }

    // it will execute after each method ends.
    @AfterEach
    public void afterEach() {
        System.out.println("it will execute after each method ends.");
    }

    @Test
    public void addTestSuccess() {
        Integer result = calculator.add(10, 10);
        assertEquals(result, 20);
    }

    @Test
    public void divideTestSuccess() {
        Integer result = calculator.divide(100, 10);
        assertEquals(10, result);
    }

    @Test
    // deactivation unit test,
    // system will ignore this unit test with "Skipped" label
    @Disabled
    // manual test description
    @DisplayName("divide by zero, expected IllegalArgument exception")
    public void divideTestError() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(100, 0);
        });
    }

    @Test
    public void testAborted() {
        String profile = System.getenv("PROFILE");

        // TestAbortedException to cancel running test
        if (!"DEV".equals(profile)) {
            throw new TestAbortedException("cancel unit test cause the server isn't DEV");
        }

        // or we can use shorter with user assumption
        assumeTrue("DEV".equals(profile));
    }
}
