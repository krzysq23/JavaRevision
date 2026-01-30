package revision.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class FairnessExample {

    /*
        Starvation: to sytuacja, w której wątek nie jest przez nic blokowany,
        ale nie może się dobić do wykonania jakiegoś bloku kodu, bo ciągle jest "wyprzedzany" przez inne wątki.

        Odpowiedzią na problem starvation jest koncepcja fairness. Zakłada ona bycie fair, czyli poświęcanie
        czasu i dopuszczanie wszystkich wątków do wykonania.

        Po pierwsze pamiętaj, że przed blokiem synchronized, nie ma żadnej kolejki FIFO, w zasadzie nie ma
        żadnej kolejki! Wątki rzucają / wciskają się na oślep!
        Wystarczy więc… zaimplementować swój mechanizm, który zagwarantuje trzymanie kolejki w sposób
        fair, przetrzymujący listę, na którą wpisujemy oczekujące wątki. Na szczęście taki fair lock mamy już
        zaimplementowany w Javie (ReentrantLock)

        takie zabezpieczenie ma swój koszt w wydajności. Trzeba mieć to na
        uwadze podczas pisania, bo zawsze jest to jakiś tradeoff. W sytuacji, gdy kod już działa powoli,
        powinniśmy zastanowić się dwa razy czy gra jest warta świeczki
     */

    public static void main(String[] args) {

    }

    public static class Fairness {

        //Używamy konstruktora przyjmującego jako argument (boolean fair) - w ten sposób gwarantujemy
        //pierwszeństwo dla najdłużej czekającego wątku.
        private final ReentrantLock reentrantLock = new ReentrantLock(true);

        public void fairNotSynchronizedMethod() {
            reentrantLock.lock();
            try {
                // critical section kodu
            } finally {
                reentrantLock.unlock(); // nie zapomnij zwolnić na koniec
            }
        }
    }

}
