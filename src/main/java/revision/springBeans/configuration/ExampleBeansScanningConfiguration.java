package revision.springBeans.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import revision.springBeans.code.InjectedBean;

@Configuration
@ComponentScan(basePackages = "revision.springBeans.code")
public class ExampleBeansScanningConfiguration {

    /*
        ComponentScan przyjmuje również wiele argumentów:
        • basePackages — lista nazw pakietów do przeskanowania,
        • basePackagesClasses — lista nazw klas, których pakiety są do przeskanowania - w ten sposób
          oznaczamy pakiet poprzez klasę, która się w takim pakiecie znajduje,
        • includeFilters — dodatkowe filtry do skanowania obiektów,
        • excludeFilter — dodatkowe filtry do pominięcia obiektów podczas skanowania,
        • useDefaultFilters — boolean determinujący czy skanowanie ma wykrywać adnotacje @Component,
          @Repository, @Service albo @Controller (domyślnie: true).
     */

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public InjectedBean injectedBean() {
        return new InjectedBean();
    }

}
