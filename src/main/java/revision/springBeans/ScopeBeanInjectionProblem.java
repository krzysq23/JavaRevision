package revision.springBeans;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import revision.springBeans.code.PrototypeBean;
import revision.springBeans.code.SingletonBean;
import revision.springBeans.configuration.ExampleBeansScanningConfiguration;

public class ScopeBeanInjectionProblem {

    public static void main(String[] args) {

        AbstractApplicationContext context
                = new AnnotationConfigApplicationContext(ExampleBeansScanningConfiguration.class);

        System.out.println();

        SingletonBean firstSingleton = context.getBean(SingletonBean.class);
        ObjectFactory<PrototypeBean> firstPrototypeFactory = firstSingleton.callPrototype();

        SingletonBean secondSingleton = context.getBean(SingletonBean.class);
        ObjectFactory<PrototypeBean> secondPrototypeFactory = secondSingleton.callPrototype();

        System.out.println("firstPrototype == secondPrototype? "
                + (firstPrototypeFactory.getObject() == secondPrototypeFactory.getObject()));

    }
}
