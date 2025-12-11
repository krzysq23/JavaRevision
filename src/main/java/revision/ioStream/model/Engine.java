package revision.ioStream.model;

import java.io.Serial;
import java.io.Serializable;

public class Engine implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String type;

    public Engine(String name) {
        this.type = name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + type + '\'' +
                '}';
    }
}
