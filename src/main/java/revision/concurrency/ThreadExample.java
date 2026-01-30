package revision.concurrency;

import revision.concurrency.code.MyVeryOwnThreadImplementation;
import revision.concurrency.code.RunnableThreadImplementation;

public class ThreadExample {

    public static void main(String[] args) {

        Thread myVeryFirstThread = new MyVeryOwnThreadImplementation();

        Runnable myRunnable = new RunnableThreadImplementation();
        Thread thread = new Thread(myRunnable);

        Thread threadLambda = new Thread(() -> System.out.println("Siema po raz trzeci z lambdy"));
        threadLambda.setName("thread-siemanko-lambda");

        Thread secondThread = new Thread(myRunnable, "thread-siemanko-z-runnabla");

        myVeryFirstThread.start();
        thread.start();
        threadLambda.start();
        System.out.println("Siemanko main");

        try {
            Thread.sleep(3000L);
            secondThread.start();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            // obsłużenie wyjątku
        }

    }

}
