package revision.designPatterns.creational;

public class SingletonExample {

    /*
        Klasa Singleton ma być odpowiedzialna za utworzenie siebie samej oraz wymuszenie istnienia tylko jednej instancji,
        można inaczej rozumieć ją jako globalną zmienną, która ma wystąpić tylko i wyłącznie raz w naszym programie.

        Singleton musi mieć:
        - prywatny konstruktor
        - instancja w zmiennej private static w tej klasie
        - statyczna metoda publiczna
        - klasa final

        Singleton może być:
        - eager -> tworzy instacje zawsze
        - lazy -> tworzy instacje kiedy go uzyjemy

        Uwaga! Singleton można używać w apliakcji jednowątkowej, w aplikacji wielowątkowej należy go dodatkowo zabezpieczyć!
        NAJLEPSZE I ZALECANE: enum Singleton
        public enum Singleton {
            INSTANCE;

            public void doSomething() {
                System.out.println("Hello from singleton");
            }
        }
        Singleton.INSTANCE.doSomething();
     */

    public static void main(String[] args) {

        System.out.println("\n===== StaticBlockSingleton ====");
        StaticBlockSingleton.someStaticMethod();

        System.out.println("\n===== LazyInitializationSingleton ====");
        LazyInitializationSingleton.someLazyStaticMethod();

        LazyInitializationSingleton instance1 = LazyInitializationSingleton.getInstance();
        System.out.println(instance1);
        LazyInitializationSingleton instance2 = LazyInitializationSingleton.getInstance();
        System.out.println(instance2);
        LazyInitializationSingleton instance3 = LazyInitializationSingleton.getInstance();
        System.out.println(instance3);
    }

    // Singleton Eager
    static class StaticBlockSingleton {

        private static final StaticBlockSingleton instance;

        static {
            try {
                instance = new StaticBlockSingleton();
            } catch (Exception e) {
                throw new RuntimeException("Exception thrown while creating instance");
            }
        }

        private StaticBlockSingleton() {
            System.out.println("Constructor StaticBlockSingleton");
        }

        public static StaticBlockSingleton getInstance() {
            System.out.println("GetInstance StaticBlockSingleton");
            return instance;
        }

        static void someStaticMethod() {
            System.out.println("Call someStaticMethod");
        }
    }

    // Singleton lazy
    static class LazyInitializationSingleton {

        private static LazyInitializationSingleton instance;

        private LazyInitializationSingleton() {
            System.out.println("Creation LazyInitializationSingleton");
        }

        public static LazyInitializationSingleton getInstance() {
            System.out.println("Initialization LazyInitializationSingleton");
            if(instance == null) {
                instance = new LazyInitializationSingleton();
            }
            return instance;
        }

        static void someLazyStaticMethod() {
            System.out.println("Call someLazyStaticMethod");
        }

    }

}