package revision.springBeans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import revision.springBeans.code.AnotherInjectedBean;

@Configuration
public class ConfigurationClassSecond {

    @Bean
    public AnotherInjectedBean anotherInjectedBean() {
        return new AnotherInjectedBean();
    }
}