package revision.springDataAccess.simple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import revision.MyProperties;

import java.sql.Driver;


@Configuration
@ComponentScan
public class DataSourceConfiguration {

    @Bean
    public SimpleDriverDataSource databaseDataSource() {

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        try {
            String url = MyProperties.getProperty("spring.datasource.url");
            String username = MyProperties.getProperty("spring.datasource.username");
            String password = MyProperties.getProperty("spring.datasource.password");

            dataSource.setDriverClass((Class<? extends Driver>) Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance().getClass());
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

        } catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return dataSource;
    }

}
