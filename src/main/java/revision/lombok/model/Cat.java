package revision.lombok.model;

import lombok.Value;

import java.util.List;

@Value
public class Cat {

    String name;

    String owner;

    Integer age;

    List<String> favouriteFood;
}
