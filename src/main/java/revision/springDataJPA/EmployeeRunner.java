package revision.springDataJPA;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import revision.springDataJPA.business.*;
import revision.springDataJPA.infrastructure.configuration.ApplicationConfiguration;

public class EmployeeRunner {

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        EmployeeService employeeService = context.getBean(EmployeeService.class);

//        employeeService.checkTransactionalSuccess();
//        employeeService.checkTransactionalFailure();


        EmployeeJPAService employeeJPAService = context.getBean(EmployeeJPAService.class);
//        employeeJPAService.runSuccessful();
//        employeeJPAService.testTransactional();

        QueryByExampleService queryByExampleService = context.getBean(QueryByExampleService.class);
//        queryByExampleService.callCustomer();
//        queryByExampleService.callProducer();
//        queryByExampleService.callPurchase();
//        queryByExampleService.callExample();
//        queryByExampleService.callQueryExample();
//        queryByExampleService.callQueryExample();


        SortingService sortingService = context.getBean(SortingService.class);
//        sortingService.sortingExample();

        PaginationService paginationService = context.getBean(PaginationService.class);
//        paginationService.paginationExample();
    }
}
