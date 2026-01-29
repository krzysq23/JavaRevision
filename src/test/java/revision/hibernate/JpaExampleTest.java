package revision.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import revision.hibernate.code.model.Person;

import java.util.List;

class JpaExampleTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void createAndUpdatePersonTest() {

        String personName = "Karol";
        int personAge = 69;

        createPerson(entityManager, personName, personAge);

        List<Person> allPeople = getAllPeople(entityManager);
        int newPersonAge = 30;

        Person karol = allPeople.get(0);

        updatePersonAge(entityManager, karol, newPersonAge);

        Assertions.assertEquals(1, allPeople.size());
        Assertions.assertEquals(30, allPeople.get(0).getAge());

    }

    private List<Person> getAllPeople(EntityManager entityManager) {

        entityManager.getTransaction().begin();
        String selectQuery = "SELECT p FROM Person p";
        Query query = entityManager.createQuery(selectQuery);
        List<Person> people = query.getResultList();
        entityManager.getTransaction().commit();
        return people;
    }

    private void updatePersonAge(EntityManager entityManager, Person personToUpdate, int newPersonAge) {

        entityManager.getTransaction().begin();
        personToUpdate.setAge(newPersonAge);
        entityManager.getTransaction().commit();
    }

    private void createPerson(EntityManager entityManager, String newPersonName, int newPersonAge) {

        entityManager.getTransaction().begin();
        Person person = new Person();
        person.setName(newPersonName);
        person.setAge(newPersonAge);
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

}