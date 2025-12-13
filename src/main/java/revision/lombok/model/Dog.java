package revision.lombok.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Dog {

    private @NonNull String name;

    // AccessLeve wyłącza dostep gettera
    @Getter(value = AccessLevel.NONE)
    private final Integer age;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Owner owner;

    public void consume(@NonNull String name) {
        System.out.println("Consume: " + name);
    }
}
