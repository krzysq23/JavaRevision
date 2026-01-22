package revision.springBeans.code;

public class InjectedBean {

    public InjectedBean() {
        System.out.println("Calling InjectedBean()");
    }

    public void someMethod() {
        System.out.println("InjectedBean someMethod()");
    }

    public void anotherExampleMethod() {
        System.out.println("===== InjectedBean anotherExampleMethod()");
    }

    public void initMethod() {
        System.out.println("===== InjectedBean initMethod() =====");
    }

    public void destroyMethod() {
        System.out.println("===== InjectedBean destroyMethod() =====");
    }

}
