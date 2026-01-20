package revision.designPatterns.structural;

import lombok.AllArgsConstructor;
import lombok.ToString;

public class BridgeExample {

    /*
        Bridge to strukturalny wzorzec projektowy, którego celem jest:
        - oddzielić abstrakcję od implementacji tak, aby mogły rozwijać się niezależnie
        Najkrócej:
        „zamiast jednej hierarchii klas – dwie współpracujące hierarchie”.
        lub
        Bridge to stworzenie interfejsu będącego mostem pomiędzy dwiema warstwami abstrakcji.

        Struktura wzorca:

        Abstraction  ──────┐
                           │ uses
        RefinedAbstraction │
                           ▼
                       Implementor
                           ▲
                   ConcreteImplementor

        • Abstraction – API dla klienta
        • Implementor – interfejs implementacji
        • Bridge – referencja do implementora

        Zalety tego podejścia:
        • Dzięki stosowaniu tego wzorca uniezależniamy się pomiędzy dwiema strukturami, które ze sobą łączymy.
        • Możemy dowolnie zmieniać hierarchię klasy Shape oraz hierarchię interfejsu Color.
          Obie te hierarchie nie będą o tym wiedziały wzajemnie.

        W poniższym przykładnie mostem jest to że klasa abstrakcyjna Shape implementuje interfejs Color,
        od której jedna jest zależna od drugiej ale obie nie wiedzą nic o sobie.

     */

    public static void main(String[] args) {

        Shape shape1 = new Square(new Red());
        System.out.println(shape1);

        Shape shape2 = new Triangle(new Green());
        System.out.println(shape2);


    }

    public interface Color {
        String apply();
    }

    @ToString
    @AllArgsConstructor
    public abstract static class Shape {
        protected Color color;
        abstract public void applyColor();
    }

    @ToString
    public static class Red implements Color {
        @Override
        public String apply() {
            return "Color is Red";
        }
    }

    @ToString
    public static class Green implements Color {
        @Override
        public String apply() {
            return "Color is Green";
        }
    }

    @ToString(callSuper = true)
    public static class Square extends Shape {
        public Square(final Color color) {
            super(color);
        }
        @Override
        public void applyColor() {
            System.out.println("Square with color: " + color.apply());
        }
    }

    @ToString(callSuper = true)
    public static class Triangle extends Shape {
        public Triangle(final Color color) {
            super(color);
        }
        @Override
        public void applyColor() {
            System.out.println("Triangle with color: " + color.apply());
        }
    }


}
