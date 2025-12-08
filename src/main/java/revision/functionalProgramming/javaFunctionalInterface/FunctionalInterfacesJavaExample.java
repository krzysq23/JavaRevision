package revision.functionalProgramming.javaFunctionalInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FunctionalInterfacesJavaExample {

    public static void main(String[] args) {

        Optional.of("someValue")
                .map(a -> args.length)
                .filter(x -> x > 5)
                .ifPresent(toConsume -> System.out.println(toConsume));


        Map<Integer, String> stringList = new HashMap<>();
        stringList.put(1, "test");
        stringList.put(2, "tekst");
        stringList.put(3, "function");
        System.out.println(stringList);
        stringList.replaceAll(
                (key, value) -> value + "_change"
        );
        System.out.println(stringList);

        Map<Integer, Cat> cats = new HashMap<>();
        cats.put(1, new Cat("Lolek"));
        cats.put(2, new Cat("Bolek"));
        cats.put(3, new Cat("leszek"));
        System.out.println(stringList);
        cats.replaceAll(
                (Integer key,Cat value) -> new Cat(value.name + "_changed")
        );
        System.out.println(cats);


    }

    static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
