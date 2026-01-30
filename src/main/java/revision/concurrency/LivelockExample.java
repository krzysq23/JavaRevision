package revision.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {

    /*
        W przeciwieństwie jednak do deadlocka gdzie wątki po prostu w nieskończoność na siebie czekają i nie
        robią nic, to tutaj dzieje się bardzo dużo. Wręcz za dużo, a mianowicie wątki reagują wzajemnie na
        siebie w taki sposób, że się blokują tak samo, gdyby nie robiły nic. W konsekwencji wątki nie są w stanie
        wykonać do końca swoich bloków kodu.
     */

    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        LivelockExample example = new LivelockExample();
        new Thread(example::operation1, "Thread1").start();
        new Thread(example::operation2, "Thread2").start();
    }

    public void operation1() {
        while (true) {
            if (!tryLock(lock1, 1000)) {
                print("Cannot acquire lock1");
            }
            print("Lock1 acquired, trying to acquire lock2");
            sleep(1000);
            if (lock2.tryLock()) {
                print("Lock2 acquired");
            } else {
                print("Releasing lock1 - cannot acquire lock2");
                lock1.unlock();
                continue;
            }
            print("Operation1");
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        while (true) {
            if (!tryLock(lock2, 1000)) {
                print("Cannot acquire lock2");
            }
            print("Lock2 acquired, trying to acquire lock1");
            sleep(1000);
            if (lock1.tryLock()) {
                print("Lock1 acquired");
            } else {
                print("Releasing lock2 - cannot acquire lock1");
                lock2.unlock();
                continue;
            }
            print("Operation2");
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }

    public void print(String message) {
        System.out.printf("Thread: %s, msg: %s%n", Thread.currentThread().getName(), message);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean tryLock(Lock lock, long millis) {
        try {
            return lock.tryLock(millis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}
