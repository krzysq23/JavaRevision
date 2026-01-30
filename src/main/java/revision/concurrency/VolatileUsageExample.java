package revision.concurrency;

public class VolatileUsageExample {

    /*
        W przeciwieństwie do synchronized - używanego do metod czy bloków kodu, volatile możemy używać
        do deklarowania zmiennych. Tylko i wyłącznie do deklarowania zmiennych.

        Poprzez dodanie tego słowa do deklaracji zmiennej instruujemy JVM,
        żeby ładował i zapisywał jej wartość nie do / z pamięci podręcznej procesora (cache), ale z RAMu.

        Procesory mają swój cache. Cache jest bardzo szybki, co oznacza, że
        wątki będą tam przechowywać wartości zmiennych - w celach optymalizacyjnych. Oznaczając zmienną
        jako volatile upewniamy się, że odczyt i zapis będzie się odbywać każdorazowo poprzez RAM.
        Gwarantujemy sobie w ten sposób, że każdy wątek zobaczy wersję zapisaną w RAMie i od razu będzie
        zapisywał do RAMu (czyli do stacku bądź heapa).

        Czy słowo volatile zabezpiecza nas przed race condition tak jak synchronized? Absolutnie nie!

        Słówko volatile nic nie blokuje, upewnia się jedynie, że zapis wartości zmiennej i jej
        odczyt będzie się odbywał natychmiast, a nie w momencie, gdy JVM uzna to za optymalne.

        Gwarancja "HAPPENS BEFORE":
        - operacje na volatile wykonają się przed innymi operacjami w bloku
        Zapis do zmiennej, który w kodzie znajduje się przed zapisem do zmiennej volatile, zawsze wydarzy się
        wcześniej (happens before!), przed zapisem do tej zmiennej volatile (JVM nie może przesunąć tych
        instrukcji na później, jak zrobił to w naszym przykładzie ze zmienną secondVariable, gdyby zmienna ta
        była oznaczona volatile - JVM nic nie mógłby zrobić).

     */


    public static class VolatileExample {

        public volatile boolean someVariableUpdatedByManyThreads = true;

        public void executeExample(){
            while(someVariableUpdatedByManyThreads) {
                System.out.println("Nadal ustawione na true");
            }
            System.out.println("Inny wątek zmienił na false");
        }
    }

}
