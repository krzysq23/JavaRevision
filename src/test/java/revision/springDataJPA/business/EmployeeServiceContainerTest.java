package revision.springDataJPA.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import revision.springDataJPA.infrastructure.configuration.ApplicationConfiguration;
import revision.springDataJPA.infrastructure.database.entity.EmployeeEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Testcontainers
@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
class EmployeeServiceContainerTest {

    @Container
    static MySQLContainer<?> mySQL = new MySQLContainer<>("mysql:8.0");

    @DynamicPropertySource
    static void postgreSQLProperties(DynamicPropertyRegistry registry) {
        registry.add("jdbc.url", mySQL::getJdbcUrl);
        registry.add("jdbc.user", mySQL::getUsername);
        registry.add("jdbc.pass", mySQL::getPassword);
    }

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