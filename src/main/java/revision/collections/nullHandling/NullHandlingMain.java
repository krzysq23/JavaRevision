package revision.collections.nullHandling;

import java.util.*;

public class NullHandlingMain {

    public static void main(String[] args) {
        Map<String, Integer> trreMap = new TreeMap<>();
//        trreMap.put("1", 1);
//        trreMap.put(null, 1);
//        System.out.println(trreMap);
//        NullPointerException

        Hashtable<Integer, String> hashtable = new Hashtable<>();
//        hashtable.put(2, "2");
//        hashtable.put(null, "2");
//        hashtable.put(3, null);
//        System.out.println(hashtable);
//        NullPointerException

        Set<Integer> treeSet = new TreeSet<>();
//        treeSet.add(1);
//        treeSet.add(null);
//        System.out.println(trreMap);
//        NullPointerException

        Queue<String> queue = new ArrayDeque<>();
//        queue.offer("123");
//        queue.offer(null);
//        System.out.println(queue);
//        NullPointerException




        /*
        Poniżej obejścia NullPointerException
         */
        Set<Integer> treeSetWithoutNull = new TreeSet<>((o1,o2) -> {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return o1 - o2;
        });
        treeSetWithoutNull.add(1);
        treeSetWithoutNull.add(null);
        treeSetWithoutNull.add(1);
        treeSetWithoutNull.add(2);
        treeSetWithoutNull.add(null);
        System.out.println(treeSetWithoutNull);

        Set<Integer> treeSetWithNull = new TreeSet<>((o1,o2) -> {
            if (o1 == null && o2 != null) {
                return -1;
            }
            if (o1 != null && o2 == null) {
                return 1;
            }
            if (o1 == null || o2 == null) {
                return 0;
            }
            return o1 - o2;
        });
        treeSetWithNull.add(1);
        treeSetWithNull.add(null);
        treeSetWithNull.add(1);
        treeSetWithNull.add(2);
        treeSetWithNull.add(null);
        System.out.println(treeSetWithNull);

        Comparator<Integer> comparator = Comparator.nullsFirst(Integer::compareTo);
        Set<Integer> treeSetWithNull2 = new TreeSet<>(comparator);
        treeSetWithNull2.add(1);
        treeSetWithNull2.add(null);
        treeSetWithNull2.add(1);
        treeSetWithNull2.add(2);
        treeSetWithNull2.add(null);
        System.out.println(treeSetWithNull2);

    }
}
