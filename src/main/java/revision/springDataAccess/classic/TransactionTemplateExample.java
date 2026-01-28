package revision.springDataAccess.classic;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import revision.springDataAccess.Person;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionTemplateExample {

    private final SimpleDriverDataSource simpleDriverDataSource;

    private TransactionTemplate transactionTemplate;

    private static final String SQL = "INSERT INTO PERSON (NAME, AGE) VALUES (:name, :age)";

    public void transactionTemplateUseExample() {
        transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                return someMethod();
            }
        });
    }

    public void transactionTemplateLambdaUseExample() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                someVoidMethod();
            }
        });
    }

    public void transactionTemplateCallback() {

        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Object result = someMethod();
                if (true) {
                    status.setRollbackOnly();
                    log.error("Transaction rollback");
                }
                return result;
            }
        });
    }

    private Integer someMethod() {
        int result = 0;

        NamedParameterJdbcTemplate jdbcTemplate
                = new NamedParameterJdbcTemplate(simpleDriverDataSource);

        Person person1 = Person.builder().name("Zenek").age(22).build();
        Person person2 = Person.builder().name("Czesio").age(49).build();
        Person person3 = Person.builder().name("Krzysio").age(33).build();

        result += jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person1));
        result += jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person2));
        result += jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person3));

        return result;
    }

    private void someVoidMethod() {

        NamedParameterJdbcTemplate jdbcTemplate
                = new NamedParameterJdbcTemplate(simpleDriverDataSource);

        Person person1 = Person.builder().name("Wiesio").age(22).build();
        Person person2 = Person.builder().name("Czesio").age(49).build();
        Person person3 = Person.builder().name("Krzysio").age(33).build();

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person1));
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person2));
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person3));

    }

}
