package revision.generics.lowerBound_wildcard;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundWildcard {

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());

        addFlyingDog(dogs);
        addFlyingDog(animals);

        List<? super Dog> list = dogs;
        List<? super Dog> list2 = animals;
        list.add(new Dog());
        list.add(new FlyingDog());
        // Nie da sie dodać do listy psow rodziców wyżej niż DOG
        // list.add(new Animal())
    }

    private static void addFlyingDog(List<? super Dog> list) {
        list.add(new FlyingDog());
    }

    static class Animal {

    }

    static class Dog extends Animal {

    }

    static class FlyingDog extends Dog {

    }
}
