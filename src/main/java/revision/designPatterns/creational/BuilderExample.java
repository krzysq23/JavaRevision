package revision.designPatterns.creational;

import lombok.ToString;

public class BuilderExample {

    /*
        Builder służy do tego, aby móc za każdym razem na etapie
        tworzenia obiektu wybrać pola, które mają zostać zainicjowane.
        W praktyce tego wzorca używa się bardzo często, dlatego też warto
        jest pamiętać o adnotacji @Builder, która przychodzi razem z Lombok.

        Na rozwiązanie składa się kilka kroków:
        • w pierwszym kroku utworzymy statyczną klasę zagnieżdżoną, która będzie miała w nazwie Builder,
          czyli będzie to np. CarBuilder. Następnie skopiujemy do tej klasy wszystkie pola, które są
          zdefiniowane w klasie, której obiekt będziemy budować, np. Car.
        • klasa Car powinna mieć statyczną metodę builder(), która tworzy instancję klasy CarBuilder.
        • klasa CarBuilder powinna mieć zdefiniowane metody, które ustawią wartości konkretnych
          parametrów, a następnie zwrócą instancję klasy CarBuilder.
        • w klasie Car definiujemy prywatny konstruktor, który jako argument przyjmuje klasę CarBuilder, a
          następnie inicjuje pola na podstawie wartości pól z klasy CarBuilder.
        • w Klasie CarBuilder definiujemy metodę build(), która wywoła konstruktor prywatny z klasy Car.
     */

    public static void main(String[] args) {

        Car car = Car.builder()
                .brand("Ford")
                .model("Mustang")
                .year(1969)
                .skiRack("skiRack")
                .build();

        System.out.println(car);
    }

    @ToString
    public static class Car {

        private final String brand;
        private final String model;
        private final Integer year;
        private final String towbar;
        private final String sunRoof;
        private final String skiRack;

        private Car(final CarBuilder carBuilder) {
            this.brand = carBuilder.brand;
            this.model = carBuilder.model;
            this.year = carBuilder.year;
            this.towbar = carBuilder.towbar;
            this.sunRoof = carBuilder.sunRoof;
            this.skiRack = carBuilder.skiRack;
        }

        public static CarBuilder builder() {
            return new CarBuilder();
        }

        public static class CarBuilder {

            private String brand;
            private String model;
            private Integer year;
            private String towbar;
            private String sunRoof;
            private String skiRack;

            public CarBuilder brand(final String brand) {
                this.brand = brand;
                return this;
            }

            public CarBuilder model(final String model) {
                this.model = model;
                return this;
            }

            public CarBuilder year(final Integer year) {
                this.year = year;
                return this;
            }

            public CarBuilder towbar(final String towbar) {
                this.towbar = towbar;
                return this;
            }

            public CarBuilder sunRoof(final String sunRoof) {
                this.sunRoof = sunRoof;
                return this;
            }

            public CarBuilder skiRack(final String skiRack) {
                this.skiRack = skiRack;
                return this;
            }

            public Car build() {
                return new Car(this);
            }
        }

    }

}
