package revision.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import revision.hibernate.code.AppConfig;
import revision.hibernate.code.CriteriaQueryExample;
import revision.hibernate.code.JPQLQueryRepository;

public class CriteriaExample {

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        CriteriaQueryExample criteriaQuery = context.getBean(CriteriaQueryExample.class);

        System.out.println("\nCriteria Query Select:");
        criteriaQuery.criteriaExample();

        System.out.println("\nCriteria Query Parameter:");
        criteriaQuery.criteriaParameterExample();

        System.out.println("\nCriteria Query Where:");
        criteriaQuery.criteriaWhereExample();

    }
}
