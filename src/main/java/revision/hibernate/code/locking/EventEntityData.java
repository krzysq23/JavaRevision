package revision.hibernate.code.locking;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class EventEntityData {

    public static EventEntity someEventEntity1() {
        return EventEntity.builder()
                .eventName("Standup")
                .capacity(5)
                .dateTime(OffsetDateTime.of(LocalDate.of(2024, 10, 2),
                        LocalTime.of(18,0,0), ZoneOffset
                        .ofHours(2)))
                .build();
    }

    public static EventEntity someEventEntity2() {
        return EventEntity.builder()
                .eventName("Koncert")
                .capacity(1)
                .dateTime(
                        OffsetDateTime.of(LocalDate.of(2022, 9, 14),
                        LocalTime.of(21,0,0), ZoneOffset.ofHours(2))
                )
                .build();
    }
}
