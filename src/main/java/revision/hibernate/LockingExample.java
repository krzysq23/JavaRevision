package revision.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import revision.hibernate.code.AppConfig;
import revision.hibernate.code.locking.EventEntity;
import revision.hibernate.code.locking.EventEntityData;
import revision.hibernate.code.locking.EventRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockingExample {

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        EventRepository eventRepository = context.getBean(EventRepository.class);

        eventRepository.deleteAll();

//        normal(eventRepository);

//        thread(eventRepository);

        optimisticLocking(eventRepository);

    }

    private static void optimisticLocking(EventRepository eventRepository) {

        eventRepository.deleteAll();
        EventEntity event1 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity1());
        EventEntity event2 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity2());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            eventRepository.changeDateTime(
                    OffsetDateTime.of(
                            LocalDate.of(2025, 6, 13), LocalTime.of(19,0,0),
                            ZoneOffset.ofHours(2)
                    ),
                    event2.getEventId()
            );
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        executor.execute(() -> {
            eventRepository.changeDateTime(
                    OffsetDateTime.of(
                            LocalDate.of(2025, 10, 2), LocalTime.of(11,0,0),
                            ZoneOffset.ofHours(2)
                    ),
                    event2.getEventId()
            );
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        executor.shutdown();
    }

    private static void normal(EventRepository eventRepository) {

        EventEntity event1 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity1());
        EventEntity event2 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity2());

        eventRepository.saveNewTicket("Karol", "Nowak", event1.getEventId());
        eventRepository.saveNewTicket("Anna", "Nowak", event2.getEventId());

    }

    private static void thread(EventRepository eventRepository) {

        EventEntity event2 = eventRepository.insertEmptyEvent(EventEntityData.someEventEntity2());

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            eventRepository.saveNewTicket("Stefan", "Adamski", event2.getEventId());
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        executor.execute(() -> {
            eventRepository.saveNewTicket("Agnieszka", "Programistka", event2.getEventId());
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        executor.execute(() -> {
            eventRepository.saveNewTicket("Tadek", "Nowak", event2.getEventId());
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        executor.execute(() -> {
            eventRepository.saveNewTicket("Radek", "Kowalski", event2.getEventId());
            try {
                Thread.sleep(1_000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        executor.shutdown();
    }

}
