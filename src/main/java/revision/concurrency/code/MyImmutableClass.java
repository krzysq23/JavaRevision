package revision.concurrency.code;

import lombok.Builder;
import lombok.Value;
import lombok.With;

@Value
@With
@Builder
public final class MyImmutableClass {

    String fieldOne;
    long fieldTwo;
}