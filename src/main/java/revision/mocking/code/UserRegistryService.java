package revision.mocking.code;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRegistryService {

    private final UsersRepository usersRepository;

    public void register(User user) {
        if (!user.isValid() || usersRepository.contains(user.getPhone())) {
            throw new RuntimeException("Invalid user data");
        }
        usersRepository.put(user);
    }

    public Optional<User> find(String phone) {
        if (phone.isEmpty()) {
            throw new RuntimeException("Invalid phone number");
        }
        if (usersRepository.contains(phone)) {
            return Optional.ofNullable(usersRepository.find(phone));
        }
        return Optional.empty();
    }

}
