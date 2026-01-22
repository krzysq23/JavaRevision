package revision.springBeans.code;

import org.springframework.stereotype.Component;

@Component
public class AnotherInjectedBean {

    public AnotherInjectedBean() {
        System.out.println("Calling AnotherInjectedBean()");
    }
}
