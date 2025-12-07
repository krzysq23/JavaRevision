package revision.collections.queue;

import revision.collections.set.SetMain;

import java.util.*;

public class QueueMain {

    /*
    Queue jest to rodzaj struktury ktory przechowuje elementy w określonej kolejności do przetworzenia
    kolejka: first in first out
    stos: last in first oput
     */

    public static void main(String[] args) {

        /*
        ArrayDeque pod spodem znajduje sie tablica o roznym rozmiarze
        Queue ma metodode umozliwiajaca dodanie elementu z przodu
         */
        List<Integer> input = List.of(1, 5, 9, 11, 5, 28, 3, 44, 1, 2, 3, 15);

        Queue<Integer> queue = new ArrayDeque<>();
        for (Integer element: input) {
            // offer dodaje element do kolejki
            System.out.println("queue.offer " + queue.offer(element) + ", added element: " + element);
//            System.out.println(queue);
        }

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            // peek zerka na pierwszy element
            System.out.println("queue.peeK: " + queue.peek());
            System.out.println(queue);
            // pool ściaga pierwszy element z kolejki
            System.out.println("queue.peeK: " + queue.poll());
            System.out.println(queue);
        }

        // Możemy też użyć linkedList tylko nie bedzie metod co ArrayDeque
        LinkedList<Integer> linqList = new LinkedList<>();
        for (Integer element: input) {
            linqList.offer(element);
        }
        System.out.println(linqList);
        linqList.addFirst(100);
        linqList.addLast(901);
        System.out.println(linqList);



        /*
        Stack - elementy są dodawane z początku, struktura LIFO
        Stack<String> stack = new Stack<>() ---> nie jest zalecany, a powinno stosowac sie to jak ponizej:
         */
        Deque<Integer> stack = new ArrayDeque<>();
        for (Integer element: input) {
            stack.push(element);
        }
        System.out.println("Stack: " + stack);
        int sizeStack = stack.size();
        for (int i = 0; i < sizeStack; i++) {
            // peek zerka na pierwszy element
            System.out.println("stack.peeK: " + stack.peek());
            System.out.println(stack);
            // pop ściaga pierwszy element z kolejki
            System.out.println("stack.peeK: " + stack.pop());
            System.out.println(stack);
        }



        /*
         - przyjmuje korparator w konstruktorze lub stosuje defaultowy comparator
         */
        Queue<Integer> priorityQueue = new PriorityQueue<>();

        System.out.println("\n\n\nPriorityQueue:");
        for (Integer element: input) {
            priorityQueue.offer(element);
        }
        System.out.println(priorityQueue);

        int sizePriorityQueue = priorityQueue.size();
        for (int i = 0; i < sizePriorityQueue; i++) {
            System.out.println("priorityQueue.peeK: " + priorityQueue.peek());
            System.out.println(priorityQueue);
            System.out.println("priorityQueue.peeK: " + priorityQueue.poll());
            System.out.println(priorityQueue);
        }

    }

}
