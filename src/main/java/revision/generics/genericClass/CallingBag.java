package revision.generics.genericClass;

import java.util.function.Predicate;

public class CallingBag {

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        System.out.println(bag);
        bag.pack("String element");
        System.out.println(bag);
        bag.empty();
        System.out.println(bag);
        System.out.println("========= END =========");

        Bag<Car> carBag = new Bag<>();
        System.out.println(carBag);
        carBag.pack(new Car("blue", 2021));
        System.out.println(carBag);
        Car emtyCar = carBag.empty();
        System.out.println(carBag);
        System.out.println("========= END =========");

        callFilteringGag();
    }

    private static void callFilteringGag() {
        Car car = new Car("red2", 2020);
        FilteringBag<Car, Predicate<Car>, Predicate<Car>> filteringBag = new FilteringBag<>(
                c -> c.getColor().equals("red"),
                c -> c.getYear().equals(2020)
        ) ;
        filteringBag.pack(car);

    }

    static class Car {
        private String color;

        private Integer year;

        public Car(String color, int year) {
            this.color = color;
            this.year = year;
        }

        public String getColor() {
            return color;
        }

        public Integer getYear() {
            return year;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setYear(Integer year) {
            this.year = year;
        }
    }

}
