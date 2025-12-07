package revision.functionalProgramming.functionalInterface;

@FunctionalInterface
public interface SomeFunctionalInterface {

    // domyślnie public abstract void consume(String arg);
    void consume(String arg);

    private void consume2(String value) {
        System.out.println("calling consume2: " + value);
    }

    default String someFunction() {
        System.out.println("calling default");
        return "some default";
    }

    default String someFunction2() {
        System.out.println("calling default2");
        return "some default2";
    }

    static  String someStatic() {
        System.out.println("calling someStatic");
        return "Some static";
    }
}
