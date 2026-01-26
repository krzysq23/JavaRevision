package revision.mocking.code;

import java.time.LocalTime;

public class StaticMethodExample {

    public int getNano() {
        LocalTime now = LocalTime.now();
        return now.getNano();
    }

    public LocalTime getNow() {
        return LocalTime.now();
    }
}
