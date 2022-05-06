package insw.generation;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CalculatorTestGenerator implements DisplayNameGenerator {
    @Override
    public String generateDisplayNameForClass(Class<?> aClass) {
        return aClass.getSimpleName();
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> aClass, Method method) {
        return aClass.getSimpleName() + "." + method.getName();
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> aClass) {
        return "";
    }
}
