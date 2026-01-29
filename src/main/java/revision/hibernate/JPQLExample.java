package revision.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import revision.hibernate.code.AppConfig;
import revision.hibernate.code.JPQLQueryRepository;

public class JPQLExample {

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        JPQLQueryRepository jpqlQuery = context.getBean(JPQLQueryRepository.class);

//        jpqlQuery.selectOwner();
//
//        jpqlQuery.selectId();
//
//        jpqlQuery.where();
//
//        jpqlQuery.andOrInBetween();

        jpqlQuery.namedQuery();

    }
}
