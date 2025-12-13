package revision.lombok.model;

import lombok.*;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
@ToString
@With
public class Owner {

    private String name;

    private String surname;

    private Integer age;
}
