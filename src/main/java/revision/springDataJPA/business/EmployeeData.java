package revision.springDataJPA.business;

import revision.springDataJPA.infrastructure.database.entity.EmployeeEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class EmployeeData {

    public static EmployeeEntity someEmployee1() {
        return EmployeeEntity.builder()
                .name("Agnieszka")
                .surname("Pracownik")
                .salary(new BigDecimal("5910.73"))
                .since(OffsetDateTime.now())
                .build();
    }

    public static EmployeeEntity someEmployee2() {
        return EmployeeEntity.builder()
                .name("Stefan")
                .surname("Nowacki")
                .salary(new BigDecimal("3455.12"))
                .since(OffsetDateTime.now())
                .build();
    }

    public static EmployeeEntity someEmployee3() {
        return EmployeeEntity.builder()
                .name("Tomasz")
                .surname("Adamski")
                .salary(new BigDecimal("6112.42"))
                .since(OffsetDateTime.now())
                .build();
    }
}
