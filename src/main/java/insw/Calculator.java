package insw;

public class Calculator {
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    public Integer divide(Integer a, Integer b) {
        if (b == 0) {
            throw new IllegalArgumentException("Can't divide by zero");
        }

        return a / b;
    }
}
