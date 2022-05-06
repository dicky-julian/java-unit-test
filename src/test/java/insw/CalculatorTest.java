package insw;

import insw.generation.CalculatorTestGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariables;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.opentest4j.TestAbortedException;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

// @DisplayName("Calculation test")
// add test description automatically with test generator
// add generator's file generation/CalculatorTestGenerator.java
@DisplayNameGeneration(CalculatorTestGenerator.class)
// make unit test just use an object for all method inside class
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
// TestMethodOrder to set ordering unit test execution
// Random -> random ordering
// OrderAnnotation -> order by annotation @Order
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
// will make unit test runs parallel (default = serial / synchronize)
// don't forget to add properties and add :
// junit.jupiter.execution.parallel.enabled=true
@Execution(value = ExecutionMode.CONCURRENT)
public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    // it will execute first before all method runs.
    @BeforeAll
    public static void beforeAll() {
        // System.out.println("it will execute first before all method runs.");
    }

    // it will execute last after all method ends.
    @AfterAll
    public static void afterAll() {
        // System.out.println("it will execute last after all method ends.");
    }

    // it will execute before each method runs.
    @BeforeEach
    public void beforeEach() {
        // System.out.println("it will execute before each method runs.");
    }

    // it will execute after each method ends.
    @AfterEach
    public void afterEach() {
        // System.out.println("it will execute after each method ends.");
    }

//    @Test
    @Order(1)
    // RepeatedTest will make a loop based on "value"
    @RepeatedTest(
            value = 5, // 5 times loop
            name = "{displayName}"
            // name = "{displayName} ke {currentRepetition} dari {totalRepetitions}"
    )
    public void addTestSuccess(TestInfo info, RepetitionInfo repetitionInfo) {
        Integer result = calculator.add(10, 10);
        assertEquals(result, 20);

        // show repetition info
        System.out.println(info.getDisplayName() + " ke " + repetitionInfo.getCurrentRepetition() + " dari " + repetitionInfo.getTotalRepetitions());
    }

    @Test
    @Order(2)
    public void divideTestSuccess() {
        Integer result = calculator.divide(100, 10);
        assertEquals(10, result);
    }

    @Test
    @Order(3)
    // deactivation unit test,
    // system will ignore this unit test with "Skipped" label
    @Disabled
    // manual test description
    @DisplayName("divide by zero, expected IllegalArgument exception")
    public void divideTestError() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(100, 0));
    }

    @Test
    @Order(4)
    // add validation based on environment variables
    @EnabledIfEnvironmentVariables({
            @EnabledIfEnvironmentVariable(named = "PROFILE", matches = "DEV")
    })
    public void testAborted() {
        String profile = System.getenv("PROFILE");
        String newProfile = System.getenv("NEW_PROFILE");

        // TestAbortedException to cancel running test
        if (!"DEV".equals(profile)) {
            throw new TestAbortedException("cancel unit test cause the server isn't DEV");
        }

        // or we can use shorter with user assumption
        assumeTrue("DEV".equals(newProfile));
    }

    @Test
    // set 5s timeout
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    public void testSlow() throws InterruptedException {
        // pending method for 10s
        Thread.sleep(10_000);
    }
}
