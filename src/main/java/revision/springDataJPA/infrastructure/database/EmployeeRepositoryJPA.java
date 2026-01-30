package revision.springDataJPA.infrastructure.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import revision.springDataJPA.infrastructure.database.entity.EmployeeEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryJPA {

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeEntity insertEmployee(final EmployeeEntity employee) {
        entityManager.persist(employee);
        return employee;
    }

    public List<EmployeeEntity> listEmployees() {
        String query = "SELECT emp FROM EmployeeEntity emp";
        return entityManager.createQuery(query, EmployeeEntity.class).getResultList();
    }

    public Optional<EmployeeEntity> getEmployee(Integer employeeId) {
        return Optional.ofNullable(entityManager.find(EmployeeEntity.class, employeeId));
    }

    public void updateEmployee(Integer employeeId, BigDecimal newSalary) {
        EmployeeEntity employee = entityManager.find(EmployeeEntity.class, employeeId);
        employee.setSalary(newSalary);
    }

    public void deleteEmployee(Integer employeeId) {
        entityManager.remove(entityManager.find(EmployeeEntity.class, employeeId));
    }

    public void deleteAll() {
        entityManager.createQuery("delete from EmployeeEntity emp").executeUpdate();
    }

}
