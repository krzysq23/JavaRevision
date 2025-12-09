package revision.streams;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamVsPrimitiveExample {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        Integer sum = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(sum);

        // IntStream, LongStream, DoubleStream
        System.out.println("\nIntStream:\n");

        int sumIntStream =  numbers.stream()
                .mapToInt(a -> a)
                .sum();

        OptionalDouble averageIntStream =  numbers.stream()
                .mapToInt(a -> a)
                .average();

        System.out.println("Suma: " + sumIntStream);
        System.out.println("Średnia: " + averageIntStream);

        OptionalDouble average = DoubleStream.generate(Math::random)
                .limit(10)
                .peek(System.out::println)
                .average();
        System.out.println(average);

        // range
        System.out.println("\nRange:\n");

        LongStream.range(1, 5)
                .forEach(e -> System.out.println("Range: " + e));

        LongStream.rangeClosed(1, 5)
                .forEach(e -> System.out.println("rangeClosed: " + e));

        LongStream.rangeClosed(1, 5)
                .mapToDouble(e -> e*1.4)
                .forEach(e -> System.out.println("rangeClosedToDouble: " + e));

        Stream<Long> boxedLong = LongStream.rangeClosed(1, 5)
                .boxed();

        Stream<Integer> boxedInt = IntStream.rangeClosed(1, 5)
                .boxed();

        Stream<Double> boxedDouble = DoubleStream.generate(Math::random)
                .limit(10)
                .boxed();
        boxedDouble.forEach(e -> System.out.println("Generated: " + e));


    }
}
