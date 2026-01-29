package revision.hibernate.code;

import revision.hibernate.code.manyToMany.Employee;
import revision.hibernate.code.oneToMany.Breed;
import revision.hibernate.code.oneToMany.Owner;
import revision.hibernate.code.oneToMany.Pet;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ExampleData {

    public static Employee someEmployee1() {
        return Employee.builder()
                .name("Agnieszka")
                .surname("Pracownik")
                .salary(new BigDecimal("5910.73"))
                .since(OffsetDateTime.now())
                .build();
    }

    public static Employee someEmployee2() {
        return Employee.builder()
                .name("Stefan")
                .surname("Nowacki")
                .salary(new BigDecimal("3455.12"))
                .since(OffsetDateTime.now())
                .build();
    }

    public static Employee someEmployee3() {
        return Employee.builder()
                .name("Tomasz")
                .surname("Adamski")
                .salary(new BigDecimal("6112.42"))
                .since(OffsetDateTime.now())
                .build();
    }

    public static Owner someOwner1() {
        return Owner.builder().name("Stefan").surname("Nowacki")
                .phone("+48 589 245 114").email("stefan@zajavka.pl").build();
    }

    public static Owner someOwner2() {
        return Owner.builder().name("Adrian").surname("Paczkomat")
                .phone("+48 894 256 331").email("adrian@zajavka.pl").build();
    }

    public static Pet somePet1() {
        return Pet.builder().name("Fafik").breed(Breed.DOG).build();
    }

    public static Pet somePet2() {
        return Pet.builder().name("Kiciek").breed(Breed.CAT).build();
    }

    public static Pet somePet3() {
        return Pet.builder().name("Szymek").breed(Breed.MONKEY).build();
    }

    public static Pet somePet4() {
        return Pet.builder().name("Gucio").breed(Breed.DOG).build();
    }


}
