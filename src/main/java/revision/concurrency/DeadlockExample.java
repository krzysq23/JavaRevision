package revision.concurrency;

public class DeadlockExample {

    /*
        Co to deadlock? Proste, określenie sytuacji, gdy 2 (albo więcej) wątki się nawzajem "blokują".
        Jak zapobiegać:
        1. obydwa wątki chciały zablokować dla siebie te same zasoby, jednak zrobiły to w odwrotnej kolejności.
           Gdyby obydwa wątki zaczynały od tego samego zasobu, do sytuacji nigdy by nie doszło.
           Czyli pierwza metoda to odpowiednia (ta sama) kolejność blokowania zasobów przez różne wątki
        2. Timeout
        3. Deadlock detection metoda, która zakłada, że każde zablokowanie zasobu zostaje
           odnotowane w globalnym "rejestrze" (np. w mapie odnotowującej kto, (czyli który wątek) co
           dokładnie zablokował). Jeśli thread nie może założyć locka, to zagląda do rejestru w poszukiwaniu,
           kto blokuje potrzebny mu zasób. Co z tym robi dalej to już kwestia uznaniowa: może np. postąpić
           podobnie jak w przypadku timeoutu tzn. zwolnić zasoby, poczekać i spróbować jeszcze raz.
     */

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {

        Member1 member1 = new Member1();
        Member2 member2 = new Member2();
        member1.start();
        member2.start();
    }

    private static class Member1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Member 1: Inside lock 1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Member 1: Before lock 2...");
                synchronized (lock2) {
                    System.out.println("Member 1: Inside lock 1 & 2...");
                }
            }
        }
    }

    private static class Member2 extends Thread {
        public void run() {
            synchronized (lock2) {
                System.out.println("Member 2: Inside lock 2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Member 2: Before for lock 1...");
                synchronized (lock1) {
                    System.out.println("Member 2: Inside lock 1 & 2...");
                }
            }
        }
    }
}
