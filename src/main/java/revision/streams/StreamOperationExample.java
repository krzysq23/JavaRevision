package revision.streams;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationExample {

    public static void main(String[] args) {

        List<String> cities = Arrays.asList("Warszawa", "Wrocław", "Zakopane",
                "Kraków", "Warszawa", "Łódź", "Szczecin", "Gdynia");

        // Operacja filter
        Stream<String> stream = cities.stream();
        Stream<String> filterResult = stream
                .filter(city -> city.contains("a"))
                .filter(city -> city.contains("e"));
        List<String> collect = filterResult.toList();
        System.out.println(collect);

        System.out.println("Filter: " +
                cities.stream()
                        .filter(StreamOperationExample::filter)
                        .toList()
        );

        // Operacja map
        System.out.println("Map length: " +
                cities.stream()
                        .map(String::length)
                        .toList()
        );
        System.out.println("Map upperCase: " +
                cities.stream()
                        .map(city -> city + "_miasto")
                        .map(String::toLowerCase)
                        .toList()
        );

        List<Person> people = Arrays.asList(
                new Person("Jan", new City("Warszawa")),
                new Person("Arek", new City("Wrocław")),
                new Person("Jan", new City("Zakopane")),
                new Person("Damian", new City("Kraków")),
                new Person("Roman", new City("Warszawa")),
                new Person("Jacek", new City("Łódź")),
                new Person("Zenek", new City("Szczecin")),
                new Person("Ala", new City("Gdynia"))
        );

        AtomicInteger counter = new AtomicInteger(1);

        String collect2 = people.stream()
                .map(Person::getCity)
                .map(city -> city.getName() + "_" + counter.getAndIncrement())
                .map(String::toUpperCase)
                .reduce("Start:", (a, b) -> String.format("%s-%s%n", a, b));
        System.out.println(collect2);


        // Operacja flatMap - spłaszczanie struktury
        List<String> cities1 = Arrays.asList("Warszawa", "Wrocław", "Zakopane");
        List<String> cities2 = Arrays.asList("Rzeszów", "Wrocław", "Białystok");
        List<String> cities3 = Arrays.asList("Radom", "Lublin", "Kraków");

        List<List<String>> citiesCombined = List.of(cities1, cities2, cities3);
        System.out.println(citiesCombined);

        Stream<List<String>> stream1 = citiesCombined.stream();

        Stream<String> stream2 = citiesCombined.stream()
                .flatMap(Collection::stream);

        Set<String> set = stream2.collect(Collectors.toSet());
        System.out.println(set);


        // Operacja peek
        Set<Integer> collect3 = people.stream()
                .peek(p -> System.out.println("Step 1: " + p))
                .map(Person::getCity)
                .peek(c -> System.out.println("Step 2: "+ c))
                .map(City::getName)
                .peek(n -> System.out.println("Step 3: " + n))
                .map(String::length)
                .peek(l -> System.out.println("Step 4: " + l))
                .collect(Collectors.toSet());
        System.out.println(collect3);


        // Operacja distinct - usuwa powtórzenia
        String collect4 = cities.stream()
                .distinct()
                .collect(Collectors.joining(","));

        System.out.println("Distinct by cities str: " + collect4);

        List<City> citiesList = Arrays.asList(
                new City("Warszawa"),
                new City("Wrocław"),
                new City("Zakopane"),
                new City("Kraków"),
                new City("Warszawa"),
                new City("Łódź"),
                new City("Szczecin"),
                new City("Gdynia")
        );

        String collect5 = citiesList.stream()
                .distinct()
                .map(City::getName)
                .collect(Collectors.joining(","));
        System.out.println("Distinct by cities Obj: " + collect5);
        // Jeżeli operujemy na distinct na obiektach to musza one miec zaimplementowane metody equals i hasCode


        // Operacja limit
        System.out.println("\nLimit Operation:\n");
        cities.stream()
            .peek(elem -> System.out.println("step1: " + elem))
            .map(String::length)
            .peek(elem -> System.out.println("step2: " + elem))
            .limit(3)
            .peek(elem -> System.out.println("step3: " + elem))
            .forEach(elem -> System.out.println("step4"));

        System.out.println("\nLimit Operation (iterate):\n");
        Stream<String> iterStream = Stream.iterate("start", (a) -> a + 1);
        iterStream
                .limit(3)
                .peek(elem -> System.out.println("step1: " + elem))
                .map(String::length)
                .peek(elem -> System.out.println("step2: " + elem))
                .peek(elem -> System.out.println("step3: " + elem))
                .forEach(elem -> System.out.println("step4"));


        // Operacja skip
        System.out.println("\nSkip Operation:\n");
        List<String> listElem = Arrays.asList("elem1", "elem2", "elem3","elem4", "elem5", "elem6");
        listElem.stream()
                .peek(elem -> System.out.println("step1: " + elem))
                .map(String::length)
                .peek(elem -> System.out.println("step2: " + elem))
                .skip(3)
                .peek(elem -> System.out.println("step3: " + elem))
                .forEach(elem -> System.out.println("step4"));


        // Operacja sorted
        System.out.println("\nSorted Operation:\n");
        cities.stream()
                .peek(elem -> System.out.println("step1: " + elem))
                .sorted()
                .peek(elem -> System.out.println("step2: " + elem))
                .forEach(elem -> System.out.println("step4"));

        System.out.println("\nSorted Operation 2:\n");
        people.stream()
                .peek(elem -> System.out.println("step1: " + elem))
//                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .sorted(Comparator.comparing(Person::getName))
                .forEach(elem -> System.out.println("step2: " + elem));

    }



    public static boolean filter(String el) {
        return el.contains("e") || el.contains("o");
    }

    public static class Person {
        private String name;
        private City city;

        public Person(String name, City city) {
            this.name = name;
            this.city = city;
        }

        public String getName() {
            return name;
        }

        public City getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", city=" + city +
                    '}';
        }
    }

    private static class City {
        private String name;

        public City(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            City city = (City) o;
            return Objects.equals(name, city.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
