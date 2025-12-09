package revision.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminateOperationExample {

    public static void main(String[] args) {

        List<String> strings = List.of("a1", "b1", "c2", "c4");

        // Operacja COUNT
        System.out.println(
                strings.stream()
                        .filter(e -> e.contains("c"))
                        .count()
        );

        // Operacje findFirst i findAny (zwróci jeden losowy lub „pierwszy z brzegu”)
        System.out.println("FindFirst: " +
                strings.stream()
                        .filter(e -> e.contains("c"))
                        .findFirst()
        );
        System.out.println("FindAny: " +
                strings.stream()
                        .findAny()
        );

        // Operacje min i max
        System.out.println("Max: " +
                strings.stream()
                        .max(String::compareTo)
        );
        System.out.println("Min: " +
                strings.stream()
                        .min(String::compareTo)
        );
        Optional<String> max = Stream.<String>empty().max(Comparator.naturalOrder());
        System.out.println("max empty: " + max);

        // Operacje match - allMatch | anyMatch | noneMatch
        boolean result = strings.stream()
                .allMatch(elem -> elem.contains("a"));
        System.out.println(result);
        boolean result2 = strings.stream()
                .anyMatch(elem -> elem.contains("a"));
        System.out.println(result2);
        boolean result3 = strings.stream()
                .noneMatch(elem -> {
                    System.out.println("check elem: " + elem);
                    return  elem.contains("b");
                });
        System.out.println(result3);


        // Operacje forach
        List<String> newList = new ArrayList<>();
        strings
                .forEach(elem -> {
                    System.out.println("add to new list" + elem);
                    newList.add(elem);
                });
        System.out.println("strings: " + strings);
        System.out.println("newList: " + newList);


        // parallelStream() dzieli dane na części i przetwarza je w kilku wątkach równolegle,
        // żeby przyspieszyć operacje na dużych kolekcjach
        // Działanie forEach będzie wywoływane z różnych wątków
        // Wynik może być w innej kolejności za każdym razem

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9);
        numbers.parallelStream()
                .forEach(System.out::println);


        // Operacje reduce - po wykonaniu stream skleja rezultat do jednej wartości wynikowej
        String[] someChars = new String[] {
                "t", "o", "j", "e", "s",
                "t", " ", "t", "e", "s",
                "t", " ", "m", "o", "j"
        };

        System.out.println(
                Arrays.stream(someChars)
                    .reduce((a, b) -> a+b)
                        .get()
        );

        System.out.println(
                Stream.of(someChars)
                        .reduce((a, b) -> a+b)
        );

        Optional<String> reduce = Stream.of(someChars)
                .reduce((a,b) -> a + ":" + b);
        System.out.println(reduce);

        String reduce2 = Stream.of(someChars)
                .reduce("start: ", String::concat);
        System.out.println(reduce2);


        // Operacja determinująca -> collect

        Set<String> collect = Stream.of(someChars)
                        .collect(Collectors.toSet());
        System.out.println(collect.getClass());
        // można wymusić kolekcje
        Set<String> collect2 = Stream.of(someChars)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect2.getClass());

        String joining = Stream.of(someChars)
                .collect(Collectors.joining(";"));
        System.out.println(joining);

        List<String> collect3 = Stream.of(someChars)
                .collect(
                        () -> new ArrayList<>(),
                        (existingSet, nextElement) -> existingSet.add(nextElement),
                        (leftColl, rightColl) -> leftColl.addAll(rightColl));
        System.out.println(collect3);

        List<String> collect4 = Stream.of(someChars)
                .collect(
                        ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll);
        System.out.println(collect4);

    }
}
