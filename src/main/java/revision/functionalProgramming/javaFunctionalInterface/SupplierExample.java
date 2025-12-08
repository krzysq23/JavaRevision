package revision.functionalProgramming.javaFunctionalInterface;

import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {

        Supplier<Integer> supplier1 = () -> 123;
        System.out.println(supplier1.get());

        Supplier<Integer> supplier2 = SupplierExample::gimme;
        System.out.println(supplier2.get());

    }

    public static Integer gimme() {
        return 456;
    }
}
