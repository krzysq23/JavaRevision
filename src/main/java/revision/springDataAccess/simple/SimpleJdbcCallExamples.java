package revision.springDataAccess.simple;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class SimpleJdbcCallExamples {

    private final SimpleDriverDataSource simpleDriverDataSource;

    public void simpleJdbcCallExample() {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(simpleDriverDataSource);
        simpleJdbcCall.withFunctionName("calc_sum");

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("value1", 2)
                .addValue("value2", 3);

        Integer result = simpleJdbcCall.executeFunction(Integer.class, sqlParameterSource);
        System.out.println("Result: " + result);
    }
}
