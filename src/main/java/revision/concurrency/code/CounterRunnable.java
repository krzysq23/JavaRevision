package revision.concurrency.code;

import java.util.Random;

public class CounterRunnable implements Runnable {

    private final ThreadLocal<MyObject> threadLocal = new ThreadLocal<>();

    private final MyObject myObject = new MyObject(0);

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        justWait(threadName);
        if (threadLocal.get() != null) {
            MyObject previous = threadLocal.get();
            threadLocal.set(previous.withCounter(threadName, previous.getCounter() + 1));
        } else {
            threadLocal.set(new MyObject(0));
        }
        myObject.withCounter(threadName, myObject.getCounter() + 1);
        System.out.printf("name: [%s] - threadLocal.get(): [%s]%n", threadName, threadLocal.get());
        System.out.printf("name: [%s] - counter: [%s]%n", threadName, myObject);
    }

    private void justWait(String who) {
        try {
            int toWait = new Random().nextInt(1000);
            System.out.printf("Thread: [%s] waiting for: [%s]%n", who, toWait);
            Thread.sleep(toWait);
        } catch (InterruptedException ex) {
            System.err.println("ERROR: " + ex.getMessage());
        }
    }

}
