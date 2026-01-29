package revision.hibernate.code;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import revision.hibernate.code.manyToMany.Employee;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void insertEmployee(final Employee employee) {

        Session session = sessionFactory.getCurrentSession();
        if (Objects.isNull(session)) {
            throw new RuntimeException("Session is null");
        }
        session.persist(employee);
    }

    @Transactional
    public List<Employee> listEmployees() {

        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT emp FROM Employee emp";
        List<Employee> employees = session.createQuery(query, Employee.class).list();
        return employees;
    }

    @Transactional
    Optional<Employee> getEmployee(Integer employeeId) {

        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.find(Employee.class, employeeId));
    }
}
