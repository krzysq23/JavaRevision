package revision.springDataJPA.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import revision.springDataJPA.infrastructure.database.EmployeeRepositoryJPA;
import revision.springDataJPA.infrastructure.database.entity.EmployeeEntity;
import revision.springDataJPA.infrastructure.database.repository.EmployeeRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepositoryJPA;

    @Transactional
    public EmployeeEntity create(EmployeeEntity employee) {
        return employeeRepositoryJPA.save(employee);
    }

    @Transactional
    public EmployeeEntity find(final Integer employeeId) {
        return employeeRepositoryJPA.findById(employeeId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Employee with id: [%s] doesn't exist", employeeId)));
    }

    @Transactional
    public EmployeeEntity find(final String name, final String surname) {
        return employeeRepositoryJPA.findByNameAndSurname(name, surname)
                .orElseThrow(() -> new RuntimeException(
                        String.format("Employee with name: [%s], surname: [%s] doesn't exist", name, surname)));
    }

    @Transactional
    public List<EmployeeEntity> findAll() {
        return employeeRepositoryJPA.findAll();
    }

    @Transactional
    public void delete(final String name, final String surname) {
        employeeRepositoryJPA.deleteByNameAndSurname(name, surname);
    }

    @Transactional
    public void deleteAll() {
        employeeRepositoryJPA.deleteAll();
    }

}
