package revision.concurrency.code;

public class MyVeryOwnThreadImplementation extends Thread {

    @Override
    public void run(){
        System.out.println("Siemanko MyVeryOwnThreadImplementation");
    }
}