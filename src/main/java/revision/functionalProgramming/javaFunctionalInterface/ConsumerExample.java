package revision.functionalProgramming.javaFunctionalInterface;

import java.util.function.Consumer;

public class ConsumerExample {

    public static void main(String[] args) {

        Consumer<String> consumer1 = intput -> System.out.println(intput);
        consumer1.accept("Comsume me");

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("Consume me 2");

        Consumer<String> consumer3 = ConsumerExample::extracted;
        consumer2.accept("Consume me 3");
    }

    public static void extracted(String input) {
        System.out.println("Calling:");
        System.out.println("Input: " + input);
    }
}
