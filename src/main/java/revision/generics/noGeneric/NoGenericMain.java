package revision.generics.noGeneric;

import java.util.ArrayList;
import java.util.List;

public class NoGenericMain {

    public static void main(String[] args) {
        List dogs = new ArrayList();
        dogs.add(new Dog());
        dogs.add(new Cat());
        print(dogs);

        List<Dog> dogs2 = new ArrayList<>();
        addDog(dogs2);
        System.out.println(dogs2);
        Dog dog = dogs2.get(0);
    }

    private static void addDog(List dogs2) {
        dogs2.add(new Cat());
    }

    private static void print(List<Dog> dogs) {
        for (Dog dog: dogs) {
            System.out.println(dog);
        }
    }

    static class Dog {

    }

    static class Cat {

    }
}
