package revision.mocking.code;

public interface ExampleBeanService {

    void setInjectedBeanService(InjectedBeanService injectedBeanService);
    String sampleMethod(String input);
    void anotherSampleMethod(String... values);
}
