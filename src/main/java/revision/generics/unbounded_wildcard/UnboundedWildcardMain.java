package revision.generics.unbounded_wildcard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnboundedWildcardMain {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Java lerning!");

        List<Tuna> tunas = new ArrayList<>();
        tunas.add(new Tuna());

        print(strings);
        print(tunas);

//        Error:
//        List<Objects> elements = tunas;

        List<?> elements = tunas;
//        elements.add("new element");
//        elements.remove(0);
        System.out.println(elements.get(0));

        Tuna<String> tuna = new Tuna<>();
        tuna.eat("trash");

        Tuna<?> tuna2 = tuna;
        // nie da sie dodac do wildard:
        // tuna2.eat("some other trash");
        tuna2.getName();

    }

    private static void print(List<?> list) {
        for (Object o: list) {
            System.out.println(o);
        }
    }

    static class Tuna<U> {
        private U element;
        void eat(U element) {
            this.element = element;
        }
        String getName() {
            return "roman";
        }
    }
}
