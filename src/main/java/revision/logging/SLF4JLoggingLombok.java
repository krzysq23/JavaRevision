package revision.logging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SLF4JLoggingLombok {

    public static void main(String[] args) {

        log.trace("Hello java!, parametr: {}", "trace");
        log.debug("Hello java!, parametr: {}", "debug");
        log.info("Hello java!, parametr: {}", "info");
        log.warn("Hello java!, parametr: {}", "warn");
        log.error("Hello java!, parametr: {}", "error");
    }
}
