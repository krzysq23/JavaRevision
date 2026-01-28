package revision.springDataAccess.simple;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import revision.springDataAccess.Person;

import java.util.HashMap;
import java.util.Map;

@Repository
@AllArgsConstructor
public class SimpleJdbcInsertExamples {

    private final SimpleDriverDataSource simpleDriverDataSource;

    public void simpleJdbcInsertExample() {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        simpleJdbcInsert.setTableName("PERSON");

        Map<String, Object> params = new HashMap<>();
        params.put("NAME", "Krzysiek");
        params.put("AGE", 15);

        int result = simpleJdbcInsert.execute(params);
        System.out.println("Rows affected: " + result);
    }

    public void simpleJdbcInsertWithBeanPropertySqlParameterSourceExample() {

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        simpleJdbcInsert.setTableName("PERSON");

        Person person = Person.builder()
                .name("Stefan")
                .age(55)
                .build();

        BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(person);

        int result = simpleJdbcInsert.execute(paramSource);
        System.out.println("Rows affected: " + result);
    }

}
