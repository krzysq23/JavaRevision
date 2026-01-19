package revision.designPatterns.creational;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class FactoryExample {

    /*
        Wzorzec projektowy Fabryka jest stosowany gdy potrzebujemy w elegancki sposób utworzyć instancję
        klasy, w sytuacji gdy mamy klasę bazową oraz kilka klas dziedziczących z niej. Na podstawie
        przekazanego parametru musimy stworzyć instancję jednej z klas dziedziczących z rodzica.

        Przykład z życia:
        Wyobraź sobie, że mamy interface Pizza i w zależności od przekazanych argumentów chcemy zwrócić inną pizzę.

     */

    public static void main(String[] args) {

        Pizza pepperoni = PizzaFactory.preparePizza("Pepperoni");
        System.out.println(pepperoni);
        Pizza hawaiian = PizzaFactory.preparePizza("Hawaiian");
        System.out.println(hawaiian);

        Pizza pepperoni2 = PizzaFactory2.preparePizza("Pepperoni");
        System.out.println(pepperoni2);
        Pizza hawaiian2 = PizzaFactory2.preparePizza("Hawaiian");
        System.out.println(hawaiian2);

        Pizza margeritta = PizzaFactory.preparePizza("Margeritta");
    }

    public interface Pizza {
        String whatSauce();
        void bake();
    }

    public static class PizzaFactory {
        public static Pizza preparePizza(final String whatPizza) {
            return switch (whatPizza) {
                case "Pepperoni" -> new PepperoniPizza();
                case "Hawaiian" -> new HawaiianPizza();
                default -> throw new RuntimeException("I'm sorry, there is only Pepperoni or Hawaiian!");
            };
        }
    }

    // Java8 <
    public static class PizzaFactory2 {

        private static final Map<String, Supplier<Pizza>> PIZZA_MAP = new HashMap<>();
        static {
            PIZZA_MAP.put("Pepperoni", PepperoniPizza::new);
            PIZZA_MAP.put("Hawaiian", HawaiianPizza::new);
        }

        public static Pizza preparePizza(String pizzaType){
            return Optional.of(PIZZA_MAP.get(pizzaType))
                    .map(Supplier::get)
                    .orElseThrow(() -> new RuntimeException(
                            String.format("I'm sorry, we don't know what [%s] is!", pizzaType)));
        }
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

}
