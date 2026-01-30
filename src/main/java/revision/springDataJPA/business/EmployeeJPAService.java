package revision.springDataJPA.business;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import revision.springDataJPA.infrastructure.database.jparespositories.EmployeeDatabaseRepository;
import revision.springDataJPA.infrastructure.database.entity.EmployeeEntity;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeJPAService {

    private final EmployeeDatabaseRepository employeeDatabaseRepository;

    @Transactional
    public void runSuccessful() {

        employeeDatabaseRepository.deleteAll();

        employeeDatabaseRepository.flush();

        EmployeeEntity employeeEntity1 = employeeDatabaseRepository.save(EmployeeData.someEmployee1());
        EmployeeEntity employeeEntity2 = employeeDatabaseRepository.save(EmployeeData.someEmployee2());
        EmployeeEntity employeeEntity3 = employeeDatabaseRepository.save(EmployeeData.someEmployee3());

        System.out.println("###Employee 1: " + employeeDatabaseRepository
                .findById(employeeEntity1.getEmployeeId()));
        System.out.println("###Employee 2: " + employeeDatabaseRepository
                .findById(employeeEntity2.getEmployeeId()));

        EmployeeEntity employeeBeforeUpdate =
                employeeDatabaseRepository.findById(employeeEntity3.getEmployeeId())
                        .orElseThrow();
        employeeBeforeUpdate.setSalary(new BigDecimal("10348.91"));

        employeeDatabaseRepository.saveAndFlush(employeeBeforeUpdate);
        System.out.println("###Employee updated: "
                + employeeDatabaseRepository.findById(employeeEntity3.getEmployeeId()));

        employeeDatabaseRepository.findAll()
                .forEach(employee -> System.out.println("###Employee: " + employee));

        employeeDatabaseRepository.deleteById(employeeEntity2.getEmployeeId());

        employeeDatabaseRepository.findAll()
                .forEach(employee -> System.out.println("###Employee: " + employee));
    }

    @Transactional
    public void testTransactional() {

        employeeDatabaseRepository.deleteAll();
        employeeDatabaseRepository.saveAll(
                List.of(
                        EmployeeData.someEmployee1(),
                        EmployeeData.someEmployee2(),
                        EmployeeData.someEmployee3(),
                        EmployeeData.someEmployee3()));
    }

}
