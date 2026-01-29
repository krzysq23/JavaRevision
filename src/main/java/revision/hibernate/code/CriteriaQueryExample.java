package revision.hibernate.code;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import revision.hibernate.code.oneToOne.Customer;

import java.util.List;

@Repository
public class CriteriaQueryExample {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void criteriaExample() {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery.select(root);
        TypedQuery<Customer> query = session.createQuery(criteriaQuery);

        List<Customer> result = query.getResultList();

        result.forEach(customer -> System.out.println("###Customer: " + customer));
    }

    @Transactional
    public void criteriaParameterExample() {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> root = criteriaQuery.from(Customer.class);

        ParameterExpression<String> parameter1 = criteriaBuilder.parameter(String.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"), parameter1));

        TypedQuery<Customer> query = session.createQuery(criteriaQuery);

        query.setParameter(parameter1, "adrian@zajavka.pl");

        List<Customer> result = query.getResultList();

        result.forEach(customer -> System.out.println("###Customer: " + customer));

    }

    @Transactional
    public void criteriaWhereExample() {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);

        Root<Customer> root = criteriaQuery.from(Customer.class);

        criteriaQuery
                .select(root)
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.like(root.get("email"), "%r%"),
                                criteriaBuilder.isNull(root.get("phone"))
                        )
                )
                .orderBy(criteriaBuilder.desc(root.get("phone")));

        Query<Customer> query = session.createQuery(criteriaQuery);
        List<Customer> result = query.getResultList();

        result.forEach(customer -> System.out.println("###Customer: " + customer));

    }
}
