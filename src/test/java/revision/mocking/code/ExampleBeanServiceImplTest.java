package revision.mocking.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class ExampleBeanServiceImplTest {

    /*
    @InjectMocks - dodajemy aby Mockito zadbało o wstrzyknięcie mockowanych zależności
     */
    @InjectMocks
    private ExampleBeanServiceImpl exampleBeanService;

    @Mock
    private InjectedBeanService injectedBeanService;

    @Test
    void testSampleMethod() {

        // given
        Mockito.when(injectedBeanService.anotherSampleMethod("test")).thenReturn("some value");

        // when
        String result = exampleBeanService.sampleMethod("test");

        // then
        Assertions.assertEquals("some value", result);
    }

    @Test
    void testSampleMethod2() {

        /*
        Zaślepka ArgumentMatchers pozwala na:
        • any() - cytując dokumentację: "Matches anything, including nulls and varargs.",
        • any(Class<T> type) - cytując dokumentację: "Matches any object of given type, excluding nulls.",
        • anyString() - w tym miejscu może znaleźć się dowolny String, który nie jest nullem,
        • anyInt() - w tym miejscu może znaleźć się dowolny Integer, który nie jest nullem,
        • anyList() - w tym miejscu może znaleźć się dowolna Lista, która nie jest nullem,
        • anyMap() - w tym miejscu może znaleźć się dowolna Mapa, która nie jest nullem,
         */

        // given
        Mockito.when(injectedBeanService.anotherSampleMethod(ArgumentMatchers.any())).thenReturn("some value");

        // when
        String result = exampleBeanService.sampleMethod("some value");
        String result2 = exampleBeanService.sampleMethod("value2");
        String result3 = exampleBeanService.sampleMethod("value3");
        String result4 = exampleBeanService.sampleMethod("value4");

        // then
        Assertions.assertEquals("some value", result);
        Assertions.assertEquals("some value", result2);
        Assertions.assertEquals("some value", result3);
        Assertions.assertEquals("some value", result4);
    }

    @Test
    void testSampleMethodException() {

        // given
        Mockito.when(injectedBeanService.anotherSampleMethod(ArgumentMatchers.anyString()))
                .thenThrow(new RuntimeException("some error"));

        // when,  then
        Assertions.assertThrows(RuntimeException.class,
                () -> exampleBeanService.sampleMethod("other value"));
    }

    @Test
    void testSampleMethodOtherSyntax() {

        /*
         Metoda doReturn nei spradza typów wiec nalezy stosować poprzedni zapis
         */
        Mockito
            .doReturn("some value")
            .when(injectedBeanService)
            .anotherSampleMethod(ArgumentMatchers.anyString());

        String result = exampleBeanService.sampleMethod("test");

        Assertions.assertEquals("some value", result);
    }

    @Test
    void testSampleMethodOtherVerify() {

        // given
        Mockito.when(injectedBeanService.anotherSampleMethod(ArgumentMatchers.anyString())).thenReturn("some value");

        // when
        String result = exampleBeanService.sampleMethod("test");

        // then
        Mockito.verify(injectedBeanService, Mockito.times(1))
                .anotherSampleMethod(Mockito.anyString());

//        Mockito.verify(injectedBeanService, Mockito.never())
//                .anotherSampleMethod(Mockito.anyString());

        Mockito.verifyNoMoreInteractions(injectedBeanService);
    }

    @ParameterizedTest
    @MethodSource
    void testSampleMethodParams(String val1, String val2) {

        // given
        Mockito.when(injectedBeanService.anotherSampleMethod(ArgumentMatchers.anyString())).thenReturn(val2);

        // when
        String result = exampleBeanService.sampleMethod(val1);

        // then
        Mockito.verify(injectedBeanService, Mockito.times(1))
                .anotherSampleMethod(Mockito.anyString());
    }

    static Stream<Arguments> testSampleMethodParams() {
        return Stream.of(
                Arguments.of("val1", "val2"),
                Arguments.of("val3", "val4"),
                Arguments.of("val5", "val6")
        );
    }

    /*
        Konfiguracja mockowania może się odbyć bez wykorzystania adnotacji.
     */
    @BeforeEach
    void init() {
        this.injectedBeanService = Mockito.mock(InjectedBeanService.class);
        this.exampleBeanService = new ExampleBeanServiceImpl();
        this.exampleBeanService.setInjectedBeanService(injectedBeanService);
    }

    /*
     Spy - jest specyficzny przypadek mocka, który oprócz tych możliwości, które
           daje mock, dodatkowo pozwala na wywołanie prawdziwych metod z obiektu.
     */

    @Spy
    private List<String> sampleList = new ArrayList<>();

    @Test
    void testNextSampleMethod_whenOneValueToAdd() {

        // given
        String testValue = "testValue";

        // when
        exampleBeanService.anotherSampleMethod(testValue);

        // then
        Mockito.verify(sampleList).add(Mockito.anyString());
        Mockito.verify(sampleList).add(testValue);
        Assertions.assertEquals(1, sampleList.size());
    }

    @Test
    void testNextSampleMethod_whenTwoValuesToAdd() {

        // given
        String testValue1 = "testValue1";
        String testValue2 = "testValue2";

        // when
        exampleBeanService.anotherSampleMethod(testValue1, testValue2);

        // then
        Mockito.verify(sampleList, Mockito.times(2)).add(Mockito.anyString());
        Mockito.verify(sampleList).add(testValue1);
        Mockito.verify(sampleList).add(testValue2);
        Assertions.assertEquals(2, sampleList.size());
    }


    /*
        Mokowanie statyczne.
        Aby zmkowować metody statyczne można owrapować metodę która pobiera coś z metody statycznej
     */

    @Spy
    private StaticMethodExample staticMethodExample = new StaticMethodExample();

    @Test
    void testGetNanoNow() {

        // given
        LocalTime now = LocalTime.now();
        LocalTime nowEarlier = now.minusNanos(100);
        int nanoNow = now.getNano();
        int nanoEarlier = nowEarlier.getNano();

        Mockito.when(staticMethodExample.getNow()).thenReturn(nowEarlier);

        // when
        int result = staticMethodExample.getNano();

        // then
        Assertions.assertNotEquals(result, nanoNow);
        Assertions.assertEquals(result, nanoEarlier);
    }

}
