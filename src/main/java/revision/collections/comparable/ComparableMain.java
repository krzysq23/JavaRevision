package revision.collections.comparable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ComparableMain {

    public static void main(String[] args) {

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog(2, "Burek"));
        dogs.add(new Dog(6, "Rex"));
        dogs.add(new Dog(4, "Trump"));
        dogs.add(new Dog(3, "Fafik"));
        dogs.add(new Dog(1, "Roman"));
        dogs.add(new Dog(5, "Stefan"));

        System.out.println(dogs);

        Collections.sort(dogs);
        System.out.println(dogs);

    }
}
