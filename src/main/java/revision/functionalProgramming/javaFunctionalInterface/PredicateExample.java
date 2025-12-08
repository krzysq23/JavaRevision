package revision.functionalProgramming.javaFunctionalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {

    public static void main(String[] args) {

        List<String> cities = new ArrayList<>();
        cities.add("Szczecin");
        cities.add("Gdynia");
        cities.add("Warszawa");
        cities.add("Zakopane");
        cities.add("Krakow ");

        print(cities, c -> c.contains("i"));

        print(cities, PredicateExample::stringTest);
    }


    private static boolean stringTest(String input) {
        return input.contains("i");
    }

    private static void print(final List<String> cities, Predicate<String> checker) {
        for (String city: cities) {
            if(checker.test(city)) {
                System.out.println(city);
            }
        }

    }
}
