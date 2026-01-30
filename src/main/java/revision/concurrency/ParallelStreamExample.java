package revision.concurrency;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamExample {

    public static void main(String[] args) {

        Stream<Integer> exampleOfRegularStream = Stream.of(1, 2, 3);
        Stream<Integer> exampleOfParallelStream = exampleOfRegularStream.parallel();
        Stream<Integer> anotherExampleOfParallelStream = Arrays.asList(1, 2, 3).parallelStream();

        Stream.of(1, 2, 3).forEach(element -> System.out.print(element + ","));
        System.out.println("\nanotherExampleOfParallelStream:");
        anotherExampleOfParallelStream.forEach(element -> System.out.print(element + ","));

        System.out.println("\nReduce:");
        String reduce = Stream.of('j', 'a', 'v', 'a', '1', '7')
                .reduce(
                        ""
                        ,
                        (previous, next) -> previous + next,
                        (left, right) -> left + right
                );
        System.out.println(reduce);
        String reduceParallel = Stream.of('j', 'a', 'v', 'a', '1', '7')
                .parallel()
                .reduce(
                        "_",
                        (previous, next) -> previous + next,
                        (left, right) -> left + right
                );
        System.out.println(reduceParallel);
        /*
            Zarówno dwu- jak i trzy- argumentowa wersja metody reduce() wspiera przetwarzanie
            przez parallel stream. Zaleca się stosowanie metody reduce() z trzema argumentami
            podczas pracy z parallel streams. Jeżeli podamy parametr combiner, JVM może wtedy
            bardziej wydajnie podzielić wykonywane operacje.
         */


        // Tworzenie wątków
        IntStream.range(0, 20)
                .forEach(i -> CompletableFuture.runAsync(() -> System.out.println(i)));

    }

}
