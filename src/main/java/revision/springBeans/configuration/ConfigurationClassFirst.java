package revision.springBeans.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import revision.springBeans.code.AnotherInjectedBean;
import revision.springBeans.code.ExampleBean;
import revision.springBeans.code.InjectedBean;
import revision.springBeans.code.SomeCommonInterface;

@Configuration
public class ConfigurationClassFirst {

    @Bean
    public ExampleBean exampleBean(InjectedBean injectedBean,
                                   AnotherInjectedBean anotherInjectedBean,
                                   @Qualifier("someMyBean") SomeCommonInterface someCommonInterface) {
        return new ExampleBean(injectedBean, anotherInjectedBean, someCommonInterface);
    }


    @Bean
    public InjectedBean injectedBean() {
        return new InjectedBean();
    }
}