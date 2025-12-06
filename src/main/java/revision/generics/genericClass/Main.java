package revision.generics.genericClass;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Animal> list = new ArrayList<>();
        list.add(new Dog());
        list.add(new Pigeon());
    }

    static class Animal{}
    static class Dog extends Animal{}
    static class Pigeon extends Animal{}
}
