package revision.designPatterns.structural;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class ProxyExample {

    /*
        Proxy to strukturalny wzorzec projektowy, którego celem jest:
        - kontrolować dostęp do innego obiektu, nie zmieniając jego kodu
        Czyli:
        „zamiast prawdziwego obiektu dajemy jego zastępcę (pośrednika)”.

        Wzorzec ten będzie nam potrzebny jeżeli będziemy zastanawiać się nad zapewnieniem kontrolowanego
        dostępu do jakiegoś zasobu. Czyli np. mamy fragment kodu, który może zostać wykonany pod
        warunkiem, że użytkownik, który go wykonuje ma odpowiednie uprawnienia.

        Innym przykładem zastosowania Proxy może być sytuacja gdy wiemy, że często odpytujemy jakiś zasób
        o tę samą informację.  Wtedy Proxy również wie, że nie ma
        sensu odpytywać serwisu internetowego o pogodę, bo przecież odpowiedź i tak jest taka sama, więc
        może równie dobrze zwrócić to co zwrócił nam ostatnio. Obiekt, który przetrzymuje takie informacje w
        ten sposób w praktyce nazywa się cache, natomiast sam proces cacheowaniem.

        Rodzaje Proxy:                      |   3. Remote Proxy                     | 6. Spring intensywnie używa Proxy (JDK Dynamic Proxy)
        1. Virtual Proxy                    |   • obiekt zdalny (RMI, REST, gRPC)   | np.: Proxy.newProxyInstance(...)
        • lazy loading                      |                                       | Spring:
        • ciężkie obiekty                   |   4. Caching Proxy                    | • @Transactional
                                            |   • zapamiętuje wyniki                | • @Cacheable
        2. Protection Proxy                 |   • zmniejsza liczbę wywołań          | • @Async
        • kontrola dostępu                  |                                       | • AOP
        np.:                                |   5. Logging / Monitoring Proxy       |
        if (!user.hasPermission()) {        |   • mierzy czas                       |
            throw new SecurityException();  |   • loguje                            |
        }                                   |                                       |

        Zalety tego podejścia:
        • Możemy kontrolować dostęp do jakiegoś zasobu i dokonywać wstępnej weryfikacji na
          wcześniejszym poziomie.
        • Możemy zmniejszyć czas odpowiedzi na jakieś zapytanie, jeżeli obiekt Proxy wie, że odpowiedź w
          danym przedziale czasu nie ulegnie zmianie. Wtedy sam obiekt proxy, bez odpytywania obiektu
          źródłowego może taką odpowiedź zwrócić.

        Struktura wzorca:

        Client → Subject (interface)
                     ▲
                     │
                 Proxy
                     │
                  RealSubject

        • Client zna tylko Subject
        • Proxy kontroluje dostęp
        • RealSubject robi „prawdziwą robotę”

        Poniższy przykłd:
        Klient -> Kelner (PizzaBakerProxy) -> Chef (PizzaBakerImpl)
     */

    public static void main(String[] args) {

        PizzaBaker executor = new PizzaBakerProxy(new PizzaBakerImpl());
        try {
            executor.bake("Pepperoni");
            executor.bake("Margeritta");
            executor.bake("Hawaiian");
        } catch (Exception e) {
            System.err.printf("Exception: %s", e.getMessage());
        }

        Image image = new ImageProxy("photo.png");

        image.display(); // ładowanie + wyświetlenie
        image.display(); // tylko wyświetlenie
    }

    @RequiredArgsConstructor
    public static class PizzaBakerProxy implements PizzaBaker {

        private static final List<String> DENIED_PIZZAS = List.of("Hawaiian");

        private final PizzaBaker executor;

        @Override
        public void bake(String pizza) {
            if (DENIED_PIZZAS.contains(pizza)) {
                throw new RuntimeException(String.format("%s? We don't do this here!", pizza));
            } else {
                executor.bake(pizza);
            }
        }
    }

    public interface PizzaBaker {
        void bake(String pizza);
    }

    public static class PizzaBakerImpl implements PizzaBaker {
        @Override
        public void bake(String pizza) {
            System.out.println("Baking pizza: " + pizza);
        }
    }


    /*
        Przykład 2
     */
    interface Image {
        void display();
    }

    @RequiredArgsConstructor
    static class RealImage implements Image {

        private final String filename;

        private void loadFromDisk(String filename) {
            System.out.println("Loading " + filename);
        }

        @Override
        public void display() {
            System.out.println("Displaying " + filename);
        }
    }

    @RequiredArgsConstructor
    static class ImageProxy implements Image {

        private RealImage realImage;
        private final String filename;

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

}
