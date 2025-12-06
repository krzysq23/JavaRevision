package revision.collections.comparable;

public class Dog implements Comparable<Dog> {

    private final Integer id;
    private final String name;

    public Dog(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog: " + id + "_" + name;
    }

    @Override
    public int compareTo(Dog o) {
//        return this.name.compareTo(o.name);
//        return o.name.compareTo(this.name);
        int result = this.name.compareTo(o.name);
        if (result != 0) {
             return result;
        }
        return o.id - this.id;
    }
}
