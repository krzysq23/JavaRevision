package revision.generics.genericMethods;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GenericMethodMain<T> {

    public static void main(String[] args) {
        call1(new Bike());
        call2(new Bike());

        String o = GenericMethodMain.<Bike>call3().getName();

//        Error:
//        String o2 = GenericMethodMain.call3().getName();

        List<Integer> len = map(Arrays.asList("java", "spring", "testy"), String::length);
        System.out.println(len);
    }

    public static <T, R> List<R> map(List<T> source,
                                     Function<T, R> mapper) {
        return source.stream()
                .map(mapper)
                .toList();
    }

    public static <T extends Vehicle> void call1(T element){
        System.out.println(element);
    }

    public static <T extends Vehicle> T call2(T element){
        System.out.println(element);
        return element;
    }

    public static Map<String, Vehicle> vehicleMap = Map.of(
            "vehicle1", new Bike()
    );

    public static <T> T call3() {
        return (T) vehicleMap.get("vehicle1");
    }

    static class Vehicle {

    }

    static class Bike extends Vehicle{
        String getName() {
            return "bikeName";
        }
    }

    interface Apple {

    }

}
