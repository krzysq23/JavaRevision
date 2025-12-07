package revision.functionalProgramming.methodReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class InstanceMethod {

    public static void main(String[] args) {
        new InstanceMethod().run();
    }

    public void run() {
        String someDog = Optional.of(new Dog("Burek"))
                .map(d -> d.name)
                .orElse("anotherDogName");
        System.out.println(someDog);

        String someDog2 = Optional.of(new Dog("Czarek"))
                .map(Dog::getName)
                .orElse("anotherDogName");
        System.out.println(someDog2);

        Optional.of(new Dog("Reksio"))
                .ifPresent(System.out::println);

        Optional.of(new Dog("Reksio xD"))
                .ifPresent(this::printDoggy);


        List<Dog> list = new ArrayList<>();
        list.add(new Dog("Fafik"));
        list.add(new Dog("Burek"));
        list.sort(Comparator.comparing(Dog::getName));
        System.out.println(list);


        // Wywołanie reference przy uzyciu konstruktora
        SteeringWheel steeringWheel = new SteeringWheel(41.20);

        Optional.of(steeringWheel)
                .map(Car::new)
                .orElse(new Car(new SteeringWheel(0.1)));
    }

    private void printDoggy(final Dog dog) {
        System.out.println(dog);
    }

    public static class Dog {
        private final String name;

        public Dog(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Dog: " + name;
        }
    }


    public static class Car {
        private final SteeringWheel steeringWheel;

        public Car(SteeringWheel steeringWheel) {
            this.steeringWheel = steeringWheel;
        }

        public SteeringWheel getSteeringWheel() {
            return steeringWheel;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "steeringWheel=" + steeringWheel +
                    '}';
        }
    }

    public static class SteeringWheel {
        private final double diameter;

        public SteeringWheel(double diameter) {
            this.diameter = diameter;
        }

        @Override
        public String toString() {
            return "SteeringWheel{" +
                    "diameter=" + diameter +
                    '}';
        }
    }
}
