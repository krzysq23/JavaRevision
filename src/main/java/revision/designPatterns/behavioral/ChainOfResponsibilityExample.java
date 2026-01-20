package revision.designPatterns.behavioral;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainOfResponsibilityExample {

    /*
        Wzorzec Chain Of Responsibility pozwala nam na zaprojektowanie łańcucha obiektów, które będą służyły do przetworzenia
        jakiegoś fragmentu logiki. Logika taka może być wtedy podzielona na kolejne kroki w łańcuchu
        przetwarzania. Każdy obiekt będący ogniwem takiego łańcucha może wtedy zdecydować czy
        kontynuować wykonywanie logiki, czy przerwać w danym momencie, ze względu na niespełnienie
        pewnych warunków. Wzorzec ten jest używany do osiągnięcia luźnego powiązania (loose coupling)
        między obiektami.

        Struktura wzorca
        Client → Handler → Handler → Handler
        - Client nie zna konkretnego handlera
        - Handler zna tylko „następnego”

        • Handler - interface, który odbiera żądanie od Klienta, a następnie wysyła takie żądanie do obsługi.
          Handler wie, tylko o pierwszym ogniwie w łańcuchu i do niego wysyła prośbę o przetworzenie
          zapytania Klienta. Nie ma natomiast pojęcia o kolejnych ogniwach łańcucha. To ogniwa wiedzą o
          sobie nawzajem (przykład z LinkedList),
        • Konkretny Handler - konkretny Handler, który jest odpowiedzialny za przetworzenie konkretnego
          zapytania, które może być skierowane albo od Klienta, albo od innego Handlera,
        • Klient - obiekt, który jest źródłem zapytania, to on rozpoczyna całą interakcję w łańcuchu.

        Chain of Responsibility w Javie:
        • Servlet Filter
        • Spring Security FilterChain
        • java.util.logging.Logger

        Zalety tego podejścia:
        • Klient nie ma pojęcia jakie procesory i w jakiej kolejności będą wykonywały zdefiniowaną logikę.
          Inaczej mówiąc, jesteśmy tutaj elastyczni bo jakakolwiek zmiana w procesorach jest przezroczysta z
          perspektywy klienta, on tylko wysyła wiadomość i chce mieć ją przeprocesowaną.
        • Możemy podzielić skomplikowaną logikę na kroki co ułatwi zrozumienie konkretnych części
          procesu. Nie umieszczamy w takiej sytuacji wszystkiego w jednej klasie - wzrasta czytelność kodu.
        • Każdy obiekt jest odpowiedzialny za swoją część procesu - Single Responsibility Principle.
        • W łatwy sposób możemy dodawać kroki do naszej logiki (nawet w środku łańcucha), wystarczy, że
          stworzymy nowy handler i dowiążemy go do istniejącego łańcucha. Dla klienta jest to niewidoczne,
          tak samo jak dla innych handlerów, nie musimy ich modyfikować aby wprowadzić zmiany w całej logice.
     */

    public static void main(String[] args) {

        System.out.println("\nExample 1");
        Handler chain = new AuthHandler();
        chain
                .setNext(new LoggingHandler())
                .setNext(new BusinessHandler());

        chain.handle(Request.of("http://test.pl/login", "POST", true));

        System.out.println("\nExample 2");
        CarHandler carHandler = new CabrioletHandler();
        CarHandler handler2 = new JeepHandler();
        CarHandler handler3 = new ColorHandler();
        CarHandler handler4 = new EquipmentHandler();
        CarHandler handler5 = new QualityCheckHandler();
        carHandler.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        handler3.setNextHandler(handler4);
        handler4.setNextHandler(handler5);
        carHandler.handle(Car.of(Car.Type.CABRIOLET, "Blue", List.of("Steering wheel")));
        carHandler.handle(Car.of(Car.Type.JEEP, "Black", List.of("Wheels")));

        System.out.println("\nExample 3 - GENERIC");
        GenericHandler<String> gHandler1 = new TextMyHandler();
        GenericHandler<String> gHandler2 = new TextBugHandler();
        GenericHandler<String> gHandler3 = new TextOpinionHandler();
        gHandler1.setNextHandler(gHandler2);
        gHandler2.setNextHandler(gHandler3);
        String result = gHandler1.handle(
                "Wziąłem udział w bootcampie i powiem wam, że java nie jest super. " +
                        "Zrobiłem zadanie 2 i w zadaniu jest błąd. " +
                        "Podsumowując, mam złą opinię o bootcampie!");
        System.out.println(result);

        System.out.println("\nExample 4 - GENERIC Java 8 <");
        UnaryOperator<String> textMyHandler = (String input) ->
                input.replace("java nie jest super", "java jest super!");
        UnaryOperator<String> textBugHandler = (String input) ->
                input.replace("w zadaniu jest błąd", "w zadaniu nie ma błędu");
        UnaryOperator<String> textOpinionHandler = (String input) ->
                input.replace("mam złą opinię o bootcampie", "bootcamp jest super");

        Function<String, String> pipeline = textMyHandler
                .andThen(textBugHandler)
                .andThen(textOpinionHandler);

        String input = "Wziąłem udział w bootcampie i powiem wam, że java nie jest super. " +
                "Zrobiłem zadanie 2 i w zadaniu jest błąd. " +
                "Podsumowując, mam złą opinię o bootcampie!";

        String result2 = pipeline.apply(input);
        System.out.println(result2);
    }

    /*
        Example 1
     */

    abstract static class Handler {

        protected Handler next;

        public Handler setNext(Handler next) {
            this.next = next;
            return next;
        }

        public abstract void handle(Request request);
    }

    @Data
    @AllArgsConstructor(staticName = "of")
    static class Request {

        private final String url;
        private final String method;
        private boolean isAuthenticated;

        public boolean isAuthenticated() {
            return isAuthenticated;
        }
    }

    static class AuthHandler extends Handler {

        @Override
        public void handle(Request request) {
            System.out.println("AuthHandler request");
            if (!request.isAuthenticated()) {
                System.out.println("Authentication failed");
                return;
            }
            if (next != null) {
                next.handle(request);
            }
        }
    }

    static class LoggingHandler extends Handler {

        @Override
        public void handle(Request request) {
            System.out.println("Logging request");
            if (next != null) {
                next.handle(request);
            }
        }
    }

    static class BusinessHandler extends Handler {

        @Override
        public void handle(Request request) {
            System.out.println("Processing business logic");
        }
    }


    /*
        Example 2
     */

    public interface CarHandler {

        void setNextHandler(CarHandler nextHandler);
        void handle(Car car);
    }

    @Data
    @With
    @AllArgsConstructor(staticName = "of")
    public static class Car {

        private Type type;
        private String color;
        private List<String> equipment;

        enum Type {
            CABRIOLET,
            JEEP
        }
    }

    public static class CabrioletHandler implements CarHandler {

        private CarHandler nextHandler;

        @Override
        public void setNextHandler(final CarHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public void handle(final Car car) {
            if (Car.Type.CABRIOLET.equals(car.getType())) {
                System.out.println("Preparing Cabriolet Skeleton");
                final List<String> cabrioletEquipment = new ArrayList<>(car.getEquipment());
                cabrioletEquipment.add("Cabriolet roof");
                this.nextHandler.handle(car.withEquipment(cabrioletEquipment));
            } else {
                this.nextHandler.handle(car);
            }
        }
    }

    public static class JeepHandler implements CarHandler {

        private CarHandler nextHandler;
        @Override
        public void setNextHandler(final CarHandler nextHandler) {
            this.nextHandler = nextHandler;
        }
        @Override
        public void handle(final Car car) {
            if (Car.Type.JEEP.equals(car.getType())) {
                System.out.println("Preparing Jeep Skeleton");
                final List<String> jeepEquipment = new ArrayList<>(car.getEquipment());
                jeepEquipment.add("Spare Wheel");
                this.nextHandler.handle(car.withEquipment(jeepEquipment));
            } else {
                this.nextHandler.handle(car);
            }
        }
    }

    public static class ColorHandler implements CarHandler {

        private CarHandler nextHandler;

        @Override
        public void setNextHandler(final CarHandler nextHandler) {
            this.nextHandler = nextHandler;
        }
        @Override
        public void handle(final Car car) {
            if (Objects.nonNull(car.getColor())) {
                System.out.printf("Preparing %s color%n", car.getColor());
            }
            this.nextHandler.handle(car);
        }
    }

    public static class EquipmentHandler implements CarHandler {

        private CarHandler nextHandler;

        @Override
        public void setNextHandler(final CarHandler nextHandler) {
            this.nextHandler = nextHandler;
        }
        @Override
        public void handle(final Car car) {
            if (Objects.nonNull(car.getEquipment())) {
                System.out.printf("Preparing equipment: %s%n", car.getEquipment());
            }
            this.nextHandler.handle(car);
        }
    }

    public static class QualityCheckHandler implements CarHandler {

        private CarHandler nextHandler;

        @Override
        public void setNextHandler(final CarHandler nextHandler) {
            this.nextHandler = nextHandler;
        }
        @Override
        public void handle(final Car car) {
            System.out.println("Checking quality!\n");
        }
    }

    /*
        Example 3 - Generic
     */

    public static abstract class GenericHandler<T> {

        protected GenericHandler<T> handler;

        public void setNextHandler(GenericHandler<T> nextHandler) {
            this.handler = nextHandler;
        }

        public T handle(T input) {
            T r = handleInput(input);
            if (handler != null) {
                return handler.handle(r);
            }
            return r;
        }

        abstract protected T handleInput(T input);
    }

    public static class TextBugHandler extends GenericHandler<String> {

        @Override
        protected String handleInput(final String input) {
            return input.replace("w zadaniu jest błąd", "w zadaniu nie ma błędu");
        }
    }

    public static class TextOpinionHandler extends GenericHandler<String> {

        @Override
        protected String handleInput(final String input) {
            return input.replace("mam złą opinię o bootcampie", "bootcamp jest super");
        }
    }

    public static class TextMyHandler extends GenericHandler<String> {

        @Override
        protected String handleInput(final String input) {
            return input.replace("java nie jest super", "java jest super!");
        }
    }



}

