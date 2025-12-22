package revision.designPrinciples;

public class DRYExample {

    /*
    Design Principles (zasady projektowania) są abstrakcyjnymi zasadami, których należy przestrzegać
    podczas pisania oprogramowania.
     */

    /*
    DRY (Don’t Repeat Yourself Principle).
        - jeżeli piszemy jakąś logikę w kodzie, to ma mieć ona tylko jedno źródło prawdy
        w naszej aplikacji. Nie piszemy obok metody, która w sumie robi to samo i jest kopią metody z innego
        miejsca
     */

    static public abstract class Animal {
        public void eat() {
            System.out.println("eat");
        }
    }

    static public class Cat extends Animal {
        public void meow() {
            System.out.println("Meow!");
        }
        // Nie powtarzamy eat!
        public void eat() {
            System.out.println("eat");
        }
    }

    static public class  Dog extends Animal {
        // Nie powtarzamy eat!
        public void eat() {
            System.out.println("eat");
        }
        public void bark() {
            System.out.println("bark");
        }
    }

    static public class CalculatorUtils {
        // Stała jest przykładem DRY (zmienna taka to magic numbesr)
        public static final Integer MAX_LIST_SIZE = 1000;

        public static int add(int a, int b) {
            someMethod(1000);
            someMethod(MAX_LIST_SIZE);
            return a+b;
        }

        private static void someMethod(int someValue) {
        }
    }
}