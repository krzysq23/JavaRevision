package revision.functionalProgramming.labbda;

public class CheckableImpl implements Checkable {

    @Override
    public boolean myTester(String value) {
        System.out.println("CheckableImpl calling:");
        return value.contains("test");
    }
}
