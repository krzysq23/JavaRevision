package revision.springDataAccess.declarative;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import revision.springDataAccess.Person;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionDeclarativeTemplateExample {

    private final SimpleDriverDataSource simpleDriverDataSource;

    private static final String SQL = "INSERT INTO PERSON (NAME, AGE) VALUES (:name, :age)";

    @Transactional
    public Integer someMethod() {
        int result = 0;

        NamedParameterJdbcTemplate jdbcTemplate
                = new NamedParameterJdbcTemplate(simpleDriverDataSource);

        Person person1 = Person.builder().name("Krzysio").age(22).build();
        Person person2 = Person.builder().name("Czesio").age(49).build();
        Person person3 = Person.builder().name("Krzysio").age(33).build();

        result += jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person1));
        result += jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person2));
        result += jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person3));

        return result;
    }

    public void someVoidMethod() {

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
