package revision.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    // Stream - linia produkcyjna
    // Stream pipline: (ciąg opercji)
    // 1. źródło (danych)
    // 2. operacje pośrednie (lazy evaluation nie wykonaja sie jesli nie ma zdefiniowanej operacji terminującej)
    // 3. operacja końcowa (terminująca)


    public static void main(String[] args) {

        List<String> cities = Arrays.asList("Warszawa", "Łódź", "Rzeszów", "Kraków", "Warszawa", "Puck");

        /*
        EXAMPLE 1 - streamy skończone
         */
        Stream<String> empty = Stream.empty();
        Stream<String> streamString = Stream.of("1", "2", "3")
                .peek(elem -> System.out.println("elem: " + elem));

        streamString.collect(Collectors.toList());

        // Jeden stream może być użyty tylko raz!!!
        // streamString.collect(Collectors.toList()); - NIE DZIAŁA

        List<String> stringList = List.of("1", "2", "3", "10", "23");
        Stream<String> stream1 = stringList.stream();
        Stream<String> stream2 = stringList.stream();

        stream1.collect(Collectors.toList());
        stream2.collect(Collectors.toList());

        /*
        EXAMPLE 2 - streamy nieskończone
         */
        Stream<String> stringStream = Stream.of("1", "7", "10")
                .peek(e -> System.out.println("e: " + e));

        // generate() tworzy nieskończony strumień danych, który generuje elementy na bieżąco
//        Stream.generate(() -> "someValue")
//                .peek(elem -> System.out.println("elem: " + elem))
//                .collect(Collectors.toList());


        // iterate
//        Stream.iterate(0, a -> a + 1)
//                .peek(elem -> System.out.println("elem: " + elem))
//                .collect(Collectors.toList());


        // 1
        System.out.println("\nNormal steps:\n");
        List<Integer> lenghts = new ArrayList<>();
        for (String city : cities) {
            lenghts.add(city.length());
        }
        System.out.println("Step1: " + lenghts);

        // 2
        lenghts.sort(Comparator.<Integer, Integer>comparing(a -> a).reversed());
        System.out.println("Step2: " + lenghts);

        // 3
        List<Integer> lenghtsFiltered = new ArrayList<>();
        for (Integer lenght : lenghts) {
            if(lenght > 5) {
                lenghtsFiltered.add(lenght);
            }
        }
        System.out.println("Step3: " + lenghtsFiltered);

        // 4
        System.out.println("Step4: " + lenghtsFiltered.get(1));
        System.out.println("Step4: " + lenghtsFiltered.get(2));

        // Podejscie funkcyje - bierze elementy 2 i 3
        System.out.println("\nStream steps:\n");
        List<Integer> listC = cities.stream()
                .map(String::length)
                .peek(e -> System.out.println("Step1 func: " + e))
                .sorted(Comparator.<Integer, Integer>comparing(a -> a).reversed())
                .peek(e -> System.out.println("Step2 func: " + e))
                .filter(a -> a > 5)
                .skip(1)
                .limit(2)
                .toList();

        System.out.println("Step4 func: " + listC);


        //
        System.out.println("\nStream steps (String):\n");
        List<String> result = cities.stream()
                .peek(e -> System.out.println("Step1: " + e))
                .filter(e -> e.length() > 5)
                .peek(e -> System.out.println("Step2: " + e))
                .sorted(Comparator.<String>naturalOrder().reversed())
                .peek(e -> System.out.println("Step3: " + e))
                .skip(1)
                .peek(e -> System.out.println("Step4: " + e))
                .limit(2)
                .peek(e -> System.out.println("Step5: " + e))
                .collect(Collectors.toList());
        System.out.println(result);


    }
}
