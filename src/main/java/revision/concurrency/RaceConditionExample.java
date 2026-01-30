package revision.concurrency;

import revision.concurrency.code.CounterRunnable;
import revision.concurrency.code.InvoiceNameGenerator;
import revision.concurrency.code.InvoiceThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceConditionExample {

    /*
        Odporność na race condition - Synchronized
        - oznaczenie metody synchronized

        Odporność na race condition - immutable
        - Obiekty immutable zawsze są thread-safe.
        - przykład MyImmutableClass
        1. ŻADNYCH SETTERÓW.
        2. Wszystkie pola ustawiane w konstruktorze.
        3. Wszystkie pola oczywiście prywatne.
        4. Klasa oznaczona jako final (nie możemy jej rozszerzyć).
        5. Wszystkie pola finalne (ustawione raz i na zawsze).

        Odporność na race condition - Lock
        - Jest to narzędzie/technika w Javie, dzięki której możemy
          "blokować" kawałki kodu do użycia przez jeden wątek.
        - synchronized zapewnia reentrance od razu,
          tak przy implementacji własnego locka musimy sami to zapewnić.

        Odporność na race condition - ThreadLocal
        -  ThreadLocal, na którym pracuje wiele wątków,
           wartości, które dane wątki przypisują do tej zmiennej,
           są trzymane osobno: per wątek. Nigdy nie dojdzie
           tutaj do żadnego race condition, bo pomimo jednej zmiennej w kodzie,
           wartości są zawsze odseparowane.


     */
    public static void main(String[] args) {

//        synchronizedMethod();

        threadLocalMethod();
    }

    private static void threadLocalMethod() {

        CounterRunnable commonObject = new CounterRunnable();
        Thread t1 = new Thread(commonObject);
        Thread t2 = new Thread(commonObject);
        Thread t3 = new Thread(commonObject);
        Thread t4 = new Thread(commonObject);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    /*
    Odporność na race condition - Synchronized
     */
    public static void synchronizedMethod() {
        ExecutorService executor = Executors.newFixedThreadPool(30);

        for (int i = 0; i < 30; i++) {
            executor.submit(InvoiceNameGeneratorSynchronized::generateNewInvoiceName);
        }

        executor.shutdown();

        InvoiceNameGenerator invoiceNameGenerator = new InvoiceNameGenerator();
        Thread firstThread = new InvoiceThread(invoiceNameGenerator);
        Thread secondThread = new InvoiceThread(invoiceNameGenerator);
        firstThread.start();
        secondThread.start();
    }

    public static class InvoiceNameGeneratorSynchronized {

        private static final String INVOICE_NAME_PATTERN = "FV_";

        private static int lastInvoiceNumber = 0;

        public static synchronized String generateNewInvoiceName() {
            lastInvoiceNumber = lastInvoiceNumber + 1;
            String invoice = INVOICE_NAME_PATTERN + lastInvoiceNumber;
            System.out.println("Invoice: "+ invoice);
            return invoice;
        }
    }

    public static class InvoiceNameGeneratorSynchronized2 {

        private static final String INVOICE_NAME_PATTERN = "FV_";

        private static int lastInvoiceNumber = 0;

        // synchronized(this) {} -> monitor object
        public String generateNewInvoiceName() {
            System.out.println("Ten print będzie wykonywany współbieżnie");
            synchronized(this) {
                System.out.println("... a ten nie");
                lastInvoiceNumber = lastInvoiceNumber + 1;
                return INVOICE_NAME_PATTERN + lastInvoiceNumber;
            }
        }
    }


}
