package revision.springBeans.code;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SingletonLombokBean {

    public PrototypeBean prototypeBean;


}
