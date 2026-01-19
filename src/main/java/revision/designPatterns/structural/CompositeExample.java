package revision.designPatterns.structural;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import revision.ioStream.model.Door;

import java.util.ArrayList;
import java.util.List;

public class CompositeExample {

    /*
        Jeżeli mamy rodzinę obiektów, które mają być potraktowane w naszym kodzie dokładnie w ten sam
        sposób - zastosujemy wzorzec Composite (kompozyt).
        Krótko:
        „Klient nie musi wiedzieć, czy pracuje z jednym obiektem, czy z ich grupą.”

        Wyróżnimy 3 rodzaje obiektów, aby zaimplementować ten wzorzec:
        • Base component - Komponent bazowy - będzie to interfejs, który będzie reprezentował każde
          zwierzątko w ZOO. Wywołując kod, który nakarmi zwierzątka, wywołamy metodę z tego interfejsu.
        • Leaf - liść - obiekty, które zdefiniują zachowania poszczególnych zwierzątek. Zwierzątko będzie
          implementowało base component. Zwierzęta nie wiedzą też nic o sobie wzajemnie.
        • Composite - kompozyt - czyli klasa, która będzie zawierała w sobie kolekcję liści i wywoływała
          metody z każdego z nich.

                Component
                 /    \
              Leaf   Composite
                        |
                     children

        • Component – wspólny interfejs -> Animal
        • Leaf – element prosty -> Monkey/Tiger/Elephant
        • Composite – element złożony (zawiera dzieci) -> ZOO

     */

    public static void main(String[] args) {

        ZOO zoo = new ZOO();
        zoo.add(new Monkey());
        zoo.add(new Elephant());
        zoo.add(new Tiger());
        zoo.add(new Tiger());

        Food food = new Food("leaf");
        System.out.println(food);
        zoo.eat(food);

    }

    @Data
    @AllArgsConstructor
    public static class ZOO implements Animal {

        private final List<Animal> animals = new ArrayList<>();

        @Override
        public void eat(Food food) {
            animals.forEach(animal -> animal.eat(food));
        }

        public void add(Animal animal) {
            this.animals.add(animal);
        }

        public void remove(Animal animal) {
            this.animals.remove(animal);
        }

        public void clear() {
            System.out.println("Removing all animals");
            this.animals.clear();
        }
    }

    public interface Animal {
        void eat(Food food);
    }

    @AllArgsConstructor
    @ToString
    public static class Food {
        private String food;
    }

    public static class Monkey implements Animal {
        @Override
        public void eat(final Food food) {
            System.out.println("Monkey eating food: " + food);
        }
    }

    public static class Tiger implements Animal {
        @Override
        public void eat(final Food food) {
            System.out.println("Tiger eating food: " + food);
        }
    }

    public static class Elephant implements Animal {
        @Override
        public void eat(final Food food) {
            System.out.println("Elephant eating food: " + food);
        }
    }

}
