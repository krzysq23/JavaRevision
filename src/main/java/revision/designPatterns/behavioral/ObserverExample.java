package revision.designPatterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ObserverExample {

    /*
        Observer to behawioralny wzorzec projektowy, który rozwiązuje problem:
        - Jak powiadamiać wiele obiektów o zmianie stanu jednego obiektu, bez silnego powiązania między nimi?
        Najprościej:
        „Ktoś się zmienił → wszyscy zainteresowani dostają powiadomienie.”

        Struktura wzorca:

        Observers ───► Subject
                  ◄─── notify()

        • Subject (Observable) – obiekt obserwowany
        • Observer – obiekty obserwujące
        Subject:
        • trzyma listę obserwatorów
        • powiadamia ich o zmianach
        Observer:
        • reaguje na powiadomienie

     */

    public static void main(String[] args) {

        // Example 1
        Subject subject = new Subject();

        subject.addObserver(new LoggerObserver());
        subject.addObserver(new UiObserver());

        subject.setState(10);

        // Example 2
        NewsTopic subject2 = new NewsTopic();

        Observer2 observer1 = new NewsSubscriber("NewsSubscriber1");
        Observer2 observer2 = new NewsSubscriber("NewsSubscriber2");
        Observer2 observer3 = new NewsSubscriber("NewsSubscriber3");

        subject2.register(observer1);
        subject2.register(observer2);
        subject2.register(observer3);

        subject2.setNews("News Arrived!\n");

        // Example 3 Java <8
        NewsTopic subject3 = new NewsTopic();
        Observer2 observer4 = news -> System.out.printf("Message received: %s%n", news);
        subject3.register(observer4);
        subject3.setNews("News Arrived!");
    }

    /*
        Example 1
     */
    interface Observer {
        void update(int newState);
    }

    static class Subject {

        private final List<Observer> observers = new ArrayList<>();
        private int state;

        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        public void setState(int state) {
            this.state = state;
            notifyObservers();
        }

        private void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(state);
            }
        }
    }

    static class LoggerObserver implements Observer {
        @Override
        public void update(int newState) {
            System.out.println("Logger: state = " + newState);
        }
    }

    static class UiObserver implements Observer {
        @Override
        public void update(int newState) {
            System.out.println("UI updated: " + newState);
        }
    }

    /*
        Example 2
     */
    public interface Subject2 {
        void register(Observer2 observer);
        void unregister(Observer2 observer);
    }

    public interface Observer2 {
        void update(final String message);
    }

    public static class NewsTopic implements Subject2 {

        private final List<Observer2> observers = new ArrayList<>();
        private String news;

        @Override
        public void register(Observer2 observer) {
            if (Objects.isNull(observer)) {
                throw new NullPointerException("Observer is null");
            }
            if (!observers.contains(observer)) {
                observers.add(observer);
            }
        }

        @Override
        public void unregister(Observer2 observer) {
            observers.remove(observer);
        }

        public void setNews(String message) {
            System.out.printf("Message posted: %s%n", message);
            this.news = message;
            for (Observer2 observer : observers) {
                observer.update(message);
            }
        }
    }

    public static class NewsSubscriber implements Observer2 {

        private final String name;

        public NewsSubscriber(String name) {
            this.name = name;
        }

        @Override
        public void update(final String message) {
            if (message == null) {
                System.out.printf("%s - Message null%n", name);
            } else
                System.out.printf("%s - Message received - %s%n", name, message);
        }
    }
}
