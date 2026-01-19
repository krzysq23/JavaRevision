package revision.designPatterns.creational;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class AbstractFactoryExample {

    /*
        Fabryka abstrakcyjna - wzorzec ten jest bardzo podobny do poprzedniego, ale jest bardziej abstrakcyjny.
        Można go rozumieć jako fabrykę fabryk.

        Zalety tego podejścia:
        • klient (czyli klasa FactoryRunner) jest niezależna od sposobu tworzenia fabryki, zmiana w
            implementacji fabryki abstrakcyjnej nie wpływa na zmianę kodu klasy FactoryRunner,
        • stosując to podejście, możemy w trakcie działania programu zdecydować jaką fabrykę utworzymy -
            w trakcie działania programu, bo decydujemy o tym przy pomocy parametru String, a nie na etapie kompilacji,
        • bez ingerencji w istniejące fabryki możemy dodać logikę odpowiedzialną za tworzenie innej rodziny
            obiektów, np. komputerów.
     */

    public static void main(String[] args) {

        AbstractFactory<?> pizzaFactory = FactoryProvider.getFactory("Pizza");
        Object created = pizzaFactory.create("Hawaiian");
        System.out.println(created);

        AbstractFactory<?> carFactory = FactoryProvider.getFactory("Car");
        Object createdCar = carFactory.create("Suv");
        System.out.println(createdCar);

    }

    public static class FactoryProvider {
        public static AbstractFactory<?> getFactory(String whatFactory) {
            return switch (whatFactory) {
                case "Pizza" -> new PizzaFactory();
                case "Car" -> new CarFactory();
                default -> throw new RuntimeException("I'm sorry, we only produce pizzas or cars!");
            };
        }
    }

    // java8 >
    public static class FactoryProvider2 {

        private static final Map<String, Supplier<AbstractFactory<?>>> FACTORIES_MAP = new HashMap<>();

        static {
            FACTORIES_MAP.put("Pizza", PizzaFactory::new);
            FACTORIES_MAP.put("Car", CarFactory::new);
        }

        public static AbstractFactory<?> getFactory(String whatFactory) {
            return Optional.ofNullable(FACTORIES_MAP.get(whatFactory))
                    .map(Supplier::get)
                    .orElseThrow(() -> new RuntimeException("I'm sorry, we only produce pizzas or cars!"));
        }
    }

    public static class CarFactory implements AbstractFactory<Car> {
        private static final Map<String, Supplier<Car>> CAR_MAP = new HashMap<>();
        static {
            CAR_MAP.put("Suv", Suv::new);
            CAR_MAP.put("Cabriolet", Cabriolet::new);
        }

        @Override
        public Car create(final String type) {
            return Optional.of(CAR_MAP.get(type))
                    .map(Supplier::get)
                    .orElseThrow(() -> new RuntimeException(
                            String.format("I'm sorry, we don't know what [%s] is!", type)));
        }
    }

    public static class PizzaFactory implements AbstractFactory<Pizza> {
        private static final Map<String, Supplier<Pizza>> PIZZA_MAP = new HashMap<>();
        static {
            PIZZA_MAP.put("Pepperoni", PepperoniPizza::new);
            PIZZA_MAP.put("Hawaiian", HawaiianPizza::new);
        }

        @Override
        public Pizza create(final String pizzaType) {
            return Optional.of(PIZZA_MAP.get(pizzaType))
                    .map(Supplier::get)
                    .orElseThrow(() -> new RuntimeException(
                            String.format("I'm sorry, we don't know what [%s] is!", pizzaType)));
        }
    }

    public static interface AbstractFactory<T> {
        T create(String type);
    }

    public static class PepperoniPizza implements Pizza {
        @Override
        public String whatSauce() {
            return "Spicy";
        }
        @Override
        public void bake() {
            System.out.println("I'm soo baking!");
        }
        public void beMoreSpicy() {
            System.out.println("Be prepared!");
        }
    }

    public static class HawaiianPizza implements Pizza {
        @Override
        public String whatSauce() {
            return "Tomato";
        }
        @Override
        public void bake() {
            System.out.println("I'm baking my pineapple!");
        }
        public void morePineapple() {
            System.out.println("More pineapple!");
        }
    }

    public interface Pizza {
        String whatSauce();
        void bake();
    }

    public static class Cabriolet implements Car {
    }

    public static class Suv implements Car {
    }

    public interface Car {
    }

}
