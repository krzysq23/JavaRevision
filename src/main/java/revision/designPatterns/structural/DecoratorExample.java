package revision.designPatterns.structural;

import lombok.AllArgsConstructor;

public class DecoratorExample {

    /*
        Decorator to strukturalny wzorzec projektowy, którego celem jest:
        - dynamiczne dodawanie nowych zachowań do obiektu bez zmiany jego klasy
        Czyli:
        „opakowujemy obiekt w kolejne warstwy, które rozszerzają jego funkcjonalność”.

        Kiedy?
        Jeżeli będziemy korzystać z jakiejś biblioteki, która będzie
        nam dostarczała obiekty i będziemy potrzebowali taki obiekt zmodyfikować. Zmodyfikować, czyli dodać
        do niego w naszym kodzie jakieś pola albo metody, a jednocześnie nie chcemy modyfikować samego
        kodu źródłowego klasy takiego obiektu. Np. modyfikacja pliku .jar, którego nie można modyfikować.

        Struktura wzorca:

        Client → Component
                     ▲
                     │
                Decorator
                     │
                 ConcreteDecorator

        • Component – wspólny interfejs
        • ConcreteComponent – obiekt bazowy
        • Decorator – abstrakcyjna klasa opakowująca
        • ConcreteDecorator – konkretne rozszerzenia

        Decorator:
        • implementuje ten sam interfejs
        • zawiera referencję do Component
        • dodaje zachowanie przed/po delegacji

        Kiedy używać Decoratora?
        • opcjonalne funkcjonalności
        • unikanie dziedziczenia
        • cross-cutting concerns
        • UI, strumienie, middleware

        Decorator w prawdziwej Javie
        JDK:
        • InputStream → BufferedInputStream
        • DataInputStream
        Np.:
        InputStream in =
            new BufferedInputStream(
                new FileInputStream("file.txt")
            );

        Zalety tego podejścia:
        • Wzorzec ten pozwala na modyfikację obiektów w elastyczny sposób,
        • Możemy na jeden obiekt nałożyć wiele dekoracji, nie ogranicza się to tylko do jednej.
     */

    public static void main(String[] args) {

        Car car1 = new SpoilerDecorator(new CarImpl());
        System.out.println(car1.create());

        Car car2 = new SpoilerDecorator(new RimsDecorator(new CarImpl()));
        System.out.println(car2.create());

        Car car3 = new SpoilerDecorator(new NeonDecorator(new RimsDecorator(new CarImpl())));
        System.out.println(car3.create());

        // JAVA8 + Lambda
        System.out.println("\nJava8 lambda example");
        Car orginalCar = new CarImpl();
        Car car4 = () -> "Before Car! " + orginalCar.create() + " with Spoiler";
        Car car5 = () -> "Before Car! " + orginalCar.create() + " with Rims with Spoiler";
        Car car6 = () -> "Before Car! " + orginalCar.create() + " with Rims with Spoiler with Neon";
        System.out.println(car4.create());
        System.out.println(car5.create());
        System.out.println(car6.create());

        // Example 2
        System.out.println("\nCofee example");
        Coffee coffee =
                new SugarDecorator(
                        new MilkDecorator(
                                new BasicCoffee()
                        )
                );

        System.out.println(coffee.description());
        System.out.println(coffee.cost());
    }

    public interface Car {
        String create();
    }

    public static class CarImpl implements Car {
        @Override
        public String create() {
            return "Car";
        }
    }

    @AllArgsConstructor
    public static abstract class CarDecorator implements Car {

        private final Car car;

        @Override
        public String create() {
            return car.create();
        }
    }

    public static class NeonDecorator extends CarDecorator {

        public NeonDecorator(Car car) {
            super(car);
        }

        @Override
        public String create() {
            System.out.println(this.getClass().getSimpleName() + " before super Calling");
            String created = super.create();
            System.out.println(this.getClass().getSimpleName() + " after super Calling");
            return created + withNeon();
        }

        private String withNeon() {
            return " with " + this.getClass().getSimpleName();
        }
    }

    public static class SpoilerDecorator extends CarDecorator {

        public SpoilerDecorator(Car car) {
            super(car);
        }

        @Override
        public String create() {
            return super.create() + withSpoiler();
        }

        private String withSpoiler() {
            return " with " + this.getClass().getSimpleName();
        }
    }

    public static class RimsDecorator extends CarDecorator {

        public RimsDecorator(Car car) {
            super(car);
        }

        @Override
        public String create() {
            return super.create() + withRims();
        }

        private String withRims() {
            return " with " + this.getClass().getSimpleName();
        }
    }


    /*
     Przykład 2
     */
    interface Coffee {
        double cost();
        String description();
    }

    static class BasicCoffee implements Coffee {

        @Override
        public double cost() {
            return 5.0;
        }

        @Override
        public String description() {
            return "Basic coffee";
        }
    }

    static abstract class CoffeeDecorator implements Coffee {

        protected final Coffee coffee;

        protected CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }
    }

    static class MilkDecorator extends CoffeeDecorator {

        MilkDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public double cost() {
            return coffee.cost() + 1.5;
        }

        @Override
        public String description() {
            return coffee.description() + ", milk";
        }
    }

    static class SugarDecorator extends CoffeeDecorator {

        SugarDecorator(Coffee coffee) {
            super(coffee);
        }

        @Override
        public double cost() {
            return coffee.cost() + 0.5;
        }

        @Override
        public String description() {
            return coffee.description() + ", sugar";
        }
    }

}
