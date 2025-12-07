package revision.collections.set;

import revision.collections.comparable.Dog;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class SetMain {

    /*
    Set służy po to aby pozbyc sie duplikatów.
    Set posiada metode equals oraz hashcode do weryfikowania czy elemet znajduje sie juz w liście
    Set jest jak worek w ktorym znajduja sie inne worki z elementami
     */

    public static void main(String[] args) {

        /*
        HashSet
        - nie ma zapewnionej kolejnosci dodawania elemetów
        - można przeiterować ale nie można odczytać elementu po indexie
        - skupia sie aby miec dobrze zaimplementowaną metode hashCode
        - kolejnosc elementow nie jest sortowana ale przypadkowa
         */
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(1);
        integerSet.add(2);
        integerSet.add(3);
        integerSet.add(2);
        integerSet.add(1);
        for (Integer integer: integerSet) {
            System.out.println(integer);
        }

        Set<Dog> dogs = new HashSet<>();
        Dog dog1 = new Dog("Romek");
        Dog dog2 = new Dog("Zenek");
        Dog dog3 = new Dog("Romek");
        Dog dog4 = new Dog("Reksio");
        Dog dog5 = new Dog("Jurek");
        Dog dog6 = new Dog("Reks");
        dogs.add(dog1);
        dogs.add(dog2);
        dogs.add(dog3);
        dogs.add(dog4);
        dogs.add(dog5);
        dogs.add(dog6);
        System.out.println(dogs);
        /*
        aby nie dodawac powyzej duplikatow nalezy nadpisac metode equals i hasCode
         */


        Set<Dog> dogs2 = new HashSet<>();
        LocalTime before = LocalTime.now();
        for (int i = 0; i < 10000; i++) {
//            System.out.println(i/12);
            dogs2.add(new Dog("Doggi" + (i/12)));
        }
        System.out.println("Took: " + Duration.between(before, LocalTime.now()).toMillis());



        /*
        LinkHashSet:
        -  utrzymuje kolejność elementow i duplikaty są usuwane
         */
        Set<String> dogs3 = new LinkedHashSet<>();
        dogs3.add("Doggo1");
        dogs3.add("Doggo2");
        dogs3.add("Doggo3");
        dogs3.add("Doggo4");
        dogs3.add("Doggo4");
        dogs3.add("Doggo5");
        dogs3.add("Doggo6");
        dogs3.add("Doggo6");
        System.out.println(dogs3);

        /*
        TreeSet:
        - set ktory umozliwa usuwanie duplikatow i sortowanie ich
        - domyślne sortowanie jest comparator naturalOrder
        - jest to strultura drzewiasta - binarne drzewo przeszukiwań
         */
        List<Integer> integerList = Arrays.asList(1, 4, 5, 6, 2, 3, 1, 3, 4, 7, 1, 2, 52, 14, 1, 52, 6, 81, 81);

        Set<Integer> treeSet = new TreeSet<>(integerList);
        Set<Integer> hashSet = new HashSet<>(integerList);

        System.out.println("Hash: " + hashSet);
        System.out.println("Tree: " + treeSet);

        /*
        żeby zadziałał poniższy przypadek klasa musi implementowac Comparable,
        lub nalezy do treeSet przekazac comparator
         */
        Set<Dog> dogs4 = new TreeSet<>((o1, o2) -> o2.name.compareTo(o1.name));
        dogs4.add(new Dog("Romek"));
        dogs4.add(new Dog("Romek"));
        dogs4.add(new Dog("Stefan"));
        dogs4.add(new Dog("Adam"));

        System.out.println(dogs4);

        /*
        NavigableSet to rozszerzenie interfejsu TreeSet
         */
        NavigableSet<Integer> navigableSet = new TreeSet<>();
        for (int i = 0; i <= 100; i++) {
            navigableSet.add(i);
        }

        System.out.println(navigableSet.lower(50));
        System.out.println(navigableSet.floor(50));
        System.out.println(navigableSet.ceiling(90));
        System.out.println(navigableSet.higher(90));
        System.out.println(navigableSet.ceiling(100));
        System.out.println(navigableSet.higher(100));
    }

    private static class Dog implements Comparable<Dog> {
        private String name;

        public Dog(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Dog: " + name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dog dog = (Dog) o;
            return Objects.equals(name, dog.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public int compareTo(Dog o) {
            return this.name.compareTo(o.name);
        }
    }

}
