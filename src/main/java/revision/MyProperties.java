package revision;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

public class MyProperties {

    private static Environment env;

    static {
        try {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
            PropertySource<?> yamlTestProperties =
                    loader.load("yamlTestProperties",
                                    new ClassPathResource("application-test.yml"))
                            .get(0);

            ctx.getEnvironment().getPropertySources().addLast(yamlTestProperties);
            ctx.refresh();
            env = ctx.getEnvironment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property) {
        return env.getProperty(property);
    }
}
