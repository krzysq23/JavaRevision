package revision.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import revision.hibernate.code.AppConfig;
import revision.hibernate.code.EmployeeRepository;
import revision.hibernate.code.ExampleData;
import revision.hibernate.code.oneToMany.Owner;
import revision.hibernate.code.oneToMany.OwnerRepository;

import java.util.Set;

public class HibernateExample {

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

//        example1(context);

        exampleOneToMany(context);
        
    }

    private static void exampleOneToMany(ApplicationContext context) {

        OwnerRepository ownerRepository = context.getBean(OwnerRepository.class);

        Owner owner1 = ownerRepository.insertData(
                ExampleData.someOwner1(),
                Set.of(ExampleData.somePet1(), ExampleData.somePet2()));
        Owner owner2 = ownerRepository.insertData(
                ExampleData.someOwner2(),
                Set.of(ExampleData.somePet3(), ExampleData.somePet4()));

        ownerRepository.listOwners()
                .forEach(owner -> System.out.println("###Owner listing: " + owner));

    }

    private static void example1(ApplicationContext context) {

        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

//        employeeRepository.insertEmployee(EmployeeData.someEmployee1());
//        employeeRepository.insertEmployee(EmployeeData.someEmployee2());
//        employeeRepository.insertEmployee(EmployeeData.someEmployee3());

        employeeRepository.listEmployees()
                .forEach(employee -> System.out.println("###Employee: " + employee));

    }

}
