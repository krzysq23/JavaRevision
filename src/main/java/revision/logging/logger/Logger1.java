package revision.logging.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logger1 {

    private static final Logger log = LoggerFactory.getLogger(Logger1.class);

    public static void log() {

        log.trace("Logging trace: {}", Logger1.class.getName());
        log.debug("Logging debug: {}", Logger1.class.getName());
        log.info("Logging info: {}", Logger1.class.getName());
        log.warn("Logging warn: {}", Logger1.class.getName());
        log.error("Logging error: {}", Logger1.class.getName());

    }
}
