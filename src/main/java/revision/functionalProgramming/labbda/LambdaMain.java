package revision.functionalProgramming.labbda;

import java.util.List;

public class LambdaMain {

    /*
    Lambda to krótki zapis funkcji, który pozwala traktować funkcje jak dane
    — przekazywać je jako argumenty, zwracać z metod itd.
    - interfejs funkcyjny musi mieć tylko jedną metode public abstract
     */
    public static void main(String[] args) {

        // Bez lambda
        Checkable checkable = new CheckableImpl();
        System.out.println(checkable.myTester("nie ma tu słowa test"));

        // Z lambda
        Checkable checkLambda = a -> {
            System.out.println("Lambda calling");
            return a.contains("test");
        };
        System.out.println(checkLambda.myTester("nie ma tu słowa test"));

        Checkable2 checkLambda2 = (input, arg1, arg2) -> input.contains("test");
        System.out.println(checkLambda2.myTester("tu nie ma słowa test", "", ""));


        // Lambda na kolekcji
        List<String> cities = List.of("Warszawa", "Krakow", "Zakopane", "Rzeszow");

        // effective leaf final - zmienna final moze byc uzywana w lambda a zmienna nie
        final Integer variable = 4;

        print(cities, v -> {
            // Deferred execution - lambda nie wywołuje sie w momencie deklaracji tylko wywołania
            System.out.println("Lambda calling: " + v);
            System.out.println(variable);
            return v.contains("a");
        });



    }

    public static void print(final List<String> inputList, final Checkable checkable) {
        for (String value: inputList) {
            if(checkable.myTester(value)) {
                System.out.println("printing: " +  value);
            }
        }
    }
}
