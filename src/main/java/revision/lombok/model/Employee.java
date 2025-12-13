package revision.lombok.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class Employee {

    private String name;
    private String surname;
    private String email;
    private BigDecimal salary;
    private LocalDate dateOfBirth;
    private String address;

}
