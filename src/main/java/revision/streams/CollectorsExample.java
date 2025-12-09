package revision.streams;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsExample {

    public static void main(String[] args) {

        List<String> cities = Arrays.asList("Warszawa", "Łódź", "Rzeszów", "Kraków", "Warszawa", "Puck");

        // collector counting
        System.out.println("Count:");
        System.out.println("count(): " +
                cities.stream()
                        .count()
        );
        System.out.println("Collectors.counting(): " +
                cities.stream()
                        .collect(Collectors.counting())
        );

        // collector joining
        System.out.println("\nJoining:");
        System.out.println("Collectors.joining(): " +
                cities.stream()
                        .filter(v -> v.length() > 6)
                        .collect(Collectors.joining(",", "<Start>", "<End>"))
        );

        // collector toCollection
        System.out.println("\nToCollection:");
        var collect = cities.stream()
                .filter(v -> v.length() > 6)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("Collectors.toCollection(): " +
                collect.getClass()
        );

        // collector maxBy minBy
        System.out.println("\nmaxBy minBy:");
        System.out.println("Collectors.maxBy(): " +
                cities.stream()
                        .filter(v -> v.length() > 6)
                        .collect(Collectors.maxBy(Comparator.<String>naturalOrder().reversed()))
        );
        System.out.println("Collectors.minBy(): " +
                cities.stream()
                        .filter(v -> v.length() > 6)
                        .min(Comparator.<String>naturalOrder().reversed())
        );


        // collector mapping - przed zwracaniem kolekcji dodaje jeszcze jeden poziom mapowania
        System.out.println("\nMapping:");
        System.out.println("reduce(): " +
                cities.stream()
                        .map(String::length)
                        .reduce(0, Integer::sum)
        );
        System.out.println("Collectors.mapping(): " +
                cities.stream()
                        .collect(Collectors.mapping(String::length, Collectors.reducing(0, Integer::sum)))
        );


        // collector toMap - tworzy mapy, ale wartosci w liście NIE MOGĄ SIE POWTARZA
        // w takim przypadku w 3cium parametrze tomap() okreslamy co ma być w powtórzonej wartości klucza
        System.out.println("\nToMap:");

        cities.stream()
                .collect(Collectors.toMap(key -> key, String::length, (left, right) -> left))
                .forEach((k, v) -> System.out.println("key: " + k + " value: " + v));
        System.out.println();
        cities.stream()
                .collect(Collectors.toMap(key -> key, List::of, CollectorsExample::merge))
                .forEach((k, v) -> System.out.println("key2: " + k + " value2: " + v));
        System.out.println("+ TreeMap");
        cities.stream()
                .collect(Collectors.toMap(key -> key, List::of, CollectorsExample::merge, TreeMap::new))
                .forEach((k, v) -> System.out.println("key3: " + k + " value3: " + v));


        // collector partitioningBy - mapa <boolean, ?>
        System.out.println("\nPartitioningBy:");

        cities.stream()
                .collect(Collectors.partitioningBy(city -> city.length() > 5))
                .forEach((k, v) -> {
                    System.out.println("key: " + k + " value: " + v);
                    System.out.println(v.getClass());
                });

        cities.stream()
                .collect(Collectors.partitioningBy(city -> city.length() > 5, Collectors.toCollection(TreeSet::new)))
                .forEach((k, v) -> {
                    System.out.println("key2: " + k + " value2: " + v);
                    System.out.println(v.getClass());
                });


        // collector groupingBy
        System.out.println("\nGroupingBy:");

        cities.stream()
                .collect(Collectors.groupingBy(String::length))
                .forEach((k, v) ->  System.out.println("key: " + k + " value: " + v));

        System.out.println();

        cities.stream()

                .collect(Collectors.groupingBy(String::length, Collectors.toSet()))
                .forEach((k, v) ->  System.out.println("key2: " + k + " value2: " + v));

        System.out.println();

        cities.stream()
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toList()))
                .forEach((k, v) ->  System.out.println("key3: " + k + " value3: " + v));

        System.out.println();

        cities.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.joining(",")))
                .forEach((k, v) ->  System.out.println("key5: " + k + " value5: " + v));


        // collector groupingBy mapping
        System.out.println("\nGroupingBy mapping:");

        Map<Integer, Optional<String>> result2 = cities.stream()
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                // Collectors.mapping(String::toUpperCase - TO NIE ZADZIAŁA!!!
                                Collectors.mapping((String value) -> value.toUpperCase(), Collectors.maxBy(Comparator.naturalOrder()))
                        )
                );
        System.out.println(result2);

        TreeSet<Object> result3 = cities.stream()
                .collect(() -> new TreeSet<>(), (a,b) -> a.add(b), (a,b) -> a.addAll(b));
        System.out.println(result3);
    }

    private static List<String> merge(List<String> left, List<String> right) {
        List<String> list = new ArrayList<>(left);
        list.addAll(right);
        return list;
    }
}
