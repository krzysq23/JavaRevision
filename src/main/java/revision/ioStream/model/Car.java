package revision.ioStream.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Car implements Serializable {

    // serialVersionUID nie jest serializowane
    @Serial
    private static final long serialVersionUID = -7678690514230413637L;

    private final String name;
    private final Integer year;
    // transient - pomija deserializacje
    //  private transient Model model;
    private final Engine model;
    private final transient List<Door> door;

    public Car(String name, Integer year, Engine model, List<Door> door) {
        this.name = name;
        this.year = year;
        this.model = model;
        this.door = door;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public Engine getModel() {
        return model;
    }

    public List<Door> getDoor() {
        return door;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", model=" + model +
                ", door=" + door +
                '}';
    }
}
