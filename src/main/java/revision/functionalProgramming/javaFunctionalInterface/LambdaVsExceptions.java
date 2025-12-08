package revision.functionalProgramming.javaFunctionalInterface;

import java.util.function.Supplier;

public class LambdaVsExceptions {

    public static void main(String[] args) {
        Supplier<String> supplier = () -> {
            try {
                return stringCreationChecked();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        Supplier<String> supplier1 = () -> stringCreationUnChecked();


        Supplier<String> supplier3 = LambdaVsExceptions::wrapped;
    }

    private static String wrapped() {
        try {
            return stringCreationChecked();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String stringCreationChecked() throws Exception {
        return "stringCreationChecked";
    }

    private static String stringCreationUnChecked() throws RuntimeException {
        return "stringCreationUnChecked";
    }
}
