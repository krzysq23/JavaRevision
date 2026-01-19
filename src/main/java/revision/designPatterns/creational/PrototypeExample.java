package revision.designPatterns.creational;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrototypeExample {

    /*
        Prototype jest stosowany gdy potrzebujemy stworzyć obiekt, którego stworzenie jest kosztowne w
        rozumieniu czasu i zasobów. Jednocześnie możemy skorzystać z tego, że podobny obiekt już istnieje.

        Wzorzec ten może zostać zaimplementowany przy wykorzystaniu interfejsu Cloneable i metody clone(),
        która pozwala nam stworzyć kopię obiektu.

        Zalety tego podejścia:
        • Wzorzec Prototype pozwala skorzystać z istniejących już obiektów w celu oszczędzenia czasu przy
          tworzeniu nowego złożonego obiektu,
        • Podejście to jest bardzo przydatne gdy chcemy zapisywać historię aktualizacji obiektu. Tworzymy
          wtedy kopię istniejącego już obiektu i zmieniamy tylko te wartości, które zostały zaktualizowane,
        • Stosujemy zasadę DRY, czyli pozbywamy się zduplikowanego kodu, który mógłby służyć do
          tworzenia różnych wariantów tego samego obiektu,
        • Tworzenie bardzo złożonych obiektów jest o wiele prostsze i wygodniejsze.


        Dodatkowo:
        1. Dodając @With z @AllArgsConstructor (Lombook) do PrototypeCar osiegniemy to samo bez clone() (Kopia płytka!).Np.:

        PrototypeCar original = new PrototypeCar();
        List<String> newDoors = new ArrayList<>(origin.getDoors());
        newDoors.add("back door");
        PrototypeCar cloned = origin.withDoors(newDoors);

        2. Admotacja @Builder(toBuilder = true) pozwala na pomowne przejście do build() na stworzonymobiekcie. Np.:

         PrototypeCar cloned = origin.toBuilder()
            .doors(newDoors)
            .build();

     */

    @SneakyThrows
    public static void main(String[] args) {

        PrototypeCar original = new PrototypeCar();
        System.out.println("original: " + original);

        PrototypeCar cloned = original.clone();
        System.out.println("cloneCar: " + cloned);

        System.out.println("original == cloneCar: " + (original == cloned));

        System.out.println(original.getSteeringWheel() == cloned.getSteeringWheel());
        System.out.println(original.getBrand() == cloned.getBrand());
        System.out.println(original.getModel() == cloned.getModel());
        System.out.println(original.getDoors() == cloned.getDoors());

        System.out.println("before adding: " + cloned);
        cloned.getDoors().add("back door");

        System.out.println("after adding proto: " + original);
        System.out.println("after adding cloned: " + cloned);

    }

    @Data
    public static class PrototypeCar implements Cloneable {

        private String brand;
        private String model;
        private SteeringWheel steeringWheel;
        private List<String> doors;

        public PrototypeCar() {
            this.brand = "Ford";
            this.model = "Mustang";
            this.steeringWheel = SteeringWheel.of(12.34);
            this.doors = new ArrayList<>(
                    Arrays.asList("left front", "right front", "left rear", "right rear"));
        }

        // deep copy - kopia głęboka
        @Override
        protected PrototypeCar clone() throws CloneNotSupportedException {
            final PrototypeCar clone = (PrototypeCar) super.clone();
            clone.steeringWheel = this.steeringWheel.clone();
            clone.doors = new ArrayList<>(this.doors);
            return clone;
        }

        // shallow copy - kopia płytka
        /*
        @Override
        protected PrototypeCar clone() throws CloneNotSupportedException {
            return (PrototypeCar) super.clone();
        }
        */
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "of")
    static class SteeringWheel implements Cloneable {

        private double diameter;

        public void diameter(double diameter) {
            this.diameter = diameter;
        }

        @Override
        protected SteeringWheel clone() throws CloneNotSupportedException {
            final SteeringWheel clone = (SteeringWheel) super.clone();
            clone.diameter = this.diameter;
            return clone;
        }

    }

}
