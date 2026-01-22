package revision.springBeans.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ ConfigurationClassFirst.class, ConfigurationClassSecond.class })
public class ConfigurationClassExample {





}

