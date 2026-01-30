package revision.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClassExample {

    /*
        Atomowość (niepodzielność) w tym przypadku oznaczałaby, że operacja miałaby zostać
        przeprowadzona jako pojedyncza porcja pracy, bez możliwości ingerencji innego wątku.

        Nazwa Klasy                 Opis
        AtomicBoolean               Wartość boolean, która może być aktualizowana atomowo
        AtomicInteger               Wartość int, która może być aktualizowana atomowo
        AtomicIntegerArray          Tablica int, w której elementy mogą być aktualizowane atomowo
        AtomicLong                  Wartość long, która może być aktualizowana atomowo
        AtomicLongArray             Tablica long, w której elementy mogą być aktualizowane atomowo
        AtomicReference             Generyczna referencja do obiektu, która może być aktualizowana atomowo
        AtomicReferenceArray        Tablica generycznych referencji do obiektów, w której elementy mogą być
                                    aktualizowane atomowo
     */

    public static void main(String[] args) {

        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(50);
            AtomicClassExample example = new AtomicClassExample();
            for (int i = 0; i < 10; i++) {
                service.submit(example::incrementAndPrint);
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    private AtomicInteger count = new AtomicInteger(0);

    private void incrementAndPrint() {
        System.out.print(count.incrementAndGet() + " ");
    }
}
