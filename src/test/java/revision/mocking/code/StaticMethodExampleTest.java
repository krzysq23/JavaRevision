package revision.mocking.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StaticMethodExampleTest {

    private StaticMethodExample staticMethodExample = new StaticMethodExample();

    @Test
    void testGetNanoNow() {

        // given
        LocalTime now = LocalTime.now();
        int nanoNow = now.getNano();
        LocalTime nowEarlier = now.minusNanos(100);
        int nanoEarlier = nowEarlier.getNano();

        int result;
        try(MockedStatic<LocalTime> timeMock = Mockito.mockStatic(LocalTime.class)) {
            timeMock.when(LocalTime::now).thenReturn(nowEarlier);
            // when
            result = staticMethodExample.getNano();
        }

        // then
        Assertions.assertNotEquals(result, nanoNow);
        Assertions.assertEquals(result, nanoEarlier);
    }

}