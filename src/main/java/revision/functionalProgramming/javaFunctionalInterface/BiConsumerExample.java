package revision.functionalProgramming.javaFunctionalInterface;

import java.util.function.BiConsumer;

public class BiConsumerExample {

    public static void main(String[] args) {

        BiConsumer<String, Cat> biConsumer = (str, c) -> System.out.println("Cat: " + c + "like eating str: " + str);
        biConsumer.accept("Milk", new Cat());

        BiConsumer<String, Cat> biConsumer2 = BiConsumerExample::consumer;
        biConsumer2.accept("Milk", new Cat());
    }

    public static void consumer(String str, Cat c) {
        System.out.println("Calling consumer");
        System.out.println("Cat: " + c + "like eating str: " + str);
        return;
    }

    public static class Cat {}
}
