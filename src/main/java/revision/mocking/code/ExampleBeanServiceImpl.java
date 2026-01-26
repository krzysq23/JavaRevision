package revision.mocking.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleBeanServiceImpl implements ExampleBeanService {

    private InjectedBeanService injectedBeanService;

    private List<String> sampleList = new ArrayList<>();

    @Override
    public void setInjectedBeanService(InjectedBeanService injectedBeanService) {
        this.injectedBeanService = injectedBeanService;
    }

    @Override
    public String sampleMethod(String input) {
        return injectedBeanService.anotherSampleMethod(input);
    }

    @Override
    public void anotherSampleMethod(String... values) {
        for (String valueToAdd : values) {
            sampleList.add(valueToAdd);
        }
    }


}