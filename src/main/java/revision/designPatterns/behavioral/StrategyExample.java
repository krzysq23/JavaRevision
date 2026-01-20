package revision.designPatterns.behavioral;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StrategyExample {

    /*
        Stategy inaczej Policy Pattern!

        Strategy to behawioralny wzorzec projektowy, który odpowiada na pytanie:
        - Jak łatwo wymieniać algorytmy, bez zmiany kodu, który z nich korzysta?
        Najprościej:
        „Mam wiele sposobów zrobienia tej samej rzeczy i mogę je wymieniać w locie.”

        Strategia rozwiązuje problem wyboru algorytmu, który ma być wykorzystany w programie przez klienta.
        Czyli, Ty jako klient danej metody chcesz zdecydować jaki algorytm ma zostać wykorzystany w jej środku.

        Struktura wzorca:

        Context → Strategy
                      ▲
             ConcreteStrategy

        • Context deleguje pracę
        • Strategie są wymienne

        Strategy w Javie:
        • Comparator
        • Runnable
        • Predicate
        • Function
        • Spring @Qualifier

        Zalety tego podejścia:
        • Wzorzec Strategii jest bardzo wygodny gdy chcemy wyjąć "fragment" logiki poza metodę i na
        zewnątrz tej metody zdecydować o algorytmie, który ma zostać uruchomiony w środku.
        • Wzorzec ten daje nam dużą elastyczność gdy mamy kilka algorytmów do wyboru i w trakcie
        działania programu chcemy wybrać jeden z nich.

     */

    public static void main(String[] args) {

        System.out.println("Example 1");
        PaymentContext ctx = new PaymentContext();

        ctx.setStrategy(new CardPayment());
        ctx.execute(100);

        ctx.setStrategy(new PaypalPayment());
        ctx.execute(200);

        System.out.println("Example 2");
        OnlineShop onlineShop = new OnlineShop();
        onlineShop.addParcel(new Parcel("skarpetki"));
        onlineShop.addParcel(new Parcel("monitory"));
        onlineShop.addParcel(new Parcel("samochody"));

        System.out.println("\n##CourierStrategy##");
        onlineShop.deliver(new CourierStrategy());

        System.out.println("\n##PostStrategy##");
        onlineShop.deliver(new PostStrategy());

        System.out.println("\n##ParcelLockerStrategy##");
        onlineShop.deliver(new ParcelLockerStrategy());

        System.out.println("Example 3 - Java 8 <");
        System.out.println("\n##CourierStrategy##");
        onlineShop.deliver2(parcel -> System.out.printf("Parcel %s delivered by Courier%n", parcel));
        System.out.println("\n##PostStrategy##");
        onlineShop.deliver2(parcel -> System.out.printf("Parcel %s delivered by Post%n", parcel));
        System.out.println("\n##ParcelLockerStrategy##");
        onlineShop.deliver2(parcel -> System.out.printf("Parcel %s delivered by ParcelLocker%n", parcel));

    }

    /*
        Example 1
     */
    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CardPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("Paid by card: " + amount);
        }
    }

    static class PaypalPayment implements PaymentStrategy {
        public void pay(double amount) {
            System.out.println("Paid by PayPal: " + amount);
        }
    }

    static class PaymentContext {

        private PaymentStrategy strategy;

        void setStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        void execute(double amount) {
            strategy.pay(amount);
        }
    }

    /*
        Example 2
     */
    public interface DeliveryStrategy {
        void deliver(final Parcel parcel);
    }

    public record Parcel(String name) {
    }

    public static class CourierStrategy implements DeliveryStrategy {

        @Override
        public void deliver(final Parcel parcel) {
            System.out.printf("Parcel %s delivered by Courier%n", parcel);
        }
    }

    public static class ParcelLockerStrategy implements DeliveryStrategy {

        @Override
        public void deliver(final Parcel parcel) {
            System.out.printf("Parcel %s delivered by ParcelLocker%n", parcel);
        }
    }

    public static class PostStrategy implements DeliveryStrategy {

        @Override
        public void deliver(final Parcel parcel) {
            System.out.printf("Parcel %s delivered by Post%n", parcel);
        }
    }

    public static class OnlineShop {

        private List<Parcel> parcels = new ArrayList<>();

        public void addParcel(Parcel parcel) {
            parcels.add(parcel);
        }

        public void deliver(DeliveryStrategy deliveryStrategy) {
            parcels.forEach(deliveryStrategy::deliver);
        }

        public void deliver2(Consumer<Parcel> deliveryStrategy) {
            parcels.forEach(deliveryStrategy);
        }
    }

}
