package revision.functionalProgramming.javaFunctionalInterface;

import java.util.Optional;
import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {

        Function<Car, String> function = Car::toString;
        Optional<String> carOpt = Optional.of(new Car())
                .map(function);
        System.out.println(carOpt);


        Function<Car, String> function2 = c -> {
            System.out.println(c);
            return "someString";
        };
        String applyResult = function2.apply(new Car());
        System.out.println(applyResult);

        Function<Car, String> function3 = FunctionExample::convertToString;
        System.out.println(function3.apply(new Car()));
    }


    public static String convertToString(Car car) {
        System.out.println("Calling convert to string");
        return "some string";
    }


    public static class Car {
        @Override
        public String toString() {
            return "Car{}";
        }
    }
}
