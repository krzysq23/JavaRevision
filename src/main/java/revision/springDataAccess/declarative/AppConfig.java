package revision.springDataAccess.declarative;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import revision.MyProperties;

import java.sql.Driver;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class AppConfig {

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

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(databaseDataSource());
    }

}
