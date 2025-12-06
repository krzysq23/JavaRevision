package revision.generics.genericInterfce;

public class PackableBag implements Packable<Car> {

    @Override
    public void pack() {

    }

    @Override
    public Car empty() {
        return null;
    }
}
