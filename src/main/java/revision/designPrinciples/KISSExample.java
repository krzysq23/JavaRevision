package revision.designPrinciples;

public class KISSExample {

    /*

    KISS - Keep It Simple, Stupid - które w wolnym tłumaczeniu można zrozumieć jako "Zrób to prosto, idioto"
    Zasada ta mówi, że nasz kod ma być prosty.

    Zamiast pisać rozległą dużą metodę, która obsługuje wiele przypadków, podzielmy ją na
    mniejsze. W większości przypadków dodanie kolejnej metody praktycznie nie wpłynie na performance,
    a zyskamy na tym czytelność kodu.

     */

    public static class CalculatorUtils {

        // Wydzielenie kodu na metody upraszcza kod
        public static void method(int a, int b) {
            add(a,b);
            subtract(a,b);
            sum(a,b);
        }

        private static void sum(int a, int b) {
        }

        private static void subtract(int a, int b) {

        }

        private static void add(int a, int b) {

        }
    }


}
