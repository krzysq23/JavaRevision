package revision.unitTest.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Calculator {

    public Integer add(int left, int right) {
        log.debug("DEBUG message");
        log.info("INFO message");
        log.warn("WARN message");
        log.error("ERROR message");
        return left + right;
    }

    public static int add(String left, String right) {
        return Integer.parseInt(left) + Integer.parseInt(right);
    }

    public static Integer subtract(int left, int right) {
        return left - right;
    }

    public static Integer multiply(int left, int right) {
        return left * right;
    }

    public static Integer divide(int left, int right) {
        return left / right;
    }

}
