package revision.lombok;

import lombok.SneakyThrows;
import revision.lombok.model.Dog;
import revision.lombok.model.Employee;
import revision.lombok.model.Owner;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class LombokExample {

    /*
        POJO - Plain Old Java Object - klasa niezależna od zewnetrznych bibliotek
        Java Bean - ustandaryzowanie klasy, która posiada:
         - prywatne pola
         - gettery i settery,
         - konwencja getX(), setX()
         - no argument constructor
         - implements Serializable
        Boilerplate - kod który jest duzy a realizuje małe zagadnienie
     */
    public static void main(String[] args) {

        Owner owner = Owner.of("Jurek", "Kowalski", 30);
        Dog dog = new Dog("Burek", 5, owner);

        System.out.println(dog);
        System.out.println(dog.getName());
        System.out.println(dog.getOwner());

        Dog dog2 = new Dog();
        System.out.println(dog2);
        System.out.println(dog2.getName());
        System.out.println(dog2.getOwner());

        dog.consume("test");

        System.out.println("Owner: "+ owner);

        Dog dog3 = new Dog("Reksio", 5, owner);
        Dog dog4 = new Dog("Reksio", 5, owner);
        System.out.println("dog3.equals(dog4): " + dog3.equals(dog4));


        Owner owner1 = Owner.of("Jan", "Nowak", 32);

        Owner owner2 = owner1
                .withName("JanNew")
                .withSurname("Bury")
                .withAge(20);

        System.out.println(owner1);
        System.out.println(owner2);

        Employee employee = Employee.builder()
                .name("Robert")
                .surname("Kowalski")
                .salary(BigDecimal.TEN)
                .build();

        System.out.println(employee);

        Employee employee2 = Employee.builder()
                .name("Jan")
                .surname("Nowak")
                .address("Aleje jerozolimskie")
                .dateOfBirth(LocalDate.of(1990, 10, 23))
                .build();

        System.out.println(employee2);


//        fileSize(Paths.get("noExist.txt"));
        fileSize2(Paths.get("noExist.txt"));
    }

    @SneakyThrows
    public static void fileSize2(Path path) {
        Files.size(path);
    }

    @SneakyThrows
    public static void fileSize(Path path) {
        try {
            Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
