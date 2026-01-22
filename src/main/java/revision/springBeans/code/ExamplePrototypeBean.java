package revision.springBeans.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExamplePrototypeBean {

    private InjectedBean injectedBean;

    private int exampleValue = 1;

    @Autowired
    public ExamplePrototypeBean(InjectedBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    public void exampleMethod() {
        injectedBean.anotherExampleMethod();
    }

    public InjectedBean getInjectedBean() {
        return injectedBean;
    }

    public int getExampleValue() {
        return exampleValue;
    }

    public void setExampleValue(int exampleValue) {
        this.exampleValue = exampleValue;
    }

}
