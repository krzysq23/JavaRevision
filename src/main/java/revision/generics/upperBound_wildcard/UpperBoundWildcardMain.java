package revision.generics.upperBound_wildcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpperBoundWildcardMain {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        List<Monkey> monkeys = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        List<ImplementingVoiceable> implementingVoiceables = new ArrayList<>();

        print(animals);
        print(dogs);
        print(monkeys);
        // NIe da sie bo nie dziedzicz po animals:
        //print(strings);
        //print(implementingVoiceables);

        List<? extends Animal> list = dogs;
        // Nie da sie :
        // list.add(new Dog());
        // list.add(new Animal());
        // ale można usunąć
        list.remove(0);

    }

    private static void print(List<? extends Animal> list) {
        for (Object o: list) {
            System.out.println(o);
        }
    }

    static class Animal implements Voiceable {

    }

    static class Monkey extends Animal {

    }

    static  class Dog extends Animal {

    }

    interface Voiceable {

    }

    static class ImplementingVoiceable implements Voiceable {

    }
}
