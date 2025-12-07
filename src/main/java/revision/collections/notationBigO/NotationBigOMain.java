package revision.collections.notationBigO;

public class NotationBigOMain {

    public static void main(String[] args) {

        // Big O - służy do porównywania wydajnosci różnych algorytmów/operacji
        // O(n) (złożoność liniowa) - np. wyszukanie ile razy element powtarza sie w liście
        // O(1) (złożoność stała) - np. wyszukanie elementu po indekscie
        // O(n^2) (złożoność kwadratowa) - np. sortowanie lub porównywanie wszystkich par ze sobą (2-krotna pętla)
        // O(log n) (złożoność logarytmiczna) - szybsza niż liniowa, rośnie wolniej niż rozmiar danych, np. wyszukiwanie binarne
        // [2, 6, 87, 2, 9]

        /*

        LinkedList vs. ArrayList

        For LinkedList<E>

                get(int index) is O(n) (with n/4 steps on average), but O(1) when index = 0 or index = list.size() - 1 (in this case, you can also use getFirst() and getLast()). One of the main benefits of LinkedList<E>
        add(int index, E element) is O(n) (with n/4 steps on average), but O(1) when index = 0 or index = list.size() - 1 (in this case, you can also use addFirst() and addLast()/add()). One of the main benefits of LinkedList<E>
        remove(int index) is O(n) (with n/4 steps on average), but O(1) when index = 0 or index = list.size() - 1 (in this case, you can also use removeFirst() and removeLast()). One of the main benefits of LinkedList<E>
        Iterator.remove() is O(1). One of the main benefits of LinkedList<E>
                ListIterator.add(E element) is O(1). One of the main benefits of LinkedList<E>
                Note: Many of the operations need n/4 steps on average, constant number of steps in the best case (e.g. index = 0), and n/2 steps in worst case (middle of list)

        For ArrayList<E>

                get(int index) is O(1). Main benefit of ArrayList<E>
        add(E element) is O(1) amortized, but O(n) worst-case since the array must be resized and copied
        add(int index, E element) is O(n) (with n/2 steps on average)
        remove(int index) is O(n) (with n/2 steps on average)
        Iterator.remove() is O(n) (with n/2 steps on average)
        ListIterator.add(E element) is O(n) (with n/2 steps on average)


         */

    }
}
