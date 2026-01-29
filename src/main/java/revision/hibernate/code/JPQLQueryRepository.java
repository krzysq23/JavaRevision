package revision.hibernate.code;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import revision.hibernate.code.manyToMany.Employee;
import revision.hibernate.code.oneToMany.Breed;
import revision.hibernate.code.oneToMany.Owner;
import revision.hibernate.code.oneToMany.Pet;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class JPQLQueryRepository {


    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void selectOwner() {

        Session session = sessionFactory.getCurrentSession();

        String select = "from Owner o";
        session.createQuery(select, Owner.class)
                .getResultList()
                .forEach(entity -> System.out.println("###Entity: " + entity));
    }

    @Transactional
    public void selectId() {

        Session session = sessionFactory.getCurrentSession();

        Owner owner = session.find(Owner.class, 1); // najprościej

        // albo JPQL:
        Owner owner2 = session.createQuery(
                        "select o from Owner o where o.id = :id", Owner.class)
                .setParameter("id", 1)
                .getSingleResult();
    }

    @Transactional
    public void where() {

        Session session = sessionFactory.getCurrentSession();

        List<Owner> owners = session.createQuery(
                        "from Owner o where o.surname = :surname", Owner.class)
                .setParameter("surname", "Kowalski")
                .getResultList();

        System.out.println(owners);

        List<Owner> owners2 = session.createQuery(
                        "from Owner o where lower(o.email) like :pattern", Owner.class)
                .setParameter("pattern", "%@gmail.com%")
                .getResultList();

        System.out.println(owners2);
    }


    @Transactional
    public void andOrInBetween() {

        Session session = sessionFactory.getCurrentSession();

        List<Owner> owners = session.createQuery(
                        "from Owner o where o.name = :name and o.surname = :surname", Owner.class)
                .setParameter("name", "Jan")
                .setParameter("surname", "Kowalski")
                .getResultList();

        System.out.println(owners);

        List<Owner> ownersIn = session.createQuery(
                        "from Owner o where o.id in :ids", Owner.class)
                .setParameterList("ids", List.of(1,2,3))
                .getResultList();

        System.out.println(ownersIn);

        List<Employee> employees = session.createQuery(
                        "from Employee e where e.salary between :min and :max", Employee.class)
                .setParameter("min", new BigDecimal("3000"))
                .setParameter("max", new BigDecimal("7000"))
                .getResultList();

        System.out.println(employees);

        List<Owner> nulls = session.createQuery(
                        "from Owner o where o.phone is null", Owner.class)
                .getResultList();

        System.out.println(nulls);
    }

    @Transactional
    public void join() {

        Session session = sessionFactory.getCurrentSession();

        // ManyToOne / OneToMany join (bez fetch)
        List<Pet> pets = session.createQuery(
                        "select p from Pet p join p.owner o where o.surname = :surname", Pet.class)
                .setParameter("surname", "Kowalski")
                .getResultList();

        System.out.println(pets);

        // JOIN FETCH (żeby dociągnąć pets i uniknąć N+1)
        List<Owner> owners = session.createQuery(
                        "select distinct o from Owner o left join fetch o.pets", Owner.class)
                .getResultList();

        System.out.println(owners);
    }

    @Transactional
    public void sort() {

        Session session = sessionFactory.getCurrentSession();

        List<Owner> owners = session.createQuery(
                        "from Owner o order by o.surname asc, o.name asc", Owner.class)
                .setFirstResult(0)   // offset
                .setMaxResults(20)   // limit
                .getResultList();

        System.out.println(owners);
    }

    @Transactional
    public void grouping() {

        Session session = sessionFactory.getCurrentSession();

        Long ownersCount = session.createQuery(
                        "select count(o) from Owner o", Long.class)
                .getSingleResult();

        System.out.println(ownersCount);

        BigDecimal avgSalary = session.createQuery(
                        "select avg(e.salary) from Employee e", BigDecimal.class)
                .getSingleResult();

        System.out.println(avgSalary);

        // grupowanie (np. liczba zwierząt na właściciela)
        List<Object[]> rows = session.createQuery(
                        "select o.id, count(p) from Owner o left join o.pets p group by o.id",
                        Object[].class)
                .getResultList();

        System.out.println(rows);
    }

    @Transactional
    public void subquery() {

        Session session = sessionFactory.getCurrentSession();
        List<Owner> owners = session.createQuery(
                        "select o from Owner o where exists (" +
                                "   select 1 from Pet p where p.owner = o and p.breed = :breed" +
                                ")", Owner.class)
                .setParameter("breed", Breed.DOG)
                .getResultList();

        System.out.println();
    }

    @Transactional
    public void namedQuery() {

        Session session = sessionFactory.getCurrentSession();

        session.createNamedSelectionQuery("Owner.findOwnerByEmail", Owner.class)
                .setParameter("email", "stefan@zajavka.pl")
                .getResultList()
                .forEach(entity -> System.out.println("####ENTITY: " + entity));

    }

}
