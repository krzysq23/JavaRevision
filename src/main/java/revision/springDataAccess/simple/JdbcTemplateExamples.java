package revision.springDataAccess.simple;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;
import revision.springDataAccess.Person;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateExamples {

    private final SimpleDriverDataSource simpleDriverDataSource;

    public void insertExample() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(simpleDriverDataSource);
        String sqlInsert = "INSERT INTO PERSON (NAME, AGE) VALUES (?, ?)";
        jdbcTemplate.update(sqlInsert, "Roman", 25);
    }

    public void updateExample() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(simpleDriverDataSource);
        String sqlUpdate = "UPDATE PERSON SET AGE = ? where NAME = ?";
        jdbcTemplate.update(sqlUpdate, 29, "Roman");
    }

    public void selectExample() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(simpleDriverDataSource);
        String sqlSelect = "SELECT * FROM PERSON";

        RowMapper<Person> personRowMapper = (resultSet, rowNum) -> Person.builder()
                .id(resultSet.getLong("ID"))
                .name(resultSet.getString("NAME"))
                .age(resultSet.getInt("AGE"))
                .build();
        List<Person> result = jdbcTemplate.query(sqlSelect, personRowMapper);
        System.out.println(result);

        BeanPropertyRowMapper<Person> personBeanPropertyRowMapper
                = BeanPropertyRowMapper.newInstance(Person.class);
        List<Person> result2 = jdbcTemplate.query(sqlSelect, personBeanPropertyRowMapper);
        System.out.println(result2);
    }

    public void deleteExample() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(simpleDriverDataSource);
        String sqlDelete = "DELETE FROM PERSON where NAME = ?";
        int result = jdbcTemplate.update(sqlDelete, "Roman");
        System.out.println(result);
    }

}
