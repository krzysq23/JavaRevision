package revision.springDataJPA.infrastructure.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import revision.springDataJPA.EmployeeRunner;

@Configuration
@ComponentScan(basePackageClasses = EmployeeRunner.class)
@Import(PersistenceJPAConfiguration.class)
public class ApplicationConfiguration {

}
