package revision.designPatterns.behavioral;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class VisitorExample {

    /*
        Visitor to behawioralny wzorzec projektowy, który odpowiada na pytanie:
        - Jak dodać nowe operacje do istniejącej struktury obiektów, bez modyfikowania ich klas?
        Najprościej:
        „Logika jest w gościu (visitorze), a obiekty tylko go przyjmują.”

        Visitor jest wzorcem, który jest stosowany w przypadku, gdy mamy wywołać jakąś operację na grupie
        podobnych do siebie obiektów. Zamiast definiować bardzo podobne zachowanie dla każdego ze
        wspomnianych obiektów, możemy przenieść podobną grupę zachowań w jedno miejsce. Zwiększa to
        czytelność, bo podobna grupa zachowań jest wtedy dostępna w jednym miejscu w kodzie, a nie w wielu klasach.

        Struktura wzorca
        Client → Visitor
                    ▲
                    │ visit(ElementA)
                    │ visit(ElementB)
                Element (accept)
                    ▲
               ConcreteElements

        • Element – interfejs z accept
        • ConcreteElement – konkretne elementy struktury
        • Visitor – interfejs operacji
        • ConcreteVisitor – konkretne operacje

        Visitor w Javie:
        • javax.lang.model.element.ElementVisitor
        • kompilatory (AST)
        • analizatory kodu
        • eksport danych (PDF, XML, JSON)

        Zalety tego podejścia:
        • Jeżeli zmianie ulegnie logika, która jest określona w klasie Visitor, musimy dokonać zmiany tylko w
          jednym miejscu w kodzie, a nie w wielu.
        • Możemy modyfikować wybraną logikę (tą która jest w Visitorze) bez modyfikacji samego obiektu.
        • Możemy zaimplementować kilku Visitorów, gdzie każdy może implementować logikę na inny
          sposób, np. jeden wizytor może zapisywać informacje do plików na dysku, a drugi w bazie danych.
     */

    public static void main(String[] args) {

        System.out.println("Example 1:");
        List<Shape> shapes = List.of(
                new Circle(2),
                new Rectangle(3, 4)
        );

        ShapeVisitor visitor = new AreaCalculator();

        for (Shape s : shapes) {
            s.accept(visitor);
        }

        System.out.println("\nExample 2");
        List<ShoppingCartElement> elements = List.of(
                new Bicycle(BigDecimal.valueOf(100.25), BigDecimal.TEN),
                new Bicycle(BigDecimal.valueOf(420.99), BigDecimal.valueOf(10.20)),
                new Apple(BigDecimal.valueOf(9.80), BigDecimal.valueOf(2.34)),
                new Apple(BigDecimal.valueOf(9.80), BigDecimal.valueOf(6.12))
        );
        ShoppingCartVisitor shopVisitor = new ShoppingCartVisitorImpl();
        BigDecimal totalCost = elements.stream()
                .map(element -> element.accept(shopVisitor))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total shopping cart cost: " + totalCost);

        System.out.println("\nExample 3");
        List<ShoppingCartElement2> elementsList = List.of(
                new Banana(BigDecimal.valueOf(100.25), BigDecimal.TEN),
                new Banana(BigDecimal.valueOf(420.99), BigDecimal.valueOf(10.20)),
                new Car(BigDecimal.valueOf(9.80), BigDecimal.valueOf(2.34)),
                new Car(BigDecimal.valueOf(9.80), BigDecimal.valueOf(6.12))
        );
        ShoppingCartVisitorMap visitorMap = new ShoppingCartVisitorMap();
        BigDecimal totalCost2 = elementsList.stream()
                .map(element -> element.accept(visitorMap.apply(element.getClass())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total shopping cart cost: " + totalCost2);

    }

    /*
        Example 1
     */
    interface Shape {
        void accept(ShapeVisitor visitor);
    }

    interface ShapeVisitor {
        void visit(Circle circle);
        void visit(Rectangle rectangle);
    }

    static class Circle implements Shape {

        double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class Rectangle implements Shape {

        double width, height;

        Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public void accept(ShapeVisitor visitor) {
            visitor.visit(this);
        }
    }

    static class AreaCalculator implements ShapeVisitor {

        public void visit(Circle circle) {
            System.out.println(Math.PI * circle.radius * circle.radius);
        }

        public void visit(Rectangle rectangle) {
            System.out.println(rectangle.width * rectangle.height);
        }
    }


    /*
        Example 2
     */
    public interface ShoppingCartElement {
        BigDecimal accept(ShoppingCartVisitor visitor);
    }

    public interface ShoppingCartVisitor {
        BigDecimal visit(Apple apple);
        BigDecimal visit(Bicycle bicycle);
    }

    @Data
    @AllArgsConstructor
    public static class Apple implements ShoppingCartElement {

        private BigDecimal pricePerKg;
        private BigDecimal weight;

        @Override
        public BigDecimal accept(final ShoppingCartVisitor visitor) {
            return visitor.visit(this);
        }
    }

    @Data
    @AllArgsConstructor
    public static class Bicycle implements ShoppingCartElement {

        private BigDecimal price;
        private BigDecimal discount;

        @Override
        public BigDecimal accept(final ShoppingCartVisitor visitor) {
            return visitor.visit(this);
        }
    }

    public static class ShoppingCartVisitorImpl implements ShoppingCartVisitor {
        @Override
        public BigDecimal visit(final Apple apple) {
            BigDecimal totalCost = apple.getWeight().multiply(apple.getPricePerKg());
            System.out.println("Calculated Apple cost: " + totalCost);
            return totalCost;
        }

        @Override
        public BigDecimal visit(final Bicycle bicycle) {
            BigDecimal totalCost = bicycle.getPrice().subtract(bicycle.getDiscount());
            System.out.println("Calculated Bicycle cost: " + totalCost);
            return totalCost;
        }
    }

    /*
        Example 3
     */
    public interface ShoppingCartElement2 {
        default BigDecimal accept(ShoppingCartVisitor2 visitor) {
            return visitor.visit(this);
        }
    }

    @FunctionalInterface
    public interface ShoppingCartVisitor2 {
        BigDecimal visit(ShoppingCartElement2 shoppingCartElement);
    }

    @Data
    @AllArgsConstructor
    public static class Banana implements ShoppingCartElement2 {
        private BigDecimal pricePerKg;
        private BigDecimal weight;
    }

    @Data
    @AllArgsConstructor
    public static class Car implements ShoppingCartElement2 {
        private BigDecimal price;
        private BigDecimal discount;
    }

    public static class ShoppingCartVisitorMap implements Function<Class<? extends ShoppingCartElement2>, ShoppingCartVisitor2> {

        private static final Map<Class<? extends ShoppingCartElement2>, ? extends ShoppingCartVisitor2>
                VISITORS = Map.of(
                Banana.class, element -> visit((Banana) element),
                Car.class, element -> visit((Car) element)
        );

        public static BigDecimal visit(final Banana banana) {
            BigDecimal totalCost = banana.getWeight().multiply(banana.getPricePerKg());
            System.out.println("Calculated Banana cost: " + totalCost);
            return totalCost;
        }

        public static BigDecimal visit(final Car car) {
            BigDecimal totalCost = car.getPrice().subtract(car.getDiscount());
            System.out.println("Calculated Car cost: " + totalCost);
            return totalCost;
        }

        @Override
        public ShoppingCartVisitor2 apply(final Class<? extends ShoppingCartElement2> aClass) {
            return VISITORS.get(aClass);
        }
    }
}

