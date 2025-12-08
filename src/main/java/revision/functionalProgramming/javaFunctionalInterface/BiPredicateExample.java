package revision.functionalProgramming.javaFunctionalInterface;

import java.util.function.BiPredicate;

public class BiPredicateExample {

    public static void main(String[] args) {

        BiPredicate<String, Dog> biPredicate = (str, d) -> d.toString().contains(str);
        boolean result = biPredicate.test("a", new Dog());
        System.out.println(result);

        BiPredicate<String, Dog> biPredicate2 = BiPredicateExample::check;
        biPredicate2.test("Dog", new Dog());
    }

    public static boolean check(String str, Dog dog) {
        System.out.printf("Check %s contains %s %n", dog.toString(), str);
        return dog.toString().contains(str);
    }

    public static class Dog {

    }

}
