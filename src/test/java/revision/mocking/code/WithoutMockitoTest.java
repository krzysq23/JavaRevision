package revision.mocking.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WithoutMockitoTest {

    /*
        Unit testy ze stubem
     */

    @Test
    void testSampleMethod() {

        // given
        ExampleBeanService exampleBeanService = new ExampleBeanServiceImpl();
        exampleBeanService.setInjectedBeanService(new StubInjectedBeanServiceImpl());

        // when
        String result = exampleBeanService.sampleMethod("some value");

        // then
        Assertions.assertEquals("some value", result);

    }

    static class StubInjectedBeanServiceImpl implements InjectedBeanService {
        @Override
        public String anotherSampleMethod(String value) {
            return "some value";
        }
    }

    /*
        Z wykorzystaniem wewnętrznej klasy anonimowej
     */
    @Test
    void testSampleMethodWithAnonymousClass() {

        ExampleBeanService exampleBeanService = new ExampleBeanServiceImpl();
        exampleBeanService.setInjectedBeanService(new InjectedBeanService() {
            @Override
            public String anotherSampleMethod(String value) {
                return "some value";
            }
        });

        String result = exampleBeanService.sampleMethod("");
        Assertions.assertEquals("some value", result);
    }

    /*
        Z wykorzystaniem wewnętrznej klasy anonimowej
     */
    @Test
    void testSampleMethodWithLambda() {
        ExampleBeanService exampleBeanService = new ExampleBeanServiceImpl();
        exampleBeanService.setInjectedBeanService((value) -> "some value");

        String result = exampleBeanService.sampleMethod("");
        Assertions.assertEquals("some value", result);
    }


}