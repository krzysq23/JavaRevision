package revision.designPrinciples;

import lombok.AllArgsConstructor;

public class CompositionOverInheritance {

    /*

     Composition over Inheritance - Kompozycja ponad dziedziczenie.
     Inheritance == Dziedziczenie

     Dziedziczenie (Inheritance) - relacja is-a :
     Zalety
        • Zapewnia strukturę hierarchiczną
        • Pozwala na reużycie kodu z klasy rodzica, wracamy tutaj do DRY.
     Wady
        • szczegóły implementacji klasy rodzica niejako wyciekają do klasy dzieci,
        • Bardzo usztywnia strukturę,
        • Małe zmiany w klasie rodzica są przerzucane automatycznie do klas dzieci,
        • Zmienne i metody z klasy rodzica mogą być widoczne w klasie dziecka, nawet jeżeli nie są w niej używane.

    Kompozycja (Composition) - relacja has-a:
    Zalety
        • Stajemy się mniej zależni od innych klas niż w przypadku dziedziczenia,
        • Możemy zastosować pełną enkapsulację, gdyż nie wprowadzamy tutaj pojęcia rodzica, ani dziecka,
        więc dziecko nie widzi informacji z rodzica.
    Wady
        • Często wymaga to napisania większej ilości kodu niż w przypadku dziedziczenia,
        • Zrozumienie działania kodu może być trudniejsze, ponieważ w przypadku kompozycji opieramy się
        na referencjach. Pod daną referencją może znajdować się wiele obiektów. Obiekty te są tworzone
        dopiero podczas działania programu, więc może być ciężej zrozumieć, który obiekt zostanie
        faktycznie wykorzystany.


    strongly coupled - obiekty są ze sobą połączone przy wykorzystaniu dziedziczenia
    loosely coupled - obiekty są połączone wykorzystując kompozycję

     */

    public static void main(String[] args) {

        /*
        Elastyczność polega na tym że użytkownik decyduje jaka implementacja ma być przekazana do kosntruktora
         */
        if(true) {
            new AmountCalculationServiceImpl(new Impl1(), new Impl2());
        } else {
            new AmountCalculationServiceImpl(new Impl3(), new Impl4());
        }
        // Wada kompozycji -> mała czytelność
    }

    public interface AmountCalculationService {

    }

    static public class AmountCalculationServiceImpl implements AmountCalculationService {

        private final ConstantAmountCalculationService constantAmountCalculationService;

        private final DecreasingAmountCalculationService decreasingAmountCalculationService;

        public AmountCalculationServiceImpl(ConstantAmountCalculationService constantAmountCalculationService,
                                            DecreasingAmountCalculationService decreasingAmountCalculationService) {
            this.constantAmountCalculationService = constantAmountCalculationService;
            this.decreasingAmountCalculationService = decreasingAmountCalculationService;
        }
    }

    public interface ConstantAmountCalculationService {

    }

    public interface DecreasingAmountCalculationService {

    }

    static public class Impl1 implements ConstantAmountCalculationService {

    }

    static public class Impl2 implements DecreasingAmountCalculationService {

    }

    static public class Impl3 implements ConstantAmountCalculationService {

    }

    static public class Impl4 implements DecreasingAmountCalculationService {

    }

}
