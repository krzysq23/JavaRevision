package revision.ioStream;

import revision.ioStream.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationExample {

    /*
     Serializacja to mechanizm zamiany obiektu na bajty,
     a deserializacja zamienia je z powrotem na obiekt
     — działa automatycznie dla klas implementujących Serializable.
     */

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File destinationCar = new File("src/main/java/revision/ioStream/files/car.txt");
        File destinationDog = new File("src/main/java/revision/ioStream/files/dog.txt");

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Ford mustang", 2020, new Engine("Diesel"),
                List.of(new Door("left"), new Door("right"))
        ));
        cars.add(new Car("BMW M3", 2023, new Engine("Petrol"),
                List.of(new Door("left"), new Door("right"))
        ));
        cars.add(new Car("Opel astra", 2021, new Engine("Petrol"),
                List.of(new Door("left"), new Door("right"))
        ));
        cars.add(new Car("Tesla model S", 2024, new Engine("Electric"),
                List.of(new Door("left"), new Door("right"))
        ));

//        serializeCars(cars, destinationCar);
        serialize(cars, destinationCar);

//        System.out.println(deserializeCars(destinationCar));
        System.out.println(deserialize(destinationCar));


        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Burek", "Burecki"));
        dogs.add(new Dog("Fafik", "Fafikowy"));
        dogs.add(new Dog("Aport", "Aporcinski"));

        System.out.println("\n\nSeriazlized\n");
        serialize(dogs, destinationDog);
        System.out.println("Deserialize\n");
        List<Dog> deserializeDogs =
                deserialize(destinationDog)
                        .stream()
                        .map(o -> (Dog) o)
                        .toList();
        System.out.println(deserializeDogs);
        System.out.println((deserializeDogs.get(0)));
        System.out.println(Dog.getAge());


        List<Persian> persian = new ArrayList<>();
        persian.add(new Persian("Kotek", "Płotek"));
        persian.add(new Persian("Lolek", "Kacapolek"));
        persian.add(new Persian("Filek", "Filemon"));

        System.out.println("\n\nSeriazlized\n");
        serialize(persian, destinationDog);
        System.out.println("Deserialize\n");
        List<Persian> deserializePersian =
                deserialize(destinationDog)
                        .stream()
                        .map(o -> (Persian) o)
                        .toList();
        System.out.println(deserializePersian);
        System.out.println((deserializePersian.get(0)));
    }

    private static List<Object> deserialize(File destination) throws IOException, ClassNotFoundException {
        List<Object> list = new ArrayList<>();
        try(
                ObjectInputStream inputStream = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(destination)
                        )
                )
        ) {
            while (true) {
                Object object = inputStream.readObject();
                if(object == null) {
                    System.out.println("Object is not Concrete!");
                    continue;
                }
                System.out.println("Successfully read object: " + object);
                list.add(object);
            }
        } catch (EOFException e) {
            System.out.println("End of file!");
        }
        return list;
    }

    private static void serialize(List<?> list, File destination) throws IOException {
        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(destination)
                        )
                )
        ) {
            for (Object object : list) {
                outputStream.writeObject(object);
            }
        }
    }

    private static List<Car> deserializeCars(File destination) throws IOException, ClassNotFoundException {
        List<Car> cars = new ArrayList<>();
        try(
                ObjectInputStream inputStream = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(destination)
                        )
                )
        ) {
            while (true) {
                Object object = inputStream.readObject();
                if(!(object instanceof Car car)) {
                    System.out.println("Object is not cars!");
                    continue;
                }
                System.out.println("Successfully read Car: " + car);
                cars.add(car);
            }
        } catch (EOFException e) {
            System.out.println("End of file!");
        }
        return cars;
    }

    private static void serializeCars(List<Car> cars, File destination) throws IOException {
        try (
                ObjectOutputStream outputStream = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(destination)
                        )
                )
        ) {
            for (Car car : cars) {
                outputStream.writeObject(car);
            }
        }
    }
}
