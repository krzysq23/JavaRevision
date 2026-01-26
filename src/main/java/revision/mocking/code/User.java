package revision.mocking.code;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class User {

    String name;
    String surname;
    String phone;
    public boolean isValid() {
        return Objects.nonNull(name)
                && Objects.nonNull(surname)
                && Objects.nonNull(phone);
    }

}
