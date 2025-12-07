package revision.collections.map;

import java.util.*;

public class MapMain {

    /*
        Map - zbiór odwzorowujacy strukture klucz -> wartość. Można nazywać słownik
        - zaliczana do colection ale nie roszerza interfejsu collection
     */

    public static void main(String[] args) {

        /*
        HashMap - nie zachowuje kolejnosci dodawania elemetów
        - stara sie optymalizaowac przechowywanie poprzez hashCode
        - zduplikowany klucz sie nadpisuje
         */
        Map<String, String> someMap = new HashMap<>();
        someMap.put("1", "ABC");
        someMap.put("2", "DEF");
        someMap.put("3", "GHI");
        someMap.put("1", "JKL");
        someMap.put("5", "MNO");

        System.out.println(someMap);

        for (String s: someMap.keySet()) {
            System.out.print("K: " + s);
        }
        System.out.println();
        for (String s: someMap.values()) {
            System.out.print("V: " + s);
        }
        System.out.println();
        for (Map.Entry<String, String> entry : someMap.entrySet()) {
            System.out.print(entry.getKey() + " = ś" + entry.getValue());
        }
        System.out.println();

        System.out.println(someMap.getOrDefault("13", "no nie ma"));

        Map<Dog, String> dogsHM = new HashMap<>();
        Dog dog1 = new Dog("Romek");
        Dog dog2 = new Dog("Stefan");
        Dog dog3 = new Dog("Romek");
        Dog dog4 = new Dog("Zbyszek");
        dogsHM.put(dog1, "abc");
        dogsHM.put(dog2, "def");
        dogsHM.put(dog3, "ghi");
        dogsHM.put(dog4, "jkl");

        System.out.println(dogsHM);

        System.out.println(dogsHM.get(dog1));
        System.out.println(dogsHM.get(dog3));

        dog1.setName("Waldek");
        System.out.println(dogsHM.get(dog1));
        dog1.setName("Romek");
        System.out.println(dogsHM.get(dog1));

        /*
        LinkedHashMap trzyma kolejnosc dodawanych kluczy
         */
        Map<String, Integer> someLM = new LinkedHashMap<>();
        someLM.put("A", 123);
        someLM.put("D", 123);
        someLM.put("B", 123);
        someLM.put("F", 123);
        someLM.put("E", 123);
        someLM.put("C", 123);
        System.out.println(someLM);

        /*
        TreeMap - struktura drzewowa
         */
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("1A", "some 1");
        treeMap.put("A5", "some 2");
        treeMap.put("G5", "some 3");
        treeMap.put("a2", "some 4");
        treeMap.put("4c", "some 5");
        treeMap.put("o9", "some 6");
        System.out.println(treeMap);

        for (String s: treeMap.keySet()) {
            System.out.print("K: " + s);
        }
        System.out.println();
        for (String s: treeMap.values()) {
            System.out.print("V: " + s);
        }
        System.out.println();

        Map<Dog, String> treeMap2 = new TreeMap<>((o1, o2) -> o1.name.compareTo(o2.name));
        treeMap2.put(new Dog("Pies 5"), "some 1");
        treeMap2.put(new Dog("Pies 3"), "some 2");
        treeMap2.put(new Dog("Pies 2"), "some 3");
        treeMap2.put(new Dog("Pies 6"), "some 4");
        treeMap2.put(new Dog("Pies 4"), "some 5");
        treeMap2.put(new Dog("Pies 1"), "some 6");
        for (Map.Entry<Dog, String> entry: treeMap2.entrySet()) {
            System.out.print(entry);
        }

    }

    public static class Dog {
        private String name;

        public Dog(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
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
    }
}
