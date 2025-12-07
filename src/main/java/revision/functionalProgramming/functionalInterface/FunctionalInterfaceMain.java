package revision.functionalProgramming.functionalInterface;

public class FunctionalInterfaceMain {

    /*
    @FunctionalInterface
    adnotacja sprawdza czy interfejs jest funkcyjny
     */
    public static void main(String[] args) {

        SomeFunctionalInterface impl = (arg) -> {
            System.out.println("calling our functional interfce: " + arg);
        };
        impl.consume("my arg");

    }
}
