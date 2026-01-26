package revision.mocking.code;

import java.util.HashMap;
import java.util.Map;

public class UsersRepository {

    private final Map<String, User> USERS_MAP = new HashMap<>();

    public void put(final User user) {
        if (user.getPhone().length() > 7) {
            throw new RuntimeException("Phone number is too long");
        }
        if (USERS_MAP.containsKey(user.getPhone())) {
            throw new RuntimeException(
                    String.format("Phone: %s already exists in registry", user.getPhone()));
        }
        USERS_MAP.put(user.getPhone(), user);
    }

    public boolean contains(final String phone) {
        return USERS_MAP.containsKey(phone);
    }

    public User find(final String phone) {
        return USERS_MAP.get(phone);
    }

}
