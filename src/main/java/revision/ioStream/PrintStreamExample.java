package revision.ioStream;

import revision.ioStream.model.Cat;

import java.io.*;

public class PrintStreamExample {

    public static void main(String[] args) throws IOException {

        // PrintStream PrintWriter - klasy te nie wyrzucają wyjatków
        // System.in - dane wejściowe np. wprowadzanie danych z klawiatury
        // System.out - dane wyjściowe np. wyświetlanie informacji na konsoli

        System.out.print("Java reversion\n");
        System.out.println("Java reversion");
        System.out.format("%s = %s %n", "Key", "Value");

        File file = new File("src/main/java/revision/ioStream/files/example.txt");
        try (
                PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file))))
        {
            writer.print(1L);
            writer.write(String.valueOf(1L));

            Car car = new Car("Stefan");
            writer.print(car);

            writer.println();
            writer.println("Java example");

            writer.printf("some value: [%s] %n", car);
            writer.printf("some value: [%s] %n", car);

        }

    }


    static class Car {
        private final String name;

        public Car(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
