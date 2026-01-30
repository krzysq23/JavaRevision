package revision.springDataJPA.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import revision.springDataJPA.infrastructure.configuration.ApplicationConfiguration;
import revision.springDataJPA.infrastructure.database.entity.EmployeeEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void firstTestForEmployeeFunctionality() {

        // given
        employeeService.create(someEmployee());

        // when
        List<EmployeeEntity> allEmployees = employeeService.findAll();

        // then
        Assertions.assertEquals(1, allEmployees.size());
    }

    private EmployeeEntity someEmployee() {
        return EmployeeEntity.builder()
                .name("Tomek")
                .surname("Romek")
                .salary(BigDecimal.valueOf(9547.11))
                .since(OffsetDateTime.of(2010, 10, 1, 12, 10, 0, 0, ZoneOffset.UTC))
                .build();
    }

}