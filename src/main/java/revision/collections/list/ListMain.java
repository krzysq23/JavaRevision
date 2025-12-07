package revision.collections.list;

import java.util.*;

public class ListMain {
    /*
    Listy:
    - elementy moga byc zdupikowane
    - kolejnosc taka jaka dodajemy do nia elementy
    - dostep poprzez indexy
    - długość jest definiowana automatycznie
     */

    public static void main(String[] args) {

        /*
        - ArrayList pod spodem ma tablice, która ArrayList'a zarzadza.
        - Wyszukiwanie elementu jest stałe w czasie
        - używa sie jej cześściej z niej czytami niż do niej piszemy
        - tablica jeśli przekroczy rozmiar lub usunie sie element z niej to jest przepisywana do nowej tablicy
        - dodawanie i usuwanie elementu jest dłuższe niż czytanie elementu
         */
        List<String> stringList = new ArrayList<>();

        /*
        - LinkedLista jest budowana inaczej niz ArrayList
        - nadaje sie do doawania do niej elementów a mniej do odczytywania
        - czytanie trwa dłużej bo należy odczytywać po kolei kolejne elementy w liście
        - nie jest dobrym wyborem jeśli chcemy czytać elementy o danym indexie
        - tablica nie puchnie bo nie musi być przepisywana do nowej
         */
        List<String> stringLinkedList = new LinkedList<>();

        // inicjalizacja
        List<String> list1 = Arrays.asList("a", "b", "c");
        List<String> list2 = List.of("a", "b", "c");
        List<String> list3 = Collections.singletonList("a");
        List<String> list4 = Collections.emptyList();
        List<String> list5 = new LinkedList<>();
        List<String> list6 = new ArrayList<>();

        /*
        Listy od 1 do 4 są niemutowane i nie można operować na nich poprzed dodawanie i usuwanie
        - list1.add("a") -> error
         */

        list6.add("a");
        list6.add("b");
        list6.add("c");
        list6.add(2, "d");
        list6.add("e");
        list6.add("f");
        System.out.println(list6);
        System.out.println(list6.get(3));
        System.out.println(list6.indexOf("c"));
        list6.remove("a");
        list6.remove(4);
        System.out.println(list6);
        list6.set(2, "1234");
        System.out.println(list6);
    }

}
