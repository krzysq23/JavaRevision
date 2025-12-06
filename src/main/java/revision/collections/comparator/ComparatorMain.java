package revision.collections.comparator;

import revision.collections.Cat;

import java.util.*;

public class ComparatorMain {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("a", "A", "1", "aA", "Aa", "A1", "a1");
        System.out.println(strings);
//        Comparator<String> naturalOrder = Comparator.naturalOrder();
//        Comparator<String> comparator = naturalOrder.reversed();
        Comparator<String> comparator = Comparator.<String>naturalOrder().reversed();
        strings.sort(comparator);
        System.out.println(strings);

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat(1, "Marek"));
        cats.add(new Cat(6, "Darek"));
        cats.add(new Cat(3, "Zbyszek"));
        cats.add(new Cat(7, "Adam"));
        cats.add(new Cat(5, "Max"));
        cats.add(new Cat(4, "Wiesiek"));
        cats.add(new Cat(2, "Adam"));
        System.out.println(cats);

        Comparator<Cat> compare1 = new Comparator<>(){
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getId() - o2.getId();
            }
        };

        cats.sort(compare1);
        System.out.println(cats);

        Comparator<Cat> compare2 = new Comparator<>(){
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        cats.sort(compare2);
        System.out.println(cats);

        cats.sort(compare2.reversed());
        System.out.println(cats);

        Comparator<Cat> compare3 = new Comparator<Cat>(){
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }.thenComparing(new Comparator<Cat>() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.getId() - o2.getId();
            }
        });

        cats.sort(compare3);
        System.out.println(cats);

        Comparator<Cat> compare4 = new Comparator<>(){
            @Override
            public int compare(Cat o1, Cat o2) {
                int result = o1.getName().compareTo(o2.getName());
                if (result != 0) return result;
                return o1.getId() - o2.getId();
            }
        };

        cats.sort(compare4);
        System.out.println(cats);

        List<Cat> cats2 = new ArrayList<>();
        cats2.add(new Cat(1, "Marek"));
        cats2.add(new Cat(6, "Darek"));
        cats2.add(new Cat(7, "Adam"));
        cats2.add(new Cat(3, "Zbyszek"));
        cats2.add(new Cat(5, "Max"));
        cats2.add(new Cat(4, "Wiesiek"));
        cats2.add(new Cat(2, "Adam"));
        System.out.println("====== Lambda ======");
        System.out.println(cats2);

        Comparator<Cat> compareLambda = Comparator.comparing((Cat cat) -> cat.getName()).reversed();
        // Comparator<Cat> compareLambda2 = (o1, o2) -> o1.getName().compareTo(o2.getName());

        compareLambda = compareLambda.thenComparing((o1, o2) -> o1.getId() - o2.getId());

        cats2.sort(compareLambda);
        System.out.println(cats);

        Comparator<Cat> comparator3 = Comparator.<Cat, String>comparing(cat1 -> cat1.getName())
                        .thenComparing(cat -> cat.getId());

        cats2.sort(comparator3);
        System.out.println(cats);

    }

}
