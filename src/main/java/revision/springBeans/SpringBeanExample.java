package revision.springBeans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import revision.springBeans.code.ExamplePrototypeBean;
import revision.springBeans.code.ExampleSingletonBean;
import revision.springBeans.code.InjectedBean;
import revision.springBeans.configuration.ExampleBeansScanningConfiguration;
import revision.springBeans.code.ExampleBean;

import java.util.Arrays;

public class SpringBeanExample {

    /*
        Czyli Spring Beany są takimi obiektami, które tworzą szkielet aplikacji i które są zarządzane przez
        kontener Spring IoC (do tego przejdziemy). Bean to obiekt, który jest tworzony, składany i zarządzany
        przez kontener Spring IoC.

        Spring Bean to obiekt zarządzany przez Spring Framework, czyli:
        - zwykła klasa Javy, której cyklem życia, tworzeniem i zależnościami zarządza Spring
        Najprościej:
        Bean = obiekt, który tworzy i kontroluje Spring, a nie Ty przez new

        Java Bean. Określenie to narzuca pewnego rodzaju standard klasy Java, w której:
        • Wszystkie pola są prywatne, a dostęp do nich jest realizowany przez gettery i settery,
        • Nazewnictwo getterów i setterów trzyma się konwencji getX() i setX(),
        • Mamy dostępny publiczny bezargumentowy konstruktor,
        • Klasa ta implementuje interfejs Serializable.

        IoC - jest wzorcem projektowym, który zaleca odwrócenie kontroli
              w celu osiągnięcia luźnego powiązania pomiędzy obiektami.

        DI - jest jednym z poziomów implementacji IoC, który polega na wstrzyknięciu jednego obiektu w
             drugi, co inaczej nazywane jest wstrzykiwaniem zależności.

        Spring IoC Container - odpowiada za wstrzykiwanie obiektów w aplikacji,
              co najważniejsze odciąża nas z obowiązku zarządzania tym ręcznie.
              My tylko definiujemy co i gdzie, a resztą zajmuje się Spring IoC Container.

        • BeanFactory jest to podstawowy interfejs kontenera IoC, zalecany do użytku jedynie w przypadku
          potrzeby wyjątkowej oszczędności pamięci,
        • ApplicationContext jest to rozszerzenie BeanFactory, ulepszone o funkcje przydane aplikacjom
          enterprise – rekomendowany do używania,
        • WebApplicationContext jest to rozszerzenie ApplicationContext dedykowane dla aplikacji webowych.

        Na swojej drodze możesz natknąć się na nazwę Spring Context. Jest to po prostu inne określenie na
        Spring IoC Container.

        @Autowired - wstrzykiwanie poprzez pole. Polega na użyciu refleksji,
                         przez co nie wymaga dodatkowego kodu konstruktora i setterów

        Wstrzykiwanie poprzez konstruktor - pomaga ukonknąć zależności cyklicznych
        public ExampleBean(InjectedBean injectedBean) {
            this.injectedBean = injectedBean;
        }

        Wstrzykiwanie poprzez właściwość (setter)
        public void setInjectedBean(InjectedBean injectedBean) {
            this.injectedBean = injectedBean;
        }

        Rodzaje stereotypów tworzących Beany:
        • @Component — podstawowe oznaczenie Beana zarządzanego przez Springa, nie ma tutaj żadnego
          dodatkowego znaczenia,
        • @Service — dla Beanów zawierających logikę biznesową. Przypomnij sobie teraz o klasach, które
          nazywaliśmy serwisami. Właśnie takie klasy oznacza się przy wykorzystaniu tej adnotacji,
        • @Repository — dla Beanów z dostępem do danych. Przykładowo będą to Beany, które będą nam
          umożliwiały realizację zapytań do baz danych - jeszcze do tego przejdziemy,
        • @Controller — dla Beanów obsługujących zapytania do API. Ta adnotacja będzie nam bardzo
          przydatna, gdy będziemy umożliwiali komunikację sieciową z naszą aplikacją. Przejdziemy jeszcze
          do tego zagadnienia.


        Możemy wyróżnić kilka zakresów Spring Beanów (bean scope):
        • singleton - jedna instancja serwisu na ApplicationContext. Pamiętasz wzorzec Singleton? Właśnie
          tutaj jest on używany. W tym przypadku w całej aplikacji jest używana jedna i ta sama instancja
          danego Beana.
        • prototype - dowolna liczba instancji serwisu na ApplicationContext. W tym przypadku Spring będzie
          tworzył nową instancję danego Beana, za każdym razem gdy będziemy potrzebowali pobrać jego instancję.
        Zakresy dostępne wyłącznie dla kontekstu webowego:
            ◦ request - jedna instancja serwisu na jedno żądanie HTTP
            ◦ session - jedna instancja serwisu na jedną sesje HTTP
            ◦ application - jedna instancja serwisu na ServerContext
            ◦ websocket - jedna instancja serwisu na WebSocket

     */

    public static void main(String[] args) {

//        AnnotationConfigApplicationContext context
//                = new AnnotationConfigApplicationContext(ConfigurationClassExample.class);

        System.out.println("### Before context");
        AbstractApplicationContext context
                = new AnnotationConfigApplicationContext(ExampleBeansScanningConfiguration.class);
        System.out.println("### After context");

//        Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);

        ExampleBean exampleBean = context.getBean(ExampleBean.class);
        exampleBean.exampleMethod();

        System.out.println("\n### Before InjectedBean retrieval");
        InjectedBean injectedBean = context.getBean(InjectedBean.class);
        injectedBean.someMethod();
        System.out.println("### After InjectedBean retrieval\n");

        ExampleSingletonBean singleton1 = context.getBean(ExampleSingletonBean.class);
        ExampleSingletonBean singleton2 = context.getBean(ExampleSingletonBean.class);

        ExamplePrototypeBean prototype1 = context.getBean(ExamplePrototypeBean.class);
        ExamplePrototypeBean prototype2 = context.getBean(ExamplePrototypeBean.class);

        System.out.println("singleton1 == singleton2? " + (singleton1 == singleton2));
        System.out.println("prototype1 == prototype2? " + (prototype1 == prototype2));

        boolean isInjectedBeanASingleton =
                injectedBean == singleton1.getInjectedBean()
                        && injectedBean == singleton2.getInjectedBean()
                        && injectedBean == prototype1.getInjectedBean()
                        && injectedBean == prototype2.getInjectedBean();
        System.out.println("Is injectedBean a Singleton? " + isInjectedBeanASingleton);


        System.out.println("--- Singleton ---");
        System.out.println("singleton1.exampleValue = " + singleton1.getExampleValue());
        System.out.println("singleton2.exampleValue = " + singleton2.getExampleValue());
        System.out.println("singleton1.setExampleValue(2)");
        singleton1.setExampleValue(2);
        System.out.println();
        System.out.println("singleton1.exampleValue = " + singleton1.getExampleValue());
        System.out.println("singleton2.exampleValue = " + singleton2.getExampleValue());
        System.out.println();
        System.out.println("--- Prototype ---");
        System.out.println("prototype1.exampleValue = " + prototype1.getExampleValue());
        System.out.println("prototype2.exampleValue = " + prototype2.getExampleValue());
        System.out.println("prototype1.setExampleValue(2)");
        prototype1.setExampleValue(2);
        System.out.println();
        System.out.println("prototype1.exampleValue = " + prototype1.getExampleValue());
        System.out.println("prototype2.exampleValue = " + prototype2.getExampleValue());


        System.out.println("\n### Before context closed");
        context.close();
        System.out.println("### After context closed");

    }

}
