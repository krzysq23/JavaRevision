package revision.springDataAccess.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import revision.springDataAccess.Person;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NamedParameterJdbcTemplateExamples {

    private final SimpleDriverDataSource simpleDriverDataSource;

    public void namedParameterJdbcTemplateExample() {

        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = new NamedParameterJdbcTemplate(simpleDriverDataSource);

        String sqlInsert = "INSERT INTO PERSON (NAME, AGE) VALUES (:name, :age)";

        Map<String, Object> params1 = new HashMap<>();
        params1.put("name", "Zenek");
        params1.put("age", 56);

        namedParameterJdbcTemplate.update(sqlInsert, params1);

        MapSqlParameterSource params2 = new MapSqlParameterSource()
                .addValue("name", "Czesiek")
                .addValue("age", 73);
        namedParameterJdbcTemplate.update(sqlInsert, params2);

        Person person = Person.builder()
                .name("Wiesio")
                .age(12)
                .build();
        BeanPropertySqlParameterSource params3 = new BeanPropertySqlParameterSource(person);

        namedParameterJdbcTemplate.update(sqlInsert, params3);
    }

}
