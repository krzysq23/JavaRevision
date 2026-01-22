package revision.springBeans.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExampleBean {

    private InjectedBean injectedBean;
    private AnotherInjectedBean anotherInjectedBean;

    private SomeCommonInterface someCommonInterface;

    public ExampleBean() {
        System.out.println("Calling ExampleBean()");
    }

    /*
    @Qualifier("someMyBean") SomeCommonInterface someCommonInterface
    lub nazwa klasy w nazie
    SomeCommonInterface someMyBean
     */
    @Autowired
    public ExampleBean(InjectedBean injectedBean,
                       AnotherInjectedBean anotherInjectedBean,
                       SomeCommonInterface someMyBean) {
        this.injectedBean = injectedBean;
        this.anotherInjectedBean = anotherInjectedBean;
        this.someCommonInterface = someMyBean;
        System.out.println("Calling ExampleBean(...)");
    }

//    @Autowired
    public void setAnotherInjectedBean(AnotherInjectedBean anotherInjectedBean) {
        this.anotherInjectedBean = anotherInjectedBean;
    }

//    @Autowired
    public void setInjectedBean(InjectedBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    public void exampleMethod() {
        System.out.println("Calling exampleMethod(), \ninjectedBean: "
                + injectedBean + ", \nanotherInjectedBean" + anotherInjectedBean
                + ", \nsomeCommonInterface: " + someCommonInterface);
    }
}
