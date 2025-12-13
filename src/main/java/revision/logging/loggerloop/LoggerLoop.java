package revision.logging.loggerloop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class LoggerLoop {

    private static final Logger log = LoggerFactory.getLogger(LoggerLoop.class);

    private static final Map<Integer, Consumer<Integer>> ACTIONS = Map.of(
            0, key -> log.debug("Some debug message, key: {}", key),
            1, key -> log.info("Some info message, key: {}", key),
            2, key -> log.warn("Some warn message, key: {}", key),
            3, key -> log.error("Some error message, key: {}", key)
    );

    public static void log() {

        IntStream.range(0, 1_000_000)
                .map(i -> i % 4)
                .forEach(key -> Optional.ofNullable(ACTIONS.get(key))
                        .orElseThrow(() -> new RuntimeException("CaseNot handled"))
                        .accept(key)
                );
    }


}
