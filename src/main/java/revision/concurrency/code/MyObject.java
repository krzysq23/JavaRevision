package revision.concurrency.code;

public class MyObject {

    private Integer counter;

    public MyObject(Integer counter) {
        this.counter = counter;
    }

    public Integer getCounter() {
        return counter;
    }

    public MyObject withCounter(String who, Integer counter) {
        System.out.printf("Changing value by: [%s] to value: [%s]%n", who, counter);
        this.counter = counter;
        return this;
    }

    @Override
    public String toString() {
        return "MyObject{counter=%s}".formatted(counter);
    }

}
