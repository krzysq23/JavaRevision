package revision.logging.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logger2 {

    private static final Logger log = LoggerFactory.getLogger(Logger2.class);

    public static void log() {

        log.trace("Logging trace: {}", Logger2.class.getName());
        log.debug("Logging debug: {}", Logger2.class.getName());
        log.info("Logging info: {}", Logger2.class.getName());
        log.warn("Logging warn: {}", Logger2.class.getName());
        log.error("Logging error: {}", Logger2.class.getName());

    }
}
