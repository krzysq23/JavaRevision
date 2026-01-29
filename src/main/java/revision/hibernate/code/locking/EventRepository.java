package revision.hibernate.code.locking;

import jakarta.persistence.LockModeType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class EventRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void deleteAll() {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<TicketEntity> deleteTicket = criteriaBuilder.createCriteriaDelete(
                TicketEntity.class);
        deleteTicket.from(TicketEntity.class);
        session.createMutationQuery(deleteTicket).executeUpdate();
        CriteriaDelete<EventEntity> deleteEvent = criteriaBuilder.createCriteriaDelete(EventEntity
                .class);
        deleteEvent.from(EventEntity.class);
        session.createMutationQuery(deleteEvent).executeUpdate();
    }

    @Transactional
    public EventEntity insertEmptyEvent(final EventEntity event) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(event);
        return event;
    }

    @Transactional
    public void saveNewTicket(String firstName, String lastName, Long eventId) {

        Session session = sessionFactory.getCurrentSession();
        EventEntity event = session.find(EventEntity.class, eventId);

//        Pesimistic Locking
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("jakarta.persistence.lock.timeout", 1000L);
//        session.lock(event, LockModeType.PESSIMISTIC_WRITE, properties);

        // Optimistic locking
        session.lock(event, LockModeType.OPTIMISTIC_FORCE_INCREMENT);

        if (event.getCapacity() <= event.getTickets().size()) {
            throw new RuntimeException("Capacity exceeded");
        }

        TicketEntity ticket = TicketEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        event.addTicket(ticket);
        session.persist(ticket);

    }

    @Transactional
    public void changeDateTime(OffsetDateTime newDateTime, Long eventId) {

        Session session = sessionFactory.getCurrentSession();
        EventEntity event = session.find(EventEntity.class, eventId);
        event.setDateTime(newDateTime);
    }


}
