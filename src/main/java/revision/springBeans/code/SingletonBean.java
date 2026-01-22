package revision.springBeans.code;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SingletonBean {

    private ObjectFactory<PrototypeBean> prototypeBean;

    public SingletonBean(final ObjectFactory<PrototypeBean> prototypeBean) {
        this.prototypeBean = prototypeBean;
        System.out.println("Singleton created");
    }

    public ObjectFactory<PrototypeBean> callPrototype() {
        System.out.println("Calling callPrototype: " + LocalTime.now());
        return prototypeBean;
    }

}
