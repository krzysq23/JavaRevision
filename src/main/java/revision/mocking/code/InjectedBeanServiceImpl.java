package revision.mocking.code;

public class InjectedBeanServiceImpl implements InjectedBeanService {

    @Override
    public String anotherSampleMethod(String input) {
        return "some value: " + input;
    }
}
