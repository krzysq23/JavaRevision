package revision.springDataAccess;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import revision.springDataAccess.classic.DataSourceConfigurationClassic;
import revision.springDataAccess.classic.TransactionTemplateExample;
import revision.springDataAccess.declarative.AppConfig;
import revision.springDataAccess.declarative.TransactionDeclarativeTemplateExample;
import revision.springDataAccess.simple.*;

public class TransactionsExample {

    public static void main(String[] args) {



//        simpleMethods();

        /*
            Zarządzanie programowalne transakcją
         */
//        classicMethods();

        /*
            Zarządzanie deklaratywne transakcją
         */
        declarativeMethods();

    }

    private static void declarativeMethods() {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

        TransactionDeclarativeTemplateExample templateExample = context.getBean(TransactionDeclarativeTemplateExample.class);
        templateExample.someMethod();

    }

    private static void classicMethods() {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(DataSourceConfigurationClassic.class);

        TransactionTemplateExample transactionTemplateExample = context.getBean(TransactionTemplateExample.class);
//        transactionTemplateExample.transactionTemplateUseExample();
//        transactionTemplateExample.transactionTemplateLambdaUseExample();
        transactionTemplateExample.transactionTemplateCallback();
    }

    public static void simpleMethods() {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);

        JdbcTemplateExamples jdbcTemplateExamples = context.getBean(JdbcTemplateExamples.class);
//        jdbcTemplateExamples.insertExample();
//        jdbcTemplateExamples.updateExample();
//        jdbcTemplateExamples.selectExample();
//        jdbcTemplateExamples.deleteExample();

        SimpleJdbcInsertExamples simpleJdbcInsertExamples = context.getBean(SimpleJdbcInsertExamples.class);
//        simpleJdbcInsertExamples.simpleJdbcInsertExample();
//        simpleJdbcInsertExamples.simpleJdbcInsertWithBeanPropertySqlParameterSourceExample();

        SimpleJdbcCallExamples simpleJdbcCallExamples = context.getBean(SimpleJdbcCallExamples.class);
//        simpleJdbcCallExamples.simpleJdbcCallExample();

        NamedParameterJdbcTemplateExamples namedParameterJdbcTemplate = context.getBean(NamedParameterJdbcTemplateExamples.class);
        namedParameterJdbcTemplate.namedParameterJdbcTemplateExample();

    }
}
