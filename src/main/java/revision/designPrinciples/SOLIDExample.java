package revision.designPrinciples;

import lombok.AllArgsConstructor;

public class SOLIDExample {

    /*
        SOLID - są to zasady, których pierwsze litery tworzą skrót SOLID (stworzone przez "Uncle Bob").
        Zasady są stworzone po to aby kod był czysty, przyjemny w odbiorze, odczycie oraz zarządzaniu.

        1. Single Responsibility Principle - SRP - klasa powinna mieć jedną, ale to tylko jedną odpowiedzialność
        2. Open/Closed Principle - OCP - Klasy/metody mają być otwarte na rozszerzenia, a zamknięte na modyfikacje
        3. Liskov Substitution Principle - LSP - jeżeli operujemy na klasach bazowych i klasach
            pochodnych, to klasy pochodne powinny być w stanie zastępować swoje klasy bazowe bez zmiany zachowania kodu
        4. Interface Segregation Principle - ISP - klasa implementująca interface nie powinna być zmuszona do polegania
            na interfejsie, którego nie używa w całości. Czyli interface powinien zawierać tylko minimalny zestaw metod,
            które są niezbędne żeby zapewnić funkcjonalność. Jednocześnie zestaw tych metod powinien być ograniczony
            tylko do tej jednej funkcjonalności
        5. Dependency Inversion Principle - DIP - moduły wysokiego poziomu i moduły niskiego
            poziomu powinny być ze sobą łączone w taki sposób, aby wprowadzanie zmian w modułach
            niskopoziomowych nie wymagało wprowadzania zmian w modułach wysokopoziomowych. Innymi
            słowami, zarówno moduły wysoko jak i niskopoziomowe nie powinny zależeć bezpośrednio od siebie,
            ale powinny zależeć od abstrakcji, jaką są interfejsy.

        Dodatkowo dla DIP:
        • Inversion of Control - IoC - jest traktowany jako Design Pattern (wzorzec projektowy).
            Wzorzec ten zaleca odwrócenie jakiegokolwiek rodzaju kontroli podczas projektowania programu
            zorientowanego na obiekty po to żebyśmy osiągnęli luźne powiązania pomiędzy klasami.

            Czytając o IoC natkniemy się na stwierdzenie IoC Container. IoC Container oznacza framework (nie
            konkretny framework tylko rodzaj frameworka), który implementuje automatyczne DI. Framework taki
            zarządza wtedy tworzeniem obiektów oraz wstrzykiwaniem zależności między klasami (Spring).

            Example:
            class A {
                private B b;
                private C c;
                // brak kontroli nad implementacja/usztywnienei bez IoC
                public A() {
                    this.b = new BImpl();
                    this.c = new C();
                }
                // kontrola nad implementacja z IoC
                public A(B b, C c) {
                    this.b = b;
                    this.c = c;
                }
            }

        • Dependency Injection - DI - o DI mówi się, że jest to Design Pattern (wzorzec projektowy), który jest implementacją IoC.
            DI jest techniką, w której obiekt otrzymuje od innego obiektu swoje gotowe przygotowane wcześniej zależności.

            Example:
            class Factory {
                void create() {
                    C c = new B();
                    A a = new A(c);
                }
            }




        Example:
        • Single Responsibility Principle (SRP) - każda klasa ma jedną odpowiedzialność. Klasa Car i Motorcycle
            są odpowiedzialne tylko za przechowywanie informacji o pojeździe i jego podzespołach oraz
            implementację interfejsu Vehicle, a klasy Engine, Transmission, Brakes i Accelerator są
            odpowiedzialne tylko za realizację odpowiednich funkcji.
        • Open/Closed Principle (OCP) - klasy są otwarte na rozszerzenie, ale zamknięte na modyfikację. Jeśli
            chcemy dodać nowy typ pojazdu, wystarczy utworzyć nową klasę implementującą interfejs Vehicle,
            bez modyfikowania istniejącego kodu.
        • Liskov Substitution Principle (LSP) - każda klasa implementująca interfejs Vehicle może być użyta w
            miejsce interfejsu Vehicle bez zmiany zachowania aplikacji.
        • Interface Segregation Principle (ISP) - interfejs Vehicle zawiera tylko te metody, które są niezbędne
            dla każdego pojazdu, a pozostałe funkcje zostały wydzielone do innych interfejsów (Transmission,
            Brakes, Accelerator).
        • Dependency Inversion Principle (DIP), która mówi, że moduły wyższego poziomu nie powinny zależeć
            od modułów niższego poziomu, oba powinny zależeć od abstrakcji, a nie od konkretnych
            implementacji. Zależności są zdefiniowane w konstruktorach Car i Motorcycle, co pozwala na łatwe
            podmienianie implementacji bez zmiany istniejącego kodu. W ten sposób implementacja modułów
            niższego poziomu nie wpływa na moduły wyższego poziomu, co ułatwia rozwijanie i testowanie aplikacji.
     */

    public static void main(String[] args) {
        Engine engine = new Engine();
        Transmission automaticTransmission = new AutomaticTransmission();
        Transmission manualTransmission = new ManualTransmission();
        Brakes hydraulicBrakes = new HydraulicBrakes();
        Brakes discBrakes = new DiscBrakes();
        Accelerator electronicAccelerator = new ElectronicAccelerator();
        Accelerator twistGripAccelerator = new TwistGripAccelerator();
        Vehicle car = new Car(engine, automaticTransmission, hydraulicBrakes, electronicAccelerator);
        Vehicle motorcycle
                = new Motorcycle(engine, manualTransmission, discBrakes, twistGripAccelerator);
        car.startEngine();
        motorcycle.startEngine();
        car.accelerate();
        motorcycle.accelerate();
        car.brake();
        motorcycle.brake();
        car.stopEngine();
        motorcycle.stopEngine();
    }

    interface Accelerator {
        void accelerate();
    }

    interface Brakes {
        void apply();
    }

    interface Transmission {
        void shiftUp();
        void shiftDown();
    }

    interface Vehicle {
        void startEngine();
        void stopEngine();
        void accelerate();
        void brake();
    }

    static class AutomaticTransmission implements Transmission {
        public void shiftUp() {
            System.out.println("Automatic transmission shifted up.");
        }
        public void shiftDown() {
            System.out.println("Automatic transmission shifted down.");
        }
    }

    @AllArgsConstructor
    static
    class Car implements Vehicle {
        private Engine engine;
        private Transmission transmission;
        private Brakes brakes;
        private Accelerator accelerator;
        @Override
        public void startEngine() {
            engine.start();
        }
        @Override
        public void stopEngine() {
            engine.stop();
        }
        @Override
        public void accelerate() {
            accelerator.accelerate();
            transmission.shiftUp();
        }
        @Override
        public void brake() {
            brakes.apply();
        }
    }

    static class DiscBrakes implements Brakes {
        public void apply() {
            System.out.println("Disc brakes applied.");
        }
    }

    static class ElectronicAccelerator implements Accelerator {
        public void accelerate() {
            System.out.println("Electronic accelerator pressed.");
        }
    }

    static class Engine {
        public void start() {
            System.out.println("Engine started.");
        }
        public void stop() {
            System.out.println("Engine stopped.");
        }
    }

    static class HydraulicBrakes implements Brakes {
        public void apply() {
            System.out.println("Hydraulic brakes applied.");
        }
    }

    static class ManualTransmission implements Transmission {
        public void shiftUp() {
            System.out.println("Manual transmission shifted up.");
        }
        public void shiftDown() {
            System.out.println("Manual transmission shifted down.");
        }
    }

    @AllArgsConstructor
    static
    class Motorcycle implements Vehicle {
        private Engine engine;
        private Transmission transmission;
        private Brakes brakes;
        private Accelerator accelerator;
        @Override
        public void startEngine() {
            engine.start();
        }
        @Override
        public void stopEngine() {
            engine.stop();
        }
        @Override
        public void accelerate() {
            accelerator.accelerate();
            transmission.shiftUp();
        }
        @Override
        public void brake() {
            brakes.apply();
        }
    }

    static class TwistGripAccelerator implements Accelerator {
        public void accelerate() {
            System.out.println("Twist grip accelerator twisted.");
        }
    }


}