package revision.logging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import revision.logging.logger.Logger1;
import revision.logging.logger.Logger2;

public class LoggingExample {

    /*
        java.util.logging (stare logowanie aplikacji)
        log4j -> log4j2
        logback:
            - logback-core - moduł podstawowego logowania
            - logback-classic - zawiera wsparcie slf4j
            - logback-access - moduł integracje z servlet container (np. tomcat, jetty)
        slf4j (simple login facade)-> nakładka/ujednolicenie komunikacji miedzy log4j2 a logback
     */

    private static final Logger log = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        System.out.println(log.getClass());

        log.trace("Hello! parameter: {}, parameter2: {}", 123, "TRACE");
        log.debug("Hello! parameter: {}, parameter2: {} ", 456, "DEBUG");
        log.info("Hello! parameter: {}, parameter2: {} ", 789, "INFO");
        log.warn("Hello! parameter: {}, parameter2: {} ", 321, "WARN");
        log.error("Hello! parameter: {}, parameter2: {} ", 654, "ERROR");

        Logger1.log();
        Logger2.log();

        try {
            method1();
        } catch (Exception e) {
            log.error("Exception", e);
        }

//        LoggerLoop.log();

    }

    private static void method1() throws Exception {
        throw new Exception("Some exception was thrown!");
    }
}
